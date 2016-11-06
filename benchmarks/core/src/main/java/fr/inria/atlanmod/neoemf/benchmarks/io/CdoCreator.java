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
import fr.inria.atlanmod.neoemf.benchmarks.util.cdo.EmbeddedCDOServer;

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

public class CdoCreator extends AbstractCreator {

    private static Creator INSTANCE;

    private CdoCreator() {
    }

    public static Creator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CdoCreator();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        CdoCreator.getInstance().createAll();
    }

    @Override
    public String getBaseName() {
        return "cdo";
    }

    @Override
    public String getResourceName() {
        return "cdoresource";
    }

    @Override
    public Class<?> getAssociatedClass() {
        return org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.class;
    }

    @Override
    public File create(String in, String out) {
        File file = new File(out);

        if (file.exists()) {
            return file;
        }

        try {
            URI sourceUri = URI.createFileURI(in);

            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

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
                    LOG.info("Commiting");
                    transaction.commit();
                    LOG.info("Commit done");
                }

                transaction.close();
                session.close();
            }
            finally {
                server.close();
            }

            sourceResource.unload();
            targetResource.unload();
        }
        catch (Exception e) {
            LOG.error(e);
            return null;
        }
        return file;
    }
}
