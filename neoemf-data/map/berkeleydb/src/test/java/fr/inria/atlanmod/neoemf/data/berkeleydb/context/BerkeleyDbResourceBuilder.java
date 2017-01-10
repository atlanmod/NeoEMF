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

package fr.inria.atlanmod.neoemf.data.berkeleydb.context;

import fr.inria.atlanmod.neoemf.context.AbstractResourceBuilder;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;

public class BerkeleyDbResourceBuilder extends AbstractResourceBuilder {

    public BerkeleyDbResourceBuilder(EPackage ePackage) {
        super(ePackage);
        initMapBuilder();
    }

    @Override
    protected void initBuilder() {
        super.initBuilder();
        initMapBuilder();
    }

    @Override
    public BerkeleyDbResourceBuilder uri(URI uri) {
        this.uri = BerkeleyDbURI.createURI(uri);
        return this;
    }

    @Override
    public BerkeleyDbResourceBuilder file(File file) {
        this.uri = BerkeleyDbURI.createFileURI(file);
        return this;
    }

    private void initMapBuilder() {
        if (!PersistenceBackendFactoryRegistry.isRegistered(BerkeleyDbURI.SCHEME)) {
            PersistenceBackendFactoryRegistry.register(BerkeleyDbURI.SCHEME,
                    BerkeleyDbPersistenceBackendFactory.getInstance());
        }
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BerkeleyDbURI.SCHEME,
                PersistentResourceFactory.getInstance());
    }
}
