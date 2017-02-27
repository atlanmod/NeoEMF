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

import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptions;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlueprintsBackendFactoryTest extends AbstractPersistenceBackendFactoryTest implements BlueprintsTest {

    @Test
    public void testCreateTransientBackend() {
        PersistenceBackend backend = context().persistenceBackendFactory().createTransientBackend();
        assertThat(backend).isInstanceOf(BlueprintsBackend.class);
    }

    @Test
    public void testCreateTransientStore() {
        PersistenceBackend backend = context().persistenceBackendFactory().createTransientBackend();

        PersistentStore store = context().persistenceBackendFactory().createTransientStore(null, backend);
        assertThat(store).isInstanceOf(DirectWriteStore.class);

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentBackend() {
        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(context().createFileURI(file()), BlueprintsOptions.noOption());
        assertThat(backend).isInstanceOf(BlueprintsBackend.class);
    }

    @Test
    public void testCreatePersistentStore() {
        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(context().createFileURI(file()), BlueprintsOptions.noOption());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, BlueprintsOptions.noOption());
        assertThat(store).isInstanceOf(DirectWriteStore.class);

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }
}
