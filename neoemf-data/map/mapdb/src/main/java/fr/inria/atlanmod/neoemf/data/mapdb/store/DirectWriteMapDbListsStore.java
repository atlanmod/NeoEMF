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

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.map.core.MapBackend;
import fr.inria.atlanmod.neoemf.data.map.core.store.MapStoreWithLists;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import org.apache.commons.collections4.CollectionUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static java.util.Objects.isNull;

/**
 * A {@link DirectWriteMapDbStore} that uses Java {@link List}s instead of arrays to persist multi-valued
 * {@link EAttribute}s and {@link EReference}s.
 * <p>
 * Using a {@link List}-based implementation allows to benefit from the rich Java {@link Collection} API, with the cost
 * of a small memory overhead compared to raw arrays.
 * <p>
 * This class re-implements {@link EStructuralFeature} accessors and mutators as well as {@link Collection} operations
 * such as {@code size}, {@code clear}, or {@code indexOf}.
 * <p>
 * This store can be used as a base store that can be complemented by plugging decorator stores on top of it
 * (see {@link AbstractPersistentStoreDecorator} subclasses) to provide additional features such as caching or logging.
 *
 * @see DirectWriteMapDbStore
 * @see MapDbPersistenceBackend
 * @see AbstractPersistentStoreDecorator
 */
public class DirectWriteMapDbListsStore extends MapStoreWithLists<MapBackend> {

    /**
     * Constructs a new {@code DirectWriteMapDbListsStore} between the given {@code resource} and the
     * {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteMapDbListsStore(Resource.Internal resource, MapDbPersistenceBackend backend) {
        super(resource, backend);
    }


}
