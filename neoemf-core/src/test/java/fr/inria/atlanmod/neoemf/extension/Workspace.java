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

import org.junit.rules.ExternalResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;

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
        if (nonNull(temporaryFolder) && temporaryFolder.toFile().exists()) {
            deleteAll(temporaryFolder);
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
     * Deletes the given {@code path} if it exists.
     *
     * @param path the file/directory path to delete
     *
     * @return {@code true} if the path has correctly been deleted
     */
    private void deleteAll(Path path) {
        try {
            if (path.toFile().isDirectory()) {
                Files.walk(path, FileVisitOption.FOLLOW_LINKS)
                        .forEach(p -> {
                            try {
                                Files.deleteIfExists(p);
                            }
                            catch (Exception ignored) {
                            }
                        });
            }
            Files.deleteIfExists(path);
        }
        catch (Exception e) {
            try {
                Runtime.getRuntime().addShutdownHook(new Thread(() -> deleteAll(path)));
            }
            catch (IllegalStateException ignored) {
            }
        }
    }
}
