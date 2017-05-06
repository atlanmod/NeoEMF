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

import fr.inria.atlanmod.neoemf.io.structure.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.structure.BasicElement;
import fr.inria.atlanmod.neoemf.io.structure.BasicReference;

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
    void onInitialize();

    /**
     * Handles the start of an element.
     *
     * @param element the element of the new element
     *
     * @see #onEndElement()
     * @see Notifier#notifyStartElement(BasicElement)
     */
    void onStartElement(BasicElement element);

    /**
     * Handles an attribute in the current element.
     *
     * @param attribute the new attribute
     *
     * @see #onStartElement(BasicElement)
     * @see Notifier#notifyAttribute(BasicAttribute)
     */
    void onAttribute(BasicAttribute attribute);

    /**
     * Handles a reference from the current element to another.
     *
     * @param reference the new reference
     *
     * @see #onStartElement(BasicElement)
     * @see Notifier#notifyReference(BasicReference)
     */
    void onReference(BasicReference reference);

    /**
     * Handles a set of characters.
     *
     * @param characters the new characters
     *
     * @see #onStartElement(BasicElement)
     * @see Notifier#notifyCharacters(String)
     */
    void onCharacters(String characters);

    /**
     * Handles the end of the current element.
     *
     * @see #onStartElement(BasicElement)
     * @see Notifier#notifyEndElement()
     */
    void onEndElement();

    /**
     * Handles the end of the current task.
     *
     * @see #onInitialize()
     * @see Notifier#notifyComplete()
     */
    void onComplete();
}
