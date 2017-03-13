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

import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.berkeleydb.option.BerkeleyDbOptions;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class BerkeleyDbBackendFactoryTest extends AbstractBackendFactoryTest implements BerkeleyDbTest {

    @Test
    public void testCreateTransientBackend() {
        Backend backend = context().factory().createTransientBackend();
        assertThat(backend).isInstanceOf(BerkeleyDbBackend.class);
    }

    @Test
    public void testCreateTransientStore() {
        Backend backend = context().factory().createTransientBackend();

        Store store = context().factory().createTransientStore(null, backend);
        assertThat(store).isInstanceOf(DirectWriteStore.class);

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentBackend() {
        Backend backend = context().factory().createPersistentBackend(context().createFileURI(file()), BerkeleyDbOptions.noOption());
        assertThat(backend).isInstanceOf(BerkeleyDbBackend.class);
    }

    @Test
    public void testCreatePersistentStore() {
        Backend backend = context().factory().createPersistentBackend(context().createFileURI(file()), BerkeleyDbOptions.noOption());

        Store store = context().factory().createPersistentStore(null, backend, BerkeleyDbOptions.noOption());
        assertThat(store).isInstanceOf(DirectWriteStore.class);

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    /**
     * Checks if {@link Backend#copyTo} creates the persistent datastores from the transient ones.
     * Only empty back-ends are tested.
     */
    @Test
    public void testCopyBackend() {
        Backend transientBackend = context().factory().createTransientBackend();
        assertThat(transientBackend).isInstanceOf(BerkeleyDbBackend.class);

        Backend persistentBackend = context().factory().createPersistentBackend(context().createFileURI(file()), BerkeleyDbOptions.noOption());
        assertThat(persistentBackend).isInstanceOf(BerkeleyDbBackend.class);

        transientBackend.copyTo(persistentBackend);
    }

    @Test
    public void testTransientBackend() {
        final int TIMES = 1000;

        try (Backend backend = context().factory().createTransientBackend()) {
            IntStream.range(0, TIMES).forEach(i -> {
                FeatureKey key = FeatureKey.of(StringId.of("object" + i), "name" + i);
                assertThat(backend.valueFor(key, "value" + i)).isNotPresent();
            });
        }

        try (Backend other = context().factory().createTransientBackend()) {
            IntStream.range(0, TIMES).forEach(i -> {
                FeatureKey key = FeatureKey.of(StringId.of("object" + i), "name" + i);
                assertThat(other.hasValue(key)).isFalse();
            });
        }
    }
}
