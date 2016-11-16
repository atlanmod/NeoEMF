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

package fr.inria.atlanmod.neoemf.io.processor.impl;

import fr.inria.atlanmod.neoemf.io.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.processor.Processor;
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
import fr.inria.atlanmod.neoemf.io.structure.Reference;

/**
 * An abstract implementation of an {@link Processor} that delegates all methods to its embedded handler.
 */
public class AbstractProcessor implements Processor {

    private final Processor processor;

    protected AbstractProcessor(Processor processor) {
        this.processor = processor;
    }

    // Handler methods

    @Override
    public void processStartDocument() throws Exception {
        processor.processStartDocument();
    }

    @Override
    public void processStartElement(Classifier classifier) throws Exception {
        processor.processStartElement(classifier);
    }

    @Override
    public void processAttribute(Attribute attribute) throws Exception {
        processor.processAttribute(attribute);
    }

    @Override
    public void processReference(Reference reference) throws Exception {
        processor.processReference(reference);
    }

    @Override
    public void processEndElement() throws Exception {
        processor.processEndElement();
    }

    @Override
    public void processEndDocument() throws Exception {
        processor.processEndDocument();
    }

    @Override
    public void processCharacters(String characters) throws Exception {
        processor.processCharacters(characters);
    }

    // Notifier methods

    @Override
    public void addHandler(PersistenceHandler persistenceHandler) {
        processor.addHandler(persistenceHandler);
    }

    @Override
    public boolean hasHandler() {
        return processor.hasHandler();
    }

    @Override
    public Iterable<PersistenceHandler> getHandlers() {
        return processor.getHandlers();
    }

    @Override
    public void notifyStartDocument() throws Exception {
        processor.notifyStartDocument();
    }

    @Override
    public void notifyStartElement(Classifier classifier) throws Exception {
        processor.notifyStartElement(classifier);
    }

    @Override
    public void notifyAttribute(Attribute attribute) throws Exception {
        processor.notifyAttribute(attribute);
    }

    @Override
    public void notifyReference(Reference reference) throws Exception {
        processor.notifyReference(reference);
    }

    @Override
    public void notifyEndElement() throws Exception {
        processor.notifyEndElement();
    }

    @Override
    public void notifyEndDocument() throws Exception {
        processor.notifyEndDocument();
    }
}
