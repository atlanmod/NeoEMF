/**
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 * Descritpion ! To come
 * @author Sunye
 */

/**
 * Interface for database transactions.
 */
package fr.inria.atlanmod.neo4emf.drivers.impl;

public interface NETransaction {

	/**
	 * Aborts the current transaction.
	 */
	public abstract void abort();

	/**
	 * Finishes the current transaction.
	 */
	public abstract void success();

	/**
	 * Commits the transaction.
	 */
	public abstract void commit();

}