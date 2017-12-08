/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.im;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.context.CoreContext;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.im.config.InMemoryConfig;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import javax.annotation.Nonnull;

/**
 * A test-case about {@link InMemoryBackendFactory}.
 */
class InMemoryBackendFactoryTest extends AbstractBackendFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return CoreContext.getDefault();
    }

    @Disabled("Not supported")
    @Override
    public void testCopyBackend() {
    }

    @Nonnull
    @Override
    protected Stream<Arguments> allMappings() {
        return Stream.of(
                Arguments.of(InMemoryConfig.newConfig(), DefaultInMemoryBackend.class)
        );
    }
}
