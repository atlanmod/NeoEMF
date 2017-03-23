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

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.option.CommonOptions;

import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;

/**
 * An adapter on top of a {@code Resource} manager.
 */
public interface Adapter {

    /**
     * Creates a resource from the given {@code name} and returns its path.
     *
     * @return the resource file
     */
    File getOrCreateResource(String name) throws Exception;

    /**
     * Creates a datastore from the given {@code file} in the default location.
     *
     * @return the datastore location
     */
    File getOrCreateStore(File file) throws Exception;

    /**
     * Creates a datastore from the given {@code file} in a temporary location.
     *
     * @return the datastore location
     */
    @SuppressWarnings("UnusedReturnValue")
    File createTempStore(File file) throws Exception;

    /**
     * Loads a resource file from the given {@code file}.
     *
     * @return the loaded resource
     */
    Resource load(File file, CommonOptions options) throws Exception;

    /**
     * Saves the given {@code resource}.
     */
    void save(Resource resource) throws Exception;

    /**
     * Unloads the given {@code resource}.
     */
    void unload(Resource resource) throws Exception;

    /**
     * Copies a datastore from the {@code file} to a temporary location.
     *
     * @return the location of the new datastore
     */
    File copy(File file) throws Exception;
}
