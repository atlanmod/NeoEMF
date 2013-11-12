package fr.inria.atlanmod.neo4emf;
/**
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 * Descritpion ! To come
 * @author Amine BENELALLAM
 * */

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import fr.inria.atlanmod.neo4emf.neo4jresolver.Neo4jResolverPlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class Neo4emfPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "fr.inria.atlanmod.neo4emf"; //$NON-NLS-1$

	public static final String DEFAULT_NEO4J_RUNTIME_ID = "fr.inria.atlanmod.neo4emf.neo4j-1.9.4"; //$NON-NLS-1$

	private static final String INSTALLERS_EXTENSION_POINT_ID = "fr.inria.atlanmod.neo4emf.neo4jRuntimes";

	private static final String ATT_ID = "id";
	
	private static final String ATT_PRIORITY = "priority";

	// The shared instance
	private static Neo4emfPlugin plugin;

	private Bundle neo4jBundle;
	
	private static ILogListener  logListener = new ILogListener() {
		
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
	public Neo4emfPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		loadNeo4jRuntime(context);
		getDefault().getLog().addLogListener(logListener);
	}

	private void loadNeo4jRuntime(BundleContext context) throws BundleException {
		neo4jBundle = Neo4jResolverPlugin.getDefault().installNeo4jRuntimeInContext(context, getRuntimeId());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		if (neo4jBundle != null) {
			neo4jBundle.stop();
		}
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Neo4emfPlugin getDefault() {
		return plugin;
	}

	private String getRuntimeId() {
		String id = DEFAULT_NEO4J_RUNTIME_ID;
		int priority = Integer.MIN_VALUE;
		IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor(INSTALLERS_EXTENSION_POINT_ID);
		for (IConfigurationElement element : elements) {
			if (Integer.valueOf(element.getAttribute(ATT_PRIORITY)) > priority) {
				id = element.getAttribute(ATT_ID);
			}
		}
		return id;
	}
}
