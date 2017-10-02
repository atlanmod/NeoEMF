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
        return BerkeleyDbBackendFactory.getInstance();
    }

    /**
     * A {@link BerkeleyDbAdapter} with a mapping with indices.
     */
    public static final class WithIndices extends BerkeleyDbAdapter {

        /**
         * Constructs a new {@code BerkeleyDbAdapter.WithIndices}.
         */
        @SuppressWarnings("unused") // Called dynamically
        public WithIndices() {
            super("indices");
        }

        @Nonnull
        @Override
        public Map<String, ?> getOptions() {
            return BerkeleyDbConfig.newConfig()
                    .withIndices()
                    .toMap();
        }
    }

    /**
     * A {@link BerkeleyDbAdapter} with a mapping with arrays.
     */
    public static final class WithArrays extends BerkeleyDbAdapter {

        /**
         * Constructs a new {@code BerkeleyDbAdapter.WithArrays}.
         */
        @SuppressWarnings("unused") // Called dynamically
        public WithArrays() {
            super("arrays");
        }

        @Nonnull
        @Override
        public Map<String, ?> getOptions() {
            return BerkeleyDbConfig.newConfig()
                    .withArrays()
                    .toMap();
        }
    }

    /**
     * A {@link BerkeleyDbAdapter} with a mapping with lists.
     */
    public static final class WithLists extends BerkeleyDbAdapter {

        /**
         * Constructs a new {@code BerkeleyDbAdapter.WithLists}.
         */
        @SuppressWarnings("unused") // Called dynamically
        public WithLists() {
            super("lists");
        }

        @Nonnull
        @Override
        public Map<String, ?> getOptions() {
            return BerkeleyDbConfig.newConfig()
                    .withLists()
                    .toMap();
        }
    }
}
