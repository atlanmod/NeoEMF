/*******************************************************************************
 * Copyright (c) 2014 Abel G�mez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel G�mez - initial API and implementation
 ******************************************************************************/
package fr.inria.atlanmod.kyanos.benchmarks;

import java.io.File;
import java.io.FileInputStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmt.modisco.java.MethodDeclaration;

import fr.inria.atlanmod.kyanos.benchmarks.queries.JavaQueries;
import fr.inria.atlanmod.kyanos.benchmarks.util.MessageUtil;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceImpl;

public class KyanosGraphQueryList {

	private static final Logger LOG = Logger.getLogger(KyanosGraphQueryList.class.getName());
	
	private static final String IN = "input";

	private static final String EPACKAGE_CLASS = "epackage_class";

	private static final String OPTIONS_FILE = "options_file";

	public static void main(String[] args) {
		Options options = new Options();
		
		Option inputOpt = OptionBuilder.create(IN);
		inputOpt.setArgName("INPUT");
		inputOpt.setDescription("Input Kyanos resource directory");
		inputOpt.setArgs(1);
		inputOpt.setRequired(true);
		
		Option inClassOpt = OptionBuilder.create(EPACKAGE_CLASS);
		inClassOpt.setArgName("CLASS");
		inClassOpt.setDescription("FQN of EPackage implementation class");
		inClassOpt.setArgs(1);
		inClassOpt.setRequired(true);
		
		Option optFileOpt = OptionBuilder.create(OPTIONS_FILE);
		optFileOpt.setArgName("FILE");
		optFileOpt.setDescription("Properties file holding the options to be used in the Kyanos Resource");
		optFileOpt.setArgs(1);
		
		options.addOption(inputOpt);
		options.addOption(inClassOpt);
		options.addOption(optFileOpt);

		CommandLineParser parser = new PosixParser();
		
		try {
			PersistenceBackendFactoryRegistry.getFactories().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, new BlueprintsPersistenceBackendFactory());

			CommandLine commandLine = parser.parse(options, args);
			
			URI uri = NeoBlueprintsURI.createNeoGraphURI(new File(commandLine.getOptionValue(IN)));

			Class<?> inClazz = KyanosGraphQueryList.class.getClassLoader().loadClass(commandLine.getOptionValue(EPACKAGE_CLASS));
			inClazz.getMethod("init").invoke(null);
						
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, PersistentResourceFactory.eINSTANCE);
			
			Resource resource = resourceSet.createResource(uri);
			
			Map<String, Object> loadOpts = new HashMap<String, Object>();

			if (commandLine.hasOption(OPTIONS_FILE)) {
				Properties properties = new Properties();
				properties.load(new FileInputStream(new File(commandLine.getOptionValue(OPTIONS_FILE))));
				for (final Entry<Object, Object> entry : properties.entrySet()) {
			        loadOpts.put((String) entry.getKey(), (String) entry.getValue());
			    }
			}
			resource.load(loadOpts);
			{
				LOG.log(Level.INFO, "Start query");
				long begin = System.currentTimeMillis();
				EList<MethodDeclaration> list = JavaQueries.getUnusedMethodsList(resource);
				long end = System.currentTimeMillis();
				LOG.log(Level.INFO, "End query");
				LOG.log(Level.INFO, MessageFormat.format("Query result (list) contains {0} elements", list.size()));
				LOG.log(Level.INFO, MessageFormat.format("Time spent: {0}", MessageUtil.formatMillis(end-begin)));
			}
			
			if (resource instanceof PersistentResourceImpl) {
				PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource); 
			} else {
				resource.unload();
			}
			
		} catch (ParseException e) {
			MessageUtil.showError(e.toString());
			MessageUtil.showError("Current arguments: " + Arrays.toString(args));
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("java -jar <this-file.jar>", options, true);
		} catch (Throwable e) {
			MessageUtil.showError(e.toString());
		}
	}
	

}
