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
import fr.inria.atlanmod.neoemf.benchmarks.queries.Queries;
import fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil;
import fr.inria.atlanmod.neoemf.benchmarks.util.MessageUtil;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.cdo.net4j.CDONet4jSession;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.ecore.resource.Resource;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.EPACKAGE_CLASS;
import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.IN;
import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.REPO_NAME;

public class CdoQueryRenameAllMethods extends CdoQuery {

    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            Map<String, String> cli = processCommandLineArgs(args);

            String repositoryDir = cli.get(IN);
            String repositoryName = cli.get(REPO_NAME);

            Class<?> inClazz = CdoQueryRenameAllMethods.class.getClassLoader().loadClass(cli.get(EPACKAGE_CLASS));
            inClazz.getMethod("init").invoke(null);

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(repositoryDir, repositoryName)) {
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
}
