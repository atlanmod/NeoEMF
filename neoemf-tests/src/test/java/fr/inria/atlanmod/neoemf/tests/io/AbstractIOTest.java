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

import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.io.reader.ReaderFactory;
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;
import fr.inria.atlanmod.neoemf.io.writer.WriterFactory;
import fr.inria.atlanmod.neoemf.tests.AbstractBackendTest;
import fr.inria.atlanmod.neoemf.util.emf.compare.LazyMatchEngineFactory;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.assertj.core.api.SoftAssertions;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.BeforeClass;

import java.io.IOException;
import java.net.URL;

public abstract class AbstractIOTest extends AbstractBackendTest {

    @BeforeClass
    public static void registerPackages() {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());

        IOResourceManager.registerPackage("java");
    }

    /**
     * Checks that the {@code expected} notifier is the same as the {@code actual}.
     *
     * @param actual   the notifier to check
     * @param expected the source notifier
     */
    protected void assertNotifierAreEqual(Notifier actual, Notifier expected) {
        Log.info("Comparing models...");

        IMatchEngine.Factory.Registry matchEngineRegistry = new MatchEngineFactoryRegistryImpl();
        matchEngineRegistry.add(new LazyMatchEngineFactory());

        IComparisonScope scope = new DefaultComparisonScope(expected, actual, null);

        Comparison comparison = EMFCompare.builder()
                .setMatchEngineFactoryRegistry(matchEngineRegistry)
                .build()
                .compare(scope);

        SoftAssertions softly = new SoftAssertions();
        {
            softly.assertThat(comparison.getConflicts()).isEmpty();
            softly.assertThat(comparison.getDifferences()).isEmpty();
        }
        softly.assertAll();
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
        return new ResourceSetImpl().getResource(uri, true).getContents().get(0);
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
        BackendFactoryRegistry.register(context().uriScheme(), context().factory());

        try (DataMapper mapper = context().createMapper(file())) {
            ReaderFactory.fromXmi(new URL(uri.toString()).openStream(), WriterFactory.toMapper(mapper));
        }

        return closeAtExit(context().loadResource(file())).getContents().get(0);
    }
}
