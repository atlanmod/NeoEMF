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
import fr.inria.atlanmod.neoemf.io.beans.NamedElement;
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

/**
 * An {@link InternalHandler internal handler} that creates and links simple elements to an Ecore structure.
 */
public class EcoreHandler extends AbstractDelegatedInternalHandler {

    /**
     * Stack containing previous {@link EClass}.
     */
    private final Deque<EClass> classesStack;

    /**
     * Stack containing previous identifier.
     */
    private final Deque<String> idsStack;

    /**
     * Attribute waiting a value (via {@link #handleCharacters(String)}.
     */
    private Attribute waitingAttribute;

    /**
     * Defines if the previous element was an attribute, or not.
     */
    private boolean wasAttribute;

    public EcoreHandler(InternalHandler handler) {
        super(handler);
        this.classesStack = new ArrayDeque<>();
        this.idsStack = new ArrayDeque<>();
        this.wasAttribute = false;
    }

    @Override
    public void handleStartElement(Classifier classifier) throws Exception {
        EClass eClass;
        EPackage ePackage;

        Namespace ns = classifier.getNamespace();

        // Is root
        if (ns != null) {
            // Retreives the EPackage from NS prefix
            ePackage = (EPackage) EPackage.Registry.INSTANCE.get(ns.getPrefix());

            // Gets the current EClass
            eClass = (EClass) ePackage.getEClassifier(classifier.getLocalName());

            // Defines the metaclass of the current element if not present
            if (classifier.getMetaclass() == null) {
                classifier.setMetaclass(new NamedElement(ns, eClass.getName()));
            }

            // Defines the classname of the current element
            classifier.setClassName(eClass.getName());

            // Notifies next handlers
            super.handleStartElement(classifier);

            // Saves the current EClass
            classesStack.addLast(eClass);

            // Gets the identifier of the element created by next handlers, and save it
            idsStack.addLast(classifier.getId());
        }
        // Is a feature of parent
        else {
            // Retreive the parent EClass
            EClass parentEClass = classesStack.getLast();

            // Gets the EPackage from it
            ePackage = parentEClass.getEPackage();
            ns = Namespace.Registry.getInstance().getFromPrefix(ePackage.getNsPrefix());

            // Gets the structural feature from the parent, according the its local name
            EStructuralFeature eStructuralFeature = parentEClass.getEStructuralFeature(classifier.getLocalName());

            // Is an attribute
            if (eStructuralFeature instanceof EAttribute) {
                EAttribute eAttribute = (EAttribute) eStructuralFeature;

                // Creates an attribute
                Attribute attr = new Attribute();
                attr.setNamespace(ns);
                attr.setLocalName(eAttribute.getName());

                // Waiting a plain text value
                this.waitingAttribute = attr;
                wasAttribute = true;
            }

            // Is a reference
            else {
                EReference eReference = (EReference) eStructuralFeature;

                // Gets the type the reference
                eClass = (EClass) eReference.getEType();

                NamedElement metaClass = classifier.getMetaclass();
                // Checks that the metaclass is a subtype of the reference type : if true, use it instead of supertype
                if (metaClass != null) {
                    EClass subEClass = (EClass) ePackage.getEClassifier(metaClass.getLocalName());

                    if (eClass.isSuperTypeOf(subEClass)) {
                        eClass = subEClass;
                    }
                    else {
                        throw new Exception(subEClass.getName() + " is not a subclass of " + eClass.getName());
                    }
                }
                // If not present, create the metaclass
                else {
                    metaClass = new NamedElement(ns, eClass.getName());
                    classifier.setMetaclass(metaClass);
                }

                // Defines the class name and the namespace of the element
                classifier.setClassName(eClass.getName());
                classifier.setNamespace(ns);

                // Notify next handlers of new element, and retreive its identifier
                super.handleStartElement(classifier);
                String currentId = classifier.getId();

                // Create a reference from the parent to this element, with the given local name
                Reference ref = new Reference();
                ref.setNamespace(ns);
                ref.setLocalName(eReference.getName());
                ref.setId(idsStack.getLast());
                ref.setValue(currentId);
                ref.setContainment(eReference.isContainment());
                handleReference(ref);

                // Save EClass and identifier
                classesStack.addLast(eClass);
                idsStack.addLast(currentId);
            }
        }
    }

    @Override
    public void handleCharacters(String characters) throws Exception {

        // Defines the value of the waiting attribute
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
            if (attribute.getNamespace() == null) {
                EPackage ePackage = eClass.getEPackage();
                attribute.setNamespace(Namespace.Registry.getInstance().getFromPrefix(ePackage.getNsPrefix()));
            }
            super.handleAttribute(attribute);
        }

        // Otherwise redirect to the reference handler
        else if (eStructuralFeature instanceof EReference) {
            NeoLogger.warn("Feature misinterpreted during the analysis : " +
                    "the attribute \"" + attribute.getLocalName() + "\" is a reference.");

            Reference reference = new Reference();
            reference.setNamespace(attribute.getNamespace());
            reference.setIndex(attribute.getIndex());
            reference.setLocalName(attribute.getLocalName());
            reference.setId(attribute.getId());
            reference.setValue(attribute.getValue());

            handleReference(reference);
        }

        else {
            NeoLogger.warn("Attribute \"" + eClass.getName() + '#' + attribute.getLocalName() + "\" does not exist in the metamodel. It will be ignored");
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

            // Update namespace if not already present
            if (reference.getNamespace() == null) {
                EPackage ePackage = eClass.getEPackage();
                reference.setNamespace(Namespace.Registry.getInstance().getFromPrefix(ePackage.getNsPrefix()));
            }
            super.handleReference(reference);
        }

        // Otherwise redirect to the attribute handler
        else if (eStructuralFeature instanceof EAttribute) {
            NeoLogger.warn("Feature misinterpreted during the analysis : " +
                    "the reference \"" + reference.getLocalName() + "\" is an attribute.");

            Attribute attribute = new Attribute();
            attribute.setNamespace(reference.getNamespace());
            attribute.setIndex(reference.getIndex());
            attribute.setLocalName(reference.getLocalName());
            attribute.setId(reference.getId());
            attribute.setValue(reference.getValue());

            handleAttribute(attribute);
        }

        else {
            NeoLogger.warn("Reference \"" + eClass.getName() + '#' + reference.getLocalName() + "\" does not exist in the metamodel. It will be ignored");
        }
    }

    @Override
    public void handleEndElement() throws Exception {
        if (!wasAttribute) {
            classesStack.removeLast();
            idsStack.removeLast();

            super.handleEndElement();
        }

        wasAttribute = false;
    }
}
