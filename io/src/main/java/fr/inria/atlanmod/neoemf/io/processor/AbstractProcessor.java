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

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.neoemf.io.persistence.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
import fr.inria.atlanmod.neoemf.io.structure.Reference;

/**
 * An abstract implementation of an {@link Processor} that delegates all methods to its embedded processor.
 */
public class AbstractProcessor implements Processor {

    private final Processor processor;

    protected AbstractProcessor(Processor processor) {
        this.processor = processor;
    }

    // Handler methods

    @Override
    public void processStartDocument() {
        processor.processStartDocument();
    }

    @Override
    public void processStartElement(Classifier classifier) {
        processor.processStartElement(classifier);
    }

    @Override
    public void processAttribute(Attribute attribute) {
        processor.processAttribute(attribute);
    }

    @Override
    public void processReference(Reference reference) {
        processor.processReference(reference);
    }

    @Override
    public void processEndElement() {
        processor.processEndElement();
    }

    @Override
    public void processEndDocument() {
        processor.processEndDocument();
    }

    @Override
    public void processCharacters(String characters) {
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
    public void notifyStartDocument() {
        processor.notifyStartDocument();
    }

    @Override
    public void notifyStartElement(Classifier classifier) {
        processor.notifyStartElement(classifier);
    }

    @Override
    public void notifyAttribute(Attribute attribute) {
        processor.notifyAttribute(attribute);
    }

    @Override
    public void notifyReference(Reference reference) {
        processor.notifyReference(reference);
    }

    @Override
    public void notifyEndElement() {
        processor.notifyEndElement();
    }

    @Override
    public void notifyEndDocument() {
        processor.notifyEndDocument();
    }
}
