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

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.InvalidTransientBackend;
import fr.inria.atlanmod.neoemf.data.hbase.context.HBaseTest;
import fr.inria.atlanmod.neoemf.data.hbase.option.HBaseOptions;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link HBaseBackendFactory}.
 */
public class HBaseBackendFactoryTest extends AbstractBackendFactoryTest implements HBaseTest {

    @Override
    public void testCreateTransientBackend() {
        Backend backend = context().factory().createTransientBackend();
        assertThat(backend).isInstanceOf(InvalidTransientBackend.class);
    }

    @Override
    public void testCreateDefaultPersistentBackend() {
        Backend backend = context().factory().createPersistentBackend(context().createUri(file()), HBaseOptions.noOption());
        assertThat(backend).isInstanceOf(HBaseBackendArraysStrings.class);
    }

    @Ignore("Not supported")
    @Override
    public void testCopyBackend() {
    }

    /**
     * Checks the creation of a {@link fr.inria.atlanmod.neoemf.data.PersistentBackend}, specific for HBase.
     * <p>
     * The mapping {@code array-strings} is declared explicitly.
     */
    @Test
    public void testCreateIndicesPersistentBackend() {
        Map<String, Object> options = HBaseOptions.builder()
                .withArraysAndStrings()
                .asMap();

        Backend backend = context().factory().createPersistentBackend(context().createUri(file()), options);
        assertThat(backend).isInstanceOf(HBaseBackendArraysStrings.class);
    }
}