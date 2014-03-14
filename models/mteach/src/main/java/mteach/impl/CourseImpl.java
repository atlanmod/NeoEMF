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
import mteach.Topic;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Course</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link mteach.impl.CourseImpl#getName <em>Name</em>}</li>
 *   <li>{@link mteach.impl.CourseImpl#getTime <em>Time</em>}</li>
 *   <li>{@link mteach.impl.CourseImpl#getTopics <em>Topics</em>}</li>
 *   <li>{@link mteach.impl.CourseImpl#getCoefficient <em>Coefficient</em>}</li>
 *   <li>{@link mteach.impl.CourseImpl#getProfessor <em>Professor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CourseImpl extends Neo4emfObject implements Course {

	 
	
	/**
	 * The cached value of the data structure {@link DataCourse <em>data</em> } 
	 * @generated
	 */
	 	protected DataCourse data;
	 
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CourseImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 
	protected DataCourse getData(){
		if ( data == null || !(data instanceof DataCourse)){
			data = new DataCourse();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataCourse) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MteachPackage.Literals.COURSE;
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().name;
		
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
	public void setName(String newName) {
	
		
		String oldName = getData().name;
		getData().name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			MteachPackage.COURSE__NAME,
			oldName, getData().name));
	    addChangelogEntry(newName, MteachPackage.COURSE__NAME);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTime() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().time;
		
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
	public void setTime(int newTime) {
	
		
		int oldTime = getData().time;
		getData().time = newTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			MteachPackage.COURSE__TIME,
			oldTime, getData().time));
	    addChangelogEntry(newTime, MteachPackage.COURSE__TIME);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Topic> getTopics() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().topics == null){
		getData().topics = new EObjectWithInverseResolvingEList<Topic>(Topic.class, this, MteachPackage.COURSE__TOPICS, MteachPackage.TOPIC__COURSE);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, MteachPackage.COURSE__TOPICS);			}
		return getData().topics;
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
	public float getCoefficient() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().coefficient;
		
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
	public Professor getProfessor() {
		try {
			loadingOnDemand = true;	
	  
		if (getData().professor == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, MteachPackage.COURSE__PROFESSOR);
		}		
		return getData().professor;
		
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
	public Professor basicGetProfessor() {
		return data != null ? getData().professor : null;
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProfessor(Professor newProfessor, NotificationChain msgs) {
	
		
	
		Professor oldProfessor = getData().professor;
		getData().professor = newProfessor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MteachPackage.COURSE__PROFESSOR, oldProfessor, newProfessor);
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
	public void setProfessor(Professor newProfessor) {
	
		
		if (newProfessor != getData().professor) {
			NotificationChain msgs = null;
			if (getData().professor != null)
				msgs = ((InternalEObject) getData().professor).eInverseRemove(this, MteachPackage.PROFESSOR__TEACHED_COURSES, Professor.class, msgs);
			if (newProfessor != null)
				msgs = ((InternalEObject)newProfessor).eInverseAdd(this, MteachPackage.PROFESSOR__TEACHED_COURSES, Professor.class, msgs);
			msgs = basicSetProfessor(newProfessor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MteachPackage.COURSE__PROFESSOR, newProfessor, newProfessor));
	    addChangelogEntry(newProfessor, MteachPackage.COURSE__PROFESSOR);
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
			case MteachPackage.COURSE__TOPICS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTopics()).basicAdd(otherEnd, msgs);
			case MteachPackage.COURSE__PROFESSOR:
				if (getData().professor != null)
					msgs = ((InternalEObject)getData().professor).eInverseRemove(this, MteachPackage.PROFESSOR__TEACHED_COURSES, Professor.class, msgs);
				return basicSetProfessor((Professor)otherEnd, msgs);
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
			case MteachPackage.COURSE__TOPICS:
				return ((InternalEList<?>)getTopics()).basicRemove(otherEnd, msgs);
			case MteachPackage.COURSE__PROFESSOR:
				return basicSetProfessor(null, msgs);
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
			case MteachPackage.COURSE__NAME:
				return getName();
			case MteachPackage.COURSE__TIME:
				return getTime();
			case MteachPackage.COURSE__TOPICS:
				return getTopics();
			case MteachPackage.COURSE__COEFFICIENT:
				return getCoefficient();
			case MteachPackage.COURSE__PROFESSOR:
				if (resolve) return getProfessor();
				return basicGetProfessor();
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
			case MteachPackage.COURSE__NAME:
				setName((String)newValue);
				return;
			case MteachPackage.COURSE__TIME:
				setTime((Integer)newValue);
				return;
			case MteachPackage.COURSE__TOPICS:
				getTopics().clear();
				getTopics().addAll((Collection<? extends Topic>)newValue);
				return;
			case MteachPackage.COURSE__PROFESSOR:
				setProfessor((Professor)newValue);
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
			case MteachPackage.COURSE__NAME:
				setName(DataCourse.NAME_EDEFAULT);
				return;
			case MteachPackage.COURSE__TIME:
				setTime(DataCourse.TIME_EDEFAULT);
				return;
			case MteachPackage.COURSE__TOPICS:
				getTopics().clear();
				return;
			case MteachPackage.COURSE__PROFESSOR:
				setProfessor((Professor)null);
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
			case MteachPackage.COURSE__NAME:
				return DataCourse.NAME_EDEFAULT == null ? getName() != null : !DataCourse.NAME_EDEFAULT.equals(getName());
			case MteachPackage.COURSE__TIME:
				return getTime() != DataCourse.TIME_EDEFAULT;
			case MteachPackage.COURSE__TOPICS:
				return getTopics() != null && !getTopics().isEmpty();
			case MteachPackage.COURSE__COEFFICIENT:
				return getCoefficient() != DataCourse.COEFFICIENT_EDEFAULT;
			case MteachPackage.COURSE__PROFESSOR:
				return getProfessor() != null;
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
protected static  class DataCourse {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected static final int TIME_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected int time = TIME_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getTopics() <em>Topics</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopics()
	 * @generated
	 * @ordered
	 */
	protected EList<Topic> topics;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getCoefficient() <em>Coefficient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoefficient()
	 * @generated
	 * @ordered
	 */
	protected static final float COEFFICIENT_EDEFAULT = 1.0F;

	/**
	 * The cached value of the '{@link #getCoefficient() <em>Coefficient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoefficient()
	 * @generated
	 * @ordered
	 */
	protected float coefficient = COEFFICIENT_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getProfessor() <em>Professor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfessor()
	 * @generated
	 * @ordered
	 */
	protected Professor professor;

	/**
	 *Constructor of DataCourse
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataCourse() {
		
	}
	
		
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (name: ");
		result.append(name);
		result.append(", time: ");
		result.append(time);
		result.append(", coefficient: ");
		result.append(coefficient);
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
} //CourseImpl
