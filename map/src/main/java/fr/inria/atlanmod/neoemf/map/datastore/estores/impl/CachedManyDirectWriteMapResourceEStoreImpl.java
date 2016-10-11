/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.map.datastore.estores.impl;

import static com.google.common.base.Preconditions.checkPositionIndex;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader.InvalidCacheLoadException;
import com.google.common.cache.CacheStats;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackend;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.resource.Resource;
import org.mapdb.Fun;
import org.mapdb.Fun.Tuple2;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class CachedManyDirectWriteMapResourceEStoreImpl extends DirectWriteMapResourceEStoreImpl {

	private static final long TIMER_PERIOD = 20000;

	private final Cache<Tuple2<Id, String>, Object> cachedArray;

	@SuppressWarnings("unchecked")
	public CachedManyDirectWriteMapResourceEStoreImpl(Resource.Internal resource, MapPersistenceBackend persistenceBackend) {
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
	protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
		Tuple2<Id, String> key = new Tuple2<>(object.id(), feature.getName());
		Object returnValue = null;
		try {
			returnValue = cachedArray.get(key, new CacheLoader(object, feature));
		} catch (ExecutionException e) {
			NeoLogger.warn(e.getCause());
		} catch (InvalidCacheLoadException e) {
//		    NeoLogger.warn(e.getCause());
		}
		return returnValue;
	}
	
	@Override
	protected void addWithReference(PersistentEObject object, EReference eReference,
	        int index, PersistentEObject value) {
	    if(eReference.isMany()) {
	        if(index == EStore.NO_INDEX) {
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
	        if (array == null) {
	            array = new Object[] {};
	        }
	        checkPositionIndex(index, array.length, "Invalid add index " + index);
	        array = ArrayUtils.add(array, index, value.id());
	        cachedArray.put(Fun.t2(object.id(), eReference.getName()), array);
	        features.put(Fun.t2(object.id(), eReference.getName()), array);
	        loadedEObjectsCache.put(value.id(),(PersistentEObject)value);
	    }
	    else {
	        super.addWithReference(object, eReference, index, value);
	    }
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
		    return CachedManyDirectWriteMapResourceEStoreImpl.super.getFromMap(persistentEObject, feature);
        }
	}
}
