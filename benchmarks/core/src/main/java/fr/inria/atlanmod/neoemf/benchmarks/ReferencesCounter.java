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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.IOUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;

public class ReferencesCounter {

    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(ReferencesCounter.class.getName());

    private static final String IN = "input";

    private static final String OUT = "output";

    private static final String LABEL = "label";

    private static final String IN_EPACKAGE_CLASS = "input_epackage_class";

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

        options.addOption(Option.builder(LABEL)
                .argName("LABEL")
                .desc("Label for the data set")
                .numberOfArgs(1)
                .required()
                .build());

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine commandLine = parser.parse(options, args);
            URI sourceUri = URI.createFileURI(commandLine.getOptionValue(IN));
            Class<?> inClazz = ReferencesCounter.class.getClassLoader().loadClass(commandLine.getOptionValue(IN_EPACKAGE_CLASS));
            @SuppressWarnings("unused")
            EPackage inEPackage = (EPackage) inClazz.getMethod("init").invoke(null);

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

            Resource sourceResource = resourceSet.getResource(sourceUri, true);

            FileWriter writer = new FileWriter(new File(commandLine.getOptionValue(OUT)));
            try {
                writer.write(commandLine.getOptionValue(LABEL));
                writer.write("\n");

                for (Iterator<EObject> iterator = sourceResource.getAllContents(); iterator.hasNext(); ) {
                    EObject eObject = iterator.next();
                    for (EStructuralFeature feature : eObject.eClass().getEAllStructuralFeatures()) {
                        if (feature.isMany() && eObject.eIsSet(feature)) {
                            EList<?> value = (EList<?>) eObject.eGet(feature);
//							if (value.size() > 10) 
                            writer.write(String.format("%d\n", value.size()));
                        }
                    }
                }
            }
            finally {
                IOUtils.closeQuietly(writer);
            }
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
