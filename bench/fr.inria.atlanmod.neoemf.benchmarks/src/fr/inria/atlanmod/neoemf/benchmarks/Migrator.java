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
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
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

        Option inputOpt = OptionBuilder.create(IN);
        inputOpt.setArgName("INPUT");
        inputOpt.setDescription("Input file");
        inputOpt.setArgs(1);
        inputOpt.setRequired(true);

        Option outputOpt = OptionBuilder.create(OUT);
        outputOpt.setArgName("OUTPUT");
        outputOpt.setDescription("Output file");
        outputOpt.setArgs(1);
        outputOpt.setRequired(true);

        Option inClassOpt = OptionBuilder.create(IN_EPACKAGE_CLASS);
        inClassOpt.setArgName("CLASS");
        inClassOpt.setDescription("FQN of input EPackage implementation class");
        inClassOpt.setArgs(1);
        inClassOpt.setRequired(true);

        Option outClassOpt = OptionBuilder.create(OUT_EPACKAGE_CLASS);
        outClassOpt.setArgName("CLASS");
        outClassOpt.setDescription("FQN of output EPackage implementation class");
        outClassOpt.setArgs(1);
        outClassOpt.setRequired(true);

        options.addOption(inputOpt);
        options.addOption(outputOpt);
        options.addOption(inClassOpt);
        options.addOption(outClassOpt);

        CommandLineParser parser = new PosixParser();

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
