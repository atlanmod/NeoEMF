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

public interface Notifier<T extends Handler> {

    /**
     * Add an {@link Handler handler} that will be notified.
     *
     * @param handler the handler to add
     */
    void addHandler(T handler);

    /**
     * Defines if this notifier has at least one {@link Handler handler} to notify.
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

    void notifyStartDocument() throws Exception;

    void notifyStartElement(String nsUri, String name, String reference) throws Exception;

    void notifyMetaClass(String nsUri, String name) throws Exception;

    void notifyAttribute(String nsUri, String name, int index, String value) throws Exception;

    void notifyReference(String nsUri, String name, int index, String reference) throws Exception;

    void notifyEndElement() throws Exception;

    void notifyEndDocument() throws Exception;
}
