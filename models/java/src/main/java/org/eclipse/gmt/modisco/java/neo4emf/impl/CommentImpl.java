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

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.Comment;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Comment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.CommentImpl#getContent <em>Content</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.CommentImpl#isEnclosedByParent <em>Enclosed By Parent</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.CommentImpl#isPrefixOfParent <em>Prefix Of Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class CommentImpl extends ASTNodeImpl implements Comment {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CommentImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataComment getData(){
		if ( data == null || !(data instanceof DataComment)){
			data = new DataComment();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataComment) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getComment();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContent() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().content;
		
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
	public void setContent(String newContent) {
	
		
	
		String oldContent = getData().content;
		getData().content = newContent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.COMMENT__CONTENT,
			oldContent, getData().content));
	    addChangelogEntry(newContent, JavaPackage.COMMENT__CONTENT);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnclosedByParent() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().enclosedByParent;
		
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
	public void setEnclosedByParent(boolean newEnclosedByParent) {
	
		
	
		boolean oldEnclosedByParent = getData().enclosedByParent;
		getData().enclosedByParent = newEnclosedByParent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.COMMENT__ENCLOSED_BY_PARENT,
			oldEnclosedByParent, getData().enclosedByParent));
	    addChangelogEntry(newEnclosedByParent, JavaPackage.COMMENT__ENCLOSED_BY_PARENT);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPrefixOfParent() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().prefixOfParent;
		
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
	public void setPrefixOfParent(boolean newPrefixOfParent) {
	
		
	
		boolean oldPrefixOfParent = getData().prefixOfParent;
		getData().prefixOfParent = newPrefixOfParent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.COMMENT__PREFIX_OF_PARENT,
			oldPrefixOfParent, getData().prefixOfParent));
	    addChangelogEntry(newPrefixOfParent, JavaPackage.COMMENT__PREFIX_OF_PARENT);
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
			case JavaPackage.COMMENT__CONTENT:
				return getContent();
			case JavaPackage.COMMENT__ENCLOSED_BY_PARENT:
				return isEnclosedByParent();
			case JavaPackage.COMMENT__PREFIX_OF_PARENT:
				return isPrefixOfParent();
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
			case JavaPackage.COMMENT__CONTENT:
				setContent((String)newValue);
				return;
			case JavaPackage.COMMENT__ENCLOSED_BY_PARENT:
				setEnclosedByParent((Boolean)newValue);
				return;
			case JavaPackage.COMMENT__PREFIX_OF_PARENT:
				setPrefixOfParent((Boolean)newValue);
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
			case JavaPackage.COMMENT__CONTENT:
				setContent(DataComment.CONTENT_EDEFAULT);
				return;
			case JavaPackage.COMMENT__ENCLOSED_BY_PARENT:
				setEnclosedByParent(DataComment.ENCLOSED_BY_PARENT_EDEFAULT);
				return;
			case JavaPackage.COMMENT__PREFIX_OF_PARENT:
				setPrefixOfParent(DataComment.PREFIX_OF_PARENT_EDEFAULT);
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
			case JavaPackage.COMMENT__CONTENT:
				return DataComment.CONTENT_EDEFAULT == null ? getContent() != null : !DataComment.CONTENT_EDEFAULT.equals(getContent());
			case JavaPackage.COMMENT__ENCLOSED_BY_PARENT:
				return isEnclosedByParent() != DataComment.ENCLOSED_BY_PARENT_EDEFAULT;
			case JavaPackage.COMMENT__PREFIX_OF_PARENT:
				return isPrefixOfParent() != DataComment.PREFIX_OF_PARENT_EDEFAULT;
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
protected static  class DataComment extends DataASTNode {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected String content = CONTENT_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #isEnclosedByParent() <em>Enclosed By Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnclosedByParent()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENCLOSED_BY_PARENT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEnclosedByParent() <em>Enclosed By Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnclosedByParent()
	 * @generated
	 * @ordered
	 */
	protected boolean enclosedByParent = ENCLOSED_BY_PARENT_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #isPrefixOfParent() <em>Prefix Of Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrefixOfParent()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PREFIX_OF_PARENT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPrefixOfParent() <em>Prefix Of Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrefixOfParent()
	 * @generated
	 * @ordered
	 */
	protected boolean prefixOfParent = PREFIX_OF_PARENT_EDEFAULT;

	/**
	 *Constructor of DataComment
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataComment() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataComment
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	//public DataComment(DataASTNode data)
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
		result.append(" (content: ");
		result.append(content);
		result.append(", enclosedByParent: ");
		result.append(enclosedByParent);
		result.append(", prefixOfParent: ");
		result.append(prefixOfParent);
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
} //CommentImpl
