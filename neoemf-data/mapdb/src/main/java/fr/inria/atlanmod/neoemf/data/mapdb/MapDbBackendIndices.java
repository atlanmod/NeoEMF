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

import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.util.serializer.MultiFeatureKeySerializer;
import fr.inria.atlanmod.neoemf.data.mapper.MultiValueWithIndices;
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * {@link PersistenceBackend} that is responsible of low-level access to a MapDB database.
 * <p>
 * It wraps an existing {@link DB} and provides facilities to create and retrieve elements. This class manages a set of
 * {@link Map}s used to represent model elements: <ul> <li><b>Containers Map: </b> holds containment and container links
 * between elements</li> <li><b>InstanceOf Map: </b> holds metaclass information for each element</li> <li><b>Features
 * Map: </b> holds non-containment {@link EStructuralFeature} links between elements </li> <li><b>Multi-valued Map: </b>
 * optional Map used in {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore} that stores {@link Collection}
 * indices instead of a serialized version of the collection itself</li> </ul>
 *
 * @note This class is used in {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore} and its subclasses to access
 * and manipulate the database.
 * @note Instances of {@link MapDbBackendIndices} are created by {@link MapDbBackendFactory} that provides an usable
 * {@link DB} that can be manipulated by this wrapper.
 * @see MapDbBackendFactory
 * @see fr.inria.atlanmod.neoemf.data.store.DirectWriteStore
 */
@ParametersAreNonnullByDefault
class MapDbBackendIndices extends AbstractMapDbBackend implements MultiValueWithIndices {

    /**
     * A persistent map that store the values of multi-valued features for {@link fr.inria.atlanmod.neoemf.core.Id}s,
     * identified by the associated {@link MultiFeatureKey}.
     */
    @Nonnull
    private final HTreeMap<MultiFeatureKey, Object> multivaluedFeatures;

    /**
     * Constructs a new {@code MapDbBackendIndices} wrapping the provided {@code db}.
     * <p>
     * This constructor initialize the different {@link Map}s from the MapDB engine and set their respective
     * {@link Serializer}s.
     *
     * @param db the {@link DB} used to creates the used {@link Map}s and manage the database
     *
     * @note This constructor is protected. To create a new {@code MapDbBackendIndices} use {@link
     * PersistenceBackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, Map)}.
     * @see MapDbBackendFactory
     */
    @SuppressWarnings("unchecked")
    protected MapDbBackendIndices(DB db) {
        super(db);

        multivaluedFeatures = db.hashMap("multivaluedFeatures")
                .keySerializer(new MultiFeatureKeySerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(MultiFeatureKey key) {
        return fromDatabase(multivaluedFeatures, key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(MultiFeatureKey key, V value) {
        Optional<V> previousValue = valueOf(key);

        toDatabase(multivaluedFeatures, key, value);

        return previousValue;
    }
}
