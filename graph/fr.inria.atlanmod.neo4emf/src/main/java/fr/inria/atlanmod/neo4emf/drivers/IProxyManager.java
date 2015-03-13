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

import org.eclipse.emf.ecore.EClass;

import com.google.common.cache.Cache;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;

public interface IProxyManager {
	
	/**
	 * Put an EObject into the proxy
	 * @param obj the INeo4emfObject to cache
	 */
	void putToProxy(INeo4emfObject obj);
	
	/**
	 * Search in the cache if there is an INeo4emfObject corresponding to the
	 * given EClass and database node ID
	 * <p>
	 * Note : The EClass is used to optimize the cache research
	 * </p>
	 * @param eClassifier the EClass of the INeo4emfObject
	 * @param nodeId the database node ID
	 * @return the INeo4emfObject if it is cached, null otherwise
	 */
	INeo4emfObject getObjectFromProxy(EClass eClassifier, long nodeId);	
	
	/**
	 * <p>
	 * Warning : This method is public only for test purpose
	 * </p>
	 * @return the internal cache mapping
	 */
	Map<EClass,Cache<Long,INeo4emfObject>> getInternalProxy();
}
