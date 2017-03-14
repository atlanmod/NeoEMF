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

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapper.AbstractMapperDecorator;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.eclipse.emf.common.util.URI;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link Store} that translates model-level operations into datastore calls.
 */
@ParametersAreNonnullByDefault
public final class DirectWriteStore extends AbstractMapperDecorator<Backend> implements Store {

    /**
     * The resource to store and access.
     */
    @Nullable
    private final PersistentResource resource;

    /**
     * Constructs a new {@code DirectWriteStore} on the given {@code backend}. This {@code DirectWriteStore} will be
     * detached of a {@link PersistentResource}.
     *
     * @param backend the back-end used to store the model
     */
    public DirectWriteStore(Backend backend) {
        this(backend, null);
    }

    /**
     * Constructs a new {@code DirectWriteStore} between the given {@code resource} and the {@code backend}.
     *
     * @param backend  the back-end used to store the model
     * @param resource the resource to store and access
     */
    public DirectWriteStore(Backend backend, @Nullable PersistentResource resource) {
        super(backend);
        this.resource = resource;

        if (nonNull(resource)) { // isAttached ?
            closeOnExit(backend, resource.getURI());
        }
    }

    /**
     * Adds a shutdown hook on the given {@code backend}. It will be stopped when the application will exit.
     *
     * @param backend the back-end to stop when the application will exit
     * @param uri     the {@link URI} of the resource used by the {@code backend}
     */
    private static void closeOnExit(Backend backend, URI uri) {
        //noinspection ConstantConditions
        if (nonNull(backend)) { // The backend can be null in tests by using mocks
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                backend.close();
                Log.debug("{0} closed: {1} ", backend.getClass().getSimpleName(), uri);
            }));
        }
    }

    @Override
    public PersistentResource resource() {
        return resource;
    }

    @Nonnull
    @Override
    public Backend backend() {
        return next();
    }
}