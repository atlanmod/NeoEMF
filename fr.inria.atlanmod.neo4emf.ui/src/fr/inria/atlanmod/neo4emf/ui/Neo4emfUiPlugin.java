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
package fr.inria.atlanmod.neo4emf.ui;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.BundleContext;

import fr.inria.atlanmod.neo4emf.Neo4emfPlugin;

/**
 * The activator class controls the plug-in life cycle
 * @author abelgomez
 */
public class Neo4emfUiPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "fr.inria.atlanmod.neo4emf.ui"; //$NON-NLS-1$

	// The shared instance
	private static Neo4emfUiPlugin plugin;
	
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
	public Neo4emfUiPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		checkNeo4emfPlugin();
		Neo4emfUiPlugin.getDefault().getLog().addLogListener(logListener);
	}

	private void checkNeo4emfPlugin() {
		Neo4emfPlugin.getDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		Neo4emfUiPlugin.getDefault().getLog().removeLogListener(logListener);
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Neo4emfUiPlugin getDefault() {
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
