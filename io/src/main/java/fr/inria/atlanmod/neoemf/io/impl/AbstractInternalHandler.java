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
public abstract class AbstractInternalHandler extends AbstractPersistenceNotifier implements InternalHandler {

    @Override
    public void handleStartDocument() throws Exception {
        notifyStartDocument();
    }

    @Override
    public void handleStartElement(String namespace, String localName, String reference) throws Exception {
        notifyStartElement(namespace, localName, reference);
    }

    @Override
    public void handleAttribute(String namespace, String localName, String value) throws Exception {
        notifyAttribute(namespace, localName, value);
    }

    @Override
    public void handleReference(String namespace, String localName, String reference) throws Exception {
        notifyReference(namespace, localName, reference);
    }

    @Override
    public void handleEndElement() throws Exception {
        notifyEndElement();
    }

    @Override
    public void handleEndDocument() throws Exception {
        notifyEndDocument();
    }
}
