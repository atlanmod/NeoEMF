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

package fr.inria.atlanmod.neoemf.tests.context;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.berkeleydb.context.BerkeleyDbContext;
import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsContext;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.context.BlueprintsNeo4jContext;
import fr.inria.atlanmod.neoemf.data.hbase.context.HBaseContext;
import fr.inria.atlanmod.neoemf.data.mapdb.context.MapDbContext;
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link ArgumentsProvider} that provides all {@link Context} for running tests.
 */
@ParametersAreNonnullByDefault
public class ContextProvider {

    /**
     * Returns a stream of all initialized contexts.
     *
     * @return a stream
     */
    @Nonnull
    public static Stream<Context> allContexts() {
        return Stream.of(
                BlueprintsContext.getWithIndices().init(),
                BlueprintsNeo4jContext.getWithIndices().init(),
                MapDbContext.getWithIndices().init(),
                MapDbContext.getWithArrays().init(),
                MapDbContext.getWithLists().init(),
                BerkeleyDbContext.getWithIndices().init(),
                BerkeleyDbContext.getWithArrays().init(),
                BerkeleyDbContext.getWithLists().init(),
                HBaseContext.getWithArraysAndStrings().init()
        ).filter(Context::isInitialized);
    }

    @Nonnull
    public static Stream<URI> allUris() {
        return Stream.of(
                IOResourceManager.xmiStandard(),
                IOResourceManager.xmiWithId(),
                IOResourceManager.zxmiStandard(),
                IOResourceManager.zxmiWithId()
        );
    }

    /**
     * An {@link ArgumentsProvider} with all {@link Context}s.
     */
    @ParametersAreNonnullByDefault
    public static class All implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return allContexts().map(Arguments::of);
        }
    }

    /**
     * An {@link ArgumentsProvider} with all {@link Context}s associated with all {@link Context}s. Each context will be
     * executed with all other contexts {@code (1 -> 1, 1 -> 2, 2 -> 1, 2 -> 2,...)}.
     */
    @ParametersAreNonnullByDefault
    public static class WithContexts implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return allContexts().flatMap(c -> allContexts().map(c1 -> Arguments.of(c, c1)));
        }
    }

    /**
     * An {@link ArgumentsProvider} with all  {@link Context}s associated with all {@link URI}s managed by {@link
     * IOResourceManager}.
     */
    @ParametersAreNonnullByDefault
    public static class WithUris implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return allContexts().flatMap(c -> allUris().map(u -> Arguments.of(c, u)));
        }
    }

    /**
     * An {@link ArgumentsProvider} with all {@link Context}s associated with all {@link Boolean} variants.
     */
    @ParametersAreNonnullByDefault
    public static class WithBooleans implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return allContexts().flatMap(c -> Stream.of(false, true).map(b -> Arguments.of(c, b)));
        }
    }

    /**
     * An {@link ArgumentsProvider} with all {@link Context}s associated with all {@link Boolean} variants (x2).
     */
    @ParametersAreNonnullByDefault
    public static final class WithBiBooleans implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return ContextProvider.allContexts()
                    .flatMap(c -> Stream.of(false, true)
                            .flatMap(b1 -> Stream.of(false, true)
                                    .map(b2 -> Arguments.of(c, b1, b2))));
        }
    }

    /**
     * An {@link ArgumentsProvider} with all {@link Context}s associated with all {@link Boolean} variants, and some
     * {@link Integer}s.
     */
    @ParametersAreNonnullByDefault
    public static final class WithBooleansAndIntegers implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return ContextProvider.allContexts()
                    .flatMap(c -> Stream.of(false, true)
                            .flatMap(b -> IntStream.rangeClosed(3, 5)
                                    .mapToObj(i -> Arguments.of(c, b, i))));
        }
    }
}
