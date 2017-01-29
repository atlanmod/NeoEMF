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

package fr.inria.atlanmod.neoemf.data.mapdb.store;

import fr.inria.atlanmod.neoemf.data.map.core.MapBackend;
import fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteCachedMapStore;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkPositionIndex;

/**
 * A {@link DirectWriteMapDbStore} that uses an internal cache to store persisted {@link Object}s that are part
 * of multi-valued {@link EReference}s to speed-up their access.
 * <p>
 * Using a cache avoids multiple {@link List} deserialization to retrieve the same element, which can be an important
 * bottleneck in terms of execution time and memory consumption if the underlying model contains very large {@link
 * Collection}s.
 * <p>
 * This store can be used as a base store that can be complemented by plugging decorator stores on top of it (see {@link
 * AbstractPersistentStoreDecorator} subclasses) to provide additional features such as caching or logging.
 *
 * @see DirectWriteMapDbStore
 * @see MapDbPersistenceBackend
 * @see AbstractPersistentStoreDecorator
 */
public class DirectWriteMapDbCacheManyStore extends DirectWriteCachedMapStore<MapBackend> {

    /**
     * Constructs a new {@code DirectWriteMapDbCacheManyStore} between the given {@code resource} and the
     * {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteMapDbCacheManyStore(Resource.Internal resource, MapDbPersistenceBackend backend) {
        super(resource, backend);
    }

}
