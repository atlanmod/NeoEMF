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

import static java.util.Objects.nonNull;

/**
 * An object that notifies registered {@link Handler}s of events during an I/O process, such as import or export.
 *
 * @param <T> the type of handlers
 *
 * @see Handler
 */
public interface Notifier<T extends Handler> {

    /**
     * Returns the {@link Handler} that will be notified.
     *
     * @return the handler
     */
    T handler();

    /**
     * Defines the {@link Handler} that will be notified.
     *
     * @param handler the handler
     */
    void handler(T handler);

    /**
     * Defines if this notifier has a {@link Handler} to notify.
     *
     * @return {@code true} if this notifier has a handler to notify.
     */
    default boolean hasHandler() {
        return nonNull(handler());
    }

    /**
     * Notifies all registered handlers of the start of a document.
     *
     * @see #notifyEndDocument()
     * @see Handler#processStartDocument()
     */
    default void notifyStartDocument() {
        handler().processStartDocument();
    }

    /**
     * Notifies all registered handlers of the start of a new element.
     *
     * @param classifier the classifier of the new element
     *
     * @see #notifyEndElement()
     * @see Handler#processStartElement(RawClassifier)
     */
    default void notifyStartElement(RawClassifier classifier) {
        handler().processStartElement(classifier);
    }

    /**
     * Notifies all registered handlers of a new attribute.
     *
     * @param attribute the new attribute
     *
     * @see Handler#processAttribute(RawAttribute)
     */
    default void notifyAttribute(RawAttribute attribute) {
        handler().processAttribute(attribute);
    }

    /**
     * Notifies all registered handlers of a new reference.
     *
     * @param reference the new reference
     *
     * @see Handler#processReference(RawReference)
     */
    default void notifyReference(RawReference reference) {
        handler().processReference(reference);
    }

    /**
     * Notifies all registered handlers of a new set of characters.
     *
     * @param characters the new characters
     *
     * @see Handler#processCharacters(String)
     */
    default void notifyCharacters(String characters) {
        handler().processCharacters(characters);
    }

    /**
     * Notifies all registered handlers of the end of the current element.
     *
     * @see #notifyStartElement(RawClassifier)
     * @see Handler#processEndElement()
     */
    default void notifyEndElement() {
        handler().processEndElement();
    }

    /**
     * Notifies all registered handlers of the end of the current document.
     *
     * @see #notifyStartDocument()
     * @see Handler#processEndDocument()
     */
    default void notifyEndDocument() {
        handler().processEndDocument();
    }
}
