/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.provider;

import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.neoemf.io.util.ResourceManager;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link ArgumentsProvider} with all {@link URI}s managed by {@link ResourceManager}, associated with all {@link
 * Boolean} variants.
 */
@Static
@ParametersAreNonnullByDefault
public final class UriProvider {

    /**
     * Returns a stream of all declared {@link URI}s.
     *
     * @return a stream
     */
    @Nonnull
    public static Stream<URI> allUris() {
        return Stream.concat(uncompressedUris(), compressedUris());
    }

    /**
     * Returns a stream of all declared {@link URI}s that identify uncompressed resources.
     *
     * @return a stream
     */
    @Nonnull
    public static Stream<URI> uncompressedUris() {
        return Stream.of(
                ResourceManager.xmiStandard(),
                ResourceManager.xmiWithId()
        );
    }

    /**
     * Returns a stream of all declared {@link URI}s that identify compressed resources.
     *
     * @return a stream
     */
    @Nonnull
    public static Stream<URI> compressedUris() {
        return Stream.of(
                ResourceManager.zxmiStandard(),
                ResourceManager.zxmiWithId()
        );
    }

    /**
     * An {@link ArgumentsProvider} with all {@link URI}s managed by {@link ResourceManager}.
     * <p>
     * It includes compressed resources.
     */
    @ParametersAreNonnullByDefault
    public static class All implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return allUris().map(Arguments::of);
        }
    }

    /**
     * An {@link ArgumentsProvider} with all {@link URI}s managed by {@link ResourceManager}, associated with all {@link
     * Boolean} variants.
     * <p>
     * It includes compressed resources.
     */
    @ParametersAreNonnullByDefault
    public static class AllWithBooleans implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return allUris().flatMap(u -> Stream.of(false, true).map(b -> Arguments.of(u, b)));
        }
    }

    /**
     * An {@link ArgumentsProvider} with all {@link URI}s managed by {@link ResourceManager}, associated with their type
     * (with {@code xmi:id} usage or not).
     * <p>
     * It includes compressed resources.
     */
    @ParametersAreNonnullByDefault
    public static class AllWithTypes implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return allUris().map(u -> Arguments.of(u, u.toString().contains("WithId.")));
        }
    }

    /**
     * An {@link ArgumentsProvider} with all {@link URI}s managed by {@link ResourceManager} that identify uncompressed
     * resources, associated with all {@link Boolean} variants.
     * <p>
     * It does not include compressed resources.
     */
    @ParametersAreNonnullByDefault
    public static class UncompressedWithBooleans implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return uncompressedUris().flatMap(u -> Stream.of(false, true).map(b -> Arguments.of(u, b)));
        }
    }
}
