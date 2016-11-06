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

package fr.inria.atlanmod.neoemf.benchmarks.io;

import fr.inria.atlanmod.neoemf.benchmarks.Creator;
import fr.inria.atlanmod.neoemf.benchmarks.query.Query;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.neo4j.resources.BlueprintsNeo4jResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions.StoreOption;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceImpl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeoGraphCreator extends AbstractCreator{

    private static Creator INSTANCE;

    private NeoGraphCreator() {
    }

    public static Creator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NeoGraphCreator();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        NeoGraphCreator.getInstance().createAll();
    }

    @Override
    public String getBaseName() {
        return "neoemf";
    }

    @Override
    public String getResourceName() {
        return "neoemfgraphresource";
    }

    @Override
    public Class<?> getAssociatedClass() {
        return org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.class;
    }

    @Override
    public File create(String in, String out) {
        File destFile = new File(out);

        if (destFile.exists()) {
            return destFile;
        }

        try {
            PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.NEO_GRAPH_SCHEME, BlueprintsPersistenceBackendFactory.getInstance());

            URI sourceUri = URI.createFileURI(in);
            URI targetUri = NeoBlueprintsURI.createNeoGraphURI(destFile);

            org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, PersistentResourceFactory.getInstance());

            Resource sourceResource = resourceSet.createResource(sourceUri);
            Map<String, Object> loadOpts = new HashMap<>();
            if ("zxmi".equals(sourceUri.fileExtension())) {
                loadOpts.put(XMIResource.OPTION_ZIP, Boolean.TRUE);
            }

            new Query<Void>() {
                @Override
                public Void call() throws Exception {
                    LOG.info("Loading source resource");
                    sourceResource.load(loadOpts);
                    LOG.info("Source resource loaded");
                    return null;
                }
            }.callWithMemory();

            Resource targetResource = resourceSet.createResource(targetUri);

            Map<String, Object> saveOpts = new HashMap<>();
            saveOpts.put(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE, BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_TYPE_NEO4J);

            List<StoreOption> storeOptions = new ArrayList<>();
            storeOptions.add(BlueprintsResourceOptions.EStoreGraphOption.AUTOCOMMIT);
            saveOpts.put(BlueprintsResourceOptions.STORE_OPTIONS, storeOptions);
            targetResource.save(saveOpts);
            targetResource.getContents().clear();

            {
                LOG.info("Start moving elements");
                targetResource.getContents().addAll(sourceResource.getContents());
                LOG.info("End moving elements");
                LOG.info("Start saving");
                targetResource.save(saveOpts);
                LOG.info("Saved");
            }

            sourceResource.unload();
            if (targetResource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) targetResource);
            }
            else {
                targetResource.unload();
            }
        }
        catch (Exception e) {
            LOG.error(e);
            return null;
        }
        return destFile;
    }
}
