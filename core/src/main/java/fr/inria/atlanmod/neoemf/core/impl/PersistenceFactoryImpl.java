/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.core.impl;

import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EFactoryImpl;

public class PersistenceFactoryImpl extends EFactoryImpl implements PersistenceFactory {
	
	@Override
	public InternalPersistentEObject create(EClass eClass) {
		PersistentEObjectImpl eObject = new PersistentEObjectImpl();
		eObject.eSetClass(eClass);
		return eObject;
	}
}
