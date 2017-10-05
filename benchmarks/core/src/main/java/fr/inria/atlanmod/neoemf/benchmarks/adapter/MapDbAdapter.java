/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.config.MapDbConfig;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Adapter} on top of a {@link fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackend}.
 */
@ParametersAreNonnullByDefault
public abstract class MapDbAdapter extends AbstractNeoAdapter {

    /**
     * Constructs a new {@code MapDbAdapter}.
     *
     * @param storeExtension the extension of the resource, used for benchmarks
     */
    protected MapDbAdapter(String storeExtension) {
        super("mapdb." + storeExtension);
    }

    @Nonnull
    @Override
    protected BackendFactory getFactory() {
        return MapDbBackendFactory.getInstance();
    }

    /**
     * A {@link MapDbAdapter} with a mapping with indices.
     */
    public static final class WithIndices extends MapDbAdapter {

        /**
         * Constructs a new {@code MapDbAdapter.WithIndices}.
         */
        @SuppressWarnings("unused") // Called dynamically
        public WithIndices() {
            super("indices");
        }

        @Nonnull
        @Override
        public Map<String, ?> getOptions() {
            return MapDbConfig.newConfig()
                    .withIndices()
                    .toMap();
        }
    }

    /**
     * A {@link MapDbAdapter} with a mapping with arrays.
     */
    public static final class WithArrays extends MapDbAdapter {

        /**
         * Constructs a new {@code MapDbAdapter.WithArrays}.
         */
        @SuppressWarnings("unused") // Called dynamically
        public WithArrays() {
            super("arrays");
        }

        @Nonnull
        @Override
        public Map<String, ?> getOptions() {
            return MapDbConfig.newConfig()
                    .withArrays()
                    .toMap();
        }
    }

    /**
     * A {@link MapDbAdapter} with a mapping with lists.
     */
    public static final class WithLists extends MapDbAdapter {

        /**
         * Constructs a new {@code MapDbAdapter.WithLists}.
         */
        @SuppressWarnings("unused") // Called dynamically
        public WithLists() {
            super("lists");
        }

        @Nonnull
        @Override
        public Map<String, ?> getOptions() {
            return MapDbConfig.newConfig()
                    .withLists()
                    .toMap();
        }
    }
}
