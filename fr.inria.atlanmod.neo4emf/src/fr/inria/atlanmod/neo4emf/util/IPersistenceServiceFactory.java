package fr.inria.atlanmod.neo4emf.util;

import fr.inria.atlanmod.neo4emf.util.impl.PersistenceServiceFactory;

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

public interface IPersistenceServiceFactory {

	/**
	 * The singleton instance
	 * @see PersistenceServiceFactory#init()
	 */
	IPersistenceServiceFactory eINSTANCE =  fr.inria.atlanmod.neo4emf.util.impl.PersistenceServiceFactory.init();

	/**
	 * Create a persistence service
	 * @param path {@link String}
	 * @param persistenceManager {@link IPersistenceManager}
	 * @return {@link IPersistenceService}
	 */
	IPersistenceService createPersistenceService(String path,
			IPersistenceManager persistenceManager);
	
}
