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
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.option.CommonOptions;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class HBaseBackendFactoryTest extends AbstractBackendFactoryTest implements HBaseTest {

    @Test
    public void testCreateTransientBackend() {
        Backend backend = context().factory().createTransientBackend();
        assertThat(backend).isInstanceOf(InvalidTransientBackend.class);
    }

    @Test
    public void testCreateTransientStore() {
        Backend backend = context().factory().createTransientBackend();

        //noinspection ConstantConditions
        Store store = context().factory().createStore(backend, null, CommonOptions.noOption());
        assertThat(store).isInstanceOf(DirectWriteStore.class);

        assertThat(store.backend()).isSameAs(backend);
    }

    @Test
    public void testCreateDefaultPersistentBackend() {
        Backend backend = context().factory().createPersistentBackend(context().createUri(file()), HBaseOptions.noOption());
        assertThat(backend).isInstanceOf(HBaseBackendArraysStrings.class);
    }

    @Test
    public void testCreateIndicesPersistentBackend() {
        Map<String, Object> options = HBaseOptions.newBuilder()
                .withArraysAndStrings()
                .asMap();

        Backend backend = context().factory().createPersistentBackend(context().createUri(file()), options);
        assertThat(backend).isInstanceOf(HBaseBackendArraysStrings.class);
    }

    @Test
    public void testCreatePersistentStore() {
        Backend backend = context().factory().createPersistentBackend(context().createUri(file()), HBaseOptions.noOption());

        //noinspection ConstantConditions
        Store store = context().factory().createStore(backend, null, HBaseOptions.noOption());
        assertThat(store).isInstanceOf(DirectWriteStore.class);

        assertThat(store.backend()).isSameAs(backend);
    }

    /**
     * Checks if {@link Backend#copyTo} creates the persistent datastores from the transient ones.
     * Only empty back-ends are tested.
     */
    @Test
    @Ignore("HBase back-ends don't support copy")
    public void testCopyBackend() {
        Backend transientBackend = context().factory().createTransientBackend();
        assertThat(transientBackend).isInstanceOf(HBaseBackend.class);

        Backend persistentBackend = context().factory().createPersistentBackend(context().createUri(file()), HBaseOptions.noOption());
        assertThat(persistentBackend).isInstanceOf(HBaseBackend.class);

        transientBackend.copyTo(persistentBackend);
    }
}