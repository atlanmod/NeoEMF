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

import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The factory that creates {@link Writer} instances.
 */
@ParametersAreNonnullByDefault
public class WriterFactory {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private WriterFactory() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Creates a {@link MapperWriter} on the given {@code mapper}.
     *
     * @param mapper the mapper where to write data
     *
     * @return a new writer
     */
    public static Writer toMapper(DataMapper mapper) {
        return new DefaultMapperWriter(mapper);
    }

    /**
     * Creates a {@link StreamWriter} on the given {@code stream}.
     *
     * @param file the file where to write data
     *
     * @return a new writer
     *
     * @throws IOException if an I/O error occurs
     */
    public static Writer toXmi(File file) throws IOException {
        return toXmi(new FileOutputStream(file));
    }

    /**
     * Creates a {@link StreamWriter} on the given {@code stream}.
     *
     * @param stream the stream where to write data
     *
     * @return a new writer
     *
     * @throws IOException if an I/O error occurs
     */
    public static Writer toXmi(OutputStream stream) throws IOException {
        return new XmiStAXCursorStreamWriter(stream);
    }
}
