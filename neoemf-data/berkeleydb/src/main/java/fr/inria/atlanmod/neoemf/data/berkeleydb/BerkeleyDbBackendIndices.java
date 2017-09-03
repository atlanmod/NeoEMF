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

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.Environment;

import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithIndices;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * A {@link BerkeleyDbBackend} that use a {@link ManyValueWithIndices} mapping for storing features.
 *
 * @see BerkeleyDbBackendFactory
 */
@ParametersAreNonnullByDefault
class BerkeleyDbBackendIndices extends AbstractBerkeleyDbBackend implements ManyValueWithIndices {

    /**
     * A persistent map that store the values of multi-valued features for {@link fr.inria.atlanmod.neoemf.core.Id},
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
    public void copyTo(DataMapper target) {
        checkArgument(BerkeleyDbBackendIndices.class.isInstance(target));
        BerkeleyDbBackendIndices to = BerkeleyDbBackendIndices.class.cast(target);

        super.copyTo(target);
        this.copy(manyFeatures, to.manyFeatures);
    }

    @Nonnull
    @Override
    protected List<Database> activeDatabases() {
        List<Database> databases = super.activeDatabases();
        databases.add(manyFeatures);
        return databases;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key);

        return get(manyFeatures, key, SERIALIZER_FACTORY.forManyFeature(), SERIALIZER_FACTORY.forAny());
    }

    @Override
    public <V> void innerValueFor(ManyFeatureBean key, @Nullable V value) {
        checkNotNull(key);

        if (nonNull(value)) {
            put(manyFeatures, key, value, SERIALIZER_FACTORY.forManyFeature(), SERIALIZER_FACTORY.forAny());
        }
        else {
            delete(manyFeatures, key, SERIALIZER_FACTORY.forManyFeature());
        }
    }
}
