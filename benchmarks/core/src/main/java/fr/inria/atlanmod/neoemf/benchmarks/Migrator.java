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

import fr.inria.atlanmod.neoemf.benchmarks.util.MigratorUtil;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Migrator {

    private static final Logger LOG = Logger.getLogger(Migrator.class.getName());

    private static final String IN = "input";

    private static final String OUT = "output";

    private static final String IN_EPACKAGE_CLASS = "input_epackage_class";

    private static final String OUT_EPACKAGE_CLASS = "output_epackage_class";

    public static void main(String[] args) {
        Options options = new Options();

        options.addOption(Option.builder(IN)
                .argName("INPUT")
                .desc("Input file")
                .numberOfArgs(1)
                .required()
                .build());

        options.addOption(Option.builder(OUT)
                .argName("OUTPUT")
                .desc("Output file")
                .numberOfArgs(1)
                .required()
                .build());

        options.addOption(Option.builder(IN_EPACKAGE_CLASS)
                .argName("CLASS")
                .desc("FQN of input EPackage implementation class")
                .numberOfArgs(1)
                .required()
                .build());

        options.addOption(Option.builder(OUT_EPACKAGE_CLASS)
                .argName("CLASS")
                .desc("FQN of output EPackage implementation class")
                .numberOfArgs(1)
                .required()
                .build());

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine commandLine = parser.parse(options, args);
            URI sourceUri = URI.createFileURI(commandLine.getOptionValue(IN));
            URI targetUri = URI.createFileURI(commandLine.getOptionValue(OUT));
            Class<?> inClazz = Migrator.class.getClassLoader().loadClass(commandLine.getOptionValue(IN_EPACKAGE_CLASS));
            Class<?> outClazz = Migrator.class.getClassLoader().loadClass(commandLine.getOptionValue(OUT_EPACKAGE_CLASS));

            @SuppressWarnings("unused")
            EPackage inEPackage = (EPackage) inClazz.getMethod("init").invoke(null);
            EPackage outEPackage = (EPackage) outClazz.getMethod("init").invoke(null);

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

            Resource sourceResource = resourceSet.getResource(sourceUri, true);
            Resource targetResource = resourceSet.createResource(targetUri);

            targetResource.getContents().clear();
            LOG.log(Level.INFO, "Start migration");
            targetResource.getContents().add(MigratorUtil.migrate(sourceResource.getContents().get(0), outEPackage));
            LOG.log(Level.INFO, "Migration finished");

            Map<String, Object> saveOpts = new HashMap<>();
            saveOpts.put(XMIResource.OPTION_ZIP, Boolean.TRUE);
            LOG.log(Level.INFO, "Start saving");
            targetResource.save(saveOpts);
            LOG.log(Level.INFO, "Saving done");
        }
        catch (ParseException e) {
            showError(e.toString());
            showError("Current arguments: " + Arrays.toString(args));
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar <this-file.jar>", options, true);
        }
        catch (Throwable e) {
            showError(e.toString());
        }
    }

    private static void showError(String message) {
        System.err.println(message);
    }
}
