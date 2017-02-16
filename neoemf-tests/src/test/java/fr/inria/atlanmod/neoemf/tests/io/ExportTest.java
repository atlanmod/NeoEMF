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
import fr.inria.atlanmod.neoemf.io.writer.WriterFactory;

import org.eclipse.emf.ecore.EObject;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
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

        File sourceFile = file();
        File targetFile = file().toPath().resolve("copy").toFile();

        try (PersistenceBackend backend = context().createBackend(sourceFile)) {
            IOFactory.fromXmi(new FileInputStream(getXmiStandard()), WriterFactory.newNaiveWriter(backend));

            try (PersistenceBackend target = context().createBackend(targetFile)) {
                new DefaultPersistenceReader(WriterFactory.newNaiveWriter(target)).read(backend);
            }
        }

        EObject sourceModel = context().loadResource(null, sourceFile).getContents().get(0);
        EObject targetModel = context().loadResource(null, targetFile).getContents().get(0);

        assertEqualEObject(targetModel, sourceModel);
    }
}
