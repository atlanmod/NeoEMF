/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.graph.blueprints.util;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class NeoBlueprintsURITest extends AllTest {

    private static final String TEST_FILENAME = "neoGraphURITestFile";

    private static final String INVALID = "invalid";

    private final PersistenceBackendFactory persistenceBackendFactory = BlueprintsPersistenceBackendFactory.getInstance();

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private File testFile;

    @Before
    public void setUp() {
        PersistenceBackendFactoryRegistry.unregisterAll();
        PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.SCHEME, persistenceBackendFactory);
        testFile = temporaryFolder.getRoot().toPath().resolve(TEST_FILENAME + new Date().getTime()).toFile();
    }

    @After
    public void tearDown() {
        temporaryFolder.delete();

        if (temporaryFolder.getRoot().exists()) {
            try {
                FileUtils.forceDeleteOnExit(temporaryFolder.getRoot());
            }
            catch (IOException e) {
                NeoLogger.warn(e);
            }
        }

        testFile = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoGraphURIFromStandardURIInvalidScheme() {
        URI invalidURI = URI.createURI(INVALID + ":/test");
        NeoBlueprintsURI.createURI(invalidURI);
    }

    @Test
    public void testCreateNeoGraphURIFromStandardURIValidScheme() {
        URI validURI = URI.createURI(NeoBlueprintsURI.SCHEME + ":/test");
        URI neoURI = NeoBlueprintsURI.createURI(validURI);
        assertThat(neoURI.scheme()).isEqualTo(NeoBlueprintsURI.SCHEME);
    }

    @Test
    public void testCreateNeoGraphURIFromFileURI() {
        URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
        URI neoURI = NeoBlueprintsURI.createURI(fileURI);
        assertThat(neoURI.scheme()).isEqualTo(NeoBlueprintsURI.SCHEME);
    }

    @Test
    public void testCreateNeoURIFromFile() {
        URI neoURI = NeoBlueprintsURI.createFileURI(testFile);
        assertThat(neoURI.scheme()).isEqualTo(NeoBlueprintsURI.SCHEME);
    }
}
