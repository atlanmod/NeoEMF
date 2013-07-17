package fr.inria.atlanmod.neo4emf.util;

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
	int DEFAULT_TRANSACTIONS_COUNT = 10000;
	String [] saveOptions = {MAX_OPERATIONS_PER_TRANSACTION};
	Object [] saveDefaultValues={DEFAULT_TRANSACTIONS_COUNT};
	void save(Map<String, Object> options);

}
