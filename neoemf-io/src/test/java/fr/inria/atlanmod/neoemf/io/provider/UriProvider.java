/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.provider;

import fr.inria.atlanmod.neoemf.io.util.ResourceManager;
import org.atlanmod.commons.annotation.Static;
import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

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
     * Returns a stream of all declared {@link URI}s that identify uncompressed resources,
     * that conforms to the: @see <a href="https://github.com/atlanmod/zoo/tree/master/emf.java">Modisco Java Meta-Model</a>
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
     * Returns a stream of all declared {@link URI}s that identify uncompressed resources.
     *
     * @return a stream
     */
    @Nonnull
    public static Stream<URI> mixedUris() {
        return Stream.of(
                ResourceManager.xmiStandard(),
                ResourceManager.xmiWithId(),
                ResourceManager.xmiIceAge()
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

    public static Stream<Map.Entry<URI, URI>> proxyUris() {
        return Stream.of(
                Map.entry(ResourceManager.proxyContainer(), ResourceManager.proxyContainment())
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

    /**
     * An {@link ArgumentsProvider} with all mixed (non-Java) {@link URI}s managed by {@link ResourceManager}.
     * <p>
     * It does not includes compressed resources.
     */
    @ParametersAreNonnullByDefault
    public static class UncompressedMixed implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return mixedUris().map(Arguments::of);
        }
    }

    /**
     * An {@link ArgumentsProvider} with resouces with proxies {@link URI}s managed by {@link ResourceManager}.
     * <p>
     * It does not includes compressed resources.
     */
    @ParametersAreNonnullByDefault
    public static class WithProxies implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return proxyUris().map( (entry) ->
                    Arguments.of(entry.getKey(), entry.getValue())
            );
        }
    }

    public static class TestData implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            URL url = UriProvider.class.getResource("/test-data/");
            Path dir = Paths.get(url.toURI());

            return Files.list(dir).map(Path::toString)
                    .map(URI::createURI)
                    .map(Arguments::of);
        }
    }
}
