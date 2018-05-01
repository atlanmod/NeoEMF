/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util.service;

import fr.inria.atlanmod.commons.Lazy;
import fr.inria.atlanmod.commons.annotation.Singleton;
import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.collect.MoreIterables;

import org.osgi.framework.BundleContext;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object able to retrieve and load services according to a {@link ServiceContext}.
 */
@Singleton
@ParametersAreNonnullByDefault
public final class ServiceResolver {

    /**
     * The current on-demand service provider.
     */
    @Nonnull
    private final Lazy<ServiceContext> provider = Lazy.with(ServiceLoaderContext::new);

    /**
     * Constructs a new {@code ServiceResolver}.
     */
    private ServiceResolver() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static ServiceResolver getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Retrieves all registered services of the specified {@code type}.
     *
     * @return an iterable
     */
    @Nonnull
    public <T> Iterable<T> find(Class<T> type) {
        return provider.get().getServices(type);
    }

    /**
     * Retrieves all registered services of the specified {@code type} that matches the {@code filter}
     *
     * @param type   the type of services to look for
     * @param filter the filter to determine if a service should be returned
     *
     * @return an iterable
     */
    @Nonnull
    public <T> Iterable<T> find(Class<T> type, Predicate<T> filter) {
        return MoreIterables.stream(find(type)).filter(filter).collect(Collectors.toList());
    }

    /**
     * Loads a {@link BundleContext}.
     *
     * @param context the bundle context to load
     *
     * @see #unloadContext()
     */
    public void loadContext(BundleContext context) {
        provider.update(new BundleServiceContext(context));
    }

    /**
     * Unloads the previously loaded {@link BundleContext}.
     *
     * @see #loadContext(BundleContext)
     */
    public void unloadContext() {
        provider.update(new ServiceLoaderContext());
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        static final ServiceResolver INSTANCE = new ServiceResolver();
    }
}
