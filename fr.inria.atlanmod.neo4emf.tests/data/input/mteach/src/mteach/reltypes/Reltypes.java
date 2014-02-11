
/**
 *
 * $Id$
 */
package mteach.reltypes;

import org.neo4j.graphdb.RelationshipType;
/**
 * <!-- begin-user-doc -->
 * Neo4j <b>relationships</b> for model persistence.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @generated
 */
public enum Reltypes implements RelationshipType {
	
    		PROFESSOR__TEACHED_COURSES,
    		
    		COURSE__TOPICS, COURSE__PROFESSOR, TOPIC__COURSE,
    		
} 

//Reltypes Class
