/**
 * *******************************************************************************
 * Copyright (c) 2009 Mia-Software.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.PackageAccess;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.PackageImpl#getOwnedElements <em>Owned Elements</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.PackageImpl#getModel <em>Model</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.PackageImpl#getOwnedPackages <em>Owned Packages</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.PackageImpl#getPackage <em>Package</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.PackageImpl#getUsagesInPackageAccess <em>Usages In Package Access</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageImpl extends NamedElementImpl implements org.eclipse.gmt.modisco.java.Package {
	/**
	 * The cached value of the '{@link #getOwnedElements() <em>Owned Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractTypeDeclaration> ownedElements;

	/**
	 * The cached value of the '{@link #getOwnedPackages() <em>Owned Packages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPackages()
	 * @generated
	 * @ordered
	 */
	protected EList<org.eclipse.gmt.modisco.java.Package> ownedPackages;

	/**
	 * The cached value of the '{@link #getUsagesInPackageAccess() <em>Usages In Package Access</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsagesInPackageAccess()
	 * @generated
	 * @ordered
	 */
	protected EList<PackageAccess> usagesInPackageAccess;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractTypeDeclaration> getOwnedElements() {
		if (ownedElements == null) {
			ownedElements = new EObjectContainmentWithInverseEList<AbstractTypeDeclaration>(AbstractTypeDeclaration.class, this, JavaPackage.PACKAGE__OWNED_ELEMENTS, JavaPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE);
		}
		return ownedElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model getModel() {
		if (eContainerFeatureID() != JavaPackage.PACKAGE__MODEL) return null;
		return (Model)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModel(Model newModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModel, JavaPackage.PACKAGE__MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(Model newModel) {
		if (newModel != eInternalContainer() || (eContainerFeatureID() != JavaPackage.PACKAGE__MODEL && newModel != null)) {
			if (EcoreUtil.isAncestor(this, newModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModel != null)
				msgs = ((InternalEObject)newModel).eInverseAdd(this, JavaPackage.MODEL__OWNED_ELEMENTS, Model.class, msgs);
			msgs = basicSetModel(newModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.PACKAGE__MODEL, newModel, newModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<org.eclipse.gmt.modisco.java.Package> getOwnedPackages() {
		if (ownedPackages == null) {
			ownedPackages = new EObjectContainmentWithInverseEList<org.eclipse.gmt.modisco.java.Package>(org.eclipse.gmt.modisco.java.Package.class, this, JavaPackage.PACKAGE__OWNED_PACKAGES, JavaPackage.PACKAGE__PACKAGE);
		}
		return ownedPackages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.gmt.modisco.java.Package getPackage() {
		if (eContainerFeatureID() != JavaPackage.PACKAGE__PACKAGE) return null;
		return (org.eclipse.gmt.modisco.java.Package)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPackage(org.eclipse.gmt.modisco.java.Package newPackage, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPackage, JavaPackage.PACKAGE__PACKAGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackage(org.eclipse.gmt.modisco.java.Package newPackage) {
		if (newPackage != eInternalContainer() || (eContainerFeatureID() != JavaPackage.PACKAGE__PACKAGE && newPackage != null)) {
			if (EcoreUtil.isAncestor(this, newPackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPackage != null)
				msgs = ((InternalEObject)newPackage).eInverseAdd(this, JavaPackage.PACKAGE__OWNED_PACKAGES, org.eclipse.gmt.modisco.java.Package.class, msgs);
			msgs = basicSetPackage(newPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.PACKAGE__PACKAGE, newPackage, newPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PackageAccess> getUsagesInPackageAccess() {
		if (usagesInPackageAccess == null) {
			usagesInPackageAccess = new EObjectWithInverseResolvingEList<PackageAccess>(PackageAccess.class, this, JavaPackage.PACKAGE__USAGES_IN_PACKAGE_ACCESS, JavaPackage.PACKAGE_ACCESS__PACKAGE);
		}
		return usagesInPackageAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaPackage.PACKAGE__OWNED_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedElements()).basicAdd(otherEnd, msgs);
			case JavaPackage.PACKAGE__MODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModel((Model)otherEnd, msgs);
			case JavaPackage.PACKAGE__OWNED_PACKAGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedPackages()).basicAdd(otherEnd, msgs);
			case JavaPackage.PACKAGE__PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPackage((org.eclipse.gmt.modisco.java.Package)otherEnd, msgs);
			case JavaPackage.PACKAGE__USAGES_IN_PACKAGE_ACCESS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getUsagesInPackageAccess()).basicAdd(otherEnd, msgs);
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
			case JavaPackage.PACKAGE__OWNED_ELEMENTS:
				return ((InternalEList<?>)getOwnedElements()).basicRemove(otherEnd, msgs);
			case JavaPackage.PACKAGE__MODEL:
				return basicSetModel(null, msgs);
			case JavaPackage.PACKAGE__OWNED_PACKAGES:
				return ((InternalEList<?>)getOwnedPackages()).basicRemove(otherEnd, msgs);
			case JavaPackage.PACKAGE__PACKAGE:
				return basicSetPackage(null, msgs);
			case JavaPackage.PACKAGE__USAGES_IN_PACKAGE_ACCESS:
				return ((InternalEList<?>)getUsagesInPackageAccess()).basicRemove(otherEnd, msgs);
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
			case JavaPackage.PACKAGE__MODEL:
				return eInternalContainer().eInverseRemove(this, JavaPackage.MODEL__OWNED_ELEMENTS, Model.class, msgs);
			case JavaPackage.PACKAGE__PACKAGE:
				return eInternalContainer().eInverseRemove(this, JavaPackage.PACKAGE__OWNED_PACKAGES, org.eclipse.gmt.modisco.java.Package.class, msgs);
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
			case JavaPackage.PACKAGE__OWNED_ELEMENTS:
				return getOwnedElements();
			case JavaPackage.PACKAGE__MODEL:
				return getModel();
			case JavaPackage.PACKAGE__OWNED_PACKAGES:
				return getOwnedPackages();
			case JavaPackage.PACKAGE__PACKAGE:
				return getPackage();
			case JavaPackage.PACKAGE__USAGES_IN_PACKAGE_ACCESS:
				return getUsagesInPackageAccess();
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
			case JavaPackage.PACKAGE__OWNED_ELEMENTS:
				getOwnedElements().clear();
				getOwnedElements().addAll((Collection<? extends AbstractTypeDeclaration>)newValue);
				return;
			case JavaPackage.PACKAGE__MODEL:
				setModel((Model)newValue);
				return;
			case JavaPackage.PACKAGE__OWNED_PACKAGES:
				getOwnedPackages().clear();
				getOwnedPackages().addAll((Collection<? extends org.eclipse.gmt.modisco.java.Package>)newValue);
				return;
			case JavaPackage.PACKAGE__PACKAGE:
				setPackage((org.eclipse.gmt.modisco.java.Package)newValue);
				return;
			case JavaPackage.PACKAGE__USAGES_IN_PACKAGE_ACCESS:
				getUsagesInPackageAccess().clear();
				getUsagesInPackageAccess().addAll((Collection<? extends PackageAccess>)newValue);
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
			case JavaPackage.PACKAGE__OWNED_ELEMENTS:
				getOwnedElements().clear();
				return;
			case JavaPackage.PACKAGE__MODEL:
				setModel((Model)null);
				return;
			case JavaPackage.PACKAGE__OWNED_PACKAGES:
				getOwnedPackages().clear();
				return;
			case JavaPackage.PACKAGE__PACKAGE:
				setPackage((org.eclipse.gmt.modisco.java.Package)null);
				return;
			case JavaPackage.PACKAGE__USAGES_IN_PACKAGE_ACCESS:
				getUsagesInPackageAccess().clear();
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
			case JavaPackage.PACKAGE__OWNED_ELEMENTS:
				return ownedElements != null && !ownedElements.isEmpty();
			case JavaPackage.PACKAGE__MODEL:
				return getModel() != null;
			case JavaPackage.PACKAGE__OWNED_PACKAGES:
				return ownedPackages != null && !ownedPackages.isEmpty();
			case JavaPackage.PACKAGE__PACKAGE:
				return getPackage() != null;
			case JavaPackage.PACKAGE__USAGES_IN_PACKAGE_ACCESS:
				return usagesInPackageAccess != null && !usagesInPackageAccess.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PackageImpl
