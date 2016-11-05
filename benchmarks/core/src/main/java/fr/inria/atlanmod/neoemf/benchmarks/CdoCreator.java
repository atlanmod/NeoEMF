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

package fr.inria.atlanmod.neoemf.benchmarks;

import fr.inria.atlanmod.neoemf.benchmarks.cdo.EmbeddedCDOServer;
import fr.inria.atlanmod.neoemf.benchmarks.util.MessageUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.util.HashMap;
import java.util.Map;

public class CdoCreator {

    private static final Logger LOG = LogManager.getLogger();

    // in =     ${java.io.tmpdir}/neoemf-benchmarks/*.cdo.zxmi
    // out =    ${java.io.tmpdir}/neoemf-benchmarks/${in.filename}.cdoresource

    public void create(String in, String out) {
        try {
            URI srcUri = URI.createFileURI(in);

            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();

            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

            Resource sourceResource = resourceSet.createResource(srcUri);
            Map<String, Object> loadOpts = new HashMap<>();
            if ("zxmi".equals(srcUri.fileExtension())) {
                loadOpts.put(XMIResource.OPTION_ZIP, Boolean.TRUE);
            }

            {
                Runtime.getRuntime().gc();
                long initialUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                LOG.info("Used memory before loading: {0}", MessageUtil.byteCountToDisplaySize(initialUsedMemory));

                LOG.info("Loading source resource");
                sourceResource.load(loadOpts);
                LOG.info("Source resource loaded");

                Runtime.getRuntime().gc();
                long finalUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                LOG.info("Used memory after loading: {0}", MessageUtil.byteCountToDisplaySize(finalUsedMemory));
                LOG.info("Memory use increase: {0}", MessageUtil.byteCountToDisplaySize(finalUsedMemory - initialUsedMemory));
            }

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(out)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                transaction.getRootResource().getContents().clear();

                {
                    LOG.info("Start moving elements");
                    transaction.getRootResource().getContents().addAll(sourceResource.getContents());
                    LOG.info("End moving elements");
                    LOG.info("Commiting");
                    transaction.commit();
                    LOG.info("Commit done");
                }

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }
}
