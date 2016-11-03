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

package fr.inria.atlanmod.neoemf.benchmarks.ase2015;

import fr.inria.atlanmod.neoemf.benchmarks.ase2015.queries.ASE2015JavaQueries;
import fr.inria.atlanmod.neoemf.benchmarks.util.CommandLineUtil;
import fr.inria.atlanmod.neoemf.benchmarks.util.MessageUtil;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions.StoreOption;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceImpl;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmt.modisco.java.MethodDeclaration;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class NeoEMFGraphQueryInvisibleMethodDeclarations {

    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            Map<String, String> cli = processCommandLineArgs(args);

            PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.NEO_GRAPH_SCHEME, BlueprintsPersistenceBackendFactory.getInstance());

            URI uri = NeoBlueprintsURI.createNeoGraphURI(new File(cli.get(CommandLineUtil.Key.IN)));

            Class<?> inClazz = NeoEMFGraphQueryInvisibleMethodDeclarations.class.getClassLoader().loadClass(cli.get(CommandLineUtil.Key.EPACKAGE_CLASS));
            inClazz.getMethod("init").invoke(null);

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, PersistentResourceFactory.getInstance());

            Resource resource = resourceSet.createResource(uri);

            Map<String, Object> loadOpts = new HashMap<>();

            if (cli.containsKey(CommandLineUtil.Key.OPTIONS_FILE)) {
                Properties properties = new Properties();
                properties.load(new FileInputStream(new File(cli.get(CommandLineUtil.Key.OPTIONS_FILE))));
                for (final Entry<Object, Object> entry : properties.entrySet()) {
                    loadOpts.put((String) entry.getKey(), entry.getValue());
                }
            }

            // Add the LoadedObjectCounter store
            List<StoreOption> storeOptions = new ArrayList<>();
//			storeOptions.add(PersistentResourceOptions.EStoreOption.LOADED_OBJECT_COUNTER_LOGGING);
            storeOptions.add(BlueprintsResourceOptions.EStoreGraphOption.AUTOCOMMIT);
            loadOpts.put(PersistentResourceOptions.STORE_OPTIONS, storeOptions);
            System.out.println(loadOpts);
            resource.load(loadOpts);
            {
                Runtime.getRuntime().gc();
                long initialUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                LOG.info("Used memory before query: {0}", MessageUtil.byteCountToDisplaySize(initialUsedMemory));
                LOG.info("Start query");
                Instant begin = Instant.now();
                EList<MethodDeclaration> list = ASE2015JavaQueries.getInvisibleMethodDeclarations(resource);
                Instant end = Instant.now();
                LOG.info("End query");
                LOG.info("Query result contains {0} elements", list.size());
                LOG.info("Time spent: {0}", Duration.between(begin, end));
                Runtime.getRuntime().gc();
                long finalUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                LOG.info("Used memory after query: {0}", MessageUtil.byteCountToDisplaySize(finalUsedMemory));
                LOG.info("Memory use increase: {0}", MessageUtil.byteCountToDisplaySize(finalUsedMemory - initialUsedMemory));
            }

            if (resource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource);
            }
            else {
                resource.unload();
            }
        }
        catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    private static Map<String, String> processCommandLineArgs(String... args) throws ParseException {
        Options options = new Options();

        options.addOption(Option.builder(CommandLineUtil.Key.IN)
                .argName("INPUT")
                .desc("Input NeoEMF resource directory")
                .numberOfArgs(1)
                .required()
                .build());

        options.addOption(Option.builder(CommandLineUtil.Key.EPACKAGE_CLASS)
                .argName("CLASS")
                .desc("FQN of EPackage implementation class")
                .numberOfArgs(1)
                .required()
                .build());

        options.addOption(Option.builder(CommandLineUtil.Key.OPTIONS_FILE)
                .argName("FILE")
                .desc("Properties file holding the options to be used in the NeoEMF Resource")
                .numberOfArgs(1)
                .build());

        return CommandLineUtil.getOptionsValues(options, args);
    }
}
