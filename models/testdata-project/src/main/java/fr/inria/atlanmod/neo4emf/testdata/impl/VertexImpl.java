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

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.NeoEObjectWithInverseResolvingEList;
import fr.inria.atlanmod.neo4emf.testdata.ContainerType;
import fr.inria.atlanmod.neo4emf.testdata.Link;
import fr.inria.atlanmod.neo4emf.testdata.Temperature;
import fr.inria.atlanmod.neo4emf.testdata.TestPackage;
import fr.inria.atlanmod.neo4emf.testdata.Vertex;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
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
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getVString <em>VString</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getATransientInteger <em>ATransient Integer</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getADate <em>ADate</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getAVolatileDate <em>AVolatile Date</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#isABoolean <em>ABoolean</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getAReal <em>AReal</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getAnIntegerArray <em>An Integer Array</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl#getARealArray <em>AReal Array</em>}</li>
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
public class VertexImpl extends NamedElementImpl implements Vertex {

	 
	
	 
	 
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
	public String getVString() {
		try {
			setLoadingOnDemand();	
	  		
			return getData().vString;
		
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
	public BigInteger getATransientInteger() {
		try {
			setLoadingOnDemand();	
	  		
			return getData().aTransientInteger;
		
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
	public void setATransientInteger(BigInteger newATransientInteger) {
	
		
	
    BigInteger oldATransientInteger = getData().aTransientInteger;
    getData().aTransientInteger = newATransientInteger;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.VERTEX__ATRANSIENT_INTEGER,
			oldATransientInteger, getData().aTransientInteger));
        }  
  if(isLoaded()) {
		  this.addChangelogEntry(newATransientInteger, TestPackage.VERTEX__ATRANSIENT_INTEGER);
    }
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getADate() {
		try {
			setLoadingOnDemand();	
	  		
			return getData().aDate;
		
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
	public void setADate(Date newADate) {
	
		
	
    Date oldADate = getData().aDate;
    getData().aDate = newADate;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.VERTEX__ADATE,
			oldADate, getData().aDate));
        }  
  if(isLoaded()) {
		  this.addChangelogEntry(newADate, TestPackage.VERTEX__ADATE);
    }
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
			setLoadingOnDemand();
		// TODO: implement this method to return the 'AVolatile Date' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
	public void setAVolatileDate(Date newAVolatileDate) {
	
		
	
		// TODO: implement this method to set the 'AVolatile Date' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
			setLoadingOnDemand();	
	  		
			return getData().aBoolean;
		
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
	public void setABoolean(boolean newABoolean) {
	
		
	
    boolean oldABoolean = getData().aBoolean;
    getData().aBoolean = newABoolean;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.VERTEX__ABOOLEAN,
			oldABoolean, getData().aBoolean));
        }  
  if(isLoaded()) {
		  this.addChangelogEntry(newABoolean, TestPackage.VERTEX__ABOOLEAN);
    }
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getAReal() {
		try {
			setLoadingOnDemand();	
	  		
			return getData().aReal;
		
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
	public void setAReal(BigDecimal newAReal) {
	
		
	
    BigDecimal oldAReal = getData().aReal;
    getData().aReal = newAReal;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.VERTEX__AREAL,
			oldAReal, getData().aReal));
        }  
  if(isLoaded()) {
		  this.addChangelogEntry(newAReal, TestPackage.VERTEX__AREAL);
    }
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BigInteger> getAnIntegerArray() {
		try {
			setLoadingOnDemand();	
	   
	  	
      			return getData().anIntegerArray;
      	
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
	public EList<BigDecimal> getARealArray() {
		try {
			setLoadingOnDemand();	
	   
	  	
      			return getData().aRealArray;
      	
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
	public EList<String> getAStringArray() {
		try {
			setLoadingOnDemand();	
	   
	  	
      			return getData().aStringArray;
      	
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
	public EList<BigDecimal> getANonUniqueArray() {
		try {
			setLoadingOnDemand();	
	   
	  	
      			return getData().aNonUniqueArray;
      	
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
	public EList<Boolean> getANonOrderedArray() {
		try {
			setLoadingOnDemand();	
	   
	  	
      			return getData().aNonOrderedArray;
      	
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
	public EList<String> getATenStringArray() {
		try {
			setLoadingOnDemand();	
	   
	  	
      			return getData().aTenStringArray;
      	
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
	public Temperature getTemperature() {
		try {
			setLoadingOnDemand();	
	  		
			return getData().temperature;
		
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
	public void setTemperature(Temperature newTemperature) {
	
		
	
    Temperature oldTemperature = getData().temperature;
              // TEST POUET
		getData().temperature = newTemperature == null ? DataVertex.TEMPERATURE_EDEFAULT : newTemperature;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.VERTEX__TEMPERATURE,
			oldTemperature, getData().temperature));
        }  
  if(isLoaded()) {
		  this.addChangelogEntry(newTemperature, TestPackage.VERTEX__TEMPERATURE);
    }
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContainerType getVcontainer() {
		try {
			setLoadingOnDemand();	
	  
		if (isLoaded() && eContainer() == null) {
			ContainerType vcontainer = (ContainerType) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, TestPackage.VERTEX__VCONTAINER);
			basicSetVcontainer(vcontainer,null);}
			return (ContainerType)eContainer();
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
	public NotificationChain basicSetVcontainer(ContainerType newVcontainer, NotificationChain msgs) {
	
		msgs = eBasicSetContainer((InternalEObject)newVcontainer, TestPackage.VERTEX__VCONTAINER, msgs);
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setVcontainer(ContainerType newVcontainer) {
	
		
	
		// TEST
      	if (newVcontainer != eInternalContainer() || (eContainerFeatureID() != TestPackage.VERTEX__VCONTAINER && newVcontainer != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newVcontainer)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if(eInternalContainer() != null) {
				if(newVcontainer == null && isLoaded()) {
					addChangelogRemoveEntry(eInternalContainer(), TestPackage.VERTEX__VCONTAINER);
				}
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if(isLoaded()) {
				addChangelogEntry(newVcontainer, TestPackage.VERTEX__VCONTAINER);
			}
			msgs = basicSetVcontainer(newVcontainer, msgs);
			if(newVcontainer != null) {
				msgs = ((InternalEObject)newVcontainer).eInverseAdd(this, TestPackage.CONTAINER_TYPE__NODES, ContainerType.class, msgs);
			}
			if(msgs != null) {
				msgs.dispatch();
			}	
		}
		else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, TestPackage.VERTEX__VCONTAINER, newVcontainer, newVcontainer));
		}
  if(isLoaded()) {
		  this.addChangelogEntry(newVcontainer, TestPackage.VERTEX__VCONTAINER);
    }
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
	   
	  	
			
				if (getData().from == null || getData().from.isEnqueued()){
				
		        
		        	EList<Link> newList = new NeoEObjectWithInverseResolvingEList<Link>(Link.class, this, TestPackage.VERTEX__FROM, TestPackage.LINK__OUT_GOING);
		        	getData().from = new SoftReference<EList<Link>>(newList, garbagedData);
					if (isLoaded()) {
						((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.VERTEX__FROM);			
		        	}
		        	else {
		        		// TODO find a better implementation
		        		getData().strongFrom = newList;
		        	}
		        
		    	}
				return getData().from.get();
      	
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
	   
	  	
			
				if (getData().to == null || getData().to.isEnqueued()){
				
		        
		        	EList<Link> newList = new NeoEObjectWithInverseResolvingEList<Link>(Link.class, this, TestPackage.VERTEX__TO, TestPackage.LINK__IN_COMING);
		        	getData().to = new SoftReference<EList<Link>>(newList, garbagedData);
					if (isLoaded()) {
						((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.VERTEX__TO);			
		        	}
		        	else {
		        		// TODO find a better implementation
		        		getData().strongTo = newList;
		        	}
		        
		    	}
				return getData().to.get();
      	
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
			case TestPackage.VERTEX__VCONTAINER:
				if (eInternalContainer() != null && !eInternalContainer().equals(otherEnd)) {
					msgs = eBasicRemoveFromContainer(msgs);
				}
				// Should be done before basicSet, otherwise the ChangeLog may be flushed and the 
				// isLoaded method would return true, and duplicate the AddLink entry
				if(isLoaded() && !isLoadingOnDemand() && !((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
					addChangelogEntry(otherEnd, TestPackage.VERTEX__VCONTAINER);
				}
				return basicSetVcontainer((ContainerType)otherEnd, msgs);
			case TestPackage.VERTEX__FROM:
    			//if(((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
    			//	setLoadingOnDemand();
    			//}
    			if(isLoaded() && !isLoadingOnDemand()) {
    				addChangelogEntry(otherEnd, TestPackage.VERTEX__FROM);
    			}
    			return ((InternalEList<InternalEObject>)(InternalEList<?>)getFrom()).basicAdd(otherEnd, msgs);
    			//if(((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
    			//	unsetLoadingOnDemand();
    			//}
    			//return null;
			case TestPackage.VERTEX__TO:
    			//if(((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
    			//	setLoadingOnDemand();
    			//}
    			if(isLoaded() && !isLoadingOnDemand()) {
    				addChangelogEntry(otherEnd, TestPackage.VERTEX__TO);
    			}
    			return ((InternalEList<InternalEObject>)(InternalEList<?>)getTo()).basicAdd(otherEnd, msgs);
    			//if(((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
    			//	unsetLoadingOnDemand();
    			//}
    			//return null;
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
  				if(isLoaded()) {
  					addChangelogRemoveEntry(otherEnd,featureID);
  				}
				return basicSetVcontainer(null, msgs);
			case TestPackage.VERTEX__FROM:
    			addChangelogRemoveEntry(otherEnd, TestPackage.VERTEX__FROM);
				return ((InternalEList<?>)getFrom()).basicRemove(otherEnd, msgs);
			case TestPackage.VERTEX__TO:
    			addChangelogRemoveEntry(otherEnd, TestPackage.VERTEX__TO);
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
				return eInternalContainer().eInverseRemove(this, TestPackage.CONTAINER_TYPE__NODES, ContainerType.class, msgs);
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
			case TestPackage.VERTEX__VSTRING:
				return getVString();
			case TestPackage.VERTEX__ATRANSIENT_INTEGER:
				return getATransientInteger();
			case TestPackage.VERTEX__ADATE:
				return getADate();
			case TestPackage.VERTEX__AVOLATILE_DATE:
				return getAVolatileDate();
			case TestPackage.VERTEX__ABOOLEAN:
				return isABoolean();
			case TestPackage.VERTEX__AREAL:
				return getAReal();
			case TestPackage.VERTEX__AN_INTEGER_ARRAY:
				return getAnIntegerArray();
			case TestPackage.VERTEX__AREAL_ARRAY:
				return getARealArray();
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
			case TestPackage.VERTEX__ADATE:
				setADate((Date)newValue);
				return;
			case TestPackage.VERTEX__AVOLATILE_DATE:
				setAVolatileDate((Date)newValue);
				return;
			case TestPackage.VERTEX__ABOOLEAN:
				setABoolean((Boolean)newValue);
				return;
			case TestPackage.VERTEX__AREAL:
				setAReal((BigDecimal)newValue);
				return;
			case TestPackage.VERTEX__AN_INTEGER_ARRAY:
				getAnIntegerArray().clear();
				getAnIntegerArray().addAll((Collection<? extends BigInteger>)newValue);
				return;
			case TestPackage.VERTEX__AREAL_ARRAY:
				getARealArray().clear();
				getARealArray().addAll((Collection<? extends BigDecimal>)newValue);
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
				setVcontainer((ContainerType)newValue);
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
			case TestPackage.VERTEX__ADATE:
				setADate(DataVertex.ADATE_EDEFAULT);
				return;
			case TestPackage.VERTEX__AVOLATILE_DATE:
				setAVolatileDate(DataVertex.AVOLATILE_DATE_EDEFAULT);
				return;
			case TestPackage.VERTEX__ABOOLEAN:
				setABoolean(DataVertex.ABOOLEAN_EDEFAULT);
				return;
			case TestPackage.VERTEX__AREAL:
				setAReal(DataVertex.AREAL_EDEFAULT);
				return;
			case TestPackage.VERTEX__AN_INTEGER_ARRAY:
				getAnIntegerArray().clear();
				return;
			case TestPackage.VERTEX__AREAL_ARRAY:
				getARealArray().clear();
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
				setVcontainer((ContainerType)null);
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
			case TestPackage.VERTEX__VSTRING:
				return DataVertex.VSTRING_EDEFAULT == null ? getVString() != null : !DataVertex.VSTRING_EDEFAULT.equals(getVString());
			case TestPackage.VERTEX__ATRANSIENT_INTEGER:
				return DataVertex.ATRANSIENT_INTEGER_EDEFAULT == null ? getATransientInteger() != null : !DataVertex.ATRANSIENT_INTEGER_EDEFAULT.equals(getATransientInteger());
			case TestPackage.VERTEX__ADATE:
				return DataVertex.ADATE_EDEFAULT == null ? getADate() != null : !DataVertex.ADATE_EDEFAULT.equals(getADate());
			case TestPackage.VERTEX__AVOLATILE_DATE:
				return DataVertex.AVOLATILE_DATE_EDEFAULT == null ? getAVolatileDate() != null : !DataVertex.AVOLATILE_DATE_EDEFAULT.equals(getAVolatileDate());
			case TestPackage.VERTEX__ABOOLEAN:
				return isABoolean() != DataVertex.ABOOLEAN_EDEFAULT;
			case TestPackage.VERTEX__AREAL:
				return DataVertex.AREAL_EDEFAULT == null ? getAReal() != null : !DataVertex.AREAL_EDEFAULT.equals(getAReal());
			case TestPackage.VERTEX__AN_INTEGER_ARRAY:
				return getAnIntegerArray() != null && !getAnIntegerArray().isEmpty();
			case TestPackage.VERTEX__AREAL_ARRAY:
				return getARealArray() != null && !getARealArray().isEmpty();
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

	/**
	 * @generated
	 */
	public void setDataStrongReferences() {
		if(data != null) {
			
				
			
				
			
				
			
				
			
				
			
				
			
				
			
				
			
				
			
				
			
				
			
				
			
				
			
				
			
				
			if(getData().strongFrom != null) {
				getData().strongFrom = getData().from.get();
			}
				
			
				
			if(getData().strongTo != null) {
				getData().strongTo = getData().to.get();
			}
				
			
		}
	}

	/**
	 * @generated
	 */
	public void releaseDataStrongReferences() {
		
			
		
			
		
			
		
			
		
			
		
			
		
			
		
			
		
			
		
			
		
			
		
			
		
			
		
			
		
			
		getData().strongFrom = null;
			
		
			
		getData().strongTo = null;
			
		
	}
/*
* Neo4EMF inserted code -- begin
*/
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataVertex getData() {
		if ( data == null || !(data instanceof DataVertex)){
			// TODO check that
			setLoadingOnDemand();
			data = new DataVertex();
			if (isLoaded()) {
				((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
			unsetLoadingOnDemand();
			}
		return (DataVertex) data;
	}

	/**
	*
	* @generated
	**/
	public void loadAllAttributesFrom(Node n) {
		this.data = new DataVertex(this);
		data.loadAllAttributesFrom(n);
	}
	
	/**
	*
	* @generated
	**/
	public void saveAllAttributesTo(Node n) {
		if (data != null) {
			data.saveAllAttributesTo(n);
		}
	}
	
	/**
	*
	* @generated
	**/
	public void saveAttributeTo(int featureID, Node n) {
		if (data != null) {
			data.saveAttributeTo(featureID, n);
		}
	}
	
/**
*   extends NamedElementImpl
*  1
*
*/
protected static class DataVertex extends DataNamedElement{


	/**
	 *Constructor of DataVertex
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVertex() {
	}


	/**
	 * Constructor of DataVertex
	 * Initializes multi-valued fields, if any.
	 *
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVertex(VertexImpl outer) {
		 super(outer); 
		anIntegerArray = new EDataTypeUniqueEList<BigInteger>(BigInteger.class, outer, TestPackage.VERTEX__AN_INTEGER_ARRAY);
		aRealArray = new EDataTypeUniqueEList<BigDecimal>(BigDecimal.class, outer, TestPackage.VERTEX__AREAL_ARRAY);
		aStringArray = new EDataTypeUniqueEList<String>(String.class, outer, TestPackage.VERTEX__ASTRING_ARRAY);
		aNonUniqueArray = new EDataTypeEList<BigDecimal>(BigDecimal.class, outer, TestPackage.VERTEX__ANON_UNIQUE_ARRAY);
		aNonOrderedArray = new EDataTypeEList<Boolean>(Boolean.class, outer, TestPackage.VERTEX__ANON_ORDERED_ARRAY);
		aTenStringArray = new EDataTypeUniqueEList<String>(String.class, outer, TestPackage.VERTEX__ATEN_STRING_ARRAY);
	}


    
	/**
	 * The default value of the '{@link #getVString() <em>VString</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVString()
	 * @generated
	 * @ordered
	 */
	protected static final String VSTRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVString() <em>VString</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVString()
	 * @generated
	 * @ordered
	 */
	protected String vString = VSTRING_EDEFAULT;

    
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

    
	/**
	 * The default value of the '{@link #getADate() <em>ADate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getADate()
	 * @generated
	 * @ordered
	 */
	protected static final Date ADATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getADate() <em>ADate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getADate()
	 * @generated
	 * @ordered
	 */
	protected Date aDate = ADATE_EDEFAULT;

    
	/**
	 * The default value of the '{@link #getAVolatileDate() <em>AVolatile Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAVolatileDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date AVOLATILE_DATE_EDEFAULT = null;

    
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

    
	/**
	 * The default value of the '{@link #getAReal() <em>AReal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAReal()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal AREAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAReal() <em>AReal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAReal()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal aReal = AREAL_EDEFAULT;

    
	/**
	 * The cached value of the '{@link #getAnIntegerArray() <em>An Integer Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnIntegerArray()
	 * @generated
	 * @ordered
	 */
    protected EList<BigInteger> anIntegerArray;

	/**
	 * The cached value of the '{@link #getARealArray() <em>AReal Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getARealArray()
	 * @generated
	 * @ordered
	 */
    protected EList<BigDecimal> aRealArray;

	/**
	 * The cached value of the '{@link #getAStringArray() <em>AString Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAStringArray()
	 * @generated
	 * @ordered
	 */
    protected EList<String> aStringArray;

	/**
	 * The cached value of the '{@link #getANonUniqueArray() <em>ANon Unique Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getANonUniqueArray()
	 * @generated
	 * @ordered
	 */
    protected EList<BigDecimal> aNonUniqueArray;

	/**
	 * The cached value of the '{@link #getANonOrderedArray() <em>ANon Ordered Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getANonOrderedArray()
	 * @generated
	 * @ordered
	 */
    protected EList<Boolean> aNonOrderedArray;

	/**
	 * The cached value of the '{@link #getATenStringArray() <em>ATen String Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getATenStringArray()
	 * @generated
	 * @ordered
	 */
    protected EList<String> aTenStringArray;

	/**
	 * The cached value of the '{@link #getAnIntegerArray() <em>An Integer Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnIntegerArray()
	 * @generated
	 * @ordered
	 */
   
    
	/**
	 * The cached value of the '{@link #getARealArray() <em>AReal Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getARealArray()
	 * @generated
	 * @ordered
	 */
   
    
	/**
	 * The cached value of the '{@link #getAStringArray() <em>AString Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAStringArray()
	 * @generated
	 * @ordered
	 */
   
    
	/**
	 * The cached value of the '{@link #getANonUniqueArray() <em>ANon Unique Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getANonUniqueArray()
	 * @generated
	 * @ordered
	 */
   
    
	/**
	 * The cached value of the '{@link #getANonOrderedArray() <em>ANon Ordered Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getANonOrderedArray()
	 * @generated
	 * @ordered
	 */
   
    
	/**
	 * The cached value of the '{@link #getATenStringArray() <em>ATen String Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getATenStringArray()
	 * @generated
	 * @ordered
	 */
   
    
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

    
    
	/**
	 * The cached value of the '{@link #getFrom() <em>From</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
      	
    protected EList<Link> strongFrom;
      	
	protected SoftReference<EList<Link>> from;
    
	/**
	 * The cached value of the '{@link #getTo() <em>To</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
      	
    protected EList<Link> strongTo;
      	
	protected SoftReference<EList<Link>> to;

	
		
	/**
	 *Constructor of DataVertex%>
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link NamedElement }
	 * @generated
	 */
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (vString: ");
		result.append(vString);
		result.append(", aTransientInteger: ");
		result.append(aTransientInteger);
		result.append(", aDate: ");
		result.append(aDate);
		result.append(", aBoolean: ");
		result.append(aBoolean);
		result.append(", aReal: ");
		result.append(aReal);
		result.append(", anIntegerArray: ");
		result.append(anIntegerArray);
		result.append(", aRealArray: ");
		result.append(aRealArray);
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
		

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void loadAllAttributesFrom(Node n) {
		super.loadAllAttributesFrom(n);
		Object aux;
		aux = n.getProperty("vString", null);
		if (aux != null) {
			vString = (String) aux;
		} 
		aux = n.getProperty("aTransientInteger", null);
		if (aux != null) {
			aTransientInteger = BigInteger.valueOf((long) aux);
		} 
		aux = n.getProperty("aDate", null);
		if (aux != null) {
			aDate = (Date) aux; // Object type : aDate
		} 
		aux = n.getProperty("aBoolean", null);
		if (aux != null) {
			aBoolean = (boolean) aux;
		} 
		aux = n.getProperty("aReal", null);
		if (aux != null) {
			aReal = BigDecimal.valueOf((double) aux);
		} 
		aux = n.getProperty("anIntegerArray", null);
		if (aux != null) {
				long[] anIntegerArrayAux = (long[]) aux;
				for(int i = 0; i < anIntegerArrayAux.length; i++) {
					anIntegerArray.add(BigInteger.valueOf(anIntegerArrayAux[i]));
				}
			
		} 
		aux = n.getProperty("aRealArray", null);
		if (aux != null) {
			double[] aRealArrayAux = (double[]) aux;
			for(int i = 0; i < aRealArrayAux.length; i++) {
				aRealArray.add(BigDecimal.valueOf(aRealArrayAux[i]));
			}
		
		} 
		aux = n.getProperty("aStringArray", null);
		if (aux != null) {
			List<String> aStringArrayAux = Arrays.asList((String[]) aux);
			aStringArray.addAll(aStringArrayAux);
		} 
		aux = n.getProperty("aNonUniqueArray", null);
		if (aux != null) {
			double[] aNonUniqueArrayAux = (double[]) aux;
			for(int i = 0; i < aNonUniqueArrayAux.length; i++) {
				aNonUniqueArray.add(BigDecimal.valueOf(aNonUniqueArrayAux[i]));
			}
		
		} 
		aux = n.getProperty("aNonOrderedArray", null);
		if (aux != null) {
			List<Boolean> aNonOrderedArrayAux = Arrays.asList((Boolean[]) aux);
			aNonOrderedArray.addAll(aNonOrderedArrayAux);
		} 
		aux = n.getProperty("aTenStringArray", null);
		if (aux != null) {
			List<String> aTenStringArrayAux = Arrays.asList((String[]) aux);
			aTenStringArray.addAll(aTenStringArrayAux);
		} 
		aux = n.getProperty("temperature", null);
		if (aux != null) {
			temperature = Temperature.get((int) aux); // Enumeration type
			
		} 
	}


	/**
 	* <!-- begin-user-doc -->
 	* <!-- end-user-doc -->
 	* @generated
 	*/
	public void saveAllAttributesTo(Node n) {
		
		super.saveAllAttributesTo(n);
		
		
		this.saveaTransientIntegerTo(n);
		this.saveaDateTo(n);
		this.saveaBooleanTo(n);
		this.saveaRealTo(n);
		this.saveanIntegerArrayTo(n);
		this.saveaRealArrayTo(n);
		this.saveaStringArrayTo(n);
		this.saveaNonUniqueArrayTo(n);
		this.saveaNonOrderedArrayTo(n);
		this.saveaTenStringArrayTo(n);
		this.savetemperatureTo(n);
	} // saveTo()
	
	/**
 	* <!-- begin-user-doc -->
 	* <!-- end-user-doc -->
 	* @generated
 	*/
	public void saveAttributeTo(int featureID, Node n) {
		switch (featureID) {
			
			case TestPackage.VERTEX__ATRANSIENT_INTEGER:
				this.saveaTransientIntegerTo(n);
				return;
			case TestPackage.VERTEX__ADATE:
				this.saveaDateTo(n);
				return;
			case TestPackage.VERTEX__ABOOLEAN:
				this.saveaBooleanTo(n);
				return;
			case TestPackage.VERTEX__AREAL:
				this.saveaRealTo(n);
				return;
			case TestPackage.VERTEX__AN_INTEGER_ARRAY:
				this.saveanIntegerArrayTo(n);
				return;
			case TestPackage.VERTEX__AREAL_ARRAY:
				this.saveaRealArrayTo(n);
				return;
			case TestPackage.VERTEX__ASTRING_ARRAY:
				this.saveaStringArrayTo(n);
				return;
			case TestPackage.VERTEX__ANON_UNIQUE_ARRAY:
				this.saveaNonUniqueArrayTo(n);
				return;
			case TestPackage.VERTEX__ANON_ORDERED_ARRAY:
				this.saveaNonOrderedArrayTo(n);
				return;
			case TestPackage.VERTEX__ATEN_STRING_ARRAY:
				this.saveaTenStringArrayTo(n);
				return;
			case TestPackage.VERTEX__TEMPERATURE:
				this.savetemperatureTo(n);
				return;
		} // switch
	} // saveAttributeTo()
	

	/*
	*
	*/
	private void saveaTransientIntegerTo(Node n) {
	
		if (aTransientInteger != null) n.setProperty("aTransientInteger", aTransientInteger.longValue());
	} 
	
	/*
	*
	*/
	private void saveaDateTo(Node n) {
	
		if (aDate != null) n.setProperty("aDate", aDate);
	} 
	
	/*
	*
	*/
	private void saveaBooleanTo(Node n) {
	
		n.setProperty("aBoolean", aBoolean);
	} 
	
	/*
	*
	*/
	private void saveaRealTo(Node n) {
	
		if (aReal != null) n.setProperty("aReal", aReal.doubleValue());
	} 
	
	/*
	*
	*/
	private void savetemperatureTo(Node n) {
		if (temperature != null) n.setProperty("temperature", temperature.getValue());
	}

	/*
	*
	*/
	private void saveanIntegerArrayTo(Node n) {
		
			if (anIntegerArray != null) {
				long[] aux = new long[anIntegerArray.size()];
				for (int i = 0; i < aux.length; i++) {
					aux[i] = anIntegerArray.get(i).longValue();
				}
				n.setProperty("anIntegerArray", aux);
			}
	}
	/*
	*
	*/
	private void saveaRealArrayTo(Node n) {
		
			if (aRealArray != null) {
				double[] aux = new double[aRealArray.size()];
				for (int i = 0; i < aux.length; i++) {
					aux[i] = aRealArray.get(i).doubleValue();
				}
				n.setProperty("aRealArray", aux);
			}
	}
	/*
	*
	*/
	private void saveaStringArrayTo(Node n) {
		
			if (aStringArray != null) n.setProperty("aStringArray", aStringArray.toArray());
			
	}
	/*
	*
	*/
	private void saveaNonUniqueArrayTo(Node n) {
		
			if (aNonUniqueArray != null) {
				double[] aux = new double[aNonUniqueArray.size()];
				for (int i = 0; i < aux.length; i++) {
					aux[i] = aNonUniqueArray.get(i).doubleValue();
				}
				n.setProperty("aNonUniqueArray", aux);
			}
	}
	/*
	*
	*/
	private void saveaNonOrderedArrayTo(Node n) {
		
			if (aNonOrderedArray != null) n.setProperty("aNonOrderedArray", aNonOrderedArray.toArray());
			
	}
	/*
	*
	*/
	private void saveaTenStringArrayTo(Node n) {
		
			if (aTenStringArray != null) n.setProperty("aTenStringArray", aTenStringArray.toArray());
			
	}


}//end attribute class
	
protected static class VertexReferences  extends DataNamedElement {
    
    
	/**
	 * The cached value of the '{@link #getFrom() <em>From</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
      	
    protected EList<Link> strongFrom;
      	
	protected SoftReference<EList<Link>> from;
    
	/**
	 * The cached value of the '{@link #getTo() <em>To</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
      	
    protected EList<Link> strongTo;
      	
	protected SoftReference<EList<Link>> to;
}
// vcontainer : ContainerType, bi:true, chan:true, list:false, change:true, kind:container reference
// from : EList<Link>, bi:true, chan:true, list:true, change:true, kind:reference list
// to : EList<Link>, bi:true, chan:true, list:true, change:true, kind:reference list
/*
* Neo4EMF inserted code -- end
*/




} //VertexImpl





