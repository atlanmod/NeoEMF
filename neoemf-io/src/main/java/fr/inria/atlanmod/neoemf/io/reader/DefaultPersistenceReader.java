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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import java.util.Objects;
import java.util.stream.IntStream;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 *
 */
@ParametersAreNonnullByDefault
public class DefaultPersistenceReader extends AbstractReader<PersistenceBackend> implements PersistenceReader {

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
        notifyInitialize();

        FeatureKey rootKey = FeatureKey.of(PersistenceConstants.ROOT_ID, PersistenceConstants.ROOT_FEATURE_NAME);
        source.allReferencesOf(rootKey).forEach(id -> readElement(source, id, true));

        notifyComplete();
    }

    protected void readElement(PersistenceBackend backend, Id id, boolean isRoot) {
        MetaclassDescriptor metaclass = backend.metaclassOf(id).orElseThrow(IllegalArgumentException::new);
        EClass eClass = metaclass.eClass();
        Namespace ns = Namespace.Registry.getInstance().register(eClass.getEPackage().getNsPrefix(), eClass.getEPackage().getNsURI());

        String name = isRoot ? metaclass.name() : backend.containerOf(id).map(ContainerDescriptor::name).orElse(null);

        RawElement element = new RawElement(ns, name);
        element.id(RawId.original(id.toString()));
        element.metaClass(new RawMetaclass(ns, metaclass.name()));
        element.className(backend.valueOf(FeatureKey.of(id, PersistenceConstants.FEATURE_NAME)).map(Object::toString).orElse(null));
        element.root(isRoot);

        notifyStartElement(element);

        eClass.getEAttributes().stream()
                .filter(a -> !Objects.equals(PersistenceConstants.FEATURE_NAME, a.getName()))
                .forEach(a -> readAttributes(backend, id, a));

        eClass.getEReferences()
                .forEach(r -> readReferences(backend, id, r));

        notifyEndElement();
    }

    protected void readAttributes(PersistenceBackend backend, Id id, EAttribute eAttribute) {
        FeatureKey key = FeatureKey.of(id, eAttribute.getName());

        if (!eAttribute.isMany()) {
            backend.valueOf(key).ifPresent(value -> {

                RawAttribute rawAttribute = new RawAttribute(key.name());
                rawAttribute.id(RawId.original(id.toString()));
                rawAttribute.value(value);

                notifyAttribute(rawAttribute);
            });
        }
        else {
            IntStream.range(0, backend.sizeOfValue(key).orElse(0)).forEach(position ->
                    backend.valueOf(key.withPosition(0)).ifPresent(value -> {

                        RawAttribute rawAttribute = new RawAttribute(key.name());
                        rawAttribute.id(RawId.original(key.id().toString()));
                        rawAttribute.value(value);
                        rawAttribute.many(true);
                        rawAttribute.index(position);

                        notifyAttribute(rawAttribute);
                    }));
        }
    }

    protected void readReferences(PersistenceBackend backend, Id id, EReference eReference) {
        FeatureKey key = FeatureKey.of(id, eReference.getName());

        if (!eReference.isMany()) {
            backend.referenceOf(key).ifPresent(idReference -> {

                RawReference rawReference = new RawReference(key.name());
                rawReference.id(RawId.original(key.id().toString()));
                rawReference.idReference(RawId.original(idReference.toString()));
                rawReference.containment(false);

                notifyReference(rawReference);

                backend.containerOf(idReference).ifPresent(c -> {
                    RawReference reverseReference = new RawReference(c.name());
                    reverseReference.id(RawId.original(idReference.toString()));
                    reverseReference.idReference(RawId.original(key.id().toString()));
                    reverseReference.containment(true);

                    notifyReference(reverseReference);

                    if (Objects.equals(c.id(), id)) {
                        readElement(backend, idReference, false);
                    }
                });
            });
        }
        else {
            IntStream.range(0, backend.sizeOfReference(key).orElse(0)).forEach(position ->
                    backend.referenceOf(key.withPosition(position)).ifPresent(idReference -> {

                        RawReference rawReference = new RawReference(key.name());
                        rawReference.id(RawId.original(key.id().toString()));
                        rawReference.idReference(RawId.original(idReference.toString()));
                        rawReference.containment(false);
                        rawReference.many(true);
                        rawReference.index(position);

                        notifyReference(rawReference);

                        backend.containerOf(idReference).ifPresent(c -> {
                            RawReference reverseReference = new RawReference(c.name());
                            reverseReference.id(RawId.original(idReference.toString()));
                            reverseReference.idReference(RawId.original(key.id().toString()));
                            reverseReference.containment(true);

                            notifyReference(reverseReference);

                            if (Objects.equals(c.id(), id)) {
                                readElement(backend, idReference, false);
                            }
                        });
                    }));
        }
    }
}
