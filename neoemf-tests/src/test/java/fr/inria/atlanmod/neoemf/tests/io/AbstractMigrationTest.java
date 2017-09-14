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
import fr.inria.atlanmod.neoemf.io.util.IOTestUtils;
import fr.inria.atlanmod.neoemf.tests.AbstractBackendTest;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class AbstractMigrationTest extends AbstractBackendTest {

    @BeforeClass
    public static void registerPackages() {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

        IOResourceManager.registerAllPackages();
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
        return IOTestUtils.loadWithEMF(uri);
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
