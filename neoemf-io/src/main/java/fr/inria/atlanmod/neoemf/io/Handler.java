/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.io.proxy.ProxyElement;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;

import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A object that handles events received from a {@link Notifier}.
 *
 * @see Notifier
 */
@ParametersAreNonnullByDefault
public interface Handler {

    /**
     * Handles the start of a task.
     *
     * @see #onComplete()
     * @see Notifier#notifyInitialize()
     */
    void onInitialize() throws IOException;

    /**
     * Handles the start of an element.
     *
     * @param element the element of the new element
     *
     * @see #onEndElement()
     * @see Notifier#notifyStartElement(ProxyElement)
     */
    void onStartElement(ProxyElement element) throws IOException;

    /**
     * Handles an attribute in the current element.
     *
     * @param attribute the new attribute
     *
     * @see #onStartElement(ProxyElement)
     * @see Notifier#notifyAttribute(ProxyAttribute)
     */
    void onAttribute(ProxyAttribute attribute) throws IOException;

    /**
     * Handles a reference from the current element to another.
     *
     * @param reference the new reference
     *
     * @see #onStartElement(ProxyElement)
     * @see Notifier#notifyReference(ProxyReference)
     */
    void onReference(ProxyReference reference) throws IOException;

    /**
     * Handles the end of the current element.
     *
     * @see #onStartElement(ProxyElement)
     * @see Notifier#notifyEndElement()
     */
    void onEndElement() throws IOException;

    /**
     * Handles the end of the current task.
     *
     * @see #onInitialize()
     * @see Notifier#notifyComplete()
     */
    void onComplete() throws IOException;
}
