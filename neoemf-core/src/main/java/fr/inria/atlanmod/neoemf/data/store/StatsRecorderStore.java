/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.neoemf.data.store.listener.FailureCallReport;
import fr.inria.atlanmod.neoemf.data.store.listener.StoreListener;
import fr.inria.atlanmod.neoemf.data.store.listener.SuccessCallReport;

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
// TODO Declare this class as a simple listener, and registers it in a ListeningStore
public class StatsRecorderStore extends AbstractListeningStore implements StoreListener {

    /**
     * A map that accumulates the different calls made on the {@link Store} chain.
     */
    @Nonnull
    private final Map<String, AtomicLong> methodCallsAccumulator = new HashMap<>();

    /**
     * Constructs a new {@code LoggingStore}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    public StatsRecorderStore(Store store) {
        super(store);
    }

    @Nonnull
    @Override
    public StoreStats stats() {
        return new StoreStats(methodCallsAccumulator);
    }

    @Override
    public void onInitialize() {
        // Do nothing
    }

    @Override
    public <K, V, R> void onSuccess(SuccessCallReport<K, V, R> callReport) {
        record(callReport.method());
    }

    @Override
    public <K, V> void onFailure(FailureCallReport<K, V> callReport) {
        record(callReport.method());
    }

    @Override
    public void onClose() {
        // Do nothing
    }

    /**
     * Records the call of a method with a value.
     *
     * @param method the name of the called method
     */
    protected void record(String method) {
        methodCallsAccumulator.computeIfAbsent(method, s -> new AtomicLong()).incrementAndGet();
    }
}
