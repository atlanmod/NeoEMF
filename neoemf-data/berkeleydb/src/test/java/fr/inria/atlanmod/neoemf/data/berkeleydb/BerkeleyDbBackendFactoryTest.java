/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.berkeleydb.config.BerkeleyDbConfig;
import fr.inria.atlanmod.neoemf.data.berkeleydb.context.BerkeleyDbContext;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link BerkeleyDbBackendFactory}.
 */
@ParametersAreNonnullByDefault
class BerkeleyDbBackendFactoryTest extends AbstractBackendFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return BerkeleyDbContext.getWithIndices();
    }

    @Nonnull
    @Override
    protected Stream<Arguments> allMappings() {
        return Stream.of(
                Arguments.of(BerkeleyDbConfig.newConfig().withIndices(), BerkeleyDbBackendIndices.class),
                Arguments.of(BerkeleyDbConfig.newConfig().withArrays(), BerkeleyDbBackendArrays.class),
                Arguments.of(BerkeleyDbConfig.newConfig().withLists(), BerkeleyDbBackendLists.class)
        );
    }
}
