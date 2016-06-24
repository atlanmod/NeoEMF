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

import fr.inria.atlanmod.neoemf.io.PersistenceHandler;

/**
 *
 */
public abstract class AbstractDelegatedPersistenceHandler implements PersistenceHandler {

    private final PersistenceHandler handler;

    public AbstractDelegatedPersistenceHandler(PersistenceHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handleStartDocument() throws Exception {
        handler.handleStartDocument();
    }

    @Override
    public void handleStartElement(String nsUri, String name, String id) throws Exception {
        handler.handleStartElement(nsUri, name, id);
    }

    @Override
    public void handleMetaClass(String nsUri, String name) throws Exception {
        handler.handleMetaClass(nsUri, name);
    }

    @Override
    public void handleAttribute(String nsUri, String name, int index, String value) throws Exception {
        handler.handleAttribute(nsUri, name, index, value);
    }

    @Override
    public void handleReference(String nsUri, String name, int index, String idReference) throws Exception {
        handler.handleReference(nsUri, name, index, idReference);
    }

    @Override
    public void handleEndElement() throws Exception {
        handler.handleEndElement();
    }

    @Override
    public void handleEndDocument() throws Exception {
        handler.handleEndDocument();
    }
}
