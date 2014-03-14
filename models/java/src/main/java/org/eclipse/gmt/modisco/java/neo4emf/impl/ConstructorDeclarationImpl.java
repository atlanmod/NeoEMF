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

import org.eclipse.gmt.modisco.java.ConstructorDeclaration;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constructor Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ConstructorDeclarationImpl extends AbstractMethodDeclarationImpl implements ConstructorDeclaration {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstructorDeclarationImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataConstructorDeclaration getData(){
		if ( data == null || !(data instanceof DataConstructorDeclaration)){
			data = new DataConstructorDeclaration();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataConstructorDeclaration) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getConstructorDeclaration();
	}


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




// data Class generation 
protected static  class DataConstructorDeclaration extends DataAbstractMethodDeclaration {


	/**
	 *Constructor of DataConstructorDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataConstructorDeclaration() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataConstructorDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link AbstractMethodDeclaration }
	 * @generated
	 */
	//public DataConstructorDeclaration(DataAbstractMethodDeclaration data)
	//{
	//	this();		
	//	
	//	body = data.body;
	//	parameters = data.parameters;
	//	thrownExceptions = data.thrownExceptions;
	//	typeParameters = data.typeParameters;
	//	usagesInDocComments = data.usagesInDocComments;
	//	usages = data.usages;
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
} //ConstructorDeclarationImpl
