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
 * An implementation of the model object '<em><b>Vertex</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getVname <em>Vname</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getATransientInteger <em>ATransient Integer</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getAVolatileDate <em>AVolatile Date</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#isABoolean <em>ABoolean</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getAStringArray <em>AString Array</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getANonUniqueArray <em>ANon Unique Array</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getANonOrderedArray <em>ANon Ordered Array</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getATenStringArray <em>ATen String Array</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getTemperature <em>Temperature</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getVcontainer <em>Vcontainer</em>}</li>
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
			TestPackage.VERTEX__ATRANSIENT_INTEGER,
			oldATransientInteger, getData().aTransientInteger));
	    addChangelogEntry(newATransientInteger, TestPackage.VERTEX__ATRANSIENT_INTEGER);
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
	    //addChangelogEntry(newAVolatileDate, TestPackage.VERTEX__AVOLATILE_DATE);
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
			TestPackage.VERTEX__ABOOLEAN,
			oldABoolean, getData().aBoolean));
	    addChangelogEntry(newABoolean, TestPackage.VERTEX__ABOOLEAN);
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
		getData().aStringArray = new EDataTypeUniqueEList<String>(String.class, this, TestPackage.VERTEX__ASTRING_ARRAY);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.VERTEX__ASTRING_ARRAY);			}
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
		getData().aNonUniqueArray = new EDataTypeEList<BigDecimal>(BigDecimal.class, this, TestPackage.VERTEX__ANON_UNIQUE_ARRAY);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.VERTEX__ANON_UNIQUE_ARRAY);			}
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
		getData().aNonOrderedArray = new EDataTypeUniqueEList<Boolean>(Boolean.class, this, TestPackage.VERTEX__ANON_ORDERED_ARRAY);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.VERTEX__ANON_ORDERED_ARRAY);			}
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
		getData().aTenStringArray = new EDataTypeUniqueEList<String>(String.class, this, TestPackage.VERTEX__ATEN_STRING_ARRAY);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.VERTEX__ATEN_STRING_ARRAY);			}
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
			TestPackage.VERTEX__TEMPERATURE,
			oldTemperature, getData().temperature));
	    addChangelogEntry(newTemperature, TestPackage.VERTEX__TEMPERATURE);
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
			fr.inria.atlanmod.neo4emf.testdata.Container vcontainer = (fr.inria.atlanmod.neo4emf.testdata.Container) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, TestPackage.VERTEX__VCONTAINER);
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
	
		msgs = eBasicSetContainer((InternalEObject)newVcontainer, TestPackage.VERTEX__VCONTAINER, msgs);
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setVcontainer(fr.inria.atlanmod.neo4emf.testdata.Container newVcontainer) {
	
		
		if (newVcontainer != eInternalContainer() || (eContainerFeatureID() != TestPackage.VERTEX__VCONTAINER && newVcontainer != null)) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, TestPackage.VERTEX__VCONTAINER, newVcontainer, newVcontainer));
	    addChangelogEntry(newVcontainer, TestPackage.VERTEX__VCONTAINER);
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
		getData().from = new EObjectWithInverseResolvingEList<Link>(Link.class, this, TestPackage.VERTEX__FROM, TestPackage.LINK__OUT_GOING);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.VERTEX__FROM);			}
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
		getData().to = new EObjectWithInverseResolvingEList<Link>(Link.class, this, TestPackage.VERTEX__TO, TestPackage.LINK__IN_COMING);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.VERTEX__TO);			}
		return getData().to;
	} finally {
	loadingOnDemand = false;
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
			case TestPackage.VERTEX__VCONTAINER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetVcontainer((fr.inria.atlanmod.neo4emf.testdata.Container)otherEnd, msgs);
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
			case TestPackage.VERTEX__VCONTAINER:
				return basicSetVcontainer(null, msgs);
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
			case TestPackage.VERTEX__VCONTAINER:
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
			case TestPackage.VERTEX__VNAME:
				return getVname();
			case TestPackage.VERTEX__ATRANSIENT_INTEGER:
				return getATransientInteger();
			case TestPackage.VERTEX__AVOLATILE_DATE:
				return getAVolatileDate();
			case TestPackage.VERTEX__ABOOLEAN:
				return isABoolean();
			case TestPackage.VERTEX__ASTRING_ARRAY:
				return getAStringArray();
			case TestPackage.VERTEX__ANON_UNIQUE_ARRAY:
				return getANonUniqueArray();
			case TestPackage.VERTEX__ANON_ORDERED_ARRAY:
				return getANonOrderedArray();
			case TestPackage.VERTEX__ATEN_STRING_ARRAY:
				return getATenStringArray();
			case TestPackage.VERTEX__TEMPERATURE:
				return getTemperature();
			case TestPackage.VERTEX__VCONTAINER:
				return getVcontainer();
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
			case TestPackage.VERTEX__ATRANSIENT_INTEGER:
				setATransientInteger((BigInteger)newValue);
				return;
			case TestPackage.VERTEX__AVOLATILE_DATE:
				setAVolatileDate((Date)newValue);
				return;
			case TestPackage.VERTEX__ABOOLEAN:
				setABoolean((Boolean)newValue);
				return;
			case TestPackage.VERTEX__ASTRING_ARRAY:
				getAStringArray().clear();
				getAStringArray().addAll((Collection<? extends String>)newValue);
				return;
			case TestPackage.VERTEX__ANON_UNIQUE_ARRAY:
				getANonUniqueArray().clear();
				getANonUniqueArray().addAll((Collection<? extends BigDecimal>)newValue);
				return;
			case TestPackage.VERTEX__ANON_ORDERED_ARRAY:
				getANonOrderedArray().clear();
				getANonOrderedArray().addAll((Collection<? extends Boolean>)newValue);
				return;
			case TestPackage.VERTEX__ATEN_STRING_ARRAY:
				getATenStringArray().clear();
				getATenStringArray().addAll((Collection<? extends String>)newValue);
				return;
			case TestPackage.VERTEX__TEMPERATURE:
				setTemperature((Temperature)newValue);
				return;
			case TestPackage.VERTEX__VCONTAINER:
				setVcontainer((fr.inria.atlanmod.neo4emf.testdata.Container)newValue);
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
			case TestPackage.VERTEX__ATRANSIENT_INTEGER:
				setATransientInteger(DataVertex.ATRANSIENT_INTEGER_EDEFAULT);
				return;
			case TestPackage.VERTEX__AVOLATILE_DATE:
				setAVolatileDate(DataVertex.AVOLATILE_DATE_EDEFAULT);
				return;
			case TestPackage.VERTEX__ABOOLEAN:
				setABoolean(DataVertex.ABOOLEAN_EDEFAULT);
				return;
			case TestPackage.VERTEX__ASTRING_ARRAY:
				getAStringArray().clear();
				return;
			case TestPackage.VERTEX__ANON_UNIQUE_ARRAY:
				getANonUniqueArray().clear();
				return;
			case TestPackage.VERTEX__ANON_ORDERED_ARRAY:
				getANonOrderedArray().clear();
				return;
			case TestPackage.VERTEX__ATEN_STRING_ARRAY:
				getATenStringArray().clear();
				return;
			case TestPackage.VERTEX__TEMPERATURE:
				setTemperature(DataVertex.TEMPERATURE_EDEFAULT);
				return;
			case TestPackage.VERTEX__VCONTAINER:
				setVcontainer((fr.inria.atlanmod.neo4emf.testdata.Container)null);
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
			case TestPackage.VERTEX__VNAME:
				return DataVertex.VNAME_EDEFAULT == null ? getVname() != null : !DataVertex.VNAME_EDEFAULT.equals(getVname());
			case TestPackage.VERTEX__ATRANSIENT_INTEGER:
				return DataVertex.ATRANSIENT_INTEGER_EDEFAULT == null ? getATransientInteger() != null : !DataVertex.ATRANSIENT_INTEGER_EDEFAULT.equals(getATransientInteger());
			case TestPackage.VERTEX__AVOLATILE_DATE:
				return DataVertex.AVOLATILE_DATE_EDEFAULT == null ? getAVolatileDate() != null : !DataVertex.AVOLATILE_DATE_EDEFAULT.equals(getAVolatileDate());
			case TestPackage.VERTEX__ABOOLEAN:
				return isABoolean() != DataVertex.ABOOLEAN_EDEFAULT;
			case TestPackage.VERTEX__ASTRING_ARRAY:
				return getAStringArray() != null && !getAStringArray().isEmpty();
			case TestPackage.VERTEX__ANON_UNIQUE_ARRAY:
				return getANonUniqueArray() != null && !getANonUniqueArray().isEmpty();
			case TestPackage.VERTEX__ANON_ORDERED_ARRAY:
				return getANonOrderedArray() != null && !getANonOrderedArray().isEmpty();
			case TestPackage.VERTEX__ATEN_STRING_ARRAY:
				return getATenStringArray() != null && !getATenStringArray().isEmpty();
			case TestPackage.VERTEX__TEMPERATURE:
				return getTemperature() != DataVertex.TEMPERATURE_EDEFAULT;
			case TestPackage.VERTEX__VCONTAINER:
				return getVcontainer() != null;
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

/*
* Neo4EMF inserted code -- begin
*/

// data Class generation 
protected static  class DataVertex {


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
	}
	
	public void saveTo(Node n) {
	}
}//end data class


/*
* Neo4EMF inserted code -- end
*/




} //VertexImpl
