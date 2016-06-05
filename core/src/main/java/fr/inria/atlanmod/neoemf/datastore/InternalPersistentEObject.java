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

package fr.inria.atlanmod.neoemf.datastore;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

public interface InternalPersistentEObject extends PersistentEObject, InternalEObject {

	boolean isMapped();

	void setMapped(boolean mapped);

	Resource.Internal resource();
	
	void resource(Resource.Internal resource);
}
