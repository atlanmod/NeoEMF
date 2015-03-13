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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.INeo4emfResourceFactory;
import fr.inria.atlanmod.neo4emf.drivers.NEConfiguration;

public class Neo4emfResourceFactory extends ResourceFactoryImpl implements
		INeo4emfResourceFactory {

	private NEConfiguration configuration;

	public Neo4emfResourceFactory(NEConfiguration nec) {
		configuration = nec;
	}

	public Neo4emfResourceFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * creates the resource 
	 */
	public INeo4emfResource createResource(URI uri) {
		assert configuration != null : "Configuration is null";
		
		return new Neo4emfResource(configuration);
	}

	/**
	 * @ see {@link INeo4emfResourceFactory#createResource(String, Map)}
	 */

	/*
	 * @Override public INeo4emfResource createResource(final String
	 * storeDirectory, final Map< String, Map <Point, RelationshipType>> map) {
	 * return new Neo4emfResource(storeDirectory, map, null); }
	 */

	/**
	 * init the singleton instance
	 * 
	 * @return singleton instance
	 */
	public static INeo4emfResourceFactory init() {
		if (eINSTANCE == null) {
			return new Neo4emfResourceFactory();
		}
		return eINSTANCE;
	}

	@Override
	public void setConfiguration(NEConfiguration nec) {
		configuration = nec;
	}

}
