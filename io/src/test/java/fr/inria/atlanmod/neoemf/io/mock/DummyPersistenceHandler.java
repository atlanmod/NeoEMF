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

package fr.inria.atlanmod.neoemf.io.mock;

import fr.inria.atlanmod.neoemf.io.persistence.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
import fr.inria.atlanmod.neoemf.io.structure.Reference;

/**
 * A persistence handler that does nothing.
 * <p/>
 * Using for basic tests.
 */
public class DummyPersistenceHandler implements PersistenceHandler {

    @Override
    public void processStartDocument() throws Exception {
        // Do nothing
    }

    @Override
    public void processStartElement(Classifier classifier) throws Exception {
        // Do nothing
    }

    @Override
    public void processAttribute(Attribute attribute) throws Exception {
        // Do nothing
    }

    @Override
    public void processReference(Reference reference) throws Exception {
        // Do nothing
    }

    @Override
    public void processEndElement() throws Exception {
        // Do nothing
    }

    @Override
    public void processEndDocument() throws Exception {
        // Do nothing
    }
}
