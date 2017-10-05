/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.io;

import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.log.Log;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A class that provides static methods for {@link Path} management.
 */
@Static
@ParametersAreNonnullByDefault
public class Workspace {

    /**
     * The environment variable key of the NeoEMF home directory.
     */
    @Nonnull
    private static final String HOME_KEY = "NEOEMF_HOME";

    /**
     * The base directory of benchmarks.
     */
    @Nonnull
    private static final Path BASE_DIRECTORY = getHome();

    /**
     * The directory where to store the original {@link org.eclipse.emf.ecore.resource.Resource}s.
     */
    @Nonnull
    private static final Path RESOURCES_DIRECTORY = getBaseDirectory().resolve("resources");

    /**
     * The directory where to store the adapted {@link org.eclipse.emf.ecore.resource.Resource}s.
     */
    @Nonnull
    private static final Path STORES_DIRECTORY = getBaseDirectory().resolve("stores");

    /**
     * The default temporary directory.
     */
    private static Path TEMP_DIRECTORY;

    @SuppressWarnings("JavaDoc")
    private Workspace() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Retrieves the base directory benchmarks.
     * <p>
     * This path can be defined with the {@code NEOEMF_HOME} environment variable. If this environment variable is not
     * defined, then {@code ${user.home}/.neoemf} is used.
     *
     * @return the directory
     */
    @Nonnull
    private static Path getHome() {
        return Optional.ofNullable(System.getenv(HOME_KEY))
                .map(s -> Paths.get(s))
                .orElseGet(() -> {
                    Path h = Paths.get(System.getProperty("user.home")).resolve(".neoemf");
                    Log.info("NEOEMF_HOME is not defined. Using {0} instead.", h);
                    return h;
                })
                .resolve("benchmarks");
    }

    /**
     * Returns the base directory of benchmarks.
     *
     * @return the directory
     */
    @Nonnull
    private static Path getBaseDirectory() {
        return createIfNecessary(BASE_DIRECTORY);
    }

    /**
     * Returns the directory where to store the original {@link org.eclipse.emf.ecore.resource.Resource}s.
     *
     * @return the directory
     */
    @Nonnull
    public static Path getResourcesDirectory() {
        return createIfNecessary(RESOURCES_DIRECTORY);
    }

    /**
     * Returns the directory where to store the adapted {@link org.eclipse.emf.ecore.resource.Resource}s.
     *
     * @return the directory
     */
    @Nonnull
    public static Path getStoreDirectory() {
        return createIfNecessary(STORES_DIRECTORY);
    }

    /**
     * Returns the default temporary directory.
     *
     * @return the temporary directory
     */
    @Nonnull
    private static Path getTempDirectory() {
        if (isNull(TEMP_DIRECTORY)) {
            try {
                TEMP_DIRECTORY = Files.createTempDirectory("neoemf-benchmark");
                Runtime.getRuntime().addShutdownHook(new Thread(() -> deleteDirectory(TEMP_DIRECTORY, true)));
            }
            catch (IOException e) {
                Log.warn(e);
            }
        }
        return TEMP_DIRECTORY;
    }

    /**
     * Creates a new temporary directory.
     *
     * @return the temporary directory.
     */
    @Nonnull
    public static Path newTempDirectory() throws IOException {
        return Files.createTempDirectory(getTempDirectory(), "tmp");
    }

    /**
     * Cleans all temporary directories.
     */
    public static void cleanTempDirectory() {
        if (nonNull(TEMP_DIRECTORY)) {
            deleteDirectory(TEMP_DIRECTORY, false);
        }
    }

    /**
     * Creates a directory if it does not already exist.
     *
     * @param path the directory to create
     *
     * @return the {@code path}
     */
    @Nonnull
    private static Path createIfNecessary(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return path;
    }

    /**
     * Deletes the given directory with all its files and sub-directories.
     *
     * @param directory     the directory directory to delete
     * @param includingRoot {@code true} if the {@code directory} must be deleted
     */
    private static void deleteDirectory(Path directory, boolean includingRoot) {
        try {
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.deleteIfExists(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (!Objects.equals(dir, directory)) {
                        Files.deleteIfExists(dir);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });

            if (includingRoot) {
                Files.deleteIfExists(directory);
            }
        }
        catch (Exception ignored) {
        }
    }
}
