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
 *     Fabien Giquel (Mia-Software) - initial API and implementation
 *     Gregoire DUPE (Mia-Software) - initial API and implementation
 * *******************************************************************************
 *
 * $Id$
 */
package org.eclipse.gmt.modisco.java.cdo.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractMethodInvocation;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.MethodRef;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.TypeParameter;

import org.eclipse.gmt.modisco.java.cdo.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Method Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.cdo.impl.AbstractMethodDeclarationImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.cdo.impl.AbstractMethodDeclarationImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.cdo.impl.AbstractMethodDeclarationImpl#getThrownExceptions <em>Thrown Exceptions</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.cdo.impl.AbstractMethodDeclarationImpl#getTypeParameters <em>Type Parameters</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.cdo.impl.AbstractMethodDeclarationImpl#getUsagesInDocComments <em>Usages In Doc Comments</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.cdo.impl.AbstractMethodDeclarationImpl#getUsages <em>Usages</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractMethodDeclarationImpl extends BodyDeclarationImpl implements AbstractMethodDeclaration {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractMethodDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getAbstractMethodDeclaration();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block getBody() {
		return (Block)eGet(JavaPackage.eINSTANCE.getAbstractMethodDeclaration_Body(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBody(Block newBody) {
		eSet(JavaPackage.eINSTANCE.getAbstractMethodDeclaration_Body(), newBody);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<SingleVariableDeclaration> getParameters() {
		return (EList<SingleVariableDeclaration>)eGet(JavaPackage.eINSTANCE.getAbstractMethodDeclaration_Parameters(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TypeAccess> getThrownExceptions() {
		return (EList<TypeAccess>)eGet(JavaPackage.eINSTANCE.getAbstractMethodDeclaration_ThrownExceptions(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TypeParameter> getTypeParameters() {
		return (EList<TypeParameter>)eGet(JavaPackage.eINSTANCE.getAbstractMethodDeclaration_TypeParameters(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<MethodRef> getUsagesInDocComments() {
		return (EList<MethodRef>)eGet(JavaPackage.eINSTANCE.getAbstractMethodDeclaration_UsagesInDocComments(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<AbstractMethodInvocation> getUsages() {
		return (EList<AbstractMethodInvocation>)eGet(JavaPackage.eINSTANCE.getAbstractMethodDeclaration_Usages(), true);
	}

} //AbstractMethodDeclarationImpl
