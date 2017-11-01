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

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link BerkeleyDbBackendFactory}.
 */
@ParametersAreNonnullByDefault
class BerkeleyDbBackendFactoryTest extends AbstractBackendFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return BerkeleyDbContext.getWithIndices();
    }

    /**
     * Checks the creation of a {@link fr.inria.atlanmod.neoemf.data.Backend}, specific for BerkeleyDB.
     * <p>
     * The mapping {@code indices} is declared explicitly.
     */
    @Test
    void testCreateIndicesBackend() throws IOException {
        ImmutableConfig config = BerkeleyDbConfig.newConfig().withIndices();

        try (Backend backend = context().factory().createBackend(context().createUri(currentTempFile()), config)) {
            assertThat(backend).isInstanceOf(BerkeleyDbBackendIndices.class);
        }
    }

    /**
     * Checks the creation of a {@link fr.inria.atlanmod.neoemf.data.Backend}, specific for BerkeleyDB.
     * <p>
     * The mapping {@code arrays} is declared explicitly.
     */
    @Test
    void testCreateArraysBackend() throws IOException {
        ImmutableConfig config = BerkeleyDbConfig.newConfig().withArrays();

        try (Backend backend = context().factory().createBackend(context().createUri(currentTempFile()), config)) {
            assertThat(backend).isInstanceOf(BerkeleyDbBackendArrays.class);
        }
    }

    /**
     * Checks the creation of a {@link fr.inria.atlanmod.neoemf.data.Backend}, specific for BerkeleyDB.
     * <p>
     * The mapping {@code lists} is declared explicitly.
     */
    @Test
    void testCreateListsBackend() throws IOException {
        ImmutableConfig config = BerkeleyDbConfig.newConfig().withLists();

        try (Backend backend = context().factory().createBackend(context().createUri(currentTempFile()), config)) {
            assertThat(backend).isInstanceOf(BerkeleyDbBackendLists.class);
        }
    }
}
