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

import fr.inria.atlanmod.neoemf.io.structure.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.structure.BasicElement;
import fr.inria.atlanmod.neoemf.io.structure.BasicId;
import fr.inria.atlanmod.neoemf.io.structure.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.structure.BasicNamespace;
import fr.inria.atlanmod.neoemf.io.structure.BasicReference;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;

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
    private final Deque<BasicId> idsStack;

    /**
     * Attribute that waits a value (via {@link #onCharacters(String)}.
     */
    private BasicAttribute waitingAttribute;

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
    public void onStartElement(BasicElement element) {
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
    public void onAttribute(BasicAttribute attribute) {
        EClass eClass = classesStack.getLast();
        EStructuralFeature feature = eClass.getEStructuralFeature(attribute.name());

        // Checks that the attribute is well a attribute
        if (EAttribute.class.isInstance(feature)) {
            EAttribute eAttribute = EAttribute.class.cast(feature);
            attribute.isMany(eAttribute.isMany());

            notifyAttribute(attribute);
        }

        // Otherwise redirect to the reference handler
        else if (EReference.class.isInstance(feature)) {
            onReference(BasicReference.from(attribute));
        }
    }

    @Override
    public void onReference(BasicReference reference) {
        EClass eClass = classesStack.getLast();
        EStructuralFeature feature = eClass.getEStructuralFeature(reference.name());

        // Checks that the reference is well a reference
        if (EReference.class.isInstance(feature)) {
            EReference eReference = EReference.class.cast(feature);

            AtomicInteger index = new AtomicInteger();

            Arrays.stream(reference.idReference().value().split(" "))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(s -> {
                        BasicReference newReference = new BasicReference(reference.name());

                        newReference.id(reference.id());
                        newReference.idReference(BasicId.original(s));

                        newReference.index(eReference.isMany() && !eReference.isContainment() ? index.getAndIncrement() : -1);

                        newReference.isContainment(eReference.isContainment());
                        newReference.isMany(eReference.isMany());

                        EClass referenceType = eReference.getEReferenceType();
                        newReference.metaclassReference(new BasicMetaclass(
                                BasicNamespace.Registry.getInstance().getFromUri(referenceType.getEPackage().getNsURI()),
                                referenceType.getName()));

                        return newReference;
                    })
                    .forEach(this::notifyReference);
        }

        // Otherwise redirect to the attribute handler
        else if (EAttribute.class.isInstance(feature)) {
            onAttribute(BasicAttribute.from(reference));
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
    private void processElementAsRoot(BasicElement element) {
        BasicNamespace ns = checkNotNull(element.ns(), "The root element must have a namespace");

        // Retrieves the EPackage from NS prefix
        EPackage ePackage = checkNotNull(EPackage.class.cast(EPackage.Registry.INSTANCE.get(ns.uri())),
                "EPackage %s is not registered.", ns.uri());

        // Gets the current EClass
        EClass eClass = checkNotNull(EClass.class.cast(ePackage.getEClassifier(element.name())),
                "Cannot retrieve EClass {0} from the EPackage {1}", element.name(), ePackage);

        // Defines the metaclass of the current element if not present
        if (isNull(element.metaclass())) {
            element.metaclass(new BasicMetaclass(ns, eClass.getName()));
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
     * @see #processElementAsAttribute(BasicElement, EAttribute)
     * @see #processElementAsReference(BasicElement, BasicNamespace, EReference, EPackage)
     */
    private void processElementAsFeature(BasicElement element) {
        // Retrieve the parent EClass
        EClass parentEClass = classesStack.getLast();

        // Gets the EPackage from it
        BasicNamespace ns;
        EPackage ePackage;

        if (nonNull(element.ns())) {
            ns = element.ns();
            ePackage = EPackage.Registry.INSTANCE.getEPackage(ns.uri());
        }
        else {
            ePackage = parentEClass.getEPackage();
            ns = BasicNamespace.Registry.getInstance().getFromPrefix(ePackage.getNsPrefix());
        }

        // Gets the structural feature from the parent, according the its local name (the attr/ref name)
        EStructuralFeature feature = parentEClass.getEStructuralFeature(element.name());

        if (EAttribute.class.isInstance(feature)) {
            processElementAsAttribute(element, EAttribute.class.cast(feature));
        }
        else if (EReference.class.isInstance(feature)) {
            processElementAsReference(element, ns, EReference.class.cast(feature), ePackage);
        }
    }

    /**
     * Processes an attribute.
     *
     * @param element   the element representing the attribute
     * @param attribute the associated EMF attribute
     */
    private void processElementAsAttribute(BasicElement element, EAttribute attribute) {
        if (nonNull(waitingAttribute)) {
            Log.warn("An attribute still waiting for a value : it will be ignored");
        }

        // Waiting a plain text value
        waitingAttribute = new BasicAttribute(attribute.getName());
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
    private void processElementAsReference(BasicElement element, BasicNamespace ns, EReference reference, EPackage ePackage) {
        // Gets the type the reference or gets the type from the registered metaclass
        EClass eClass = resolveInstanceOf(element, ns, EClass.class.cast(reference.getEType()), ePackage);

        element.ns(ns);

        // Notify next handlers of new element, and retrieve its identifier
        notifyStartElement(element);
        BasicId currentId = element.id();

        // Create a reference from the parent to this element, with the given local name
        if (reference.isContainment()) {
            BasicReference ref = new BasicReference(reference.getName());
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
    private EClass resolveInstanceOf(BasicElement element, BasicNamespace ns, EClass superClass, EPackage ePackage) {
        BasicMetaclass metaClass = element.metaclass();

        if (nonNull(metaClass)) {
            EClass subClass = EClass.class.cast(ePackage.getEClassifier(metaClass.name()));

            // Checks that the metaclass is a subtype of the reference type.
            // If true, use it instead of supertype
            if (superClass.isSuperTypeOf(subClass)) {
                superClass = subClass;
            }
            else {
                throw new IllegalArgumentException(
                        String.format("%s is not a subclass of %s", subClass.getName(), superClass.getName()));
            }
        }

        // If not present, create the metaclass from the current class
        else {
            element.metaclass(new BasicMetaclass(ns, superClass.getName()));
        }

        return superClass;
    }
}
