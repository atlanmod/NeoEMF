/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;
import fr.inria.atlanmod.neoemf.util.UriFactory;

import org.atlanmod.commons.Throwables;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;
import static org.atlanmod.commons.Preconditions.checkState;

/**
 * An abstract {@link Adapter} on top of a {@link fr.inria.atlanmod.neoemf.data.Backend}.
 */
@ParametersAreNonnullByDefault
abstract class AbstractPersistentAdapter extends AbstractAdapter {

    /**
     * Constructs a new {@code AbstractPersistentAdapter}.
     */
    protected AbstractPersistentAdapter() {
        super("neoemf", org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.class);
    }

    @Override
    public boolean supportsMapper() {
        return true;
    }

    @Nonnull
    @Override
    protected final Map<String, ?> getOptions(ImmutableConfig config) {
        return new BaseConfig<>().merge(config).merge(createConfig()).toMap();
    }

    @Nonnull
    @Override
    public Resource create(URI uri) {
        return new ResourceSetImpl().createResource(uri);
    }

    @Nonnull
    @Override
    public DataMapper createMapper(URI uri, ImmutableConfig config) {
        ImmutableConfig mergedConfig = new BaseConfig<>().merge(config).merge(createConfig());

        Backend backend = getBackendFactory().createBackend(uri, mergedConfig);
        return StoreFactory.getInstance().createStore(backend, mergedConfig);
    }

    /**
     * Creates a new configuration.
     *
     * @return a new configuration
     */
    @Nonnull
    protected abstract ImmutableConfig createConfig();

    /**
     * Returns the {@link BackendFactory} associated with this adapter.
     *
     * @return the backend factory
     */
    @Nonnull
    protected final BackendFactory getBackendFactory() {
        final FactoryBinding annotation = createConfig().getClass().getAnnotation(FactoryBinding.class);
        checkState(nonNull(annotation), "Cannot retrieve the %s instance for this adapter", BackendFactory.class.getSimpleName());

        try {
            final Class<? extends BackendFactory> factoryType = annotation.factory();
            return factoryType.getConstructor().newInstance();
        }
        catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw Throwables.shouldNeverHappen(e);
        }
    }

    /**
     * Returns the {@link UriFactory} associated with this adapter.
     *
     * @return the URI factory
     */
    @Nonnull
    protected final UriFactory getUriFactory() {
        return UriFactory.forName(getBackendFactory().name());
    }
}
