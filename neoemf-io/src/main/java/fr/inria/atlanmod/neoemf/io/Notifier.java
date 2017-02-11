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
import fr.inria.atlanmod.neoemf.io.structure.RawElement;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An object that notifies registered {@link Handler}s of events during an I/O process, such as import or export.
 *
 * @param <H> the type of handlers
 *
 * @see Handler
 */
@ParametersAreNonnullByDefault
public interface Notifier<H extends Handler> {

    /**
     * Returns the {@link Handler} that will be notified by this {@code Notifier}.
     *
     * @return the handler to notify
     */
    @Nonnull
    Iterable<H> next();

    /**
     * Notifies all registered handlers of the start of a document.
     *
     * @see #notifyEndDocument()
     * @see Handler#handleStartDocument()
     */
    default void notifyStartDocument() {
        next().forEach(Handler::handleStartDocument);
    }

    /**
     * Notifies all registered handlers of the start of a new element.
     *
     * @param element the element of the new element
     *
     * @see #notifyEndElement()
     * @see Handler#handleStartElement(RawElement)
     */
    default void notifyStartElement(RawElement element) {
        checkNotNull(element);

        next().forEach(h -> h.handleStartElement(element));
    }

    /**
     * Notifies all registered handlers of a new attribute.
     *
     * @param attribute the new attribute
     *
     * @see Handler#handleAttribute(RawAttribute)
     */
    default void notifyAttribute(RawAttribute attribute) {
        checkNotNull(attribute);

        next().forEach(h -> h.handleAttribute(attribute));
    }

    /**
     * Notifies all registered handlers of a new reference.
     *
     * @param reference the new reference
     *
     * @see Handler#handleReference(RawReference)
     */
    default void notifyReference(RawReference reference) {
        checkNotNull(reference);

        next().forEach(h -> h.handleReference(reference));
    }

    /**
     * Notifies all registered handlers of a new set of characters.
     *
     * @param characters the new characters
     *
     * @see Handler#handleCharacters(String)
     */
    default void notifyCharacters(String characters) {
        checkNotNull(characters);

        next().forEach(h -> h.handleCharacters(characters));
    }

    /**
     * Notifies all registered handlers of the end of the current element.
     *
     * @see #notifyStartElement(RawElement)
     * @see Handler#handleEndElement()
     */
    default void notifyEndElement() {
        next().forEach(Handler::handleEndElement);
    }

    /**
     * Notifies all registered handlers of the end of the current document.
     *
     * @see #notifyStartDocument()
     * @see Handler#handleEndDocument()
     */
    default void notifyEndDocument() {
        next().forEach(Handler::handleEndDocument);
    }
}
