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

package fr.inria.atlanmod.neoemf.io.persistence;

import fr.inria.atlanmod.neoemf.io.AbstractInputNotifier;
import fr.inria.atlanmod.neoemf.io.processor.Processor;
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
import fr.inria.atlanmod.neoemf.io.structure.Reference;

/**
 * A {@link Processor} that notifies registered {@link PersistenceHandler} of events.
 */
public final class PersistenceNotifier extends AbstractInputNotifier<PersistenceHandler> implements Processor {

    /**
     * Constructs a new {@code PersistenceNotifier}.
     */
    public PersistenceNotifier() {
    }

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
}
