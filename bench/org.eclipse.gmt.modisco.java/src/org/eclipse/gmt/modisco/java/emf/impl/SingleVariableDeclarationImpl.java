/**
 * *******************************************************************************
 * Copyright (c) 2009 Mia-Software.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 
 *     Sebastien Minguet (Mia-Software) - initial API and implementation
 *     Frederic Madiot (Mia-Software) - initial API and implementation
 *     Fabien Giquel (Mia-Software) - initial API and implementation
 *     Gabriel Barbier (Mia-Software) - initial API and implementation
 *     Erwan Breton (Sodifrance) - initial API and implementation
 *     Romain Dervaux (Mia-Software) - initial API and implementation
 * *******************************************************************************
 *
 * $Id$
 */
package org.eclipse.gmt.modisco.java.emf.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.CatchClause;
import org.eclipse.gmt.modisco.java.EnhancedForStatement;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.TypeAccess;

import org.eclipse.gmt.modisco.java.emf.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Single Variable Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.SingleVariableDeclarationImpl#getModifier <em>Modifier</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.SingleVariableDeclarationImpl#isVarargs <em>Varargs</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.SingleVariableDeclarationImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.SingleVariableDeclarationImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.SingleVariableDeclarationImpl#getMethodDeclaration <em>Method Declaration</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.SingleVariableDeclarationImpl#getCatchClause <em>Catch Clause</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.SingleVariableDeclarationImpl#getEnhancedForStatement <em>Enhanced For Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SingleVariableDeclarationImpl extends VariableDeclarationImpl implements SingleVariableDeclaration {
	/**
	 * The cached value of the '{@link #getModifier() <em>Modifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifier()
	 * @generated
	 * @ordered
	 */
	protected Modifier modifier;

	/**
	 * The default value of the '{@link #isVarargs() <em>Varargs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVarargs()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VARARGS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isVarargs() <em>Varargs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVarargs()
	 * @generated
	 * @ordered
	 */
	protected boolean varargs = VARARGS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected TypeAccess type;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SingleVariableDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getSingleVariableDeclaration();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Modifier getModifier() {
		return modifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModifier(Modifier newModifier, NotificationChain msgs) {
		Modifier oldModifier = modifier;
		modifier = newModifier;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.SINGLE_VARIABLE_DECLARATION__MODIFIER, oldModifier, newModifier);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModifier(Modifier newModifier) {
		if (newModifier != modifier) {
			NotificationChain msgs = null;
			if (modifier != null)
				msgs = ((InternalEObject)modifier).eInverseRemove(this, JavaPackage.MODIFIER__SINGLE_VARIABLE_DECLARATION, Modifier.class, msgs);
			if (newModifier != null)
				msgs = ((InternalEObject)newModifier).eInverseAdd(this, JavaPackage.MODIFIER__SINGLE_VARIABLE_DECLARATION, Modifier.class, msgs);
			msgs = basicSetModifier(newModifier, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.SINGLE_VARIABLE_DECLARATION__MODIFIER, newModifier, newModifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isVarargs() {
		return varargs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVarargs(boolean newVarargs) {
		boolean oldVarargs = varargs;
		varargs = newVarargs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.SINGLE_VARIABLE_DECLARATION__VARARGS, oldVarargs, varargs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(TypeAccess newType, NotificationChain msgs) {
		TypeAccess oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.SINGLE_VARIABLE_DECLARATION__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(TypeAccess newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.SINGLE_VARIABLE_DECLARATION__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.SINGLE_VARIABLE_DECLARATION__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.SINGLE_VARIABLE_DECLARATION__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Annotation> getAnnotations() {
		if (annotations == null) {
			annotations = new EObjectContainmentEList<Annotation>(Annotation.class, this, JavaPackage.SINGLE_VARIABLE_DECLARATION__ANNOTATIONS);
		}
		return annotations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractMethodDeclaration getMethodDeclaration() {
		if (eContainerFeatureID() != JavaPackage.SINGLE_VARIABLE_DECLARATION__METHOD_DECLARATION) return null;
		return (AbstractMethodDeclaration)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMethodDeclaration(AbstractMethodDeclaration newMethodDeclaration, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newMethodDeclaration, JavaPackage.SINGLE_VARIABLE_DECLARATION__METHOD_DECLARATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodDeclaration(AbstractMethodDeclaration newMethodDeclaration) {
		if (newMethodDeclaration != eInternalContainer() || (eContainerFeatureID() != JavaPackage.SINGLE_VARIABLE_DECLARATION__METHOD_DECLARATION && newMethodDeclaration != null)) {
			if (EcoreUtil.isAncestor(this, newMethodDeclaration))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newMethodDeclaration != null)
				msgs = ((InternalEObject)newMethodDeclaration).eInverseAdd(this, JavaPackage.ABSTRACT_METHOD_DECLARATION__PARAMETERS, AbstractMethodDeclaration.class, msgs);
			msgs = basicSetMethodDeclaration(newMethodDeclaration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.SINGLE_VARIABLE_DECLARATION__METHOD_DECLARATION, newMethodDeclaration, newMethodDeclaration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CatchClause getCatchClause() {
		if (eContainerFeatureID() != JavaPackage.SINGLE_VARIABLE_DECLARATION__CATCH_CLAUSE) return null;
		return (CatchClause)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCatchClause(CatchClause newCatchClause, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCatchClause, JavaPackage.SINGLE_VARIABLE_DECLARATION__CATCH_CLAUSE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCatchClause(CatchClause newCatchClause) {
		if (newCatchClause != eInternalContainer() || (eContainerFeatureID() != JavaPackage.SINGLE_VARIABLE_DECLARATION__CATCH_CLAUSE && newCatchClause != null)) {
			if (EcoreUtil.isAncestor(this, newCatchClause))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCatchClause != null)
				msgs = ((InternalEObject)newCatchClause).eInverseAdd(this, JavaPackage.CATCH_CLAUSE__EXCEPTION, CatchClause.class, msgs);
			msgs = basicSetCatchClause(newCatchClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.SINGLE_VARIABLE_DECLARATION__CATCH_CLAUSE, newCatchClause, newCatchClause));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnhancedForStatement getEnhancedForStatement() {
		if (eContainerFeatureID() != JavaPackage.SINGLE_VARIABLE_DECLARATION__ENHANCED_FOR_STATEMENT) return null;
		return (EnhancedForStatement)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEnhancedForStatement(EnhancedForStatement newEnhancedForStatement, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newEnhancedForStatement, JavaPackage.SINGLE_VARIABLE_DECLARATION__ENHANCED_FOR_STATEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnhancedForStatement(EnhancedForStatement newEnhancedForStatement) {
		if (newEnhancedForStatement != eInternalContainer() || (eContainerFeatureID() != JavaPackage.SINGLE_VARIABLE_DECLARATION__ENHANCED_FOR_STATEMENT && newEnhancedForStatement != null)) {
			if (EcoreUtil.isAncestor(this, newEnhancedForStatement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newEnhancedForStatement != null)
				msgs = ((InternalEObject)newEnhancedForStatement).eInverseAdd(this, JavaPackage.ENHANCED_FOR_STATEMENT__PARAMETER, EnhancedForStatement.class, msgs);
			msgs = basicSetEnhancedForStatement(newEnhancedForStatement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.SINGLE_VARIABLE_DECLARATION__ENHANCED_FOR_STATEMENT, newEnhancedForStatement, newEnhancedForStatement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__MODIFIER:
				if (modifier != null)
					msgs = ((InternalEObject)modifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.SINGLE_VARIABLE_DECLARATION__MODIFIER, null, msgs);
				return basicSetModifier((Modifier)otherEnd, msgs);
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__METHOD_DECLARATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetMethodDeclaration((AbstractMethodDeclaration)otherEnd, msgs);
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__CATCH_CLAUSE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCatchClause((CatchClause)otherEnd, msgs);
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__ENHANCED_FOR_STATEMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetEnhancedForStatement((EnhancedForStatement)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__MODIFIER:
				return basicSetModifier(null, msgs);
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__TYPE:
				return basicSetType(null, msgs);
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__ANNOTATIONS:
				return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__METHOD_DECLARATION:
				return basicSetMethodDeclaration(null, msgs);
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__CATCH_CLAUSE:
				return basicSetCatchClause(null, msgs);
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__ENHANCED_FOR_STATEMENT:
				return basicSetEnhancedForStatement(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__METHOD_DECLARATION:
				return eInternalContainer().eInverseRemove(this, JavaPackage.ABSTRACT_METHOD_DECLARATION__PARAMETERS, AbstractMethodDeclaration.class, msgs);
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__CATCH_CLAUSE:
				return eInternalContainer().eInverseRemove(this, JavaPackage.CATCH_CLAUSE__EXCEPTION, CatchClause.class, msgs);
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__ENHANCED_FOR_STATEMENT:
				return eInternalContainer().eInverseRemove(this, JavaPackage.ENHANCED_FOR_STATEMENT__PARAMETER, EnhancedForStatement.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__MODIFIER:
				return getModifier();
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__VARARGS:
				return isVarargs();
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__TYPE:
				return getType();
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__ANNOTATIONS:
				return getAnnotations();
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__METHOD_DECLARATION:
				return getMethodDeclaration();
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__CATCH_CLAUSE:
				return getCatchClause();
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__ENHANCED_FOR_STATEMENT:
				return getEnhancedForStatement();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__MODIFIER:
				setModifier((Modifier)newValue);
				return;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__VARARGS:
				setVarargs((Boolean)newValue);
				return;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__TYPE:
				setType((TypeAccess)newValue);
				return;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__ANNOTATIONS:
				getAnnotations().clear();
				getAnnotations().addAll((Collection<? extends Annotation>)newValue);
				return;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__METHOD_DECLARATION:
				setMethodDeclaration((AbstractMethodDeclaration)newValue);
				return;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__CATCH_CLAUSE:
				setCatchClause((CatchClause)newValue);
				return;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__ENHANCED_FOR_STATEMENT:
				setEnhancedForStatement((EnhancedForStatement)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__MODIFIER:
				setModifier((Modifier)null);
				return;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__VARARGS:
				setVarargs(VARARGS_EDEFAULT);
				return;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__TYPE:
				setType((TypeAccess)null);
				return;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__ANNOTATIONS:
				getAnnotations().clear();
				return;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__METHOD_DECLARATION:
				setMethodDeclaration((AbstractMethodDeclaration)null);
				return;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__CATCH_CLAUSE:
				setCatchClause((CatchClause)null);
				return;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__ENHANCED_FOR_STATEMENT:
				setEnhancedForStatement((EnhancedForStatement)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__MODIFIER:
				return modifier != null;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__VARARGS:
				return varargs != VARARGS_EDEFAULT;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__TYPE:
				return type != null;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__ANNOTATIONS:
				return annotations != null && !annotations.isEmpty();
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__METHOD_DECLARATION:
				return getMethodDeclaration() != null;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__CATCH_CLAUSE:
				return getCatchClause() != null;
			case JavaPackage.SINGLE_VARIABLE_DECLARATION__ENHANCED_FOR_STATEMENT:
				return getEnhancedForStatement() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (varargs: ");
		result.append(varargs);
		result.append(')');
		return result.toString();
	}

} //SingleVariableDeclarationImpl
