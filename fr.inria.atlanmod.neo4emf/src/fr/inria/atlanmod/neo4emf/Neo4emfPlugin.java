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

import java.text.MessageFormat;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import fr.inria.atlanmod.neo4emf.logger.Logger;
import fr.inria.atlanmod.neo4emf.neo4jresolver.Neo4jResolverPlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class Neo4emfPlugin extends Plugin {

	private class RuntimeNotFoundException extends Exception {

		private static final long serialVersionUID = 1L;
		
		public RuntimeNotFoundException(String runtimeId) {
			super(MessageFormat.format("Neo4J runtime not found ({0})", runtimeId));
		}
		
	}
	
	private static ILogListener logListener;
	
	{
	// Log listener initialization	
		try {
			logListener = new ILogListener() {
				StatusManager manager = StatusManager.getManager();
				@Override
				public void logging(IStatus status, String plugin) {
					if (status.matches(IStatus.ERROR) && manager != null) {
						manager.handle(status, StatusManager.BLOCK);
					} 
				}
			};
		} catch (NoClassDefFoundError e) {
			// Eclipse UI not available
		}
	}
	
	// The plug-in ID
	public static final String PLUGIN_ID = "fr.inria.atlanmod.neo4emf"; //$NON-NLS-1$

	public static final String DEFAULT_NEO4J_RUNTIME_ID = "fr.inria.atlanmod.neo4emf.neo4j"; //$NON-NLS-1$

	private static final String INSTALLERS_EXTENSION_POINT_ID = "fr.inria.atlanmod.neo4emf.neo4jRuntimes";

	private static final String ATT_ID = "id";
	
	private static final String ATT_PRIORITY = "priority";

	// The shared instance
	private static Neo4emfPlugin plugin;

	private Bundle neo4jBundle;
	
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
		if (logListener != null) {
			getDefault().getLog().addLogListener(logListener);
		}
		try {
			loadNeo4jRuntime(context);
		} catch (RuntimeNotFoundException e) {
			// Runtime not found... let's try below to install a runtime...
		}
		if (neo4jBundle == null && isNeo4jResolverAvailable()) {
			Thread installThread = new Thread() {
				public void run() {
					Neo4jResolverPlugin.getDefault().getRuntimesManager().checkRuntimes();
					try {
						try {
							loadNeo4jRuntime(getDefault().getBundle().getBundleContext());
						} catch (RuntimeNotFoundException | BundleException e) {
							Logger.log(Logger.SEVERITY_ERROR, 
									"Unable to load Neo4J runtime. "
									+ "Neo4EMF requires Neo4J to run and will be shutdown. "
									+ "Install a Neo4J bundle and restart the platform", e);
						}
						if (neo4jBundle == null) {
							getDefault().getBundle().stop();
						}
					} catch (BundleException e) {
						Logger.log(Logger.SEVERITY_ERROR, "Unable to stop Neo4EMF bundle", e);
					}
				}
			};
			installThread.start();
		}
	}

	private void loadNeo4jRuntime(BundleContext context) throws BundleException, RuntimeNotFoundException {
		String id = getRuntimeId();
		neo4jBundle = Platform.getBundle(id);
		if (neo4jBundle == null) {
			if (isNeo4jResolverAvailable()) {
				if (Neo4jResolverPlugin.getDefault().getRuntimesManager().getRuntime(id) != null) {
					neo4jBundle = Neo4jResolverPlugin.getDefault().installNeo4jRuntimeInContext(context, id);
				} else {
					throw new RuntimeNotFoundException(id);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		if (logListener != null) {
			getDefault().getLog().removeLogListener(logListener);
		}
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

	/**
	 * Returns the ID of the bundle containing the Neo4J runtime libraries
	 * @return
	 */
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
	
	private boolean isNeo4jResolverAvailable() {
		try {
			if (Neo4jResolverPlugin.getDefault() != null) {
				return true;
			}
		} catch (Throwable e) {
		}
		return false;
	}
	
}
