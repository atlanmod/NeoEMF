package fr.inria.atlanmod.neoemf.benchmarks.resource;

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.Migrator;
import fr.inria.atlanmod.neoemf.option.PersistenceOptions;

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
    public File getOrCreateStore(File file, PersistenceOptions options, Adapter.Internal adapter, Path dir) throws IOException {
        File targetFile = dir.resolve(StoreCreator.getTargetFileName(file, adapter)).toFile();

        if (targetFile.exists()) {
            return targetFile;
        }

        Log.info("Creating store with NeoEMF importer");

        adapter.initAndGetEPackage();

        Log.info("Migrating resource content...");

        try (DataMapper mapper = adapter.createMapper(targetFile, options)) {
            Migrator.fromXmi(file)
                    .toMapper(mapper)
                    .migrate();
        }

        return targetFile;
    }
}
