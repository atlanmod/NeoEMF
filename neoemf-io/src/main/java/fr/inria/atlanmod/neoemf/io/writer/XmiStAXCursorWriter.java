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
import fr.inria.atlanmod.neoemf.io.processor.Processor;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A {@link Writer} that uses a StAX implementation with cursors for writing XMI files.
 */
@Experimental
public class XmiStAXCursorWriter extends AbstractXmiWriter {

    @Override
    public Processor defaultProcessor() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void write(OutputStream stream) throws IOException {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
