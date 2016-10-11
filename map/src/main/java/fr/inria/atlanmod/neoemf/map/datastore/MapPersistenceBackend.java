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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;

import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.pojo.ContainerInfo;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.pojo.EClassInfo;
import org.eclipse.emf.ecore.EClass;
import org.mapdb.DB;
import org.mapdb.Engine;
import org.mapdb.HTreeMap;

import java.util.Map;

/**
 * Persistence Backend for MapDB databases
 *
 * @author sunye
 */
public class MapPersistenceBackend implements PersistenceBackend {

	private static final String CONTAINER = "eContainer";
	private static final String INSTANCE_OF = "neoInstanceOf";

	private DB db;

	/**
	 * A persistent map that stores the container of persistent EObjects.
	 */
	private final Map<Id, ContainerInfo> containersMap;

	/**
	 * A persistent map that stores the EClass for persistent EObjects.
	 * The key is the persistent object Id.
	 */
	private final Map<Id, EClassInfo> instanceOfMap;
	
	public MapPersistenceBackend(Engine mapEngine) {
		db = new DB(mapEngine);
		containersMap = db.getHashMap(CONTAINER);
		instanceOfMap = db.getHashMap(INSTANCE_OF);
	}
	
	@Override
	public void start(Map<?, ?> options) throws InvalidDataStoreException {

	}

	@Override
	public boolean isStarted() {
	    return ! db.isClosed();
	}

	@Override
	public void stop() {
		db.close();
	}

	@Override
	public void save() {
		db.commit();
	}
	
	@Override
	public Object getAllInstances(EClass eClass, boolean strict) {
		throw new UnsupportedOperationException("MapDB backend does not support custom all instances computation");
	}


	public <K,V> HTreeMap<K,V> getHashMap(String aString) {
		return db.getHashMap(aString);
	}

	public Map<String,Object> getAll() {
		return db.getAll();
	}

	public  <E> E get(String name) {
		return db.get(name);
	}

	/**
	 * Retrieves container for a given object id.
	 * @param id
	 * @return
	 */
	public ContainerInfo containerFor(Id id) {
		return containersMap.get(id);
	}

	/**
	 * Stores containter information for an object id
	 * @param id
	 * @param container
	 */
	public void storeContainer(Id id, ContainerInfo container) {
		containersMap.put(id, container);
	}

	/**
	 * Retrieves metaclass (EClass) for a given object id
	 * @param id
	 * @return
	 */
	public EClassInfo metaclassFor(Id id) {
		return instanceOfMap.get(id);
	}

	/**
	 * Stores metaclass (EClass) information for an object id.
	 * @param id
	 * @param metaclass
	 */
	public void storeMetaclass(Id id, EClassInfo metaclass) {
		instanceOfMap.put(id, metaclass);
	}

}
