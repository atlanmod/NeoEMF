/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util.service;

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.commons.annotation.VisibleForReflection;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link ServiceContext} able to retrieve registered services from a {@link BundleContext}.
 */
@Component(immediate = true)
@VisibleForReflection
@ParametersAreNonnullByDefault
public class BundleServiceContext implements ServiceContext {

    /**
     * The current bundle context.
     */
    private BundleContext context;

    @Activate
    public void onActivate(BundleContext context) {
        this.context = context;
        ServiceResolver.getInstance().setContext(this);
    }

    @Deactivate
    public void onDeactivate() {
        this.context = null;
        ServiceResolver.getInstance().unloadContext();
    }

    @Nonnull
    @Override
    public <T> Iterable<T> getServices(Class<T> type) {
        return getServices(type, null);
    }

    @Nonnull
    public <T> Iterable<T> getServices(Class<T> type, @Nullable String filter) {
        try {
            return context.getServiceReferences(type, filter).stream()
                    .map(context::getService)
                    .collect(Collectors.toSet());
        }
        catch (InvalidSyntaxException e) {
            throw Throwables.shouldNeverHappen(e);
        }
    }
}
