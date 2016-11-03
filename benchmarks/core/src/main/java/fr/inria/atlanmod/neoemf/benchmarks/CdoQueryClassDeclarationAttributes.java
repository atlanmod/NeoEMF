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
import fr.inria.atlanmod.neoemf.benchmarks.queries.JavaQueries;
import fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.NamedElement;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.EPACKAGE_CLASS;
import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.IN;
import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.REPO_NAME;

public class CdoQueryClassDeclarationAttributes {

    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            Map<String, String> cli = processCommandLineArgs(args);

            String repositoryDir = cli.get(IN);
            String repositoryName = cli.get(REPO_NAME);

            Class<?> inClazz = CdoQueryClassDeclarationAttributes.class.getClassLoader().loadClass(cli.get(EPACKAGE_CLASS));
            inClazz.getMethod("init").invoke(null);

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(repositoryDir, repositoryName)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                Resource resource = transaction.getRootResource();

                {
                    LOG.info("Start query");
                    Instant begin = Instant.now();
                    HashMap<String, EList<NamedElement>> map = JavaQueries.getClassDeclarationAttributes(resource);
                    Instant end = Instant.now();
                    LOG.info("End query");
                    LOG.info("Query result contains {0} elements", map.entrySet().size());
                    LOG.info("Time spent: {0}", Duration.between(begin, end));
                }

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    private static Map<String, String> processCommandLineArgs(String... args) throws ParseException {
        Options options = new Options();

        options.addOption(Option.builder(IN)
                .argName("INPUT")
                .desc("Input CDO resource directory")
                .numberOfArgs(1)
                .required()
                .build());

        options.addOption(Option.builder(EPACKAGE_CLASS)
                .argName("CLASS")
                .desc("FQN of EPackage implementation class")
                .numberOfArgs(1)
                .required()
                .build());

        options.addOption(Option.builder(REPO_NAME)
                .argName("REPO_NAME")
                .desc("CDO Repository name")
                .numberOfArgs(1)
                .required()
                .build());

        return CommandLineUtil.getValues(options, args);
    }
}
