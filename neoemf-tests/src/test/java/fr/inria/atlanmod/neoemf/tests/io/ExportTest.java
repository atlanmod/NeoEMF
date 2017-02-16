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

package fr.inria.atlanmod.neoemf.tests.io;

import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.io.IOFactory;
import fr.inria.atlanmod.neoemf.io.reader.DefaultPersistenceReader;
import fr.inria.atlanmod.neoemf.io.writer.PersistenceWriter;
import fr.inria.atlanmod.neoemf.io.writer.WriterFactory;

import org.junit.Ignore;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * A test case about the export from {@link PersistenceBackend}s.
 */
@Ignore
public class ExportTest extends AbstractIOTest {

    /**
     * Checks the copy from a {@link PersistenceBackend} to another.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testCopy() throws IOException {
        PersistenceBackendFactoryRegistry.register(context().uriScheme(), context().persistenceBackendFactory());

        try (PersistenceBackend backend = context().createBackend(file())) {
            PersistenceWriter handler = WriterFactory.newNaiveWriter(backend);
            IOFactory.fromXmi(new FileInputStream(getXmiStandard()), handler);

            try (PersistenceBackend target = context.createBackend(file().toPath().resolve("tmp").toFile())) {
                new DefaultPersistenceReader(WriterFactory.newNaiveWriter(target)).read(backend);
            }
        }
    }
}
