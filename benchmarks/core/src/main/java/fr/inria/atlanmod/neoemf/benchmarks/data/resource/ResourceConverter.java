/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.data.resource;

import fr.inria.atlanmod.commons.io.MoreFiles;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.benchmarks.io.LocalWorkspace;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link ResourceTransformer} that converts a standard {@link Resource} to an identifiable {@link Resource}, that
 * uses {@code xmi:id}.
 */
@ParametersAreNonnullByDefault
final class ResourceConverter implements ResourceTransformer {

    @Nonnull
    @Override
    public File transform(File file, Adapter.Internal adapter) throws IOException {
        String targetFileName = MoreFiles.nameWithoutExtension(file.getAbsolutePath()) + "-id.xmi";
        File targetFile = LocalWorkspace.getResourcesDirectory().resolve(targetFileName).toFile();

        if (targetFile.exists()) {
            return targetFile;
        }

        Log.info("Converting resource with xmi:id");

        org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.init();

        final ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl() {
            @Override
            public Resource createResource(URI uri) {
                return new XMIResourceImpl(uri) {
                    @Override
                    protected boolean useUUIDs() {
                        return true;
                    }
                };
            }
        });

        Resource resource = resourceSet.createResource(URI.createFileURI(file.getAbsolutePath()));
        resource.load(Collections.emptyMap());

        try (OutputStream out = new FileOutputStream(targetFile)) {
            resource.save(out, Collections.emptyMap());
        }

        resource.unload();

        return targetFile;
    }
}
