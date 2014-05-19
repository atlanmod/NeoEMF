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

import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.kyanos.core.impl.KyanosResourceFactoryImpl;

public interface KyanosResourceFactory extends Resource.Factory {

	public static KyanosResourceFactory eINSTANCE = new KyanosResourceFactoryImpl();
	
}
