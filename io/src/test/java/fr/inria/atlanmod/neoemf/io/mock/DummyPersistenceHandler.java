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

package fr.inria.atlanmod.neoemf.io.mock;

import fr.inria.atlanmod.neoemf.io.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.ClassifierElement;
import fr.inria.atlanmod.neoemf.io.beans.Reference;

/**
 * A persistence handler that does nothing.
 * <p/>
 * Using for basic tests.
 */
public class DummyPersistenceHandler implements PersistenceHandler {

    @Override
    public void handleStartDocument() throws Exception {
        // Do nothing
    }

    @Override
    public void handleStartElement(ClassifierElement element) throws Exception {
        // Do nothing
    }

    @Override
    public void handleAttribute(Attribute attribute) throws Exception {
        // Do nothing
    }

    @Override
    public void handleReference(Reference reference) throws Exception {
        // Do nothing
    }

    @Override
    public void handleEndElement() throws Exception {
        // Do nothing
    }

    @Override
    public void handleEndDocument() throws Exception {
        // Do nothing
    }
}
