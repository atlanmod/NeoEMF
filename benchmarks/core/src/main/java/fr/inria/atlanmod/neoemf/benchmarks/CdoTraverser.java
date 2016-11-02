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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
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
import java.util.Arrays;

public class CdoTraverser {

    private static final Logger LOG = LogManager.getLogger();

    private static final String IN = "input";

    private static final String REPO_NAME = "reponame";

    private static final String EPACKAGE_CLASS = "epackage_class";

    public static void main(String[] args) {
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

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine commandLine = parser.parse(options, args);

            String repositoryDir = commandLine.getOptionValue(IN);
            String repositoryName = commandLine.getOptionValue(REPO_NAME);

            Class<?> inClazz = CdoTraverser.class.getClassLoader().loadClass(commandLine.getOptionValue(EPACKAGE_CLASS));
            inClazz.getMethod("init").invoke(null);

            EmbeddedCDOServer server = new EmbeddedCDOServer(repositoryDir, repositoryName);
            try {
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
            finally {
                server.stop();
            }
        }
        catch (ParseException e) {
            LOG.error(e.toString());
            LOG.error("Current arguments: " + Arrays.toString(args));
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar <this-file.jar>", options, true);
        }
        catch (Throwable e) {
            LOG.error(e.toString());
        }
    }
}
