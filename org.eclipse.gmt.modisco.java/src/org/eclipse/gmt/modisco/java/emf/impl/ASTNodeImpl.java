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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.ClassFile;
import org.eclipse.gmt.modisco.java.Comment;
import org.eclipse.gmt.modisco.java.CompilationUnit;

import org.eclipse.gmt.modisco.java.emf.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AST Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.ASTNodeImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.ASTNodeImpl#getOriginalCompilationUnit <em>Original Compilation Unit</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.emf.impl.ASTNodeImpl#getOriginalClassFile <em>Original Class File</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ASTNodeImpl extends MinimalEObjectImpl implements ASTNode {
	/**
	 * The cached value of the '{@link #getComments() <em>Comments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComments()
	 * @generated
	 * @ordered
	 */
	protected EList<Comment> comments;

	/**
	 * The cached value of the '{@link #getOriginalCompilationUnit() <em>Original Compilation Unit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalCompilationUnit()
	 * @generated
	 * @ordered
	 */
	protected CompilationUnit originalCompilationUnit;

	/**
	 * The cached value of the '{@link #getOriginalClassFile() <em>Original Class File</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalClassFile()
	 * @generated
	 * @ordered
	 */
	protected ClassFile originalClassFile;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ASTNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getASTNode();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Comment> getComments() {
		if (comments == null) {
			comments = new EObjectContainmentEList<Comment>(Comment.class, this, JavaPackage.AST_NODE__COMMENTS);
		}
		return comments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnit getOriginalCompilationUnit() {
		if (originalCompilationUnit != null && originalCompilationUnit.eIsProxy()) {
			InternalEObject oldOriginalCompilationUnit = (InternalEObject)originalCompilationUnit;
			originalCompilationUnit = (CompilationUnit)eResolveProxy(oldOriginalCompilationUnit);
			if (originalCompilationUnit != oldOriginalCompilationUnit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, JavaPackage.AST_NODE__ORIGINAL_COMPILATION_UNIT, oldOriginalCompilationUnit, originalCompilationUnit));
			}
		}
		return originalCompilationUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnit basicGetOriginalCompilationUnit() {
		return originalCompilationUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOriginalCompilationUnit(CompilationUnit newOriginalCompilationUnit) {
		CompilationUnit oldOriginalCompilationUnit = originalCompilationUnit;
		originalCompilationUnit = newOriginalCompilationUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.AST_NODE__ORIGINAL_COMPILATION_UNIT, oldOriginalCompilationUnit, originalCompilationUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassFile getOriginalClassFile() {
		if (originalClassFile != null && originalClassFile.eIsProxy()) {
			InternalEObject oldOriginalClassFile = (InternalEObject)originalClassFile;
			originalClassFile = (ClassFile)eResolveProxy(oldOriginalClassFile);
			if (originalClassFile != oldOriginalClassFile) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, JavaPackage.AST_NODE__ORIGINAL_CLASS_FILE, oldOriginalClassFile, originalClassFile));
			}
		}
		return originalClassFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassFile basicGetOriginalClassFile() {
		return originalClassFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOriginalClassFile(ClassFile newOriginalClassFile) {
		ClassFile oldOriginalClassFile = originalClassFile;
		originalClassFile = newOriginalClassFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.AST_NODE__ORIGINAL_CLASS_FILE, oldOriginalClassFile, originalClassFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaPackage.AST_NODE__COMMENTS:
				return ((InternalEList<?>)getComments()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JavaPackage.AST_NODE__COMMENTS:
				return getComments();
			case JavaPackage.AST_NODE__ORIGINAL_COMPILATION_UNIT:
				if (resolve) return getOriginalCompilationUnit();
				return basicGetOriginalCompilationUnit();
			case JavaPackage.AST_NODE__ORIGINAL_CLASS_FILE:
				if (resolve) return getOriginalClassFile();
				return basicGetOriginalClassFile();
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
			case JavaPackage.AST_NODE__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case JavaPackage.AST_NODE__ORIGINAL_COMPILATION_UNIT:
				setOriginalCompilationUnit((CompilationUnit)newValue);
				return;
			case JavaPackage.AST_NODE__ORIGINAL_CLASS_FILE:
				setOriginalClassFile((ClassFile)newValue);
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
			case JavaPackage.AST_NODE__COMMENTS:
				getComments().clear();
				return;
			case JavaPackage.AST_NODE__ORIGINAL_COMPILATION_UNIT:
				setOriginalCompilationUnit((CompilationUnit)null);
				return;
			case JavaPackage.AST_NODE__ORIGINAL_CLASS_FILE:
				setOriginalClassFile((ClassFile)null);
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
			case JavaPackage.AST_NODE__COMMENTS:
				return comments != null && !comments.isEmpty();
			case JavaPackage.AST_NODE__ORIGINAL_COMPILATION_UNIT:
				return originalCompilationUnit != null;
			case JavaPackage.AST_NODE__ORIGINAL_CLASS_FILE:
				return originalClassFile != null;
		}
		return super.eIsSet(featureID);
	}

} //ASTNodeImpl
