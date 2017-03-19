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

import fr.inria.atlanmod.neoemf.benchmarks.datastore.Backend;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.eclipse.emf.ecore.resource.Resource;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.TearDown;

import java.io.File;
import java.util.Objects;

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
     * The location of the current {@link Backend}.
     */
    protected File storeFile;

    /**
     * Returns the current resource loaded from the datastore.
     */
    public Resource getResource() {
        if (Objects.isNull(resource)) {
            throw new NullPointerException();
        }
        return resource;
    }

    /**
     * Returns the location of the current {@link Backend}.
     *
     * @return the location of the current {@link Backend}.
     */
    protected File getStoreLocation() {
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
        storeFile = getBackend().getOrCreateStore(getResourceFile());

        Log.info("Loading the resource");
        resource = getBackend().load(getStoreLocation(), getOptions());
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
            getBackend().unload(resource);
            resource = null;
        }
        Backend.clean();
    }
}
