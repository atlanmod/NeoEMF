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

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object that notifies registered {@link Handler}s of events during an I/O process.
 *
 * @see Handler
 */
@ParametersAreNonnullByDefault
public interface Notifier {

    /**
     * Notifies all registered handlers of the start of a task.
     *
     * @see #notifyComplete()
     * @see Handler#onInitialize()
     */
    void notifyInitialize() throws IOException;

    /**
     * Notifies all registered handlers of the start of a new element.
     *
     * @param element the element of the new element
     *
     * @see #notifyEndElement()
     * @see Handler#onStartElement(BasicElement)
     */
    void notifyStartElement(BasicElement element) throws IOException;

    /**
     * Notifies all registered handlers of a new attribute.
     *
     * @param attribute the new attribute
     *
     * @see Handler#onAttribute(BasicAttribute)
     */
    void notifyAttribute(BasicAttribute attribute) throws IOException;

    /**
     * Notifies all registered handlers of a new reference.
     *
     * @param reference the new reference
     *
     * @see Handler#onReference(BasicReference)
     */
    void notifyReference(BasicReference reference) throws IOException;

    /**
     * Notifies all registered handlers of the end of the current element.
     *
     * @see #notifyStartElement(BasicElement)
     * @see Handler#onEndElement()
     */
    void notifyEndElement() throws IOException;

    /**
     * Notifies all registered handlers of the end of the current task.
     *
     * @see #notifyInitialize()
     * @see Handler#onComplete()
     */
    void notifyComplete() throws IOException;
}
