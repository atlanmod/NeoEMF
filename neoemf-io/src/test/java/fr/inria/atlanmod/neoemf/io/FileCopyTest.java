/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.io.provider.UriProvider;
import fr.inria.atlanmod.neoemf.io.util.ResourceManager;
import fr.inria.atlanmod.neoemf.util.ModelComparisonUtils;

import org.atlanmod.commons.AbstractFileBasedTest;
import org.atlanmod.commons.log.Log;
import org.atlanmod.commons.primitive.Strings;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about the copy from a file to another.
 */
@ParametersAreNonnullByDefault
class FileCopyTest extends AbstractFileBasedTest {

    @BeforeAll
    static void registerPackages() {
        ResourceManager.registerAllPackages();
    }

    /**
     * Checks the copy from a XMI file to another.
     *
     * @param uri            the URI of the file to copy
     * @param useCompression {@code true} if the target file must be compressed
     */
    @ParameterizedTest(name = "[{index}] source = {0} ; useCompression = {1}")
    @ArgumentsSource(UriProvider.AllWithBooleans.class)
    void testCopy(URI uri, Boolean useCompression) throws IOException {
        final File targetFile = new File(currentTempFile() + "." + (useCompression ? "z" : Strings.EMPTY) + "xmi");
        Log.info("Exporting to {0}", targetFile);

        try (InputStream in = new URL(uri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .toXmi(targetFile, useCompression)
                    .migrate();
        }

        EObject actual = ResourceManager.load(URI.createFileURI(targetFile.toString()));
        EObject expected = ResourceManager.load(uri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);

        actual.eResource().unload();
        expected.eResource().unload();
    }
}
