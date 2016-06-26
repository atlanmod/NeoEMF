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
import fr.inria.atlanmod.neoemf.io.beans.ClassifierElement;
import fr.inria.atlanmod.neoemf.io.beans.NamedElement;
import fr.inria.atlanmod.neoemf.io.beans.Namespace;
import fr.inria.atlanmod.neoemf.io.beans.Reference;
import fr.inria.atlanmod.neoemf.io.internal.InternalHandler;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 *
 */
public class EcoreProcessor extends AbstractInternalProcessor {

    /**
     * Map containing all declared {@link EPackage} for parsing.
     */
    // TODO Use a EPackage.Registry
    private final Map<String, EPackage> ePackages;

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

    public EcoreProcessor(Map<String, EPackage> ePackages, InternalHandler handler) {
        super(handler);
        this.ePackages = ePackages;
        this.classesStack = new ArrayDeque<>();
        this.idsStack = new ArrayDeque<>();
        this.wasAttribute = false;
    }

    @Override
    public void handleStartElement(ClassifierElement element) throws Exception {
        EClass eClass;
        EPackage ePackage;

        // Is root
        if (element.getNamespace().getPrefix() != null) {
            // Retreives the EPackage from NS prefix
            ePackage = ePackages.get(element.getNamespace().getPrefix());
            Namespace ns = new Namespace(ePackage.getNsPrefix(), ePackage.getNsURI());

            // Gets the current EClass
            eClass = (EClass) ePackage.getEClassifier(element.getLocalName());

            // Defines the metaclass of the current element if not present
            if (element.getMetaclass() == null) {
                NamedElement metaClass = new NamedElement();
                metaClass.setNamespace(ns);
                metaClass.setLocalName(eClass.getName());
                element.setMetaclass(metaClass);
            }

            // Defines the classname of the current element
            element.setClassName(eClass.getName());

            // Notifies next handlers
            super.handleStartElement(element);

            // Saves the current EClass
            classesStack.addLast(eClass);

            // Gets the identifier of the element created by next handlers, and save it
            idsStack.addLast(element.getId());
        }
        // Is a feature of parent
        else {
            // Retreive the parent EClass
            EClass parentEClass = classesStack.getLast();

            // Gets the EPackage from it
            ePackage = parentEClass.getEPackage();
            Namespace ns = new Namespace(ePackage.getNsPrefix(), ePackage.getNsURI());

            // Gets the structural feature from the parent, according the its local name
            EStructuralFeature eStructuralFeature = parentEClass.getEStructuralFeature(element.getLocalName());

            // Is an attribute
            if (eStructuralFeature instanceof EAttribute) {
                // Creates an attribute
                Attribute attr = new Attribute();
                attr.setNamespace(ns);
                attr.setLocalName(eStructuralFeature.getName());
                attr.setIndex(InternalEObject.EStore.NO_INDEX);

                // Waiting a plain text value
                this.waitingAttribute = attr;
                wasAttribute = true;
            }

            // Is a reference
            else {
                // Gets the type the reference
                eClass = (EClass) eStructuralFeature.getEType();

                NamedElement metaClass = element.getMetaclass();
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

                    metaClass = new NamedElement();
                    metaClass.setNamespace(ns);
                    metaClass.setLocalName(eClass.getName());
                    element.setMetaclass(metaClass);
                }

                // Defines the class name and the namespace of the element
                element.setClassName(eClass.getName());
                element.setNamespace(ns);

                // Notify next handlers of new element, and retreive its identifier
                super.handleStartElement(element);
                String currentId = element.getId();

                // Create a reference from the parent to this element, with the given local name
                Reference ref = new Reference();
                ref.setNamespace(ns);
                ref.setLocalName(eStructuralFeature.getName());
                ref.setIndex(InternalEObject.EStore.NO_INDEX);
                ref.setId(idsStack.getLast());
                ref.setValue(currentId);
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
    public void handleEndElement() throws Exception {
        if (!wasAttribute) {
            classesStack.removeLast();
            idsStack.removeLast();

            super.handleEndElement();
        }

        wasAttribute = false;
    }
}
