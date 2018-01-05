/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.mapdb.config.MapDbConfig;
import fr.inria.atlanmod.neoemf.data.mapdb.context.MapDbContext;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link MapDbBackendFactory}.
 */
@ParametersAreNonnullByDefault
class MapDbBackendFactoryTest extends AbstractBackendFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return MapDbContext.getWithIndices();
    }

    @Nonnull
    @Override
    protected Stream<Arguments> allMappings() {
        return Stream.of(
                Arguments.of(MapDbConfig.newConfig().withIndices(), MapDbBackendIndices.class),
                Arguments.of(MapDbConfig.newConfig().withArrays(), MapDbBackendArrays.class),
                Arguments.of(MapDbConfig.newConfig().withLists(), MapDbBackendLists.class)
        );
    }
}
