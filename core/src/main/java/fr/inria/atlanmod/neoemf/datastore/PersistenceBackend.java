/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.datastore;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public interface PersistenceBackend {

	/**
	 * Starts the underlying data store with the given <code>options</code>
	 * 
	 * @param options
	 * @throws InvalidDataStoreException
	 */
	public void start(Map<?, ?> options) throws InvalidDataStoreException;
	
	/**
	 * Returns whether the underlying data store has been started or not.
	 * 
	 * @return
	 */
	public boolean isStarted();
	
	/**
	 * Cleanly stops the underlying data store.
	 */
	public void stop();
	
	/**
	 * Saves the modifications of the owned {@link EObject}s in the persistence
	 * back-end
	 */
	public void save();
	
	/**
	 * Back-end specific computation of allInstances
	 * @param eClass the class to compute the instances of
	 * @param strict true if the lookup searches for strict instances
	 * @return an Object containing the back-end specific objects corresponding to the instances of the EClass
	 * @throws UnsupportedOperationException if the back-end does not support allInstances lookup
	 */
	public Object getAllInstances(EClass eClass, boolean strict) throws UnsupportedOperationException;
	
}

