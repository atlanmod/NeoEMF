/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store.listener;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A failure {@link AbstractCallReport}.
 *
 * @param <K> the type of the key used during the call
 * @param <V> the type of the value used during the call
 */
@ParametersAreNonnullByDefault
public class FailureCallReport<K, V> extends AbstractCallReport<K, V> {

    /**
     * The exception thrown during the call.
     */
    @Nullable
    private final Throwable thrownException;

    /**
     * Constructs a new failed call information.
     *
     * @param backend information about the backend related to this call
     * @param method  the name of the called method
     * @param key     the key used during the call
     * @param value   the value used during the call
     * @param e       the exception thrown during the call
     */
    public FailureCallReport(BackendReport backend, String method, @Nullable K key, @Nullable V value, @Nullable Throwable e) {
        super(backend, method, key, value);
        this.thrownException = e;
    }

    /**
     * Returns the exception thrown during the call.
     *
     * @return the exception thrown during the call
     */
    @Nullable
    public Throwable thrownException() {
        return thrownException;
    }
}
