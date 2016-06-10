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

import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.neoemf.core.impl.NeoEMFResourceFactoryImpl;

public interface NeoEMFResourceFactory extends Resource.Factory {

	public static NeoEMFResourceFactory eINSTANCE = new NeoEMFResourceFactoryImpl();
	
}
