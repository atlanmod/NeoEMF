/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

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
    default void notifyInitialize() throws IOException {
        for (H h : next()) {
            h.onInitialize();
        }
    }

    /**
     * Notifies all registered handlers of the start of a new element.
     *
     * @param element the element of the new element
     *
     * @see #notifyEndElement()
     * @see Handler#onStartElement(BasicElement)
     */
    default void notifyStartElement(BasicElement element) throws IOException {
        checkNotNull(element, "element");

        for (H h : next()) {
            h.onStartElement(element);
        }
    }

    /**
     * Notifies all registered handlers of a new attribute.
     *
     * @param attribute the new attribute
     *
     * @see Handler#onAttribute(BasicAttribute)
     */
    default void notifyAttribute(BasicAttribute attribute) throws IOException {
        checkNotNull(attribute, "attribute");

        for (H h : next()) {
            h.onAttribute(attribute);
        }
    }

    /**
     * Notifies all registered handlers of a new reference.
     *
     * @param reference the new reference
     *
     * @see Handler#onReference(BasicReference)
     */
    default void notifyReference(BasicReference reference) throws IOException {
        checkNotNull(reference, "reference");

        for (H h : next()) {
            h.onReference(reference);
        }
    }

    /**
     * Notifies all registered handlers of the end of the current element.
     *
     * @see #notifyStartElement(BasicElement)
     * @see Handler#onEndElement()
     */
    default void notifyEndElement() throws IOException {
        for (H h : next()) {
            h.onEndElement();
        }
    }

    /**
     * Notifies all registered handlers of the end of the current task.
     *
     * @see #notifyInitialize()
     * @see Handler#onComplete()
     */
    default void notifyComplete() throws IOException {
        for (H h : next()) {
            h.onComplete();
        }
    }
}
