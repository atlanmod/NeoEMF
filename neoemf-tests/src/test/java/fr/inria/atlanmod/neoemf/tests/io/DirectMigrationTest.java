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
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.Migrator;
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;
import fr.inria.atlanmod.neoemf.io.util.IOTestUtils;
import fr.inria.atlanmod.neoemf.tests.AllContextTest;
import fr.inria.atlanmod.neoemf.tests.context.ContextProvider;
import fr.inria.atlanmod.neoemf.util.ModelComparisonUtils;

import org.apache.logging.log4j.util.Strings;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case that checks the behavior of direct I/O methods, by using the `neoemf-io` module.
 */
@ParametersAreNonnullByDefault
public class DirectMigrationTest extends AllContextTest {

    /**
     * Registers all {@link org.eclipse.emf.ecore.EPackage}s associated with this test-case.
     */
    @BeforeAll
    public static void registerPackages() {
        IOResourceManager.registerAllPackages();
    }

    /**
     * Checks the import from a file to a {@link Backend}.
     */
    @ParameterizedTest(name = "[{index}] {0} <- {1}")
    @ArgumentsSource(ContextProvider.WithUris.class)
    public void testDirectImport(Context context, URI uri) throws IOException {
        final File sourceFile = file();
        Log.info("Importing from file... [{0}]", sourceFile);

        try (DataMapper mapper = context.createMapper(sourceFile); InputStream in = new URL(uri.toString()).openStream()) {
            Migrator.fromXmi(in).toMapper(mapper).migrate();
        }

        // Comparing with EMF
        final EObject actual = context.loadResource(sourceFile).getContents().get(0);
        EObject expected = IOTestUtils.loadWithEMF(uri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);

        actual.eResource().unload();
        expected.eResource().unload();
    }

    /**
     * Checks the export from a {@link Backend} to a file.
     */
    @ParameterizedTest(name = "[{index}] {0}: useCompression = {1}")
    @ArgumentsSource(ContextProvider.WithBooleans.class)
    public void testDirectExport(Context context, Boolean useCompression) throws IOException {
        final File targetFile = new File(file() + "." + (useCompression ? "z" : Strings.EMPTY) + "xmi");
        Log.info("Exporting to file... [{0}]", targetFile);

        URI expectedUri = IOResourceManager.xmiWithId();

        try (DataMapper mapper = context.createMapper(file()); InputStream in = new URL(expectedUri.toString()).openStream()) {
            Migrator.fromXmi(in).toMapper(mapper).migrate();

            Migrator.fromMapper(mapper)
                    .toXmi(targetFile, useCompression)
                    .migrate();
        }

        // Comparing with EMF
        final EObject actual = IOTestUtils.loadWithEMF(URI.createFileURI(targetFile.toString()));
        EObject expected = IOTestUtils.loadWithEMF(expectedUri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);

        actual.eResource().unload();
        expected.eResource().unload();
    }

    /**
     * Checks the copy from a {@link Backend} to another.
     */
    @Tag("slow")
    @ParameterizedTest(name = "[{index}] {0} -> {0}")
    @ArgumentsSource(ContextProvider.All.class)
    public void testDirectCopy(Context context) throws IOException {
        testDirectCrossCopy(context, context);
    }

    /**
     * Checks the copy from a {@link Backend} to another.
     */
    @Disabled("Very slow and expensive test")
    @ParameterizedTest(name = "[{index}] {0} -> {1}")
    @ArgumentsSource(ContextProvider.WithContexts.class)
    public void testDirectCrossCopy(Context sourceContext, Context targetContext) throws IOException {
        final File sourceFile = file();
        final File targetFile = Paths.get(file() + "-copy").toFile();

        URI expectedUri = IOResourceManager.xmiStandard();

        try (DataMapper sourceMapper = sourceContext.createMapper(sourceFile); InputStream in = new URL(expectedUri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .toMapper(sourceMapper)
                    .migrate();

            Log.info("Copying backends...");

            try (DataMapper targetMapper = targetContext.createMapper(targetFile)) {
                Migrator.fromMapper(sourceMapper)
                        .toMapper(targetMapper)
                        .migrate();
            }
        }

        final EObject actual = closeAtExit(targetContext.loadResource(targetFile)).getContents().get(0);

        // Comparing PersistentBackend
        EObject expected = closeAtExit(sourceContext.loadResource(sourceFile)).getContents().get(0);
        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);

        // Comparing with EMF
        expected = IOTestUtils.loadWithEMF(expectedUri);
        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);

        actual.eResource().unload();
        expected.eResource().unload();
    }
}
