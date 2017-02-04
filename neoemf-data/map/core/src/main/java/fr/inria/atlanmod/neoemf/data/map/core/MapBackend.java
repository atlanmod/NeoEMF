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

package fr.inria.atlanmod.neoemf.data.map.core;

import fr.inria.atlanmod.neoemf.annotations.VisibleForTesting;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteMapStore;

import java.util.Collection;
import java.util.Map;

/**
 * An adapter on top of a map-based database that provides specific methods for communicating with the database that it
 * uses. Each {@code MapBackend} manage one single instance of a database.
 * <p>
 * It does not provide model-level translation; these functions are handled by {@link DirectWriteMapStore}s.
 *
 * @see DirectWriteMapStore
 */
public interface MapBackend extends PersistenceBackend {

    /**
     * Returns all the {@link Collection}s contained in the database.
     *
     * @return a {@link Map} containing all the {@link Collection}s contained in the database and their associated names
     */
    @VisibleForTesting
    Map<String, Object> getAll();

    /**
     * ???
     *
     * @param name ???
     * @param <E>  ???
     *
     * @return ???
     */
    @VisibleForTesting
    <E> E get(String name);
}
