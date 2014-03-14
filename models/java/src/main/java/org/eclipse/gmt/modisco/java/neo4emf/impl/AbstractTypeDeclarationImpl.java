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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.Comment;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Type Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.AbstractTypeDeclarationImpl#getUsagesInTypeAccess <em>Usages In Type Access</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.AbstractTypeDeclarationImpl#getBodyDeclarations <em>Body Declarations</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.AbstractTypeDeclarationImpl#getCommentsBeforeBody <em>Comments Before Body</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.AbstractTypeDeclarationImpl#getCommentsAfterBody <em>Comments After Body</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.AbstractTypeDeclarationImpl#getPackage <em>Package</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.AbstractTypeDeclarationImpl#getSuperInterfaces <em>Super Interfaces</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractTypeDeclarationImpl extends BodyDeclarationImpl implements AbstractTypeDeclaration {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractTypeDeclarationImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataAbstractTypeDeclaration getData(){
		if ( data == null || !(data instanceof DataAbstractTypeDeclaration)){
			data = new DataAbstractTypeDeclaration();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataAbstractTypeDeclaration) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getAbstractTypeDeclaration();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeAccess> getUsagesInTypeAccess() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().usagesInTypeAccess == null){
		getData().usagesInTypeAccess = new EObjectWithInverseResolvingEList<TypeAccess>(TypeAccess.class, this, JavaPackage.ABSTRACT_TYPE_DECLARATION__USAGES_IN_TYPE_ACCESS, JavaPackage.TYPE_ACCESS__TYPE);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ABSTRACT_TYPE_DECLARATION__USAGES_IN_TYPE_ACCESS);			}
		return getData().usagesInTypeAccess;
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
	public EList<BodyDeclaration> getBodyDeclarations() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().bodyDeclarations == null){
		getData().bodyDeclarations = new EObjectContainmentWithInverseEList<BodyDeclaration>(BodyDeclaration.class, this, JavaPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS, JavaPackage.BODY_DECLARATION__ABSTRACT_TYPE_DECLARATION);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS);			}
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
	public EList<Comment> getCommentsBeforeBody() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().commentsBeforeBody == null){
		getData().commentsBeforeBody = new EObjectContainmentEList<Comment>(Comment.class, this, JavaPackage.ABSTRACT_TYPE_DECLARATION__COMMENTS_BEFORE_BODY);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ABSTRACT_TYPE_DECLARATION__COMMENTS_BEFORE_BODY);			}
		return getData().commentsBeforeBody;
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
	public EList<Comment> getCommentsAfterBody() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().commentsAfterBody == null){
		getData().commentsAfterBody = new EObjectContainmentEList<Comment>(Comment.class, this, JavaPackage.ABSTRACT_TYPE_DECLARATION__COMMENTS_AFTER_BODY);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ABSTRACT_TYPE_DECLARATION__COMMENTS_AFTER_BODY);			}
		return getData().commentsAfterBody;
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
	public org.eclipse.gmt.modisco.java.Package getPackage() {
		try {
			loadingOnDemand = true;	
	  
		if (isLoaded() && eContainer() == null) {
			org.eclipse.gmt.modisco.java.Package package_ = (org.eclipse.gmt.modisco.java.Package) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, JavaPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE);
			basicSetPackage(package_,null);}
		return (org.eclipse.gmt.modisco.java.Package)eContainer();
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
	public NotificationChain basicSetPackage(org.eclipse.gmt.modisco.java.Package newPackage, NotificationChain msgs) {
	
		msgs = eBasicSetContainer((InternalEObject)newPackage, JavaPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE, msgs);
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setPackage(org.eclipse.gmt.modisco.java.Package newPackage) {
	
		
	
		if (newPackage != eInternalContainer() || (eContainerFeatureID() != JavaPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE && newPackage != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newPackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPackage != null)
				msgs = ((InternalEObject)newPackage).eInverseAdd(this, JavaPackage.PACKAGE__OWNED_ELEMENTS, org.eclipse.gmt.modisco.java.Package.class, msgs);
			msgs = basicSetPackage(newPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE, newPackage, newPackage));
	    addChangelogEntry(newPackage, JavaPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeAccess> getSuperInterfaces() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().superInterfaces == null){
		getData().superInterfaces = new EObjectContainmentEList<TypeAccess>(TypeAccess.class, this, JavaPackage.ABSTRACT_TYPE_DECLARATION__SUPER_INTERFACES);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ABSTRACT_TYPE_DECLARATION__SUPER_INTERFACES);			}
		return getData().superInterfaces;
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
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__USAGES_IN_TYPE_ACCESS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getUsagesInTypeAccess()).basicAdd(otherEnd, msgs);
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getBodyDeclarations()).basicAdd(otherEnd, msgs);
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPackage((org.eclipse.gmt.modisco.java.Package)otherEnd, msgs);
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
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__USAGES_IN_TYPE_ACCESS:
				return ((InternalEList<?>)getUsagesInTypeAccess()).basicRemove(otherEnd, msgs);
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
				return ((InternalEList<?>)getBodyDeclarations()).basicRemove(otherEnd, msgs);
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__COMMENTS_BEFORE_BODY:
				return ((InternalEList<?>)getCommentsBeforeBody()).basicRemove(otherEnd, msgs);
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__COMMENTS_AFTER_BODY:
				return ((InternalEList<?>)getCommentsAfterBody()).basicRemove(otherEnd, msgs);
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE:
				return basicSetPackage(null, msgs);
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__SUPER_INTERFACES:
				return ((InternalEList<?>)getSuperInterfaces()).basicRemove(otherEnd, msgs);
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
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE:
				return eInternalContainer().eInverseRemove(this, JavaPackage.PACKAGE__OWNED_ELEMENTS, org.eclipse.gmt.modisco.java.Package.class, msgs);
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
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__USAGES_IN_TYPE_ACCESS:
				return getUsagesInTypeAccess();
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
				return getBodyDeclarations();
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__COMMENTS_BEFORE_BODY:
				return getCommentsBeforeBody();
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__COMMENTS_AFTER_BODY:
				return getCommentsAfterBody();
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE:
				return getPackage();
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__SUPER_INTERFACES:
				return getSuperInterfaces();
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
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__USAGES_IN_TYPE_ACCESS:
				getUsagesInTypeAccess().clear();
				getUsagesInTypeAccess().addAll((Collection<? extends TypeAccess>)newValue);
				return;
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
				getBodyDeclarations().clear();
				getBodyDeclarations().addAll((Collection<? extends BodyDeclaration>)newValue);
				return;
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__COMMENTS_BEFORE_BODY:
				getCommentsBeforeBody().clear();
				getCommentsBeforeBody().addAll((Collection<? extends Comment>)newValue);
				return;
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__COMMENTS_AFTER_BODY:
				getCommentsAfterBody().clear();
				getCommentsAfterBody().addAll((Collection<? extends Comment>)newValue);
				return;
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE:
				setPackage((org.eclipse.gmt.modisco.java.Package)newValue);
				return;
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__SUPER_INTERFACES:
				getSuperInterfaces().clear();
				getSuperInterfaces().addAll((Collection<? extends TypeAccess>)newValue);
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
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__USAGES_IN_TYPE_ACCESS:
				getUsagesInTypeAccess().clear();
				return;
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
				getBodyDeclarations().clear();
				return;
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__COMMENTS_BEFORE_BODY:
				getCommentsBeforeBody().clear();
				return;
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__COMMENTS_AFTER_BODY:
				getCommentsAfterBody().clear();
				return;
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE:
				setPackage((org.eclipse.gmt.modisco.java.Package)null);
				return;
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__SUPER_INTERFACES:
				getSuperInterfaces().clear();
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
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__USAGES_IN_TYPE_ACCESS:
				return getUsagesInTypeAccess() != null && !getUsagesInTypeAccess().isEmpty();
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
				return getBodyDeclarations() != null && !getBodyDeclarations().isEmpty();
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__COMMENTS_BEFORE_BODY:
				return getCommentsBeforeBody() != null && !getCommentsBeforeBody().isEmpty();
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__COMMENTS_AFTER_BODY:
				return getCommentsAfterBody() != null && !getCommentsAfterBody().isEmpty();
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE:
				return getPackage() != null;
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__SUPER_INTERFACES:
				return getSuperInterfaces() != null && !getSuperInterfaces().isEmpty();
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
		if (baseClass == Type.class) {
			switch (derivedFeatureID) {
				case JavaPackage.ABSTRACT_TYPE_DECLARATION__USAGES_IN_TYPE_ACCESS: return JavaPackage.TYPE__USAGES_IN_TYPE_ACCESS;
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
		if (baseClass == Type.class) {
			switch (baseFeatureID) {
				case JavaPackage.TYPE__USAGES_IN_TYPE_ACCESS: return JavaPackage.ABSTRACT_TYPE_DECLARATION__USAGES_IN_TYPE_ACCESS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




// data Class generation 
protected static  class DataAbstractTypeDeclaration extends DataBodyDeclaration {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getUsagesInTypeAccess() <em>Usages In Type Access</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsagesInTypeAccess()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeAccess> usagesInTypeAccess;

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
	 * The cached value of the '{@link #getCommentsBeforeBody() <em>Comments Before Body</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommentsBeforeBody()
	 * @generated
	 * @ordered
	 */
	protected EList<Comment> commentsBeforeBody;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getCommentsAfterBody() <em>Comments After Body</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommentsAfterBody()
	 * @generated
	 * @ordered
	 */
	protected EList<Comment> commentsAfterBody;

// The goal of this template is to BLAH, BLAH, BLAH

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getSuperInterfaces() <em>Super Interfaces</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeAccess> superInterfaces;

	/**
	 *Constructor of DataAbstractTypeDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataAbstractTypeDeclaration() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataAbstractTypeDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link BodyDeclaration }
	 * @generated
	 */
	//public DataAbstractTypeDeclaration(DataBodyDeclaration data)
	//{
	//	this();		
	//	
	//	abstractTypeDeclaration = data.abstractTypeDeclaration;
	//	annotations = data.annotations;
	//	anonymousClassDeclarationOwner = data.anonymousClassDeclarationOwner;
	//	modifier = data.modifier;
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
} //AbstractTypeDeclarationImpl
