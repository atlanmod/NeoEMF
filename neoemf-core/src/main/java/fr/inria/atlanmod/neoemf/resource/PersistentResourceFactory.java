/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.resource;

import fr.inria.atlanmod.commons.annotation.Singleton;
import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * The factory that creates {@link PersistentResource} instances.
 */
@Singleton
@ParametersAreNonnullByDefault
public class PersistentResourceFactory implements Resource.Factory {

    /**
     * Constructs a new {@code PersistentResourceFactory}.
     */
    protected PersistentResourceFactory() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static PersistentResourceFactory getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The {@code uri} must be registered in the {@link BackendFactoryRegistry}.
     */
    @Nonnull
    @Override
    public PersistentResource createResource(URI uri) {
        checkNotNull(uri, "uri");
        checkArgument(BackendFactoryRegistry.getInstance().isRegistered(uri.scheme()),
                "Unregistered scheme (%s)", uri.scheme());

        return new DefaultPersistentResource(uri);
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        static final PersistentResourceFactory INSTANCE = new PersistentResourceFactory();
    }
}
