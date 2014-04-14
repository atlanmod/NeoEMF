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

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.AnonymousClassDeclaration;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;

import org.eclipse.gmt.modisco.java.emf.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Body Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.BodyDeclarationImpl#getAbstractTypeDeclaration <em>Abstract Type Declaration</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.BodyDeclarationImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.BodyDeclarationImpl#getAnonymousClassDeclarationOwner <em>Anonymous Class Declaration Owner</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.BodyDeclarationImpl#getModifier <em>Modifier</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class BodyDeclarationImpl extends NamedElementImpl implements BodyDeclaration {
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
	 * The cached value of the '{@link #getModifier() <em>Modifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifier()
	 * @generated
	 * @ordered
	 */
	protected Modifier modifier;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BodyDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getBodyDeclaration();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractTypeDeclaration getAbstractTypeDeclaration() {
		if (eContainerFeatureID() != JavaPackage.BODY_DECLARATION__ABSTRACT_TYPE_DECLARATION) return null;
		return (AbstractTypeDeclaration)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAbstractTypeDeclaration(AbstractTypeDeclaration newAbstractTypeDeclaration, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAbstractTypeDeclaration, JavaPackage.BODY_DECLARATION__ABSTRACT_TYPE_DECLARATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstractTypeDeclaration(AbstractTypeDeclaration newAbstractTypeDeclaration) {
		if (newAbstractTypeDeclaration != eInternalContainer() || (eContainerFeatureID() != JavaPackage.BODY_DECLARATION__ABSTRACT_TYPE_DECLARATION && newAbstractTypeDeclaration != null)) {
			if (EcoreUtil.isAncestor(this, newAbstractTypeDeclaration))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAbstractTypeDeclaration != null)
				msgs = ((InternalEObject)newAbstractTypeDeclaration).eInverseAdd(this, JavaPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS, AbstractTypeDeclaration.class, msgs);
			msgs = basicSetAbstractTypeDeclaration(newAbstractTypeDeclaration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.BODY_DECLARATION__ABSTRACT_TYPE_DECLARATION, newAbstractTypeDeclaration, newAbstractTypeDeclaration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Annotation> getAnnotations() {
		if (annotations == null) {
			annotations = new EObjectContainmentEList<Annotation>(Annotation.class, this, JavaPackage.BODY_DECLARATION__ANNOTATIONS);
		}
		return annotations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnonymousClassDeclaration getAnonymousClassDeclarationOwner() {
		if (eContainerFeatureID() != JavaPackage.BODY_DECLARATION__ANONYMOUS_CLASS_DECLARATION_OWNER) return null;
		return (AnonymousClassDeclaration)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAnonymousClassDeclarationOwner(AnonymousClassDeclaration newAnonymousClassDeclarationOwner, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAnonymousClassDeclarationOwner, JavaPackage.BODY_DECLARATION__ANONYMOUS_CLASS_DECLARATION_OWNER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnonymousClassDeclarationOwner(AnonymousClassDeclaration newAnonymousClassDeclarationOwner) {
		if (newAnonymousClassDeclarationOwner != eInternalContainer() || (eContainerFeatureID() != JavaPackage.BODY_DECLARATION__ANONYMOUS_CLASS_DECLARATION_OWNER && newAnonymousClassDeclarationOwner != null)) {
			if (EcoreUtil.isAncestor(this, newAnonymousClassDeclarationOwner))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAnonymousClassDeclarationOwner != null)
				msgs = ((InternalEObject)newAnonymousClassDeclarationOwner).eInverseAdd(this, JavaPackage.ANONYMOUS_CLASS_DECLARATION__BODY_DECLARATIONS, AnonymousClassDeclaration.class, msgs);
			msgs = basicSetAnonymousClassDeclarationOwner(newAnonymousClassDeclarationOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.BODY_DECLARATION__ANONYMOUS_CLASS_DECLARATION_OWNER, newAnonymousClassDeclarationOwner, newAnonymousClassDeclarationOwner));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.BODY_DECLARATION__MODIFIER, oldModifier, newModifier);
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
				msgs = ((InternalEObject)modifier).eInverseRemove(this, JavaPackage.MODIFIER__BODY_DECLARATION, Modifier.class, msgs);
			if (newModifier != null)
				msgs = ((InternalEObject)newModifier).eInverseAdd(this, JavaPackage.MODIFIER__BODY_DECLARATION, Modifier.class, msgs);
			msgs = basicSetModifier(newModifier, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.BODY_DECLARATION__MODIFIER, newModifier, newModifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaPackage.BODY_DECLARATION__ABSTRACT_TYPE_DECLARATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAbstractTypeDeclaration((AbstractTypeDeclaration)otherEnd, msgs);
			case JavaPackage.BODY_DECLARATION__ANONYMOUS_CLASS_DECLARATION_OWNER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAnonymousClassDeclarationOwner((AnonymousClassDeclaration)otherEnd, msgs);
			case JavaPackage.BODY_DECLARATION__MODIFIER:
				if (modifier != null)
					msgs = ((InternalEObject)modifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.BODY_DECLARATION__MODIFIER, null, msgs);
				return basicSetModifier((Modifier)otherEnd, msgs);
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
			case JavaPackage.BODY_DECLARATION__ABSTRACT_TYPE_DECLARATION:
				return basicSetAbstractTypeDeclaration(null, msgs);
			case JavaPackage.BODY_DECLARATION__ANNOTATIONS:
				return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
			case JavaPackage.BODY_DECLARATION__ANONYMOUS_CLASS_DECLARATION_OWNER:
				return basicSetAnonymousClassDeclarationOwner(null, msgs);
			case JavaPackage.BODY_DECLARATION__MODIFIER:
				return basicSetModifier(null, msgs);
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
			case JavaPackage.BODY_DECLARATION__ABSTRACT_TYPE_DECLARATION:
				return eInternalContainer().eInverseRemove(this, JavaPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS, AbstractTypeDeclaration.class, msgs);
			case JavaPackage.BODY_DECLARATION__ANONYMOUS_CLASS_DECLARATION_OWNER:
				return eInternalContainer().eInverseRemove(this, JavaPackage.ANONYMOUS_CLASS_DECLARATION__BODY_DECLARATIONS, AnonymousClassDeclaration.class, msgs);
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
			case JavaPackage.BODY_DECLARATION__ABSTRACT_TYPE_DECLARATION:
				return getAbstractTypeDeclaration();
			case JavaPackage.BODY_DECLARATION__ANNOTATIONS:
				return getAnnotations();
			case JavaPackage.BODY_DECLARATION__ANONYMOUS_CLASS_DECLARATION_OWNER:
				return getAnonymousClassDeclarationOwner();
			case JavaPackage.BODY_DECLARATION__MODIFIER:
				return getModifier();
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
			case JavaPackage.BODY_DECLARATION__ABSTRACT_TYPE_DECLARATION:
				setAbstractTypeDeclaration((AbstractTypeDeclaration)newValue);
				return;
			case JavaPackage.BODY_DECLARATION__ANNOTATIONS:
				getAnnotations().clear();
				getAnnotations().addAll((Collection<? extends Annotation>)newValue);
				return;
			case JavaPackage.BODY_DECLARATION__ANONYMOUS_CLASS_DECLARATION_OWNER:
				setAnonymousClassDeclarationOwner((AnonymousClassDeclaration)newValue);
				return;
			case JavaPackage.BODY_DECLARATION__MODIFIER:
				setModifier((Modifier)newValue);
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
			case JavaPackage.BODY_DECLARATION__ABSTRACT_TYPE_DECLARATION:
				setAbstractTypeDeclaration((AbstractTypeDeclaration)null);
				return;
			case JavaPackage.BODY_DECLARATION__ANNOTATIONS:
				getAnnotations().clear();
				return;
			case JavaPackage.BODY_DECLARATION__ANONYMOUS_CLASS_DECLARATION_OWNER:
				setAnonymousClassDeclarationOwner((AnonymousClassDeclaration)null);
				return;
			case JavaPackage.BODY_DECLARATION__MODIFIER:
				setModifier((Modifier)null);
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
			case JavaPackage.BODY_DECLARATION__ABSTRACT_TYPE_DECLARATION:
				return getAbstractTypeDeclaration() != null;
			case JavaPackage.BODY_DECLARATION__ANNOTATIONS:
				return annotations != null && !annotations.isEmpty();
			case JavaPackage.BODY_DECLARATION__ANONYMOUS_CLASS_DECLARATION_OWNER:
				return getAnonymousClassDeclarationOwner() != null;
			case JavaPackage.BODY_DECLARATION__MODIFIER:
				return modifier != null;
		}
		return super.eIsSet(featureID);
	}

} //BodyDeclarationImpl
