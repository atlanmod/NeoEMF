/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store.listener;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link StoreListener} that records every calls to build usage statistics.
 *
 * @see StoreStats
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public class RecordingStoreListener implements StoreListener {

    /**
     * The stats to update.
     */
    @Nonnull
    private final StoreStats stats;

    /**
     * Constructs a new {@code RecordingStoreListener}.
     *
     * @param stats the stats to update
     */
    public RecordingStoreListener(StoreStats stats) {
        this.stats = stats;
    }

    @Nonnull
    public StoreStats stats() {
        return stats;
    }

    @Override
    public void onInitialize() {
        // Do nothing
    }

    @Override
    public <K, V, R> void onSuccess(SuccessCallReport<K, V, R> callReport) {
        stats.hasBeenInvoked(callReport.method());
    }

    @Override
    public <K, V> void onFailure(FailureCallReport<K, V> callReport) {
        stats.hasBeenInvoked(callReport.method());
    }

    @Override
    public void onClose() {
        // Do nothing
    }
}
