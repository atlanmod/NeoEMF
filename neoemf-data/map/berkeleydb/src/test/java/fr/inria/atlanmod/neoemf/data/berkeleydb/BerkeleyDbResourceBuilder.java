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

import fr.inria.atlanmod.neoemf.AbstractResourceBuilder;
import fr.inria.atlanmod.neoemf.ResourceBuilder;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;

/**
 * A specific {@link ResourceBuilder} for the BerkeleyDB implementation.
 */
public class BerkeleyDbResourceBuilder extends AbstractResourceBuilder<BerkeleyDbResourceBuilder> {

    /**
     * Constructs a new {@code BerkeleyDbResourceBuilder} with the given {@code ePackage}.
     *
     * @param ePackage the {@link EPackage} associated to the built {@link Resource}
     *
     * @see EPackage.Registry
     */
    public BerkeleyDbResourceBuilder(EPackage ePackage) {
        super(ePackage);
    }

    @Override
    protected void initBuilder() {
        super.initBuilder();
        if (!PersistenceBackendFactoryRegistry.isRegistered(BerkeleyDbURI.SCHEME)) {
            PersistenceBackendFactoryRegistry.register(BerkeleyDbURI.SCHEME,
                    BerkeleyDbBackendFactory.getInstance());
        }
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BerkeleyDbURI.SCHEME, PersistentResourceFactory.getInstance());
    }

    @Override
    public BerkeleyDbResourceBuilder uri(URI uri) {
        this.uri = BerkeleyDbURI.createURI(uri);
        return me();
    }

    @Override
    public BerkeleyDbResourceBuilder file(File file) {
        this.uri = BerkeleyDbURI.createFileURI(file);
        return me();
    }
}
