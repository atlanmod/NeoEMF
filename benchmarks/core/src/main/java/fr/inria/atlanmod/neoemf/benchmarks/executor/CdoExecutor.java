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

package fr.inria.atlanmod.neoemf.benchmarks.executor;

import fr.inria.atlanmod.neoemf.benchmarks.creator.CdoCreator;
import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory;
import fr.inria.atlanmod.neoemf.benchmarks.util.cdo.EmbeddedCDOServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.cdo.net4j.CDONet4jSession;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;

import java.io.IOException;
import java.util.UUID;

public class CdoExecutor extends AbstractQueryExecutor {

    private static final Logger LOG = LogManager.getLogger();

    private EmbeddedCDOServer server;
    private CDOSession session;
    private CDOTransaction transaction;

    @Override
    public void loadResourcesAndStores() {
        System.out.println();
        LOG.info("Initializing resources");
        PATHS = CdoCreator.getInstance().createAll();
        LOG.info("Resources initialized");
    }

    @Override
    public void createResource() throws IOException {
        org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

        server = new EmbeddedCDOServer(getPath());
        server.run();

        session = server.openSession();
        transaction = session.openTransaction();
        resource = transaction.getRootResource();
    }

    @Override
    public void destroyResource() {
        transaction.close();
        session.close();
        server.stop();

        if (resource != null && resource.isLoaded()) {
            resource.unload();
        }
    }

    @Override
    public void queryRenameAllMethods() {
        try {
            ((CDONet4jSession) session).options().setCommitTimeout(50 * 1000);
            String name = UUID.randomUUID().toString();
            QueryFactory.queryRenameAllMethods(resource, name).callWithTime();
            transaction.commit();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }
}
