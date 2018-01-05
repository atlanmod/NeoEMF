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
import fr.inria.atlanmod.neoemf.data.query.CommonQueries;
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
import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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

        source.allReferencesOf(SingleFeatureBean.of(PersistentResource.ROOT_ID, -1))
                .to(CommonQueries::toStream)
                .forEach(this::readRoot);

        notifyComplete();
    }

    /**
     * Reads the root element identified by its {@code id}.
     *
     * @param id the identifier of the root element
     */
    private void readRoot(Id id) {
        readElement(id, null, null);
    }

    /**
     * Reads the element identified by its {@code id}.
     *
     * @param id          the identifier of the element
     * @param parentClass the instance of the parent element; if {@code parentClass != null} this implied that
     *                    {@code id} represents a containment element
     */
    private void readElement(Id id, @Nullable EClass parentClass, @Nullable SingleFeatureBean container) {
        // Retrieve the meta-class and namespace
        EClass eClass = mapper.metaClassOf(id)
                .map(ClassBean::get)
                .toSingle()
                .blockingGet();

        EPackage ePackage = eClass.getEPackage();
        BasicNamespace ns = BasicNamespace.Registry.getInstance().register(ePackage.getNsPrefix(), ePackage.getNsURI());
        ns.ePackage(ePackage);

        // Retrieve the name of the element
        // If root it's the name of the meta-class, otherwise the name of the containing feature from the previous class
        String name;
        if (nonNull(parentClass) && nonNull(container)) {
            name = Optional.of(container)
                    .map(SingleFeatureBean::id)
                    .map(parentClass::getEStructuralFeature)
                    .map(EStructuralFeature::getName)
                    .<IllegalStateException>orElseThrow(IllegalStateException::new);
        }
        else {
            // Element is root
            name = eClass.getName();
        }

        // Create the element
        BasicElement element = new BasicElement();
        element.name(name);
        element.id(id);
        element.isRoot(isNull(parentClass));

        BasicMetaclass metaClass = new BasicMetaclass(ns);
        metaClass.eClass(eClass);
        element.metaClass(metaClass);

        notifyStartElement(element);

        readAllFeatures(id, eClass);

        notifyEndElement();
    }

    /**
     * Reads all features of the specified {@code eClass} for the given {@code id}.
     *
     * @param id     the identifier of the element
     * @param eClass the meta-class of the element
     */
    private void readAllFeatures(Id id, EClass eClass) {
        eClass.getEAllStructuralFeatures().stream()
                .flatMap(f -> readFeature(id, eClass, f))
                .collect(Collectors.toList()) // Blocking collect to keep hierarchy
                .forEach(next -> readNext(id, eClass, next));
    }

    /**
     * Reads the {@code eFeature} of the specified {@code eClass} for the given {@code id}.
     *
     * @param id       the identifier of the element
     * @param eClass   the meta-class of the element
     * @param eFeature the current feature of the element
     *
     * @return a stream over containment elements if {@code eFeature} is a containment reference, otherwise a
     * {@link Stream#empty()}
     */
    @Nonnull
    private Stream<Id> readFeature(Id id, EClass eClass, EStructuralFeature eFeature) {
        SingleFeatureBean key = SingleFeatureBean.of(id, eClass.getFeatureID(eFeature));

        if (EObjects.isAttribute(eFeature)) {
            readAttribute(key, EObjects.asAttribute(eFeature));
            return Stream.empty();
        }
        else {
            return readReference(key, EObjects.asReference(eFeature));
        }
    }

    /**
     * Reads the {@code eAttribute} of the specified {@code key}.
     *
     * @param key        the key identifying the current element
     * @param eAttribute the current attribute of the element
     */
    private void readAttribute(SingleFeatureBean key, EAttribute eAttribute) {
        Stream<Object> stream;

        if (!eAttribute.isMany()) {
            stream = mapper.valueOf(key).to(CommonQueries::toStream);
        }
        else {
            stream = mapper.allValuesOf(key).to(CommonQueries::toStream);
        }

        stream.forEach(v -> createAttribute(key, eAttribute, v));
    }

    /**
     * Reads the {@code eReference} of the specified {@code key}.
     *
     * @param key        the key identifying the current element
     * @param eReference the current reference of the element
     *
     * @return a stream over containment elements if {@code eReference} is a containment reference
     */
    @Nonnull
    private Stream<Id> readReference(SingleFeatureBean key, EReference eReference) {
        Stream<Id> stream;

        if (!eReference.isMany()) {
            stream = mapper.referenceOf(key).to(CommonQueries::toStream);
        }
        else {
            stream = mapper.allReferencesOf(key).to(CommonQueries::toStream);
        }

        List<Id> containments = stream
                .map(r -> createReference(key, eReference, r))
                .collect(Collectors.toList());

        return eReference.isContainment()
                ? containments.stream()
                : Stream.empty();
    }

    /**
     * Reads the next element, identified by {@code next}, only if {@code containerOf(next).id == id}.
     *
     * @param current the identifier of the current element
     * @param next    the identifier of the next element
     */
    private void readNext(Id current, EClass eClass, Id next) {
        mapper.containerOf(next)
                .filter(c -> Objects.equals(c.owner(), current))
                .to(CommonQueries::toOptional)
                .ifPresent(c -> readElement(next, eClass, c));
    }

    /**
     * Creates and notifies a new attribute.
     *
     * @param key        the owner of the attribute
     * @param eAttribute the associated EMF attribute
     * @param value      the value of the attribute
     */
    private void createAttribute(SingleFeatureBean key, EAttribute eAttribute, Object value) {
        BasicAttribute attribute = new BasicAttribute();
        attribute.owner(key.owner());
        attribute.id(key.id());
        attribute.eFeature(eAttribute);
        attribute.value(value);

        notifyAttribute(attribute);
    }

    /**
     * Creates and notify a new reference.
     *
     * @param key        the owner of the reference
     * @param eReference the associated EMF reference
     * @param value      the value of the reference
     *
     * @return the identifier of the referenced element if the reference is a containment, {@code null} otherwise
     */
    @Nullable
    private Id createReference(SingleFeatureBean key, EReference eReference, Id value) {
        BasicReference reference = new BasicReference();
        reference.owner(key.owner());
        reference.id(key.id());
        reference.eFeature(eReference);
        reference.value(value);

        notifyReference(reference);

        return eReference.isContainment() ? value : null;
    }
}
