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

import static com.google.common.base.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

/**
 * A {@link DirectWriteMapDbStore} subclass that uses an internal cache to store persisted {@link Object}s
 * that are part of multi-valued {@link EReference}s to speed-up their access.
 * <p>
 * Using a cache avoids multiple {@link List} deserialization to retrieve the same element, which can be an important bottleneck
 * in terms of execution time and memory consumption if the underlying model contains very large {@link Collection}s.
 * <p>
 * This store can be used as a base store that can be complemented by plugging decorator stores on top of it
 * (see {@link AbstractPersistentStoreDecorator} subclasses) to provide additional features such as caching or logging.
 * 
 * @see DirectWriteMapDbStore
 * @see MapDbPersistenceBackend
 * @see AbstractPersistentStoreDecorator
 */
public class DirectWriteMapDbCacheManyStore extends DirectWriteMapDbStore {

    private final Cache<FeatureKey, Object> objectsCache;

    /**
     * Constructs a new {@code DirectWriteMapDbCacheManyStore} between the given {@code resource} and the
     * {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend the persistence back-end used to store the model
     */
    public DirectWriteMapDbCacheManyStore(Resource.Internal resource, MapDbPersistenceBackend backend) {
        super(resource, backend);
        this.objectsCache = Caffeine.newBuilder().maximumSize(10000).build();
    }

    @Override
    protected void addReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        if (reference.isMany()) {
            FeatureKey featureKey = FeatureKey.from(object, reference);
            if (index == NO_INDEX) {
                /*
                 * Handle NO_INDEX index, which represent direct-append feature.
	             * The call to size should not cause an overhead because it would have been done in regular
	             * addUnique() otherwise.
	             */
                index = size(object, reference);
            }
            updateContainment(object, reference, value);
            updateInstanceOf(value);
            Object[] array = (Object[]) getFromMap(featureKey);
            if (isNull(array)) {
                array = new Object[]{};
            }
            checkPositionIndex(index, array.length, "Invalid add index " + index);
            array = ArrayUtils.add(array, index, value.id());
            objectsCache.put(featureKey, array);
            backend.storeValue(featureKey, array);
            persistentObjectsCache.put(value.id(), value);
        }
        else {
            super.addReference(object, reference, index, value);
        }
    }

    @Override
    protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        return objectsCache.get(featureKey, super::getFromMap);
    }
}
