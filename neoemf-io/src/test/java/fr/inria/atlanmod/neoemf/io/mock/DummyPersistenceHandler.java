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

package fr.inria.atlanmod.neoemf.io.mock;

import fr.inria.atlanmod.neoemf.io.persistence.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawClassifier;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;

/**
 * A persistence handler that does nothing.
 * <p>
 * Using for basic tests.
 */
public class DummyPersistenceHandler implements PersistenceHandler {

    @Override
    public void processStartDocument() {
        // Do nothing
    }

    @Override
    public void processStartElement(RawClassifier classifier) {
        // Do nothing
    }

    @Override
    public void processAttribute(RawAttribute attribute) {
        // Do nothing
    }

    @Override
    public void processReference(RawReference reference) {
        // Do nothing
    }

    @Override
    public void processEndElement() {
        // Do nothing
    }

    @Override
    public void processEndDocument() {
        // Do nothing
    }

    @Override
    public void processCharacters(String characters) {
        // Do nothing
    }
}
