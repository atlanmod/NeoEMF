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

import fr.inria.atlanmod.neoemf.io.structure.RawClassifier;
import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawMetaClassifier;
import fr.inria.atlanmod.neoemf.io.structure.RawIdentifier;
import fr.inria.atlanmod.neoemf.io.structure.RawNamespace;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link Processor} that creates and links simple elements to an EMF structure.
 */
public class EcoreProcessor extends AbstractProcessor {

    /**
     * Stack containing previous {@link EClass}.
     */
    private final Deque<EClass> classesStack;

    /**
     * Stack containing previous identifier.
     */
    private final Deque<RawIdentifier> idsStack;

    /**
     * Attribute waiting a value (via {@link #processCharacters(String)}.
     */
    private RawAttribute waitingAttribute;

    /**
     * Defines if the previous element was an attribute, or not.
     */
    private boolean previousWasAttribute;

    /**
     * Constructs a new {@code EcoreProcessor} on the given {@code processor}.
     *
     * @param processor the underlying processor
     */
    public EcoreProcessor(Processor processor) {
        super(processor);
        this.classesStack = new ArrayDeque<>();
        this.idsStack = new ArrayDeque<>();
        this.previousWasAttribute = false;
    }

    /**
     * Returns the {@link EClass} associated with the given {@code classifier}.
     *
     * @param classifier the classifier representing the class
     * @param ns         the namespace of the {@code superClass}
     * @param superClass the super-type of the sought class
     * @param ePackage   the package where to find the class
     *
     * @return a class
     *
     * @throws IllegalArgumentException if the {@code superClass} is not the super-type of the sought class
     */
    private static EClass getEClass(RawClassifier classifier, RawNamespace ns, EClass superClass, EPackage ePackage) {
        RawMetaClassifier metaClassifier = classifier.metaClassifier();

        if (nonNull(metaClassifier)) {
            EClass subEClass = (EClass) ePackage.getEClassifier(metaClassifier.localName());

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
            metaClassifier = new RawMetaClassifier(ns, superClass.getName());
            classifier.metaClassifier(metaClassifier);
        }

        return superClass;
    }

    @Override
    public void processStartElement(RawClassifier classifier) {
        // Is root
        if (classesStack.isEmpty()) {
            createRootObject(classifier);
        }
        // Is a feature of parent
        else {
            processFeature(classifier);
        }
    }

    @Override
    public void processAttribute(RawAttribute attribute) {
        EClass eClass = classesStack.getLast();
        EStructuralFeature feature = eClass.getEStructuralFeature(attribute.localName());

        // Checks that the attribute is well a attribute
        if (feature instanceof EAttribute) {
            EAttribute eAttribute = (EAttribute) feature;
            attribute.many(eAttribute.isMany());
            notifyAttribute(attribute);
        }

        // Otherwise redirect to the reference handler
        else if (feature instanceof EReference) {
            processReference(RawReference.from(attribute));
        }
    }

    @Override
    public void processReference(RawReference reference) {
        EClass eClass = classesStack.getLast();
        EStructuralFeature feature = eClass.getEStructuralFeature(reference.localName());

        // Checks that the reference is well a reference
        if (feature instanceof EReference) {
            EReference eReference = (EReference) feature;
            reference.containment(eReference.isContainment());
            reference.many(eReference.isMany());
            notifyReference(reference);
        }

        // Otherwise redirect to the attribute handler
        else if (feature instanceof EAttribute) {
            processAttribute(RawAttribute.from(reference));
        }
    }

    @Override
    public void processEndElement() {
        if (!previousWasAttribute) {
            classesStack.removeLast();
            idsStack.removeLast();

            notifyEndElement();
        }
        else {
            NeoLogger.warn("An attribute still waiting for a value : it will be ignored");
            waitingAttribute = null; // Clean the waiting attribute : no character has been found to fill its value
            previousWasAttribute = false;
        }
    }

    @Override
    public void processCharacters(String characters) {
        // Defines the value of the waiting attribute, if exists
        if (nonNull(waitingAttribute)) {
            waitingAttribute.value(characters);
            processAttribute(waitingAttribute);

            waitingAttribute = null;
        }
    }

    /**
     * Creates the root element from the given {@code classifier}.
     *
     * @param classifier the classifier representing the root element
     *
     * @throws NullPointerException if the {@code classifier} does not have a namespace
     */
    private void createRootObject(RawClassifier classifier) {
        RawNamespace ns = checkNotNull(classifier.namespace(), "The root element must have a namespace");

        // Retrieves the EPackage from NS prefix
        EPackage ePackage = checkNotNull((EPackage) EPackage.Registry.INSTANCE.get(ns.uri()),
                "EPackage %s is not registered.", ns.uri());

        // Gets the current EClass
        EClass eClass = (EClass) ePackage.getEClassifier(classifier.localName());

        // Defines the metaclass of the current element if not present
        if (isNull(classifier.metaClassifier())) {
            classifier.metaClassifier(new RawMetaClassifier(ns, eClass.getName()));
        }

        // Defines the element as root node
        classifier.root(true);

        // Notifies next handlers
        notifyStartElement(classifier);

        // Saves the current EClass
        classesStack.addLast(eClass);

        // Gets the identifier of the element created by next handlers, and save it
        idsStack.addLast(classifier.id());
    }

    /**
     * Processes a feature and redirects the processing to the associated method according to its type (attribute or
     * reference).
     *
     * @param classifier the classifier representing the feature
     *
     * @see #processAttribute(RawClassifier, EAttribute)
     * @see #processReference(RawClassifier, RawNamespace, EReference, EPackage)
     */
    private void processFeature(RawClassifier classifier) {
        // Retrieve the parent EClass
        EClass parentEClass = classesStack.getLast();

        // Gets the EPackage from it
        EPackage ePackage = parentEClass.getEPackage();
        RawNamespace ns = RawNamespace.Registry.getInstance().getFromPrefix(ePackage.getNsPrefix());

        // Gets the structural feature from the parent, according the its local name (the attr/ref name)
        EStructuralFeature feature = parentEClass.getEStructuralFeature(classifier.localName());

        if (feature instanceof EAttribute) {
            processAttribute(classifier, (EAttribute) feature);
        }
        else if (feature instanceof EReference) {
            processReference(classifier, ns, (EReference) feature, ePackage);
        }
    }

    /**
     * Processes an attribute.
     *
     * @param classifier the classifier representing the attribute
     * @param attribute  the associated EMF attribute
     */
    private void processAttribute(@SuppressWarnings("unused") RawClassifier classifier, EAttribute attribute) {
        if (nonNull(waitingAttribute)) {
            NeoLogger.warn("An attribute still waiting for a value : it will be ignored");
        }

        // Waiting a plain text value
        waitingAttribute = new RawAttribute(attribute.getName());
        previousWasAttribute = true;
    }

    /**
     * Processes a reference.
     *
     * @param classifier the classifier representing the reference
     * @param ns         the namespace of the class of the reference
     * @param reference  the associated EMF reference
     * @param ePackage   the package where to find the class of the reference
     */
    private void processReference(RawClassifier classifier, RawNamespace ns, EReference reference, EPackage ePackage) {
        // Gets the type the reference or gets the type from the registered metaclass
        EClass eClass = getEClass(classifier, ns, (EClass) reference.getEType(), ePackage);

        classifier.namespace(ns);

        // Notify next handlers of new element, and retrieve its identifier
        notifyStartElement(classifier);
        RawIdentifier currentId = classifier.id();

        // Create a reference from the parent to this element, with the given local name
        if (reference.isContainment()) {
            NeoLogger.debug("{0}#{1} is a containment : creating the reverse reference.", classifier.metaClassifier(), reference.getName());

            RawReference ref = new RawReference(reference.getName());
            ref.id(idsStack.getLast());
            ref.idReference(currentId);

            processReference(ref);
        }

        // Save EClass and identifier
        classesStack.addLast(eClass);
        idsStack.addLast(currentId);
    }
}
