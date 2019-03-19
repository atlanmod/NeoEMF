/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.data.store;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface StoreCreator {

    /**
     * Retrieves or creates a new {@link Resource} (a {@link fr.inria.atlanmod.neoemf.resource.PersistentResource} in
     * case of NeoEMF) from the given {@code file}, and stores it to the given {@code targetAdapter}, located in {@code
     * dir}.
     *
     * @param resourceFile the resource file
     * @param uri          the location of the adapter
     *
     * @throws IOException if a error occurs during the creation of the store
     */
    void create(File resourceFile, URI uri, ImmutableConfig config) throws IOException;
}
