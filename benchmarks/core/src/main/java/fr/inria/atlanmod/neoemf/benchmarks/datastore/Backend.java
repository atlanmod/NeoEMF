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

package fr.inria.atlanmod.neoemf.benchmarks.datastore;

import fr.inria.atlanmod.neoemf.benchmarks.datastore.helper.Workspace;
import fr.inria.atlanmod.neoemf.option.PersistenceOptions;

import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;

public interface Backend {

    /**
     * Cleans temporary files from backends.
     */
    static void clean() {
        Workspace.cleanTempDirectory();
    }

    /**
     * Creates a resource from the given {@code resourceName} and returns its path.
     *
     * @return the resource file
     */
    File getOrCreateResource(String resourceName) throws Exception;

    /**
     * Creates a datastore from the given {@code resourceFile} in the default location.
     *
     * @return the datastore location
     */
    File getOrCreateStore(File resourceFile) throws Exception;

    /**
     * Creates a datastore from the given {@code resourceFile} in a temporary location.
     *
     * @return the datastore location
     */
    File createTempStore(File resourceFile) throws Exception;

    /**
     * Loads a resource file from the given {@code file}.
     *
     * @return the loaded resource
     */
    Resource load(File file, PersistenceOptions options) throws Exception;

    /**
     * Saves the given {@code resource}.
     */
    void save(Resource resource) throws Exception;

    /**
     * Unloads the given {@code resource}.
     */
    void unload(Resource resource) throws Exception;

    /**
     * Copies a datastore from the {@code storeLocation} to a temporary location.
     *
     * @return the location of the new datastore
     */
    File copy(File storeLocation) throws Exception;
}
