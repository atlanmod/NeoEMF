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
**/
package fr.inria.atlanmod.neo4emf.testdata.reltypes;

import fr.inria.atlanmod.neo4emf.Point;

import fr.inria.atlanmod.neo4emf.testdata.TestPackage;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.RelationshipType;

	/**
 	* <!-- begin-user-doc -->
 	* Neo4j <b>relationships mapping</b> for model persistence.
 	* It provides hashmaps to map relationships to the appropriate feature ID.
 	* <!-- end-user-doc -->
 	* @generated
 	*/
public class ReltypesMappings {
	private static ReltypesMappings instance;
	
	
	/**
	 * 
	 * @generated
	 */
	 
	public static ReltypesMappings getInstance(){
		if (instance == null)
			return new ReltypesMappings ();
		else return instance;
		}
		
	/**
	 *getter of the Map
	 * @generated
	 */
		public Map<String,Map<Point,RelationshipType>> getMap(){
		return reference2relation;
	}
	
	/**
	 * constructor of Relationship type mappings
	 * @generated
	 */
		private final Map<String,Map<Point,RelationshipType>> reference2relation;
	
		private ReltypesMappings (){
			
			reference2relation= new HashMap<String,Map<Point,RelationshipType>>();		
		
			
		
					Map<Point,RelationshipType> mapTestPackage = new HashMap<Point,RelationshipType>();
				
			mapTestPackage.put(new Point(TestPackage.CONTAINER_TYPE,TestPackage.CONTAINER_TYPE__NODES),Reltypes.CONTAINER_TYPE__NODES);
					
			mapTestPackage.put(new Point(TestPackage.CONTAINER_TYPE,TestPackage.CONTAINER_TYPE__LINKS),Reltypes.CONTAINER_TYPE__LINKS);
					
			mapTestPackage.put(new Point(TestPackage.LINK,TestPackage.LINK__OUT_GOING),Reltypes.LINK__OUT_GOING);
					
			mapTestPackage.put(new Point(TestPackage.LINK,TestPackage.LINK__CONTAINER),Reltypes.LINK__CONTAINER);
					
			mapTestPackage.put(new Point(TestPackage.LINK,TestPackage.LINK__IN_COMING),Reltypes.LINK__IN_COMING);
					
			mapTestPackage.put(new Point(TestPackage.VERTEX,TestPackage.VERTEX__VCONTAINER),Reltypes.VERTEX__VCONTAINER);
					
			mapTestPackage.put(new Point(TestPackage.VERTEX,TestPackage.VERTEX__FROM),Reltypes.VERTEX__FROM);
					
			mapTestPackage.put(new Point(TestPackage.VERTEX,TestPackage.VERTEX__TO),Reltypes.VERTEX__TO);
					
			mapTestPackage.put(new Point(TestPackage.LINK_VERTEX,TestPackage.LINK_VERTEX__OUT_GOING),Reltypes.LINK_VERTEX__OUT_GOING);
					
			mapTestPackage.put(new Point(TestPackage.LINK_VERTEX,TestPackage.LINK_VERTEX__CONTAINER),Reltypes.LINK_VERTEX__CONTAINER);
					
			mapTestPackage.put(new Point(TestPackage.LINK_VERTEX,TestPackage.LINK_VERTEX__IN_COMING),Reltypes.LINK_VERTEX__IN_COMING);
					
			mapTestPackage.put(new Point(TestPackage.LINK_VERTEX,TestPackage.LINK_VERTEX__VCONTAINER),Reltypes.LINK_VERTEX__VCONTAINER);
					
			mapTestPackage.put(new Point(TestPackage.LINK_VERTEX,TestPackage.LINK_VERTEX__FROM),Reltypes.LINK_VERTEX__FROM);
					
			mapTestPackage.put(new Point(TestPackage.LINK_VERTEX,TestPackage.LINK_VERTEX__TO),Reltypes.LINK_VERTEX__TO);
					
			mapTestPackage.put(new Point(TestPackage.LINK_VERTEX,TestPackage.LINK_VERTEX__LINK),Reltypes.LINK_VERTEX__LINK);
					
			mapTestPackage.put(new Point(TestPackage.LINK_VERTEX,TestPackage.LINK_VERTEX__VERTEX),Reltypes.LINK_VERTEX__VERTEX);
					
			mapTestPackage.put(new Point(TestPackage.COLORED_VERTEX,TestPackage.COLORED_VERTEX__VCONTAINER),Reltypes.COLORED_VERTEX__VCONTAINER);
					
			mapTestPackage.put(new Point(TestPackage.COLORED_VERTEX,TestPackage.COLORED_VERTEX__FROM),Reltypes.COLORED_VERTEX__FROM);
					
			mapTestPackage.put(new Point(TestPackage.COLORED_VERTEX,TestPackage.COLORED_VERTEX__TO),Reltypes.COLORED_VERTEX__TO);
					
			reference2relation.put(TestPackage.eNS_URI,mapTestPackage);
		
		}
		
	/**
	* Getting a Relationship from an eRef belonging to 
	* an {@link EObject} eObject
	*@param eObject {@link EObject}
	*@param eRef {@link EReference}
	*@generated
	*/
	//public RelationshipType getReltype(EObject eObject, EReference eRef) {
		//	String key = eObject.eClass().getEPackage().getNsURI();		
			//return getMap().get(key).get(new Point(eObject.eClass().getClassifierID(), eRef.getFeatureID()));
		//}
} 
	
//Reltypes Class
