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

/**
 * A structural handler that receives and uses events sent from a {@link Notifier} where it has to be registered by
 * the {@link Notifier#addHandler(Handler)} method.
 */
public interface InternalHandler extends Handler {

    /**
     * Handle the start of an element.
     *
     * @param namespace the namespace of the element
     * @param localName the name of the element in its namespace
     */
    void handleStartElement(String prefix, String namespace, String localName, String reference) throws Exception;

    /**
     * Handle an attribute in the current element.
     * <p/>
     * An attribute is a simple key/value.
     *
     * @param namespace the namespace of the attribute
     * @param localName the name of the attribute in its namespace
     * @param value     the value of the attribute
     */
    void handleAttribute(String namespace, String localName, String value) throws Exception;

    /**
     * Handle a reference from the current element to another element.
     * <p/>
     * A reference is an attribute which is link to another element.
     *
     * @param namespace the namespace of the attribute
     * @param localName the name of the attribute in its namespace
     * @param reference the value of the attribute
     */
    void handleReference(String namespace, String localName, String reference) throws Exception;

    /**
     * Handle the end of the current element.
     */
    void handleEndElement() throws Exception;
}
