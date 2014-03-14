
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
    		
    		COURSE__TOPICS,
    		
    		COURSE__PROFESSOR,
    		
    		TOPIC__COURSE,
    		
} 

//Reltypes Class
