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

package fr.inria.atlanmod.neoemf.io.impl;

import fr.inria.atlanmod.neoemf.io.InternalHandler;

/**
 *
 */
public abstract class AbstractInternalNotifier extends AbstractNotifier<InternalHandler> {

    protected final void notifyStartElement(String prefix, String namespace, String localName) throws Exception {
        for (InternalHandler h : getHandlers()) {
            h.handleStartElement(prefix, namespace, localName, null);
        }
    }

    protected final void notifyAttribute(String namespace, String localName, String value) throws Exception {
        for (InternalHandler h : getHandlers()) {
            h.handleAttribute(namespace, localName, value);
        }
    }

    protected final void notifyReference(String namespace, String localName, String reference) throws Exception {
        for (InternalHandler h : getHandlers()) {
            h.handleReference(namespace, localName, reference);
        }
    }

    protected final void notifyEndElement() throws Exception {
        for (InternalHandler h : getHandlers()) {
            h.handleEndElement();
        }
    }
}
