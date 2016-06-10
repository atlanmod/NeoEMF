/*******************************************************************************
 * Copyright (c) 2014 Abel G�mez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel G�mez - initial API and implementation
 ******************************************************************************/
package fr.inria.atlanmod.neoemf.core;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

public interface NeoEMFResource extends Resource, Resource.Internal {

	public final static String OPTIONS_GRAPH_TYPE = "blueprints.graph";
	public final static String OPTIONS_GRAPH_TYPE_DEFAULT = "com.tinkerpop.blueprints.impls.tg.TinkerGraph";
	/**
	 * an option for creating read-only models
	 * the value by default is {@value <code>false</code>}
	 */
	String  OPTIONS_HBASE_READ_ONLY = "hbase.readOnly";
	boolean OPTIONS_HBASE_READ_ONLY_DEFAULT = false; 
	
	public abstract InternalEObject.EStore eStore();
	
}
