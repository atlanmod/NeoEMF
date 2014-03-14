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

import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.Expression;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Annotation Member Value Pair</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.AnnotationMemberValuePairImpl#getMember <em>Member</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.AnnotationMemberValuePairImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnnotationMemberValuePairImpl extends NamedElementImpl implements AnnotationMemberValuePair {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnnotationMemberValuePairImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataAnnotationMemberValuePair getData(){
		if ( data == null || !(data instanceof DataAnnotationMemberValuePair)){
			data = new DataAnnotationMemberValuePair();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataAnnotationMemberValuePair) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getAnnotationMemberValuePair();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnnotationTypeMemberDeclaration getMember() {
		try {
			loadingOnDemand = true;	
	  
		if (getData().member == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__MEMBER);
		}		
		return getData().member;
		
	} finally {
	loadingOnDemand = false;
}
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX8-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnnotationTypeMemberDeclaration basicGetMember() {
		return data != null ? getData().member : null;
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMember(AnnotationTypeMemberDeclaration newMember, NotificationChain msgs) {
	
		
	
		AnnotationTypeMemberDeclaration oldMember = getData().member;
		getData().member = newMember;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__MEMBER, oldMember, newMember);
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
	public void setMember(AnnotationTypeMemberDeclaration newMember) {
	
		
	
		if (newMember != getData().member) {
			NotificationChain msgs = null;
			if (getData().member != null)
				msgs = ((InternalEObject) getData().member).eInverseRemove(this, JavaPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__USAGES, AnnotationTypeMemberDeclaration.class, msgs);
			if (newMember != null)
				msgs = ((InternalEObject)newMember).eInverseAdd(this, JavaPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__USAGES, AnnotationTypeMemberDeclaration.class, msgs);
			msgs = basicSetMember(newMember, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__MEMBER, newMember, newMember));
	    addChangelogEntry(newMember, JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__MEMBER);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getValue() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__VALUE);
		}
		return getData().value;
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
	public NotificationChain basicSetValue(Expression newValue, NotificationChain msgs) {
	
		
	
		Expression oldValue = getData().value;
		getData().value = newValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__VALUE, oldValue, newValue);
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
	public void setValue(Expression newValue) {
	
		
	
		if (newValue != getData().value) {
			NotificationChain msgs = null;
			if (getData().value != null)
				msgs = ((InternalEObject) getData().value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__VALUE, null, msgs);
			if (newValue != null)
				msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__VALUE, null, msgs);
			msgs = basicSetValue(newValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__VALUE, newValue, newValue));
	    addChangelogEntry(newValue, JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__VALUE);
	} 

/**
	 * <!-- begin-user-doc -->
	 *YY12
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__MEMBER:
				if (getData().member != null)
					msgs = ((InternalEObject)getData().member).eInverseRemove(this, JavaPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__USAGES, AnnotationTypeMemberDeclaration.class, msgs);
				return basicSetMember((AnnotationTypeMemberDeclaration)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
			case JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__MEMBER:
				return basicSetMember(null, msgs);
			case JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__VALUE:
				return basicSetValue(null, msgs);
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
			case JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__MEMBER:
				if (resolve) return getMember();
				return basicGetMember();
			case JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__VALUE:
				return getValue();
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
			case JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__MEMBER:
				setMember((AnnotationTypeMemberDeclaration)newValue);
				return;
			case JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__VALUE:
				setValue((Expression)newValue);
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
			case JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__MEMBER:
				setMember((AnnotationTypeMemberDeclaration)null);
				return;
			case JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__VALUE:
				setValue((Expression)null);
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
			case JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__MEMBER:
				return getMember() != null;
			case JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR__VALUE:
				return getValue() != null;
		}
		return super.eIsSet(featureID);
	}

/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




// data Class generation 
protected static  class DataAnnotationMemberValuePair extends DataNamedElement {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getMember() <em>Member</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMember()
	 * @generated
	 * @ordered
	 */
	protected AnnotationTypeMemberDeclaration member;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected Expression value;

	/**
	 *Constructor of DataAnnotationMemberValuePair
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataAnnotationMemberValuePair() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataAnnotationMemberValuePair
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link NamedElement }
	 * @generated
	 */
	//public DataAnnotationMemberValuePair(DataNamedElement data)
	//{
	//	this();		
	//	
	//	name = data.name;
	//	proxy = data.proxy;
	//	usagesInImports = data.usagesInImports;
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
} //AnnotationMemberValuePairImpl
