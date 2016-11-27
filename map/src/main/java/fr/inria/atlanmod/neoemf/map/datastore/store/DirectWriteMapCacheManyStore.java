/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.map.datastore.store;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.stats.CacheStats;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.datastore.store.cache.FeatureKey;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackend;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.Timer;
import java.util.TimerTask;

import static com.google.common.base.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

public class DirectWriteMapCacheManyStore extends DirectWriteMapStore {

    // TODO Find the more predictable maximum cache size
    private static final int DEFAULT_CACHE_SIZE = 10000;

    private static final long TIMER_PERIOD = 20000;

    private final Cache<FeatureKey, Object> cachedArray;

    public DirectWriteMapCacheManyStore(Resource.Internal resource, MapPersistenceBackend persistenceBackend) {
        super(resource, persistenceBackend);
        cachedArray = Caffeine.newBuilder().maximumSize(DEFAULT_CACHE_SIZE).recordStats().build();
        new Timer(true).scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                CacheStats cacheStats = cachedArray.stats();
                NeoLogger.info("Founds: {0} / Not Founds: {1}", cacheStats.hitCount(), cacheStats.missCount());
            }
        }, 0, TIMER_PERIOD);
    }

    @Override
    protected void addReference(PersistentEObject object, EReference eReference, int index, PersistentEObject value) {
        if (eReference.isMany()) {
            FeatureKey featureKey = FeatureKey.from(object, eReference);
            if (index == NO_INDEX) {
                /*
                 * Handle NO_INDEX index, which represent direct-append feature.
	             * The call to size should not cause an overhead because it would have been done in regular
	             * addUnique() otherwise.
	             */
                add(object, eReference, size(object, eReference), value);
            }
            updateContainment(object, eReference, value);
            updateInstanceOf(value);
            Object[] array = (Object[]) getFromMap(featureKey);
            if (isNull(array)) {
                array = new Object[]{};
            }
            checkPositionIndex(index, array.length, "Invalid add index " + index);
            array = ArrayUtils.add(array, index, value.id());
            cachedArray.put(featureKey, array);
            persistenceBackend.storeValue(featureKey, array);
            loadedEObjects.put(value.id(), value);
        }
        else {
            super.addReference(object, eReference, index, value);
        }
    }

    @Override
    protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        Object returnValue = null;
        try {
            returnValue = cachedArray.get(featureKey, super::getFromMap);
        }
        catch (Exception e) {
            NeoLogger.warn(e);
        }
        return returnValue;
    }
}
