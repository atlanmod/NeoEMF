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

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapper.AbstractMapperDecorator;

import org.eclipse.emf.ecore.resource.Resource;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} that translates model-level operations into datastore calls.
 */
@ParametersAreNonnullByDefault
public class DirectWriteStore extends AbstractMapperDecorator<Backend> implements Store {

    /**
     * A set that holds all {@link Backend}s that need to be closed when the JVM will shutdown.
     */
    @Nonnull
    private static final Set<Backend> BACKENDS = new HashSet<>();

    static {
        ThreadFactory factory = task -> {
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            return thread;
        };

        Runtime.getRuntime().addShutdownHook(factory.newThread(() -> {
            int count = BACKENDS.size();

            for (Iterator<Backend> adapters = BACKENDS.iterator(); adapters.hasNext(); ) {
                adapters.next().close();
                adapters.remove();
            }

            if (count > 0) {
                Log.info("All back-ends have been properly closed ({0,number,#})", count);
            }
        }));
    }

    /**
     * The resource to store and access.
     */
    @Nullable
    private Resource.Internal resource;

    /**
     * Constructs a new {@code DirectWriteStore} between the given {@code resource} and the {@code backend}.
     *
     * @param backend  the back-end used to store the model
     * @param resource the resource to store and access
     */
    public DirectWriteStore(Backend backend, @Nullable Resource.Internal resource) {
        super(backend);
        this.resource = resource;

        BACKENDS.add(backend);
    }

    @Nullable
    @Override
    public Resource.Internal resource() {
        return resource;
    }

    @Override
    public void resource(@Nullable Resource.Internal resource) {
        this.resource = resource;
    }

    @Nonnull
    @Override
    public Backend backend() {
        return next();
    }

    @Override
    public void close() {
        BACKENDS.remove(backend());
        super.close();
    }
}