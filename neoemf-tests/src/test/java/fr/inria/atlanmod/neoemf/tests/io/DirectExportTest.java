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

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.Migrator;
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;
import fr.inria.atlanmod.neoemf.io.util.IOTestUtils;
import fr.inria.atlanmod.neoemf.util.ModelComparisonUtils;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * A test case about the export from {@link Backend}s to files.
 */
public class DirectExportTest extends AbstractMigrationTest {

    /**
     * Checks the export from a {@link Backend} to a standard XMI.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testExportWithId() throws IOException {
        final File targetFile = new File(file() + ".xmi");
        Log.info("Exporting to {0}", targetFile);

        URI expectedUri = IOResourceManager.xmiWithId();

        try (DataMapper mapper = context().createMapper(file()); InputStream in = new URL(expectedUri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .toMapper(mapper)
                    .migrate();

            Migrator.fromMapper(mapper)
                    .toXmi(targetFile)
                    .migrate();
        }

        final EObject actual = IOTestUtils.loadWithEMF(URI.createFileURI(targetFile.toString()));
        EObject expected = IOTestUtils.loadWithEMF(expectedUri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);
    }

    /**
     * Checks the export from a {@link Backend} to a compressed XMI.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testExportWithIdCompressed() throws IOException {
        final File targetFile = new File(file() + ".zxmi");
        Log.info("Exporting to {0}", targetFile);

        URI expectedUri = IOResourceManager.xmiWithId();

        try (DataMapper mapper = context().createMapper(file()); InputStream in = new URL(expectedUri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .toMapper(mapper)
                    .migrate();

            Migrator.fromMapper(mapper)
                    .toZXmi(targetFile)
                    .migrate();
        }

        final EObject actual = IOTestUtils.loadWithEMF(URI.createFileURI(targetFile.toString()));
        EObject expected = IOTestUtils.loadWithEMF(expectedUri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);
    }
}
