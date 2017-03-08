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
import com.sleepycat.je.EnvironmentConfig;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapper.MultiValueWithIndices;
import fr.inria.atlanmod.neoemf.data.mapper.PersistenceMapper;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * ???
 */
@ParametersAreNonnullByDefault
class BerkeleyDbBackendIndices extends AbstractBerkeleyDbBackend implements MultiValueWithIndices {

    /**
     * A persistent map that store the values of multi-valued features for {@link Id}, identified by the associated
     * {@link ManyFeatureKey}.
     */
    @Nonnull
    private final Database multivaluedFeatures;

    /**
     * Constructs a new {@code BerkeleyDbBackendIndices} on the given {@code file} with the given
     * {@code envConfig}.
     * <p>
     * <b>Note:</b> This constructor is protected. To create a new {@code BerkeleyDbBackendIndices} use {@link
     * PersistenceBackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, Map)}.
     *
     * @param file      ???
     * @param envConfig ???
     * @param dbConfig  ???
     */
    protected BerkeleyDbBackendIndices(File file, EnvironmentConfig envConfig, DatabaseConfig dbConfig) {
        super(file, envConfig, dbConfig);

        this.multivaluedFeatures = environment.openDatabase(null, "multivaluedFeatures", dbConfig);
    }

    @Override
    public void copyTo(PersistenceMapper target) {
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
