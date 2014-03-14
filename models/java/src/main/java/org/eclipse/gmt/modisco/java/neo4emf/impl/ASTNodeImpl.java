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

import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.ClassFile;
import org.eclipse.gmt.modisco.java.Comment;
import org.eclipse.gmt.modisco.java.CompilationUnit;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AST Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ASTNodeImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ASTNodeImpl#getOriginalCompilationUnit <em>Original Compilation Unit</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ASTNodeImpl#getOriginalClassFile <em>Original Class File</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ASTNodeImpl extends Neo4emfObject implements ASTNode {

	 
	
	/**
	 * The cached value of the data structure {@link DataASTNode <em>data</em> } 
	 * @generated
	 */
	 	protected DataASTNode data;
	 
	 
	 
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
	 
	protected DataASTNode getData(){
		if ( data == null || !(data instanceof DataASTNode)){
			data = new DataASTNode();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataASTNode) data;
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

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Comment> getComments() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().comments == null){
		getData().comments = new EObjectContainmentEList<Comment>(Comment.class, this, JavaPackage.AST_NODE__COMMENTS);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.AST_NODE__COMMENTS);			}
		return getData().comments;
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
	public CompilationUnit getOriginalCompilationUnit() {
		try {
			loadingOnDemand = true;	
	  
		if (getData().originalCompilationUnit == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.AST_NODE__ORIGINAL_COMPILATION_UNIT);
		}		
		return getData().originalCompilationUnit;
		
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
	public CompilationUnit basicGetOriginalCompilationUnit() {
		return data != null ? getData().originalCompilationUnit : null;
	}
 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setOriginalCompilationUnit(CompilationUnit newOriginalCompilationUnit) {
	
		
		CompilationUnit oldOriginalCompilationUnit = getData().originalCompilationUnit;
		getData().originalCompilationUnit = newOriginalCompilationUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.AST_NODE__ORIGINAL_COMPILATION_UNIT,
			oldOriginalCompilationUnit, getData().originalCompilationUnit));
	    addChangelogEntry(newOriginalCompilationUnit, JavaPackage.AST_NODE__ORIGINAL_COMPILATION_UNIT);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassFile getOriginalClassFile() {
		try {
			loadingOnDemand = true;	
	  
		if (getData().originalClassFile == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.AST_NODE__ORIGINAL_CLASS_FILE);
		}		
		return getData().originalClassFile;
		
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
	public ClassFile basicGetOriginalClassFile() {
		return data != null ? getData().originalClassFile : null;
	}
 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setOriginalClassFile(ClassFile newOriginalClassFile) {
	
		
		ClassFile oldOriginalClassFile = getData().originalClassFile;
		getData().originalClassFile = newOriginalClassFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.AST_NODE__ORIGINAL_CLASS_FILE,
			oldOriginalClassFile, getData().originalClassFile));
	    addChangelogEntry(newOriginalClassFile, JavaPackage.AST_NODE__ORIGINAL_CLASS_FILE);
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
			case JavaPackage.AST_NODE__COMMENTS:
				return ((InternalEList<?>)getComments()).basicRemove(otherEnd, msgs);
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
	 *YY16
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
	 *YY17-Bis
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
	 *YY18
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case JavaPackage.AST_NODE__COMMENTS:
				return getComments() != null && !getComments().isEmpty();
			case JavaPackage.AST_NODE__ORIGINAL_COMPILATION_UNIT:
				return getOriginalCompilationUnit() != null;
			case JavaPackage.AST_NODE__ORIGINAL_CLASS_FILE:
				return getOriginalClassFile() != null;
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
protected static  class DataASTNode {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getComments() <em>Comments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComments()
	 * @generated
	 * @ordered
	 */
	protected EList<Comment> comments;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getOriginalCompilationUnit() <em>Original Compilation Unit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalCompilationUnit()
	 * @generated
	 * @ordered
	 */
	protected CompilationUnit originalCompilationUnit;

// The goal of this template is to BLAH, BLAH, BLAH

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
	 *Constructor of DataASTNode
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataASTNode() {
		
	}
	
		
	
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
} //ASTNodeImpl
