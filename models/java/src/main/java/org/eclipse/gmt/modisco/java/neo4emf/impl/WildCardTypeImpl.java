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
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.WildCardType;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Wild Card Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.WildCardTypeImpl#isUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.WildCardTypeImpl#getBound <em>Bound</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WildCardTypeImpl extends TypeImpl implements WildCardType {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WildCardTypeImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataWildCardType getData(){
		if ( data == null || !(data instanceof DataWildCardType)){
			data = new DataWildCardType();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataWildCardType) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getWildCardType();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUpperBound() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().upperBound;
		
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
	public void setUpperBound(boolean newUpperBound) {
	
		
	
		boolean oldUpperBound = getData().upperBound;
		getData().upperBound = newUpperBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.WILD_CARD_TYPE__UPPER_BOUND,
			oldUpperBound, getData().upperBound));
	    addChangelogEntry(newUpperBound, JavaPackage.WILD_CARD_TYPE__UPPER_BOUND);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess getBound() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.WILD_CARD_TYPE__BOUND);
		}
		return getData().bound;
	} finally {
	loadingOnDemand = false;
}
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBound(TypeAccess newBound, NotificationChain msgs) {
	
		
	
		TypeAccess oldBound = getData().bound;
		getData().bound = newBound;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.WILD_CARD_TYPE__BOUND, oldBound, newBound);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setBound(TypeAccess newBound) {
	
		
	
		if (newBound != getData().bound) {
			NotificationChain msgs = null;
			if (getData().bound != null)
				msgs = ((InternalEObject) getData().bound).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.WILD_CARD_TYPE__BOUND, null, msgs);
			if (newBound != null)
				msgs = ((InternalEObject)newBound).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.WILD_CARD_TYPE__BOUND, null, msgs);
			msgs = basicSetBound(newBound, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.WILD_CARD_TYPE__BOUND, newBound, newBound));
	    addChangelogEntry(newBound, JavaPackage.WILD_CARD_TYPE__BOUND);
	} 

	/**
	 * <!-- begin-user-doc -->
	 *YY13
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaPackage.WILD_CARD_TYPE__BOUND:
				return basicSetBound(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
			case JavaPackage.WILD_CARD_TYPE__UPPER_BOUND:
				return isUpperBound();
			case JavaPackage.WILD_CARD_TYPE__BOUND:
				return getBound();
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
			case JavaPackage.WILD_CARD_TYPE__UPPER_BOUND:
				setUpperBound((Boolean)newValue);
				return;
			case JavaPackage.WILD_CARD_TYPE__BOUND:
				setBound((TypeAccess)newValue);
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
			case JavaPackage.WILD_CARD_TYPE__UPPER_BOUND:
				setUpperBound(DataWildCardType.UPPER_BOUND_EDEFAULT);
				return;
			case JavaPackage.WILD_CARD_TYPE__BOUND:
				setBound((TypeAccess)null);
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
			case JavaPackage.WILD_CARD_TYPE__UPPER_BOUND:
				return isUpperBound() != DataWildCardType.UPPER_BOUND_EDEFAULT;
			case JavaPackage.WILD_CARD_TYPE__BOUND:
				return getBound() != null;
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
protected static  class DataWildCardType extends DataType {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #isUpperBound() <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUpperBound()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UPPER_BOUND_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUpperBound() <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUpperBound()
	 * @generated
	 * @ordered
	 */
	protected boolean upperBound = UPPER_BOUND_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getBound() <em>Bound</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBound()
	 * @generated
	 * @ordered
	 */
	protected TypeAccess bound;

	/**
	 *Constructor of DataWildCardType
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataWildCardType() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataWildCardType
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Type }
	 * @generated
	 */
	//public DataWildCardType(DataType data)
	//{
	//	this();		
	//	
	//	usagesInTypeAccess = data.usagesInTypeAccess;
	//	}
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (upperBound: ");
		result.append(upperBound);
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
} //WildCardTypeImpl
