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

import fr.inria.atlanmod.neoemf.io.structure.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.structure.BasicElement;
import fr.inria.atlanmod.neoemf.io.structure.BasicReference;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * An object that notifies registered {@link Handler}s of events during an I/O process.
 *
 * @param <H> the type of handlers
 *
 * @see Handler
 */
@ParametersAreNonnullByDefault
public interface Notifier<H extends Handler> {

    /**
     * Returns the {@link Handler}s that will be notified by this {@code Notifier}.
     *
     * @return the handler to notify
     */
    @Nonnull
    Iterable<H> next();

    /**
     * Notifies all registered handlers of the start of a task.
     *
     * @see #notifyComplete()
     * @see Handler#onInitialize()
     */
    default void notifyInitialize() {
        next().forEach(Handler::onInitialize);
    }

    /**
     * Notifies all registered handlers of the start of a new element.
     *
     * @param element the element of the new element
     *
     * @see #notifyEndElement()
     * @see Handler#onStartElement(BasicElement)
     */
    default void notifyStartElement(BasicElement element) {
        checkNotNull(element);

        next().forEach(h -> h.onStartElement(element));
    }

    /**
     * Notifies all registered handlers of a new attribute.
     *
     * @param attribute the new attribute
     *
     * @see Handler#onAttribute(BasicAttribute)
     */
    default void notifyAttribute(BasicAttribute attribute) {
        checkNotNull(attribute);

        next().forEach(h -> h.onAttribute(attribute));
    }

    /**
     * Notifies all registered handlers of a new reference.
     *
     * @param reference the new reference
     *
     * @see Handler#onReference(BasicReference)
     */
    default void notifyReference(BasicReference reference) {
        checkNotNull(reference);

        next().forEach(h -> h.onReference(reference));
    }

    /**
     * Notifies all registered handlers of a new set of characters.
     *
     * @param characters the new characters
     *
     * @see Handler#onCharacters(String)
     */
    default void notifyCharacters(String characters) {
        checkNotNull(characters);

        next().forEach(h -> h.onCharacters(characters));
    }

    /**
     * Notifies all registered handlers of the end of the current element.
     *
     * @see #notifyStartElement(BasicElement)
     * @see Handler#onEndElement()
     */
    default void notifyEndElement() {
        next().forEach(Handler::onEndElement);
    }

    /**
     * Notifies all registered handlers of the end of the current task.
     *
     * @see #notifyInitialize()
     * @see Handler#onComplete()
     */
    default void notifyComplete() {
        next().forEach(Handler::onComplete);
    }
}
