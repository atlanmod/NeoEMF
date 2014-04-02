package fr.inria.atlanmod.kyanos;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class KyanosPlugin extends Plugin {

	public static final String PLUGIN_ID = "fr.inria.atlanmod.kyanos";
	
	protected static KyanosPlugin plugin;
	
	public KyanosPlugin() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		super.start(bundleContext);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		plugin = null;
		super.stop(bundleContext);
	}
	
	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static KyanosPlugin getDefault() {
		return plugin;
	}
}
