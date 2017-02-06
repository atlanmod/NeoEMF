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
import fr.inria.atlanmod.neoemf.data.map.core.MapBackend;
import fr.inria.atlanmod.neoemf.data.mapdb.serializer.FeatureKeySerializer;
import fr.inria.atlanmod.neoemf.data.mapdb.serializer.IdSerializer;
import fr.inria.atlanmod.neoemf.data.structure.ContainerValue;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassValue;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.util.Map;

/**
 * An abstract {@link fr.inria.atlanmod.neoemf.data.PersistenceBackend} that is responsible of low-level access to a
 * MapDB database.
 */
public abstract class AbstractMapDbBackend extends AbstractPersistenceBackend implements MapDbBackend {

    /**
     * The literal description of this back-end.
     */
    public static final String NAME = "mapdb";

    /**
     * A persistent map that stores the container of {@link PersistentEObject}s, identified by the object {@link Id}.
     */
    protected final HTreeMap<Id, ContainerValue> containersMap;

    /**
     * A persistent map that stores the EClass for {@link PersistentEObject}s, identified by the object {@link Id}.
     */
    protected final HTreeMap<Id, MetaclassValue> instanceOfMap;

    /**
     * A persistent map that stores Structural feature values for {@link PersistentEObject}s, identified by the
     * associated {@link FeatureKey}.
     */
    protected final HTreeMap<FeatureKey, Object> features;

    /**
     * The MapDB database.
     */
    private final DB db;

    /**
     * Constructs a new {@code AbstractMapDbBackend} wrapping the provided {@code db}.
     * <p>
     * This constructor initialize the different {@link Map}s from the MapDB engine and set their respective
     * {@link Serializer}s.
     *
     * @param db the {@link DB} used to creates the used {@link Map}s and manage the database
     *
     * @note This constructor is protected. To create a new {@code AbstractMapDbBackend} use {@link
     * MapDbBackendFactory#createPersistentBackend(java.io.File, Map)}.
     * @see MapDbBackendFactory
     */
    @SuppressWarnings("unchecked")
    protected AbstractMapDbBackend(DB db) {
        this.db = db;

        containersMap = db.hashMap("eContainer")
                .keySerializer(new IdSerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        instanceOfMap = db.hashMap("neoInstanceOf")
                .keySerializer(new IdSerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        features = db.hashMap("features")
                .keySerializer(new FeatureKeySerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();
    }

    @Override
    public void save() {
        db.commit();
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
    public boolean isClosed() {
        return db.isClosed();
    }

    @Override
    public boolean isDistributed() {
        return false;
    }

    @Override
    public void create(Id id) {
        // Do nothing
    }

    @Override
    public boolean has(Id id) {
        return false;
    }

    @VisibleForTesting
    public Map<String, Object> getAll() {
        return db.getAll();
    }

    @VisibleForTesting
    public <E> E get(String name) {
        return db.get(name);
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <P extends MapDbBackend> void copyTo(P target) {
        AbstractMapDbBackend backend = (AbstractMapDbBackend) target;

        for (Map.Entry<String, Object> entry : db.getAll().entrySet()) {
            Object collection = entry.getValue();
            if (collection instanceof Map) {
                Map fromMap = (Map) collection;
                Map toMap = backend.db.hashMap(entry.getKey()).createOrOpen();

                toMap.putAll(fromMap);
            }
            else {
                throw new UnsupportedOperationException("Cannot copy MapDB backend: store type " + collection.getClass().getSimpleName() + " is not supported");
            }
        }
    }
}
