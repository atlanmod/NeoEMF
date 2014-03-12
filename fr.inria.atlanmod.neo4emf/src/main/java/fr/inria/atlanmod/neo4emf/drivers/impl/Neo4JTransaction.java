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
package fr.inria.atlanmod.neo4emf.drivers.impl;

import org.neo4j.graphdb.Transaction;

/**
 * Wrapper class for Neo4j transactions.
 * 
 * @author sunye
 *
 */
public class Neo4JTransaction implements NETransaction {

	final private Transaction transaction;
	
	public Neo4JTransaction(Transaction t) {
		this.transaction = t;
	}
	
	/* (non-Javadoc)
	 * @see fr.inria.atlanmod.neo4emf.drivers.impl.NETransaction#abort()
	 */
	@Override
	public void abort() {
		transaction.failure();
	}
	
	/* (non-Javadoc)
	 * @see fr.inria.atlanmod.neo4emf.drivers.impl.NETransaction#success()
	 */
	@Override
	public void success() {
		transaction.success();
	}
	
	/* (non-Javadoc)
	 * @see fr.inria.atlanmod.neo4emf.drivers.impl.NETransaction#commit()
	 */
	@Override
	public void commit() {
		transaction.finish();
	}
}
