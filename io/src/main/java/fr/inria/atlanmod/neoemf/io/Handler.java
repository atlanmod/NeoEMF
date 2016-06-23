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
 * A basic handler.
 */
public interface Handler {

    /**
     * Handle the start of a document.
     */
    void handleStartDocument() throws Exception;

    /**
     * Handle the start of an element.
     *
     * @param nsUri the nsUri of the element
     * @param name the name of the element in its nsUri
     * @param reference the reference identifier of the element
     */
    void handleStartElement(String nsUri, String name, String reference) throws Exception;

    /**
     * Handle a metaclass in the current element.
     * @param nsUri the nsUri URI of the metaclass
     * @param name the name of the metaclass
     */
    void handleMetaClass(String nsUri, String name) throws Exception;

    /**
     * Handle an attribute in the current element.
     * <p/>
     * An attribute is a simple key/value.
     *
     * @param nsUri the nsUri of the attribute
     * @param name the name of the attribute in its nsUri
     * @param value     the value of the attribute
     */
    void handleAttribute(String nsUri, String name, int index, String value) throws Exception;

    /**
     * Handle a reference from the current element to another element.
     * <p/>
     * A reference is an attribute which is link to another element.
     *
     * @param nsUri the nsUri of the reference
     * @param name the name of the reference in its nsUri
     * @param reference the referenced element identifier
     */
    void handleReference(String nsUri, String name, int index, String reference) throws Exception;

    /**
     * Handle the end of the current element.
     */
    void handleEndElement() throws Exception;

    /**
     * Handle the end of a document.
     */
    void handleEndDocument() throws Exception;
}
