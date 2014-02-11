/**
 * Copyright (c) 2006, 2013 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package fr.inria.atlanmod.neo4emf.codegen;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

/**
 * Neo4emfGeneratorPlugin singleton instance
 * 
 * @author abelgomez
 * 
 */
public final class Neo4emfGeneratorPlugin extends EMFPlugin {
	
	/**
	 * The plugin ID
	 */
	public static final String PLUGIN_ID = "fr.inria.atlanmod.neo4emf.codegen";

	/**
	 * The singleton instance of the plugin.
	 */
	public static final Neo4emfGeneratorPlugin INSTANCE = new Neo4emfGeneratorPlugin();

	/**
	 * The one instance of this class.
	 */
	private static Implementation plugin;

	/**
	 * Creates the singleton instance.
	 */
	private Neo4emfGeneratorPlugin() {
		super(new ResourceLocator[] {});
	}

	/*
	 * Javadoc copied from base class.
	 */
	@Override
	public ResourceLocator getPluginResourceLocator() {
		return plugin;
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * 
	 * @return the singleton instance.
	 */
	public static Implementation getPlugin() {
		return plugin;
	}

	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>.
	 */
	public static class Implementation extends EclipsePlugin {
		/**
		 * Creates an instance.
		 */
		public Implementation() {
			super();

			// Remember the static instance.
			//
			plugin = this;
		}
	}
}
