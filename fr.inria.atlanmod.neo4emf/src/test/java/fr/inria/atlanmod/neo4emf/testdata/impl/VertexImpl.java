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
package fr.inria.atlanmod.neo4emf.testdata.impl;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;

import fr.inria.atlanmod.neo4emf.testdata.Link;
import fr.inria.atlanmod.neo4emf.testdata.TestPackage;
import fr.inria.atlanmod.neo4emf.testdata.Vertex;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vertex</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getName <em>Name</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getContainer <em>Container</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getFrom <em>From</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VertexImpl extends Neo4emfObject implements Vertex {

	 
	
	/**
	 * The cached value of the data structure {@link DataVertex <em>data</em> } 
	 * @generated
	 */
	 	protected DataVertex data;
	 
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VertexImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 
	protected DataVertex getData(){
		if ( data == null || !(data instanceof DataVertex)){
			data = new DataVertex();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataVertex) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestPackage.Literals.VERTEX;
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		try {
			setLoadingOnDemand();	
	  		
		return getData().name;
		
	} finally {
	unsetLoadingOnDemand();
}
	}
 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setName(String newName) {
	
		
		String oldName = getData().name;
		getData().name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.VERTEX__NAME,
			oldName, getData().name));
	    addChangelogEntry(newName, TestPackage.VERTEX__NAME);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public fr.inria.atlanmod.neo4emf.testdata.Container getContainer() {
		try {
			setLoadingOnDemand();
	  
		if (isLoaded() && eContainer() == null) {
			fr.inria.atlanmod.neo4emf.testdata.Container container = (fr.inria.atlanmod.neo4emf.testdata.Container) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, TestPackage.VERTEX__CONTAINER);
			basicSetContainer(container,null);}
		return (fr.inria.atlanmod.neo4emf.testdata.Container)eContainer();
	} finally {
	unsetLoadingOnDemand();
}
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainer(fr.inria.atlanmod.neo4emf.testdata.Container newContainer, NotificationChain msgs) {
	
		msgs = eBasicSetContainer((InternalEObject)newContainer, TestPackage.VERTEX__CONTAINER, msgs);
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setContainer(fr.inria.atlanmod.neo4emf.testdata.Container newContainer) {
	
		
		if (newContainer != eInternalContainer() || (eContainerFeatureID() != TestPackage.VERTEX__CONTAINER && newContainer != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newContainer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContainer != null)
				msgs = ((InternalEObject)newContainer).eInverseAdd(this, TestPackage.CONTAINER__NODES, fr.inria.atlanmod.neo4emf.testdata.Container.class, msgs);
			msgs = basicSetContainer(newContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestPackage.VERTEX__CONTAINER, newContainer, newContainer));
	    addChangelogEntry(newContainer, TestPackage.VERTEX__CONTAINER);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Link> getFrom() {
		try {
			setLoadingOnDemand();	
	   
		
		if (getData().from == null){
		getData().from = new EObjectWithInverseResolvingEList<Link>(Link.class, this, TestPackage.VERTEX__FROM, TestPackage.LINK__OUT_GOING);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.VERTEX__FROM);			}
		return getData().from;
	} finally {
	unsetLoadingOnDemand();
}
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Link> getTo() {
		try {
			setLoadingOnDemand();
	   
		
		if (getData().to == null){
		getData().to = new EObjectWithInverseResolvingEList<Link>(Link.class, this, TestPackage.VERTEX__TO, TestPackage.LINK__IN_COMING);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.VERTEX__TO);			}
		return getData().to;
	} finally {
	unsetLoadingOnDemand();
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
			case TestPackage.VERTEX__CONTAINER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetContainer((fr.inria.atlanmod.neo4emf.testdata.Container)otherEnd, msgs);
			case TestPackage.VERTEX__FROM:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFrom()).basicAdd(otherEnd, msgs);
			case TestPackage.VERTEX__TO:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTo()).basicAdd(otherEnd, msgs);
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
			case TestPackage.VERTEX__CONTAINER:
				return basicSetContainer(null, msgs);
			case TestPackage.VERTEX__FROM:
				return ((InternalEList<?>)getFrom()).basicRemove(otherEnd, msgs);
			case TestPackage.VERTEX__TO:
				return ((InternalEList<?>)getTo()).basicRemove(otherEnd, msgs);
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
			case TestPackage.VERTEX__CONTAINER:
				return eInternalContainer().eInverseRemove(this, TestPackage.CONTAINER__NODES, fr.inria.atlanmod.neo4emf.testdata.Container.class, msgs);
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
			case TestPackage.VERTEX__NAME:
				return getName();
			case TestPackage.VERTEX__CONTAINER:
				return getContainer();
			case TestPackage.VERTEX__FROM:
				return getFrom();
			case TestPackage.VERTEX__TO:
				return getTo();
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
			case TestPackage.VERTEX__NAME:
				setName((String)newValue);
				return;
			case TestPackage.VERTEX__CONTAINER:
				setContainer((fr.inria.atlanmod.neo4emf.testdata.Container)newValue);
				return;
			case TestPackage.VERTEX__FROM:
				getFrom().clear();
				getFrom().addAll((Collection<? extends Link>)newValue);
				return;
			case TestPackage.VERTEX__TO:
				getTo().clear();
				getTo().addAll((Collection<? extends Link>)newValue);
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
			case TestPackage.VERTEX__NAME:
				setName(DataVertex.NAME_EDEFAULT);
				return;
			case TestPackage.VERTEX__CONTAINER:
				setContainer((fr.inria.atlanmod.neo4emf.testdata.Container)null);
				return;
			case TestPackage.VERTEX__FROM:
				getFrom().clear();
				return;
			case TestPackage.VERTEX__TO:
				getTo().clear();
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
			case TestPackage.VERTEX__NAME:
				return DataVertex.NAME_EDEFAULT == null ? getName() != null : !DataVertex.NAME_EDEFAULT.equals(getName());
			case TestPackage.VERTEX__CONTAINER:
				return getContainer() != null;
			case TestPackage.VERTEX__FROM:
				return getFrom() != null && !getFrom().isEmpty();
			case TestPackage.VERTEX__TO:
				return getTo() != null && !getTo().isEmpty();
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




// data Class generation 
protected static  class DataVertex {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getFrom() <em>From</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected EList<Link> from;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getTo() <em>To</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected EList<Link> to;

	/**
	 *Constructor of DataVertex
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVertex() {
		
	}
	
		
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}
		
}//end data class
} //VertexImpl
