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

package fr.inria.atlanmod.neoemf.data.blueprints.util;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;

import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;
import static fr.inria.atlanmod.neoemf.NeoAssertions.catchThrowable;

public class BlueprintsUriTest extends AllTest {

    private static final String SCHEME_INVALID = "invalid";

    private final PersistenceBackendFactory persistenceBackendFactory = BlueprintsPersistenceBackendFactory.getInstance();

    private File testFile;

    @Before
    public void setUp() {
        PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME, persistenceBackendFactory);
        testFile = tempFile("Blueprints");
    }

    @After
    public void tearDown() {
        PersistenceBackendFactoryRegistry.unregisterAll();
    }

    @Test
    public void testCreateNeoGraphURIFromStandardURIValidScheme() {
        URI validURI = URI.createURI(BlueprintsURI.SCHEME + ":/test");
        URI neoURI = BlueprintsURI.createURI(validURI);
        assertThat(neoURI).hasScheme(BlueprintsURI.SCHEME);
    }

    @Test
    public void testCreateNeoGraphURIFromFileURI() {
        URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
        URI neoURI = BlueprintsURI.createURI(fileURI);
        assertThat(neoURI).hasScheme(BlueprintsURI.SCHEME);
    }

    @Test
    public void testCreateNeoURIFromFile() {
        URI neoURI = BlueprintsURI.createFileURI(testFile);
        assertThat(neoURI).hasScheme(BlueprintsURI.SCHEME);
    }

    @Test
    public void testCreateNeoGraphURIFromStandardURIInvalidScheme() {
        URI invalidURI = URI.createURI(SCHEME_INVALID + ":/test");

        Throwable thrown = catchThrowable(() -> BlueprintsURI.createURI(invalidURI));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }
}
