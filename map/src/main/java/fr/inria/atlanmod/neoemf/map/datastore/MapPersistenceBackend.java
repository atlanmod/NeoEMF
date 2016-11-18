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

package fr.inria.atlanmod.neoemf.map.datastore;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.store.impl.cache.FeatureKey;
import fr.inria.atlanmod.neoemf.datastore.store.impl.cache.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.datastore.store.impl.info.ClassInfo;
import fr.inria.atlanmod.neoemf.datastore.store.impl.info.ContainerInfo;
import fr.inria.atlanmod.neoemf.map.datastore.store.impl.serializer.FeatureKeySerializer;
import fr.inria.atlanmod.neoemf.map.datastore.store.impl.serializer.IdSerializer;
import fr.inria.atlanmod.neoemf.map.datastore.store.impl.serializer.MultivaluedFeatureKeySerializer;

import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.util.Map;

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
    private final HTreeMap<Id, ClassInfo> instanceOfMap;

    /**
     * A persistent map that stores Structural feature values for persistent EObjects.
     * The key is build using the persistent object Id plus the name of the feature.
     */
    private final HTreeMap<FeatureKey, Object> features;

    /**
     * A persistent map that store the values of multivalued features for persistent EObjects.
     * The key is build using the persistent object Id plus the name of the feature plus the index of the value.
     */
    private final HTreeMap<MultivaluedFeatureKey, Object> multivaluedFeatures;

    @SuppressWarnings("unchecked") // Unchecked cast: 'HTreeMap' to 'HTreeMap<...>'
    MapPersistenceBackend(DB aDB) {
        db = aDB;

        containersMap = db.hashMap(CONTAINER)
                .keySerializer(new IdSerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        instanceOfMap = db.hashMap(INSTANCE_OF)
                .keySerializer(new IdSerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        features = db.hashMap(FEATURES)
                .keySerializer(new FeatureKeySerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        multivaluedFeatures = db.hashMap(MULTIVALUED_FEATURES)
                .keySerializer(new MultivaluedFeatureKeySerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();
    }

    @Override
    public boolean isClosed() {
        return db.isClosed();
    }

    @Override
    public void close() {
        db.close();
    }

    @Override
    public void save() {
        db.commit();
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
     */
    public ContainerInfo containerFor(Id id) {
        return containersMap.get(id);
    }

    /**
     * Stores containter information for an object id
     */
    public void storeContainer(Id id, ContainerInfo container) {
        containersMap.put(id, container);
    }

    /**
     * Retrieves metaclass (EClass) for a given object id
     */
    public ClassInfo metaclassFor(Id id) {
        return instanceOfMap.get(id);
    }

    /**
     * Stores metaclass (EClass) information for an object id.
     */
    public void storeMetaclass(Id id, ClassInfo metaclass) {
        instanceOfMap.put(id, metaclass);
    }

    /**
     * Store the value of a given feature.
     */
    public Object storeValue(FeatureKey key, Object value) {
        return features.put(key, value);
    }

    /**
     * Retrieves the value of a given feature.
     */
    public Object valueOf(FeatureKey key) {
        return features.get(key);
    }

    /**
     * Removes the value of a given feature. The feature becomes unset.
     */
    public Object removeFeature(FeatureKey key) {
        return features.remove(key);
    }

    /**
     * Return true if the feature was set, false otherwise.
     */
    public boolean isFeatureSet(FeatureKey key) {
        return features.containsKey(key);
    }

    /**
     * Stores the single value of a given multivalued feature at the given index.
     */
    public Object storeValueAtIndex(MultivaluedFeatureKey key, Object value) {
        return multivaluedFeatures.put(key, value);
    }

    /**
     * Retrieves the value of a given multivalued feature at a given index.
     */
    public Object valueAtIndex(MultivaluedFeatureKey key) {
        return multivaluedFeatures.get(key);
    }

    /**
     * Copies all the contents of this backend to the target one.
     */
    @SuppressWarnings({"unchecked", "rawtypes"}) // Unchecked cast: 'Map' to 'Map<...>'
    public void copyTo(MapPersistenceBackend target) {
        for (Map.Entry<String, Object> entry : db.getAll().entrySet()) {
            Object collection = entry.getValue();
            if (collection instanceof Map) {
                Map fromMap = (Map) collection;
                Map toMap = target.db.hashMap(entry.getKey()).createOrOpen();

                toMap.putAll(fromMap);
            }
            else {
                throw new UnsupportedOperationException("Cannot copy Map backend: store type " + collection.getClass().getSimpleName() + " is not supported");
            }
        }
    }
}
