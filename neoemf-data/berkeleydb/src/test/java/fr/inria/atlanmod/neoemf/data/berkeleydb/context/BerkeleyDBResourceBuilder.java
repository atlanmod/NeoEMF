/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.berkeleydb.context;

import fr.inria.atlanmod.neoemf.context.AbstractResourceBuilder;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDBPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDBURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;

public class BerkeleyDBResourceBuilder extends AbstractResourceBuilder {

    public BerkeleyDBResourceBuilder(EPackage ePackage) {
        super(ePackage);
        initMapBuilder();
    }

    @Override
    protected void initBuilder() {
        super.initBuilder();
        initMapBuilder();
    }

    @Override
    public BerkeleyDBResourceBuilder uri(URI uri) {
        this.uri = BerkeleyDBURI.createURI(uri);
        return this;
    }

    @Override
    public BerkeleyDBResourceBuilder file(File file) {
        this.uri = BerkeleyDBURI.createFileURI(file);
        return this;
    }

    private void initMapBuilder() {
        if (!PersistenceBackendFactoryRegistry.isRegistered(BerkeleyDBURI.SCHEME)) {
            PersistenceBackendFactoryRegistry.register(BerkeleyDBURI.SCHEME,
                    BerkeleyDBPersistenceBackendFactory.getInstance());
        }
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BerkeleyDBURI.SCHEME,
                PersistentResourceFactory.getInstance());
    }
}
