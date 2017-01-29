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

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.annotations.VisibleForTesting;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.map.core.MapBackend;
import fr.inria.atlanmod.neoemf.data.mapdb.serializer.FeatureKeySerializer;
import fr.inria.atlanmod.neoemf.data.mapdb.serializer.IdSerializer;
import fr.inria.atlanmod.neoemf.data.mapdb.serializer.MultivaluedFeatureKeySerializer;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbCacheManyStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbIndicesStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbListsStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbStore;
import fr.inria.atlanmod.neoemf.data.structure.ClassInfo;
import fr.inria.atlanmod.neoemf.data.structure.ContainerInfo;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.util.Collection;
import java.util.Map;

/**
 * {@link PersistenceBackend} that is responsible of low-level access to a MapDB database.
 * <p>
 * It wraps an existing {@link DB} and provides facilities to create and retrieve elements. This class manages a set of
 * {@link Map}s used to represent model elements: <ul> <li><b>Containers Map: </b> holds containment and container links
 * between elements</li> <li><b>InstanceOf Map: </b> holds metaclass information for each element</li> <li><b>Features
 * Map: </b> holds non-containment {@link EStructuralFeature} links between elements </li> <li><b>Multi-valued Map: </b>
 * optional Map used in {@link DirectWriteMapDbIndicesStore} that stores {@link Collection} indices instead of a
 * serialized version of the collection itself</li> </ul>
 *
 * @note This class is used in {@link DirectWriteMapDbStore} and its subclasses to access and manipulate the database.
 * @note Instances of {@link MapDbPersistenceBackend} are created by {@link MapDbPersistenceBackendFactory} that
 * provides an usable {@link DB} that can be manipulated by this wrapper.
 * @see MapDbPersistenceBackendFactory
 * @see DirectWriteMapDbStore
 * @see DirectWriteMapDbListsStore
 * @see DirectWriteMapDbIndicesStore
 * @see DirectWriteMapDbCacheManyStore
 */
public class MapDbPersistenceBackend extends AbstractPersistenceBackend implements MapBackend {

    /**
     * The literal description of this back-end.
     */
    public static final String NAME = "mapdb";

    /**
     * ???
     */
    private static final String KEY_CONTAINER = "eContainer";

    /**
     * ???
     */
    private static final String KEY_INSTANCE_OF = "neoInstanceOf";

    /**
     * ???
     */
    private static final String KEY_FEATURES = "features";

    /**
     * ???
     */
    private static final String KEY_MULTIVALUED_FEATURES = "multivaluedFeatures";

    /**
     * The MapDB database.
     */
    private final DB db;

    /**
     * A persistent map that stores the container of {@link PersistentEObject}s, identified by the object {@link Id}.
     */
    private final HTreeMap<Id, ContainerInfo> containersMap;

    /**
     * A persistent map that stores the EClass for {@link PersistentEObject}s, identified by the object {@link Id}.
     */
    private final HTreeMap<Id, ClassInfo> instanceOfMap;

    /**
     * A persistent map that stores Structural feature values for {@link PersistentEObject}s, identified by the
     * associated {@link FeatureKey}.
     */
    private final HTreeMap<FeatureKey, Object> features;

    /**
     * A persistent map that store the values of multi-valued features for {@link PersistentEObject}s, identified by the
     * associated {@link MultivaluedFeatureKey}.
     */
    private final HTreeMap<MultivaluedFeatureKey, Object> multivaluedFeatures;

    /**
     * Constructs a new {@code MapDbPersistenceBackend} wrapping the provided {@code db}.
     * <p>
     * This constructor initialize the different {@link Map}s from the MapDB engine and set their respective
     * {@link Serializer}s.
     *
     * @param db the {@link DB} used to creates the used {@link Map}s and manage the database
     *
     * @note This constructor is protected. To create a new {@code MapDbPersistenceBackend} use {@link
     * MapDbPersistenceBackendFactory#createPersistentBackend(java.io.File, Map)}.
     * @see MapDbPersistenceBackendFactory
     */
    @SuppressWarnings("unchecked")
    protected MapDbPersistenceBackend(DB db) {

        this.db = db;

        containersMap = this.db.hashMap(KEY_CONTAINER)
                .keySerializer(new IdSerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        instanceOfMap = this.db.hashMap(KEY_INSTANCE_OF)
                .keySerializer(new IdSerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        features = this.db.hashMap(KEY_FEATURES)
                .keySerializer(new FeatureKeySerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        multivaluedFeatures = this.db.hashMap(KEY_MULTIVALUED_FEATURES)
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
        try {
            db.close();
        }
        catch (Exception e) {
            NeoLogger.warn(e);
        }
    }

    @Override
    public void save() {
        db.commit();
    }
    
    @Override
    public boolean isDistributed() {
        return false;
    }

    @Override
    @VisibleForTesting
    public Map<String, Object> getAll() {
        return db.getAll();
    }

    @Override
    @VisibleForTesting
    public <E> E get(String name) {
        return db.get(name);
    }

    @Override
    public ContainerInfo containerFor(Id id) {
        return containersMap.get(id);
    }

    @Override
    public void storeContainer(Id id, ContainerInfo container) {
        containersMap.put(id, container);
    }

    @Override
    public ClassInfo metaclassFor(Id id) {
        return instanceOfMap.get(id);
    }

    @Override
    public void storeMetaclass(Id id, ClassInfo metaclass) {
        instanceOfMap.put(id, metaclass);
    }

    @Override
    public Object storeValue(FeatureKey key, Object value) {
        return features.put(key, value);
    }

    @Override
    public Object valueOf(FeatureKey key) {
        return features.get(key);
    }

    @Override
    public Object removeFeature(FeatureKey key) {
        return features.remove(key);
    }

    @Override
    public boolean isFeatureSet(FeatureKey key) {
        return features.containsKey(key);
    }

    @Override
    public Object storeValueAtIndex(MultivaluedFeatureKey key, Object value) {
        return multivaluedFeatures.put(key, value);
    }

    @Override
    public Object valueAtIndex(MultivaluedFeatureKey key) {
        return multivaluedFeatures.get(key);
    }

    //@Override
    @SuppressWarnings({"unchecked", "rawtypes"}) // Unchecked cast: 'Map' to 'Map<...>'
    public void copyTo(MapDbPersistenceBackend target) {
        for (Map.Entry<String, Object> entry : db.getAll().entrySet()) {
            Object collection = entry.getValue();
            if (collection instanceof Map) {
                Map fromMap = (Map) collection;
                Map toMap = target.db.hashMap(entry.getKey()).createOrOpen();

                toMap.putAll(fromMap);
            }
            else {
                throw new UnsupportedOperationException("Cannot copy MapDB backend: store type " + collection.getClass().getSimpleName() + " is not supported");
            }
        }
    }
}
