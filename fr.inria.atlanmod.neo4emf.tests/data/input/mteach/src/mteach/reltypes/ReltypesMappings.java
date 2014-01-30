/**
 *
 * $Id$
 */
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
