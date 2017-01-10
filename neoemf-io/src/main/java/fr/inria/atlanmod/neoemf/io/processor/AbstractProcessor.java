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
        notifyStartDocument();
    }

    @Override
    public void processStartElement(Classifier classifier) {
        notifyStartElement(classifier);
    }

    @Override
    public void processAttribute(Attribute attribute) {
        notifyAttribute(attribute);
    }

    @Override
    public void processReference(Reference reference) {
        notifyReference(reference);
    }

    @Override
    public void processEndElement() {
        notifyEndElement();
    }

    @Override
    public void processEndDocument() {
        notifyEndDocument();
    }

    @Override
    public void processCharacters(String characters) {
        notifyCharacters(characters);
    }

    // Notifier methods

    @Override
    public final void addHandler(PersistenceHandler persistenceHandler) {
        processor.addHandler(persistenceHandler);
    }

    @Override
    public final boolean hasHandler() {
        return processor.hasHandler();
    }

    @Override
    public final Iterable<PersistenceHandler> getHandlers() {
        return processor.getHandlers();
    }

    @Override
    public final void notifyStartDocument() {
        processor.processStartDocument();
    }

    @Override
    public final void notifyStartElement(Classifier classifier) {
        processor.processStartElement(classifier);
    }

    @Override
    public final void notifyAttribute(Attribute attribute) {
        processor.processAttribute(attribute);
    }

    @Override
    public final void notifyReference(Reference reference) {
        processor.processReference(reference);
    }

    @Override
    public final void notifyCharacters(String characters) {
        processor.processCharacters(characters);
    }

    @Override
    public final void notifyEndElement() {
        processor.processEndElement();
    }

    @Override
    public final void notifyEndDocument() {
        processor.processEndDocument();
    }
}
