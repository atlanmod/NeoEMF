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

package fr.inria.atlanmod.neoemf.data.map.core.store;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.map.core.MapPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractDirectWriteStore;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * An abstract {@link AbstractDirectWriteStore} that redirects certain methods according to the instance of the
 * encountered {@link EStructuralFeature}. If the subclass does not re-implement the inherited methods of EMF, the call
 * is automatically redirected to the associated method that begins with the same name.
 *
 * @param <P> the type of the supported {@link PersistenceBackend}
 */
public abstract class MapDirectWriteStore<P extends MapPersistenceBackend> extends AbstractDirectWriteStore<P> {

    /**
     * The default cache size (10 000).
     */
    // TODO Find the more predictable maximum cache size
    private static final int DEFAULT_CACHE_SIZE = 10000;

    /**
     * In-memory cache that holds recently loaded {@link PersistentEObject}s, identified by their {@link Id}.
     */
    protected final Cache<Id, PersistentEObject> persistentObjectsCache = Caffeine.newBuilder()
            .maximumSize(DEFAULT_CACHE_SIZE).build();

    /**
     * Constructs a new {@code MapDirectWriteStore} between the given {@code resource} and the {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public MapDirectWriteStore(Resource.Internal resource, P backend) {
        super(resource, backend);
    }

    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        return backend.isFeatureSet(featureKey);
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        backend.removeFeature(featureKey);
    }
}
