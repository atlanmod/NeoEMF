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
import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.io.util.ResourceManager;
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
import java.io.InputStream;
import java.net.URL;
import java.util.stream.Stream;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about the copy from a file to another.
 */
@ParametersAreNonnullByDefault
public class CopyTest extends AbstractFileBasedTest {

    @BeforeAll
    static void registerPackages() throws Exception {
        ResourceManager.registerAllPackages();
    }

    /**
     * Checks the copy from a XMI file to another.
     *
     * @param uri            the URI of the file to copy
     * @param useCompression {@code true} if the target file must be compressed
     */
    @ParameterizedTest(name = "[{index}] source = {0} ; useCompression = {1}")
    @ArgumentsSource(UriProvider.class)
    public void testCopy(URI uri, Boolean useCompression) throws Exception {
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

    /**
     * An {@link ArgumentsProvider} with all {@link URI}s managed by {@link ResourceManager}, associated with all {@link
     * Boolean} variants.
     */
    @ParametersAreNonnullByDefault
    public static class UriProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    ResourceManager.xmiStandard(),
                    ResourceManager.xmiWithId(),
                    ResourceManager.zxmiStandard(),
                    ResourceManager.zxmiWithId()
            ).flatMap(u -> Stream.of(false, true).map(b -> Arguments.of(u, b)));
        }
    }
}
