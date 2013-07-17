package fr.inria.atlanmod.neo4emf.impl;

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
import java.awt.Point;


import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.neo4j.graphdb.RelationshipType;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.INeo4emfResourceFactory;

public class Neo4emfResourceFactory extends ResourceFactoryImpl implements
		INeo4emfResourceFactory {

	@Override
	/**
	 * creates the resource 
	 */
	public INeo4emfResource createResource() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @ see {@link INeo4emfResourceFactory#createResource(String, Map)}
	 */
	@Override
	public INeo4emfResource createResource(String storeDirectory, Map <Point, RelationshipType> map) {
		return new Neo4emfResource(storeDirectory,map);
	}
	/**
	 * init the singleton instance
	 * @return singleton instance 
	 */
	public static INeo4emfResourceFactory init() {
		if (eINSTANCE == null)
			return new Neo4emfResourceFactory();
		return eINSTANCE;
	}
	
}
