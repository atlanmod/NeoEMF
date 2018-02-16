/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.resource;

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.Migrator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link StoreCreator} that uses the direct importer from the `neoemf-io` module (NeoEMF only).
 */
@ParametersAreNonnullByDefault
final class DirectStoreCreator implements StoreCreator {

    @Nonnull
    @Override
    public File getOrCreateStore(File file, ImmutableConfig config, Adapter.Internal adapter, Path dir) throws IOException {
        File targetFile = dir.resolve(StoreCreator.getTargetFileName(file, adapter)).toFile();

        if (targetFile.exists()) {
            return targetFile;
        }

        Log.info("Creating store with NeoEMF importer");

        adapter.initAndGetEPackage();

        Log.info("Migrating resource content...");

        try (DataMapper mapper = adapter.createMapper(targetFile, config)) {
            Migrator.fromXmi(file)
                    .toMapper(mapper)
                    .migrate();
        }

        return targetFile;
    }
}
