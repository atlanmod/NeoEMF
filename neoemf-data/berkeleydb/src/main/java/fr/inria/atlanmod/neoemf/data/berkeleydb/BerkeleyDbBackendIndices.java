/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.Environment;

import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithIndices;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;
import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A {@link BerkeleyDbBackend} that use a {@link ManyValueWithIndices} mapping for storing features.
 *
 * @see BerkeleyDbBackendFactory
 */
@ParametersAreNonnullByDefault
class BerkeleyDbBackendIndices extends AbstractBerkeleyDbBackend implements ManyValueWithIndices {

    /**
     * A persistent map that stores many-feature values for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the associated {@link ManyFeatureBean}.
     */
    @Nonnull
    private final Database manyFeatures;

    /**
     * Constructs a new {@code BerkeleyDbBackendIndices} wrapping the provided {@code environment}.
     *
     * @param environment    the database environment
     * @param databaseConfig the database configuration
     *
     * @see BerkeleyDbBackendFactory
     */
    protected BerkeleyDbBackendIndices(Environment environment, DatabaseConfig databaseConfig) {
        super(environment, databaseConfig);

        this.manyFeatures = environment.openDatabase(null, "features/many", databaseConfig);
    }

    @Override
    protected void internalClose() {
        manyFeatures.close();
        super.internalClose();
    }

    @Override
    protected void internalCopyTo(DataMapper target) {
        super.internalCopyTo(target);

        BerkeleyDbBackendIndices to = BerkeleyDbBackendIndices.class.cast(target);
        this.copy(manyFeatures, to.manyFeatures);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        return get(manyFeatures, feature, SERIALIZER_FACTORY.forManyFeature(), SERIALIZER_FACTORY.forAny());
    }

    @Override
    public <V> void valueForNullable(ManyFeatureBean feature, @Nullable V value) {
        checkNotNull(feature, "feature");

        if (nonNull(value)) {
            put(manyFeatures, feature, value, SERIALIZER_FACTORY.forManyFeature(), SERIALIZER_FACTORY.forAny());
        }
        else {
            delete(manyFeatures, feature, SERIALIZER_FACTORY.forManyFeature());
        }
    }
}
