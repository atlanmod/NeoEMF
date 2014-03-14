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
package mteach.impl;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;

import java.util.Collection;

import mteach.Course;
import mteach.MteachPackage;
import mteach.Professor;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Professor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link mteach.impl.ProfessorImpl#getLastName <em>Last Name</em>}</li>
 *   <li>{@link mteach.impl.ProfessorImpl#getFirstName <em>First Name</em>}</li>
 *   <li>{@link mteach.impl.ProfessorImpl#getTeachedCourses <em>Teached Courses</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProfessorImpl extends Neo4emfObject implements Professor {

	 
	
	/**
	 * The cached value of the data structure {@link DataProfessor <em>data</em> } 
	 * @generated
	 */
	 	protected DataProfessor data;
	 
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProfessorImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 
	protected DataProfessor getData(){
		if ( data == null || !(data instanceof DataProfessor)){
			data = new DataProfessor();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataProfessor) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MteachPackage.Literals.PROFESSOR;
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLastName() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().lastName;
		
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
	public void setLastName(String newLastName) {
	
		
		String oldLastName = getData().lastName;
		getData().lastName = newLastName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			MteachPackage.PROFESSOR__LAST_NAME,
			oldLastName, getData().lastName));
	    addChangelogEntry(newLastName, MteachPackage.PROFESSOR__LAST_NAME);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getFirstName() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().firstName == null){
		getData().firstName = new EDataTypeUniqueEList<String>(String.class, this, MteachPackage.PROFESSOR__FIRST_NAME);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, MteachPackage.PROFESSOR__FIRST_NAME);			}
		return getData().firstName;
	} finally {
	loadingOnDemand = false;
}
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Course> getTeachedCourses() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().teachedCourses == null){
		getData().teachedCourses = new EObjectWithInverseResolvingEList<Course>(Course.class, this, MteachPackage.PROFESSOR__TEACHED_COURSES, MteachPackage.COURSE__PROFESSOR);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, MteachPackage.PROFESSOR__TEACHED_COURSES);			}
		return getData().teachedCourses;
	} finally {
	loadingOnDemand = false;
}
	} 

/**
	 * <!-- begin-user-doc -->
	 *YY12
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MteachPackage.PROFESSOR__TEACHED_COURSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTeachedCourses()).basicAdd(otherEnd, msgs);
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
			case MteachPackage.PROFESSOR__TEACHED_COURSES:
				return ((InternalEList<?>)getTeachedCourses()).basicRemove(otherEnd, msgs);
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
			case MteachPackage.PROFESSOR__LAST_NAME:
				return getLastName();
			case MteachPackage.PROFESSOR__FIRST_NAME:
				return getFirstName();
			case MteachPackage.PROFESSOR__TEACHED_COURSES:
				return getTeachedCourses();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY16
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MteachPackage.PROFESSOR__LAST_NAME:
				setLastName((String)newValue);
				return;
			case MteachPackage.PROFESSOR__FIRST_NAME:
				getFirstName().clear();
				getFirstName().addAll((Collection<? extends String>)newValue);
				return;
			case MteachPackage.PROFESSOR__TEACHED_COURSES:
				getTeachedCourses().clear();
				getTeachedCourses().addAll((Collection<? extends Course>)newValue);
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
			case MteachPackage.PROFESSOR__LAST_NAME:
				setLastName(DataProfessor.LAST_NAME_EDEFAULT);
				return;
			case MteachPackage.PROFESSOR__FIRST_NAME:
				getFirstName().clear();
				return;
			case MteachPackage.PROFESSOR__TEACHED_COURSES:
				getTeachedCourses().clear();
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
			case MteachPackage.PROFESSOR__LAST_NAME:
				return DataProfessor.LAST_NAME_EDEFAULT == null ? getLastName() != null : !DataProfessor.LAST_NAME_EDEFAULT.equals(getLastName());
			case MteachPackage.PROFESSOR__FIRST_NAME:
				return getFirstName() != null && !getFirstName().isEmpty();
			case MteachPackage.PROFESSOR__TEACHED_COURSES:
				return getTeachedCourses() != null && !getTeachedCourses().isEmpty();
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
protected static  class DataProfessor {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getLastName() <em>Last Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastName()
	 * @generated
	 * @ordered
	 */
	protected static final String LAST_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLastName() <em>Last Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastName()
	 * @generated
	 * @ordered
	 */
	protected String lastName = LAST_NAME_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getFirstName() <em>First Name</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstName()
	 * @generated
	 * @ordered
	 */
	protected EList<String> firstName;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getTeachedCourses() <em>Teached Courses</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTeachedCourses()
	 * @generated
	 * @ordered
	 */
	protected EList<Course> teachedCourses;

	/**
	 *Constructor of DataProfessor
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataProfessor() {
		
	}
	
		
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (lastName: ");
		result.append(lastName);
		result.append(", firstName: ");
		result.append(firstName);
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
} //ProfessorImpl
