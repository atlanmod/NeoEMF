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

import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawClassifier;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;

/**
 * An object that notifies registered {@link InputHandler}s of events during an I/O process, such as import or export.
 *
 * @param <T> the type of handlers
 *
 * @see InputHandler
 */
public interface InputNotifier<T extends InputHandler> {

    /**
     * Adds an {@link InputHandler} that will be notified.
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
     * Returns all registered handlers.
     *
     * @return an collection
     */
    Iterable<T> getHandlers();

    /**
     * Notifies all registered handlers of the start of a document.
     *
     * @see #notifyEndDocument()
     * @see InputHandler#processStartDocument()
     */
    default void notifyStartDocument() {
        getHandlers().forEach(InputHandler::processStartDocument);
    }

    /**
     * Notifies all registered handlers of the start of a new element.
     *
     * @param classifier the classifier of the new element
     *
     * @see #notifyEndElement()
     * @see InputHandler#processStartElement(RawClassifier)
     */
    default void notifyStartElement(RawClassifier classifier) {
        getHandlers().forEach(h -> h.processStartElement(classifier));
    }

    /**
     * Notifies all registered handlers of a new attribute.
     *
     * @param attribute the new attribute
     *
     * @see InputHandler#processAttribute(RawAttribute)
     */
    default void notifyAttribute(RawAttribute attribute) {
        getHandlers().forEach(h -> h.processAttribute(attribute));
    }

    /**
     * Notifies all registered handlers of a new reference.
     *
     * @param reference the new reference
     *
     * @see InputHandler#processReference(RawReference)
     */
    default void notifyReference(RawReference reference) {
        getHandlers().forEach(h -> h.processReference(reference));
    }

    /**
     * Notifies all registered handlers of a new set of characters.
     *
     * @param characters the new characters
     *
     * @see InputHandler#processCharacters(String)
     */
    default void notifyCharacters(String characters) {
        getHandlers().forEach(p -> p.processCharacters(characters));
    }

    /**
     * Notifies all registered handlers of the end of the current element.
     *
     * @see #notifyStartElement(RawClassifier)
     * @see InputHandler#processEndElement()
     */
    default void notifyEndElement() {
        getHandlers().forEach(InputHandler::processEndElement);
    }

    /**
     * Notifies all registered handlers of the end of the current document.
     *
     * @see #notifyStartDocument()
     * @see InputHandler#processEndDocument()
     */
    default void notifyEndDocument() {
        getHandlers().forEach(InputHandler::processEndDocument);
    }
}
