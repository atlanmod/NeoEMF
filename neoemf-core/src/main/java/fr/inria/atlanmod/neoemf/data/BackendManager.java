/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

import org.atlanmod.commons.annotation.Singleton;
import org.atlanmod.commons.annotation.Static;
import org.atlanmod.commons.concurrent.MoreThreads;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Nonnull;

/**
 * The manager of {@link Backend} instances. All registered {@link Backend}s will be automatically closed when the
 * application will shutdown.
 */
@Singleton
final class BackendManager {

    /**
     * A set that holds all active {@link Backend} instances.
     */
    @Nonnull
    private final Set<AbstractBackend> activeBackends = new LinkedHashSet<>();

    /**
     * Constructs a new {@code BackendManager}.
     */
    private BackendManager() {
        MoreThreads.executeAtExit(this::onShutdown);
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static BackendManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Registers the {@code backend}.
     *
     * @param backend the backend to register
     */
    public void register(AbstractBackend backend) {
        activeBackends.add(backend);
    }

    /**
     * Unregisters the {@code backend}.
     *
     * @param backend the backend to unregister
     */
    public void unregister(AbstractBackend backend) {
        activeBackends.remove(backend);
    }

    /**
     * Cleanly closes all registered back-ends.
     */
    private synchronized void onShutdown() {
        if (!activeBackends.isEmpty()) {
            activeBackends.parallelStream().forEachOrdered(b -> b.close(false));
            activeBackends.clear();
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
        static final BackendManager INSTANCE = new BackendManager();
    }
}