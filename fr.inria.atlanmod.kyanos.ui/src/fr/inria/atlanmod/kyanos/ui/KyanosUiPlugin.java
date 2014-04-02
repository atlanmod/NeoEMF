package fr.inria.atlanmod.kyanos.ui;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.BundleContext;

public class KyanosUiPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "fr.inria.atlanmod.kyanos.ui"; //$NON-NLS-1$

	// The shared instance
	private static KyanosUiPlugin plugin;
	
	private static ILogListener logListener = new ILogListener() {
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
	public KyanosUiPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		KyanosUiPlugin.getDefault().getLog().addLogListener(logListener);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		KyanosUiPlugin.getDefault().getLog().removeLogListener(logListener);
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static KyanosUiPlugin getDefault() {
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
}
