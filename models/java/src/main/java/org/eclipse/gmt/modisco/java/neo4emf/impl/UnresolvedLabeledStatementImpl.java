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
package org.eclipse.gmt.modisco.java.neo4emf.impl;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.UnresolvedLabeledStatement;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unresolved Labeled Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class UnresolvedLabeledStatementImpl extends LabeledStatementImpl implements UnresolvedLabeledStatement {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnresolvedLabeledStatementImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataUnresolvedLabeledStatement getData(){
		if ( data == null || !(data instanceof DataUnresolvedLabeledStatement)){
			data = new DataUnresolvedLabeledStatement();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataUnresolvedLabeledStatement) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getUnresolvedLabeledStatement();
	}


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




// data Class generation 
protected static  class DataUnresolvedLabeledStatement extends DataLabeledStatement {


	/**
	 *Constructor of DataUnresolvedLabeledStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataUnresolvedLabeledStatement() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataUnresolvedLabeledStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link LabeledStatement }
	 * @generated
	 */
	//public DataUnresolvedLabeledStatement(DataLabeledStatement data)
	//{
	//	this();		
	//	
	//	body = data.body;
	//	usagesInBreakStatements = data.usagesInBreakStatements;
	//	usagesInContinueStatements = data.usagesInContinueStatements;
	//	}
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(')');
		return result.toString();
	}
		

/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/
}//end data class
} //UnresolvedLabeledStatementImpl
