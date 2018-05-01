/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util.service;

import fr.inria.atlanmod.commons.Throwables;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;

import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link ServiceContext} able to retrieve registered services from a {@link BundleContext}.
 */
@ParametersAreNonnullByDefault
class BundleServiceContext implements ServiceContext {

    /**
     * The bundle context.
     */
    @Nonnull
    private final BundleContext context;

    /**
     * Constructs a new {@code BundleServiceContext}.
     *
     * @param context the bundle context
     */
    public BundleServiceContext(BundleContext context) {
        this.context = context;
    }

    @Nonnull
    @Override
    public <T> Iterable<T> getServices(Class<T> superType) {
        try {
            return context.getServiceReferences(superType, null).stream()
                    .map(context::getService)
                    .collect(Collectors.toSet());
        }
        catch (InvalidSyntaxException e) {
            throw Throwables.shouldNeverHappen(e);
        }
    }
}
