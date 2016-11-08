/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.benchmarks.backend;

import fr.inria.atlanmod.neoemf.benchmarks.query.Query;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.map.util.NeoMapURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceImpl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class NeoMapBackend extends AbstractNeoBackend {

    public static final String NAME = "neo-map";

    private static final String STORE_EXTENSION = "map.resource"; // -> neoemf.map.resource

    @Override
    protected String getStoreExtension() {
        return STORE_EXTENSION;
    }

    @Override
    protected File create(File inputFile, Path outputPath) throws Exception {
        File outputFile = outputPath.toFile();

        if (outputFile.exists()) {
            LOG.info("Already existing resource : " + outputFile);
            return outputFile;
        }

        PersistenceBackendFactoryRegistry.register(NeoMapURI.NEO_MAP_SCHEME, MapPersistenceBackendFactory.getInstance());

        URI sourceUri = URI.createFileURI(inputFile.getAbsolutePath());
        URI targetUri = NeoMapURI.createNeoMapURI(outputFile);

        org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, PersistentResourceFactory.getInstance());

        Resource sourceResource = resourceSet.createResource(sourceUri);
        Map<Object, Object> loadOpts = new HashMap<>();
        if ("zxmi".equals(sourceUri.fileExtension())) {
            loadOpts.put(XMIResource.OPTION_ZIP, Boolean.TRUE);
        }

        ((Query<Void>) () -> {
            Query.LOG.info("Loading source resource");
            sourceResource.load(loadOpts);
            Query.LOG.info("Source resource loaded");
            return null;
        }).callWithMemory();

        Resource targetResource = resourceSet.createResource(targetUri);

        targetResource.getContents().clear();

        {
            LOG.info("Start moving elements");
            targetResource.getContents().addAll(sourceResource.getContents());
            LOG.info("End moving elements");
        }

        {
            LOG.info("Start saving");
            targetResource.save(getSaveOptions());
            LOG.info("Saved");
        }

        sourceResource.unload();
        if (targetResource instanceof PersistentResourceImpl) {
            PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) targetResource);
        }
        else {
            targetResource.unload();
        }

        return outputFile;
    }

    @Override
    public Resource load(File file) throws Exception {
        Resource resource;

        PersistenceBackendFactoryRegistry.register(NeoMapURI.NEO_MAP_SCHEME, MapPersistenceBackendFactory.getInstance());

        URI uri = NeoMapURI.createNeoMapURI(file);

        getEPackageClass().getMethod("init").invoke(null);

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, PersistentResourceFactory.getInstance());

        resource = resourceSet.createResource(uri);
        resource.load(getLoadOptions());

        return resource;
    }
}
