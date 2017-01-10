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

import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
import fr.inria.atlanmod.neoemf.io.structure.Reference;

/**
 * A object able to handle notifications sent by a {@link InputNotifier}.
 */
public interface InputHandler {

    /**
     * Process the start of a document.
     *
     * @see InputNotifier#notifyStartDocument()
     */
    void processStartDocument();

    /**
     * Process the start of an element.
     *
     * @param classifier the classifier of the new element
     *
     * @see InputNotifier#notifyStartElement(Classifier)
     */
    void processStartElement(Classifier classifier);

    /**
     * Process an attribute in the current element.
     * <p/>
     * An attribute is a simple key/value.
     *
     * @param attribute the new attribute
     *
     * @see InputNotifier#notifyAttribute(Attribute)
     */
    void processAttribute(Attribute attribute);

    /**
     * Process a reference from the current element to another element.
     * <p/>
     * A reference is an attribute which is link to another element.
     *
     * @param reference the new reference
     *
     * @see InputNotifier#notifyReference(Reference)
     */
    void processReference(Reference reference);

    /**
     * Process the end of the current element.
     *
     * @see InputNotifier#notifyEndElement()
     */
    void processEndElement();

    /**
     * Process the end of a document.
     *
     * @see InputNotifier#notifyEndDocument()
     */
    void processEndDocument();

    /**
     * Process a set of characters.
     *
     * @param characters the new characters
     */
    void processCharacters(String characters);
}
