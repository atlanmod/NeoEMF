/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassDescriptor;
import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.structure.Namespace;
import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawElement;
import fr.inria.atlanmod.neoemf.io.structure.RawId;
import fr.inria.atlanmod.neoemf.io.structure.RawMetaclass;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;
import fr.inria.atlanmod.neoemf.io.util.PersistenceConstants;

import org.eclipse.emf.ecore.EClass;

import java.util.Objects;
import java.util.stream.IntStream;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 *
 */
@ParametersAreNonnullByDefault
public class DefaultPersistenceReader extends AbstractReader<PersistenceBackend> implements PersistenceReader {

    /**
     * The backend to read.
     */
    private PersistenceBackend backend;

    /**
     * Constructs a new {@code DefaultPersistenceReader} with the given {@code handlers}.
     *
     * @param handlers the handlers to notify
     */
    public DefaultPersistenceReader(Handler... handlers) {
        super(handlers);
    }

    @Override
    public void read(PersistenceBackend source) {
        backend = source;

        notifyInitialize();

        FeatureKey rootKey = FeatureKey.of(PersistenceConstants.ROOT_ID, PersistenceConstants.ROOT_FEATURE_NAME);
        source.allReferencesOf(rootKey).forEach(id -> readElement(id, true));

        notifyComplete();

        backend = null;
    }

    /**
     * Reads the element identified by its {@code id}.
     *
     * @param id the identifier of the element
     */
    protected void readElement(Id id) {
        readElement(id, false);
    }

    /**
     * Reads the element identified by its {@code id}.
     *
     * @param id     the identifier of the element
     * @param isRoot {@code true} if the element is a root element
     */
    protected void readElement(Id id, boolean isRoot) {
        // Retrieve the metaclass and namespace
        MetaclassDescriptor metaclass = backend.metaclassOf(id).orElseThrow(IllegalArgumentException::new);
        EClass realMetaclass = metaclass.eClass();

        Namespace ns = Namespace.Registry.getInstance().register(realMetaclass.getEPackage().getNsPrefix(), realMetaclass.getEPackage().getNsURI());

        // Retrieve the name of the element
        // If root it's the name of the metaclass, otherwise the name of the containing feature
        String elementName = isRoot ? metaclass.name() : backend.containerOf(id).map(ContainerDescriptor::name).orElse(null);

        // Create the element
        RawElement element = new RawElement(ns, elementName);
        element.id(RawId.original(id.toString()));
        element.metaClass(new RawMetaclass(ns, metaclass.name()));
        element.root(isRoot);

        // Retrieve the real name of this element
        String name = backend.valueOf(FeatureKey.of(id, PersistenceConstants.FEATURE_NAME)).map(Object::toString).orElse(null);
        element.className(name);

        notifyStartElement(element);

        // Process all attributes
        realMetaclass.getEAllAttributes().stream()
                .filter(attribute -> !Objects.equals(PersistenceConstants.FEATURE_NAME, attribute.getName())) // "name" has a special treatment
                .forEach(attribute -> {
                    FeatureKey key = FeatureKey.of(id, attribute.getName());
                    if (!attribute.isMany()) {
                        readValue(key);
                    }
                    else {
                        readAllValues(key);
                    }
                });

        // Process all references
        realMetaclass.getEAllReferences()
                .forEach(reference -> {
                    FeatureKey key = FeatureKey.of(id, reference.getName());
                    if (!reference.isMany()) {
                        readReference(key);
                    }
                    else {
                        readAllReferences(key);
                    }
                });

        notifyEndElement();
    }

    /**
     * Reads the single-valued attribute of the specified {@code key}.
     *
     * @param key the key identifying the attribute
     */
    protected void readValue(FeatureKey key) {
        backend.valueOf(key).ifPresent(value -> {
            RawAttribute attribute = new RawAttribute(key.name());
            attribute.id(RawId.original(key.id().toString()));
            attribute.value(value);

            notifyAttribute(attribute);
        });
    }

    /**
     * Reads all multi-valued attributes of the specified {@code key}.
     *
     * @param key the key identifying the attributes
     */
    protected void readAllValues(FeatureKey key) {
        int size = backend.sizeOfValue(key).orElse(0);

        IntStream.range(0, size).forEach(position ->
                backend.valueOf(key.withPosition(0)).ifPresent(value -> {
                    RawAttribute attribute = new RawAttribute(key.name());
                    attribute.id(RawId.original(key.id().toString()));
                    attribute.value(value);
                    attribute.many(true);
                    attribute.index(position);

                    notifyAttribute(attribute);
                }));
    }

    /**
     * Reads the single-valued reference of the specified {@code key}.
     *
     * @param key the key identifying the reference
     */
    protected void readReference(FeatureKey key) {
        backend.referenceOf(key).ifPresent(id -> {
            RawReference reference = new RawReference(key.name());
            reference.id(RawId.original(key.id().toString()));
            reference.idReference(RawId.original(id.toString()));

            notifyReference(reference);

            next(key.id(), id);
        });
    }

    /**
     * Reads all multi-valued references of the specified {@code key}.
     *
     * @param key the key identifying the references
     */
    protected void readAllReferences(FeatureKey key) {
        int size = backend.sizeOfReference(key).orElse(0);

        IntStream.range(0, size).forEach(position ->
                backend.referenceOf(key.withPosition(position)).ifPresent(id -> {
                    RawReference reference = new RawReference(key.name());
                    reference.id(RawId.original(key.id().toString()));
                    reference.idReference(RawId.original(id.toString()));
                    reference.many(true);
                    reference.index(position);

                    notifyReference(reference);

                    next(key.id(), id);
                }));
    }

    /**
     * Reads the {@code next} element if {@code containerOf(next) == parent}.
     *
     * @param parent the parent identifier of {@code next}
     * @param next   the next identifier
     */
    protected void next(Id parent, Id next) {
        backend.containerOf(next).ifPresent(container -> {
            if (Objects.equals(container.id(), parent)) {
                readElement(next);
            }
        });
    }
}
