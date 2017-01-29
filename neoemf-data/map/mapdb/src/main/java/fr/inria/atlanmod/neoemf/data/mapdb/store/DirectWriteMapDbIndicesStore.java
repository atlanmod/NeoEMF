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
import fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteMapStoreWithIndices;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.Collection;
import java.util.Collections;

/**
 * A {@link DirectWriteMapDbStore} that persists {@link Collection} indices instead of serialized arrays.
 * <p>
 * Indices are persisted with dedicated {@link FeatureKey}s containing the index of the element to
 * store. Using this approach avoid to deserialize entire {@link Collection}s to retrieve a single
 * element, which can be an important bottleneck in terms of execution time and memory consumption
 * if the underlying model contains very large {@link Collections}.
 * <p>
 * This class re-implements {@link EStructuralFeature} accessors and mutators as well as {@link Collection}
 * operations such as {@code size}, {@code clear}, or {@code indexOf}.
 * <p>
 * This store can be used as a base store that can be complemented by plugging decorator stores on top of it
 * (see {@link AbstractPersistentStoreDecorator} subclasses) to provide additional features such as caching or logging.
 *
 * @see DirectWriteMapDbStore
 * @see MapDbPersistenceBackend
 * @see AbstractPersistentStoreDecorator
 */
public class DirectWriteMapDbIndicesStore extends DirectWriteMapStoreWithIndices<MapBackend> {

    /**
     * Constructs a new {@code DirectWriteMapDbIndicesStore} between the given {@code resource} and the
     * {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteMapDbIndicesStore(Resource.Internal resource, MapDbPersistenceBackend backend) {
        super(resource, backend);
    }

}
