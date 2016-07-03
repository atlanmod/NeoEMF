/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.internal.impl;

import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.Classifier;
import fr.inria.atlanmod.neoemf.io.beans.Identifier;
import fr.inria.atlanmod.neoemf.io.beans.MetaClassifier;
import fr.inria.atlanmod.neoemf.io.beans.Namespace;
import fr.inria.atlanmod.neoemf.io.beans.Reference;
import fr.inria.atlanmod.neoemf.io.internal.InternalHandler;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An {@link InternalHandler internal handler} that creates and links simple elements to an Ecore structure.
 */
public class EcoreDelegatedInternalHandler extends AbstractDelegatedInternalHandler {

    /**
     * Stack containing previous {@link EClass}.
     */
    private final Deque<EClass> classesStack;

    /**
     * Stack containing previous identifier.
     */
    private final Deque<Identifier> idsStack;

    /**
     * Attribute waiting a value (via {@link #handleCharacters(String)}.
     */
    private Attribute waitingAttribute;

    /**
     * Defines if the previous element was an attribute, or not.
     */
    private boolean ignoreCleaning;

    public EcoreDelegatedInternalHandler(InternalHandler handler) {
        super(handler);
        this.classesStack = new ArrayDeque<>();
        this.idsStack = new ArrayDeque<>();
        this.ignoreCleaning = false;
    }

    @Override
    public void handleStartElement(Classifier classifier) throws Exception {
        // Is root
        if (classesStack.isEmpty()) {
            createRootObject(classifier);
        }
        // Is a feature of parent
        else {
            handleFeature(classifier);
        }
    }

    @Override
    public void handleCharacters(String characters) throws Exception {
        // Defines the value of the waiting attribute, if exists
        if (waitingAttribute != null) {
            waitingAttribute.setValue(characters);
            super.handleAttribute(waitingAttribute);

            waitingAttribute = null;
        }
    }

    @Override
    public void handleAttribute(Attribute attribute) throws Exception {
        EClass eClass = classesStack.getLast();
        EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(attribute.getLocalName());

        // Checks that the attribute is well a attribute
        if (eStructuralFeature instanceof EAttribute) {
            super.handleAttribute(attribute);
        }

        // Otherwise redirect to the reference handler
        else if (eStructuralFeature instanceof EReference) {
            handleReference(Reference.from(attribute));
        }
    }

    @Override
    public void handleReference(Reference reference) throws Exception {
        EClass eClass = classesStack.getLast();
        EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(reference.getLocalName());

        // Checks that the reference is well a reference
        if (eStructuralFeature instanceof EReference) {
            // Update containment value
            EReference eReference = (EReference) eStructuralFeature;
            reference.setContainment(eReference.isContainment());

            EReference eOpposite = eReference.getEOpposite();
            if (eOpposite != null) {
                Reference opposite = new Reference(eOpposite.getName());
                opposite.setContainment(opposite.isContainment());
                reference.setOpposite(opposite);
            }

            super.handleReference(reference);
        }

        // Otherwise redirect to the attribute handler
        else if (eStructuralFeature instanceof EAttribute) {
            handleAttribute(Attribute.from(reference));
        }
    }

    @Override
    public void handleEndElement() throws Exception {
        if (!ignoreCleaning) {
            classesStack.removeLast();
            idsStack.removeLast();

            super.handleEndElement();
        } else {
            ignoreCleaning = false;
        }
    }

    /**
     * Creates the root element from the given {@code classifier}.
     */
    private void createRootObject(Classifier classifier) throws Exception {
        Namespace ns = checkNotNull(classifier.getNamespace(),
                "The root element must have a namespace");

        // Retreives the EPackage from NS prefix
        EPackage ePackage = checkNotNull((EPackage) EPackage.Registry.INSTANCE.get(ns.getPrefix()),
                "EPackage " + ns.getPrefix() + " is not registered.");

        // Gets the current EClass
        EClass eClass = (EClass) ePackage.getEClassifier(classifier.getLocalName());

        // Defines the metaclass of the current element if not present
        if (classifier.getMetaClassifier() == null) {
            classifier.setMetaClassifier(new MetaClassifier(ns, eClass.getName()));
        }

        // Defines the classname of the current element
        classifier.setClassName(eClass.getName());

        // Defines the element as root node
        classifier.setRoot(true);

        // Notifies next handlers
        super.handleStartElement(classifier);

        // Saves the current EClass
        classesStack.addLast(eClass);

        // Gets the identifier of the element created by next handlers, and save it
        idsStack.addLast(classifier.getId());
    }

    private void handleFeature(Classifier classifier) throws Exception {
        // Retreive the parent EClass
        EClass parentEClass = classesStack.getLast();

        // Gets the EPackage from it
        EPackage ePackage = parentEClass.getEPackage();
        Namespace ns = Namespace.Registry.getInstance().getFromPrefix(ePackage.getNsPrefix());

        // Gets the structural feature from the parent, according the its local name
        EStructuralFeature eStructuralFeature = parentEClass.getEStructuralFeature(classifier.getLocalName());

        if (eStructuralFeature instanceof EAttribute) {
            handleAttribute(classifier, (EAttribute) eStructuralFeature);
        }
        else if (eStructuralFeature instanceof EReference) {
            handleReference(classifier, ns, (EReference) eStructuralFeature, ePackage);
        }
    }

    private void handleAttribute(Classifier classifier, EAttribute eAttribute) {
        if (waitingAttribute != null) {
            NeoLogger.warn("An attribute still waiting for a value. It wiil be ignored");
        }

        // Waiting a plain text value
        waitingAttribute = new Attribute(eAttribute.getName());
        ignoreCleaning = true;
    }

    private void handleReference(Classifier classifier, Namespace ns, EReference eReference, EPackage ePackage) throws Exception {
        // Gets the type the reference or gets the type from the registered metaclass
        EClass eClass = getEClass(classifier, ns, (EClass) eReference.getEType(), ePackage);

        // Defines the class name and the namespace of the element
        classifier.setClassName(eClass.getName());
        classifier.setNamespace(ns);

        // Notify next handlers of new element, and retreive its identifier
        super.handleStartElement(classifier);
        Identifier currentId = classifier.getId();

        // Create a reference from the parent to this element, with the given local name
        Reference ref = new Reference(eReference.getName());
        ref.setId(idsStack.getLast());
        ref.setIdReference(currentId);
        ref.setContainment(eReference.isContainment());

        EReference eOpposite = eReference.getEOpposite();
        if (eOpposite != null) {
            Reference opposite = new Reference(eOpposite.getName());
            opposite.setContainment(opposite.isContainment());
            ref.setOpposite(opposite);
        }

        super.handleReference(ref);

        // Save EClass and identifier
        classesStack.addLast(eClass);
        idsStack.addLast(currentId);
    }

    private EClass getEClass(Classifier classifier, Namespace ns, EClass eClass, EPackage ePackage) throws Exception {
        MetaClassifier metaClassifier = classifier.getMetaClassifier();

        if (metaClassifier != null) {
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
}
