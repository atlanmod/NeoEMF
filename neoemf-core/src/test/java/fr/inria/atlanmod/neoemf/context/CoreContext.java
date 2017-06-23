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
import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptions;
import fr.inria.atlanmod.neoemf.option.CommonOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.AbstractUriBuilder;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * A specific {@link Context} for the core.
 */
public interface CoreContext extends Context {

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class.
     */
    static Context get() {
        Context c = new CoreContext() {
        };

        // Register the custom URI scheme
        BackendFactoryRegistry.getInstance().register(c.uriScheme(), c.factory());

        return c;
    }

    @Override
    default String name() {
        return "Core";
    }

    @Override
    default BackendFactory factory() {
        AbstractBackendFactory factory = mock(AbstractBackendFactory.class);

        when(factory.name()).thenReturn("mock");
        when(factory.supportsTransient()).thenCallRealMethod();

        when(factory.createTransientBackend()).thenCallRealMethod();
        when(factory.createPersistentBackend(any(), any())).thenReturn(mock(PersistentBackend.class));

        return factory;
    }

    @Override
    default AbstractPersistenceOptions<?> optionsBuilder() {
        return CommonOptions.builder();
    }

    @Override
    default String uriScheme() {
        return "mock";
    }

    /**
     * @see #uriScheme()
     */
    @Override
    default URI createUri(URI uri) {
        return AbstractUriBuilder.builder(uriScheme()).fromUri(uri);
    }

    /**
     * @see #uriScheme()
     */
    @Override
    default URI createUri(File file) {
        return AbstractUriBuilder.builder(uriScheme()).fromFile(file);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This {@code Context} doesn't support the {@link PersistentResource} creation.
     *
     * @throws UnsupportedOperationException every time
     */
    @Override
    default PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This {@code Context} doesn't support the {@link PersistentResource} creation.
     *
     * @throws UnsupportedOperationException every time
     */
    @Override
    default PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This {@code Context} doesn't support the {@link PersistentResource} loading.
     *
     * @throws UnsupportedOperationException every time
     */
    @Override
    default PersistentResource loadResource(EPackage ePackage, File file) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    default Backend createMapper(File file) {
        return new DefaultTransientBackend();
    }
}
