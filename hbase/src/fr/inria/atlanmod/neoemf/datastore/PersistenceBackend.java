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
package fr.inria.atlanmod.neoemf.datastore;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import fr.inria.atlanmod.neoemf.datastore.exceptions.InvalidDataStoreException;

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
}

