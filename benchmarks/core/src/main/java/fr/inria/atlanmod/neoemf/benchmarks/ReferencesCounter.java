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

import fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.util.Iterator;
import java.util.Map;

import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.IN;
import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.IN_EPACKAGE_CLASS;
import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.LABEL;
import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.OUT;

public class ReferencesCounter {

    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            Map<String, String> cli = processCommandLineArgs(args);

            URI sourceUri = URI.createFileURI(cli.get(IN));
            ReferencesCounter.class.getClassLoader().loadClass(cli.get(IN_EPACKAGE_CLASS)).getMethod("init").invoke(null);

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

            Resource sourceResource = resourceSet.getResource(sourceUri, true);

            FileWriter writer = new FileWriter(new File(cli.get(OUT)));
            try {
                writer.write(cli.get(LABEL));
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
        catch (Exception e) {
            LOG.error(e);
        }
    }

    private static Map<String, String> processCommandLineArgs(String... args) throws ParseException {
        Options options = new Options();

        options.addOption(Option.builder(IN)
                .argName("INPUT")
                .desc("Input file")
                .hasArg()
                .required()
                .build());

        options.addOption(Option.builder(OUT)
                .argName("OUTPUT")
                .desc("Output file")
                .hasArg()
                .required()
                .build());

        options.addOption(Option.builder(IN_EPACKAGE_CLASS)
                .argName("CLASS")
                .desc("FQN of input EPackage implementation class")
                .hasArg()
                .required()
                .build());

        options.addOption(Option.builder(LABEL)
                .argName("LABEL")
                .desc("Label for the data set")
                .hasArg()
                .required()
                .build());

        return CommandLineUtil.getValues(options, args);
    }
}
