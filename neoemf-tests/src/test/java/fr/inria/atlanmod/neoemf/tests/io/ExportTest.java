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
import fr.inria.atlanmod.neoemf.io.util.IOResources;
import fr.inria.atlanmod.neoemf.io.writer.WriterFactory;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

        File sourceBackend = file();
        File targetBackend = Paths.get(file() + "-copy").toFile();

        try (DataMapper sourceMapper = context().createMapper(sourceBackend)) {
            ReaderFactory.fromXmi(IOResources.xmiStandard(), WriterFactory.toMapper(sourceMapper));

            try (DataMapper targetMapper = context().createMapper(targetBackend)) {
                ReaderFactory.fromMapper(sourceMapper, WriterFactory.toMapper(targetMapper));
            }
        }

        // Comparing PersistentBackend
        Resource sourceResource = closeAtExit(context().loadResource(sourceBackend));
        Resource targetResource = closeAtExit(context().loadResource(targetBackend));

        EObject sourceModel = sourceResource.getContents().get(0);
        EObject targetModel = targetResource.getContents().get(0);

        assertEqualEObject(targetModel, sourceModel);

        // Comparing with EMF
        sourceModel = loadWithEMF(IOResources.xmiStandard());

        assertEqualEObject(targetModel, sourceModel);
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

        File targetFile = new File(file() + ".xmi");

        Log.info("Writing to {0}", targetFile);

        ReaderFactory.fromXmi(IOResources.xmiStandard(), WriterFactory.toXmi(targetFile));

        EObject sourceModel = loadWithEMF(IOResources.xmiStandard());
        EObject targetModel = loadWithEMF(new FileInputStream(targetFile));

        assertEqualEObject(targetModel, sourceModel);
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

        File targetFile = new File(file() + ".xmi");

        Log.info("Writing to {0}", targetFile);

        try (DataMapper mapper = context().createMapper(file())) {
            ReaderFactory.fromXmi(IOResources.xmiStandard(), WriterFactory.toMapper(mapper));
            ReaderFactory.fromMapper(mapper, WriterFactory.toXmi(targetFile));
        }

        EObject sourceModel = loadWithEMF(IOResources.xmiStandard());
        EObject targetModel = loadWithEMF(new FileInputStream(targetFile));

        assertEqualEObject(targetModel, sourceModel);
    }
}
