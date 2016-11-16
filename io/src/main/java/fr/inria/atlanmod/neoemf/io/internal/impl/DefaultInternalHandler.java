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
import fr.inria.atlanmod.neoemf.io.impl.AbstractNotifier;
import fr.inria.atlanmod.neoemf.io.internal.InternalHandler;

/**
 * A simple {@link InternalHandler} that notifies registered {@link PersistenceHandler}.
 */
public class DefaultInternalHandler extends AbstractNotifier<PersistenceHandler> implements InternalHandler {

    @Override
    public void handleStartDocument() throws Exception {
        notifyStartDocument();
    }

    @Override
    public void handleStartElement(Classifier classifier) throws Exception {
        notifyStartElement(classifier);
    }

    @Override
    public void handleAttribute(Attribute attribute) throws Exception {
        notifyAttribute(attribute);
    }

    @Override
    public void handleReference(Reference reference) throws Exception {
        notifyReference(reference);
    }

    @Override
    public void handleEndElement() throws Exception {
        notifyEndElement();
    }

    @Override
    public void handleEndDocument() throws Exception {
        notifyEndDocument();
    }

    @Override
    public void handleCharacters(String characters) throws Exception {
        // Do nothing : Persistence handlers do not have to deal with characters
    }
}
