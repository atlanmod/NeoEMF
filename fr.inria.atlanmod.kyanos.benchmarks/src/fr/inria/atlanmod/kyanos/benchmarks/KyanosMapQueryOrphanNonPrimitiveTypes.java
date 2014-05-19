package fr.inria.atlanmod.kyanos.benchmarks;

import java.io.File;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
import org.eclipse.gmt.modisco.java.Type;

import fr.inria.atlanmod.kyanos.benchmarks.queries.JavaQueries;
import fr.inria.atlanmod.kyanos.benchmarks.util.MessageUtil;
import fr.inria.atlanmod.kyanos.core.KyanosResourceFactory;
import fr.inria.atlanmod.kyanos.core.impl.KyanosMapResourceImpl;
import fr.inria.atlanmod.kyanos.util.KyanosURI;

public class KyanosMapQueryOrphanNonPrimitiveTypes {

	private static final Logger LOG = Logger.getLogger(KyanosMapQueryOrphanNonPrimitiveTypes.class.getName());
	
	private static final String IN = "input";

	private static final String EPACKAGE_CLASS = "epackage_class";

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
		
		options.addOption(inputOpt);
		options.addOption(inClassOpt);

		CommandLineParser parser = new PosixParser();
		
		try {
			CommandLine commandLine = parser.parse(options, args);
			
			URI uri = KyanosURI.createKyanosMapURI(new File(commandLine.getOptionValue(IN)));

			Class<?> inClazz = KyanosMapQueryOrphanNonPrimitiveTypes.class.getClassLoader().loadClass(commandLine.getOptionValue(EPACKAGE_CLASS));
			inClazz.getMethod("init").invoke(null);
			
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(KyanosURI.KYANOS_MAP_SCHEME, KyanosResourceFactory.eINSTANCE);
			
			Resource resource = resourceSet.createResource(uri);
			
			Map<String, Object> loadOpts = new HashMap<String, Object>();
			resource.load(loadOpts);
			{
				LOG.log(Level.INFO, "Start query");
				long begin = System.currentTimeMillis();
				EList<Type> list = JavaQueries.getOrphanNonPrimitivesTypes(resource);
				long end = System.currentTimeMillis();
				LOG.log(Level.INFO, "End query");
				LOG.log(Level.INFO, MessageFormat.format("Query result contains {0} elements", list.size()));
				LOG.log(Level.INFO, MessageFormat.format("Time spent: {0}", MessageUtil.formatMillis(end-begin)));
			}
			
			if (resource instanceof KyanosMapResourceImpl) {
				KyanosMapResourceImpl.shutdownWithoutUnload((KyanosMapResourceImpl) resource); 
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
