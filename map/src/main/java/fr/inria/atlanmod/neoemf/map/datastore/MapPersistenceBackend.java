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

package fr.inria.atlanmod.neoemf.map.datastore;

import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;

import org.eclipse.emf.ecore.EClass;
import org.mapdb.DB;
import org.mapdb.Engine;

import java.util.Map;

public class MapPersistenceBackend extends DB implements PersistenceBackend {
	
	public MapPersistenceBackend(Engine mapEngine) {
		super(mapEngine);
	}
	
	@Override
	public void start(Map<?, ?> options) throws InvalidDataStoreException {

	}

	@Override
	public boolean isStarted() {
	    return !isClosed();
	}

	@Override
	public void stop() {
		close();
	}

	@Override
	public void save() {
		commit();
	}
	
	@Override
	public Object getAllInstances(EClass eClass, boolean strict) {
		throw new UnsupportedOperationException("MapDB backend does not support custom all instances computation");
	}
}
