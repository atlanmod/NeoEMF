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
import fr.inria.atlanmod.neoemf.io.reader.DefaultPersistenceReader;
import fr.inria.atlanmod.neoemf.io.reader.ReaderFactory;
import fr.inria.atlanmod.neoemf.io.writer.WriterFactory;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.eclipse.emf.ecore.EObject;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * A test case about the export from {@link PersistenceBackend}s.
 */
public class ExportTest extends AbstractIOTest {

    /**
     * Checks the copy from a {@link PersistenceBackend} to another.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testCopyBackend() throws IOException {
        PersistenceBackendFactoryRegistry.register(context().uriScheme(), context().persistenceBackendFactory());

        File sourceBackend = file();
        File targetBackend = Paths.get(file() + "-copy").toFile();

        try (PersistenceBackend backend = context().createBackend(sourceBackend)) {
            ReaderFactory.fromXmi(getXmiStandard(), WriterFactory.toNaiveBackend(backend));

            try (PersistenceBackend target = context().createBackend(targetBackend)) {
                new DefaultPersistenceReader(WriterFactory.toNaiveBackend(target)).read(backend);
            }
        }

        EObject sourceModel = context().loadResource(null, sourceBackend).getContents().get(0);
        EObject targetModel = context().loadResource(null, targetBackend).getContents().get(0);
        assertEqualEObject(targetModel, sourceModel);
    }

    /**
     * Checks the copy from a {@link PersistenceBackend} to another.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    @Ignore // FIXME Some attributes cannot be written
    public void testCopyFile() throws IOException {
        PersistenceBackendFactoryRegistry.register(context().uriScheme(), context().persistenceBackendFactory());

        File targetFile = new File(file() + ".xmi");

        NeoLogger.info("Writing to {0}", targetFile);

        ReaderFactory.fromXmi(getXmiStandard(), WriterFactory.toXmi(targetFile));

        EObject sourceModel = loadWithEMF(getXmiStandard());
        EObject targetModel = loadWithEMF(targetFile);
        assertEqualEObject(targetModel, sourceModel);
    }

    /**
     * Checks the copy from a {@link PersistenceBackend} to another.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    @Ignore // FIXME Some elements are missing
    public void testExportToXmi() throws IOException {
        PersistenceBackendFactoryRegistry.register(context().uriScheme(), context().persistenceBackendFactory());

        File targetFile = new File(file() + ".xmi");

        NeoLogger.info("Writing to {0}", targetFile);

        try (PersistenceBackend backend = context().createBackend(file())) {
            ReaderFactory.fromXmi(getXmiStandard(), WriterFactory.toNaiveBackend(backend));

            new DefaultPersistenceReader(WriterFactory.toXmi(targetFile)).read(backend);
        }

        EObject sourceModel = loadWithEMF(getXmiStandard());
        EObject targetModel = loadWithEMF(targetFile);
        assertEqualEObject(targetModel, sourceModel);
    }
}
