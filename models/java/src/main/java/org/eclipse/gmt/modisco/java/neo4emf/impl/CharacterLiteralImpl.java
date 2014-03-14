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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.CharacterLiteral;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Character Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.CharacterLiteralImpl#getEscapedValue <em>Escaped Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CharacterLiteralImpl extends ExpressionImpl implements CharacterLiteral {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CharacterLiteralImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataCharacterLiteral getData(){
		if ( data == null || !(data instanceof DataCharacterLiteral)){
			data = new DataCharacterLiteral();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataCharacterLiteral) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getCharacterLiteral();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEscapedValue() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().escapedValue;
		
	} finally {
	loadingOnDemand = false;
}
	}
 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setEscapedValue(String newEscapedValue) {
	
		
	
		String oldEscapedValue = getData().escapedValue;
		getData().escapedValue = newEscapedValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.CHARACTER_LITERAL__ESCAPED_VALUE,
			oldEscapedValue, getData().escapedValue));
	    addChangelogEntry(newEscapedValue, JavaPackage.CHARACTER_LITERAL__ESCAPED_VALUE);
	} 

	/**
	 * <!-- begin-user-doc -->
	 *YY15
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JavaPackage.CHARACTER_LITERAL__ESCAPED_VALUE:
				return getEscapedValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY16
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JavaPackage.CHARACTER_LITERAL__ESCAPED_VALUE:
				setEscapedValue((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY17-Bis
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case JavaPackage.CHARACTER_LITERAL__ESCAPED_VALUE:
				setEscapedValue(DataCharacterLiteral.ESCAPED_VALUE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY18
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case JavaPackage.CHARACTER_LITERAL__ESCAPED_VALUE:
				return DataCharacterLiteral.ESCAPED_VALUE_EDEFAULT == null ? getEscapedValue() != null : !DataCharacterLiteral.ESCAPED_VALUE_EDEFAULT.equals(getEscapedValue());
		}
		return super.eIsSet(featureID);
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY27
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		if (data != null) result.append(data.toString());
		
		return result.toString();
		}

/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




// data Class generation 
protected static  class DataCharacterLiteral extends DataExpression {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getEscapedValue() <em>Escaped Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEscapedValue()
	 * @generated
	 * @ordered
	 */
	protected static final String ESCAPED_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEscapedValue() <em>Escaped Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEscapedValue()
	 * @generated
	 * @ordered
	 */
	protected String escapedValue = ESCAPED_VALUE_EDEFAULT;

	/**
	 *Constructor of DataCharacterLiteral
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataCharacterLiteral() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataCharacterLiteral
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	//public DataCharacterLiteral(DataExpression data)
	//{
	//	this();		
	//	
	//	}
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (escapedValue: ");
		result.append(escapedValue);
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
} //CharacterLiteralImpl
