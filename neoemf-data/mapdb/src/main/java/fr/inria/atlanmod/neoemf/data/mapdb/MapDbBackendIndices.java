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

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithIndices;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;

import org.mapdb.DB;
import org.mapdb.Serializer;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * A {@link MapDbBackend} that use a {@link ManyValueWithIndices} mapping for storing features.
 *
 * @see MapDbBackendFactory
 */
@ParametersAreNonnullByDefault
class MapDbBackendIndices extends AbstractMapDbBackend implements ManyValueWithIndices {

    /**
     * A persistent map that store the values of multi-valued features for {@link PersistentEObject}s,
     * identified by the associated {@link ManyFeatureKey}.
     */
    @Nonnull
    private final ConcurrentMap<ManyFeatureKey, Object> multivaluedFeatures;

    /**
     * Constructs a new {@code MapDbBackendIndices} wrapping the provided {@code db}.
     * <p>
     * This constructor initialize the different {@link java.util.concurrent.ConcurrentMap}s from the MapDB engine and
     * set their respective {@link Serializer}s.
     * <p>
     * <b>Note:</b> This constructor is protected. To create a new {@link MapDbBackend} use {@link
     * BackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, java.util.Map)}.
     *
     * @param db the {@link DB} used to creates the used {@link java.util.concurrent.ConcurrentMap}s and manage the
     *           database
     *
     * @see MapDbBackendFactory
     */
    @SuppressWarnings("unchecked")
    protected MapDbBackendIndices(DB db) {
        super(db);

        multivaluedFeatures = db.hashMap("multivaluedFeatures")
                .keySerializer(Serializer.JAVA)
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        checkNotNull(key);

        return get(multivaluedFeatures, key);
    }

    @Override
    public <V> void safeValueFor(ManyFeatureKey key, @Nullable V value) {
        checkNotNull(key);

        if (nonNull(value)) {
            put(multivaluedFeatures, key, value);
        }
        else {
            delete(multivaluedFeatures, key);
        }
    }

    @Override
    public void copyTo(DataMapper target) {
        checkArgument(target instanceof MapDbBackendIndices);
        super.copyTo(target);
    }
}
