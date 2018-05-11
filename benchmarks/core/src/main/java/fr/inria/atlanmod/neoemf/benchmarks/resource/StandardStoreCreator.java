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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link StoreCreator} that uses standard EMF for store creation.
 */
@ParametersAreNonnullByDefault
public final class StandardStoreCreator implements StoreCreator {

    @Nonnull
    @Override
    public File getOrCreateStore(File file, ImmutableConfig config, Adapter.Internal adapter, Path dir) throws IOException {
        File targetFile = dir.resolve(StoreCreator.getTargetFileName(file, adapter)).toFile();

        if (targetFile.exists()) {
            return targetFile;
        }

        Log.info("Creating store with standard EMF");

        org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.init();

        Log.info("Loading resource from: {0}", file);

        URI sourceUri = URI.createFileURI(file.getAbsolutePath());
        Resource sourceResource = new ResourceSetImpl().createResource(sourceUri);

        adapter.initAndGetEPackage();

        Map<String, Object> loadOpts = new HashMap<>();
        if (Objects.equals("zxmi", sourceUri.fileExtension())) {
            loadOpts.put(XMIResource.OPTION_ZIP, true);
        }
        sourceResource.load(loadOpts);

        Log.info("Migrating resource content...");

        Resource targetResource = adapter.createResource(targetFile);
        adapter.save(targetResource, config);

        targetResource.getContents().addAll(sourceResource.getContents());

        Log.info("Saving resource to: {0}", targetResource.getURI());
        adapter.save(targetResource, config);

        sourceResource.unload();
        adapter.unload(targetResource);

        return targetFile;
    }
}
