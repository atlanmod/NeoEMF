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
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicId;
import fr.inria.atlanmod.neoemf.io.bean.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.bean.BasicNamespace;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;
import fr.inria.atlanmod.neoemf.io.util.MapperConstants;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The default implementation of a {@link MapperReader}.
 */
@ParametersAreNonnullByDefault
public class DefaultMapperReader extends AbstractReader<DataMapper> implements MapperReader {

    /**
     * The mapper to read.
     */
    private DataMapper mapper;

    /**
     * Constructs a new {@code DefaultMapperReader} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public DefaultMapperReader(Handler handler) {
        super(handler);
    }

    @Override
    public void read(DataMapper source) {
        mapper = source;

        notifyInitialize();

        SingleFeatureBean rootKey = SingleFeatureBean.of(MapperConstants.ROOT_ID, MapperConstants.ROOT_FEATURE_NAME);
        source.allReferencesOf(rootKey).forEach(id -> readElement(id, true));

        notifyComplete();

        mapper = null;
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
        // Retrieve the meta-class and namespace
        EClass metaClass = mapper.metaClassOf(id)
                .map(ClassBean::get)
                .<IllegalArgumentException>orElseThrow(IllegalArgumentException::new);

        EPackage ePackage = metaClass.getEPackage();
        BasicNamespace ns = BasicNamespace.Registry.getInstance().register(ePackage.getNsPrefix(), ePackage.getNsURI());

        // Retrieve the name of the element
        // If root it's the name of the meta-class, otherwise the name of the containing feature
        String elementName = isRoot ? metaClass.getName() : mapper.containerOf(id)
                .map(SingleFeatureBean::id)
                .<IllegalStateException>orElseThrow(IllegalStateException::new);

        // Create the element
        BasicElement element = new BasicElement(ns, elementName);
        element.id(BasicId.original(id.toString()));
        element.metaClass(new BasicMetaclass(ns, metaClass.getName()));
        element.isRoot(isRoot);

        // Retrieve the real "name" of this element
        mapper.valueOf(SingleFeatureBean.of(id, MapperConstants.FEATURE_NAME))
                .map(Object::toString)
                .ifPresent(element::className);

        notifyStartElement(element);

        // Process all attributes
        metaClass.getEAllAttributes().stream()
                .filter(a -> !Objects.equals(MapperConstants.FEATURE_NAME, a.getName())) // "name" has a special treatment
                .forEach(a -> {
                    SingleFeatureBean key = SingleFeatureBean.of(id, a.getName());
                    if (!a.isMany()) {
                        readValue(key);
                    }
                    else {
                        readAllValues(key);
                    }
                });

        // Process all references
        metaClass.getEAllReferences()
                .forEach(r -> {
                    SingleFeatureBean key = SingleFeatureBean.of(id, r.getName());
                    if (!r.isMany()) {
                        readReference(key, r.isContainment());
                    }
                    else {
                        readAllReferences(key, r.isContainment());
                    }
                });

        notifyEndElement();
    }

    /**
     * Reads the single-valued attribute of the specified {@code key}.
     *
     * @param key the key identifying the attribute
     */
    protected void readValue(SingleFeatureBean key) {
        mapper.<String>valueOf(key).ifPresent(value -> {
            BasicAttribute attribute = new BasicAttribute();
            attribute.name(key.id());
            attribute.owner(BasicId.original(key.owner().toString()));
            attribute.value(value);

            notifyAttribute(attribute);
        });
    }

    /**
     * Reads the multi-valued attribute of the specified {@code key}.
     *
     * @param key the key identifying the attributes
     */
    protected void readAllValues(SingleFeatureBean key) {
        final List<String> values = mapper.allValuesOf(key);

        IntStream.range(0, values.size()).forEach(position ->
                Optional.ofNullable(values.get(position)).ifPresent(value -> {
                    BasicAttribute attribute = new BasicAttribute();
                    attribute.name(key.id());
                    attribute.owner(BasicId.original(key.owner().toString()));
                    attribute.value(value);
                    attribute.index(position);
                    attribute.isMany(true);

                    notifyAttribute(attribute);
                }));
    }

    /**
     * Reads the single-valued reference of the specified {@code key}.
     *
     * @param key           the key identifying the reference
     * @param isContainment {@code true} if the reference is a containment
     */
    protected void readReference(SingleFeatureBean key, boolean isContainment) {
        mapper.referenceOf(key).ifPresent(id -> {
            BasicReference reference = new BasicReference();
            reference.name(key.id());
            reference.owner(BasicId.original(key.owner().toString()));
            reference.idReference(BasicId.original(id.toString()));
            reference.isContainment(isContainment);

            mapper.metaClassOf(id).ifPresent(m -> {
                BasicNamespace ns = BasicNamespace.Registry.getInstance().getFromUri(m.uri());
                reference.metaClassReference(new BasicMetaclass(ns, m.name()));
            });

            notifyReference(reference);

            next(key.owner(), id);
        });
    }

    /**
     * Reads the multi-valued reference of the specified {@code key}.
     *
     * @param key           the key identifying the reference
     * @param isContainment {@code true} if the reference is a containment
     */
    protected void readAllReferences(SingleFeatureBean key, boolean isContainment) {
        final List<Id> references = mapper.allReferencesOf(key);

        IntStream.range(0, references.size()).forEach(position ->
                Optional.ofNullable(references.get(position)).ifPresent(id -> {
                    BasicReference reference = new BasicReference();
                    reference.name(key.id());
                    reference.owner(BasicId.original(key.owner().toString()));
                    reference.idReference(BasicId.original(id.toString()));
                    reference.index(position);
                    reference.isContainment(isContainment);
                    reference.isMany(true);

                    mapper.metaClassOf(id).ifPresent(m -> {
                        BasicNamespace ns = BasicNamespace.Registry.getInstance().getFromUri(m.uri());
                        reference.metaClassReference(new BasicMetaclass(ns, m.name()));
                    });

                    notifyReference(reference);

                    next(key.owner(), id);
                }));
    }

    /**
     * Reads the {@code next} element if {@code containerOf(next) == parent}.
     *
     * @param parent the parent identifier of {@code next}
     * @param next   the next identifier
     */
    protected void next(Id parent, Id next) {
        mapper.containerOf(next)
                .filter(c -> Objects.equals(c.owner(), parent))
                .ifPresent(container -> readElement(next));
    }
}
