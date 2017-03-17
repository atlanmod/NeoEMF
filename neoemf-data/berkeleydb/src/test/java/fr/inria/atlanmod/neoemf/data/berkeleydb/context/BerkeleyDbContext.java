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
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.option.BerkeleyDbOptions;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbURI;
import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptions;

import org.eclipse.emf.common.util.URI;

import java.io.File;

/**
 * A specific {@link Context} for the BerkeleyDB implementation.
 */
public abstract class BerkeleyDbContext implements Context {

    /**
     * Creates a new {@code BerkeleyDbContext} with a mapping with indices.
     *
     * @return a new context.
     */
    public static Context getWithIndices() {
        return new BerkeleyDbContext() {
            @Override
            public AbstractPersistenceOptions<?> optionsBuilder() {
                return BerkeleyDbOptions.newBuilder().withIndices();
            }
        };
    }

    /**
     * Creates a new {@code BerkeleyDbContext} with a mapping with arrays.
     *
     * @return a new context.
     */
    public static Context getWithArrays() {
        return new BerkeleyDbContext() {
            @Override
            public AbstractPersistenceOptions<?> optionsBuilder() {
                return BerkeleyDbOptions.newBuilder().withArrays();
            }
        };
    }

    /**
     * Creates a new {@code BerkeleyDbContext} with a mapping with lists.
     *
     * @return a new context.
     */
    public static Context getWithLists() {
        return new BerkeleyDbContext() {
            @Override
            public AbstractPersistenceOptions<?> optionsBuilder() {
                return BerkeleyDbOptions.newBuilder().withLists();
            }
        };
    }

    @Override
    public String name() {
        return "BerkeleyDB";
    }

    @Override
    public BackendFactory factory() {
        return BerkeleyDbBackendFactory.getInstance();
    }

    @Override
    public String uriScheme() {
        return BerkeleyDbURI.SCHEME;
    }

    @Override
    public URI createUri(URI uri) {
        return BerkeleyDbURI.createURI(uri);
    }

    @Override
    public URI createUri(File file) {
        return BerkeleyDbURI.createFileURI(file);
    }
}
