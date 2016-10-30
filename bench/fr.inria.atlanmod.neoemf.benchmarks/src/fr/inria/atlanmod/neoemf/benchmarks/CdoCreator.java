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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CdoCreator {

    private static final Logger LOG = Logger.getLogger(CdoCreator.class.getName());

    private static final String IN = "input";

    private static final String OUT = "output";

    private static final String REPO_NAME = "reponame";

    private static final String EPACKAGE_CLASS = "epackage_class";

    public static void main(String[] args) {
        Options options = new Options();

        Option inputOpt = OptionBuilder.create(IN);
        inputOpt.setArgName("INPUT");
        inputOpt.setDescription("Input file");
        inputOpt.setArgs(1);
        inputOpt.setRequired(true);

        Option outputOpt = OptionBuilder.create(OUT);
        outputOpt.setArgName("OUTPUT");
        outputOpt.setDescription("Output directory");
        outputOpt.setArgs(1);
        outputOpt.setRequired(true);

        Option inClassOpt = OptionBuilder.create(EPACKAGE_CLASS);
        inClassOpt.setArgName("CLASS");
        inClassOpt.setDescription("FQN of EPackage implementation class");
        inClassOpt.setArgs(1);
        inClassOpt.setRequired(true);

        Option repoOpt = OptionBuilder.create(REPO_NAME);
        repoOpt.setArgName("REPO_NAME");
        repoOpt.setDescription("CDO Repository name");
        repoOpt.setArgs(1);
        repoOpt.setRequired(true);

        options.addOption(inputOpt);
        options.addOption(outputOpt);
        options.addOption(inClassOpt);
        options.addOption(repoOpt);

        CommandLineParser parser = new PosixParser();

        try {
            CommandLine commandLine = parser.parse(options, args);
            URI sourceUri = URI.createFileURI(commandLine.getOptionValue(IN));

            String outputDir = commandLine.getOptionValue(OUT);
            String repositoryName = commandLine.getOptionValue(REPO_NAME);

            Class<?> inClazz = CdoCreator.class.getClassLoader().loadClass(commandLine.getOptionValue(EPACKAGE_CLASS));
            inClazz.getMethod("init").invoke(null);

            ResourceSet resourceSet = new ResourceSetImpl();

            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

            Resource sourceResource = resourceSet.createResource(sourceUri);
            Map<String, Object> loadOpts = new HashMap<>();
            if ("zxmi".equals(sourceUri.fileExtension())) {
                loadOpts.put(XMIResource.OPTION_ZIP, Boolean.TRUE);
            }

            Runtime.getRuntime().gc();
            long initialUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            LOG.log(Level.INFO, MessageFormat.format("Used memory before loading: {0}",
                    MessageUtil.byteCountToDisplaySize(initialUsedMemory)));
            LOG.log(Level.INFO, "Loading source resource");
            sourceResource.load(loadOpts);
            LOG.log(Level.INFO, "Source resource loaded");
            Runtime.getRuntime().gc();
            long finalUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            LOG.log(Level.INFO, MessageFormat.format("Used memory after loading: {0}",
                    MessageUtil.byteCountToDisplaySize(finalUsedMemory)));
            LOG.log(Level.INFO, MessageFormat.format("Memory use increase: {0}",
                    MessageUtil.byteCountToDisplaySize(finalUsedMemory - initialUsedMemory)));

            EmbeddedCDOServer server = new EmbeddedCDOServer(outputDir, repositoryName);
            try {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                transaction.getRootResource().getContents().clear();
                LOG.log(Level.INFO, "Start moving elements");
                transaction.getRootResource().getContents().addAll(sourceResource.getContents());
                LOG.log(Level.INFO, "End moving elements");
                LOG.log(Level.INFO, "Commiting");
                transaction.commit();
                LOG.log(Level.INFO, "Commit done");
                transaction.close();
                session.close();
            }
            finally {
                server.stop();
            }
        }
        catch (ParseException e) {
            MessageUtil.showError(e.toString());
            MessageUtil.showError("Current arguments: " + Arrays.toString(args));
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar <this-file.jar>", options, true);
        }
        catch (Throwable e) {
            MessageUtil.showError(e.toString());
        }
    }
}
