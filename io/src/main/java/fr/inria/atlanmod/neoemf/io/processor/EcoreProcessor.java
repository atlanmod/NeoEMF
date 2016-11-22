/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
import fr.inria.atlanmod.neoemf.io.structure.Identifier;
import fr.inria.atlanmod.neoemf.io.structure.MetaClassifier;
import fr.inria.atlanmod.neoemf.io.structure.Namespace;
import fr.inria.atlanmod.neoemf.io.structure.Reference;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

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
 * An {@link Processor} that creates and links simple elements to an Ecore structure.
 */
public class EcoreProcessor extends AbstractProcessor {

    /**
     * Stack containing previous {@link EClass}.
     */
    private final Deque<EClass> classesStack;

    /**
     * Stack containing previous identifier.
     */
    private final Deque<Identifier> idsStack;

    /**
     * Attribute waiting a value (via {@link #processCharacters(String)}.
     */
    private Attribute waitingAttribute;

    /**
     * Defines if the previous element was an attribute, or not.
     */
    private boolean previousWasAttribute;

    public EcoreProcessor(Processor processor) {
        super(processor);
        this.classesStack = new ArrayDeque<>();
        this.idsStack = new ArrayDeque<>();
        this.previousWasAttribute = false;
    }

    private static EClass getEClass(Classifier classifier, Namespace ns, EClass eClass, EPackage ePackage) throws Exception {
        MetaClassifier metaClassifier = classifier.getMetaClassifier();

        if (nonNull(metaClassifier)) {
            EClass subEClass = (EClass) ePackage.getEClassifier(metaClassifier.getLocalName());

            // Checks that the metaclass is a subtype of the reference type.
            // If true, use it instead of supertype
            if (eClass.isSuperTypeOf(subEClass)) {
                eClass = subEClass;
            }
            else {
                throw new Exception(subEClass.getName() + " is not a subclass of " + eClass.getName());
            }
        }

        // If not present, create the metaclass from the current class
        else {
            metaClassifier = new MetaClassifier(ns, eClass.getName());
            classifier.setMetaClassifier(metaClassifier);
        }

        return eClass;
    }

    @Override
    public void processStartElement(Classifier classifier) throws Exception {
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
    public void processAttribute(Attribute attribute) throws Exception {
        EClass eClass = classesStack.getLast();
        EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(attribute.getLocalName());

        // Checks that the attribute is well a attribute
        if (eStructuralFeature instanceof EAttribute) {
            EAttribute eAttribute = (EAttribute) eStructuralFeature;
            attribute.setMany(eAttribute.isMany());
            super.processAttribute(attribute);
        }

        // Otherwise redirect to the reference handler
        else if (eStructuralFeature instanceof EReference) {
            processReference(Reference.from(attribute));
        }
    }

    @Override
    public void processReference(Reference reference) throws Exception {
        EClass eClass = classesStack.getLast();
        EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(reference.getLocalName());

        // Checks that the reference is well a reference
        if (eStructuralFeature instanceof EReference) {
            EReference eReference = (EReference) eStructuralFeature;
            reference.setContainment(eReference.isContainment());
            reference.setMany(eReference.isMany());
            super.processReference(reference);
        }

        // Otherwise redirect to the attribute handler
        else if (eStructuralFeature instanceof EAttribute) {
            processAttribute(Attribute.from(reference));
        }
    }

    @Override
    public void processEndElement() throws Exception {
        if (!previousWasAttribute) {
            classesStack.removeLast();
            idsStack.removeLast();

            super.processEndElement();
        }
        else {
            NeoLogger.warn("An attribute still waiting for a value : it will be ignored");
            waitingAttribute = null; // Clean the waiting attribute : no character has been found to fill its value
            previousWasAttribute = false;
        }
    }

    @Override
    public void processCharacters(String characters) throws Exception {
        // Defines the value of the waiting attribute, if exists
        if (nonNull(waitingAttribute)) {
            waitingAttribute.setValue(characters);
            processAttribute(waitingAttribute);

            waitingAttribute = null;
        }
    }

    /**
     * Creates the root element from the given {@code classifier}.
     */
    private void createRootObject(Classifier classifier) throws Exception {
        Namespace ns = checkNotNull(classifier.getNamespace(), "The root element must have a namespace");

        // Retreives the EPackage from NS prefix
        EPackage ePackage = checkNotNull((EPackage) EPackage.Registry.INSTANCE.get(ns.getUri()),
                "EPackage %s is not registered.", ns.getUri());

        // Gets the current EClass
        EClass eClass = (EClass) ePackage.getEClassifier(classifier.getLocalName());

        // Defines the metaclass of the current element if not present
        if (isNull(classifier.getMetaClassifier())) {
            classifier.setMetaClassifier(new MetaClassifier(ns, eClass.getName()));
        }

        // Defines the element as root node
        classifier.setRoot(true);

        // Notifies next handlers
        super.processStartElement(classifier);

        // Saves the current EClass
        classesStack.addLast(eClass);

        // Gets the identifier of the element created by next handlers, and save it
        idsStack.addLast(classifier.getId());
    }

    private void processFeature(Classifier classifier) throws Exception {
        // Retreive the parent EClass
        EClass parentEClass = classesStack.getLast();

        // Gets the EPackage from it
        EPackage ePackage = parentEClass.getEPackage();
        Namespace ns = Namespace.Registry.getInstance().getFromPrefix(ePackage.getNsPrefix());

        // Gets the structural feature from the parent, according the its local name (the attr/ref name)
        EStructuralFeature eStructuralFeature = parentEClass.getEStructuralFeature(classifier.getLocalName());

        if (eStructuralFeature instanceof EAttribute) {
            processAttribute(classifier, (EAttribute) eStructuralFeature);
        }
        else if (eStructuralFeature instanceof EReference) {
            processReference(classifier, ns, (EReference) eStructuralFeature, ePackage);
        }
    }

    private void processAttribute(@SuppressWarnings("unused") Classifier classifier, EAttribute eAttribute) {
        if (nonNull(waitingAttribute)) {
            NeoLogger.warn("An attribute still waiting for a value : it will be ignored");
        }

        // Waiting a plain text value
        waitingAttribute = new Attribute(eAttribute.getName());
        previousWasAttribute = true;
    }

    private void processReference(Classifier classifier, Namespace ns, EReference eReference, EPackage ePackage) throws Exception {
        // Gets the type the reference or gets the type from the registered metaclass
        EClass eClass = getEClass(classifier, ns, (EClass) eReference.getEType(), ePackage);

        classifier.setNamespace(ns);

        // Notify next handlers of new element, and retreive its identifier
        super.processStartElement(classifier);
        Identifier currentId = classifier.getId();

        // Create a reference from the parent to this element, with the given local name
        if (eReference.isContainment()) {
            NeoLogger.debug("{0}#{1} is a containment : creating the reverse reference.", classifier.getMetaClassifier(), eReference.getName());

            Reference ref = new Reference(eReference.getName());
            ref.setId(idsStack.getLast());
            ref.setIdReference(currentId);

            processReference(ref);
        }

        // Save EClass and identifier
        classesStack.addLast(eClass);
        idsStack.addLast(currentId);
    }
}
