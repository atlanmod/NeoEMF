/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
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
    void processStartDocument() throws Exception;

    /**
     * Process the start of an element.
     *
     * @see InputNotifier#notifyStartElement(Classifier)
     */
    void processStartElement(Classifier classifier) throws Exception;

    /**
     * Process an attribute in the current element.
     * <p/>
     * An attribute is a simple key/value.
     *
     * @see InputNotifier#notifyAttribute(Attribute)
     */
    void processAttribute(Attribute attribute) throws Exception;

    /**
     * Process a reference from the current element to another element.
     * <p/>
     * A reference is an attribute which is link to another element.
     *
     * @see InputNotifier#notifyReference(Reference)
     */
    void processReference(Reference reference) throws Exception;

    /**
     * Process the end of the current element.
     *
     * @see InputNotifier#notifyEndElement()
     */
    void processEndElement() throws Exception;

    /**
     * Process the end of a document.
     *
     * @see InputNotifier#notifyEndDocument()
     */
    void processEndDocument() throws Exception;
}
