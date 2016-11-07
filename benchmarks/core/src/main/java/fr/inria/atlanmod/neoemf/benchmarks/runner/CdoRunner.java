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

package fr.inria.atlanmod.neoemf.benchmarks.runner;

import fr.inria.atlanmod.neoemf.benchmarks.Creator;
import fr.inria.atlanmod.neoemf.benchmarks.io.CdoCreator;
import fr.inria.atlanmod.neoemf.benchmarks.util.cdo.EmbeddedCDOServer;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.net4j.CDONet4jSession;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CdoRunner extends AbstractQueryRunner {

    private EmbeddedCDOServer server;
    private CDOSession session;
    private CDOTransaction transaction;

    @Override
    protected Creator getCreator() {
        return CdoCreator.getInstance();
    }

    @Override
    public void createResource() throws IOException {
        org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

        server = new EmbeddedCDOServer(getPath());
        server.run();

        session = server.openSession();
        transaction = session.openTransaction();
        resource = transaction.getRootResource();

        ((CDONet4jSession) session).options().setCommitTimeout(50 * 1000);
    }

    @Override
    public void destroyResource() {
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
    protected Map<Object, Object> getSaveOptions() {
        Map<Object, Object> saveOpts = new HashMap<>();
        saveOpts.put(CDOResource.OPTION_SAVE_OVERRIDE_TRANSACTION, transaction);
        return saveOpts;
    }
}
