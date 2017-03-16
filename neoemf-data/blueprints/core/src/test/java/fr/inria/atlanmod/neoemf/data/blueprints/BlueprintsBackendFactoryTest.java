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

package fr.inria.atlanmod.neoemf.data.blueprints;

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsTest;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptions;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.option.CommonOptions;

import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BlueprintsBackendFactoryTest extends AbstractBackendFactoryTest implements BlueprintsTest {

    @Test
    public void testCreateTransientBackend() {
        Backend backend = context().factory().createTransientBackend();
        assertThat(backend).isInstanceOf(BlueprintsBackend.class);
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
        Backend backend = context().factory().createPersistentBackend(context().createFileUri(file()), BlueprintsOptions.noOption());
        assertThat(backend).isInstanceOf(BlueprintsBackendIndices.class);
    }

    @Test
    public void testCreateIndicesPersistentBackend() {
        Map<String, Object> options = BlueprintsOptions.newBuilder()
                .withIndices()
                .asMap();

        Backend backend = context().factory().createPersistentBackend(context().createFileUri(file()), options);
        assertThat(backend).isInstanceOf(BlueprintsBackendIndices.class);
    }

    @Test
    public void testCreatePersistentStore() {
        Backend backend = context().factory().createPersistentBackend(context().createFileUri(file()), BlueprintsOptions.noOption());

        //noinspection ConstantConditions
        Store store = context().factory().createStore(backend, null, BlueprintsOptions.noOption());
        assertThat(store).isInstanceOf(DirectWriteStore.class);

        assertThat(store.backend()).isSameAs(backend);
    }
}
