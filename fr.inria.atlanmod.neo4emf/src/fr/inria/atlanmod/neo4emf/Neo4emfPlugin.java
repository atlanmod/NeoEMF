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
import java.io.File;
import java.net.MalformedURLException;

import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import fr.inria.atlanmod.neo4emf.neo4jresolver.Neo4jResolverPlugin;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntime;

/**
 * The activator class controls the plug-in life cycle
 */
public class Neo4emfPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "fr.inria.atlanmod.neo4emf"; //$NON-NLS-1$

	public static final String NEO4J_RUNTIME_ID = "fr.inria.atlanmod.neo4emf.neo4j-1.9.4"; //$NON-NLS-1$

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
		loadNeo4jRuntime(context);
	}

	private void loadNeo4jRuntime(BundleContext context) throws BundleException {
		neo4jBundle = Neo4jResolverPlugin.getDefault().installNeo4jRuntimeInContext(context, NEO4J_RUNTIME_ID);
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

}
