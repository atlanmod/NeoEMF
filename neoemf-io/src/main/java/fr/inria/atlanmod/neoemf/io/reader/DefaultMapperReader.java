/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.bean.BasicNamespace;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;
import fr.inria.atlanmod.neoemf.io.processor.Processor;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.EObjects;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The default implementation of a {@link Reader} that reads data from a {@link fr.inria.atlanmod.neoemf.data.mapping.DataMapper}.
 */
@ParametersAreNonnullByDefault
public class DefaultMapperReader extends AbstractReader<DataMapper> {

    /**
     * A LIFO that holds the current {@link EClass} chain. It contains the current element and the previous.
     */
    @Nonnull
    private final Deque<EClass> classes = new ArrayDeque<>();
    /**
     * The mapper to read.
     */
    private DataMapper mapper;

    /**
     * Constructs a new {@code DefaultMapperReader} with the given {@code processor}.
     *
     * @param processor the processor to notify
     */
    public DefaultMapperReader(Processor processor) {
        super(processor);
    }

    @Override
    public void read(DataMapper source) {
        mapper = source;

        notifyInitialize();

        SingleFeatureBean rootKey = SingleFeatureBean.of(PersistentResource.ROOT_ID, -1);
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

        BasicNamespace ns = BasicNamespace.Registry.getInstance().register(eClass.getEPackage());

        // Retrieve the name of the element
        // If root it's the name of the meta-class, otherwise the name of the containing feature from the previous class
        String name = isRoot ? eClass.getName() : mapper.containerOf(id)
                .map(SingleFeatureBean::id)
                .map(classes.getLast()::getEStructuralFeature)
                .map(EStructuralFeature::getName)
                .<IllegalStateException>orElseThrow(IllegalStateException::new);

        // Create the meta-class
        BasicMetaclass metaClass = new BasicMetaclass(ns)
                .eClass(eClass);

        // Create the element
        BasicElement element = new BasicElement()
                .name(name)
                .id(id)
                .isRoot(isRoot)
                .metaClass(metaClass);

        notifyStartElement(element);
        classes.addLast(eClass);

        // Process all features
        readAllFeatures(id, eClass);

        classes.removeLast();
        notifyEndElement();
    }

    /**
     * Reads all values of the features of the {@code eClass} for the given {@code id}.
     *
     * @param id     the identifier of the element
     * @param eClass the meta-class of the element
     */
    private void readAllFeatures(Id id, EClass eClass) {
        // Read all feature of the element, and notify the next handler
        // Read the next element only if containerOf(next) == parent
        eClass.getEAllStructuralFeatures().stream()
                .map(f -> readFeature(id, eClass, f))
                .flatMap(List::stream)
                .collect(Collectors.toList())
                .forEach(r -> mapper.containerOf(r)
                        .filter(c -> Objects.equals(c.owner(), id))
                        .ifPresent(c -> readElement(r, false)));
    }

    /**
     * Reads the value(s) of the {@code eFeature} for the given {@code id}.
     *
     * @param id       the identifier of the element
     * @param eClass   the identifier of the element
     * @param eFeature the feature
     *
     * @return a stream of containment references
     */
    @Nonnull
    private List<Id> readFeature(Id id, EClass eClass, EStructuralFeature eFeature) {
        final SingleFeatureBean bean = SingleFeatureBean.of(id, eClass.getFeatureID(eFeature));

        if (EObjects.isAttribute(eFeature)) {
            readAttribute(bean, EObjects.asAttribute(eFeature));
            return Collections.emptyList();
        }
        else {
            return readReference(bean, EObjects.asReference(eFeature));
        }
    }

    /**
     * Reads the value(s) of the {@code eAttribute} for the given {@code bean}.
     *
     * @param feature    the owner of the attribute
     * @param eAttribute the associated EMF attribute
     */
    private void readAttribute(SingleFeatureBean feature, EAttribute eAttribute) {
        if (!eAttribute.isMany()) {
            mapper.valueOf(feature).ifPresent(v -> createAttribute(feature, eAttribute, v));
        }
        else {
            mapper.allValuesOf(feature).forEach(v -> createAttribute(feature, eAttribute, v));
        }
    }

    /**
     * Reads the value(s) of the {@code eReference} for the given {@code bean}.
     *
     * @param feature    the owner of the reference
     * @param eReference the associated EMF reference
     *
     * @return a list of containment references
     */
    @Nonnull
    private List<Id> readReference(SingleFeatureBean feature, EReference eReference) {
        boolean isContainment = eReference.isContainment();

        if (!eReference.isMany()) {
            Optional<Id> reference = mapper.referenceOf(feature)
                    .map(r -> createReference(feature, eReference, r));

            if (isContainment) {
                return reference.map(Collections::singletonList).orElseGet(Collections::emptyList);
            }
        }
        else {
            List<Id> references = mapper.allReferencesOf(feature)
                    .map(r -> createReference(feature, eReference, r))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            if (isContainment) {
                return Collections.unmodifiableList(references);
            }
        }

        return Collections.emptyList();
    }

    /**
     * Creates and notifies a new attribute.
     *
     * @param feature    the owner of the attribute
     * @param eAttribute the associated EMF attribute
     * @param value      the value of the attribute
     */
    private void createAttribute(SingleFeatureBean feature, EAttribute eAttribute, Object value) {
        checkFeatureMap(eAttribute);

        BasicAttribute attribute = new BasicAttribute()
                .owner(feature.owner())
                .id(feature.id())
                .eFeature(eAttribute)
                .value(value);

        notifyAttribute(attribute);
    }

    /**
     * Creates and notify a new reference.
     *
     * @param feature    the owner of the reference
     * @param eReference the associated EMF reference
     * @param value      the value of the reference
     *
     * @return the identifier of the referenced element if the reference is a containment, {@code null} otherwise
     */
    @Nullable
    private Id createReference(SingleFeatureBean feature, EReference eReference, Id value) {
        checkFeatureMap(eReference);

        BasicReference reference = new BasicReference()
                .owner(feature.owner())
                .id(feature.id())
                .eFeature(eReference)
                .value(value);

        notifyReference(reference);

        return eReference.isContainment() ? value : null;
    }

    /**
     * Ensures that the {@code eFeature} is not a {@link org.eclipse.emf.ecore.util.FeatureMap}. They are not supported
     * yet.
     *
     * @param eFeature the EMF feature to test
     *
     * @throws UnsupportedOperationException if the feature is a feature map
     */
    private void checkFeatureMap(EStructuralFeature eFeature) {
        if (FeatureMapUtil.isFeatureMap(eFeature)) {
            throw new UnsupportedOperationException("FeatureMaps are not supported yet: Use standard EMF to export your model");
        }
    }
}
