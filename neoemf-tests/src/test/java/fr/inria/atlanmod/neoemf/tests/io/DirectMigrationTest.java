/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.tests.io;

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.Migrator;
import fr.inria.atlanmod.neoemf.io.util.ResourceManager;
import fr.inria.atlanmod.neoemf.tests.AbstractResourceBasedTest;
import fr.inria.atlanmod.neoemf.tests.provider.ContextProvider;
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
@Disabled("See `neoemf-io` tests")
@ParametersAreNonnullByDefault
class DirectMigrationTest extends AbstractResourceBasedTest {

    /**
     * Registers all {@link org.eclipse.emf.ecore.EPackage}s associated with this test-case.
     */
    @BeforeAll
    static void registerPackages() {
        ResourceManager.registerAllPackages();
    }

    /**
     * Checks the import from a file to a {@link Backend}.
     */
    @Tag("slower")
    @ParameterizedTest(name = "[{index}] {0} <- {1}")
    @ArgumentsSource(ContextProvider.AllWithUris.class)
    void testDirectImport(Context context, URI uri) throws IOException {
        final File sourceFile = currentTempFile();
        Log.info("Importing from file... [{0}]", sourceFile);

        try (DataMapper mapper = context.createMapper(sourceFile); InputStream in = new URL(uri.toString()).openStream()) {
            Migrator.fromXmi(in).toMapper(mapper).migrate();
        }

        // Comparing with EMF
        EObject actual = context.loadResource(sourceFile).getContents().get(0);
        EObject expected = ResourceManager.load(uri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);

        actual.eResource().unload();
        expected.eResource().unload();
    }

    /**
     * Checks the export from a {@link Backend} to a file.
     */
    @Tag("slower")
    @ParameterizedTest(name = "[{index}] {0}: useCompression = {1}")
    @ArgumentsSource(ContextProvider.AllWithBooleans.class)
    void testDirectExport(Context context, Boolean useCompression) throws IOException {
        final File targetFile = new File(currentTempFile() + "." + (useCompression ? "z" : Strings.EMPTY) + "xmi");
        Log.info("Exporting to file... [{0}]", targetFile);

        URI expectedUri = ResourceManager.xmiWithId();

        try (DataMapper mapper = context.createMapper(currentTempFile()); InputStream in = new URL(expectedUri.toString()).openStream()) {
            Migrator.fromXmi(in).toMapper(mapper).migrate();

            Migrator.fromMapper(mapper)
                    .toXmi(targetFile, useCompression)
                    .migrate();
        }

        // Comparing with EMF
        EObject actual = ResourceManager.load(URI.createFileURI(targetFile.toString()));
        EObject expected = ResourceManager.load(expectedUri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);

        actual.eResource().unload();
        expected.eResource().unload();
    }

    /**
     * Checks the copy from a {@link Backend} to another.
     */
    @Tag("slower")
    @ParameterizedTest(name = "[{index}] {0} -> {0}")
    @ArgumentsSource(ContextProvider.All.class)
    void testDirectCopy(Context context) throws IOException {
        testDirectCrossCopy(context, context);
    }

    /**
     * Checks the copy from a {@link Backend} to another.
     */
    @Tag("slowest")
    @ParameterizedTest(name = "[{index}] {0} -> {1}")
    @ArgumentsSource(ContextProvider.AllWithContexts.class)
    void testDirectCrossCopy(Context sourceContext, Context targetContext) throws IOException {
        final File sourceFile = currentTempFile();
        final File targetFile = Paths.get(currentTempFile() + "-copy").toFile();

        URI expectedUri = ResourceManager.xmiStandard();

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

        EObject actual = targetContext.loadResource(targetFile).getContents().get(0);

        // Comparing PersistentBackend
        EObject expected = sourceContext.loadResource(sourceFile).getContents().get(0);
        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);

        // Comparing with EMF
        expected = ResourceManager.load(expectedUri);
        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);

        actual.eResource().unload();
        expected.eResource().unload();
    }
}
