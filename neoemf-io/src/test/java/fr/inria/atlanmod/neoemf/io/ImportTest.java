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
import fr.inria.atlanmod.neoemf.bind.Bindings;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.im.DefaultInMemoryBackend;
import fr.inria.atlanmod.neoemf.io.provider.UriProvider;
import fr.inria.atlanmod.neoemf.io.util.ResourceManager;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.util.ModelComparisonUtils;

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

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about the import of a model to a {@link Backend}.
 */
@ParametersAreNonnullByDefault
class ImportTest extends AbstractFileBasedTest {

    @BeforeAll
    static void registerPackages() {
        ResourceManager.registerAllPackages();
    }

    /**
     * Creates a {@link PersistentResource} on the given {@code backend}.
     *
     * @param uri     the URI of the resource
     * @param backend the backend of the resource
     *
     * @return a new {@link PersistentResource}
     */
    @Nonnull
    private static PersistentResource createMockResource(URI uri, Backend backend) throws IOException {
        BackendFactoryRegistry.getInstance().register(Bindings.schemeOf(MockBackendFactory.MockUri.class), new MockBackendFactory(backend));

        PersistentResource resource = PersistentResourceFactory.getInstance().createResource(MockBackendFactory.MockUri.builder().fromUri(uri));
        resource.save(MockBackendFactory.MockConfig.newConfig());
        return resource;
    }

    /**
     * Checks the import from a file to a {@link Backend}.
     */
    @Tag("slow")
    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(UriProvider.All.class)
    void testDirectImport(URI uri) throws IOException {
        final File sourceFile = currentTempFile();
        Log.info("Importing from file... [{0}]", sourceFile);

        try (Backend backend = new DefaultInMemoryBackend(); InputStream in = new URL(uri.toString()).openStream()) {
            Migrator.fromXmi(in).toMapper(backend).migrate();

            EObject actual = createMockResource(uri, backend).getContents().get(0);
            EObject expected = ResourceManager.load(uri);

            // Comparing with EMF
            ModelComparisonUtils.assertEObjectAreEqual(actual, expected);

            expected.eResource().unload();
        }
    }
}
