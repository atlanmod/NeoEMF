/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store.listener;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Information about a call to a {@link fr.inria.atlanmod.neoemf.data.Backend}.
 *
 * @param <K> the type of the key used during the call
 * @param <V> the type of the value used during the call
 */
@ParametersAreNonnullByDefault
public abstract class AbstractCallReport<K, V> {

    /**
     * Information about the backend related to this call.
     */
    @Nonnull
    private final BackendReport backend;

    /**
     * The name of the called method.
     */
    @Nonnull
    private final String method;

    /**
     * The key used during the call.
     */
    @Nullable
    private final K key;

    /**
     * The value used during the call.
     */
    @Nullable
    private final V value;

    /**
     * Constructs a new succeeded call information.
     *
     * @param backend information about the backend related to this call
     * @param method  the name of the called method
     * @param key     the key used during the call
     * @param value   the value used during the call
     */
    public AbstractCallReport(BackendReport backend, String method, @Nullable K key, @Nullable V value) {
        this.method = method;
        this.key = key;
        this.value = value;
        this.backend = backend;
    }

    /**
     * Returns the information about the backend related to this call.
     *
     * @return the information about the backend related to this call
     */
    @Nonnull
    public BackendReport backend() {
        return backend;
    }

    /**
     * Returns the name of the called method.
     *
     * @return the name of the called method
     */
    @Nonnull
    public String method() {
        return method;
    }

    /**
     * Returns the key used during the call.
     *
     * @return the key used during the call
     */
    @Nullable
    public K key() {
        return key;
    }

    /**
     * Returns the value used during the call.
     *
     * @return the value used during the call
     */
    @Nullable
    public V value() {
        return value;
    }
}
