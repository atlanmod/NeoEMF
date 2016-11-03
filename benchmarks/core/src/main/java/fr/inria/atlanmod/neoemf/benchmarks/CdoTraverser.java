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
import fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.ecore.resource.Resource;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

public class CdoTraverser {

    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            Map<String, String> cli = processCommandLineArgs(args);

            String repositoryDir = cli.get(CommandLineUtil.Key.IN);
            String repositoryName = cli.get(CommandLineUtil.Key.REPO_NAME);

            Class<?> inClazz = CdoTraverser.class.getClassLoader().loadClass(cli.get(CommandLineUtil.Key.EPACKAGE_CLASS));
            inClazz.getMethod("init").invoke(null);

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(repositoryDir, repositoryName)) {
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

    private static Map<String, String> processCommandLineArgs(String... args) throws ParseException {
        Options options = new Options();

        options.addOption(Option.builder(CommandLineUtil.Key.IN)
                .argName("INPUT")
                .desc("Input CDO resource directory")
                .numberOfArgs(1)
                .required()
                .build());

        options.addOption(Option.builder(CommandLineUtil.Key.EPACKAGE_CLASS)
                .argName("CLASS")
                .desc("FQN of EPackage implementation class")
                .numberOfArgs(1)
                .required()
                .build());

        options.addOption(Option.builder(CommandLineUtil.Key.REPO_NAME)
                .argName("REPO_NAME")
                .desc("CDO Repository name")
                .numberOfArgs(1)
                .required()
                .build());

        return CommandLineUtil.getOptionsValues(options, args);
    }
}
