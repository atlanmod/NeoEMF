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

import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithIndices;

import org.mapdb.DB;
import org.mapdb.Serializer;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * A {@link MapDbBackend} that use a {@link ManyValueWithIndices} mapping for storing features.
 *
 * @see MapDbBackendFactory
 */
@ParametersAreNonnullByDefault
class MapDbBackendIndices extends AbstractMapDbBackend implements ManyValueWithIndices {

    /**
     * A persistent map that store the values of multi-valued features for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the associated {@link ManyFeatureBean}.
     */
    @Nonnull
    private final Map<ManyFeatureBean, Object> manyFeatures;

    /**
     * Constructs a new {@code MapDbBackendIndices} wrapping the provided {@code db}.
     *
     * @param db the {@link DB} used to creates the used {@link Map}s and manage the database
     *
     * @see MapDbBackendFactory
     */
    @SuppressWarnings("unchecked")
    protected MapDbBackendIndices(DB db) {
        super(db);

        this.manyFeatures = db.hashMap("features/many")
                .keySerializer(new SerializerDecorator<>(SERIALIZER_FACTORY.forManyFeature()))
                .valueSerializer(Serializer.ELSA)
                .createOrOpen();
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key);

        return get(manyFeatures, key);
    }

    @Override
    public <V> void innerValueFor(ManyFeatureBean key, @Nullable V value) {
        checkNotNull(key);

        if (nonNull(value)) {
            put(manyFeatures, key, value);
        }
        else {
            delete(manyFeatures, key);
        }
    }
}
