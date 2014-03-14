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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmt.modisco.java.AbstractVariablesContainer;
import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable Declaration Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.VariableDeclarationStatementImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.VariableDeclarationStatementImpl#getFragments <em>Fragments</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.VariableDeclarationStatementImpl#getExtraArrayDimensions <em>Extra Array Dimensions</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.VariableDeclarationStatementImpl#getModifier <em>Modifier</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.VariableDeclarationStatementImpl#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariableDeclarationStatementImpl extends StatementImpl implements VariableDeclarationStatement {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableDeclarationStatementImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataVariableDeclarationStatement getData(){
		if ( data == null || !(data instanceof DataVariableDeclarationStatement)){
			data = new DataVariableDeclarationStatement();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataVariableDeclarationStatement) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getVariableDeclarationStatement();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess getType() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.VARIABLE_DECLARATION_STATEMENT__TYPE);
		}
		return getData().type;
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
	public NotificationChain basicSetType(TypeAccess newType, NotificationChain msgs) {
	
		
	
		TypeAccess oldType = getData().type;
		getData().type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.VARIABLE_DECLARATION_STATEMENT__TYPE, oldType, newType);
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
	public void setType(TypeAccess newType) {
	
		
	
		if (newType != getData().type) {
			NotificationChain msgs = null;
			if (getData().type != null)
				msgs = ((InternalEObject) getData().type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.VARIABLE_DECLARATION_STATEMENT__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.VARIABLE_DECLARATION_STATEMENT__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.VARIABLE_DECLARATION_STATEMENT__TYPE, newType, newType));
	    addChangelogEntry(newType, JavaPackage.VARIABLE_DECLARATION_STATEMENT__TYPE);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariableDeclarationFragment> getFragments() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().fragments == null){
		getData().fragments = new EObjectContainmentWithInverseEList<VariableDeclarationFragment>(VariableDeclarationFragment.class, this, JavaPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS, JavaPackage.VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS);			}
		return getData().fragments;
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
	public int getExtraArrayDimensions() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().extraArrayDimensions;
		
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
	public void setExtraArrayDimensions(int newExtraArrayDimensions) {
	
		
	
		int oldExtraArrayDimensions = getData().extraArrayDimensions;
		getData().extraArrayDimensions = newExtraArrayDimensions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.VARIABLE_DECLARATION_STATEMENT__EXTRA_ARRAY_DIMENSIONS,
			oldExtraArrayDimensions, getData().extraArrayDimensions));
	    addChangelogEntry(newExtraArrayDimensions, JavaPackage.VARIABLE_DECLARATION_STATEMENT__EXTRA_ARRAY_DIMENSIONS);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Modifier getModifier() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIER);
		}
		return getData().modifier;
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
	public NotificationChain basicSetModifier(Modifier newModifier, NotificationChain msgs) {
	
		
	
		Modifier oldModifier = getData().modifier;
		getData().modifier = newModifier;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIER, oldModifier, newModifier);
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
	public void setModifier(Modifier newModifier) {
	
		
	
		if (newModifier != getData().modifier) {
			NotificationChain msgs = null;
			if (getData().modifier != null)
				msgs = ((InternalEObject) getData().modifier).eInverseRemove(this, JavaPackage.MODIFIER__VARIABLE_DECLARATION_STATEMENT, Modifier.class, msgs);
			if (newModifier != null)
				msgs = ((InternalEObject)newModifier).eInverseAdd(this, JavaPackage.MODIFIER__VARIABLE_DECLARATION_STATEMENT, Modifier.class, msgs);
			msgs = basicSetModifier(newModifier, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIER, newModifier, newModifier));
	    addChangelogEntry(newModifier, JavaPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIER);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Annotation> getAnnotations() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().annotations == null){
		getData().annotations = new EObjectContainmentEList<Annotation>(Annotation.class, this, JavaPackage.VARIABLE_DECLARATION_STATEMENT__ANNOTATIONS);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.VARIABLE_DECLARATION_STATEMENT__ANNOTATIONS);			}
		return getData().annotations;
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
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFragments()).basicAdd(otherEnd, msgs);
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIER:
				if (getData().modifier != null)
					msgs = ((InternalEObject)getData().modifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIER, null, msgs);
				return basicSetModifier((Modifier)otherEnd, msgs);
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
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__TYPE:
				return basicSetType(null, msgs);
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS:
				return ((InternalEList<?>)getFragments()).basicRemove(otherEnd, msgs);
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIER:
				return basicSetModifier(null, msgs);
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__ANNOTATIONS:
				return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
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
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__TYPE:
				return getType();
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS:
				return getFragments();
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__EXTRA_ARRAY_DIMENSIONS:
				return getExtraArrayDimensions();
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIER:
				return getModifier();
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__ANNOTATIONS:
				return getAnnotations();
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
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__TYPE:
				setType((TypeAccess)newValue);
				return;
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS:
				getFragments().clear();
				getFragments().addAll((Collection<? extends VariableDeclarationFragment>)newValue);
				return;
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__EXTRA_ARRAY_DIMENSIONS:
				setExtraArrayDimensions((Integer)newValue);
				return;
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIER:
				setModifier((Modifier)newValue);
				return;
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__ANNOTATIONS:
				getAnnotations().clear();
				getAnnotations().addAll((Collection<? extends Annotation>)newValue);
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
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__TYPE:
				setType((TypeAccess)null);
				return;
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS:
				getFragments().clear();
				return;
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__EXTRA_ARRAY_DIMENSIONS:
				setExtraArrayDimensions(DataVariableDeclarationStatement.EXTRA_ARRAY_DIMENSIONS_EDEFAULT);
				return;
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIER:
				setModifier((Modifier)null);
				return;
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__ANNOTATIONS:
				getAnnotations().clear();
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
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__TYPE:
				return getType() != null;
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS:
				return getFragments() != null && !getFragments().isEmpty();
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__EXTRA_ARRAY_DIMENSIONS:
				return getExtraArrayDimensions() != DataVariableDeclarationStatement.EXTRA_ARRAY_DIMENSIONS_EDEFAULT;
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIER:
				return getModifier() != null;
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT__ANNOTATIONS:
				return getAnnotations() != null && !getAnnotations().isEmpty();
		}
		return super.eIsSet(featureID);
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY19
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractVariablesContainer.class) {
			switch (derivedFeatureID) {
				case JavaPackage.VARIABLE_DECLARATION_STATEMENT__TYPE: return JavaPackage.ABSTRACT_VARIABLES_CONTAINER__TYPE;
				case JavaPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS: return JavaPackage.ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY20
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractVariablesContainer.class) {
			switch (baseFeatureID) {
				case JavaPackage.ABSTRACT_VARIABLES_CONTAINER__TYPE: return JavaPackage.VARIABLE_DECLARATION_STATEMENT__TYPE;
				case JavaPackage.ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS: return JavaPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
protected static  class DataVariableDeclarationStatement extends DataStatement {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected TypeAccess type;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getFragments() <em>Fragments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFragments()
	 * @generated
	 * @ordered
	 */
	protected EList<VariableDeclarationFragment> fragments;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getExtraArrayDimensions() <em>Extra Array Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtraArrayDimensions()
	 * @generated
	 * @ordered
	 */
	protected static final int EXTRA_ARRAY_DIMENSIONS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getExtraArrayDimensions() <em>Extra Array Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtraArrayDimensions()
	 * @generated
	 * @ordered
	 */
	protected int extraArrayDimensions = EXTRA_ARRAY_DIMENSIONS_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getModifier() <em>Modifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifier()
	 * @generated
	 * @ordered
	 */
	protected Modifier modifier;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnnotations()
	 * @generated
	 * @ordered
	 */
	protected EList<Annotation> annotations;

	/**
	 *Constructor of DataVariableDeclarationStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVariableDeclarationStatement() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataVariableDeclarationStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Statement }
	 * @generated
	 */
	//public DataVariableDeclarationStatement(DataStatement data)
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
		result.append(" (extraArrayDimensions: ");
		result.append(extraArrayDimensions);
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
} //VariableDeclarationStatementImpl
