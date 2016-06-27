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
import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.Classifier;
import fr.inria.atlanmod.neoemf.io.beans.Reference;

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
    public void handleStartElement(Classifier classifier) throws Exception {
        handler.handleStartElement(classifier);
    }

    @Override
    public void handleAttribute(Attribute attribute) throws Exception {
        handler.handleAttribute(attribute);
    }

    @Override
    public void handleReference(Reference reference) throws Exception {
        handler.handleReference(reference);
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
