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

import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawElement;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;
import fr.inria.atlanmod.neoemf.io.writer.PersistenceWriter;

/**
 * A persistence handler that does nothing.
 * <p>
 * Using for basic tests.
 */
public class DummyPersistenceWriter implements PersistenceWriter {

    @Override
    public void handleStartDocument() {
        // Do nothing
    }

    @Override
    public void handleStartElement(RawElement element) {
        // Do nothing
    }

    @Override
    public void handleAttribute(RawAttribute attribute) {
        // Do nothing
    }

    @Override
    public void handleReference(RawReference reference) {
        // Do nothing
    }

    @Override
    public void handleEndElement() {
        // Do nothing
    }

    @Override
    public void handleEndDocument() {
        // Do nothing
    }

    @Override
    public void handleCharacters(String characters) {
        // Do nothing
    }
}
