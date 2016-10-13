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
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.FeatureKey;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.pojo.ContainerInfo;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.pojo.EClassInfo;
import org.eclipse.emf.ecore.EClass;
import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.util.Map;

/**
 * Persistence Backend for MapDB databases
 *
 * @author sunye
 */
public class MapPersistenceBackend implements PersistenceBackend {

    private static final String CONTAINER = "eContainer";
    private static final String INSTANCE_OF = "neoInstanceOf";
    private static final String FEATURES = "features";
    private static final String MULTIVALUED_FEATURES = "multivaluedFeatures";

    private final DB db;

    /**
     * A persistent map that stores the container of persistent EObjects.
     */
    private final HTreeMap<Id, ContainerInfo> containersMap;

    /**
     * A persistent map that stores the EClass for persistent EObjects.
     * The key is the persistent object Id.
     */
    private final HTreeMap<Id, EClassInfo> instanceOfMap;

    /**
     * A persistent map that stores Structural feature values for persistent EObjects.
     * The key is build using the persistent object Id plus the name of the feature.
     */
    private final Map<FeatureKey, Object> features;

    /**
     * A persistent map that store the values of multivalued features for persistent EObjects.
     * The key is build using the persistent object Id plus the name of the feature plus the index of the value.
     *
     */
    private final Map<MultivaluedFeatureKey, Object> multivaluedFeatures;


    public MapPersistenceBackend(DB aDB) {
        db = aDB;
        containersMap = db.hashMap(CONTAINER)
                .keySerializer(Serializer.JAVA)
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();
        instanceOfMap = db.hashMap(INSTANCE_OF)
                .keySerializer(Serializer.JAVA)
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();
        features = db.hashMap(FEATURES)
                .keySerializer(Serializer.JAVA)
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();
        multivaluedFeatures = db.hashMap(MULTIVALUED_FEATURES)
                .keySerializer(Serializer.JAVA)
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();
    }

    @Override
    public void start(Map<?, ?> options) throws InvalidDataStoreException {

    }

    @Override
    public boolean isStarted() {
        return !db.isClosed();
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


    public DB.HashMapMaker<?, ?> hashMapMaker(String aString) {
        return db.hashMap(aString);
    }

    public Map<String, Object> getAll() {
        return db.getAll();
    }

    public <E> E get(String name) {
        return db.get(name);
    }

    /**
     * Retrieves container for a given object id.
     *
     * @param id
     * @return
     */
    public ContainerInfo containerFor(Id id) {
        return containersMap.get(id);
    }

    /**
     * Stores containter information for an object id
     *
     * @param id
     * @param container
     */
    public void storeContainer(Id id, ContainerInfo container) {
        containersMap.put(id, container);
    }

    /**
     * Retrieves metaclass (EClass) for a given object id
     *
     * @param id
     * @return
     */
    public EClassInfo metaclassFor(Id id) {
        return instanceOfMap.get(id);
    }

    /**
     * Stores metaclass (EClass) information for an object id.
     *
     * @param id
     * @param metaclass
     */
    public void storeMetaclass(Id id, EClassInfo metaclass) {
        instanceOfMap.put(id, metaclass);
    }

    /**
     * Store the value of a given feature.
     * @param key
     * @param value
     * @return
     */
    public Object storeValue(FeatureKey key, Object value) {
        return features.put(key,value);
    }

    /**
     * Retrieves the value of a given feature.
     * @param key
     * @return
     */
    public Object valueOf(FeatureKey key) {
        return features.get(key);
    }

    /**
     * Removes the value of a given feature. The feature becomes unset.
     * @param key
     * @return
     */
    public Object removeFeature(FeatureKey key) {
        return features.remove(key);
    }

    /**
     * Return true if the feature was set, false otherwise.
     * @param key
     * @return
     */
    public boolean isFeatureSet(FeatureKey key) {
        return features.containsKey(key);
    }

    /**
     * Stores the single value of a given multivalued feature at the given index.
     * @param key
     * @param value
     * @return
     */
    public Object storeValueAtIndex(MultivaluedFeatureKey key, Object value) {
        return multivaluedFeatures.put(key,value);
    }

    /**
     * Retrieves the value of a given multivalued feature at a given index.
     * @param key
     * @return
     */
    public Object valueAtIndex(MultivaluedFeatureKey key) {
        return multivaluedFeatures.get(key);
    }


    /**
     * Copies all the contents of this backend to the target one.
     * @param target
     */
    public void copyTo(MapPersistenceBackend target) {
        // TODO: Using the default serializer may not work if we use other serializers in the code.
        for(Map.Entry<String, Object> entry : db.getAll().entrySet()) {
            Object collection = entry.getValue();
            if(collection instanceof Map) {
                Map fromMap = (Map)collection;
                //Map toMap = target.db.hashMap(entry.getKey(),Serializer.JAVA, Serializer.JAVA).createOrOpen();
                Map toMap = target.db.hashMap(entry.getKey()).createOrOpen();

                toMap.putAll(fromMap);
            }
            else {
                throw new UnsupportedOperationException("Cannot copy Map backend: store type " + collection.getClass().getSimpleName() + " is not supported");
            }
        }


    }
}
