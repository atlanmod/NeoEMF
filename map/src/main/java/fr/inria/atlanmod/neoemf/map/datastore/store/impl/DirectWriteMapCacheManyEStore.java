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

package fr.inria.atlanmod.neoemf.map.datastore.store.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader.InvalidCacheLoadException;
import com.google.common.cache.CacheStats;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackend;
import fr.inria.atlanmod.neoemf.map.datastore.store.impl.key.FeatureKey;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import static com.google.common.base.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

public class DirectWriteMapCacheManyEStore extends DirectWriteMapEStore {

    private static final long TIMER_PERIOD = 20000;

    private final Cache<FeatureKey, Object> cachedArray;

    public DirectWriteMapCacheManyEStore(Resource.Internal resource, MapPersistenceBackend persistenceBackend) {
        super(resource, persistenceBackend);
        cachedArray = CacheBuilder.newBuilder().softValues().recordStats().build();
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
            if (index == EStore.NO_INDEX) {
                /*
                 * Handle NO_INDEX index, which represent direct-append feature.
	             * The call to size should not cause an overhead because it would have been done in regular
	             * addUnique() otherwise.
	             */
                add(object, eReference, size(object, eReference), value);
            }
            updateContainment(object, eReference, value);
            updateInstanceOf(value);
            Object[] array = (Object[]) getFromMap(object, eReference);
            if (isNull(array)) {
                array = new Object[]{};
            }
            checkPositionIndex(index, array.length, "Invalid add index " + index);
            array = ArrayUtils.add(array, index, value.id());
            cachedArray.put(new FeatureKey(object.id(), eReference.getName()), array);
            persistenceBackend.storeValue(new FeatureKey(object.id(), eReference.getName()), array);
            loadedEObjectsCache.put(value.id(), value);
        }
        else {
            super.addReference(object, eReference, index, value);
        }
    }

    @Override
    protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
        FeatureKey key = new FeatureKey(object.id(), feature.getName());
        Object returnValue = null;
        try {
            returnValue = cachedArray.get(key, new CacheLoader(object, feature));
        }
        catch (ExecutionException e) {
            NeoLogger.warn(e.getCause());
        }
        catch (InvalidCacheLoadException ignore) {
//		    NeoLogger.warn(e.getCause());
        }
        return returnValue;
    }

    private class CacheLoader implements Callable<Object> {

        private final PersistentEObject persistentEObject;
        private final EStructuralFeature feature;

        public CacheLoader(PersistentEObject persistentEObject, EStructuralFeature feature) {
            this.persistentEObject = persistentEObject;
            this.feature = feature;
        }

        @Override
        public Object call() throws Exception {
            return DirectWriteMapCacheManyEStore.super.getFromMap(persistentEObject, feature);
        }
    }
}
