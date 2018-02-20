/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.bean.BasicNamespace;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;
import fr.inria.atlanmod.neoemf.util.EObjects;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * A {@link Processor} that transforms simple elements to an EMF structure.
 */
@ParametersAreNonnullByDefault
public class EcoreMapper extends AbstractProcessor {

    /**
     * A LIFO that holds the current {@link BasicElement} chain. It contains the current element and the previous.
     */
    @Nonnull
    private final Deque<BasicElement> elements = new ArrayDeque<>();

    /**
     * Constructs a new {@code EcoreMapper}.
     *
     * @param processor the next processor
     */
    public EcoreMapper(Processor processor) {
        super(processor);
    }

    @Override
    public void onStartElement(BasicElement element) throws IOException {
        // Is root
        if (elements.isEmpty()) {
            processElementAsRoot(element);
        }
        // Is a feature of parent
        else {
            processElementAsFeature(element);
        }
    }

    @Override
    public void onAttribute(BasicAttribute attribute) throws IOException {
        final EStructuralFeature eFeature = getFeature(attribute.name());

        if (isFeatureMap(eFeature)) {
            throw new UnsupportedOperationException("FeatureMaps are not supported yet: Use standard EMF to import your model");
        }

        if (EObjects.isAttribute(eFeature)) {
            // The attribute is well a attribute
            processAttribute(attribute, EObjects.asAttribute(eFeature));
        }
        else {
            // Otherwise redirect to the reference handler
            BasicReference reference = new BasicReference()
                    .rawValue(attribute.rawValue());

            processReference(reference, EObjects.asReference(eFeature));
        }
    }

    @Override
    public void onReference(BasicReference reference) throws IOException {
        final EStructuralFeature eFeature = getFeature(reference.name());

        processReference(reference, EObjects.asReference(eFeature));
    }

    @Override
    public void onEndElement() throws IOException {
        elements.removeLast();

        notifyEndElement();
    }

    /**
     * Creates the root element from the given {@code element}.
     *
     * @param element the element representing the root element
     *
     * @throws NullPointerException if the {@code element} does not have a namespace
     */
    private void processElementAsRoot(BasicElement element) throws IOException {
        checkNotNull(element.metaClass(), "The root element must have a namespace");

        // Retrieve the current EClass & define the meta-class of the current element if not present
        BasicMetaclass metaClass = element.metaClass();
        metaClass.eClass(getClass(element.name(), metaClass.ns().ePackage()));

        // Define the element as root node
        element.isRoot(true);

        // Notify next handlers
        notifyStartElement(element);

        // Save the current element
        elements.addLast(element);
    }

    /**
     * Processes an element as a feature and redirects the processing to the associated method according to its type
     * (attribute or reference).
     *
     * @param element the element representing the feature
     */
    private void processElementAsFeature(BasicElement element) throws IOException {
        final EStructuralFeature feature = getFeature(element.name());

        // Check if the feature is a FeatureMap.Entry
        if (isFeatureMap(feature)) {
            throw new UnsupportedOperationException("FeatureMaps are not supported yet: Use standard EMF to import your model");
        }

        if (EObjects.isAttribute(feature)) {
            throw new IllegalStateException(String.format("You should never have arrived here: The element is an attribute: %s", feature.getName()));
        }

        EReference eReference = EObjects.asReference(feature);
        EClass baseClass = EClass.class.cast(eReference.getEType());

        // Retrieve the type the reference
        if (nonNull(element.metaClass())) {
            // The meta-class was specified in the element
            BasicMetaclass metaClass = element.metaClass();
            metaClass.eClass(resolveClass(metaClass, baseClass));
        }
        else {
            BasicNamespace ns = BasicNamespace.Registry.getInstance().get(baseClass.getEPackage());
            element.metaClass(new BasicMetaclass(ns).eClass(baseClass));
        }

        processElementAsReference(element, eReference);
    }

    /**
     * Processes an element as a reference.
     *
     * @param element    the element representing the reference
     * @param eReference the associated EMF reference
     */
    private void processElementAsReference(BasicElement element, EReference eReference) throws IOException {
        // Notify next handlers of new element
        notifyStartElement(element);

        // Retrieve the identifier of the element (generated by next handlers)
        Id currentId = element.id();

        // Create a reference from the parent to this element, with the given local name
        if (eReference.isContainment()) {
            BasicReference reference = new BasicReference()
                    .value(currentId);

            processReference(reference, eReference);
        }

        // Save the current element
        elements.addLast(element);
    }

    /**
     * Processes an attribute.
     *
     * @param attribute  the attribute to process
     * @param eAttribute the associated EMF attribute
     */
    private void processAttribute(BasicAttribute attribute, EAttribute eAttribute) throws IOException {
        BasicElement parentElement = elements.getLast();
        EClass parentClass = parentElement.metaClass().eClass();

        attribute.owner(parentElement.id())
                .id(parentClass.getFeatureID(eAttribute))
                .eFeature(eAttribute)
                .value(ValueConverter.INSTANCE.convert(attribute.rawValue(), eAttribute));

        notifyAttribute(attribute);
    }

    /**
     * Processes a reference.
     *
     * @param reference  the reference to process
     * @param eReference the associated EMF reference
     */
    private void processReference(BasicReference reference, EReference eReference) throws IOException {
        BasicElement parentElement = elements.getLast();
        EClass parentClass = parentElement.metaClass().eClass();

        reference.owner(parentElement.id())
                .id(parentClass.getFeatureID(eReference))
                .eFeature(eReference);

        // The unique identifier is already set
        if (nonNull(reference.value())) {
            notifyReference(reference);
        }
        else {
            processRawReference(reference, eReference);
        }
    }

    /**
     * Process one or several references from the raw value of the {@code reference}. If the reference is a multi-valued
     * reference, each raw identifier will be delimited by a space.
     *
     * @param reference  the reference to process
     * @param eReference the associated EMF reference
     */
    private void processRawReference(BasicReference reference, EReference eReference) throws IOException {
        final Function<String, BasicReference> createFunc = s -> new BasicReference()
                .owner(reference.owner())
                .id(reference.id())
                .eFeature(eReference)
                .rawValue(s);

        List<BasicReference> allReferences = Arrays.stream(reference.<String>rawValue().split(Strings.SPACE))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(createFunc)
                .collect(Collectors.toList());

        for (BasicReference r : allReferences) {
            notifyReference(r);
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
        BasicElement parentElement = elements.getLast();
        EClass parentClass = parentElement.metaClass().eClass();
        EStructuralFeature eFeature = parentClass.getEStructuralFeature(name);

        checkNotNull(eFeature, "No feature named {0} has been found in class {1}", name, parentClass);

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
        EClass eClass = EClass.class.cast(ePackage.getEClassifier(name));

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
    private EClass resolveClass(BasicMetaclass metaClass, EClass superClass) {
        final EPackage ePackage = metaClass.ns().ePackage();

        // Is a more specific meta-class defined ?
        if (nonNull(metaClass.name())) {
            EClass eClass = getClass(metaClass.name(), ePackage);

            // Checks that the meta-class is a subtype of the reference type
            checkArgument(superClass.isSuperTypeOf(eClass),
                    "%s is not a subclass of %s", eClass.getName(), superClass.getName());

            return eClass;
        }
        else {
            return getClass(superClass.getName(), ePackage);
        }
    }
}
