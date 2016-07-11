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

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.Classifier;
import fr.inria.atlanmod.neoemf.io.beans.Reference;

/**
 * A object able to notify {@link IOHandler handlers}.
 *
 * @param <T> the type of handlers
 */
public interface IONotifier<T extends IOHandler> {

    /**
     * Add an {@link IOHandler handler} that will be notified.
     *
     * @param handler the handler to add
     */
    void addHandler(T handler);

    /**
     * Defines if this notifier has at least one {@link IOHandler handler} to notify.
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
     */
    void notifyStartDocument() throws Exception;

    /**
     * Notifies that start of a new element.
     *
     * @see #notifyEndElement()
     */
    void notifyStartElement(Classifier classifier) throws Exception;

    /**
     * Notifies a new attribute.
     */
    void notifyAttribute(Attribute attribute) throws Exception;

    /**
     * Notifies a new reference.
     */
    void notifyReference(Reference reference) throws Exception;

    /**
     * Notifies the end of the current element.
     *
     * @see #notifyStartElement(Classifier)
     */
    void notifyEndElement() throws Exception;

    /**
     * Notifies the end of the current action.
     *
     * @see #notifyStartDocument()
     */
    void notifyEndDocument() throws Exception;
}
