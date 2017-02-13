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

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.io.processor.EcoreProcessor;
import fr.inria.atlanmod.neoemf.io.processor.PersistenceProcessor;
import fr.inria.atlanmod.neoemf.io.processor.Processor;
import fr.inria.atlanmod.neoemf.io.processor.XPathProcessor;
import fr.inria.atlanmod.neoemf.io.reader.XmiStAXCursorStreamReader;
import fr.inria.atlanmod.neoemf.io.writer.PersistenceWriter;

import java.io.IOException;
import java.io.InputStream;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Static methods to import data into NeoEMF.
 */
public final class Importer {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private Importer() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Imports a XMI file into persistence handlers.
     *
     * @param stream   the stream of XMI data
     * @param handlers the persistence handlers where to store the read data
     *
     * @throws IllegalArgumentException if there is no handler to notify
     * @throws IOException              if an error occurred during the import
     */
    public static void fromXmi(InputStream stream, PersistenceWriter... handlers) throws IOException {
        checkNotNull(handlers, "The handler must be defined");

        Processor processor = new PersistenceProcessor(handlers);

        // Custom options come here

        processor = new XPathProcessor(processor);
        processor = new EcoreProcessor(processor);

        new XmiStAXCursorStreamReader(processor).read(stream);
    }
}
