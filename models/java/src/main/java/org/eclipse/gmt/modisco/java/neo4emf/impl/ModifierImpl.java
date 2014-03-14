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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.InheritanceKind;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.VariableDeclarationExpression;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.eclipse.gmt.modisco.java.VisibilityKind;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Modifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModifierImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModifierImpl#getInheritance <em>Inheritance</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModifierImpl#isStatic <em>Static</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModifierImpl#isTransient <em>Transient</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModifierImpl#isVolatile <em>Volatile</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModifierImpl#isNative <em>Native</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModifierImpl#isStrictfp <em>Strictfp</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModifierImpl#isSynchronized <em>Synchronized</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModifierImpl#getBodyDeclaration <em>Body Declaration</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModifierImpl#getSingleVariableDeclaration <em>Single Variable Declaration</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModifierImpl#getVariableDeclarationStatement <em>Variable Declaration Statement</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModifierImpl#getVariableDeclarationExpression <em>Variable Declaration Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModifierImpl extends ASTNodeImpl implements Modifier {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModifierImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataModifier getData(){
		if ( data == null || !(data instanceof DataModifier)){
			data = new DataModifier();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataModifier) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getModifier();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VisibilityKind getVisibility() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().visibility;
		
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
	public void setVisibility(VisibilityKind newVisibility) {
	
		
	
		VisibilityKind oldVisibility = getData().visibility;
		getData().visibility = newVisibility == null ? getData().VISIBILITY_EDEFAULT : newVisibility;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.MODIFIER__VISIBILITY,
			oldVisibility, getData().visibility));
	    addChangelogEntry(newVisibility, JavaPackage.MODIFIER__VISIBILITY);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InheritanceKind getInheritance() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().inheritance;
		
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
	public void setInheritance(InheritanceKind newInheritance) {
	
		
	
		InheritanceKind oldInheritance = getData().inheritance;
		getData().inheritance = newInheritance == null ? getData().INHERITANCE_EDEFAULT : newInheritance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.MODIFIER__INHERITANCE,
			oldInheritance, getData().inheritance));
	    addChangelogEntry(newInheritance, JavaPackage.MODIFIER__INHERITANCE);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStatic() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().static_;
		
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
	public void setStatic(boolean newStatic) {
	
		
	
		boolean oldStatic = getData().static_;
		getData().static_ = newStatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.MODIFIER__STATIC,
			oldStatic, getData().static_));
	    addChangelogEntry(newStatic, JavaPackage.MODIFIER__STATIC);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTransient() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().transient_;
		
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
	public void setTransient(boolean newTransient) {
	
		
	
		boolean oldTransient = getData().transient_;
		getData().transient_ = newTransient;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.MODIFIER__TRANSIENT,
			oldTransient, getData().transient_));
	    addChangelogEntry(newTransient, JavaPackage.MODIFIER__TRANSIENT);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isVolatile() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().volatile_;
		
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
	public void setVolatile(boolean newVolatile) {
	
		
	
		boolean oldVolatile = getData().volatile_;
		getData().volatile_ = newVolatile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.MODIFIER__VOLATILE,
			oldVolatile, getData().volatile_));
	    addChangelogEntry(newVolatile, JavaPackage.MODIFIER__VOLATILE);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNative() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().native_;
		
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
	public void setNative(boolean newNative) {
	
		
	
		boolean oldNative = getData().native_;
		getData().native_ = newNative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.MODIFIER__NATIVE,
			oldNative, getData().native_));
	    addChangelogEntry(newNative, JavaPackage.MODIFIER__NATIVE);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStrictfp() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().strictfp_;
		
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
	public void setStrictfp(boolean newStrictfp) {
	
		
	
		boolean oldStrictfp = getData().strictfp_;
		getData().strictfp_ = newStrictfp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.MODIFIER__STRICTFP,
			oldStrictfp, getData().strictfp_));
	    addChangelogEntry(newStrictfp, JavaPackage.MODIFIER__STRICTFP);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSynchronized() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().synchronized_;
		
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
	public void setSynchronized(boolean newSynchronized) {
	
		
	
		boolean oldSynchronized = getData().synchronized_;
		getData().synchronized_ = newSynchronized;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.MODIFIER__SYNCHRONIZED,
			oldSynchronized, getData().synchronized_));
	    addChangelogEntry(newSynchronized, JavaPackage.MODIFIER__SYNCHRONIZED);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BodyDeclaration getBodyDeclaration() {
		try {
			loadingOnDemand = true;	
	  
		if (isLoaded() && eContainer() == null) {
			BodyDeclaration bodyDeclaration = (BodyDeclaration) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, JavaPackage.MODIFIER__BODY_DECLARATION);
			basicSetBodyDeclaration(bodyDeclaration,null);}
		return (BodyDeclaration)eContainer();
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
	public NotificationChain basicSetBodyDeclaration(BodyDeclaration newBodyDeclaration, NotificationChain msgs) {
	
		msgs = eBasicSetContainer((InternalEObject)newBodyDeclaration, JavaPackage.MODIFIER__BODY_DECLARATION, msgs);
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setBodyDeclaration(BodyDeclaration newBodyDeclaration) {
	
		
	
		if (newBodyDeclaration != eInternalContainer() || (eContainerFeatureID() != JavaPackage.MODIFIER__BODY_DECLARATION && newBodyDeclaration != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newBodyDeclaration))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBodyDeclaration != null)
				msgs = ((InternalEObject)newBodyDeclaration).eInverseAdd(this, JavaPackage.BODY_DECLARATION__MODIFIER, BodyDeclaration.class, msgs);
			msgs = basicSetBodyDeclaration(newBodyDeclaration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.MODIFIER__BODY_DECLARATION, newBodyDeclaration, newBodyDeclaration));
	    addChangelogEntry(newBodyDeclaration, JavaPackage.MODIFIER__BODY_DECLARATION);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleVariableDeclaration getSingleVariableDeclaration() {
		try {
			loadingOnDemand = true;	
	  
		if (isLoaded() && eContainer() == null) {
			SingleVariableDeclaration singleVariableDeclaration = (SingleVariableDeclaration) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, JavaPackage.MODIFIER__SINGLE_VARIABLE_DECLARATION);
			basicSetSingleVariableDeclaration(singleVariableDeclaration,null);}
		return (SingleVariableDeclaration)eContainer();
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
	public NotificationChain basicSetSingleVariableDeclaration(SingleVariableDeclaration newSingleVariableDeclaration, NotificationChain msgs) {
	
		msgs = eBasicSetContainer((InternalEObject)newSingleVariableDeclaration, JavaPackage.MODIFIER__SINGLE_VARIABLE_DECLARATION, msgs);
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setSingleVariableDeclaration(SingleVariableDeclaration newSingleVariableDeclaration) {
	
		
	
		if (newSingleVariableDeclaration != eInternalContainer() || (eContainerFeatureID() != JavaPackage.MODIFIER__SINGLE_VARIABLE_DECLARATION && newSingleVariableDeclaration != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newSingleVariableDeclaration))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSingleVariableDeclaration != null)
				msgs = ((InternalEObject)newSingleVariableDeclaration).eInverseAdd(this, JavaPackage.SINGLE_VARIABLE_DECLARATION__MODIFIER, SingleVariableDeclaration.class, msgs);
			msgs = basicSetSingleVariableDeclaration(newSingleVariableDeclaration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.MODIFIER__SINGLE_VARIABLE_DECLARATION, newSingleVariableDeclaration, newSingleVariableDeclaration));
	    addChangelogEntry(newSingleVariableDeclaration, JavaPackage.MODIFIER__SINGLE_VARIABLE_DECLARATION);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclarationStatement getVariableDeclarationStatement() {
		try {
			loadingOnDemand = true;	
	  
		if (isLoaded() && eContainer() == null) {
			VariableDeclarationStatement variableDeclarationStatement = (VariableDeclarationStatement) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, JavaPackage.MODIFIER__VARIABLE_DECLARATION_STATEMENT);
			basicSetVariableDeclarationStatement(variableDeclarationStatement,null);}
		return (VariableDeclarationStatement)eContainer();
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
	public NotificationChain basicSetVariableDeclarationStatement(VariableDeclarationStatement newVariableDeclarationStatement, NotificationChain msgs) {
	
		msgs = eBasicSetContainer((InternalEObject)newVariableDeclarationStatement, JavaPackage.MODIFIER__VARIABLE_DECLARATION_STATEMENT, msgs);
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setVariableDeclarationStatement(VariableDeclarationStatement newVariableDeclarationStatement) {
	
		
	
		if (newVariableDeclarationStatement != eInternalContainer() || (eContainerFeatureID() != JavaPackage.MODIFIER__VARIABLE_DECLARATION_STATEMENT && newVariableDeclarationStatement != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newVariableDeclarationStatement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newVariableDeclarationStatement != null)
				msgs = ((InternalEObject)newVariableDeclarationStatement).eInverseAdd(this, JavaPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIER, VariableDeclarationStatement.class, msgs);
			msgs = basicSetVariableDeclarationStatement(newVariableDeclarationStatement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.MODIFIER__VARIABLE_DECLARATION_STATEMENT, newVariableDeclarationStatement, newVariableDeclarationStatement));
	    addChangelogEntry(newVariableDeclarationStatement, JavaPackage.MODIFIER__VARIABLE_DECLARATION_STATEMENT);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclarationExpression getVariableDeclarationExpression() {
		try {
			loadingOnDemand = true;	
	  
		if (isLoaded() && eContainer() == null) {
			VariableDeclarationExpression variableDeclarationExpression = (VariableDeclarationExpression) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, JavaPackage.MODIFIER__VARIABLE_DECLARATION_EXPRESSION);
			basicSetVariableDeclarationExpression(variableDeclarationExpression,null);}
		return (VariableDeclarationExpression)eContainer();
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
	public NotificationChain basicSetVariableDeclarationExpression(VariableDeclarationExpression newVariableDeclarationExpression, NotificationChain msgs) {
	
		msgs = eBasicSetContainer((InternalEObject)newVariableDeclarationExpression, JavaPackage.MODIFIER__VARIABLE_DECLARATION_EXPRESSION, msgs);
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setVariableDeclarationExpression(VariableDeclarationExpression newVariableDeclarationExpression) {
	
		
	
		if (newVariableDeclarationExpression != eInternalContainer() || (eContainerFeatureID() != JavaPackage.MODIFIER__VARIABLE_DECLARATION_EXPRESSION && newVariableDeclarationExpression != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newVariableDeclarationExpression))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newVariableDeclarationExpression != null)
				msgs = ((InternalEObject)newVariableDeclarationExpression).eInverseAdd(this, JavaPackage.VARIABLE_DECLARATION_EXPRESSION__MODIFIER, VariableDeclarationExpression.class, msgs);
			msgs = basicSetVariableDeclarationExpression(newVariableDeclarationExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.MODIFIER__VARIABLE_DECLARATION_EXPRESSION, newVariableDeclarationExpression, newVariableDeclarationExpression));
	    addChangelogEntry(newVariableDeclarationExpression, JavaPackage.MODIFIER__VARIABLE_DECLARATION_EXPRESSION);
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
			case JavaPackage.MODIFIER__BODY_DECLARATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBodyDeclaration((BodyDeclaration)otherEnd, msgs);
			case JavaPackage.MODIFIER__SINGLE_VARIABLE_DECLARATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSingleVariableDeclaration((SingleVariableDeclaration)otherEnd, msgs);
			case JavaPackage.MODIFIER__VARIABLE_DECLARATION_STATEMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetVariableDeclarationStatement((VariableDeclarationStatement)otherEnd, msgs);
			case JavaPackage.MODIFIER__VARIABLE_DECLARATION_EXPRESSION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetVariableDeclarationExpression((VariableDeclarationExpression)otherEnd, msgs);
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
			case JavaPackage.MODIFIER__BODY_DECLARATION:
				return basicSetBodyDeclaration(null, msgs);
			case JavaPackage.MODIFIER__SINGLE_VARIABLE_DECLARATION:
				return basicSetSingleVariableDeclaration(null, msgs);
			case JavaPackage.MODIFIER__VARIABLE_DECLARATION_STATEMENT:
				return basicSetVariableDeclarationStatement(null, msgs);
			case JavaPackage.MODIFIER__VARIABLE_DECLARATION_EXPRESSION:
				return basicSetVariableDeclarationExpression(null, msgs);
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
			case JavaPackage.MODIFIER__BODY_DECLARATION:
				return eInternalContainer().eInverseRemove(this, JavaPackage.BODY_DECLARATION__MODIFIER, BodyDeclaration.class, msgs);
			case JavaPackage.MODIFIER__SINGLE_VARIABLE_DECLARATION:
				return eInternalContainer().eInverseRemove(this, JavaPackage.SINGLE_VARIABLE_DECLARATION__MODIFIER, SingleVariableDeclaration.class, msgs);
			case JavaPackage.MODIFIER__VARIABLE_DECLARATION_STATEMENT:
				return eInternalContainer().eInverseRemove(this, JavaPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIER, VariableDeclarationStatement.class, msgs);
			case JavaPackage.MODIFIER__VARIABLE_DECLARATION_EXPRESSION:
				return eInternalContainer().eInverseRemove(this, JavaPackage.VARIABLE_DECLARATION_EXPRESSION__MODIFIER, VariableDeclarationExpression.class, msgs);
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
			case JavaPackage.MODIFIER__VISIBILITY:
				return getVisibility();
			case JavaPackage.MODIFIER__INHERITANCE:
				return getInheritance();
			case JavaPackage.MODIFIER__STATIC:
				return isStatic();
			case JavaPackage.MODIFIER__TRANSIENT:
				return isTransient();
			case JavaPackage.MODIFIER__VOLATILE:
				return isVolatile();
			case JavaPackage.MODIFIER__NATIVE:
				return isNative();
			case JavaPackage.MODIFIER__STRICTFP:
				return isStrictfp();
			case JavaPackage.MODIFIER__SYNCHRONIZED:
				return isSynchronized();
			case JavaPackage.MODIFIER__BODY_DECLARATION:
				return getBodyDeclaration();
			case JavaPackage.MODIFIER__SINGLE_VARIABLE_DECLARATION:
				return getSingleVariableDeclaration();
			case JavaPackage.MODIFIER__VARIABLE_DECLARATION_STATEMENT:
				return getVariableDeclarationStatement();
			case JavaPackage.MODIFIER__VARIABLE_DECLARATION_EXPRESSION:
				return getVariableDeclarationExpression();
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
			case JavaPackage.MODIFIER__VISIBILITY:
				setVisibility((VisibilityKind)newValue);
				return;
			case JavaPackage.MODIFIER__INHERITANCE:
				setInheritance((InheritanceKind)newValue);
				return;
			case JavaPackage.MODIFIER__STATIC:
				setStatic((Boolean)newValue);
				return;
			case JavaPackage.MODIFIER__TRANSIENT:
				setTransient((Boolean)newValue);
				return;
			case JavaPackage.MODIFIER__VOLATILE:
				setVolatile((Boolean)newValue);
				return;
			case JavaPackage.MODIFIER__NATIVE:
				setNative((Boolean)newValue);
				return;
			case JavaPackage.MODIFIER__STRICTFP:
				setStrictfp((Boolean)newValue);
				return;
			case JavaPackage.MODIFIER__SYNCHRONIZED:
				setSynchronized((Boolean)newValue);
				return;
			case JavaPackage.MODIFIER__BODY_DECLARATION:
				setBodyDeclaration((BodyDeclaration)newValue);
				return;
			case JavaPackage.MODIFIER__SINGLE_VARIABLE_DECLARATION:
				setSingleVariableDeclaration((SingleVariableDeclaration)newValue);
				return;
			case JavaPackage.MODIFIER__VARIABLE_DECLARATION_STATEMENT:
				setVariableDeclarationStatement((VariableDeclarationStatement)newValue);
				return;
			case JavaPackage.MODIFIER__VARIABLE_DECLARATION_EXPRESSION:
				setVariableDeclarationExpression((VariableDeclarationExpression)newValue);
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
			case JavaPackage.MODIFIER__VISIBILITY:
				setVisibility(DataModifier.VISIBILITY_EDEFAULT);
				return;
			case JavaPackage.MODIFIER__INHERITANCE:
				setInheritance(DataModifier.INHERITANCE_EDEFAULT);
				return;
			case JavaPackage.MODIFIER__STATIC:
				setStatic(DataModifier.STATIC_EDEFAULT);
				return;
			case JavaPackage.MODIFIER__TRANSIENT:
				setTransient(DataModifier.TRANSIENT_EDEFAULT);
				return;
			case JavaPackage.MODIFIER__VOLATILE:
				setVolatile(DataModifier.VOLATILE_EDEFAULT);
				return;
			case JavaPackage.MODIFIER__NATIVE:
				setNative(DataModifier.NATIVE_EDEFAULT);
				return;
			case JavaPackage.MODIFIER__STRICTFP:
				setStrictfp(DataModifier.STRICTFP_EDEFAULT);
				return;
			case JavaPackage.MODIFIER__SYNCHRONIZED:
				setSynchronized(DataModifier.SYNCHRONIZED_EDEFAULT);
				return;
			case JavaPackage.MODIFIER__BODY_DECLARATION:
				setBodyDeclaration((BodyDeclaration)null);
				return;
			case JavaPackage.MODIFIER__SINGLE_VARIABLE_DECLARATION:
				setSingleVariableDeclaration((SingleVariableDeclaration)null);
				return;
			case JavaPackage.MODIFIER__VARIABLE_DECLARATION_STATEMENT:
				setVariableDeclarationStatement((VariableDeclarationStatement)null);
				return;
			case JavaPackage.MODIFIER__VARIABLE_DECLARATION_EXPRESSION:
				setVariableDeclarationExpression((VariableDeclarationExpression)null);
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
			case JavaPackage.MODIFIER__VISIBILITY:
				return getVisibility() != DataModifier.VISIBILITY_EDEFAULT;
			case JavaPackage.MODIFIER__INHERITANCE:
				return getInheritance() != DataModifier.INHERITANCE_EDEFAULT;
			case JavaPackage.MODIFIER__STATIC:
				return isStatic() != DataModifier.STATIC_EDEFAULT;
			case JavaPackage.MODIFIER__TRANSIENT:
				return isTransient() != DataModifier.TRANSIENT_EDEFAULT;
			case JavaPackage.MODIFIER__VOLATILE:
				return isVolatile() != DataModifier.VOLATILE_EDEFAULT;
			case JavaPackage.MODIFIER__NATIVE:
				return isNative() != DataModifier.NATIVE_EDEFAULT;
			case JavaPackage.MODIFIER__STRICTFP:
				return isStrictfp() != DataModifier.STRICTFP_EDEFAULT;
			case JavaPackage.MODIFIER__SYNCHRONIZED:
				return isSynchronized() != DataModifier.SYNCHRONIZED_EDEFAULT;
			case JavaPackage.MODIFIER__BODY_DECLARATION:
				return getBodyDeclaration() != null;
			case JavaPackage.MODIFIER__SINGLE_VARIABLE_DECLARATION:
				return getSingleVariableDeclaration() != null;
			case JavaPackage.MODIFIER__VARIABLE_DECLARATION_STATEMENT:
				return getVariableDeclarationStatement() != null;
			case JavaPackage.MODIFIER__VARIABLE_DECLARATION_EXPRESSION:
				return getVariableDeclarationExpression() != null;
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
protected static  class DataModifier extends DataASTNode {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected static final VisibilityKind VISIBILITY_EDEFAULT = VisibilityKind.NONE;

	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected VisibilityKind visibility = VISIBILITY_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getInheritance() <em>Inheritance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInheritance()
	 * @generated
	 * @ordered
	 */
	protected static final InheritanceKind INHERITANCE_EDEFAULT = InheritanceKind.NONE;

	/**
	 * The cached value of the '{@link #getInheritance() <em>Inheritance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInheritance()
	 * @generated
	 * @ordered
	 */
	protected InheritanceKind inheritance = INHERITANCE_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STATIC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected boolean static_ = STATIC_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #isTransient() <em>Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTransient()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TRANSIENT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTransient() <em>Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTransient()
	 * @generated
	 * @ordered
	 */
	protected boolean transient_ = TRANSIENT_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #isVolatile() <em>Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVolatile()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VOLATILE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isVolatile() <em>Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVolatile()
	 * @generated
	 * @ordered
	 */
	protected boolean volatile_ = VOLATILE_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #isNative() <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNative()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NATIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNative() <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNative()
	 * @generated
	 * @ordered
	 */
	protected boolean native_ = NATIVE_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #isStrictfp() <em>Strictfp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStrictfp()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STRICTFP_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStrictfp() <em>Strictfp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStrictfp()
	 * @generated
	 * @ordered
	 */
	protected boolean strictfp_ = STRICTFP_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #isSynchronized() <em>Synchronized</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSynchronized()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SYNCHRONIZED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSynchronized() <em>Synchronized</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSynchronized()
	 * @generated
	 * @ordered
	 */
	protected boolean synchronized_ = SYNCHRONIZED_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

// The goal of this template is to BLAH, BLAH, BLAH

// The goal of this template is to BLAH, BLAH, BLAH

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 *Constructor of DataModifier
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataModifier() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataModifier
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	//public DataModifier(DataASTNode data)
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
		result.append(" (visibility: ");
		result.append(visibility);
		result.append(", inheritance: ");
		result.append(inheritance);
		result.append(", static: ");
		result.append(static_);
		result.append(", transient: ");
		result.append(transient_);
		result.append(", volatile: ");
		result.append(volatile_);
		result.append(", native: ");
		result.append(native_);
		result.append(", strictfp: ");
		result.append(strictfp_);
		result.append(", synchronized: ");
		result.append(synchronized_);
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
} //ModifierImpl
