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

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.mapdb.DB;
import org.mapdb.Fun.Tuple2;

import java.util.Timer;
import java.util.TimerTask;

public class CachedManyDirectWriteMapResourceEStoreImpl extends DirectWriteMapResourceEStoreImpl {

	private static final long TIMER_PERIOD = 20000;

	private final Cache<Tuple2<Id, String>, Object> cachedArray;

	@SuppressWarnings("unchecked")
	public CachedManyDirectWriteMapResourceEStoreImpl(Internal resource, DB db) {
		super(resource, db);
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
		Object returnValue = cachedArray.getIfPresent(key);
		if (returnValue == null) {
			returnValue = super.getFromMap(object, feature);
			cachedArray.put(key, returnValue);
		}
		return returnValue;
	}
}
