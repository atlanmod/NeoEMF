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
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class CdoBackend extends AbstractBackend {

    public static final String NAME = "cdo";

    private static final String RESOURCE_EXTENSION = "cdo";
    private static final String STORE_EXTENSION = "resource"; // -> cdo.resource

    private static final Class<?> EPACKAGE_CLASS = org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.class;

    private EmbeddedCDOServer server;
    private CDOSession session;
    private CDOTransaction transaction;

    @Override
    protected String getResourceExtension() {
        return RESOURCE_EXTENSION;
    }

    @Override
    protected String getStoreExtension() {
        return STORE_EXTENSION;
    }

    @Override
    protected Class<?> getEPackageClass() {
        return EPACKAGE_CLASS;
    }

    @Override
    protected File create(File inputFile, Path outputPath) throws Exception {
        File outputFile = outputPath.toFile();

        if (outputFile.exists()) {
            LOG.info("Already existing resource : " + outputFile);
            return outputFile;
        }

        URI sourceUri = URI.createFileURI(inputFile.getAbsolutePath());

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
        }).callWithMemoryAndTime();

        Resource targetResource;

        EmbeddedCDOServer server = new EmbeddedCDOServer(outputPath);
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

        return outputFile;
    }

    @Override
    public Map<Object, Object> getSaveOptions() {
        Map<Object, Object> saveOpts = new HashMap<>();
        saveOpts.put(CDOResource.OPTION_SAVE_OVERRIDE_TRANSACTION, transaction);
        return saveOpts;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Resource load(File file) throws Exception {
        Resource resource;

        getEPackageClass().getMethod("init").invoke(null);

        server = new EmbeddedCDOServer(file.toPath());
        server.run();

        session = server.openSession();
        ((CDONet4jSession) session).options().setCommitTimeout(50 * 1000);

        transaction = session.openTransaction();

        resource = transaction.getRootResource();

        return resource;
    }

    @Override
    public void unload(Resource resource) {
        if (!isNull(transaction) && !transaction.isClosed()) {
            transaction.close();
        }

        if (!isNull(session) && !session.isClosed()) {
            session.close();
        }

        if (!isNull(server) && !server.isClosed()) {
            server.close();
        }

        if (!isNull(resource) && resource.isLoaded()) {
            resource.unload();
        }
    }
}
