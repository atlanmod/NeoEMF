/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.Backend;

import org.atlanmod.commons.annotation.Singleton;
import org.atlanmod.commons.annotation.Static;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A factory that creates instances of {@link Store}.
 */
@Singleton
@ParametersAreNonnullByDefault
public final class StoreFactory {

    /**
     * Constructs a new {@code StoreFactory}.
     */
    private StoreFactory() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static StoreFactory getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Creates a new {@link Store} in front of the {@code backend} according to the specified {@code baseConfig}.
     * <p>
     * The returned {@link Store} may be a succession of several {@link Store}.
     *
     * @param backend    the back-end where to store data
     * @param baseConfig the configuration that defines the behaviour of the store
     *
     * @return a new store
     *
     * @throws NullPointerException  if the {@code store} or the {@code baseConfig} are {@code null}
     * @throws InvalidStoreException if an error occurs during the creation of the store
     */
    @Nonnull
    public Store createStore(Backend backend, ImmutableConfig baseConfig) {
        checkNotNull(baseConfig, "baseConfig");

        try {
            // The tail of the store chain
            Store currentStore = new NoopStore(backend);

            final List<Store> reverseStores = baseConfig.getStores().stream()
                    .sorted(Collections.reverseOrder())
                    .collect(Collectors.toList());

            for (Store s : reverseStores) {
                AbstractStore.class.cast(s).next(currentStore);
                currentStore = s;
            }

            return currentStore;
        }
        catch (Exception e) {
            throw new InvalidStoreException(e);
        }
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        static final StoreFactory INSTANCE = new StoreFactory();
    }
}
