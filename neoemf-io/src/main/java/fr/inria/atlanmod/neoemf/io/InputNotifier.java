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

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
import fr.inria.atlanmod.neoemf.io.structure.Reference;

/**
 * A object able to notify {@link InputHandler}.
 *
 * @param <T> the type of handlers
 */
public interface InputNotifier<T extends InputHandler> {

    /**
     * Add an {@link InputHandler} that will be notified.
     *
     * @param handler the handler to add
     */
    void addHandler(T handler);

    /**
     * Defines if this notifier has at least one {@link InputHandler} to notify.
     *
     * @return {@code true} if this notifier has at least one handler to notify.
     */
    boolean hasHandler();

    /**
     * Returns an immutable collection of registered handlers.
     *
     * @return an immutable collection
     */
    Iterable<T> getHandlers();

    /**
     * Notifies that start of an action.
     *
     * @see #notifyEndDocument()
     * @see InputHandler#processStartDocument()
     */
    default void notifyStartDocument() {
        getHandlers().forEach(InputHandler::processStartDocument);
    }

    /**
     * Notifies that start of a new element.
     *
     * @param classifier the classifier of the new element
     *
     * @see #notifyEndElement()
     * @see InputHandler#processStartElement(Classifier)
     */
    default void notifyStartElement(Classifier classifier) {
        getHandlers().forEach(h -> h.processStartElement(classifier));
    }

    /**
     * Notifies a new attribute.
     *
     * @param attribute the new attribute
     *
     * @see InputHandler#processAttribute(Attribute)
     */
    default void notifyAttribute(Attribute attribute) {
        getHandlers().forEach(h -> h.processAttribute(attribute));
    }

    /**
     * Notifies a new reference.
     *
     * @param reference the new reference
     *
     * @see InputHandler#processReference(Reference)
     */
    default void notifyReference(Reference reference) {
        getHandlers().forEach(h -> h.processReference(reference));
    }

    /**
     * Notifies a new set of characters.
     *
     * @param characters the new characters
     */
    default void notifyCharacters(String characters) {
        getHandlers().forEach(p -> p.processCharacters(characters));
    }

    /**
     * Notifies the end of the current element.
     *
     * @see #notifyStartElement(Classifier)
     * @see InputHandler#processEndElement()
     */
    default void notifyEndElement() {
        getHandlers().forEach(InputHandler::processEndElement);
    }

    /**
     * Notifies the end of the current action.
     *
     * @see #notifyStartDocument()
     * @see InputHandler#processEndDocument()
     */
    default void notifyEndDocument() {
        getHandlers().forEach(InputHandler::processEndDocument);
    }
}
