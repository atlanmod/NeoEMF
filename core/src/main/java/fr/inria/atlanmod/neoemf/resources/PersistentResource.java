/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p>
 * Contributors:
 * Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.resources;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

public interface PersistentResource extends Resource, Resource.Internal {
	
	public abstract InternalEObject.EStore eStore();
	
	/**
	 * <p>
	 * Computes the set of instances of the given EClass (including its sub-types)
	 * This method is similar to getAllInstances(myEClass, false);
	 * </p>
	 * @param eClass the EClass for which look for instances
	 * @return all the instances of the given EClass from the resource
	 */
	public EList<EObject> getAllInstances(EClass eClass);
	
	/**
	 * <p>
	 * Computes the set of instances of the given EClass
	 * </p>
	 * @param eClass the EClass for which look for instances
	 * @param strict true if the lookup searches for strict instances
	 * @return if <b>true</b> then the method returns only 
     * the strict instances of the given EClass. If <b>false</b> it 
     * also returns the instances of the sub-types of eClass.
	 */
	public EList<EObject> getAllInstances(EClass eClass, boolean strict);
	
}
