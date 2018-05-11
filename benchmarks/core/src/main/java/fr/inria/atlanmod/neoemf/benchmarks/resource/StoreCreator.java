/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.resource;

import fr.inria.atlanmod.commons.io.MoreFiles;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;

import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;

@ParametersAreNonnullByDefault
interface StoreCreator {

    @Nonnull
    static String getTargetFileName(File resourceFile, Adapter.Internal adapter) {
        Resources.checkValid(resourceFile.getName());
        checkArgument(resourceFile.exists(), "Resource '%s' does not exist", resourceFile);

        String targetFileName = MoreFiles.nameWithoutExtension(resourceFile.getAbsolutePath());

        // Has been converted ?
        if (targetFileName.contains("-id." + adapter.getResourceExtension() + ".")) {
            targetFileName = targetFileName.replaceFirst("-id", "");
        }

        final String additionalExtension = adapter.getStoreExtension();
        return targetFileName + (additionalExtension.isEmpty() ? "" : "." + additionalExtension);
    }

    /**
     * Retrieves or creates a new {@link Resource} (a {@link fr.inria.atlanmod.neoemf.resource.PersistentResource} in
     * case of NeoEMF) from the given {@code file}, and stores it to the given {@code targetAdapter}, located in {@code
     * dir}.
     *
     * @param file    the resource file
     * @param adapter the adapter where to store the resource
     * @param dir     the location of the adapter
     *
     * @return the created file
     *
     * @throws IOException if a error occurs during the creation of the store
     */
    @Nonnull
    File getOrCreateStore(File file, ImmutableConfig config, Adapter.Internal adapter, Path dir) throws IOException;
}
