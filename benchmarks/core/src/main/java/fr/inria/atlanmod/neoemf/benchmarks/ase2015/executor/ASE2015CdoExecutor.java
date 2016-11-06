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

package fr.inria.atlanmod.neoemf.benchmarks.ase2015.executor;

import fr.inria.atlanmod.neoemf.benchmarks.ase2015.ASE2015QueryExecutor;
import fr.inria.atlanmod.neoemf.benchmarks.ase2015.query.ASE2015QueryFactory;
import fr.inria.atlanmod.neoemf.benchmarks.executor.CdoExecutor;
import fr.inria.atlanmod.neoemf.benchmarks.util.cdo.EmbeddedCDOServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.ecore.resource.Resource;

public class ASE2015CdoExecutor extends CdoExecutor implements ASE2015QueryExecutor {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void queryASE2015GetBranchStatements(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                ASE2015QueryFactory.queryAse2015GetCommentsTagContent(resource).callWithMemoryAndTime();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void queryASE2015Grabats09(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                ASE2015QueryFactory.queryAse2015Grabats09(resource).callWithMemoryAndTime();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void queryASE2015InvisibleMethodDeclarations(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                ASE2015QueryFactory.queryInvisibleMethodDeclarations(resource).callWithMemoryAndTime();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void queryASE2015SpecificInvisibleMethodDeclarations(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                ASE2015QueryFactory.queryAse2015SpecificInvisibleMethodDeclarations(resource).callWithMemoryAndTime();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void queryASE2015ThrownExceptions(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                ASE2015QueryFactory.queryAse2015ThrownExceptions(resource).callWithMemoryAndTime();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }
}
