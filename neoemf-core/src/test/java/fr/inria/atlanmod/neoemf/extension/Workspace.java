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

package fr.inria.atlanmod.neoemf.extension;

import fr.inria.atlanmod.common.concurrent.MoreExecutors;

import org.junit.rules.ExternalResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * An {@link ExternalResource} that manages temporary resources.
 */
@ParametersAreNonnullByDefault
public class Workspace extends ExternalResource {

    /**
     * The prefix for temporary folder.
     */
    private static final String PREFIX = "neoemf";

    /**
     * The temporary folder.
     */
    private Path temporaryFolder;

    @Override
    public void before() throws IOException {
        temporaryFolder = Files.createTempDirectory(PREFIX);
    }

    @Override
    public void after() {
        if (nonNull(temporaryFolder) && Files.exists(temporaryFolder)) {
            deleteDirectory(temporaryFolder);
        }
    }

    /**
     * Creates a new temporary file that begins with the given {@code prefix}.
     *
     * @param prefix the prefix of the file name
     *
     * @return a new {@code File} (not created)
     *
     * @throws IOException if an I/O error occurs
     */
    public File newFile(String prefix) throws IOException {
        Path createdFolder = Files.createTempDirectory(temporaryFolder, prefix);
        Files.deleteIfExists(createdFolder);
        return createdFolder.toFile();
    }

    /**
     * Deletes the given directory with all its files and sub-directories.
     *
     * @param directory the directory to delete
     */
    private void deleteDirectory(Path directory) {
        try {
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.deleteIfExists(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.deleteIfExists(dir);
                    return FileVisitResult.CONTINUE;
                }
            });

            Files.deleteIfExists(directory);
        }
        catch (Exception e) {
            try {
                MoreExecutors.executeAtExit(() -> deleteDirectory(directory));
            }
            catch (IllegalStateException ignored) {
            }
        }
    }
}
