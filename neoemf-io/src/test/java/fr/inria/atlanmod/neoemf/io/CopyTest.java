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

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.commons.AbstractTest;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;
import fr.inria.atlanmod.neoemf.io.util.IOTestUtils;
import fr.inria.atlanmod.neoemf.util.ModelComparisonUtils;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.stream.Stream;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about the copy from a file to another.
 */
@ParametersAreNonnullByDefault
public class CopyTest extends AbstractTest {

    @BeforeAll
    public static void registerPackages() {
        IOResourceManager.registerAllPackages();
    }

    /**
     * Checks the copy from a XMI file to another.
     *
     * @param uri            the URI of the file to copy
     * @param useCompression {@code true} if the target file must be compressed
     */
    @ParameterizedTest(name = "[{index}] source = {0} ; useCompression = {1}")
    @ArgumentsSource(UriProvider.class)
    public void testCopy(URI uri, Boolean useCompression) throws IOException {
        final File targetFile = new File(newFile("io") + "." + (useCompression ? "z" : Strings.EMPTY) + "xmi");
        Log.info("Exporting to {0}", targetFile);

        try (InputStream in = new URL(uri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .toXmi(targetFile, useCompression)
                    .migrate();
        }

        final EObject actual = IOTestUtils.loadWithEMF(URI.createFileURI(targetFile.toString()));
        EObject expected = IOTestUtils.loadWithEMF(uri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);

        actual.eResource().unload();
        expected.eResource().unload();
    }

    /**
     * An {@link ArgumentsProvider} with all {@link URI}s managed by {@link IOResourceManager}, associated with all
     * {@link Boolean} variants.
     */
    @ParametersAreNonnullByDefault
    public static class UriProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    IOResourceManager.xmiStandard(),
                    IOResourceManager.xmiWithId(),
                    IOResourceManager.zxmiStandard(),
                    IOResourceManager.zxmiWithId()
            ).flatMap(u -> Stream.of(false, true).map(b -> Arguments.of(u, b)));
        }
    }
}
