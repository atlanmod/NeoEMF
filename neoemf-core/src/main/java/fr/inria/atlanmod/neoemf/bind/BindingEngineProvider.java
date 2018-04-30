/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.bind;

import fr.inria.atlanmod.commons.Lazy;
import fr.inria.atlanmod.commons.annotation.Singleton;
import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.neoemf.bind.internal.ReflectiveBindingEngine;
import fr.inria.atlanmod.neoemf.bind.internal.ServiceBindingEngine;

import org.osgi.framework.BundleContext;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A provider of the {@link BindingEngine} instance to use.
 */
@Singleton
@ParametersAreNonnullByDefault
public final class BindingEngineProvider {

    /**
     * The current on-demand binding engine.
     */
    @Nonnull
    private final Lazy<BindingEngine> engine = Lazy.with(ServiceBindingEngine::new);

    /**
     * Constructs a new {@code BindingEngineProvider}.
     */
    private BindingEngineProvider() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static BindingEngineProvider getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Returns the current binding engine.
     *
     * @return the current binding engine
     */
    @Nonnull
    public BindingEngine getEngine() {
        return engine.get();
    }

    /**
     * Loads a {@code context} to be scanned for binding.
     *
     * @param context the OSGi context to load
     */
    public void loadContext(BundleContext context) {
        engine.update(new ReflectiveBindingEngine(context));
    }

    /**
     * Unloads a {@code context} previously loaded for binding.
     *
     * @param context the OSGi context to unload
     */
    public void unloadContext(BundleContext context) {
        engine.update(new ServiceBindingEngine());
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        static final BindingEngineProvider INSTANCE = new BindingEngineProvider();
    }
}
