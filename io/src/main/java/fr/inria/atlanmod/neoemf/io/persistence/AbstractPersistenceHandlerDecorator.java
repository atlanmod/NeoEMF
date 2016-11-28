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

package fr.inria.atlanmod.neoemf.io.persistence;

import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
import fr.inria.atlanmod.neoemf.io.structure.Reference;

/**
 * An abstract implementation of an {@link PersistenceHandler} that delegates all methods to its embedded handler.
 */
public abstract class AbstractPersistenceHandlerDecorator implements PersistenceHandler {

    private final PersistenceHandler handler;

    public AbstractPersistenceHandlerDecorator(PersistenceHandler handler) {
        this.handler = handler;
    }

    @Override
    public void processStartDocument() {
        handler.processStartDocument();
    }

    @Override
    public void processStartElement(Classifier classifier) {
        handler.processStartElement(classifier);
    }

    @Override
    public void processAttribute(Attribute attribute) {
        handler.processAttribute(attribute);
    }

    @Override
    public void processReference(Reference reference) {
        handler.processReference(reference);
    }

    @Override
    public void processCharacters(String characters) {
        handler.processCharacters(characters);
    }

    @Override
    public void processEndElement() {
        handler.processEndElement();
    }

    @Override
    public void processEndDocument() {
        handler.processEndDocument();
    }
}
