/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.data.store;

import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.benchmarks.io.LocalWorkspace;

import org.eclipse.emf.common.util.URI;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Guards.checkNotNull;
import static org.atlanmod.commons.Guards.checkState;

/**
 * A {@link StoreCopier} that copies all the content of a directory into another.
 */
@ParametersAreNonnullByDefault
public class FileStoreCopier implements StoreCopier {

    /**
     * The adapter used to create the store where data will be saved.
     */
    @Nonnull
    private final Adapter adapter;

    /**
     * Constructs a new {@code FileStoreCopier}.
     *
     * @param adapter the adapter used to create the store where data will be saved
     */
    public FileStoreCopier(Adapter adapter) {
        this.adapter = checkNotNull(adapter, "adapter");
    }

    @Nonnull
    @Override
    public URI copy(URI uri) throws IOException {
        checkState(uri.isFile(), "URI must be file but was %s", uri);

        final Path file = Paths.get(uri.toFileString());
        final String fileName = file.getFileName().toString();

        final Path outputDirectory = LocalWorkspace.newTempDirectory();
        final Path outputFilePath = outputDirectory.resolve(fileName);

        Files.walkFileTree(file, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path targetPath = outputFilePath.resolve(file.relativize(dir));
                if (!Files.exists(targetPath)) {
                    Files.createDirectory(targetPath);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                Files.copy(path, outputFilePath.resolve(file.relativize(path)), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });

        return adapter.createUri(outputDirectory, fileName);
    }
}
