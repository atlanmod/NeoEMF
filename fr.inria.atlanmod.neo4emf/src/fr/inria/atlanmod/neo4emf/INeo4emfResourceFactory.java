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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.neo4j.graphdb.RelationshipType;

import fr.inria.atlanmod.neo4emf.impl.Neo4emfResource;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfResourceFactory;



public interface INeo4emfResourceFactory extends Resource.Factory {
	
		/**
		 * The singleton instance
		 */
		INeo4emfResourceFactory eINSTANCE = Neo4emfResourceFactory.init();
		/**
		 * Creates a resource from an URI
		 */
		public abstract INeo4emfResource createResource(URI uri);
		
		/**
		 * creates {@link Neo4emfResource} 
		 * @param storeDirectory
		 * @param map
		 * @return {@link INeo4emfObject}
		 */
		INeo4emfResource createResource(String storeDirectory,
				Map< String ,Map <Point, RelationshipType>> map);
		/**
		 * Sets the relationshipsMap to create the Factory
		 * @param map
		 * @return
		 */
		INeo4emfResourceFactory setRelationshipsMap(Map<String, Map<Point, RelationshipType>> map);

		
}
