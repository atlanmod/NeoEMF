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

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.Context;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * A specific {@link Context} for the MapDB implementation.
 */
public class MapDbContext implements Context {

    /**
     * The name of this context.
     */
    public static final String NAME = "MapDb";

    /**
     * Constructs a new {@code MapDbContext}.
     */
    protected MapDbContext() {
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
        return MapDbURI.SCHEME;
    }

    @Override
    public URI createURI(URI uri) {
        return MapDbURI.createURI(uri);
    }

    @Override
    public URI createFileURI(File file) {
        return MapDbURI.createFileURI(file);
    }

    @Override
    public PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException {
        return new MapDbTestBuilder(ePackage).persistent().file(file).createResource();
    }

    @Override
    public PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException {
        return new MapDbTestBuilder(ePackage).file(file).createResource();
    }

    @Override
    public PersistentResource loadResource(EPackage ePackage, File file) throws IOException {
        return new MapDbTestBuilder(ePackage).file(file).loadResource();
    }

    @Override
    public PersistenceBackend createBackend(File file) throws IOException {
        return new MapDbTestBuilder(null).file(file).createBackend();
    }

    @Override
    public PersistenceBackendFactory persistenceBackendFactory() {
        return MapDbBackendFactory.getInstance();
    }

    @Override
    public Map<String, Object> defaultOptions() {
        return MapDbOptionsBuilder.noOption();
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static class Holder {

        /**
         * The instance of the outer class.
         */
        private static final Context INSTANCE = new MapDbContext();
    }
}
