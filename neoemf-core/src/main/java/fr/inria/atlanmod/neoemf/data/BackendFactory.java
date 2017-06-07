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
import fr.inria.atlanmod.neoemf.option.CommonOptions;
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
    String CONFIG_FILE = "neoconfig.properties";

    /**
     * The "back-end" property in the configuration file.
     */
    String BACKEND_PROPERTY = "backend";

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
     * @see #createTransientStore(PersistentResource)
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
     * @throws InvalidDataStoreException if there is at least one invalid value in {@code options}, or if an option is
     *                                   missing
     * @see #supportsTransient()
     */
    @Nonnull
    Backend createTransientBackend();

    /**
     * Creates a {@link Backend} by using the given {@code uri}.
     *
     * @param uri     the {@link URI} where to store the back-end
     * @param options the options that defines the behaviour of the back-end
     *
     * @return a new back-end
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in {@code options}, or if an option is
     *                                   missing
     */
    @Nonnull
    Backend createPersistentBackend(URI uri, Map<String, Object> options);

    /**
     * Creates a {@link Store} between the given {@code resource} and an in-memory backend, created with the {@link
     * #createTransientBackend()} method.
     *
     * @param resource the resource to store and access
     *
     * @return a new store
     *
     * @see #createStore(Backend, PersistentResource, Map)
     * @see #createTransientBackend()
     */
    @Nonnull
    default Store createTransientStore(PersistentResource resource) {
        return createStore(createTransientBackend(), resource, CommonOptions.noOption());
    }

    /**
     * Creates a {@link Store} between the given {@code resource} and a persistent backend, created with the {@link
     * #createPersistentBackend(URI, Map)} method, according to the given {@code options}.
     * <p>
     * The returned {@link Store} may be a succession of several {@link Store}.
     *
     * @param resource the resource to store and access
     * @param options  the options that defines the behaviour of the back-end and stores
     *
     * @return a new store
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in {@code options}, or if an option is
     *                                   missing
     * @throws IllegalArgumentException  if the given {@code backend} is not an instance of the targeted {@link
     *                                   Backend} for this factory
     * @see #createStore(Backend, PersistentResource, Map)
     * @see #createPersistentBackend(URI, Map)
     */
    @Nonnull
    default Store createPersistentStore(PersistentResource resource, Map<String, Object> options) {
        return createStore(createPersistentBackend(resource.getURI(), options), resource, options);
    }

    /**
     * Creates a {@link Store} between the given {@code resource} and the given {@code backend} according to the given
     * {@code options}.
     * <p>
     * The returned {@link Store} may be a succession of several {@link Store}.
     *
     * @param backend  the back-end where to store data
     * @param resource the resource to store and access
     * @param options  the options that defines the behaviour of the back-end and stores
     *
     * @return a new store
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in {@code options}, or if an option is
     *                                   missing
     * @throws IllegalArgumentException  if the given {@code backend} is not an instance of the targeted {@link
     *                                   Backend} for this factory
     */
    @Nonnull
    Store createStore(Backend backend, PersistentResource resource, Map<String, Object> options);
}
