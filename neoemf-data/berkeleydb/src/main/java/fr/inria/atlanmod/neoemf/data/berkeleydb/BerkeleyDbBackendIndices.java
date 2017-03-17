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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithIndices;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * A {@link BerkeleyDbBackend} that use a {@link ManyValueWithIndices} mapping for storing features.
 *
 * @see BerkeleyDbBackendFactory
 */
@ParametersAreNonnullByDefault
class BerkeleyDbBackendIndices extends AbstractBerkeleyDbBackend implements ManyValueWithIndices {

    /**
     * A persistent map that store the values of multi-valued features for {@link Id}, identified by the associated
     * {@link ManyFeatureKey}.
     */
    @Nonnull
    private final Database multivaluedFeatures;

    /**
     * Constructs a new {@code BerkeleyDbBackendIndices} wrapping the provided {@code environment}.
     * <p>
     * <b>Note:</b> This constructor is protected. To create a new {@link BerkeleyDbBackend} use {@link
     * BackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, java.util.Map)}.
     *
     * @param environment    the database environment
     * @param databaseConfig the database configuration
     *
     * @see BerkeleyDbBackendFactory
     */
    protected BerkeleyDbBackendIndices(Environment environment, DatabaseConfig databaseConfig) {
        super(environment, databaseConfig);

        this.multivaluedFeatures = environment.openDatabase(null, "multivaluedFeatures", databaseConfig);
    }

    @Override
    public void copyTo(DataMapper target) {
        checkArgument(target instanceof BerkeleyDbBackendIndices);
        BerkeleyDbBackendIndices to = (BerkeleyDbBackendIndices) target;

        super.copyTo(target);
        this.copy(multivaluedFeatures, to.multivaluedFeatures);
    }

    @Nonnull
    @Override
    protected List<Database> allDatabases() {
        List<Database> databases = super.allDatabases();
        databases.add(multivaluedFeatures);
        return databases;
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
}
