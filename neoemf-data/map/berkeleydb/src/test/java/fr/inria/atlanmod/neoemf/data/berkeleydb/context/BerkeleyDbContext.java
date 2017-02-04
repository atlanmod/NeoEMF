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

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbURI;
import fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteMapStore;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

/**
 * A specific {@link Context} for the BerkeleyDB implementation.
 */
public class BerkeleyDbContext implements Context {

    /**
     * The name of this context.
     */
    public static final String NAME = "BerkeleyDB";

    /**
     * Constructs a new {@code BerkeleyDbContext}.
     */
    protected BerkeleyDbContext() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class.
     */
    public static Context get() {
        return Holder.INSTANCE;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String uriScheme() {
        return BerkeleyDbURI.SCHEME;
    }

    @Override
    public URI createURI(URI uri) {
        return BerkeleyDbURI.createURI(uri);
    }

    @Override
    public URI createFileURI(File file) {
        return BerkeleyDbURI.createFileURI(file);
    }

    @Override
    public PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException {
        return new BerkeleyDbResourceBuilder(ePackage).persistent().file(file).build();
    }

    @Override
    public PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException {
        return new BerkeleyDbResourceBuilder(ePackage).file(file).build();
    }

    @Override
    public PersistenceBackendFactory persistenceBackendFactory() {
        return BerkeleyDbPersistenceBackendFactory.getInstance();
    }

    @Override
    public Class<? extends DirectWriteStore> directWriteClass() {
        return DirectWriteMapStore.class;
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static class Holder {

        /**
         * The instance of the outer class.
         */
        private static final Context INSTANCE = new BerkeleyDbContext();
    }
}
