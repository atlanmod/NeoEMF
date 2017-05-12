/*******************************************************************************
 * Copyright (c) 2008, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.examples;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * ATL examples plugin.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class NeoEMFExamplesPlugin extends AbstractUIPlugin {

	/** The plugin id. */
	public static final String PLUGIN_ID = "fr.inria.atlanmod.neoemf.examples"; //$NON-NLS-1$
	
	private static NeoEMFExamplesPlugin instance;

	/**
	 * Constructor.
	 */
	public NeoEMFExamplesPlugin() {
	    System.out.println("NeoEMF tutorial plugin started");
		instance = this;
	}

	/**
	 * Returns the default {@link NeoEMFExamplesPlugin} instance.
	 * 
	 * @return the default {@link NeoEMFExamplesPlugin} instance
	 */
	public static NeoEMFExamplesPlugin getDefault() {
		if (instance == null) {
			return new NeoEMFExamplesPlugin();
		}
		return instance;
	}

}
