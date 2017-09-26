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

import fr.inria.atlanmod.neoemf.context.AbstractContext;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.option.BerkeleyDbOptions;
import fr.inria.atlanmod.neoemf.option.PersistenceOptions;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link Context} for the BerkeleyDB implementation.
 */
@ParametersAreNonnullByDefault
public abstract class BerkeleyDbContext extends AbstractContext {

    /**
     * Creates a new {@code BerkeleyDbContext} with a mapping with indices.
     *
     * @return a new context.
     */
    @Nonnull
    public static Context getWithIndices() {
        return new BerkeleyDbContext() {
            @Nonnull
            @Override
            public String name() {
                return super.name() + "-Indices";
            }

            @Nonnull
            @Override
            public PersistenceOptions optionsBuilder() {
                return BerkeleyDbOptions.builder().withIndices();
            }
        };
    }

    /**
     * Creates a new {@code BerkeleyDbContext} with a mapping with arrays.
     *
     * @return a new context.
     */
    @Nonnull
    public static Context getWithArrays() {
        return new BerkeleyDbContext() {
            @Nonnull
            @Override
            public String name() {
                return super.name() + "-Arrays";
            }

            @Nonnull
            @Override
            public PersistenceOptions optionsBuilder() {
                return BerkeleyDbOptions.builder().withArrays();
            }
        };
    }

    /**
     * Creates a new {@code BerkeleyDbContext} with a mapping with lists.
     *
     * @return a new context.
     */
    @Nonnull
    public static Context getWithLists() {
        return new BerkeleyDbContext() {
            @Nonnull
            @Override
            public String name() {
                return super.name() + "-Lists";
            }

            @Nonnull
            @Override
            public PersistenceOptions optionsBuilder() {
                return BerkeleyDbOptions.builder().withLists();
            }
        };
    }

    @Nonnull
    @Override
    public String name() {
        return "BerkeleyDB";
    }

    @Nonnull
    @Override
    public BackendFactory factory() {
        return BerkeleyDbBackendFactory.getInstance();
    }
}
