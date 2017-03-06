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

import fr.inria.atlanmod.neoemf.io.structure.Namespace;
import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawElement;
import fr.inria.atlanmod.neoemf.io.structure.RawId;
import fr.inria.atlanmod.neoemf.io.structure.RawMetaclass;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.ArrayDeque;
import java.util.Deque;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link Processor} that creates and links simple elements to an EMF structure.
 */
@ParametersAreNonnullByDefault
public class EcoreProcessor extends AbstractProcessor<Processor> {

    /**
     * Stack containing previous {@link EClass}.
     */
    private final Deque<EClass> classesStack;

    /**
     * Stack containing previous identifier.
     */
    private final Deque<RawId> idsStack;

    /**
     * RawAttribute that waits a value (via {@link #onCharacters(String)}.
     */
    private RawAttribute waitingAttribute;

    /**
     * Defines if the previous element was an attribute, or not.
     */
    private boolean previousWasAttribute;

    /**
     * Constructs a new {@code EcoreProcessor} with the given {@code processors}.
     *
     * @param processors the processors to notify
     */
    public EcoreProcessor(Processor... processors) {
        super(processors);
        this.classesStack = new ArrayDeque<>();
        this.idsStack = new ArrayDeque<>();
        this.previousWasAttribute = false;
    }

    @Override
    public void onStartElement(RawElement element) {
        // Is root
        if (classesStack.isEmpty()) {
            processElementAsRoot(element);
        }
        // Is a feature of parent
        else {
            processElementAsFeature(element);
        }
    }

    @Override
    public void onAttribute(RawAttribute attribute) {
        EClass eClass = classesStack.getLast();
        EStructuralFeature feature = eClass.getEStructuralFeature(attribute.name());

        // Checks that the attribute is well a attribute
        if (feature instanceof EAttribute) {
            EAttribute eAttribute = (EAttribute) feature;
            attribute.isMany(eAttribute.isMany());

            notifyAttribute(attribute);
        }

        // Otherwise redirect to the reference handler
        else if (feature instanceof EReference) {
            onReference(RawReference.from(attribute));
        }
    }

    @Override
    public void onReference(RawReference reference) {
        EClass eClass = classesStack.getLast();
        EStructuralFeature feature = eClass.getEStructuralFeature(reference.name());

        // Checks that the reference is well a reference
        if (feature instanceof EReference) {
            EReference eReference = (EReference) feature;
            reference.isContainment(eReference.isContainment());
            reference.isMany(eReference.isMany());

            EClass referenceType = eReference.getEReferenceType();
            reference.metaclassReference(new RawMetaclass(Namespace.Registry.getInstance().getFromUri(referenceType.getEPackage().getNsURI()), referenceType.getName()));

            notifyReference(reference);
        }

        // Otherwise redirect to the attribute handler
        else if (feature instanceof EAttribute) {
            onAttribute(RawAttribute.from(reference));
        }
    }

    @Override
    public void onCharacters(String characters) {
        // Defines the value of the waiting attribute, if exists
        if (nonNull(waitingAttribute)) {
            waitingAttribute.value(characters);
            onAttribute(waitingAttribute);

            waitingAttribute = null;
        }
    }

    @Override
    public void onEndElement() {
        if (!previousWasAttribute) {
            classesStack.removeLast();
            idsStack.removeLast();

            notifyEndElement();
        }
        else {
            Log.warn("An attribute still waiting for a value : it will be ignored");
            waitingAttribute = null; // Clean the waiting attribute : no character has been found to fill its value
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
    private void processElementAsRoot(RawElement element) {
        Namespace ns = checkNotNull(element.ns(), "The root element must have a namespace");

        // Retrieves the EPackage from NS prefix
        EPackage ePackage = checkNotNull((EPackage) EPackage.Registry.INSTANCE.get(ns.uri()),
                "EPackage %s is not registered.", ns.uri());

        // Gets the current EClass
        EClass eClass = (EClass) ePackage.getEClassifier(element.name());

        // Defines the metaclass of the current element if not present
        if (isNull(element.metaclass())) {
            element.metaclass(new RawMetaclass(ns, eClass.getName()));
        }

        // Defines the element as root node
        element.isRoot(true);

        // Notifies next handlers
        notifyStartElement(element);

        // Saves the current EClass
        classesStack.addLast(eClass);

        // Gets the identifier of the element created by next handlers, and save it
        idsStack.addLast(element.id());
    }

    /**
     * Processes a feature and redirects the processing to the associated method according to its type (attribute or
     * reference).
     *
     * @param element the element representing the feature
     *
     * @see #processElementAsAttribute(RawElement, EAttribute)
     * @see #processElementAsReference(RawElement, Namespace, EReference, EPackage)
     */
    private void processElementAsFeature(RawElement element) {
        // Retrieve the parent EClass
        EClass parentEClass = classesStack.getLast();

        // Gets the EPackage from it
        EPackage ePackage = parentEClass.getEPackage();
        Namespace ns = Namespace.Registry.getInstance().getFromPrefix(ePackage.getNsPrefix());

        // Gets the structural feature from the parent, according the its local name (the attr/ref name)
        EStructuralFeature feature = parentEClass.getEStructuralFeature(element.name());

        if (feature instanceof EAttribute) {
            processElementAsAttribute(element, (EAttribute) feature);
        }
        else if (feature instanceof EReference) {
            processElementAsReference(element, ns, (EReference) feature, ePackage);
        }
    }

    /**
     * Processes an attribute.
     *
     * @param element   the element representing the attribute
     * @param attribute the associated EMF attribute
     */
    private void processElementAsAttribute(RawElement element, EAttribute attribute) {
        if (nonNull(waitingAttribute)) {
            Log.warn("An attribute still waiting for a value : it will be ignored");
        }

        // Waiting a plain text value
        waitingAttribute = new RawAttribute(attribute.getName());
        waitingAttribute.id(idsStack.getLast());

        previousWasAttribute = true;
    }

    /**
     * Processes a reference.
     *
     * @param element   the element representing the reference
     * @param ns        the namespace of the class of the reference
     * @param reference the associated EMF reference
     * @param ePackage  the package where to find the class of the reference
     */
    private void processElementAsReference(RawElement element, Namespace ns, EReference reference, EPackage ePackage) {
        // Gets the type the reference or gets the type from the registered metaclass
        EClass eClass = resolveInstanceOf(element, ns, (EClass) reference.getEType(), ePackage);

        element.ns(ns);

        // Notify next handlers of new element, and retrieve its identifier
        notifyStartElement(element);
        RawId currentId = element.id();

        // Create a reference from the parent to this element, with the given local name
        if (reference.isContainment()) {
            RawReference ref = new RawReference(reference.getName());
            ref.id(idsStack.getLast());
            ref.idReference(currentId);

            onReference(ref);
        }

        // Save EClass and identifier
        classesStack.addLast(eClass);
        idsStack.addLast(currentId);
    }

    /**
     * Returns the {@link EClass} associated with the given {@code element}.
     *
     * @param element    the element representing the class
     * @param ns         the namespace of the {@code superClass}
     * @param superClass the super-type of the sought class
     * @param ePackage   the package where to find the class
     *
     * @return a class
     *
     * @throws IllegalArgumentException if the {@code superClass} is not the super-type of the sought class
     */
    @Nonnull
    private EClass resolveInstanceOf(RawElement element, Namespace ns, EClass superClass, EPackage ePackage) {
        RawMetaclass metaClass = element.metaclass();

        if (nonNull(metaClass)) {
            EClass subEClass = (EClass) ePackage.getEClassifier(metaClass.name());

            // Checks that the metaclass is a subtype of the reference type.
            // If true, use it instead of supertype
            if (superClass.isSuperTypeOf(subEClass)) {
                superClass = subEClass;
            }
            else {
                throw new IllegalArgumentException(subEClass.getName() + " is not a subclass of " + superClass.getName());
            }
        }

        // If not present, create the metaclass from the current class
        else {
            metaClass = new RawMetaclass(ns, superClass.getName());
            element.metaclass(metaClass);
        }

        return superClass;
    }
}
