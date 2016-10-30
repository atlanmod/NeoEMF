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

import fr.inria.atlanmod.neoemf.benchmarks.util.MessageUtil;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions.StoreOption;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceImpl;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.File;
import java.io.FileInputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NeoEMFGraphCreator {

    private static final Logger LOG = Logger.getLogger(NeoEMFGraphCreator.class.getName());

    private static final String IN = "input";

    private static final String OUT = "output";

    private static final String EPACKAGE_CLASS = "epackage_class";

    private static final String OPTIONS_FILE = "options_file";

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

        Option optFileOpt = OptionBuilder.create(OPTIONS_FILE);
        optFileOpt.setArgName("FILE");
        optFileOpt.setDescription("Properties file holding the options to be used in the NeoEMF Resource");
        optFileOpt.setArgs(1);

        options.addOption(inputOpt);
        options.addOption(outputOpt);
        options.addOption(inClassOpt);
        options.addOption(optFileOpt);

        CommandLineParser parser = new PosixParser();

        try {
            PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.NEO_GRAPH_SCHEME, BlueprintsPersistenceBackendFactory.getInstance());

            CommandLine commandLine = parser.parse(options, args);

            URI sourceUri = URI.createFileURI(commandLine.getOptionValue(IN));
            URI targetUri = NeoBlueprintsURI.createNeoGraphURI(new File(commandLine.getOptionValue(OUT)));

            Class<?> inClazz = NeoEMFGraphCreator.class.getClassLoader().loadClass(commandLine.getOptionValue(EPACKAGE_CLASS));
            inClazz.getMethod("init").invoke(null);

            ResourceSet resourceSet = new ResourceSetImpl();

            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, PersistentResourceFactory.getInstance());

            Resource sourceResource = resourceSet.createResource(sourceUri);
            Map<String, Object> loadOpts = new HashMap<>();
            if ("zxmi".equals(sourceUri.fileExtension())) {
                loadOpts.put(XMIResource.OPTION_ZIP, Boolean.TRUE);
            }

            Runtime.getRuntime().gc();
            long initialUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            LOG.log(Level.INFO, MessageFormat.format("Used memory before loading: {0}", MessageUtil.byteCountToDisplaySize(initialUsedMemory)));
            LOG.log(Level.INFO, "Loading source resource");
            sourceResource.load(loadOpts);
            LOG.log(Level.INFO, "Source resource loaded");
            Runtime.getRuntime().gc();
            long finalUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            LOG.log(Level.INFO, MessageFormat.format("Used memory after loading: {0}", MessageUtil.byteCountToDisplaySize(finalUsedMemory)));
            LOG.log(Level.INFO, MessageFormat.format("Memory use increase: {0}", MessageUtil.byteCountToDisplaySize(finalUsedMemory - initialUsedMemory)));

            Resource targetResource = resourceSet.createResource(targetUri);

            Map<String, Object> saveOpts = new HashMap<>();

            if (commandLine.hasOption(OPTIONS_FILE)) {
                Properties properties = new Properties();
                properties.load(new FileInputStream(new File(commandLine.getOptionValue(OPTIONS_FILE))));
                for (final Entry<Object, Object> entry : properties.entrySet()) {
                    saveOpts.put((String) entry.getKey(), entry.getValue());
                }
            }

            List<StoreOption> storeOptions = new ArrayList<>();
            storeOptions.add(BlueprintsResourceOptions.EStoreGraphOption.AUTOCOMMIT);
            saveOpts.put(BlueprintsResourceOptions.STORE_OPTIONS, storeOptions);
            targetResource.save(saveOpts);

            LOG.log(Level.INFO, "Start moving elements");
            targetResource.getContents().clear();
            targetResource.getContents().addAll(sourceResource.getContents());
            LOG.log(Level.INFO, "End moving elements");
            LOG.log(Level.INFO, "Start saving");
            targetResource.save(saveOpts);
            LOG.log(Level.INFO, "Saved");

            if (targetResource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) targetResource);
            }
            else {
                targetResource.unload();
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
