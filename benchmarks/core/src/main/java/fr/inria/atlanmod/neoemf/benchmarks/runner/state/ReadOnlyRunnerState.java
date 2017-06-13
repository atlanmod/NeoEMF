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

package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.benchmarks.Workspace;

import org.eclipse.emf.ecore.resource.Resource;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.TearDown;

import java.io.File;
import java.util.Objects;

import javax.annotation.Nonnull;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * This state provided a ready-to-use datastore. It is automatically preloaded and unloaded from the default location.
 * <p/>
 * It is used for simple queries.
 */
public class ReadOnlyRunnerState extends RunnerState {

    /**
     * The current {@link Resource}.
     */
    protected Resource resource;

    /**
     * The location of the current {@link Adapter}.
     */
    protected File storeFile;

    /**
     * Returns the current resource loaded from the datastore.
     *
     * @throws NullPointerException if the resource has not been initialized
     */
    @Nonnull
    public Resource getResource() {
        checkNotNull(resource);

        return resource;
    }

    /**
     * Returns the location of the current {@link Adapter}.
     *
     * @return the location of the current {@link Adapter}.
     *
     * @throws NullPointerException if the store file has not been initialized
     */
    @Nonnull
    protected File getStoreLocation() {
        checkNotNull(storeFile);

        return storeFile;
    }

    /**
     * Loads and creates the current datastore and its resource.
     * <p/>
     * This method is automatically called when setup the iteration level.
     */
    @Setup(Level.Iteration)
    public void loadResource() throws Exception {
        Log.info("Initializing the datastore");
        storeFile = getAdapter().getOrCreateStore(getResourceFile());

        Log.info("Loading the resource");
        resource = getAdapter().load(getStoreLocation(), getOptions());
    }

    /**
     * Unloads the current resource.
     * <p/>
     * This method is automatically called when tear down the iteration level.
     */
    @TearDown(Level.Iteration)
    public void unloadResource() throws Exception {
        Log.info("Unloading the resource");
        if (!Objects.isNull(resource)) {
            getAdapter().unload(resource);
            resource = null;
        }

        Workspace.cleanTempDirectory();
    }
}
