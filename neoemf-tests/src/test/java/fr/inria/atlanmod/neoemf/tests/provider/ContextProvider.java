/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.tests.provider;

import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.io.provider.UriProvider;
import fr.inria.atlanmod.neoemf.io.util.ResourceManager;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.ServiceLoader;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link ArgumentsProvider} that provides all {@link Context} for running tests.
 */
@Static
@ParametersAreNonnullByDefault
public final class ContextProvider {

    /**
     * Returns a stream of all initialized contexts.
     *
     * @return a stream
     */
    @Nonnull
    public static Stream<Context> allContexts() {
        return MoreIterables.stream(ServiceLoader.load(Context.class)).map(Context::init).filter(Context::isInitialized);
    }

    /**
     * An {@link ArgumentsProvider} with all {@link Context}s.
     */
    @ParametersAreNonnullByDefault
    public static class All implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return allContexts().map(Arguments::of);
        }
    }

    /**
     * An {@link ArgumentsProvider} with all {@link Context}s associated with all {@link Context}s. Each context will be
     * executed with all other contexts {@code (1 -> 1, 1 -> 2, 2 -> 1, 2 -> 2,...)}.
     */
    @ParametersAreNonnullByDefault
    public static class AllWithContexts implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return allContexts().flatMap(c -> allContexts().map(c1 -> Arguments.of(c, c1)));
        }
    }

    /**
     * An {@link ArgumentsProvider} with all  {@link Context}s associated with all {@link URI}s managed by {@link
     * ResourceManager}.
     */
    @ParametersAreNonnullByDefault
    public static class AllWithUris implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return allContexts().flatMap(c -> UriProvider.allUris().map(u -> Arguments.of(c, u)));
        }
    }

    /**
     * An {@link ArgumentsProvider} with all {@link Context}s associated with all {@link Boolean} variants.
     */
    @ParametersAreNonnullByDefault
    public static class AllWithBooleans implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return allContexts().flatMap(c -> Stream.of(false, true).map(b -> Arguments.of(c, b)));
        }
    }

    /**
     * An {@link ArgumentsProvider} with all {@link Context}s associated with all {@link Boolean} variants, and some
     * {@link Integer}s.
     */
    @ParametersAreNonnullByDefault
    public static class AllWithIntegers implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return ContextProvider.allContexts()
                    .flatMap(c -> IntStream.rangeClosed(3, 5)
                            .mapToObj(i -> Arguments.of(c, i)));
        }
    }
}
