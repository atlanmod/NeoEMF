package fr.inria.atlanmod.neo4emf.neo4jresolver;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

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
	}
	
	private void checkRuntimesAsync() {
		Neo4JRuntimesManager.INSTANCE.checkRuntimes(true);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
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
	
	public INeo4jRuntimesManager getRuntimesManager() {
		return Neo4JRuntimesManager.INSTANCE;
	}
}
