/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.resource;

import fr.inria.atlanmod.commons.io.MoreFiles;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.benchmarks.io.Workspace;

import org.eclipse.emf.ecore.resource.Resource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link ResourceTransformer} that migrate a {@link Resource} from a {@link org.eclipse.emf.ecore.EPackage} to
 * another. The target {@link org.eclipse.emf.ecore.EPackage} is determined by {@link
 * Adapter.Internal#initAndGetEPackage()}.
 */
@ParametersAreNonnullByDefault
final class ResourceMigrator implements ResourceTransformer {

    @Nonnull
    @Override
    public File transform(File file, Adapter.Internal adapter) throws IOException {
        String targetFileName = MoreFiles.nameWithoutExtension(file.getAbsolutePath()) + "." + adapter.getResourceExtension() + ".zxmi";
        File targetFile = Workspace.getResourcesDirectory().resolve(targetFileName).toFile();

        if (targetFile.exists()) {
            return targetFile;
        }

        Log.info("Adapting resource to URI {0}", adapter.initAndGetEPackage().getNsURI());

        // Replace the 'xmlns:java' value
        Charset charset = StandardCharsets.UTF_8;

        try (ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(targetFile.toPath()))) {
            out.putNextEntry(new ZipEntry("ResourceContents"));

            try (BufferedReader reader = Files.newBufferedReader(file.toPath(), charset); BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, charset))) {
                boolean migrated = false;

                String line;
                while (nonNull(line = reader.readLine())) {
                    if (!migrated && line.contains("xmlns:java")) {
                        writer.write(line.replaceFirst("(xmlns:java=\")[^\"]*(\")", "$1" + adapter.initAndGetEPackage().getNsURI() + "$2"));
                        migrated = true; // Only one occurence
                    }
                    else {
                        writer.write(line);
                    }
                    writer.newLine();
                }
            }
        }

        return targetFile;
    }
}
