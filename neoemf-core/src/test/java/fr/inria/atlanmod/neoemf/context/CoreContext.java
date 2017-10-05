/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.DefaultTransientBackend;
import fr.inria.atlanmod.neoemf.data.PersistentBackend;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.AbstractUriBuilder;

import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.io.IOException;

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
    public static Context get() {
        Context c = new CoreContext() {
        };

        // Register the custom URI scheme
        BackendFactoryRegistry.getInstance().register(c.uriScheme(), c.factory());

        return c;
    }

    @Nonnull
    @Override
    public String name() {
        return "Core";
    }

    @Nonnull
    @Override
    public BackendFactory factory() {
        AbstractBackendFactory factory = mock(AbstractBackendFactory.class);

        when(factory.name()).thenReturn("mock");
        when(factory.supportsTransient()).thenCallRealMethod();

        when(factory.createTransientBackend()).thenCallRealMethod();
        when(factory.createPersistentBackend(any(), any())).thenReturn(mock(PersistentBackend.class));

        return factory;
    }

    @Nonnull
    @Override
    public Config config() {
        return BaseConfig.newConfig();
    }

    @Nonnull
    @Override
    public String uriScheme() {
        return "mock";
    }

    /**
     * @see #uriScheme()
     */
    @Nonnull
    @Override
    public URI createUri(URI uri) {
        return AbstractUriBuilder.builder(uriScheme()).fromUri(uri);
    }

    /**
     * @see #uriScheme()
     */
    @Nonnull
    @Override
    public URI createUri(File file) {
        return AbstractUriBuilder.builder(uriScheme()).fromFile(file);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This {@code Context} doesn't support the {@link PersistentResource} creation.
     *
     * @throws UnsupportedOperationException every time
     */
    @Nonnull
    @Override
    public PersistentResource createPersistentResource(File file) throws IOException {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This {@code Context} doesn't support the {@link PersistentResource} creation.
     *
     * @throws UnsupportedOperationException every time
     */
    @Nonnull
    @Override
    public PersistentResource createTransientResource(File file) throws IOException {
        throw new UnsupportedOperationException();
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
    public PersistentResource loadResource(File file) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Nonnull
    @Override
    public Backend createMapper(File file) {
        return new DefaultTransientBackend();
    }
}
