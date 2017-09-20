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
import fr.inria.atlanmod.neoemf.io.bean.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.bean.BasicNamespace;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.EObjects;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The default implementation of a {@link Reader} that reads data from a {@link DataMapper}.
 */
@ParametersAreNonnullByDefault
public class DefaultMapperReader extends AbstractReader<DataMapper> {

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

        SingleFeatureBean rootKey = SingleFeatureBean.of(PersistentResource.ROOT_ID, PersistentResource.ROOT_REFERENCE_NAME);
        source.allReferencesOf(rootKey).forEach(id -> readElement(id, true));

        notifyComplete();
    }

    /**
     * Reads the element identified by its {@code id}.
     *
     * @param id     the identifier of the element
     * @param isRoot {@code true} if the element is a root element
     */
    private void readElement(Id id, boolean isRoot) {
        // Retrieve the meta-class and namespace
        EClass eClass = mapper.metaClassOf(id)
                .map(ClassBean::get)
                .<IllegalArgumentException>orElseThrow(IllegalArgumentException::new);

        EPackage ePackage = eClass.getEPackage();
        BasicNamespace ns = BasicNamespace.Registry.getInstance().register(ePackage.getNsPrefix(), ePackage.getNsURI());
        ns.ePackage(ePackage);

        // Retrieve the name of the element
        // If root it's the name of the meta-class, otherwise the name of the containing feature
        String name = isRoot ? eClass.getName() : mapper.containerOf(id)
                .map(SingleFeatureBean::id)
                .<IllegalStateException>orElseThrow(IllegalStateException::new);

        // Create the element
        BasicElement element = new BasicElement();
        element.name(name);
        element.id(id);
        element.isRoot(isRoot);

        BasicMetaclass metaClass = new BasicMetaclass(ns, eClass.getName());
        metaClass.eClass(eClass);
        element.metaClass(metaClass);

        notifyStartElement(element);

        // Process all features
        readAllFeatures(id, eClass);

        notifyEndElement();
    }

    /**
     * Reads all features of the speficied {@code eClass} for the given {@code id}.
     *
     * @param id     the identifier of the element
     * @param eClass the meta-class of the element
     */
    private void readAllFeatures(Id id, EClass eClass) {
        // Read all feature of the element, and notify the next handler
        List<Id> containmentId = eClass.getEAllStructuralFeatures().stream()
                .flatMap(f -> {
                    Stream<Id> containmentStream = Stream.empty();

                    SingleFeatureBean key = SingleFeatureBean.of(id, f.getName());

                    if (EObjects.isAttribute(f)) {
                        EAttribute eAttribute = EObjects.asAttribute(f);

                        if (!f.isMany()) {
                            readValue(key, eAttribute);
                        }
                        else {
                            readAllValues(key, eAttribute);
                        }
                    }
                    else {
                        EReference eReference = EObjects.asReference(f);
                        boolean isContainment = eReference.isContainment();

                        if (!f.isMany()) {
                            Optional<Id> r = readReference(key, eReference, isContainment);
                            if (isContainment) {
                                containmentStream = r.map(Stream::of).orElseGet(Stream::empty);
                            }
                        }
                        else {
                            List<Id> rs = readAllReferences(key, eReference, isContainment);
                            if (isContainment) {
                                containmentStream = rs.stream();
                            }
                        }
                    }

                    return containmentStream;
                })
                .collect(Collectors.toList());

        // Read the next element only if containerOf(next) == parent
        containmentId.forEach(r -> mapper.containerOf(r)
                .filter(c -> Objects.equals(c.owner(), id))
                .ifPresent(c -> readElement(r, false)));
    }

    /**
     * Reads the single-valued attribute of the specified {@code key}.
     *
     * @param key the key identifying the attribute
     */
    private void readValue(SingleFeatureBean key, EAttribute eAttribute) {
        mapper.valueOf(key).ifPresent(value -> {
            BasicAttribute attribute = new BasicAttribute();
            attribute.name(key.id());
            attribute.owner(key.owner());
            attribute.eFeature(eAttribute);
            attribute.value(value);

            notifyAttribute(attribute);
        });
    }

    /**
     * Reads the multi-valued attribute of the specified {@code key}.
     *
     * @param key the key identifying the attributes
     */
    private void readAllValues(SingleFeatureBean key, EAttribute eAttribute) {
        mapper.allValuesOf(key)
                .forEach(value -> {
                    BasicAttribute attribute = new BasicAttribute();
                    attribute.name(key.id());
                    attribute.owner(key.owner());
                    attribute.value(value);
                    attribute.eFeature(eAttribute);
                    attribute.isMany(true);

                    notifyAttribute(attribute);
                });
    }

    /**
     * Reads the single-valued reference of the specified {@code key}.
     *
     * @param key           the key identifying the reference
     * @param isContainment {@code true} if the reference is a containment
     *
     * @return an {@link Optional} containing the reference, or {@link Optional#empty()} if the reference is not defined
     * or if {@code isContainment == false}
     */
    @Nonnull
    private Optional<Id> readReference(SingleFeatureBean key, EReference eReference, boolean isContainment) {
        return mapper.referenceOf(key)
                .map(id -> {
                    BasicReference reference = new BasicReference();
                    reference.name(key.id());
                    reference.owner(key.owner());
                    reference.value(id);
                    reference.eFeature(eReference);
                    reference.isContainment(isContainment);

                    notifyReference(reference);

                    return isContainment ? id : null;
                });
    }

    /**
     * Reads the multi-valued reference of the specified {@code key}.
     *
     * @param key           the key identifying the reference
     * @param isContainment {@code true} if the reference is a containment
     *
     * @return a list of all references, or an empty list if the reference is not defined or if {@code isContainment ==
     * false}
     */
    @Nonnull
    private List<Id> readAllReferences(SingleFeatureBean key, EReference eReference, boolean isContainment) {
        return mapper.allReferencesOf(key).stream()
                .map(id -> {
                    BasicReference reference = new BasicReference();
                    reference.name(key.id());
                    reference.owner(key.owner());
                    reference.value(id);
                    reference.eFeature(eReference);
                    reference.isMany(true);
                    reference.isContainment(isContainment);

                    notifyReference(reference);

                    return isContainment ? id : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
