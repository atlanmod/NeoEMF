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
package mteach.reltypes;

import fr.inria.atlanmod.neo4emf.Point;

import java.util.HashMap;
import java.util.Map;

import mteach.MteachPackage;

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
		
			
		
					Map<Point,RelationshipType> mapMteachPackage = new HashMap<Point,RelationshipType>();
				
			mapMteachPackage.put(new Point(MteachPackage.PROFESSOR,MteachPackage.PROFESSOR__TEACHED_COURSES),Reltypes.PROFESSOR__TEACHED_COURSES);
					
			mapMteachPackage.put(new Point(MteachPackage.COURSE,MteachPackage.COURSE__TOPICS),Reltypes.COURSE__TOPICS);
					
			mapMteachPackage.put(new Point(MteachPackage.COURSE,MteachPackage.COURSE__PROFESSOR),Reltypes.COURSE__PROFESSOR);
					
			mapMteachPackage.put(new Point(MteachPackage.TOPIC,MteachPackage.TOPIC__COURSE),Reltypes.TOPIC__COURSE);
					
			reference2relation.put(MteachPackage.eNS_URI,mapMteachPackage);
		
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
