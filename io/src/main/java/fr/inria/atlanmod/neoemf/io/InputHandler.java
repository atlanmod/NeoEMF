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

import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.Classifier;
import fr.inria.atlanmod.neoemf.io.beans.Reference;

/**
 * A object able to handle notifications sent by a {@link InputNotifier}.
 */
public interface InputHandler {

    /**
     * Handle the start of a document.
     */
    void processStartDocument() throws Exception;

    /**
     * Handle the start of an element.
     */
    void processStartElement(Classifier classifier) throws Exception;

    /**
     * Handle an attribute in the current element.
     * <p/>
     * An attribute is a simple key/value.
     */
    void processAttribute(Attribute attribute) throws Exception;

    /**
     * Handle a reference from the current element to another element.
     * <p/>
     * A reference is an attribute which is link to another element.
     */
    void processReference(Reference reference) throws Exception;

    /**
     * Handle the end of the current element.
     */
    void processEndElement() throws Exception;

    /**
     * Handle the end of a document.
     */
    void processEndDocument() throws Exception;
}
