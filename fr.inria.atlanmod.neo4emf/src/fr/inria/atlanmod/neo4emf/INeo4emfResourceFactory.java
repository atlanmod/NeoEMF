package fr.inria.atlanmod.neo4emf;
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
import java.awt.Point;
import java.util.Map;

import org.neo4j.graphdb.RelationshipType;

import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfResource;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfResourceFactory;


public interface INeo4emfResourceFactory  {
	
		/**
		 * The singleton instance
		 */
		INeo4emfResourceFactory eINSTANCE = Neo4emfResourceFactory.init();
		public INeo4emfResource createResource();
		
		/**
		 * creates {@link Neo4emfResource} 
		 * @param storeDirectory
		 * @param map
		 * @return {@link INeo4emfObject}
		 */
		INeo4emfResource createResource(String storeDirectory,
				Map<Point, RelationshipType> map);

	
}
