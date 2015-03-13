/*******************************************************************************
 * Copyright (c) 2014 Abel Gómez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel Gómez - initial API and implementation
 ******************************************************************************/
package fr.inria.atlanmod.kyanos.datastore.estores.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.jboss.util.collection.SoftValueHashMap;
import org.mapdb.DB;
import org.mapdb.Fun.Tuple2;

import fr.inria.atlanmod.kyanos.core.KyanosEObject;


public class CachedManyDirectWriteMapResourceEStoreImpl extends DirectWriteMapResourceEStoreImpl {

	class InfoThread extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					sleep(20000);
				} catch (InterruptedException e) {
				}
				System.err.println("Founds: " + founds + " / Not Founds: " + notFounds);
			}
		}
	}
	
	protected Map<Tuple2<String, String>, Object> cachedArray;
	protected long founds;
	protected long notFounds;
	protected InfoThread thread;
	
	public CachedManyDirectWriteMapResourceEStoreImpl(Internal resource, DB db) {
		super(resource, db);
		cachedArray = new SoftValueHashMap();
		thread = new InfoThread();
		thread.setDaemon(true);
		thread.start();
	}
	
	@Override
	protected Object getFromMap(KyanosEObject object, EStructuralFeature feature) {
		Tuple2<String, String> key = new Tuple2<String, String>(object.kyanosId(), feature.getName());
		Object returnValue = cachedArray.get(key);
		if (returnValue == null) {
			returnValue = super.getFromMap(object, feature);
			cachedArray.put(key, returnValue);
			notFounds++;
		} else {
			founds++;
		}
		return returnValue;
	}
}
