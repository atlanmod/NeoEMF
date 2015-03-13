/*******************************************************************************
 * Copyright (c) 2014 Abel Gómez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel Gómez - initial API and implementation
 ******************************************************************************/
package fr.inria.atlanmod.kyanos.core;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

public interface KyanosResource extends Resource, Resource.Internal {

	public final static String OPTIONS_GRAPH_TYPE = "blueprints.graph";
	public final static String OPTIONS_GRAPH_TYPE_DEFAULT = "com.tinkerpop.blueprints.impls.tg.TinkerGraph";

	
	public abstract InternalEObject.EStore eStore();
	
}
