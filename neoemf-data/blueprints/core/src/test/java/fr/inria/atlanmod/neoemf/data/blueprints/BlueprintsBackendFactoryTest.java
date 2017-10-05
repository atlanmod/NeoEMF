/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.blueprints.config.BlueprintsTinkerConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsContext;

import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link BlueprintsBackendFactory}.
 */
@ParametersAreNonnullByDefault
public class BlueprintsBackendFactoryTest extends AbstractBackendFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return BlueprintsContext.getDefault();
    }

    @Override
    public void testCreateTransientBackend() throws Exception {
        Backend backend = context().factory().createTransientBackend();
        assertThat(backend).isInstanceOf(BlueprintsBackend.class);
    }

    @Override
    public void testCreateDefaultPersistentBackend() throws Exception {
        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), BlueprintsTinkerConfig.newConfig());
        assertThat(backend).isInstanceOf(DefaultBlueprintsBackend.class);
    }

    @Override
    public void testCopyBackend() throws Exception {
        Backend transientBackend = context().factory().createTransientBackend();
        assertThat(transientBackend).isInstanceOf(BlueprintsBackend.class);

        Backend persistentBackend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), BlueprintsTinkerConfig.newConfig());
        assertThat(persistentBackend).isInstanceOf(DefaultBlueprintsBackend.class);

        transientBackend.copyTo(persistentBackend);
    }

    /**
     * Checks the creation of a {@link fr.inria.atlanmod.neoemf.data.PersistentBackend}, specific for Blueprints.
     * <p>
     * The mapping {@code indices} is declared explicitly.
     */
    @Test
    public void testCreateIndicesPersistentBackend() throws Exception {
        ImmutableConfig config = BlueprintsTinkerConfig.newConfig();

        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), config);
        assertThat(backend).isInstanceOf(DefaultBlueprintsBackend.class);
    }
}
