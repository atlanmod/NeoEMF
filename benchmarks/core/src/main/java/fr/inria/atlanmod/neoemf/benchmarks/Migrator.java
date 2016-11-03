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
import fr.inria.atlanmod.neoemf.benchmarks.util.MigratorUtil;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.util.HashMap;
import java.util.Map;

import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.IN;
import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.IN_EPACKAGE_CLASS;
import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.OUT;
import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.OUT_EPACKAGE_CLASS;

public class Migrator {

    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            Map<String, String> cli = processCommandLineArgs(args);
            URI sourceUri = URI.createFileURI(cli.get(IN));
            URI targetUri = URI.createFileURI(cli.get(OUT));
            Class<?> inClazz = Migrator.class.getClassLoader().loadClass(cli.get(IN_EPACKAGE_CLASS));
            Class<?> outClazz = Migrator.class.getClassLoader().loadClass(cli.get(OUT_EPACKAGE_CLASS));

            @SuppressWarnings("unused")
            EPackage inEPackage = (EPackage) inClazz.getMethod("init").invoke(null);
            EPackage outEPackage = (EPackage) outClazz.getMethod("init").invoke(null);

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

            Resource sourceResource = resourceSet.getResource(sourceUri, true);
            Resource targetResource = resourceSet.createResource(targetUri);

            targetResource.getContents().clear();
            LOG.info("Start migration");
            targetResource.getContents().add(MigratorUtil.migrate(sourceResource.getContents().get(0), outEPackage));
            LOG.info("Migration finished");

            Map<String, Object> saveOpts = new HashMap<>();
            saveOpts.put(XMIResource.OPTION_ZIP, Boolean.TRUE);
            LOG.info("Start saving");
            targetResource.save(saveOpts);
            LOG.info("Saving done");
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    private static Map<String, String> processCommandLineArgs(String... args) throws ParseException {
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

        return CommandLineUtil.getValues(options, args);
    }
}
