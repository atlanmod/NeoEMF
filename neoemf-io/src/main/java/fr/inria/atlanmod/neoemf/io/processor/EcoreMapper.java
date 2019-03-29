/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyElement;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyClass;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyValue;
import fr.inria.atlanmod.neoemf.util.EFeatures;

import org.atlanmod.commons.primitive.Strings;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;
import static org.atlanmod.commons.Preconditions.checkArgument;
import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A {@link Processor} that transforms simple elements to an EMF structure.
 */
@ParametersAreNonnullByDefault
public class EcoreMapper extends AbstractProcessor {

    /**
     * A LIFO that holds the current {@link Id} chain.
     */
    @Nonnull
    private final Deque<Id> identifiers = new ArrayDeque<>();

    /**
     * A LIFO that holds the current meta-class chain.
     */
    @Nonnull
    private final Deque<EClass> metaClasses = new ArrayDeque<>();

    @Override
    public void onStartElement(ProxyElement element) throws IOException {
        // Is root
        if (identifiers.isEmpty()) {
            processElementAsRoot(element);
        }
        // Is a feature of parent
        else {
            processElementAsFeature(element);
        }
    }

    @Override
    public void onAttribute(ProxyAttribute attribute) throws IOException {
        final EStructuralFeature eFeature = getFeature(attribute.getName());

        if (isFeatureMap(eFeature)) {
            throw new UnsupportedOperationException("FeatureMaps are not supported yet: Use standard EMF to import your model");
        }

        if (EFeatures.isAttribute(eFeature)) {
            // The attribute is well a attribute
            processAttribute(attribute, EFeatures.asAttribute(eFeature));
        }
        else {
            // Otherwise redirect to the reference handler
            final Object rawValue = attribute.getValue().getRaw();
            ProxyReference reference = new ProxyReference().setValue(ProxyValue.raw(rawValue));
            processReference(reference, EFeatures.asReference(eFeature));
        }
    }

    @Override
    public void onReference(ProxyReference reference) throws IOException {
        final EStructuralFeature eFeature = getFeature(reference.getName());

        processReference(reference, EFeatures.asReference(eFeature));
    }

    @Override
    public void onEndElement() throws IOException {
        identifiers.removeLast();
        metaClasses.removeLast();

        notifyEndElement();
    }

    /**
     * Creates the root element from the given {@code element}.
     *
     * @param element the element representing the root element
     *
     * @throws NullPointerException if the {@code element} does not have a namespace
     */
    private void processElementAsRoot(ProxyElement element) throws IOException {
        checkNotNull(element.getMetaClass(), "The root element must have a namespace");

        // Retrieve the current EClass & define the meta-class of the current element if not present
        final ProxyClass metaClass = element.getMetaClass();
        final EClass eClass = getClass(element.getName(), metaClass.getNamespace().getOrigin());
        metaClass.setOrigin(eClass);

        // Define the element as root node
        element.setRoot(true);

        // Notify next handlers
        notifyStartElement(element);

        // Save the current element
        identifiers.addLast(element.getId().getResolved());
        metaClasses.addLast(eClass);
    }

    /**
     * Processes an element as a feature and redirects the processing to the associated method according to its type
     * (attribute or reference).
     *
     * @param element the element representing the feature
     */
    private void processElementAsFeature(ProxyElement element) throws IOException {
        final EStructuralFeature feature = getFeature(element.getName());

        // Check if the feature is a FeatureMap.Entry
        if (isFeatureMap(feature)) {
            throw new UnsupportedOperationException("FeatureMaps are not supported yet: Use standard EMF to import your model");
        }

        if (EFeatures.isAttribute(feature)) {
            throw new IllegalStateException(String.format("You should never have arrived here: The element is an attribute: %s", feature.getName()));
        }

        final EReference eReference = EFeatures.asReference(feature);
        final EClass baseClass = (EClass) eReference.getEType();

        // Retrieve the type the reference
        if (nonNull(element.getMetaClass())) {
            // The meta-class was specified in the element
            ProxyClass metaClass = element.getMetaClass();
            metaClass.setOrigin(resolveClass(metaClass, baseClass));
        }
        else {
            element.setMetaClass(new ProxyClass(baseClass));
        }

        processElementAsReference(element, eReference);
    }

    /**
     * Processes an element as a reference.
     *
     * @param element    the element representing the reference
     * @param eReference the associated EMF reference
     */
    private void processElementAsReference(ProxyElement element, EReference eReference) throws IOException {
        notifyStartElement(element);

        // Create a reference from the parent to this element
        if (eReference.isContainment()) {
            final Id ownerId = element.getId().getResolved();
            processReference(new ProxyReference().setValue(ProxyValue.resolved(ownerId)), eReference);
        }

        identifiers.addLast(element.getId().getResolved());
        metaClasses.addLast(element.getMetaClass().getOrigin());
    }

    /**
     * Processes an attribute.
     *
     * @param attribute  the attribute to process
     * @param eAttribute the associated EMF attribute
     */
    private void processAttribute(ProxyAttribute attribute, EAttribute eAttribute) throws IOException {
        final Id ownerId = identifiers.getLast();
        final EClass ownerClass = metaClasses.getLast();

        final Object resolvedValue = ValueConverter.INSTANCE.convert(attribute.getValue().getRaw(), eAttribute);

        attribute.setOwner(ownerId)
                .setId(ownerClass.getFeatureID(eAttribute))
                .setOrigin(eAttribute)
                .setValue(ProxyValue.resolved(resolvedValue));

        notifyAttribute(attribute);
    }

    /**
     * Processes a reference.
     *
     * @param reference  the reference to process
     * @param eReference the associated EMF reference
     */
    private void processReference(ProxyReference reference, EReference eReference) throws IOException {
        final Id ownerId = identifiers.getLast();
        final EClass ownerClass = metaClasses.getLast();

        reference.setOwner(ownerId)
                .setId(ownerClass.getFeatureID(eReference))
                .setOrigin(eReference);

        // The unique identifier is already set
        if (reference.getValue().isResolved()) {
            notifyReference(reference);
        }
        else {
            splitReference(reference);
        }
    }

    /**
     * Process one or several references from the raw value of the {@code reference}. If the reference is a multi-valued
     * reference, each identifier will be delimited by a space.
     *
     * @param reference the reference to process
     */
    private void splitReference(ProxyReference reference) throws IOException {
        for (String s : reference.getValue().<String>getRaw().split(Strings.SPACE)) {
            final String trim = s.trim();
            if (!trim.isEmpty()) {
                notifyReference(ProxyReference.copy(reference).setValue(ProxyValue.raw(trim)));
            }
        }
    }

    /**
     * Returns {@code true} if the feature is a feature map.
     *
     * @param eFeature the feature to test
     *
     * @return {@code true} if the feature is a feature map
     */
    private boolean isFeatureMap(EStructuralFeature eFeature) {
        final EStructuralFeature featureGroup = ExtendedMetaData.INSTANCE.getGroup(eFeature);
        return nonNull(featureGroup) && FeatureMapUtil.isFeatureMap(featureGroup);
    }

    /**
     * Finds the {@link EStructuralFeature} with the specified {@code name} using the meta-class of the last element.
     *
     * @param name the name of the feature
     *
     * @return the feature
     *
     * @throws NullPointerException if the feature cannot be found
     */
    @Nonnull
    private EStructuralFeature getFeature(String name) {
        EClass ownerClass = metaClasses.getLast();
        EStructuralFeature eFeature = ownerClass.getEStructuralFeature(name);

        checkNotNull(eFeature, "No feature named '%s' has been found in class '%s'", name, ownerClass.getName());

        return eFeature;
    }

    /**
     * Returns the {@link EClass} with the given {@code name} from the {@code package}.
     *
     * @param name     the name of the class
     * @param ePackage the package where to find the class
     *
     * @return the class
     *
     * @throws NullPointerException if the class cannot be found if the {@code package}
     */
    @Nonnull
    private EClass getClass(String name, EPackage ePackage) {
        EClass eClass = (EClass) ePackage.getEClassifier(name);
        checkNotNull(eClass, "Unable to find EClass '%s' from EPackage '%s'", name, ePackage.getNsURI());

        return eClass;
    }

    /**
     * Returns the {@link EClass} associated with the given {@code element}.
     *
     * @param metaClass  the element representing the class
     * @param superClass the super-type of the class
     *
     * @return the class
     *
     * @throws NullPointerException     if the class cannot be found
     * @throws IllegalArgumentException if the {@code superClass} is not the super-type of the sought class
     */
    @Nonnull
    private EClass resolveClass(ProxyClass metaClass, EClass superClass) {
        final EPackage ePackage = metaClass.getNamespace().getOrigin();

        // Is a more specific meta-class defined ?
        if (nonNull(metaClass.getName())) {
            EClass eClass = getClass(metaClass.getName(), ePackage);

            // Checks that the meta-class is a subtype of the reference type
            checkArgument(superClass.isSuperTypeOf(eClass), "%s is not a subclass of %s", eClass.getName(), superClass.getName());

            return eClass;
        }
        else {
            return getClass(superClass.getName(), ePackage);
        }
    }
}
