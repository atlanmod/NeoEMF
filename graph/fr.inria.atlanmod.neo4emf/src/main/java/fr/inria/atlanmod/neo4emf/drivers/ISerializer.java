package fr.inria.atlanmod.neo4emf.drivers;

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
 * @author Amine BENELALLAM
 * */

import java.util.Map;

public interface ISerializer {
	
	String MAX_OPERATIONS_PER_TRANSACTION = "max_oper_per_trans";
	
	String TMP_SAVE = "tmp_save";
	
	int DEFAULT_TRANSACTIONS_COUNT = 10000;
	
	boolean DEFAULT_TMP_SAVE = false;
	
	String [] saveOptions = {MAX_OPERATIONS_PER_TRANSACTION, TMP_SAVE};
	
	Object [] saveDefaultValues={DEFAULT_TRANSACTIONS_COUNT, DEFAULT_TMP_SAVE};
	
	/**
	 * @see IPersistenceManager#save(Map)
	 * @param options
	 */
	void save(Map<String, Object> options);
}
