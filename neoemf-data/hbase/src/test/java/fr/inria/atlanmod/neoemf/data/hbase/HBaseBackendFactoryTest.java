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

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.InvalidTransientBackend;
import fr.inria.atlanmod.neoemf.data.hbase.context.HBaseContext;
import fr.inria.atlanmod.neoemf.data.hbase.option.HBaseOptions;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link HBaseBackendFactory}.
 */
@ParametersAreNonnullByDefault
public class HBaseBackendFactoryTest extends AbstractBackendFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return HBaseContext.getWithArraysAndStrings();
    }

    @Override
    public void testCreateTransientBackend() throws Exception {
        Backend backend = context().factory().createTransientBackend();
        assertThat(backend).isInstanceOf(InvalidTransientBackend.class);
    }

    @Override
    public void testCreateDefaultPersistentBackend() throws Exception {
        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), HBaseOptions.noOption());
        assertThat(backend).isInstanceOf(DefaultHBaseBackend.class);
    }

    @Disabled("Not supported")
    @Override
    public void testCopyBackend() throws Exception {
    }

    /**
     * Checks the creation of a {@link fr.inria.atlanmod.neoemf.data.PersistentBackend}, specific for HBase.
     * <p>
     * The mapping {@code array-strings} is declared explicitly.
     */
    @Test
    public void testCreateIndicesPersistentBackend() throws Exception {
        Map<String, Object> options = HBaseOptions.builder()
                .withArraysAndStrings()
                .asMap();

        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), options);
        assertThat(backend).isInstanceOf(DefaultHBaseBackend.class);
    }
}