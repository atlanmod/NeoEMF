/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.config.BerkeleyDbConfig;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Adapter} on top of a {@link fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackend}.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public abstract class BerkeleyDbAdapter extends AbstractNeoAdapter {

    /**
     * Constructs a new {@code BerkeleyDbAdapter}.
     *
     * @param storeExtension the extension of the resource, used for benchmarks
     */
    protected BerkeleyDbAdapter(String storeExtension) {
        super("berkeleydb." + storeExtension);
    }

    @Nonnull
    @Override
    protected BackendFactory getFactory() {
        return new BerkeleyDbBackendFactory();
    }

    /**
     * A {@link BerkeleyDbAdapter} with a mapping with indices.
     */
    @ParametersAreNonnullByDefault
    public static final class WithIndices extends BerkeleyDbAdapter {

        /**
         * Constructs a new {@code BerkeleyDbAdapter.WithIndices}.
         */
        public WithIndices() {
            super("indices");
        }

        @Nonnull
        @Override
        public Map<String, ?> getOptions() {
            return new BerkeleyDbConfig().withIndices().toMap();
        }
    }

    /**
     * A {@link BerkeleyDbAdapter} with a mapping with arrays.
     */
    @ParametersAreNonnullByDefault
    public static final class WithArrays extends BerkeleyDbAdapter {

        /**
         * Constructs a new {@code BerkeleyDbAdapter.WithArrays}.
         */
        public WithArrays() {
            super("arrays");
        }

        @Nonnull
        @Override
        public Map<String, ?> getOptions() {
            return new BerkeleyDbConfig().withArrays().toMap();
        }
    }

    /**
     * A {@link BerkeleyDbAdapter} with a mapping with lists.
     */
    @ParametersAreNonnullByDefault
    public static final class WithLists extends BerkeleyDbAdapter {

        /**
         * Constructs a new {@code BerkeleyDbAdapter.WithLists}.
         */
        public WithLists() {
            super("lists");
        }

        @Nonnull
        @Override
        public Map<String, ?> getOptions() {
            return new BerkeleyDbConfig().withLists().toMap();
        }
    }
}
