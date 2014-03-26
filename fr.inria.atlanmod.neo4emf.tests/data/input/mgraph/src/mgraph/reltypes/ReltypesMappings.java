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
package mgraph.reltypes;

import fr.inria.atlanmod.neo4emf.Point;

import java.util.HashMap;
import java.util.Map;

import mgraph.MgraphPackage;

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
		
			
		
					Map<Point,RelationshipType> mapMgraphPackage = new HashMap<Point,RelationshipType>();
				
			mapMgraphPackage.put(new Point(MgraphPackage.MGRAPH,MgraphPackage.MGRAPH__NODES),Reltypes.MGRAPH__NODES);
					
			mapMgraphPackage.put(new Point(MgraphPackage.MGRAPH,MgraphPackage.MGRAPH__EDGES),Reltypes.MGRAPH__EDGES);
					
			mapMgraphPackage.put(new Point(MgraphPackage.MEDGE,MgraphPackage.MEDGE__IN_COMING),Reltypes.MEDGE__IN_COMING);
					
			mapMgraphPackage.put(new Point(MgraphPackage.MEDGE,MgraphPackage.MEDGE__OUT_GOING),Reltypes.MEDGE__OUT_GOING);
					
			mapMgraphPackage.put(new Point(MgraphPackage.MEDGE,MgraphPackage.MEDGE__GRAPH),Reltypes.MEDGE__GRAPH);
					
			mapMgraphPackage.put(new Point(MgraphPackage.MNODE,MgraphPackage.MNODE__GRAPH),Reltypes.MNODE__GRAPH);
					
			mapMgraphPackage.put(new Point(MgraphPackage.MNODE,MgraphPackage.MNODE__FROM),Reltypes.MNODE__FROM);
					
			mapMgraphPackage.put(new Point(MgraphPackage.MNODE,MgraphPackage.MNODE__TO),Reltypes.MNODE__TO);
					
			reference2relation.put(MgraphPackage.eNS_URI,mapMgraphPackage);
		
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
