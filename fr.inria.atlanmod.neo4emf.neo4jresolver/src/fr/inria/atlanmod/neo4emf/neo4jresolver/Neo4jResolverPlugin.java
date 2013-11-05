package fr.inria.atlanmod.neo4emf.neo4jresolver;

import java.io.File;
import java.net.MalformedURLException;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntime;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntimesManager;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.internal.Neo4JRuntimesManager;

/**
 * The activator class controls the plug-in life cycle
 */
public class Neo4jResolverPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "fr.inria.atlanmod.neo4emf.neo4jresolver"; //$NON-NLS-1$

	public static final String OBJ16_LIBRARY_OBJ = "OBJ16_LIBRARY_OBJ";
	
	// The shared instance
	private static Neo4jResolverPlugin plugin;

	private ILogListener logListener = new ILogListener() {
		@Override
		public void logging(IStatus status, String plugin) {
			if (status.matches(IStatus.ERROR)) {
				StatusManager.getManager().handle(status, StatusManager.BLOCK);
			}
		}
	};
	
	/**
	 * The constructor
	 */
	public Neo4jResolverPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		checkRuntimesAsync();
		getDefault().getLog().addLogListener(logListener);
	}
	
	private void checkRuntimesAsync() {
		Neo4JRuntimesManager.INSTANCE.checkRuntimes(false);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		getDefault().getLog().removeLogListener(logListener);
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Neo4jResolverPlugin getDefault() {
		return plugin;
	}
	
	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse.jface.resource.ImageRegistry)
	 */
	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		super.initializeImageRegistry(reg);
		reg.put(OBJ16_LIBRARY_OBJ, getImageDescriptor("icons/full/obj16/library_obj.gif"));
	}
	
	public Bundle installNeo4jRuntimeInContext(BundleContext context, String runtimeId) throws BundleException {
		Bundle neo4jBundle = null;
		INeo4jRuntime runtime = getRuntimesManager().getRuntime(runtimeId);
		try {
			File file = runtime.getPath().toFile();
			if (file != null && file.isDirectory()) {
				neo4jBundle = context.installBundle(file.toURI().toURL().toString());
				neo4jBundle.start();
			}
		} catch (Exception e) {
			throw new BundleException(
					NLS.bind("Unable to load Neo4J bundle ({0}) required by {1}", runtimeId, context.getBundle().getSymbolicName()));
		}
		return neo4jBundle;
	}
	
	public INeo4jRuntimesManager getRuntimesManager() {
		return Neo4JRuntimesManager.INSTANCE;
	}
}
