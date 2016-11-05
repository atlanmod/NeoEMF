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

import com.google.common.collect.Iterators;

import fr.inria.atlanmod.neoemf.benchmarks.cdo.EmbeddedCDOServer;
import fr.inria.atlanmod.neoemf.benchmarks.queries.Queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.cdo.net4j.CDONet4jSession;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.ecore.resource.Resource;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

// in = ${java.io.tmpdir}/neoemf-benchmarks/temp/*.cdoresource

public class CdoQuery {

    private static final Logger LOG = LogManager.getLogger();

    public void queryClassDeclarationAttributes(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                Queries.getClassDeclarationAttributes(resource).callWithTimeSpent();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    public void queryGrabats(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                Queries.grabats09(resource).callWithTimeSpent();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    public void queryInvisibleMethodDeclarations(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                Queries.getInvisibleMethodDeclarations(resource).callWithTimeSpent();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    public void queryOrphanNonPrimitiveTypes(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                Queries.getOrphanNonPrimitivesTypes(resource).callWithTimeSpent();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    public void queryRenameAllMethods(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                ((CDONet4jSession) session).options().setCommitTimeout(50 * 1000);
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                String name = UUID.randomUUID().toString();
                Queries.renameAllMethods(resource, name).callWithTimeSpent();
                transaction.commit();

//				{
//					transaction.close();
//					session.close();
//
//					session = server.openSession();
//					transaction = session.openTransaction();
//					resource = transaction.getRootResource();
//
//					EList<? extends EObject> methodList = Queries.getAllInstances(resource, JavaPackage.eINSTANCE.getMethodDeclaration());
//					int i = 0;
//					for (EObject eObject: methodList) {
//						MethodDeclaration method = (MethodDeclaration) eObject;
//						if (name.equals(method.getName())) {
//							i++;
//						}
//					}
//					LOG.info("Renamed {0} methods", i);
//				}

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    public void queryThrownExceptionsPerPackage(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                Queries.getThrownExceptionsPerPackage(resource).callWithTimeSpent();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    public void queryUnusedMethodsList(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                Queries.getUnusedMethodsList(resource).callWithTimeSpent();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    public void queryUnusedMethodsLoop(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                Queries.getUnusedMethodsLoop(resource).callWithTimeSpent(); // Query result (loops)

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    public void traverse(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                LOG.info("Start counting");
                Instant begin = Instant.now();
                int count = Iterators.size(resource.getAllContents());
                Instant end = Instant.now();
                LOG.info("End counting");
                LOG.info("Resource {0} contains {1} elements", resource.getURI(), count);
                LOG.info("Time spent: {0}", Duration.between(begin, end));

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }
}
