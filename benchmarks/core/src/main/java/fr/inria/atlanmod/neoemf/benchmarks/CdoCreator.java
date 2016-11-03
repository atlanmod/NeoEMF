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
import fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil;
import fr.inria.atlanmod.neoemf.benchmarks.util.MessageUtil;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

import java.util.HashMap;
import java.util.Map;

public class CdoCreator {

    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            Map<String, String> cli = processCommandLineArgs(args);
            URI sourceUri = URI.createFileURI(cli.get(CommandLineUtil.Key.IN));

            String outputDir = cli.get(CommandLineUtil.Key.OUT);
            String repositoryName = cli.get(CommandLineUtil.Key.REPO_NAME);

            Class<?> inClazz = CdoCreator.class.getClassLoader().loadClass(cli.get(CommandLineUtil.Key.EPACKAGE_CLASS));
            inClazz.getMethod("init").invoke(null);

            ResourceSet resourceSet = new ResourceSetImpl();

            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

            Resource sourceResource = resourceSet.createResource(sourceUri);
            Map<String, Object> loadOpts = new HashMap<>();
            if ("zxmi".equals(sourceUri.fileExtension())) {
                loadOpts.put(XMIResource.OPTION_ZIP, Boolean.TRUE);
            }

            {
                Runtime.getRuntime().gc();
                long initialUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                LOG.info("Used memory before loading: {0}", MessageUtil.byteCountToDisplaySize(initialUsedMemory));
                LOG.info("Loading source resource");
                sourceResource.load(loadOpts);
                LOG.info("Source resource loaded");
                Runtime.getRuntime().gc();
                long finalUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                LOG.info("Used memory after loading: {0}", MessageUtil.byteCountToDisplaySize(finalUsedMemory));
                LOG.info("Memory use increase: {0}", MessageUtil.byteCountToDisplaySize(finalUsedMemory - initialUsedMemory));
            }

            try (EmbeddedCDOServer server = new EmbeddedCDOServer(outputDir, repositoryName)) {
                server.run();
                CDOSession session = server.openSession();
                CDOTransaction transaction = session.openTransaction();
                transaction.getRootResource().getContents().clear();

                {
                    LOG.info("Start moving elements");
                    transaction.getRootResource().getContents().addAll(sourceResource.getContents());
                    LOG.info("End moving elements");
                    LOG.info("Commiting");
                    transaction.commit();
                    LOG.info("Commit done");
                }

                transaction.close();
                session.close();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    private static Map<String, String> processCommandLineArgs(String... args) throws ParseException {
        Options options = new Options();

        options.addOption(Option.builder(CommandLineUtil.Key.IN)
                .argName("INPUT")
                .desc("Input file")
                .numberOfArgs(1)
                .required()
                .build());

        options.addOption(Option.builder(CommandLineUtil.Key.OUT)
                .argName("OUTPUT")
                .desc("Output directory")
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

    /*
     * Sample methods to complete.
     */

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Fork(jvmArgs = {"-Xmx8g"})
    public void benchmarkWith8G() {

    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Fork(jvmArgs = {"-Xmx512m"})
    public void benchmarkWith512M() {

    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Fork(jvmArgs = {"-Xmx256m"})
    public void benchmarkWith256M() {

    }
}
