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

import com.google.common.collect.Iterators;

import fr.inria.atlanmod.neoemf.benchmarks.QueryExecutor;
import fr.inria.atlanmod.neoemf.benchmarks.Traverser;
import fr.inria.atlanmod.neoemf.benchmarks.util.cdo.EmbeddedCDOServer;
import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.cdo.net4j.CDONet4jSession;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.ecore.resource.Resource;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

// in = BenchmarkUtil.getTestDirectory()/*.cdoresource

public class CdoExecutor implements QueryExecutor, Traverser {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void queryClassDeclarationAttributes(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                QueryFactory.getClassDeclarationAttributes(resource).callWithTimeSpent();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    @Override
    public void queryGrabats(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                QueryFactory.grabats09(resource).callWithTimeSpent();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    @Override
    public void queryInvisibleMethodDeclarations(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                QueryFactory.getInvisibleMethodDeclarations(resource).callWithTimeSpent();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    @Override
    public void queryOrphanNonPrimitiveTypes(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                QueryFactory.getOrphanNonPrimitivesTypes(resource).callWithTimeSpent();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    @Override
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
                QueryFactory.renameAllMethods(resource, name).callWithTimeSpent();
                transaction.commit();

//				{
//					transaction.close();
//					session.close();
//
//					session = server.openSession();
//					transaction = session.openTransaction();
//					resource = transaction.getRootResource();
//
//					EList<? extends EObject> methodList = QueryFactory.getAllInstances(resource, JavaPackage.eINSTANCE.getMethodDeclaration());
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

    @Override
    public void queryThrownExceptionsPerPackage(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                QueryFactory.getThrownExceptionsPerPackage(resource).callWithTimeSpent();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    @Override
    public void queryUnusedMethodsList(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                QueryFactory.getUnusedMethodsList(resource).callWithTimeSpent();

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    @Override
    public void queryUnusedMethodsLoop(String in) {
        try {
            org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.init();

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(in)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                QueryFactory.getUnusedMethodsLoop(resource).callWithTimeSpent(); // Query result (loops)

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    @Override
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
