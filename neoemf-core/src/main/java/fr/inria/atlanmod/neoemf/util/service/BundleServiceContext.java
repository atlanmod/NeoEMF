/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util.service;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link ServiceContext} able to retrieve OSGi Declarative Services from a {@link BundleContext}.
 *
 * <b>NOTE:</b> This context is automatically loaded and configured when this bundle is starting under an OSGi environment.
 */
@Component(immediate = true)
@VisibleForReflection
@ParametersAreNonnullByDefault
public class BundleServiceContext implements ServiceContext {

    /**
     * The current bundle context.
     */
    private BundleContext context;

    /**
     * Activates this context with the specified OSGi {@code context}.
     *
     * @param context the current bundle context
     */
    @Activate
    public void onActivate(BundleContext context) {
        this.context = context;
        ServiceResolver.getInstance().setContext(this);
    }

    /**
     * Deactivates this context.
     */
    @Deactivate
    public void onDeactivate() {
        this.context = null;
        ServiceResolver.getInstance().unloadContext();
    }

    @Nonnull
    @Override
    public <T> Stream<T> getServices(Class<T> type) {
        return getServices(type, null);
    }

    /**
     * Retrieves all registered services of the specified {@code type}.
     *
     * @param type   the type of services to look for
     * @param filter the filter to determine if a service should be returned
     *
     * @return a parallel stream of all registered services of the specified {@code type}
     */
    @Nonnull
    public <T> Stream<T> getServices(Class<T> type, @Nullable String filter) {
        try {
            return context.getServiceReferences(type, filter).stream().map(context::getService).parallel();
        }
        catch (InvalidSyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
