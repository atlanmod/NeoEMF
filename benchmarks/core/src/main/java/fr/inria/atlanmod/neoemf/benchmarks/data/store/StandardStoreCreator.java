/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.data.store;

import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link StoreCreator} that uses standard EMF for store creation.
 */
@ParametersAreNonnullByDefault
public class StandardStoreCreator implements StoreCreator {

    /**
     *
     */
    @Nonnull
    private final Adapter.Internal adapter;

    /**
     * Constructs a new {@code StandardStoreCreator}.
     *
     * @param adapter
     */
    public StandardStoreCreator(Adapter.Internal adapter) {
        this.adapter = adapter;
    }

    @Override
    public void create(File resourceFile, URI uri, ImmutableConfig config) throws IOException {
        adapter.initAndGetEPackage();
        org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.init();

        URI sourceUri = URI.createFileURI(resourceFile.getAbsolutePath());
        Resource sourceResource = new ResourceSetImpl().createResource(sourceUri);

        Map<String, Object> loadOpts = new HashMap<>();
        if (Objects.equals("zxmi", sourceUri.fileExtension())) {
            loadOpts.put(XMIResource.OPTION_ZIP, true);
        }
        sourceResource.load(loadOpts);

        Resource targetResource = adapter.create(uri);
        adapter.save(targetResource, config);

        targetResource.getContents().addAll(sourceResource.getContents());

        adapter.save(targetResource, config);

        sourceResource.unload();
        adapter.unload(targetResource);
    }
}
