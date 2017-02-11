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

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import fr.inria.atlanmod.neoemf.AbstractTestBuilder;
import fr.inria.atlanmod.neoemf.TestBuilder;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.io.IOException;

/**
 * A specific {@link TestBuilder} for the BerkeleyDB implementation.
 */
public class BerkeleyDbTestBuilder extends AbstractTestBuilder<BerkeleyDbTestBuilder> {

    /**
     * Constructs a new {@code BerkeleyDbTestBuilder} with the given {@code ePackage}.
     *
     * @param ePackage the {@link EPackage} associated to the built {@link Resource}
     *
     * @see EPackage.Registry
     */
    public BerkeleyDbTestBuilder(EPackage ePackage) {
        super(ePackage);
    }

    @Override
    protected void initBuilder() {
        super.initBuilder();

        PersistenceBackendFactoryRegistry.register(BerkeleyDbURI.SCHEME, BerkeleyDbBackendFactory.getInstance());

        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BerkeleyDbURI.SCHEME, PersistentResourceFactory.getInstance());
    }

    @Override
    public BerkeleyDbTestBuilder uri(URI uri) {
        this.uri = BerkeleyDbURI.createURI(uri);
        return me();
    }

    @Override
    public BerkeleyDbTestBuilder file(File file) {
        this.uri = BerkeleyDbURI.createFileURI(file);
        return me();
    }

    @Override
    public PersistenceBackend createBackend() throws IOException {
        return BerkeleyDbBackendFactory.getInstance().createPersistentBackend(uri, resourceOptions);
    }
}
