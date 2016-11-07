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
import fr.inria.atlanmod.neoemf.benchmarks.util.cdo.EmbeddedCDOServer;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.net4j.CDONet4jSession;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CdoBackend extends AbstractBackend {

    private EmbeddedCDOServer server;
    private CDOSession session;
    private CDOTransaction transaction;

    @Override
    public String getName() {
        return "cdo";
    }

    @Override
    public String getResourceName() {
        return "cdoresource";
    }

    @Override
    public Class<?> getEPackageClass() {
        return org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.class;
    }

    @Override
    public Resource loadResource(String path) throws Exception {
        Resource resource;

        getEPackageClass().getMethod("init").invoke(null);

        server = new EmbeddedCDOServer(path);
        server.run();

        session = server.openSession();
        ((CDONet4jSession) session).options().setCommitTimeout(50 * 1000);

        transaction = session.openTransaction();

        resource = transaction.getRootResource();

        return resource;
    }

    @Override
    public void unloadResource(Resource resource) {
        if (transaction != null && !transaction.isClosed()) {
            transaction.close();
        }

        if (session != null && !session.isClosed()) {
            session.close();
        }

        if (server != null && !server.isClosed()) {
            server.close();
        }

        if (resource != null && resource.isLoaded()) {
            resource.unload();
        }
    }

    @Override
    public Map<Object, Object> getSaveOptions() {
        Map<Object, Object> saveOpts = new HashMap<>();
        saveOpts.put(CDOResource.OPTION_SAVE_OVERRIDE_TRANSACTION, transaction);
        return saveOpts;
    }

    @Override
    public File create(String in, String out) throws Exception {
        File file = new File(out);

        if (file.exists()) {
            return file;
        }

        URI sourceUri = URI.createFileURI(in);

        org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

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

        Resource targetResource;

        EmbeddedCDOServer server = new EmbeddedCDOServer(out);
        try {
            server.run();
            CDOSession session = server.openSession();
            CDOTransaction transaction = session.openTransaction();
            targetResource = transaction.getRootResource();

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

            transaction.close();
            session.close();
        }
        finally {
            server.close();
        }

        sourceResource.unload();
        targetResource.unload();

        return file;
    }
}
