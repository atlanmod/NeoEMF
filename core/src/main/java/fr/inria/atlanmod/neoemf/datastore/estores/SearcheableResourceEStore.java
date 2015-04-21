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
package fr.inria.atlanmod.neoemf.datastore.estores;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.neoemf.core.Id;

/**
 * This interface extends the {@link EStore} interface and allows to establish a
 * mapping between {@link Resource}s and {@link EStore}s
 * 
 * @author agomez
 * 
 */
public interface SearcheableResourceEStore extends InternalEObject.EStore {

	/**
	 * Returns the {@link Resource} to which this {@link EStore} is associated
	 * 
	 * @return
	 */
	public Resource resource();

	/**
	 * Returns the resolved {@link EObject} identified by the given
	 * <code>id</code> or <code>null</code> if no {@link EObject} can be
	 * resolved.
	 * 
	 * @param id
	 * @return
	 */
	public EObject eObject(Id id);
	
}
