/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;
import fr.inria.atlanmod.neoemf.util.UriFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.File;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkState;
import static java.util.Objects.nonNull;

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

    /**
     * Creates a new configuration.
     *
     * @return a new configuration
     */
    @Nonnull
    protected abstract ImmutableConfig createConfig();

    /**
     * Returns the {@link BackendFactory} associated to this adapter.
     *
     * @return a factory
     */
    @Nonnull
    protected final BackendFactory getFactory() {
        final FactoryBinding annotation = createConfig().getClass().getAnnotation(FactoryBinding.class);
        checkState(nonNull(annotation), "Cannot retrieve the %s instance for this adapter", BackendFactory.class.getSimpleName());

        try {
            final Class<? extends BackendFactory> factoryType = annotation.factory();
            return factoryType.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) {
            throw Throwables.shouldNeverHappen(e);
        }
    }

    @Override
    public boolean supportsMapper() {
        return true;
    }

    @Nonnull
    @Override
    public DataMapper createMapper(File file, ImmutableConfig config) {
        ImmutableConfig mergedConfig = new BaseConfig<>().merge(config).merge(createConfig());

        Backend backend = getFactory().createBackend(URI.createFileURI(file.getAbsolutePath()), mergedConfig);
        return StoreFactory.getInstance().createStore(backend, mergedConfig);
    }

    @Nonnull
    @Override
    public Resource createResource(File file) {
        URI uri = UriFactory.forName(getFactory().name()).createLocalUri(file);
        return new ResourceSetImpl().createResource(uri);
    }

    @Nonnull
    @Override
    protected final Map<String, ?> getOptions(ImmutableConfig config) {
        return new BaseConfig<>().merge(config).merge(createConfig()).toMap();
    }
}
