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

package fr.inria.atlanmod.neoemf.io.internal.impl;

import fr.inria.atlanmod.neoemf.io.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.Classifier;
import fr.inria.atlanmod.neoemf.io.beans.Reference;
import fr.inria.atlanmod.neoemf.io.internal.InternalHandler;

/**
 * An abstract implementation of an {@link InternalHandler} that delegates all methods to its embedded handler.
 */
public class AbstractDelegatedInternalHandler implements InternalHandler {

    private final InternalHandler internalHandler;

    protected AbstractDelegatedInternalHandler(InternalHandler internalHandler) {
        this.internalHandler = internalHandler;
    }

    // Handler methods

    @Override
    public void handleStartDocument() throws Exception {
        internalHandler.handleStartDocument();
    }

    @Override
    public void handleStartElement(Classifier classifier) throws Exception {
        internalHandler.handleStartElement(classifier);
    }

    @Override
    public void handleAttribute(Attribute attribute) throws Exception {
        internalHandler.handleAttribute(attribute);
    }

    @Override
    public void handleReference(Reference reference) throws Exception {
        internalHandler.handleReference(reference);
    }

    @Override
    public void handleEndElement() throws Exception {
        internalHandler.handleEndElement();
    }

    @Override
    public void handleEndDocument() throws Exception {
        internalHandler.handleEndDocument();
    }

    @Override
    public void handleCharacters(String characters) throws Exception {
        internalHandler.handleCharacters(characters);
    }

    // Notifier methods

    @Override
    public void addHandler(PersistenceHandler persistenceHandler) {
        internalHandler.addHandler(persistenceHandler);
    }

    @Override
    public boolean hasHandler() {
        return internalHandler.hasHandler();
    }

    @Override
    public Iterable<PersistenceHandler> getHandlers() {
        return internalHandler.getHandlers();
    }

    @Override
    public void notifyStartDocument() throws Exception {
        internalHandler.notifyStartDocument();
    }

    @Override
    public void notifyStartElement(Classifier classifier) throws Exception {
        internalHandler.notifyStartElement(classifier);
    }

    @Override
    public void notifyAttribute(Attribute attribute) throws Exception {
        internalHandler.notifyAttribute(attribute);
    }

    @Override
    public void notifyReference(Reference reference) throws Exception {
        internalHandler.notifyReference(reference);
    }

    @Override
    public void notifyEndElement() throws Exception {
        internalHandler.notifyEndElement();
    }

    @Override
    public void notifyEndDocument() throws Exception {
        internalHandler.notifyEndDocument();
    }
}
