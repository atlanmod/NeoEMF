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

import fr.inria.atlanmod.neoemf.data.PersistenceBackend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The factory that creates instances of {@link Writer}s.
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
     * Creates a {@link PersistenceWriter} on the given {@code backend} with conflict resolution feature.
     *
     * @param backend the back-end where data must persist
     *
     * @return a new persistence writer
     */
    public static PersistenceWriter toAwareBackend(PersistenceBackend backend) {
        return new PersistenceAwareWriter(backend);
    }

    /**
     * Creates a {@link PersistenceWriter} on the given {@code backend} <b>without</b> conflict resolution feature.
     *
     * @param backend the back-end where data must persist
     *
     * @return a new persistence writer
     */
    public static PersistenceWriter toNaiveBackend(PersistenceBackend backend) {
        return new PersistenceNaiveWriter(backend);
    }

    /**
     * Creates a {@link StreamWriter} on the given {@code stream}.
     *
     * @param file the file where data must be written
     *
     * @return a new stream writer
     */
    public static StreamWriter toXmi(File file) throws IOException {
        return new XmiStAXCursorStreamWriter(new FileOutputStream(file));
    }

    /**
     * Creates a {@link StreamWriter} on the given {@code stream}.
     *
     * @param stream the stream where data must be written
     *
     * @return a new stream writer
     */
    public static StreamWriter toXmi(OutputStream stream) throws IOException {
        return new XmiStAXCursorStreamWriter(stream);
    }
}
