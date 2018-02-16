/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.commons.annotation.Singleton;
import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.neoemf.config.ConfigType;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.config.InvalidConfigException;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractMapperFactory;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A factory that creates instances of {@link Store}.
 */
@Singleton
@ParametersAreNonnullByDefault
public final class StoreFactory extends AbstractMapperFactory {

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

        // The tail of the store chain
        Store currentStore = createDefaultStore(backend);

        try {
            List<ConfigType<? extends Store>> storeTypes = baseConfig.getStoreTypes().stream()
                    .sorted(Collections.reverseOrder())
                    .collect(Collectors.toList());

            for (ConfigType<? extends Store> st : storeTypes) {
                Deque<Object> parameters = findValuesFor(st, baseConfig);

                // Each store has a store as first argument
                parameters.addFirst(currentStore);

                currentStore = createMapper(st.typeName(), parameters.toArray(new Object[parameters.size()]));
            }
        }
        catch (Exception e) {
            throw Throwables.wrap(e, InvalidStoreException.class);
        }

        return currentStore;
    }

    /**
     * Creates the default store in front of the {@code backend}.
     *
     * @param backend the back-end where to store data
     *
     * @return the default store
     */
    @Nonnull
    protected Store createDefaultStore(Backend backend) {
        return new NoopStore(backend);
    }

    /**
     * Finds the values of all defined parameters of {@code type} in the {@code baseConfig}.
     *
     * @param type       the store declaration
     * @param baseConfig the configuration that defines the behaviour of the store
     *
     * @return a set of values
     */
    @Nonnull
    private Deque<Object> findValuesFor(ConfigType<?> type, ImmutableConfig baseConfig) {
        return type.parameterKeys().stream()
                .filter(baseConfig::hasOption)
                .map(p -> baseConfig.getOption(p).<InvalidConfigException>orElseThrow(InvalidConfigException::new))
                .collect(Collectors.toCollection(ArrayDeque::new));
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
