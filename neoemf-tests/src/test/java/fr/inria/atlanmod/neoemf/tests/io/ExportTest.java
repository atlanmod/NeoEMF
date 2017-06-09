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

import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.io.reader.ReaderFactory;
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;
import fr.inria.atlanmod.neoemf.io.writer.WriterFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * A test case about the export from {@link Backend}s.
 */
public class ExportTest extends AbstractIOTest {

    /**
     * Checks the copy from a {@link Backend} to another.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testCopyBackend() throws IOException {
        BackendFactoryRegistry.register(context().uriScheme(), context().factory());

        final File sourceBackend = file();
        final File targetBackend = Paths.get(file() + "-copy").toFile();

        try (DataMapper sourceMapper = context().createMapper(sourceBackend)) {
            ReaderFactory.fromXmi(new URL(IOResourceManager.xmiStandard().toString()).openStream(), WriterFactory.toMapper(sourceMapper));

            try (DataMapper targetMapper = context().createMapper(targetBackend)) {
                ReaderFactory.fromMapper(sourceMapper, WriterFactory.toMapper(targetMapper));
            }
        }

        final EObject actual = closeAtExit(context().loadResource(targetBackend)).getContents().get(0);

        // Comparing PersistentBackend
        EObject expected = closeAtExit(context().loadResource(sourceBackend)).getContents().get(0);
        assertNotifierAreEqual(actual, expected);

        // Comparing with EMF
        expected = loadWithEMF(IOResourceManager.xmiStandard());
        assertNotifierAreEqual(actual, expected);
    }

    /**
     * Checks the copy from a {@link Backend} to another.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    @Ignore("Incomplete implementation") // FIXME Some attributes cannot be written
    public void testCopyFile() throws IOException {
        BackendFactoryRegistry.register(context().uriScheme(), context().factory());

        final File targetFile = new File(file() + ".xmi");

        ReaderFactory.fromXmi(new URL(IOResourceManager.xmiStandard().toString()).openStream(), WriterFactory.toXmi(targetFile));

        final EObject actual = loadWithEMF(URI.createFileURI(targetFile.toString()));
        EObject expected = loadWithEMF(IOResourceManager.xmiStandard());

        assertNotifierAreEqual(actual, expected);
    }

    /**
     * Checks the copy from a {@link Backend} to another.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    @Ignore("Incomplete implementation") // FIXME Some elements are missing
    public void testExportToXmi() throws IOException {
        BackendFactoryRegistry.register(context().uriScheme(), context().factory());

        final File targetFile = new File(file() + ".xmi");

        try (DataMapper mapper = context().createMapper(file())) {
            ReaderFactory.fromXmi(new URL(IOResourceManager.xmiStandard().toString()).openStream(), WriterFactory.toMapper(mapper));
            ReaderFactory.fromMapper(mapper, WriterFactory.toXmi(targetFile));
        }

        final EObject actual = loadWithEMF(URI.createFileURI(targetFile.toString()));
        EObject expected = loadWithEMF(IOResourceManager.xmiStandard());

        assertNotifierAreEqual(actual, expected);
    }
}
