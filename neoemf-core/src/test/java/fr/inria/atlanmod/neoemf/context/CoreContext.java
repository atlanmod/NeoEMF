/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.im.BoundInMemoryBackend;
import fr.inria.atlanmod.neoemf.data.im.InMemoryBackendFactory;
import fr.inria.atlanmod.neoemf.data.im.config.InMemoryConfig;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * A specific {@link Context} for the core.
 */
@ParametersAreNonnullByDefault
public abstract class CoreContext extends AbstractContext {

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class.
     */
    @Nonnull
    public static Context getDefault() {
        return new Default();
    }

    @Nonnull
    public static Context boundTo(Id id) {
        return new Bound(id);
    }

    @Nonnull
    @Override
    public String name() {
        return "Core";
    }

    @Nonnull
    @Override
    public BackendFactory factory() {
        return InMemoryBackendFactory.getInstance();
    }

    @Nonnull
    @Override
    public Config config() {
        return InMemoryConfig.newConfig();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This {@code Context} doesn't support the {@link PersistentResource} loading.
     *
     * @throws UnsupportedOperationException every time
     */
    @Nonnull
    @Override
    public PersistentResource loadResource(File file) {
        throw new UnsupportedOperationException();
    }

    @ParametersAreNonnullByDefault
    private static class Default extends CoreContext {
    }

    @ParametersAreNonnullByDefault
    private static class Bound extends CoreContext {

        @Nonnull
        private final Id id;

        public Bound(Id id) {
            this.id = id;
        }

        @Nonnull
        @Override
        public String name() {
            return super.name() + "#Bound[" + id + ']';
        }

        @Nonnull
        @Override
        public BackendFactory factory() {
            BackendFactory factory = mock(InMemoryBackendFactory.class);
            when(factory.name()).thenCallRealMethod();
            when(factory.supportsTransient()).thenCallRealMethod();
            when(factory.createBackend(any(URI.class), any(ImmutableConfig.class))).thenAnswer((i) -> new BoundInMemoryBackend(id));
            return factory;
        }
    }
}
