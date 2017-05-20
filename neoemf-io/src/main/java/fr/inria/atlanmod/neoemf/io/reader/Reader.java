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

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.Notifier;

import java.io.IOException;
import java.io.InputStream;

/**
 * An {@link Notifier} that reads data from a file.
 */
public interface Reader extends Notifier<Handler> {

    /**
     * Reads a stream and notifies registered {@link Handler}.
     *
     * @param stream the stream to read
     *
     * @throws IllegalArgumentException if this reader hasn't any handler
     * @throws IOException              if an error occurred during the I/O process
     */
    void read(InputStream stream) throws IOException;
}
