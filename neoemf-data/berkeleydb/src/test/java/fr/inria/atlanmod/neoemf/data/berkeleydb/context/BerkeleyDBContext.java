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

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDBPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.store.DirectWriteBerkeleyDBStore;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDBURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

public class BerkeleyDBContext implements Context {

    public static final String NAME = "BerkeleyDB";

    protected BerkeleyDBContext() {
    }

    public static Context get() {
        return Holder.INSTANCE;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String uriScheme() {
        return BerkeleyDBURI.SCHEME;
    }

    @Override
    public PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException {
        return new BerkeleyDBResourceBuilder(ePackage).persistent().file(file).build();
    }

    @Override
    public PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException {
        return new BerkeleyDBResourceBuilder(ePackage).file(file).build();
    }

    @Override
    public PersistenceBackendFactory persistenceBackendFactory() {
        return BerkeleyDBPersistenceBackendFactory.getInstance();
    }

    @Override
    public URI createURI(URI uri) {
        return BerkeleyDBURI.createURI(uri);
    }

    @Override
    public URI createFileURI(File file) {
        return BerkeleyDBURI.createFileURI(file);
    }

    @Override
    public Class<?> directWriteClass() {
        return DirectWriteBerkeleyDBStore.class;
    }

    private static class Holder {
        private static final Context INSTANCE = new BerkeleyDBContext();
    }
}
