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

import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.io.processor.DirectWriteProcessor;
import fr.inria.atlanmod.neoemf.io.processor.EcoreProcessor;
import fr.inria.atlanmod.neoemf.io.processor.Processor;
import fr.inria.atlanmod.neoemf.io.processor.XPathProcessor;
import fr.inria.atlanmod.neoemf.io.writer.Writer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * The factory that creates instances of {@link Reader}s.
 */
@ParametersAreNonnullByDefault
public class ReaderFactory {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private ReaderFactory() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Reads an XMI file into writers.
     *
     * @param file    the file to read
     * @param writers the writers where to store the read data
     *
     * @throws IllegalArgumentException if there is no handler to notify
     * @throws IOException              if an error occurred during the import
     */
    public static void fromXmi(File file, Writer... writers) throws IOException {
        fromXmi(new FileInputStream(file), writers);
    }

    /**
     * Reads an XMI file into writers.
     *
     * @param stream  the stream of XMI data
     * @param writers the writers where to store the read data
     *
     * @throws IllegalArgumentException if there is no handler to notify
     * @throws IOException              if an error occurred during the import
     */
    public static void fromXmi(InputStream stream, Writer... writers) throws IOException {
        checkNotNull(writers);

        Processor processor = new DirectWriteProcessor(writers);

        // Custom options come here

        processor = new XPathProcessor(processor);
        processor = new EcoreProcessor(processor);

        new XmiStAXCursorStreamReader(processor).read(stream);
    }

    /**
     * Reads a {@link DataMapper} file into writers.
     *
     * @param mapper  the mapper of XMI data
     * @param writers the writers where to store the read data
     *
     * @throws IllegalArgumentException if there is no handler to notify
     * @throws IOException              if an error occurred during the import
     */
    public static void fromMapper(DataMapper mapper, Writer... writers) throws IOException {
        checkNotNull(writers);

        Processor processor = new DirectWriteProcessor(writers);

        // Custom options come here

        new DefaultMapperReader(processor).read(mapper);
    }
}