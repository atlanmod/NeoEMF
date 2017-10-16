/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;

import java.util.Map;

import javax.annotation.MatchesPattern;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A factory of {@link Backend} and {@link Store}.
 * <p>
 * The creation can be configured using {@link PersistentResource#save(Map)} and {@link PersistentResource#load(Map)}
 * option maps.
 * <p>
 * For reflection reasons, each instance of {@code BackendFactory} <b>must</b> have a static field {@code NAME},
 * representing the name of the created {@link Backend} instances.
 *
 * @see Config
 */
@ParametersAreNonnullByDefault
public interface BackendFactory {

    /**
     * Returns the literal description of the created {@link Backend}.
     *
     * @return the literal description of the created {@link Backend}
     */
    @MatchesPattern("^[a-z]+$")
    String name();

    /**
     * Returns whether the {@link Backend}s created by this factory support the transient state.
     *
     * @return {@code true} if the {@link Backend}s created by this factory support the transient state, {@code false}
     * otherwise
     */
    default boolean supportsTransient() {
        return true;
    }

    /**
     * Creates a persistent {@link Backend} located by the {@code uri}.
     *
     * @param uri        the {@link URI} where to store the back-end
     * @param baseConfig the base configuration that defines the behaviour of the back-end
     *
     * @return a new back-end
     *
     * @throws InvalidBackendException if there is at least one invalid value in {@code baseConfig}, or if an option is
     *                                 missing
     */
    @Nonnull
    Backend createBackend(URI uri, ImmutableConfig baseConfig);
}
