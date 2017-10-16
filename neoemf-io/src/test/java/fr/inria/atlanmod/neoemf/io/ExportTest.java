/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.commons.AbstractFileBasedTest;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.im.DefaultInMemoryBackend;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.provider.UriProvider;
import fr.inria.atlanmod.neoemf.io.util.ResourceManager;
import fr.inria.atlanmod.neoemf.util.ModelComparisonUtils;

import org.apache.logging.log4j.util.Strings;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about the export of a model from a {@link Backend}.
 */
@ParametersAreNonnullByDefault
class ExportTest extends AbstractFileBasedTest {

    @BeforeAll
    static void registerPackages() {
        ResourceManager.registerAllPackages();
    }

    /**
     * Checks the export from a {@link Backend} to a file.
     */
    @Tag("slow")
    @ParameterizedTest(name = "[{index}] source = {0}: useCompression = {1}")
    @ArgumentsSource(UriProvider.UncompressedWithBooleans.class)
    void testDirectExport(URI uri, Boolean useCompression) throws IOException {
        final File targetFile = new File(currentTempFile() + "." + (useCompression ? "z" : Strings.EMPTY) + "xmi");
        Log.info("Exporting to file... [{0}]", targetFile);

        try (DataMapper mapper = new DefaultInMemoryBackend(); InputStream in = new URL(uri.toString()).openStream()) {
            Migrator.fromXmi(in).toMapper(mapper).migrate();

            Migrator.fromMapper(mapper)
                    .toXmi(targetFile, useCompression)
                    .migrate();
        }

        // Comparing with EMF
        EObject actual = ResourceManager.load(URI.createFileURI(targetFile.toString()));
        EObject expected = ResourceManager.load(uri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);

        actual.eResource().unload();
        expected.eResource().unload();
    }
}
