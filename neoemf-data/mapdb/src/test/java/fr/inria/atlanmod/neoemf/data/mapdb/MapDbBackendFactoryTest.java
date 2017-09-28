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

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapdb.context.MapDbContext;
import fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbOptions;

import org.junit.jupiter.api.Test;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link MapDbBackendFactory}.
 */
@ParametersAreNonnullByDefault
public class MapDbBackendFactoryTest extends AbstractBackendFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return MapDbContext.getWithIndices();
    }

    @Override
    public void testCreateTransientBackend() throws Exception {
        Backend backend = context().factory().createTransientBackend();
        assertThat(backend).isInstanceOf(MapDbBackend.class);
    }

    @Override
    public void testCreateDefaultPersistentBackend() throws Exception {
        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), MapDbOptions.noOption());
        assertThat(backend).isInstanceOf(MapDbBackendIndices.class);
    }

    @Override
    public void testCopyBackend() throws Exception {
        Backend transientBackend = context().factory().createTransientBackend();
        assertThat(transientBackend).isInstanceOf(MapDbBackend.class);

        Backend persistentBackend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), MapDbOptions.noOption());
        assertThat(persistentBackend).isInstanceOf(MapDbBackend.class);

        transientBackend.copyTo(persistentBackend);
    }

    /**
     * Checks the creation of a {@link fr.inria.atlanmod.neoemf.data.PersistentBackend}, specific for MapDB.
     * <p>
     * The mapping {@code indices} is declared explicitly.
     */
    @Test
    public void testCreateIndicesPersistentBackend() throws Exception {
        Map<String, Object> options = MapDbOptions.builder()
                .withIndices()
                .asMap();

        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), options);
        assertThat(backend).isInstanceOf(MapDbBackendIndices.class);
    }

    /**
     * Checks the creation of a {@link fr.inria.atlanmod.neoemf.data.PersistentBackend}, specific for MapDB.
     * <p>
     * The mapping {@code arrays} is declared explicitly.
     */
    @Test
    public void testCreateArraysPersistentBackend() throws Exception {
        Map<String, Object> options = MapDbOptions.builder()
                .withArrays()
                .asMap();

        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), options);
        assertThat(backend).isInstanceOf(MapDbBackendArrays.class);
    }

    /**
     * Checks the creation of a {@link fr.inria.atlanmod.neoemf.data.PersistentBackend}, specific for MapDB.
     * <p>
     * The mapping {@code lists} is declared explicitly.
     */
    @Test
    public void testCreateListsPersistentBackend() throws Exception {
        Map<String, Object> options = MapDbOptions.builder()
                .withLists()
                .asMap();

        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), options);
        assertThat(backend).isInstanceOf(MapDbBackendLists.class);
    }
}
