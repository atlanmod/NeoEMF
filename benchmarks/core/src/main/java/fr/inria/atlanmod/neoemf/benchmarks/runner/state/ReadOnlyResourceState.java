/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.benchmarks.io.LocalWorkspace;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.TearDown;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link ResourceState} that provides a ready-to-use datastore. It is automatically preloaded and unloaded from the
 * default location.
 * <p/>
 * It is used for simple queries.
 */
@ParametersAreNonnullByDefault
public class ReadOnlyResourceState extends ResourceState {

    /**
     * The current {@link Resource}.
     */
    private Resource resource;

    /**
     * The location of the current {@link Adapter}.
     */
    private URI resourceUri;

    /**
     * Returns the current resource loaded from the datastore.
     */
    @Nonnull
    public Resource resource() {
        return resource;
    }

    /**
     * Returns the location of the current {@link Adapter}.
     */
    @Nonnull
    protected URI resourceUri() {
        return resourceUri;
    }

    /**
     * Loads and creates the current datastore and its resource.
     */
    @Setup(Level.Iteration)
    public void loadResource() throws IOException {
        resourceUri = adapter().getOrCreateStore(resourceFile(), baseConfig(), useDirectImport());
        resource = adapter().load(resourceUri(), baseConfig());
    }

    /**
     * Unloads the current resource.
     */
    @TearDown(Level.Iteration)
    public void unloadResource() {
        if (nonNull(resource)) {
            adapter().unload(resource);
            resource = null;
        }

        LocalWorkspace.cleanTempDirectory();
    }
}
