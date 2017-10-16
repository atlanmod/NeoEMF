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
import fr.inria.atlanmod.neoemf.data.im.InMemoryBackendFactory;
import fr.inria.atlanmod.neoemf.data.im.config.InMemoryConfig;
import fr.inria.atlanmod.neoemf.data.im.util.InMemoryUri;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link BlueprintsBackendFactory}.
 */
@ParametersAreNonnullByDefault
class BlueprintsBackendFactoryTest extends AbstractBackendFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return BlueprintsContext.getDefault();
    }

    @Test
    public void testCreateDefaultBackend() throws IOException {
        ImmutableConfig config = BlueprintsTinkerConfig.newConfig();

        try (Backend backend = context().factory().createBackend(context().createUri(currentTempFile()), config)) {
            assertThat(backend).isInstanceOf(DefaultBlueprintsBackend.class);
        }
    }

    @Override
    public void testCopyBackend() throws IOException {
        ImmutableConfig config = BlueprintsTinkerConfig.newConfig();

        File file = currentTempFile();
        try (Backend transientBackend = InMemoryBackendFactory.getInstance().createBackend(InMemoryUri.builder().fromFile(file), InMemoryConfig.newConfig())) {
            try (Backend persistentBackend = context().factory().createBackend(context().createUri(file), config)) {
                assertThat(persistentBackend).isInstanceOf(DefaultBlueprintsBackend.class);

                transientBackend.copyTo(persistentBackend);
            }
        }
    }
}
