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

package fr.inria.atlanmod.neoemf.io.writer;

import fr.inria.atlanmod.neoemf.annotations.Experimental;
import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawElement;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link StreamWriter} that uses a StAX implementation with cursors for writing XMI files.
 */
@Experimental
@ParametersAreNonnullByDefault
public class XmiStAXCursorStreamWriter extends AbstractXmiStreamWriter {

    @Override
    public void onInitialize() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void onStartElement(RawElement element) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void onAttribute(RawAttribute attribute) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void onReference(RawReference reference) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void onCharacters(String characters) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void onEndElement() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void onComplete() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
