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

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.berkeleydb.context.BerkeleyDbContext;
import fr.inria.atlanmod.neoemf.data.berkeleydb.option.BerkeleyDbOptions;

import org.junit.jupiter.api.Test;

import java.util.Map;

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
        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), BerkeleyDbOptions.noOption());
        assertThat(backend).isInstanceOf(BerkeleyDbBackendIndices.class);
    }

    @Override
    public void testCopyBackend() throws Exception {
        Backend transientBackend = context().factory().createTransientBackend();
        assertThat(transientBackend).isInstanceOf(BerkeleyDbBackend.class);

        Backend persistentBackend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), BerkeleyDbOptions.noOption());
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
        Map<String, Object> options = BerkeleyDbOptions.builder()
                .withIndices()
                .asMap();

        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), options);
        assertThat(backend).isInstanceOf(BerkeleyDbBackendIndices.class);
    }

    /**
     * Checks the creation of a {@link fr.inria.atlanmod.neoemf.data.PersistentBackend}, specific for BerkeleyDB.
     * <p>
     * The mapping {@code arrays} is declared explicitly.
     */
    @Test
    public void testCreateArraysPersistentBackend() throws Exception {
        Map<String, Object> options = BerkeleyDbOptions.builder()
                .withArrays()
                .asMap();

        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), options);
        assertThat(backend).isInstanceOf(BerkeleyDbBackendArrays.class);
    }

    /**
     * Checks the creation of a {@link fr.inria.atlanmod.neoemf.data.PersistentBackend}, specific for BerkeleyDB.
     * <p>
     * The mapping {@code lists} is declared explicitly.
     */
    @Test
    public void testCreateListsPersistentBackend() throws Exception {
        Map<String, Object> options = BerkeleyDbOptions.builder()
                .withLists()
                .asMap();

        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), options);
        assertThat(backend).isInstanceOf(BerkeleyDbBackendLists.class);
    }
}
