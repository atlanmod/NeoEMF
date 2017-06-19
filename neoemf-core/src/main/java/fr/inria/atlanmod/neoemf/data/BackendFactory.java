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

package fr.inria.atlanmod.neoemf.data;

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
 *
 * @see fr.inria.atlanmod.neoemf.option.PersistenceOptions
 */
@ParametersAreNonnullByDefault
public interface BackendFactory {

    /**
     * The name of the configuration file of a back-end persistence.
     */
    // TODO Replace the file name to something like "neoemf.properties"
    String CONFIG_FILE = "neoconfig.properties";

    /**
     * The property to define the {@link Backend} in the configuration file.
     */
    String BACKEND_PROPERTY = "backend";

    /**
     * The property to define the {@link BackendFactory} in the configuration file.
     */
    String FACTORY_PROPERTY = "factory";

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
     *
     * @see #createTransientBackend()
     */
    default boolean supportsTransient() {
        return true;
    }

    /**
     * Creates an in-memory {@link Backend}.
     *
     * @return a new back-end
     *
     * @throws InvalidBackendException if there is at least one invalid value in {@code options}, or if an option is
     *                                 missing
     * @see #supportsTransient()
     */
    @Nonnull
    // TODO Return a `TransientBackend`
    Backend createTransientBackend();

    /**
     * Creates a {@link Backend} by using the given {@code uri}.
     *
     * @param uri     the {@link URI} where to store the back-end
     * @param options the options that defines the behaviour of the back-end
     *
     * @return a new back-end
     *
     * @throws InvalidBackendException if there is at least one invalid value in {@code options}, or if an option is
     *                                 missing
     */
    @Nonnull
    PersistentBackend createPersistentBackend(URI uri, Map<String, Object> options);
}
