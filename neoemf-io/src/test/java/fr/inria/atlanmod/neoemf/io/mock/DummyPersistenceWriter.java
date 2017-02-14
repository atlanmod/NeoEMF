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

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link PersistenceWriter} that does nothing.
 */
@ParametersAreNonnullByDefault
public class DummyPersistenceWriter implements PersistenceWriter {

    @Override
    public void onInitialize() {
        // Do nothing
    }

    @Override
    public void onStartElement(RawElement element) {
        // Do nothing
    }

    @Override
    public void onAttribute(RawAttribute attribute) {
        // Do nothing
    }

    @Override
    public void onReference(RawReference reference) {
        // Do nothing
    }

    @Override
    public void onCharacters(String characters) {
        // Do nothing
    }

    @Override
    public void onEndElement() {
        // Do nothing
    }

    @Override
    public void onComplete() {
        // Do nothing
    }
}
