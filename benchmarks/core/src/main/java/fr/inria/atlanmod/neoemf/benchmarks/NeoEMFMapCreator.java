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
import fr.inria.atlanmod.neoemf.benchmarks.util.MessageUtil;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.map.resources.MapResourceOptions;
import fr.inria.atlanmod.neoemf.map.util.NeoMapURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions.StoreOption;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceImpl;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.EPACKAGE_CLASS;
import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.IN;
import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.OPTIONS_FILE;
import static fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil.Key.OUT;

public class NeoEMFMapCreator {

    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            Map<String, String> cli = processCommandLineArgs(args);

            PersistenceBackendFactoryRegistry.register(NeoMapURI.NEO_MAP_SCHEME, MapPersistenceBackendFactory.getInstance());

            URI sourceUri = URI.createFileURI(cli.get(IN));
            URI targetUri = NeoMapURI.createNeoMapURI(new File(cli.get(OUT)));

            Class<?> inClazz = NeoEMFMapCreator.class.getClassLoader().loadClass(cli.get(EPACKAGE_CLASS));
            inClazz.getMethod("init").invoke(null);

            ResourceSet resourceSet = new ResourceSetImpl();

            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, PersistentResourceFactory.getInstance());

            Resource sourceResource = resourceSet.createResource(sourceUri);
            Map<String, Object> loadOpts = new HashMap<>();
            if ("zxmi".equals(sourceUri.fileExtension())) {
                loadOpts.put(XMIResource.OPTION_ZIP, Boolean.TRUE);
            }

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

            Resource targetResource = resourceSet.createResource(targetUri);

            Map<String, Object> saveOpts = new HashMap<>();

            if (cli.containsKey(OPTIONS_FILE)) {
                Properties properties = new Properties();
                properties.load(new FileInputStream(new File(cli.get(OPTIONS_FILE))));
                for (final Map.Entry<Object, Object> entry : properties.entrySet()) {
                    saveOpts.put((String) entry.getKey(), entry.getValue());
                }
            }

            List<StoreOption> storeOptions = new ArrayList<>();
            storeOptions.add(MapResourceOptions.EStoreMapOption.AUTOCOMMIT);
            saveOpts.put(MapResourceOptions.STORE_OPTIONS, storeOptions);
            targetResource.save(saveOpts);

            LOG.info("Start moving elements");
            targetResource.getContents().clear();
            targetResource.getContents().addAll(sourceResource.getContents());
            LOG.info("End moving elements");
            LOG.info("Start saving");
            targetResource.save(saveOpts);
            LOG.info("Saved");

            if (targetResource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) targetResource);
            }
            else {
                targetResource.unload();
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
                .desc("Output directory")
                .hasArg()
                .required()
                .build());

        options.addOption(Option.builder(EPACKAGE_CLASS)
                .argName("CLASS")
                .desc("FQN of EPackage implementation class")
                .hasArg()
                .required()
                .build());

        options.addOption(Option.builder(OPTIONS_FILE)
                .argName("FILE")
                .desc("Properties file holding the options to be used in the NeoEMF Resource")
                .hasArg()
                .build());

        return CommandLineUtil.getValues(options, args);
    }
}
