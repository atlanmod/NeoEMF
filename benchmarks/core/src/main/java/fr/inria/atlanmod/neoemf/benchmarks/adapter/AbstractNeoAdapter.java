/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link Adapter} on top of a {@link fr.inria.atlanmod.neoemf.data.Backend}.
 */
@ParametersAreNonnullByDefault
abstract class AbstractNeoAdapter extends AbstractAdapter {

    /**
     * Constructs a new {@code AbstractNeoAdapter}.
     *
     * @param storeExtension the extension of the resource, used for benchmarks
     */
    protected AbstractNeoAdapter(String storeExtension) {
        super("neoemf", storeExtension + ".resource", org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.class);
    }

    /**
     * Returns the {@link BackendFactory} associated to this adapter.
     *
     * @return a factory
     */
    @Nonnull
    protected abstract BackendFactory getFactory();

    @Override
    public boolean supportsMapper() {
        return true;
    }

    @Override
    public DataMapper createMapper(File file, Config config) {
        Config mergedConfig = config.merge(getOptions());

        Backend backend = getFactory().createBackend(URI.createFileURI(file.getAbsolutePath()), mergedConfig);
        return StoreFactory.getInstance().createStore(backend, mergedConfig);
    }

    @Nonnull
    @Override
    public Resource createResource(File file, ResourceSet resourceSet) {
        return resourceSet.createResource(UriBuilder.forName(getFactory().name()).fromFile(file));
    }

    @Nonnull
    @Override
    public Resource load(File file, Config config) throws IOException {
        initAndGetEPackage();

        Resource resource = createResource(file, new ResourceSetImpl());
        resource.load(config.merge(getOptions()).toMap());

        return resource;
    }

    @Override
    public void unload(Resource resource) {
        if (resource.isLoaded()) {
            resource.unload();
        }
    }
}
