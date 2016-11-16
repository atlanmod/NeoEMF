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

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
import fr.inria.atlanmod.neoemf.io.structure.Reference;
import fr.inria.atlanmod.neoemf.io.impl.AbstractInputNotifier;
import fr.inria.atlanmod.neoemf.io.processor.Processor;

/**
 * A simple {@link Processor} that notifies registered {@link PersistenceHandler}.
 */
public final class PersistenceNotifier extends AbstractInputNotifier<PersistenceHandler> implements Processor {

    @Override
    public void processStartDocument() throws Exception {
        notifyStartDocument();
    }

    @Override
    public void processStartElement(Classifier classifier) throws Exception {
        notifyStartElement(classifier);
    }

    @Override
    public void processAttribute(Attribute attribute) throws Exception {
        notifyAttribute(attribute);
    }

    @Override
    public void processReference(Reference reference) throws Exception {
        notifyReference(reference);
    }

    @Override
    public void processEndElement() throws Exception {
        notifyEndElement();
    }

    @Override
    public void processEndDocument() throws Exception {
        notifyEndDocument();
    }

    @Override
    public void processCharacters(String characters) throws Exception {
        // Do nothing : Persistence handlers do not have to deal with characters
    }
}
