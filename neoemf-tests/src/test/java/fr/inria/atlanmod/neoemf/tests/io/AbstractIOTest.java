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

import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.Migrator;
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;
import fr.inria.atlanmod.neoemf.tests.AbstractBackendTest;
import fr.inria.atlanmod.neoemf.util.compare.LazyMatchEngineFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.compare.utils.UseIdentifiers;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractIOTest extends AbstractBackendTest {

    @BeforeClass
    public static void registerPackages() {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

        IOResourceManager.registerPackage("java");
    }

    /**
     * Checks that the {@code actual} object is the same as the {@code expected}.
     *
     * @param actual   the object to check
     * @param expected the expected object
     */
    protected void assertNotifierAreEqual(EObject actual, EObject expected) {
        IMatchEngine.Factory factory = new LazyMatchEngineFactory(UseIdentifiers.NEVER);

        IMatchEngine.Factory.Registry registry = new MatchEngineFactoryRegistryImpl();
        registry.add(factory);

        IComparisonScope scope = new DefaultComparisonScope(expected, actual, null);

        Comparison comparison = EMFCompare.builder()
                .setMatchEngineFactoryRegistry(registry)
                .build()
                .compare(scope);

        assertThat(comparison.getDifferences()).hasSize(0);
    }

    /**
     * Loads the {@code uri} with standard EMF.
     *
     * @param uri the URI to load
     *
     * @return the the loaded content
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    protected EObject loadWithEMF(URI uri) throws IOException {
        Map<String, Object> options = new HashMap<>();
        if (uri.fileExtension().endsWith("zxmi")) {
            options.put(XMIResource.OPTION_ZIP, true);
        }

        Resource resource = new ResourceSetImpl().createResource(uri);
        resource.load(options);

        return resource.getContents().get(0);
    }

    /**
     * Loads the {@code uri} with NeoEMF according to the current {@link #context() Context}.
     *
     * @param uri the URI to load
     *
     * @return the loaded content
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    protected EObject loadWithNeoEMF(URI uri) throws IOException {
        try (DataMapper mapper = context().createMapper(file()); InputStream in = new URL(uri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .toMapper(mapper)
                    .migrate();
        }

        return closeAtExit(context().loadResource(file())).getContents().get(0);
    }
}
