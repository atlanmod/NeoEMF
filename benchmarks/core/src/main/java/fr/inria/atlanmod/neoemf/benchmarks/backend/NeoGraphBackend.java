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
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceImpl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class NeoGraphBackend extends AbstractNeoBackend {

    public static final String NAME = "neo-graph";

    @Override
    protected String getResourceExportExtension() {
        return "tinker.resource";
    }

    @Override
    protected File create(String in, String out) throws Exception {
        File destFile = new File(out);

        if (destFile.exists()) {
            return destFile;
        }

        PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.NEO_GRAPH_SCHEME, BlueprintsPersistenceBackendFactory.getInstance());

        URI sourceUri = URI.createFileURI(in);
        URI targetUri = NeoBlueprintsURI.createNeoGraphURI(destFile);

        org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, PersistentResourceFactory.getInstance());

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

        return destFile;
    }

    @Override
    public Resource loadResource(String path) throws Exception {
        Resource resource;

        PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.NEO_GRAPH_SCHEME, BlueprintsPersistenceBackendFactory.getInstance());

        URI uri = NeoBlueprintsURI.createNeoGraphURI(new File(path));

        getEPackageClass().getMethod("init").invoke(null);

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, PersistentResourceFactory.getInstance());

        resource = resourceSet.createResource(uri);
        resource.load(getLoadOptions());

        return resource;
    }
}
