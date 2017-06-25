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

package fr.inria.atlanmod.neoemf.data.berkeleydb.context;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.option.BerkeleyDbOptions;

/**
 * A specific {@link Context} for the BerkeleyDB implementation.
 */
@FunctionalInterface
public interface BerkeleyDbContext extends Context {

    /**
     * Creates a new {@code BerkeleyDbContext} with a mapping with indices.
     *
     * @return a new context.
     */
    static Context getWithIndices() {
        return (BerkeleyDbContext) () -> BerkeleyDbOptions.builder().withIndices();
    }

    /**
     * Creates a new {@code BerkeleyDbContext} with a mapping with arrays.
     *
     * @return a new context.
     */
    static Context getWithArrays() {
        return (BerkeleyDbContext) () -> BerkeleyDbOptions.builder().withArrays();
    }

    /**
     * Creates a new {@code BerkeleyDbContext} with a mapping with lists.
     *
     * @return a new context.
     */
    static Context getWithLists() {
        return (BerkeleyDbContext) () -> BerkeleyDbOptions.builder().withLists();
    }

    /**
     * Creates a new {@code BerkeleyDbContext} with a mapping with maps.
     *
     * @return a new context.
     */
    static Context getWithMaps() {
        return (BerkeleyDbContext) () -> BerkeleyDbOptions.builder().withMaps();
    }

    @Override
    default String name() {
        return "BerkeleyDB";
    }

    @Override
    default BackendFactory factory() {
        return BerkeleyDbBackendFactory.getInstance();
    }
}
