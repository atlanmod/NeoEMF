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

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
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

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * A {@link Processor} that transforms simple elements to an EMF structure.
 */
@ParametersAreNonnullByDefault
public class EcoreProcessor extends AbstractProcessor<Processor> {

    /**
     * A LIFO that holds the current {@link EClass} chain. It contains the current meta-class and the previous.
     */
    @Nonnull
    private final Deque<EClass> previousMetaClasses = new ArrayDeque<>();

    /**
     * A LIFO that holds the current {@link Id} chain. It contains the current identifier and the previous.
     */
    @Nonnull
    private final Deque<Id> previousIds = new ArrayDeque<>();

    /**
     * An attribute that is waiting for a value.
     *
     * @see #onCharacters(String)
     */
    @Nullable
    private BasicAttribute waitingAttribute;

    /**
     * Defines if the previous element was an attribute, or not.
     */
    private boolean previousWasAttribute;

    /**
     * Constructs a new {@code EcoreProcessor} with the given {@code processor}.
     *
     * @param processor the processor to notify
     */
    public EcoreProcessor(Processor processor) {
        super(processor);
    }

    @Override
    public void onStartElement(BasicElement element) {
        // Is root
        if (previousMetaClasses.isEmpty()) {
            processElementAsRoot(element);
        }
        // Is a feature of parent
        else {
            processElementAsFeature(element);
        }
    }

    @Override
    public void onAttribute(BasicAttribute attribute) {
        EStructuralFeature eFeature = previousMetaClasses.getLast().getEStructuralFeature(attribute.name());

        if (EObjects.isAttribute(eFeature)) {
            // The attribute is well a attribute
            processAttribute(attribute, EObjects.asAttribute(eFeature));
        }
        else {
            // Otherwise redirect to the reference handler
            BasicReference reference = new BasicReference();
            reference.name(attribute.name());
            reference.value(StringId.of(attribute.value()));

            processReference(reference, EObjects.asReference(eFeature));
        }
    }

    @Override
    public void onReference(BasicReference reference) {
        EStructuralFeature eFeature = previousMetaClasses.getLast().getEStructuralFeature(reference.name());

        processReference(reference, EObjects.asReference(eFeature));
    }

    @Override
    public void onCharacters(String characters) {
        // Defines the value of the waiting attribute, if exists
        if (nonNull(waitingAttribute)) {
            waitingAttribute.value(characters);
            onAttribute(waitingAttribute);

            waitingAttribute = null;
        }
        else {
            Log.debug("Ignoring characters: \"{0}\"", characters);
        }
    }

    @Override
    public void onEndElement() {
        if (!previousWasAttribute) {
            previousMetaClasses.removeLast();
            previousIds.removeLast();

            notifyEndElement();
        }
        else {
            Log.warn("An attribute still waiting for a value: it will be ignored");
            waitingAttribute = null;
            previousWasAttribute = false;
        }
    }

    /**
     * Creates the root element from the given {@code element}.
     *
     * @param element the element representing the root element
     *
     * @throws NullPointerException if the {@code element} does not have a namespace
     */
    private void processElementAsRoot(BasicElement element) {
        checkNotNull(element.metaClass(), "The root element must have a namespace");

        BasicNamespace ns = element.metaClass().ns();

        // Retrieve the EPackage from the NS prefix
        EPackage ePackage = EPackage.class.cast(EPackage.Registry.INSTANCE.get(ns.uri()));
        checkNotNull(ePackage, "EPackage %s is not registered.", ns.uri());

        // Retrieve the current EClass
        EClass eClass = EClass.class.cast(ePackage.getEClassifier(element.name()));
        checkNotNull(eClass, "Cannot retrieve EClass %s from the EPackage %s", element.name(), ePackage);

        // Define the meta-class of the current element if not present
        element.metaClass().name(eClass.getName());

        // Define the element as root node
        element.isRoot(true);

        // Notify next handlers
        notifyStartElement(element);

        // Save the current EClass, and the identifier of the element created by next handlers
        previousMetaClasses.addLast(eClass);
        previousIds.addLast(element.id());
    }

    /**
     * Processes an element as a feature and redirects the processing to the associated method according to its type
     * (attribute or reference).
     *
     * @param element the element representing the feature
     *
     * @see #processElementAsAttribute(BasicElement, EAttribute)
     * @see #processElementAsReference(BasicElement, EReference)
     */
    private void processElementAsFeature(BasicElement element) {
        // Retrieve the parent EClass
        EClass parentEClass = previousMetaClasses.getLast();

        // Retrieve the structural feature from the parent, according the its local name (the attr/ref name)
        EStructuralFeature feature = parentEClass.getEStructuralFeature(element.name());

        if (EObjects.isAttribute(feature)) {
            processElementAsAttribute(element, EObjects.asAttribute(feature));
        }
        else {
            EReference eReference = EObjects.asReference(feature);

            // Retrieve the namespace xor the EPackage, and build the meta-class
            BasicNamespace ns;
            EPackage ePackage;

            if (nonNull(element.metaClass())) {
                ns = element.metaClass().ns();
                ePackage = EPackage.Registry.INSTANCE.getEPackage(ns.uri());
            }
            else {
                ePackage = parentEClass.getEPackage();
                ns = BasicNamespace.Registry.getInstance().getFromUri(ePackage.getNsURI());
                element.metaClass(new BasicMetaclass(ns));
            }

            // Retrieve the type the reference or gets the type from the registered meta-class
            EClass eClass = resolveInstanceOf(element.metaClass(), EClass.class.cast(eReference.getEType()), ePackage);
            element.metaClass().name(eClass.getName());

            // Save the original meta-class
            previousMetaClasses.addLast(eClass);

            processElementAsReference(element, eReference);
        }
    }

    /**
     * Processes an element as an attribute.
     *
     * @param element    the element representing the attribute
     * @param eAttribute the associated EMF attribute
     */
    private void processElementAsAttribute(@SuppressWarnings("unused") BasicElement element, EAttribute eAttribute) {
        if (nonNull(waitingAttribute)) {
            Log.warn("An attribute still waiting for a value : it will be ignored");
        }

        // Waiting a plain text value
        BasicAttribute attribute = new BasicAttribute();
        attribute.name(eAttribute.getName());
        attribute.owner(previousIds.getLast());
        attribute.isMany(eAttribute.isMany());

        // The attribute waiting for a value
        waitingAttribute = attribute;
        previousWasAttribute = true;
    }

    /**
     * Processes an element as a reference.
     *
     * @param element    the element representing the reference
     * @param eReference the associated EMF reference
     */
    private void processElementAsReference(BasicElement element, EReference eReference) {
        // Notify next handlers of new element
        notifyStartElement(element);

        // Retrieve the identifier of the element (generated by next handlers)
        Id currentId = element.id();

        // Create a reference from the parent to this element, with the given local name
        if (eReference.isContainment()) {
            BasicReference reference = new BasicReference();
            reference.name(eReference.getName());
            reference.value(currentId);

            processReference(reference, eReference);
        }

        // Save the identifier
        previousIds.addLast(currentId);
    }

    /**
     * Processes an attribute.
     *
     * @param attribute  the attribute to process
     * @param eAttribute the associated EMF attribute
     */
    private void processAttribute(BasicAttribute attribute, EAttribute eAttribute) {
        attribute.owner(previousIds.getLast());
        attribute.isMany(eAttribute.isMany());

        notifyAttribute(attribute);
    }

    /**
     * Processes a reference.
     *
     * @param reference  the reference to process
     * @param eReference the associated EMF reference
     */
    private void processReference(BasicReference reference, EReference eReference) {
        reference.owner(previousIds.getLast());
        reference.isMany(eReference.isMany());
        reference.isContainment(eReference.isContainment());

        // Split a multi-valued reference
        Arrays.stream(reference.value().toString().split(Strings.SPACE))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> {
                    BasicReference newRef = new BasicReference();
                    newRef.name(reference.name());
                    newRef.owner(reference.owner());
                    newRef.value(StringId.of(s));
                    newRef.isMany(reference.isMany());
                    newRef.isContainment(reference.isContainment());

                    return newRef;
                })
                .forEach(this::notifyReference);
    }

    /**
     * Returns the {@link EClass} associated with the given {@code element}.
     *
     * @param metaClass  the element representing the class
     * @param superClass the super-type of the sought class
     * @param ePackage   the package where to find the class
     *
     * @return a class
     *
     * @throws IllegalArgumentException if the {@code superClass} is not the super-type of the sought class
     */
    @Nonnull
    private EClass resolveInstanceOf(BasicMetaclass metaClass, EClass superClass, EPackage ePackage) {
        // Is a more specific meta-class defined ?
        if (nonNull(metaClass.name())) {
            EClass eClass = EClass.class.cast(ePackage.getEClassifier(metaClass.name()));

            checkNotNull(eClass, "Cannot retrieve EClass {0} from the EPackage {1}", metaClass.name(), ePackage);

            // Checks that the meta-class is a subtype of the reference type
            checkArgument(superClass.isSuperTypeOf(eClass),
                    "%s is not a subclass of %s", eClass.getName(), superClass.getName());

            return eClass;
        }

        return superClass;
    }
}
