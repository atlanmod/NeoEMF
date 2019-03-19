/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.AbstractFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyElement;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyClass;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyValue;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.EFeatures;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
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

    @Override
    public void read(DataMapper source) throws IOException {
        mapper = source;

        notifyInitialize();

        SingleFeatureBean rootKey = SingleFeatureBean.of(PersistentResource.ROOT_ID, -1);
        Iterable<Id> rootReferences = source.allReferencesOf(rootKey).collect(Collectors.toList());
        for (Id id : rootReferences) {
            readElement(id, true);
        }

        notifyComplete();
    }

    /**
     * Reads the element identified by its {@code id}.
     *
     * @param id     the identifier of the element
     * @param isRoot {@code true} if the element is a root element
     */
    private void readElement(Id id, boolean isRoot) throws IOException {
        // Retrieve the meta-class and namespace
        EClass eClass = mapper.metaClassOf(id)
                .map(ClassBean::get)
                .orElseThrow(IllegalArgumentException::new);

        // Retrieve the name of the element
        // If root it's the name of the meta-class, otherwise the name of the containing feature from the previous class
        String name = isRoot ? eClass.getName() : mapper.containerOf(id)
                .map(SingleFeatureBean::id)
                .map(classes.getLast()::getEStructuralFeature)
                .map(EStructuralFeature::getName)
                .orElseThrow(IllegalStateException::new);

        // Create the meta-class
        ProxyClass metaClass = new ProxyClass(eClass);

        // Create the element
        ProxyElement element = new ProxyElement()
                .setName(name)
                .setId(ProxyValue.resolved(id))
                .setRoot(isRoot)
                .setMetaClass(metaClass);

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
    private void readAllFeatures(Id id, EClass eClass) throws IOException {
        List<Id> containments = new ArrayList<>();

        // Read all feature of the element, and notify the next handler
        for (EStructuralFeature f : eClass.getEAllStructuralFeatures()) {
            containments.addAll(readFeature(id, eClass, f));
        }

        // Read the next element only if containerOf(next) == parent
        for (Id r : containments) {
            if (mapper.containerOf(r).map(AbstractFeatureBean::owner).filter(id::equals).isPresent()) {
                readElement(r, false);
            }
        }
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
    private List<Id> readFeature(Id id, EClass eClass, EStructuralFeature eFeature) throws IOException {
        final SingleFeatureBean bean = SingleFeatureBean.of(id, eClass.getFeatureID(eFeature));

        if (EFeatures.isAttribute(eFeature)) {
            readAttribute(bean, EFeatures.asAttribute(eFeature));
            return Collections.emptyList();
        }
        else {
            return readReference(bean, EFeatures.asReference(eFeature));
        }
    }

    /**
     * Reads the value(s) of the {@code eAttribute} for the given {@code feature}.
     *
     * @param feature    the owner of the attribute
     * @param eAttribute the associated EMF attribute
     */
    private void readAttribute(SingleFeatureBean feature, EAttribute eAttribute) throws IOException {
        if (!eAttribute.isMany()) {
            Optional<Object> value = mapper.valueOf(feature);
            if (value.isPresent()) {
                createAttribute(feature, eAttribute, value.get());
            }
        }
        else {
            Iterable<Object> values = mapper.allValuesOf(feature).collect(Collectors.toList());
            for (Object v : values) {
                createAttribute(feature, eAttribute, v);
            }
        }
    }

    /**
     * Reads the value(s) of the {@code eReference} for the given {@code feature}.
     *
     * @param feature    the owner of the reference
     * @param eReference the associated EMF reference
     *
     * @return a list of containment references
     */
    @Nonnull
    private List<Id> readReference(SingleFeatureBean feature, EReference eReference) throws IOException {
        boolean isContainment = eReference.isContainment();

        if (!eReference.isMany()) {
            Optional<Id> reference = mapper.referenceOf(feature);
            if (reference.isPresent()) {
                createReference(feature, eReference, reference.get());
            }

            if (isContainment) {
                return reference.map(Collections::singletonList).orElseGet(Collections::emptyList);
            }
        }
        else {
            List<Id> references = mapper.allReferencesOf(feature).collect(Collectors.toList());

            List<Id> containments = new ArrayList<>();

            for (Id r : references) {
                Optional<Id> id = Optional.ofNullable(createReference(feature, eReference, r));
                if (isContainment && id.isPresent()) {
                    containments.add(id.get());
                }
            }

            if (isContainment) {
                return Collections.unmodifiableList(containments);
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
    private void createAttribute(SingleFeatureBean feature, EAttribute eAttribute, Object value) throws IOException {
        checkFeatureMap(eAttribute);

        ProxyAttribute attribute = new ProxyAttribute()
                .setOwner(feature.owner())
                .setId(feature.id())
                .setOrigin(eAttribute)
                .setValue(ProxyValue.resolved(value));

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
    private Id createReference(SingleFeatureBean feature, EReference eReference, Id value) throws IOException {
        checkFeatureMap(eReference);

        ProxyReference reference = new ProxyReference()
                .setOwner(feature.owner())
                .setId(feature.id())
                .setOrigin(eReference)
                .setValue(ProxyValue.resolved(value));

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
