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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmt.modisco.java.AnonymousClassDeclaration;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Anonymous Class Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.AnonymousClassDeclarationImpl#getBodyDeclarations <em>Body Declarations</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.AnonymousClassDeclarationImpl#getClassInstanceCreation <em>Class Instance Creation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnonymousClassDeclarationImpl extends ASTNodeImpl implements AnonymousClassDeclaration {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnonymousClassDeclarationImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataAnonymousClassDeclaration getData(){
		if ( data == null || !(data instanceof DataAnonymousClassDeclaration)){
			data = new DataAnonymousClassDeclaration();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataAnonymousClassDeclaration) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getAnonymousClassDeclaration();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BodyDeclaration> getBodyDeclarations() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().bodyDeclarations == null){
		getData().bodyDeclarations = new EObjectContainmentWithInverseEList<BodyDeclaration>(BodyDeclaration.class, this, JavaPackage.ANONYMOUS_CLASS_DECLARATION__BODY_DECLARATIONS, JavaPackage.BODY_DECLARATION__ANONYMOUS_CLASS_DECLARATION_OWNER);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ANONYMOUS_CLASS_DECLARATION__BODY_DECLARATIONS);			}
		return getData().bodyDeclarations;
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
	public ClassInstanceCreation getClassInstanceCreation() {
		try {
			loadingOnDemand = true;	
	  
		if (isLoaded() && eContainer() == null) {
			ClassInstanceCreation classInstanceCreation = (ClassInstanceCreation) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, JavaPackage.ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION);
			basicSetClassInstanceCreation(classInstanceCreation,null);}
		return (ClassInstanceCreation)eContainer();
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
	public NotificationChain basicSetClassInstanceCreation(ClassInstanceCreation newClassInstanceCreation, NotificationChain msgs) {
	
		msgs = eBasicSetContainer((InternalEObject)newClassInstanceCreation, JavaPackage.ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION, msgs);
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setClassInstanceCreation(ClassInstanceCreation newClassInstanceCreation) {
	
		
	
		if (newClassInstanceCreation != eInternalContainer() || (eContainerFeatureID() != JavaPackage.ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION && newClassInstanceCreation != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newClassInstanceCreation))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newClassInstanceCreation != null)
				msgs = ((InternalEObject)newClassInstanceCreation).eInverseAdd(this, JavaPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION, ClassInstanceCreation.class, msgs);
			msgs = basicSetClassInstanceCreation(newClassInstanceCreation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION, newClassInstanceCreation, newClassInstanceCreation));
	    addChangelogEntry(newClassInstanceCreation, JavaPackage.ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION);
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
			case JavaPackage.ANONYMOUS_CLASS_DECLARATION__BODY_DECLARATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getBodyDeclarations()).basicAdd(otherEnd, msgs);
			case JavaPackage.ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetClassInstanceCreation((ClassInstanceCreation)otherEnd, msgs);
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
			case JavaPackage.ANONYMOUS_CLASS_DECLARATION__BODY_DECLARATIONS:
				return ((InternalEList<?>)getBodyDeclarations()).basicRemove(otherEnd, msgs);
			case JavaPackage.ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION:
				return basicSetClassInstanceCreation(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY14
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case JavaPackage.ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION:
				return eInternalContainer().eInverseRemove(this, JavaPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION, ClassInstanceCreation.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
			case JavaPackage.ANONYMOUS_CLASS_DECLARATION__BODY_DECLARATIONS:
				return getBodyDeclarations();
			case JavaPackage.ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION:
				return getClassInstanceCreation();
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
			case JavaPackage.ANONYMOUS_CLASS_DECLARATION__BODY_DECLARATIONS:
				getBodyDeclarations().clear();
				getBodyDeclarations().addAll((Collection<? extends BodyDeclaration>)newValue);
				return;
			case JavaPackage.ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION:
				setClassInstanceCreation((ClassInstanceCreation)newValue);
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
			case JavaPackage.ANONYMOUS_CLASS_DECLARATION__BODY_DECLARATIONS:
				getBodyDeclarations().clear();
				return;
			case JavaPackage.ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION:
				setClassInstanceCreation((ClassInstanceCreation)null);
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
			case JavaPackage.ANONYMOUS_CLASS_DECLARATION__BODY_DECLARATIONS:
				return getBodyDeclarations() != null && !getBodyDeclarations().isEmpty();
			case JavaPackage.ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION:
				return getClassInstanceCreation() != null;
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
protected static  class DataAnonymousClassDeclaration extends DataASTNode {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getBodyDeclarations() <em>Body Declarations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBodyDeclarations()
	 * @generated
	 * @ordered
	 */
	protected EList<BodyDeclaration> bodyDeclarations;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 *Constructor of DataAnonymousClassDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataAnonymousClassDeclaration() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataAnonymousClassDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	//public DataAnonymousClassDeclaration(DataASTNode data)
	//{
	//	this();		
	//	
	//	comments = data.comments;
	//	originalCompilationUnit = data.originalCompilationUnit;
	//	originalClassFile = data.originalClassFile;
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
} //AnonymousClassDeclarationImpl
