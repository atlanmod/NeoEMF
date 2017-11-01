/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that records every calls to build usage statistics.
 *
 * @see StoreStats
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public class StatsRecordStore extends AbstractListenerStore {

    /**
     * A map that accumulates the different calls made on the {@link Store} chain.
     */
    @Nonnull
    private final Map<String, AtomicLong> methodCallsAccumulator = new HashMap<>();

    /**
     * Constructs a new {@code LogStore}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    public StatsRecordStore(Store store) {
        super(store);
    }

    @Nonnull
    @Override
    public StoreStats stats() {
        return new StoreStats(methodCallsAccumulator);
    }

    @Override
    protected <K, V, R> void onSuccess(CallInfo<K, V, R> info) {
        record(info);
    }

    @Override
    protected <K, V> void onFailure(CallInfo<K, V, ?> info) {
        record(info);
    }

    /**
     * Records the call of a method with a value.
     */
    protected void record(CallInfo<?, ?, ?> info) {
        methodCallsAccumulator.computeIfAbsent(info.method(), s -> new AtomicLong()).incrementAndGet();
    }
}
