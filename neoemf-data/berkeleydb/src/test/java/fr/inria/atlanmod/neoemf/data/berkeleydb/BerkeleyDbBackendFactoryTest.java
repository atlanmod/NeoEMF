/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.berkeleydb.config.BerkeleyDbConfig;
import fr.inria.atlanmod.neoemf.data.berkeleydb.context.BerkeleyDbContext;

import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link BerkeleyDbBackendFactory}.
 */
@ParametersAreNonnullByDefault
public class BerkeleyDbBackendFactoryTest extends AbstractBackendFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return BerkeleyDbContext.getWithIndices();
    }

    @Override
    public void testCreateTransientBackend() throws Exception {
        Backend backend = context().factory().createTransientBackend();
        assertThat(backend).isInstanceOf(BerkeleyDbBackend.class);
    }

    @Override
    public void testCreateDefaultPersistentBackend() throws Exception {
        ImmutableConfig config = BerkeleyDbConfig.newConfig().withIndices();

        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), config);
        assertThat(backend).isInstanceOf(BerkeleyDbBackendIndices.class);
    }

    @Override
    public void testCopyBackend() throws Exception {
        ImmutableConfig config = BerkeleyDbConfig.newConfig().withIndices();

        Backend transientBackend = context().factory().createTransientBackend();
        assertThat(transientBackend).isInstanceOf(BerkeleyDbBackend.class);

        Backend persistentBackend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), config);
        assertThat(persistentBackend).isInstanceOf(BerkeleyDbBackend.class);

        transientBackend.copyTo(persistentBackend);
    }

    /**
     * Checks the creation of a {@link fr.inria.atlanmod.neoemf.data.PersistentBackend}, specific for BerkeleyDB.
     * <p>
     * The mapping {@code indices} is declared explicitly.
     */
    @Test
    public void testCreateIndicesPersistentBackend() throws Exception {
        ImmutableConfig config = BerkeleyDbConfig.newConfig().withIndices();

        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), config);
        assertThat(backend).isInstanceOf(BerkeleyDbBackendIndices.class);
    }

    /**
     * Checks the creation of a {@link fr.inria.atlanmod.neoemf.data.PersistentBackend}, specific for BerkeleyDB.
     * <p>
     * The mapping {@code arrays} is declared explicitly.
     */
    @Test
    public void testCreateArraysPersistentBackend() throws Exception {
        ImmutableConfig config = BerkeleyDbConfig.newConfig().withArrays();

        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), config);
        assertThat(backend).isInstanceOf(BerkeleyDbBackendArrays.class);
    }

    /**
     * Checks the creation of a {@link fr.inria.atlanmod.neoemf.data.PersistentBackend}, specific for BerkeleyDB.
     * <p>
     * The mapping {@code lists} is declared explicitly.
     */
    @Test
    public void testCreateListsPersistentBackend() throws Exception {
        ImmutableConfig config = BerkeleyDbConfig.newConfig().withLists();

        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), config);
        assertThat(backend).isInstanceOf(BerkeleyDbBackendLists.class);
    }
}
