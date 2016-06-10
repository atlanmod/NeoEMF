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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;

import fr.inria.atlanmod.neoemf.core.impl.NeoEMFEFactoryImpl;


public interface NeoEMFEFactory extends EFactory {
	
	public static int UUID_LENGTH = 23;

	public static NeoEMFEFactory eINSTANCE = new NeoEMFEFactoryImpl();

	@Override
	public NeoEMFEObject create(EClass eClass);
	
}
