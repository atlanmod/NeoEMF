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

package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.DefaultTransientBackend;
import fr.inria.atlanmod.neoemf.data.PersistentBackend;
import fr.inria.atlanmod.neoemf.option.CommonOptions;
import fr.inria.atlanmod.neoemf.option.PersistenceOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.AbstractUriBuilder;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
    public PersistenceOptions optionsBuilder() {
        return CommonOptions.builder();
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
    public PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException {
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
    public PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException {
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
    public PersistentResource loadResource(@Nullable EPackage ePackage, File file) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Nonnull
    @Override
    public Backend createMapper(File file) {
        return new DefaultTransientBackend();
    }
}
