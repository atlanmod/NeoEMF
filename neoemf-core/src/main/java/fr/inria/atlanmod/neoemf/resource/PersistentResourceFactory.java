/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.resource;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * The factory that creates {@link PersistentResource}s.
 */
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
     * The {@code uri} must be registered in the {@link PersistenceBackendFactoryRegistry}.
     */
    @Nullable
    @Override
    public Resource createResource(URI uri) {
        checkNotNull(uri);

        return PersistenceBackendFactoryRegistry.isRegistered(uri.scheme()) ? new DefaultPersistentResource(uri) : null;
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final PersistentResourceFactory INSTANCE = new PersistentResourceFactory();
    }
}
