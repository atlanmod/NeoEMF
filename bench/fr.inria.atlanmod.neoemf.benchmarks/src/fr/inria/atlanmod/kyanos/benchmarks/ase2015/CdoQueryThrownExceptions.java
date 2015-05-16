/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.kyanos.benchmarks.ase2015;

import java.text.MessageFormat;
import java.util.Arrays;
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
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.TypeAccess;

import fr.inria.atlanmod.kyanos.benchmarks.ase2015.queries.ASE2015JavaQueries;
import fr.inria.atlanmod.kyanos.benchmarks.cdo.EmbeddedCDOServer;
import fr.inria.atlanmod.kyanos.benchmarks.util.MessageUtil;

public class CdoQueryThrownExceptions {

	private static final Logger LOG = Logger.getLogger(CdoQueryThrownExceptions.class.getName());

	private static final String IN = "input";

	private static final String REPO_NAME = "reponame";

	private static final String EPACKAGE_CLASS = "epackage_class";


	public static void main(String[] args) {
		Options options = new Options();
		
		Option inputOpt = OptionBuilder.create(IN);
		inputOpt.setArgName("INPUT");
		inputOpt.setDescription("Input CDO resource directory");
		inputOpt.setArgs(1);
		inputOpt.setRequired(true);
		
		Option inClassOpt = OptionBuilder.create(EPACKAGE_CLASS);
		inClassOpt.setArgName("CLASS");
		inClassOpt.setDescription("FQN of EPackage implementation class");
		inClassOpt.setArgs(1);
		inClassOpt.setRequired(true);
		
		Option repoOpt = OptionBuilder.create(REPO_NAME);
		repoOpt.setArgName("REPO_NAME");
		repoOpt.setDescription("CDO Repository name");
		repoOpt.setArgs(1);
		repoOpt.setRequired(true);
		
		options.addOption(inputOpt);
		options.addOption(inClassOpt);
		options.addOption(repoOpt);

		CommandLineParser parser = new PosixParser();
		
		try {
			CommandLine commandLine = parser.parse(options, args);

			String repositoryDir = commandLine.getOptionValue(IN);
			String repositoryName = commandLine.getOptionValue(REPO_NAME);
			
			Class<?> inClazz = CdoQueryThrownExceptions.class.getClassLoader().loadClass(commandLine.getOptionValue(EPACKAGE_CLASS));
			inClazz.getMethod("init").invoke(null);
			
			EmbeddedCDOServer server = new EmbeddedCDOServer(repositoryDir, repositoryName);
			try {
				server.run();
				CDOSession session = server.openSession();
				CDOTransaction transaction = session.openTransaction();
				Resource resource = transaction.getRootResource();
				{
					Runtime.getRuntime().gc();
					long initialUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
					LOG.log(Level.INFO, MessageFormat.format("Used memory before query: {0}", 
							MessageUtil.byteCountToDisplaySize(initialUsedMemory)));
					LOG.log(Level.INFO, "Start query");
					long begin = System.currentTimeMillis();
					EList<TypeAccess> list = ASE2015JavaQueries.getThrownExceptions(resource);
					long end = System.currentTimeMillis();
					LOG.log(Level.INFO, "End query");
					LOG.log(Level.INFO, MessageFormat.format("Query result contains {0} elements", list.size()));
					LOG.log(Level.INFO, MessageFormat.format("Time spent: {0}", MessageUtil.formatMillis(end-begin)));
					Runtime.getRuntime().gc();
					long finalUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
					LOG.log(Level.INFO, MessageFormat.format("Used memory after query: {0}", 
							MessageUtil.byteCountToDisplaySize(finalUsedMemory)));
					LOG.log(Level.INFO, MessageFormat.format("Memory use increase: {0}", 
							MessageUtil.byteCountToDisplaySize(finalUsedMemory - initialUsedMemory)));
				}
				
				transaction.close();
				session.close();
			} finally {
				server.stop();
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
