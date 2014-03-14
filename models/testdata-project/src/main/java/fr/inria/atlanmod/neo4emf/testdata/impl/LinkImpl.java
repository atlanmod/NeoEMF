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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.neo4j.graphdb.Node;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkImpl#getName <em>Name</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkImpl#getOutGoing <em>Out Going</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkImpl#getContainer <em>Container</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkImpl#getInComing <em>In Coming</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkImpl extends Neo4emfObject implements Link {

	 
	
	/**
	 * The cached value of the data structure {@link DataLink <em>data</em> } 
	 * @generated
	 */
	 	protected DataLink data;
	 
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LinkImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 
	protected DataLink getData(){
		if ( data == null || !(data instanceof DataLink)){
			data = new DataLink();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataLink) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestPackage.Literals.LINK;
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
			loadingOnDemand = true;	
	  		
		return getData().name;
		
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
	public void setName(String newName) {
	
		
		String oldName = getData().name;
		getData().name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.LINK__NAME,
			oldName, getData().name));
	    addChangelogEntry(newName, TestPackage.LINK__NAME);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vertex getOutGoing() {
		try {
			loadingOnDemand = true;	
	  
		if (getData().outGoing == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK__OUT_GOING);
		}		
		return getData().outGoing;
		
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
	public Vertex basicGetOutGoing() {
		return data != null ? getData().outGoing : null;
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutGoing(Vertex newOutGoing, NotificationChain msgs) {
	
		
	
		Vertex oldOutGoing = getData().outGoing;
		getData().outGoing = newOutGoing;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TestPackage.LINK__OUT_GOING, oldOutGoing, newOutGoing);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setOutGoing(Vertex newOutGoing) {
	
		
		if (newOutGoing != getData().outGoing) {
			NotificationChain msgs = null;
			if (getData().outGoing != null)
				msgs = ((InternalEObject) getData().outGoing).eInverseRemove(this, TestPackage.VERTEX__FROM, Vertex.class, msgs);
			if (newOutGoing != null)
				msgs = ((InternalEObject)newOutGoing).eInverseAdd(this, TestPackage.VERTEX__FROM, Vertex.class, msgs);
			msgs = basicSetOutGoing(newOutGoing, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestPackage.LINK__OUT_GOING, newOutGoing, newOutGoing));
	    addChangelogEntry(newOutGoing, TestPackage.LINK__OUT_GOING);
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
			loadingOnDemand = true;	
	  
		if (isLoaded() && eContainer() == null) {
			fr.inria.atlanmod.neo4emf.testdata.Container container = (fr.inria.atlanmod.neo4emf.testdata.Container) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, TestPackage.LINK__CONTAINER);
			basicSetContainer(container,null);}
		return (fr.inria.atlanmod.neo4emf.testdata.Container)eContainer();
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
	public NotificationChain basicSetContainer(fr.inria.atlanmod.neo4emf.testdata.Container newContainer, NotificationChain msgs) {
	
		msgs = eBasicSetContainer((InternalEObject)newContainer, TestPackage.LINK__CONTAINER, msgs);
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setContainer(fr.inria.atlanmod.neo4emf.testdata.Container newContainer) {
	
		
		if (newContainer != eInternalContainer() || (eContainerFeatureID() != TestPackage.LINK__CONTAINER && newContainer != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newContainer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContainer != null)
				msgs = ((InternalEObject)newContainer).eInverseAdd(this, TestPackage.CONTAINER__LINKS, fr.inria.atlanmod.neo4emf.testdata.Container.class, msgs);
			msgs = basicSetContainer(newContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestPackage.LINK__CONTAINER, newContainer, newContainer));
	    addChangelogEntry(newContainer, TestPackage.LINK__CONTAINER);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vertex getInComing() {
		try {
			loadingOnDemand = true;	
	  
		if (getData().inComing == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK__IN_COMING);
		}		
		return getData().inComing;
		
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
	public Vertex basicGetInComing() {
		return data != null ? getData().inComing : null;
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInComing(Vertex newInComing, NotificationChain msgs) {
	
		
	
		Vertex oldInComing = getData().inComing;
		getData().inComing = newInComing;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TestPackage.LINK__IN_COMING, oldInComing, newInComing);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setInComing(Vertex newInComing) {
	
		
		if (newInComing != getData().inComing) {
			NotificationChain msgs = null;
			if (getData().inComing != null)
				msgs = ((InternalEObject) getData().inComing).eInverseRemove(this, TestPackage.VERTEX__TO, Vertex.class, msgs);
			if (newInComing != null)
				msgs = ((InternalEObject)newInComing).eInverseAdd(this, TestPackage.VERTEX__TO, Vertex.class, msgs);
			msgs = basicSetInComing(newInComing, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestPackage.LINK__IN_COMING, newInComing, newInComing));
	    addChangelogEntry(newInComing, TestPackage.LINK__IN_COMING);
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
			case TestPackage.LINK__OUT_GOING:
				if (getData().outGoing != null)
					msgs = ((InternalEObject)getData().outGoing).eInverseRemove(this, TestPackage.VERTEX__FROM, Vertex.class, msgs);
				return basicSetOutGoing((Vertex)otherEnd, msgs);
			case TestPackage.LINK__CONTAINER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetContainer((fr.inria.atlanmod.neo4emf.testdata.Container)otherEnd, msgs);
			case TestPackage.LINK__IN_COMING:
				if (getData().inComing != null)
					msgs = ((InternalEObject)getData().inComing).eInverseRemove(this, TestPackage.VERTEX__TO, Vertex.class, msgs);
				return basicSetInComing((Vertex)otherEnd, msgs);
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
			case TestPackage.LINK__OUT_GOING:
				return basicSetOutGoing(null, msgs);
			case TestPackage.LINK__CONTAINER:
				return basicSetContainer(null, msgs);
			case TestPackage.LINK__IN_COMING:
				return basicSetInComing(null, msgs);
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
			case TestPackage.LINK__CONTAINER:
				return eInternalContainer().eInverseRemove(this, TestPackage.CONTAINER__LINKS, fr.inria.atlanmod.neo4emf.testdata.Container.class, msgs);
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
			case TestPackage.LINK__NAME:
				return getName();
			case TestPackage.LINK__OUT_GOING:
				if (resolve) return getOutGoing();
				return basicGetOutGoing();
			case TestPackage.LINK__CONTAINER:
				return getContainer();
			case TestPackage.LINK__IN_COMING:
				if (resolve) return getInComing();
				return basicGetInComing();
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
			case TestPackage.LINK__NAME:
				setName((String)newValue);
				return;
			case TestPackage.LINK__OUT_GOING:
				setOutGoing((Vertex)newValue);
				return;
			case TestPackage.LINK__CONTAINER:
				setContainer((fr.inria.atlanmod.neo4emf.testdata.Container)newValue);
				return;
			case TestPackage.LINK__IN_COMING:
				setInComing((Vertex)newValue);
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
			case TestPackage.LINK__NAME:
				setName(DataLink.NAME_EDEFAULT);
				return;
			case TestPackage.LINK__OUT_GOING:
				setOutGoing((Vertex)null);
				return;
			case TestPackage.LINK__CONTAINER:
				setContainer((fr.inria.atlanmod.neo4emf.testdata.Container)null);
				return;
			case TestPackage.LINK__IN_COMING:
				setInComing((Vertex)null);
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
			case TestPackage.LINK__NAME:
				return DataLink.NAME_EDEFAULT == null ? getName() != null : !DataLink.NAME_EDEFAULT.equals(getName());
			case TestPackage.LINK__OUT_GOING:
				return getOutGoing() != null;
			case TestPackage.LINK__CONTAINER:
				return getContainer() != null;
			case TestPackage.LINK__IN_COMING:
				return getInComing() != null;
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

// data Class generation 
protected static  class DataLink {


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

	/**
	 * The cached value of the '{@link #getOutGoing() <em>Out Going</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutGoing()
	 * @generated
	 * @ordered
	 */
	protected Vertex outGoing;

// The goal of this template is to BLAH, BLAH, BLAH

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getInComing() <em>In Coming</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInComing()
	 * @generated
	 * @ordered
	 */
	protected Vertex inComing;

	/**
	 *Constructor of DataLink
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataLink() {
		
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
		

	public void loadFrom(Node n) {
	// name:String isField: true isPrimitiveType: false isListType: false
		name = (String) n.getProperty("name");
	}
	
	public void saveTo(Node n) {
	}
}//end data class


/*
* Neo4EMF inserted code -- end
*/




} //LinkImpl
