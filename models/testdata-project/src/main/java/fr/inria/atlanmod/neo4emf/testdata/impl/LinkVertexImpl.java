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

import fr.inria.atlanmod.neo4emf.testdata.Link;
import fr.inria.atlanmod.neo4emf.testdata.LinkVertex;
import fr.inria.atlanmod.neo4emf.testdata.Temperature;
import fr.inria.atlanmod.neo4emf.testdata.TestPackage;
import fr.inria.atlanmod.neo4emf.testdata.Vertex;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.neo4j.graphdb.Node;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link Vertex</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getVname <em>Vname</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getATransientInteger <em>ATransient Integer</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getAVolatileDate <em>AVolatile Date</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#isABoolean <em>ABoolean</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getAStringArray <em>AString Array</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getANonUniqueArray <em>ANon Unique Array</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getANonOrderedArray <em>ANon Ordered Array</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getATenStringArray <em>ATen String Array</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getTemperature <em>Temperature</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getVcontainer <em>Vcontainer</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getFrom <em>From</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getTo <em>To</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getFirstName <em>First Name</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getLink <em>Link</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getVertex <em>Vertex</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkVertexImpl extends LinkImpl implements LinkVertex {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LinkVertexImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataLinkVertex getData(){
		if ( data == null || !(data instanceof DataLinkVertex)){
			data = new DataLinkVertex();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataLinkVertex) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestPackage.Literals.LINK_VERTEX;
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVname() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().vname;
		
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
	public BigInteger getATransientInteger() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().aTransientInteger;
		
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
	public void setATransientInteger(BigInteger newATransientInteger) {
	
		
	
		BigInteger oldATransientInteger = getData().aTransientInteger;
		getData().aTransientInteger = newATransientInteger;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.LINK_VERTEX__ATRANSIENT_INTEGER,
			oldATransientInteger, getData().aTransientInteger));
	    addChangelogEntry(newATransientInteger, TestPackage.LINK_VERTEX__ATRANSIENT_INTEGER);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getAVolatileDate() {
		try {
			loadingOnDemand = true;
		// TODO: implement this method to return the 'AVolatile Date' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
	public void setAVolatileDate(Date newAVolatileDate) {
	
		
	
		// TODO: implement this method to set the 'AVolatile Date' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	    addChangelogEntry(newAVolatileDate, TestPackage.LINK_VERTEX__AVOLATILE_DATE);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isABoolean() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().aBoolean;
		
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
	public void setABoolean(boolean newABoolean) {
	
		
	
		boolean oldABoolean = getData().aBoolean;
		getData().aBoolean = newABoolean;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.LINK_VERTEX__ABOOLEAN,
			oldABoolean, getData().aBoolean));
	    addChangelogEntry(newABoolean, TestPackage.LINK_VERTEX__ABOOLEAN);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getAStringArray() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().aStringArray == null){
		getData().aStringArray = new EDataTypeUniqueEList<String>(String.class, this, TestPackage.LINK_VERTEX__ASTRING_ARRAY);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__ASTRING_ARRAY);			}
		return getData().aStringArray;
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
	public EList<BigDecimal> getANonUniqueArray() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().aNonUniqueArray == null){
		getData().aNonUniqueArray = new EDataTypeEList<BigDecimal>(BigDecimal.class, this, TestPackage.LINK_VERTEX__ANON_UNIQUE_ARRAY);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__ANON_UNIQUE_ARRAY);			}
		return getData().aNonUniqueArray;
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
	public EList<Boolean> getANonOrderedArray() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().aNonOrderedArray == null){
		getData().aNonOrderedArray = new EDataTypeUniqueEList<Boolean>(Boolean.class, this, TestPackage.LINK_VERTEX__ANON_ORDERED_ARRAY);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__ANON_ORDERED_ARRAY);			}
		return getData().aNonOrderedArray;
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
	public EList<String> getATenStringArray() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().aTenStringArray == null){
		getData().aTenStringArray = new EDataTypeUniqueEList<String>(String.class, this, TestPackage.LINK_VERTEX__ATEN_STRING_ARRAY);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__ATEN_STRING_ARRAY);			}
		return getData().aTenStringArray;
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
	public Temperature getTemperature() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().temperature;
		
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
	public void setTemperature(Temperature newTemperature) {
	
		
	
		Temperature oldTemperature = getData().temperature;
		getData().temperature = newTemperature == null ? getData().TEMPERATURE_EDEFAULT : newTemperature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.LINK_VERTEX__TEMPERATURE,
			oldTemperature, getData().temperature));
	    addChangelogEntry(newTemperature, TestPackage.LINK_VERTEX__TEMPERATURE);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public fr.inria.atlanmod.neo4emf.testdata.Container getVcontainer() {
		try {
			loadingOnDemand = true;	
	  
		if (isLoaded() && eContainer() == null) {
			fr.inria.atlanmod.neo4emf.testdata.Container vcontainer = (fr.inria.atlanmod.neo4emf.testdata.Container) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, TestPackage.LINK_VERTEX__VCONTAINER);
			basicSetVcontainer(vcontainer,null);}
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
	public NotificationChain basicSetVcontainer(fr.inria.atlanmod.neo4emf.testdata.Container newVcontainer, NotificationChain msgs) {
	
		msgs = eBasicSetContainer((InternalEObject)newVcontainer, TestPackage.LINK_VERTEX__VCONTAINER, msgs);
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setVcontainer(fr.inria.atlanmod.neo4emf.testdata.Container newVcontainer) {
	
		
	
		if (newVcontainer != eInternalContainer() || (eContainerFeatureID() != TestPackage.LINK_VERTEX__VCONTAINER && newVcontainer != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newVcontainer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newVcontainer != null)
				msgs = ((InternalEObject)newVcontainer).eInverseAdd(this, TestPackage.CONTAINER__NODES, fr.inria.atlanmod.neo4emf.testdata.Container.class, msgs);
			msgs = basicSetVcontainer(newVcontainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestPackage.LINK_VERTEX__VCONTAINER, newVcontainer, newVcontainer));
	    addChangelogEntry(newVcontainer, TestPackage.LINK_VERTEX__VCONTAINER);
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
			loadingOnDemand = true;	
	   
		
		if (getData().from == null){
		getData().from = new EObjectWithInverseResolvingEList<Link>(Link.class, this, TestPackage.LINK_VERTEX__FROM, TestPackage.LINK__OUT_GOING);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__FROM);			}
		return getData().from;
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
	public EList<Link> getTo() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().to == null){
		getData().to = new EObjectWithInverseResolvingEList<Link>(Link.class, this, TestPackage.LINK_VERTEX__TO, TestPackage.LINK__IN_COMING);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__TO);			}
		return getData().to;
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
	public String getFirstName() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().firstName;
		
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
	public void setFirstName(String newFirstName) {
	
		
	
		String oldFirstName = getData().firstName;
		getData().firstName = newFirstName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.LINK_VERTEX__FIRST_NAME,
			oldFirstName, getData().firstName));
	    addChangelogEntry(newFirstName, TestPackage.LINK_VERTEX__FIRST_NAME);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Link getLink() {
		try {
			loadingOnDemand = true;	
	  
		if (getData().link == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__LINK);
		}		
		return getData().link;
		
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
	public Link basicGetLink() {
		return data != null ? getData().link : null;
	}
 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setLink(Link newLink) {
	
		
	
		Link oldLink = getData().link;
		getData().link = newLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.LINK_VERTEX__LINK,
			oldLink, getData().link));
	    addChangelogEntry(newLink, TestPackage.LINK_VERTEX__LINK);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vertex getVertex() {
		try {
			loadingOnDemand = true;	
	  
		if (getData().vertex == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__VERTEX);
		}		
		return getData().vertex;
		
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
	public Vertex basicGetVertex() {
		return data != null ? getData().vertex : null;
	}
 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setVertex(Vertex newVertex) {
	
		
	
		Vertex oldVertex = getData().vertex;
		getData().vertex = newVertex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.LINK_VERTEX__VERTEX,
			oldVertex, getData().vertex));
	    addChangelogEntry(newVertex, TestPackage.LINK_VERTEX__VERTEX);
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
			case TestPackage.LINK_VERTEX__VCONTAINER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetVcontainer((fr.inria.atlanmod.neo4emf.testdata.Container)otherEnd, msgs);
			case TestPackage.LINK_VERTEX__FROM:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFrom()).basicAdd(otherEnd, msgs);
			case TestPackage.LINK_VERTEX__TO:
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
			case TestPackage.LINK_VERTEX__VCONTAINER:
				return basicSetVcontainer(null, msgs);
			case TestPackage.LINK_VERTEX__FROM:
				return ((InternalEList<?>)getFrom()).basicRemove(otherEnd, msgs);
			case TestPackage.LINK_VERTEX__TO:
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
			case TestPackage.LINK_VERTEX__VCONTAINER:
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
			case TestPackage.LINK_VERTEX__VNAME:
				return getVname();
			case TestPackage.LINK_VERTEX__ATRANSIENT_INTEGER:
				return getATransientInteger();
			case TestPackage.LINK_VERTEX__AVOLATILE_DATE:
				return getAVolatileDate();
			case TestPackage.LINK_VERTEX__ABOOLEAN:
				return isABoolean();
			case TestPackage.LINK_VERTEX__ASTRING_ARRAY:
				return getAStringArray();
			case TestPackage.LINK_VERTEX__ANON_UNIQUE_ARRAY:
				return getANonUniqueArray();
			case TestPackage.LINK_VERTEX__ANON_ORDERED_ARRAY:
				return getANonOrderedArray();
			case TestPackage.LINK_VERTEX__ATEN_STRING_ARRAY:
				return getATenStringArray();
			case TestPackage.LINK_VERTEX__TEMPERATURE:
				return getTemperature();
			case TestPackage.LINK_VERTEX__VCONTAINER:
				return getVcontainer();
			case TestPackage.LINK_VERTEX__FROM:
				return getFrom();
			case TestPackage.LINK_VERTEX__TO:
				return getTo();
			case TestPackage.LINK_VERTEX__FIRST_NAME:
				return getFirstName();
			case TestPackage.LINK_VERTEX__LINK:
				if (resolve) return getLink();
				return basicGetLink();
			case TestPackage.LINK_VERTEX__VERTEX:
				if (resolve) return getVertex();
				return basicGetVertex();
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
			case TestPackage.LINK_VERTEX__ATRANSIENT_INTEGER:
				setATransientInteger((BigInteger)newValue);
				return;
			case TestPackage.LINK_VERTEX__AVOLATILE_DATE:
				setAVolatileDate((Date)newValue);
				return;
			case TestPackage.LINK_VERTEX__ABOOLEAN:
				setABoolean((Boolean)newValue);
				return;
			case TestPackage.LINK_VERTEX__ASTRING_ARRAY:
				getAStringArray().clear();
				getAStringArray().addAll((Collection<? extends String>)newValue);
				return;
			case TestPackage.LINK_VERTEX__ANON_UNIQUE_ARRAY:
				getANonUniqueArray().clear();
				getANonUniqueArray().addAll((Collection<? extends BigDecimal>)newValue);
				return;
			case TestPackage.LINK_VERTEX__ANON_ORDERED_ARRAY:
				getANonOrderedArray().clear();
				getANonOrderedArray().addAll((Collection<? extends Boolean>)newValue);
				return;
			case TestPackage.LINK_VERTEX__ATEN_STRING_ARRAY:
				getATenStringArray().clear();
				getATenStringArray().addAll((Collection<? extends String>)newValue);
				return;
			case TestPackage.LINK_VERTEX__TEMPERATURE:
				setTemperature((Temperature)newValue);
				return;
			case TestPackage.LINK_VERTEX__VCONTAINER:
				setVcontainer((fr.inria.atlanmod.neo4emf.testdata.Container)newValue);
				return;
			case TestPackage.LINK_VERTEX__FROM:
				getFrom().clear();
				getFrom().addAll((Collection<? extends Link>)newValue);
				return;
			case TestPackage.LINK_VERTEX__TO:
				getTo().clear();
				getTo().addAll((Collection<? extends Link>)newValue);
				return;
			case TestPackage.LINK_VERTEX__FIRST_NAME:
				setFirstName((String)newValue);
				return;
			case TestPackage.LINK_VERTEX__LINK:
				setLink((Link)newValue);
				return;
			case TestPackage.LINK_VERTEX__VERTEX:
				setVertex((Vertex)newValue);
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
			case TestPackage.LINK_VERTEX__ATRANSIENT_INTEGER:
				setATransientInteger(DataLinkVertex.ATRANSIENT_INTEGER_EDEFAULT);
				return;
			case TestPackage.LINK_VERTEX__AVOLATILE_DATE:
				setAVolatileDate(DataLinkVertex.AVOLATILE_DATE_EDEFAULT);
				return;
			case TestPackage.LINK_VERTEX__ABOOLEAN:
				setABoolean(DataLinkVertex.ABOOLEAN_EDEFAULT);
				return;
			case TestPackage.LINK_VERTEX__ASTRING_ARRAY:
				getAStringArray().clear();
				return;
			case TestPackage.LINK_VERTEX__ANON_UNIQUE_ARRAY:
				getANonUniqueArray().clear();
				return;
			case TestPackage.LINK_VERTEX__ANON_ORDERED_ARRAY:
				getANonOrderedArray().clear();
				return;
			case TestPackage.LINK_VERTEX__ATEN_STRING_ARRAY:
				getATenStringArray().clear();
				return;
			case TestPackage.LINK_VERTEX__TEMPERATURE:
				setTemperature(DataLinkVertex.TEMPERATURE_EDEFAULT);
				return;
			case TestPackage.LINK_VERTEX__VCONTAINER:
				setVcontainer((fr.inria.atlanmod.neo4emf.testdata.Container)null);
				return;
			case TestPackage.LINK_VERTEX__FROM:
				getFrom().clear();
				return;
			case TestPackage.LINK_VERTEX__TO:
				getTo().clear();
				return;
			case TestPackage.LINK_VERTEX__FIRST_NAME:
				setFirstName(DataLinkVertex.FIRST_NAME_EDEFAULT);
				return;
			case TestPackage.LINK_VERTEX__LINK:
				setLink((Link)null);
				return;
			case TestPackage.LINK_VERTEX__VERTEX:
				setVertex((Vertex)null);
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
			case TestPackage.LINK_VERTEX__VNAME:
				return DataLinkVertex.VNAME_EDEFAULT == null ? getVname() != null : !DataLinkVertex.VNAME_EDEFAULT.equals(getVname());
			case TestPackage.LINK_VERTEX__ATRANSIENT_INTEGER:
				return DataLinkVertex.ATRANSIENT_INTEGER_EDEFAULT == null ? getATransientInteger() != null : !DataLinkVertex.ATRANSIENT_INTEGER_EDEFAULT.equals(getATransientInteger());
			case TestPackage.LINK_VERTEX__AVOLATILE_DATE:
				return DataLinkVertex.AVOLATILE_DATE_EDEFAULT == null ? getAVolatileDate() != null : !DataLinkVertex.AVOLATILE_DATE_EDEFAULT.equals(getAVolatileDate());
			case TestPackage.LINK_VERTEX__ABOOLEAN:
				return isABoolean() != DataLinkVertex.ABOOLEAN_EDEFAULT;
			case TestPackage.LINK_VERTEX__ASTRING_ARRAY:
				return getAStringArray() != null && !getAStringArray().isEmpty();
			case TestPackage.LINK_VERTEX__ANON_UNIQUE_ARRAY:
				return getANonUniqueArray() != null && !getANonUniqueArray().isEmpty();
			case TestPackage.LINK_VERTEX__ANON_ORDERED_ARRAY:
				return getANonOrderedArray() != null && !getANonOrderedArray().isEmpty();
			case TestPackage.LINK_VERTEX__ATEN_STRING_ARRAY:
				return getATenStringArray() != null && !getATenStringArray().isEmpty();
			case TestPackage.LINK_VERTEX__TEMPERATURE:
				return getTemperature() != DataLinkVertex.TEMPERATURE_EDEFAULT;
			case TestPackage.LINK_VERTEX__VCONTAINER:
				return getVcontainer() != null;
			case TestPackage.LINK_VERTEX__FROM:
				return getFrom() != null && !getFrom().isEmpty();
			case TestPackage.LINK_VERTEX__TO:
				return getTo() != null && !getTo().isEmpty();
			case TestPackage.LINK_VERTEX__FIRST_NAME:
				return DataLinkVertex.FIRST_NAME_EDEFAULT == null ? getFirstName() != null : !DataLinkVertex.FIRST_NAME_EDEFAULT.equals(getFirstName());
			case TestPackage.LINK_VERTEX__LINK:
				return getLink() != null;
			case TestPackage.LINK_VERTEX__VERTEX:
				return getVertex() != null;
		}
		return super.eIsSet(featureID);
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY19
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Vertex.class) {
			switch (derivedFeatureID) {
				case TestPackage.LINK_VERTEX__VNAME: return TestPackage.VERTEX__VNAME;
				case TestPackage.LINK_VERTEX__ATRANSIENT_INTEGER: return TestPackage.VERTEX__ATRANSIENT_INTEGER;
				case TestPackage.LINK_VERTEX__AVOLATILE_DATE: return TestPackage.VERTEX__AVOLATILE_DATE;
				case TestPackage.LINK_VERTEX__ABOOLEAN: return TestPackage.VERTEX__ABOOLEAN;
				case TestPackage.LINK_VERTEX__ASTRING_ARRAY: return TestPackage.VERTEX__ASTRING_ARRAY;
				case TestPackage.LINK_VERTEX__ANON_UNIQUE_ARRAY: return TestPackage.VERTEX__ANON_UNIQUE_ARRAY;
				case TestPackage.LINK_VERTEX__ANON_ORDERED_ARRAY: return TestPackage.VERTEX__ANON_ORDERED_ARRAY;
				case TestPackage.LINK_VERTEX__ATEN_STRING_ARRAY: return TestPackage.VERTEX__ATEN_STRING_ARRAY;
				case TestPackage.LINK_VERTEX__TEMPERATURE: return TestPackage.VERTEX__TEMPERATURE;
				case TestPackage.LINK_VERTEX__VCONTAINER: return TestPackage.VERTEX__VCONTAINER;
				case TestPackage.LINK_VERTEX__FROM: return TestPackage.VERTEX__FROM;
				case TestPackage.LINK_VERTEX__TO: return TestPackage.VERTEX__TO;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY20
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Vertex.class) {
			switch (baseFeatureID) {
				case TestPackage.VERTEX__VNAME: return TestPackage.LINK_VERTEX__VNAME;
				case TestPackage.VERTEX__ATRANSIENT_INTEGER: return TestPackage.LINK_VERTEX__ATRANSIENT_INTEGER;
				case TestPackage.VERTEX__AVOLATILE_DATE: return TestPackage.LINK_VERTEX__AVOLATILE_DATE;
				case TestPackage.VERTEX__ABOOLEAN: return TestPackage.LINK_VERTEX__ABOOLEAN;
				case TestPackage.VERTEX__ASTRING_ARRAY: return TestPackage.LINK_VERTEX__ASTRING_ARRAY;
				case TestPackage.VERTEX__ANON_UNIQUE_ARRAY: return TestPackage.LINK_VERTEX__ANON_UNIQUE_ARRAY;
				case TestPackage.VERTEX__ANON_ORDERED_ARRAY: return TestPackage.LINK_VERTEX__ANON_ORDERED_ARRAY;
				case TestPackage.VERTEX__ATEN_STRING_ARRAY: return TestPackage.LINK_VERTEX__ATEN_STRING_ARRAY;
				case TestPackage.VERTEX__TEMPERATURE: return TestPackage.LINK_VERTEX__TEMPERATURE;
				case TestPackage.VERTEX__VCONTAINER: return TestPackage.LINK_VERTEX__VCONTAINER;
				case TestPackage.VERTEX__FROM: return TestPackage.LINK_VERTEX__FROM;
				case TestPackage.VERTEX__TO: return TestPackage.LINK_VERTEX__TO;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
protected static  class DataLinkVertex extends DataLink {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getVname() <em>Vname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVname()
	 * @generated
	 * @ordered
	 */
	protected static final String VNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVname() <em>Vname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVname()
	 * @generated
	 * @ordered
	 */
	protected String vname = VNAME_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getATransientInteger() <em>ATransient Integer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getATransientInteger()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger ATRANSIENT_INTEGER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getATransientInteger() <em>ATransient Integer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getATransientInteger()
	 * @generated
	 * @ordered
	 */
	protected BigInteger aTransientInteger = ATRANSIENT_INTEGER_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getAVolatileDate() <em>AVolatile Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAVolatileDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date AVOLATILE_DATE_EDEFAULT = null;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #isABoolean() <em>ABoolean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isABoolean()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ABOOLEAN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isABoolean() <em>ABoolean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isABoolean()
	 * @generated
	 * @ordered
	 */
	protected boolean aBoolean = ABOOLEAN_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getAStringArray() <em>AString Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAStringArray()
	 * @generated
	 * @ordered
	 */
	protected EList<String> aStringArray;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getANonUniqueArray() <em>ANon Unique Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getANonUniqueArray()
	 * @generated
	 * @ordered
	 */
	protected EList<BigDecimal> aNonUniqueArray;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getANonOrderedArray() <em>ANon Ordered Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getANonOrderedArray()
	 * @generated
	 * @ordered
	 */
	protected EList<Boolean> aNonOrderedArray;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getATenStringArray() <em>ATen String Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getATenStringArray()
	 * @generated
	 * @ordered
	 */
	protected EList<String> aTenStringArray;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getTemperature() <em>Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemperature()
	 * @generated
	 * @ordered
	 */
	protected static final Temperature TEMPERATURE_EDEFAULT = Temperature.HOT;

	/**
	 * The cached value of the '{@link #getTemperature() <em>Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemperature()
	 * @generated
	 * @ordered
	 */
	protected Temperature temperature = TEMPERATURE_EDEFAULT;

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

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getFirstName() <em>First Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstName()
	 * @generated
	 * @ordered
	 */
	protected static final String FIRST_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFirstName() <em>First Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstName()
	 * @generated
	 * @ordered
	 */
	protected String firstName = FIRST_NAME_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getLink() <em>Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLink()
	 * @generated
	 * @ordered
	 */
	protected Link link;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getVertex() <em>Vertex</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVertex()
	 * @generated
	 * @ordered
	 */
	protected Vertex vertex;

	/**
	 *Constructor of DataLinkVertex
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataLinkVertex() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataLinkVertex
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Link }
	 * @generated
	 */
	//public DataLinkVertex(DataLink data)
	//{
	//	this();		
	//	
	//	name = data.name;
	//	outGoing = data.outGoing;
	//	container = data.container;
	//	inComing = data.inComing;
	//	}
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (vname: ");
		result.append(vname);
		result.append(", aTransientInteger: ");
		result.append(aTransientInteger);
		result.append(", aBoolean: ");
		result.append(aBoolean);
		result.append(", aStringArray: ");
		result.append(aStringArray);
		result.append(", aNonUniqueArray: ");
		result.append(aNonUniqueArray);
		result.append(", aNonOrderedArray: ");
		result.append(aNonOrderedArray);
		result.append(", aTenStringArray: ");
		result.append(aTenStringArray);
		result.append(", temperature: ");
		result.append(temperature);
		result.append(", firstName: ");
		result.append(firstName);
		result.append(')');
		return result.toString();
	}
		

	public void loadFrom(Node n) {
	// vname:String isField: true isPrimitiveType: false isListType: false
		vname = (String) n.getProperty("vname");
	// aTransientInteger:BigInteger isField: true isPrimitiveType: false isListType: false
		aTransientInteger = (BigInteger) n.getProperty("aTransientInteger");
	// aBoolean:boolean isField: true isPrimitiveType: true isListType: false
		aBoolean = (boolean) n.getProperty("aBoolean");
	// aStringArray:EList<String> isField: true isPrimitiveType: false isListType: true
		aStringArray = (EList<String>) n.getProperty("aStringArray");
	// aNonUniqueArray:EList<BigDecimal> isField: true isPrimitiveType: false isListType: true
		aNonUniqueArray = (EList<BigDecimal>) n.getProperty("aNonUniqueArray");
	// aNonOrderedArray:EList<Boolean> isField: true isPrimitiveType: false isListType: true
		aNonOrderedArray = (EList<Boolean>) n.getProperty("aNonOrderedArray");
	// aTenStringArray:EList<String> isField: true isPrimitiveType: false isListType: true
		aTenStringArray = (EList<String>) n.getProperty("aTenStringArray");
	// temperature:Temperature isField: true isPrimitiveType: false isListType: false
		temperature = (Temperature) n.getProperty("temperature");
	// firstName:String isField: true isPrimitiveType: false isListType: false
		firstName = (String) n.getProperty("firstName");
	}
	
	public void saveTo(Node n) {
	}
}//end data class


/*
* Neo4EMF inserted code -- end
*/




} //LinkVertexImpl
