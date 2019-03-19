/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithIndices;

import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;
import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A {@link MapDbBackend} that use a {@link ManyValueWithIndices} mapping for storing features.
 *
 * @see MapDbBackendFactory
 */
@ParametersAreNonnullByDefault
class MapDbBackendIndices extends AbstractMapDbBackend implements ManyValueWithIndices {

    /**
     * A persistent map that stores many-feature values for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the associated {@link ManyFeatureBean}.
     */
    @Nonnull
    private final HTreeMap<ManyFeatureBean, Object> manyFeatures;

    /**
     * Constructs a new {@code MapDbBackendIndices} wrapping the provided {@code database}.
     *
     * @param database the {@link org.mapdb.DB} used to creates the used {@link org.mapdb.HTreeMap}s and manage the
     *                 database
     *
     * @see MapDbBackendFactory
     */
    @SuppressWarnings("unchecked")
    protected MapDbBackendIndices(DB database) {
        super(database);

        manyFeatures = database.hashMap("features/many")
                .keySerializer(new SerializerDecorator<>(SERIALIZER_FACTORY.forManyFeature()))
                .valueSerializer(Serializer.ELSA)
                .createOrOpen();
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        return get(manyFeatures, feature);
    }

    @Override
    public <V> void valueForNullable(ManyFeatureBean feature, @Nullable V value) {
        checkNotNull(feature, "feature");

        if (nonNull(value)) {
            put(manyFeatures, feature, value);
        }
        else {
            delete(manyFeatures, feature);
        }
    }
}
