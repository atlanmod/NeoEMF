/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.berkeleydb.config.BerkeleyDbConfig;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Adapter} on top of a {@link fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackend}.
 */
@ParametersAreNonnullByDefault
public abstract class BerkeleyDbAdapter extends AbstractPersistentAdapter {

    /**
     * A {@link BerkeleyDbAdapter} with a mapping with indices.
     */
    @AdapterName("berkeleydb-i")
    public static final class WithIndices extends BerkeleyDbAdapter {

        @Nonnull
        @Override
        protected ImmutableConfig createConfig() {
            return new BerkeleyDbConfig().withIndices();
        }
    }

    /**
     * A {@link BerkeleyDbAdapter} with a mapping with arrays.
     */
    @AdapterName("berkeleydb-a")
    public static final class WithArrays extends BerkeleyDbAdapter {

        @Nonnull
        @Override
        protected ImmutableConfig createConfig() {
            return new BerkeleyDbConfig().withArrays();
        }
    }

    /**
     * A {@link BerkeleyDbAdapter} with a mapping with lists.
     */
    @AdapterName("berkeleydb-l")
    public static final class WithLists extends BerkeleyDbAdapter {

        @Nonnull
        @Override
        protected ImmutableConfig createConfig() {
            return new BerkeleyDbConfig().withLists();
        }
    }
}
