/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.InvalidTransientBackend;
import fr.inria.atlanmod.neoemf.data.hbase.config.HBaseConfig;
import fr.inria.atlanmod.neoemf.data.hbase.context.HBaseContext;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link HBaseBackendFactory}.
 */
@ParametersAreNonnullByDefault
class HBaseBackendFactoryTest extends AbstractBackendFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return HBaseContext.getDefault();
    }

    @Override
    public void testCreateTransientBackend() {
        Backend backend = context().factory().createTransientBackend();
        assertThat(backend).isInstanceOf(InvalidTransientBackend.class);
    }

    @Override
    public void testCreateDefaultPersistentBackend() throws IOException {
        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), HBaseConfig.newConfig());
        assertThat(backend).isInstanceOf(DefaultHBaseBackend.class);
    }

    @Disabled("Not supported")
    @Override
    public void testCopyBackend() {
    }

    /**
     * Checks the creation of a {@link fr.inria.atlanmod.neoemf.data.PersistentBackend}, specific for HBase.
     * <p>
     * The mapping {@code array-strings} is declared explicitly.
     */
    @Test
    void testCreateIndicesPersistentBackend() throws IOException {
        ImmutableConfig config = HBaseConfig.newConfig();

        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), config);
        assertThat(backend).isInstanceOf(DefaultHBaseBackend.class);
    }
}