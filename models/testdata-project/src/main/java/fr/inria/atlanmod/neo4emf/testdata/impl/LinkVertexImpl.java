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
import fr.inria.atlanmod.neo4emf.NeoEDataTypeEList;
import fr.inria.atlanmod.neo4emf.NeoEDataTypeUniqueEList;
import fr.inria.atlanmod.neo4emf.NeoEObjectWithInverseResolvingEList;

import fr.inria.atlanmod.neo4emf.testdata.Container;
import fr.inria.atlanmod.neo4emf.testdata.Link;
import fr.inria.atlanmod.neo4emf.testdata.LinkVertex;
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
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getVString <em>VString</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getATransientInteger <em>ATransient Integer</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getADate <em>ADate</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getAVolatileDate <em>AVolatile Date</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#isABoolean <em>ABoolean</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getAReal <em>AReal</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getAnIntegerArray <em>An Integer Array</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl#getARealArray <em>AReal Array</em>}</li>
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
			TestPackage.LINK_VERTEX__ATRANSIENT_INTEGER,
			oldATransientInteger, getData().aTransientInteger));
        }  
		this.addChangelogEntry(newATransientInteger, TestPackage.LINK_VERTEX__ATRANSIENT_INTEGER);
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
			TestPackage.LINK_VERTEX__ADATE,
			oldADate, getData().aDate));
        }  
		this.addChangelogEntry(newADate, TestPackage.LINK_VERTEX__ADATE);
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
			TestPackage.LINK_VERTEX__ABOOLEAN,
			oldABoolean, getData().aBoolean));
        }  
		this.addChangelogEntry(newABoolean, TestPackage.LINK_VERTEX__ABOOLEAN);
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
			TestPackage.LINK_VERTEX__AREAL,
			oldAReal, getData().aReal));
        }  
		this.addChangelogEntry(newAReal, TestPackage.LINK_VERTEX__AREAL);
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
	   
		
			if (getData().anIntegerArray == null || getData().anIntegerArray.isEnqueued()){
			
	        
	        	EList<BigInteger> newList = new NeoEDataTypeUniqueEList<BigInteger>(BigInteger.class, this, TestPackage.LINK_VERTEX__AN_INTEGER_ARRAY);
	        	getData().anIntegerArray = new SoftReference<EList<BigInteger>>(newList, garbagedData);
				if (isLoaded()) {
					((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__AN_INTEGER_ARRAY);			
	        	}
	        	else {
	        		// TODO find a better implementation
	        		getData().strongAnIntegerArray = newList;
	        	}
	        
	    	}
			return getData().anIntegerArray.get();
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
	   
		
			if (getData().aRealArray == null || getData().aRealArray.isEnqueued()){
			
	        
	        	EList<BigDecimal> newList = new NeoEDataTypeUniqueEList<BigDecimal>(BigDecimal.class, this, TestPackage.LINK_VERTEX__AREAL_ARRAY);
	        	getData().aRealArray = new SoftReference<EList<BigDecimal>>(newList, garbagedData);
				if (isLoaded()) {
					((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__AREAL_ARRAY);			
	        	}
	        	else {
	        		// TODO find a better implementation
	        		getData().strongARealArray = newList;
	        	}
	        
	    	}
			return getData().aRealArray.get();
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
	   
		
			if (getData().aStringArray == null || getData().aStringArray.isEnqueued()){
			
	        
	        	EList<String> newList = new NeoEDataTypeUniqueEList<String>(String.class, this, TestPackage.LINK_VERTEX__ASTRING_ARRAY);
	        	getData().aStringArray = new SoftReference<EList<String>>(newList, garbagedData);
				if (isLoaded()) {
					((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__ASTRING_ARRAY);			
	        	}
	        	else {
	        		// TODO find a better implementation
	        		getData().strongAStringArray = newList;
	        	}
	        
	    	}
			return getData().aStringArray.get();
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
	   
		
			if (getData().aNonUniqueArray == null || getData().aNonUniqueArray.isEnqueued()){
			
	        
	        	EList<BigDecimal> newList = new NeoEDataTypeEList<BigDecimal>(BigDecimal.class, this, TestPackage.LINK_VERTEX__ANON_UNIQUE_ARRAY);
	        	getData().aNonUniqueArray = new SoftReference<EList<BigDecimal>>(newList, garbagedData);
				if (isLoaded()) {
					((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__ANON_UNIQUE_ARRAY);			
	        	}
	        	else {
	        		// TODO find a better implementation
	        		getData().strongANonUniqueArray = newList;
	        	}
	        
	    	}
			return getData().aNonUniqueArray.get();
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
	   
		
			if (getData().aNonOrderedArray == null || getData().aNonOrderedArray.isEnqueued()){
			
	        
	        	EList<Boolean> newList = new NeoEDataTypeEList<Boolean>(Boolean.class, this, TestPackage.LINK_VERTEX__ANON_ORDERED_ARRAY);
	        	getData().aNonOrderedArray = new SoftReference<EList<Boolean>>(newList, garbagedData);
				if (isLoaded()) {
					((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__ANON_ORDERED_ARRAY);			
	        	}
	        	else {
	        		// TODO find a better implementation
	        		getData().strongANonOrderedArray = newList;
	        	}
	        
	    	}
			return getData().aNonOrderedArray.get();
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
	   
		
			if (getData().aTenStringArray == null || getData().aTenStringArray.isEnqueued()){
			
	        
	        	EList<String> newList = new NeoEDataTypeUniqueEList<String>(String.class, this, TestPackage.LINK_VERTEX__ATEN_STRING_ARRAY);
	        	getData().aTenStringArray = new SoftReference<EList<String>>(newList, garbagedData);
				if (isLoaded()) {
					((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__ATEN_STRING_ARRAY);			
	        	}
	        	else {
	        		// TODO find a better implementation
	        		getData().strongATenStringArray = newList;
	        	}
	        
	    	}
			return getData().aTenStringArray.get();
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
		getData().temperature = newTemperature == null ? DataLinkVertex.TEMPERATURE_EDEFAULT : newTemperature;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.LINK_VERTEX__TEMPERATURE,
			oldTemperature, getData().temperature));
        }  
		this.addChangelogEntry(newTemperature, TestPackage.LINK_VERTEX__TEMPERATURE);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container getVcontainer() {
		try {
			setLoadingOnDemand();	
	  
		if (isLoaded() && eContainer() == null) {
			Container vcontainer = (Container) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, TestPackage.LINK_VERTEX__VCONTAINER);
			basicSetVcontainer(vcontainer,null);}
			return (Container)eContainer();
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
	public NotificationChain basicSetVcontainer(Container newVcontainer, NotificationChain msgs) {
	
		msgs = eBasicSetContainer((InternalEObject)newVcontainer, TestPackage.LINK_VERTEX__VCONTAINER, msgs);
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setVcontainer(Container newVcontainer) {
	
		
	
		// TEST
      	if (newVcontainer != eInternalContainer() || (eContainerFeatureID() != TestPackage.LINK_VERTEX__VCONTAINER && newVcontainer != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newVcontainer)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if(eInternalContainer() != null) {
				if(newVcontainer == null && isLoaded()) {
					addChangelogRemoveEntry(eInternalContainer(), TestPackage.LINK_VERTEX__VCONTAINER);
				}
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if(isLoaded()) {
				addChangelogEntry(newVcontainer, TestPackage.LINK_VERTEX__VCONTAINER);
			}
			msgs = basicSetVcontainer(newVcontainer, msgs);
			if(newVcontainer != null) {
				msgs = ((InternalEObject)newVcontainer).eInverseAdd(this, TestPackage.CONTAINER__NODES, Container.class, msgs);
			}
			if(msgs != null) {
				msgs.dispatch();
			}	
		}
		else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, TestPackage.LINK_VERTEX__VCONTAINER, newVcontainer, newVcontainer));
		}
		this.addChangelogEntry(newVcontainer, TestPackage.LINK_VERTEX__VCONTAINER);
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
			
	        
	        	EList<Link> newList = new NeoEObjectWithInverseResolvingEList<Link>(Link.class, this, TestPackage.LINK_VERTEX__FROM, TestPackage.LINK__OUT_GOING);
	        	getData().from = new SoftReference<EList<Link>>(newList, garbagedData);
				if (isLoaded()) {
					((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__FROM);			
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
			
	        
	        	EList<Link> newList = new NeoEObjectWithInverseResolvingEList<Link>(Link.class, this, TestPackage.LINK_VERTEX__TO, TestPackage.LINK__IN_COMING);
	        	getData().to = new SoftReference<EList<Link>>(newList, garbagedData);
				if (isLoaded()) {
					((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__TO);			
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

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFirstName() {
		try {
			setLoadingOnDemand();	
	  		
			return getData().firstName;
		
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
	public void setFirstName(String newFirstName) {
	
		
	
		String oldFirstName = getData().firstName;
		getData().firstName = newFirstName;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.LINK_VERTEX__FIRST_NAME,
			oldFirstName, getData().firstName));
        }  
		this.addChangelogEntry(newFirstName, TestPackage.LINK_VERTEX__FIRST_NAME);
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
			setLoadingOnDemand();	
	  
			if (getData().link == null && isLoaded()) {
				((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__LINK);
			} else if(getData().link != null && getData().link.isEnqueued()) {
				assert isLoaded();
				((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__LINK);
			}
			if(getData().link == null) {
				return null; // avoid a get() call on a null pointer, maybe not enough
			}		
			return getData().link.get();
		
		} finally {
			unsetLoadingOnDemand();
		}
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX8-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Link basicGetLink() {
      	if(data != null) {
			return getData().link == null ? null : getData().link.get();
      	}
      	return null;
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
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.LINK_VERTEX__LINK,
			oldLink, getData().link));
        }  
		this.addChangelogEntry(newLink, TestPackage.LINK_VERTEX__LINK);
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
			setLoadingOnDemand();	
	  
			if (getData().vertex == null && isLoaded()) {
				((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__VERTEX);
			} else if(getData().vertex != null && getData().vertex.isEnqueued()) {
				assert isLoaded();
				((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK_VERTEX__VERTEX);
			}
			if(getData().vertex == null) {
				return null; // avoid a get() call on a null pointer, maybe not enough
			}		
			return getData().vertex.get();
		
		} finally {
			unsetLoadingOnDemand();
		}
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX8-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vertex basicGetVertex() {
      	if(data != null) {
			return getData().vertex == null ? null : getData().vertex.get();
      	}
      	return null;
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
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.LINK_VERTEX__VERTEX,
			oldVertex, getData().vertex));
        }  
		this.addChangelogEntry(newVertex, TestPackage.LINK_VERTEX__VERTEX);
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
				if (eInternalContainer() != null && !eInternalContainer().equals(otherEnd)) {
					msgs = eBasicRemoveFromContainer(msgs);
				}
				// Should be done before basicSet, otherwise the ChangeLog may be flushed and the 
				// isLoaded method would return true, and duplicate the AddLink entry
				if(isLoaded() && !isLoadingOnDemand() && !((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
					addChangelogEntry(otherEnd, TestPackage.LINK_VERTEX__VCONTAINER);
				}
				return basicSetVcontainer((Container)otherEnd, msgs);
			case TestPackage.LINK_VERTEX__FROM:
    			//if(((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
    			//	setLoadingOnDemand();
    			//}
    			if(isLoaded() && !isLoadingOnDemand()) {
    				addChangelogEntry(otherEnd, TestPackage.LINK_VERTEX__FROM);
    			}
    			return ((InternalEList<InternalEObject>)(InternalEList<?>)getFrom()).basicAdd(otherEnd, msgs);
    			//if(((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
    			//	unsetLoadingOnDemand();
    			//}
    			//return null;
			case TestPackage.LINK_VERTEX__TO:
    			//if(((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
    			//	setLoadingOnDemand();
    			//}
    			if(isLoaded() && !isLoadingOnDemand()) {
    				addChangelogEntry(otherEnd, TestPackage.LINK_VERTEX__TO);
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
			case TestPackage.LINK_VERTEX__VCONTAINER:
  				if(isLoaded()) {
  					addChangelogRemoveEntry(otherEnd,featureID);
  				}
				return basicSetVcontainer(null, msgs);
			case TestPackage.LINK_VERTEX__FROM:
    			addChangelogRemoveEntry(otherEnd, TestPackage.LINK_VERTEX__FROM);
				return ((InternalEList<?>)getFrom()).basicRemove(otherEnd, msgs);
			case TestPackage.LINK_VERTEX__TO:
    			addChangelogRemoveEntry(otherEnd, TestPackage.LINK_VERTEX__TO);
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
				return eInternalContainer().eInverseRemove(this, TestPackage.CONTAINER__NODES, Container.class, msgs);
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
			case TestPackage.LINK_VERTEX__VSTRING:
				return getVString();
			case TestPackage.LINK_VERTEX__ATRANSIENT_INTEGER:
				return getATransientInteger();
			case TestPackage.LINK_VERTEX__ADATE:
				return getADate();
			case TestPackage.LINK_VERTEX__AVOLATILE_DATE:
				return getAVolatileDate();
			case TestPackage.LINK_VERTEX__ABOOLEAN:
				return isABoolean();
			case TestPackage.LINK_VERTEX__AREAL:
				return getAReal();
			case TestPackage.LINK_VERTEX__AN_INTEGER_ARRAY:
				return getAnIntegerArray();
			case TestPackage.LINK_VERTEX__AREAL_ARRAY:
				return getARealArray();
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
			case TestPackage.LINK_VERTEX__ADATE:
				setADate((Date)newValue);
				return;
			case TestPackage.LINK_VERTEX__AVOLATILE_DATE:
				setAVolatileDate((Date)newValue);
				return;
			case TestPackage.LINK_VERTEX__ABOOLEAN:
				setABoolean((Boolean)newValue);
				return;
			case TestPackage.LINK_VERTEX__AREAL:
				setAReal((BigDecimal)newValue);
				return;
			case TestPackage.LINK_VERTEX__AN_INTEGER_ARRAY:
				getAnIntegerArray().clear();
				getAnIntegerArray().addAll((Collection<? extends BigInteger>)newValue);
				return;
			case TestPackage.LINK_VERTEX__AREAL_ARRAY:
				getARealArray().clear();
				getARealArray().addAll((Collection<? extends BigDecimal>)newValue);
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
				setVcontainer((Container)newValue);
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
			case TestPackage.LINK_VERTEX__ADATE:
				setADate(DataLinkVertex.ADATE_EDEFAULT);
				return;
			case TestPackage.LINK_VERTEX__AVOLATILE_DATE:
				setAVolatileDate(DataLinkVertex.AVOLATILE_DATE_EDEFAULT);
				return;
			case TestPackage.LINK_VERTEX__ABOOLEAN:
				setABoolean(DataLinkVertex.ABOOLEAN_EDEFAULT);
				return;
			case TestPackage.LINK_VERTEX__AREAL:
				setAReal(DataLinkVertex.AREAL_EDEFAULT);
				return;
			case TestPackage.LINK_VERTEX__AN_INTEGER_ARRAY:
				getAnIntegerArray().clear();
				return;
			case TestPackage.LINK_VERTEX__AREAL_ARRAY:
				getARealArray().clear();
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
				setVcontainer((Container)null);
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
			case TestPackage.LINK_VERTEX__VSTRING:
				return DataLinkVertex.VSTRING_EDEFAULT == null ? getVString() != null : !DataLinkVertex.VSTRING_EDEFAULT.equals(getVString());
			case TestPackage.LINK_VERTEX__ATRANSIENT_INTEGER:
				return DataLinkVertex.ATRANSIENT_INTEGER_EDEFAULT == null ? getATransientInteger() != null : !DataLinkVertex.ATRANSIENT_INTEGER_EDEFAULT.equals(getATransientInteger());
			case TestPackage.LINK_VERTEX__ADATE:
				return DataLinkVertex.ADATE_EDEFAULT == null ? getADate() != null : !DataLinkVertex.ADATE_EDEFAULT.equals(getADate());
			case TestPackage.LINK_VERTEX__AVOLATILE_DATE:
				return DataLinkVertex.AVOLATILE_DATE_EDEFAULT == null ? getAVolatileDate() != null : !DataLinkVertex.AVOLATILE_DATE_EDEFAULT.equals(getAVolatileDate());
			case TestPackage.LINK_VERTEX__ABOOLEAN:
				return isABoolean() != DataLinkVertex.ABOOLEAN_EDEFAULT;
			case TestPackage.LINK_VERTEX__AREAL:
				return DataLinkVertex.AREAL_EDEFAULT == null ? getAReal() != null : !DataLinkVertex.AREAL_EDEFAULT.equals(getAReal());
			case TestPackage.LINK_VERTEX__AN_INTEGER_ARRAY:
				return getAnIntegerArray() != null && !getAnIntegerArray().isEmpty();
			case TestPackage.LINK_VERTEX__AREAL_ARRAY:
				return getARealArray() != null && !getARealArray().isEmpty();
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
				case TestPackage.LINK_VERTEX__VSTRING: return TestPackage.VERTEX__VSTRING;
				case TestPackage.LINK_VERTEX__ATRANSIENT_INTEGER: return TestPackage.VERTEX__ATRANSIENT_INTEGER;
				case TestPackage.LINK_VERTEX__ADATE: return TestPackage.VERTEX__ADATE;
				case TestPackage.LINK_VERTEX__AVOLATILE_DATE: return TestPackage.VERTEX__AVOLATILE_DATE;
				case TestPackage.LINK_VERTEX__ABOOLEAN: return TestPackage.VERTEX__ABOOLEAN;
				case TestPackage.LINK_VERTEX__AREAL: return TestPackage.VERTEX__AREAL;
				case TestPackage.LINK_VERTEX__AN_INTEGER_ARRAY: return TestPackage.VERTEX__AN_INTEGER_ARRAY;
				case TestPackage.LINK_VERTEX__AREAL_ARRAY: return TestPackage.VERTEX__AREAL_ARRAY;
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
				case TestPackage.VERTEX__VSTRING: return TestPackage.LINK_VERTEX__VSTRING;
				case TestPackage.VERTEX__ATRANSIENT_INTEGER: return TestPackage.LINK_VERTEX__ATRANSIENT_INTEGER;
				case TestPackage.VERTEX__ADATE: return TestPackage.LINK_VERTEX__ADATE;
				case TestPackage.VERTEX__AVOLATILE_DATE: return TestPackage.LINK_VERTEX__AVOLATILE_DATE;
				case TestPackage.VERTEX__ABOOLEAN: return TestPackage.LINK_VERTEX__ABOOLEAN;
				case TestPackage.VERTEX__AREAL: return TestPackage.LINK_VERTEX__AREAL;
				case TestPackage.VERTEX__AN_INTEGER_ARRAY: return TestPackage.LINK_VERTEX__AN_INTEGER_ARRAY;
				case TestPackage.VERTEX__AREAL_ARRAY: return TestPackage.LINK_VERTEX__AREAL_ARRAY;
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

	/**
	 * @generated
	 */
	public void setDataStrongReferences() {
		if(data != null) {
			
				
			
				
			
				
			
		}
	}

	/**
	 * @generated
	 */
	public void releaseDataStrongReferences() {
		
			
		
			
		
			
		
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
	protected DataLinkVertex getData() {
		if ( data == null || !(data instanceof DataLinkVertex)){
			// TODO check that
			setLoadingOnDemand();
			data = new DataLinkVertex();
			if (isLoaded()) {
				((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
			unsetLoadingOnDemand();
			}
		return (DataLinkVertex) data;
	}

	/**
	*
	* @generated
	**/
	public void loadAllAttributesFrom(Node n) {
		this.data = new DataLinkVertex(this);
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
*   extends LinkImpl
*  2
*
*/
protected static class DataLinkVertex extends DataLink{


	/**
	 *Constructor of DataLinkVertex
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataLinkVertex() {
	}


	/**
	 * Constructor of DataLinkVertex
	 * Initializes multi-valued fields, if any.
	 *
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataLinkVertex(LinkVertexImpl outer) {
		 super(outer); 
		anIntegerArray = new EDataTypeUniqueEList<BigInteger>(BigInteger.class, outer, TestPackage.LINK_VERTEX__AN_INTEGER_ARRAY);
		aRealArray = new EDataTypeUniqueEList<BigDecimal>(BigDecimal.class, outer, TestPackage.LINK_VERTEX__AREAL_ARRAY);
		aStringArray = new EDataTypeUniqueEList<String>(String.class, outer, TestPackage.LINK_VERTEX__ASTRING_ARRAY);
		aNonUniqueArray = new EDataTypeEList<BigDecimal>(BigDecimal.class, outer, TestPackage.LINK_VERTEX__ANON_UNIQUE_ARRAY);
		aNonOrderedArray = new EDataTypeEList<Boolean>(Boolean.class, outer, TestPackage.LINK_VERTEX__ANON_ORDERED_ARRAY);
		aTenStringArray = new EDataTypeUniqueEList<String>(String.class, outer, TestPackage.LINK_VERTEX__ATEN_STRING_ARRAY);
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
    protected EList<BigInteger> strongAnIntegerArray;
	protected SoftReference<EList<BigInteger>> anIntegerArray;
    
	/**
	 * The cached value of the '{@link #getARealArray() <em>AReal Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getARealArray()
	 * @generated
	 * @ordered
	 */
    protected EList<BigDecimal> strongARealArray;
	protected SoftReference<EList<BigDecimal>> aRealArray;
    
	/**
	 * The cached value of the '{@link #getAStringArray() <em>AString Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAStringArray()
	 * @generated
	 * @ordered
	 */
    protected EList<String> strongAStringArray;
	protected SoftReference<EList<String>> aStringArray;
    
	/**
	 * The cached value of the '{@link #getANonUniqueArray() <em>ANon Unique Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getANonUniqueArray()
	 * @generated
	 * @ordered
	 */
    protected EList<BigDecimal> strongANonUniqueArray;
	protected SoftReference<EList<BigDecimal>> aNonUniqueArray;
    
	/**
	 * The cached value of the '{@link #getANonOrderedArray() <em>ANon Ordered Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getANonOrderedArray()
	 * @generated
	 * @ordered
	 */
    protected EList<Boolean> strongANonOrderedArray;
	protected SoftReference<EList<Boolean>> aNonOrderedArray;
    
	/**
	 * The cached value of the '{@link #getATenStringArray() <em>ATen String Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getATenStringArray()
	 * @generated
	 * @ordered
	 */
    protected EList<String> strongATenStringArray;
	protected SoftReference<EList<String>> aTenStringArray;
    
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

    
	/**
	 * The cached value of the '{@link #getLink() <em>Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLink()
	 * @generated
	 * @ordered
	 */
	protected SoftReference<Link> link;
    
	/**
	 * The cached value of the '{@link #getVertex() <em>Vertex</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVertex()
	 * @generated
	 * @ordered
	 */
	protected SoftReference<Vertex> vertex;

	
		
	/**
	 *Constructor of DataLinkVertex%>
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Link }
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
		result.append(", firstName: ");
		result.append(firstName);
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
		aux = n.getProperty("firstName", null);
		if (aux != null) {
			firstName = (String) aux;
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
		this.savefirstNameTo(n);
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
			
			case TestPackage.LINK_VERTEX__ATRANSIENT_INTEGER:
				this.saveaTransientIntegerTo(n);
				return;
			case TestPackage.LINK_VERTEX__ADATE:
				this.saveaDateTo(n);
				return;
			case TestPackage.LINK_VERTEX__ABOOLEAN:
				this.saveaBooleanTo(n);
				return;
			case TestPackage.LINK_VERTEX__AREAL:
				this.saveaRealTo(n);
				return;
			case TestPackage.LINK_VERTEX__FIRST_NAME:
				this.savefirstNameTo(n);
				return;
			case TestPackage.LINK_VERTEX__AN_INTEGER_ARRAY:
				this.saveanIntegerArrayTo(n);
				return;
			case TestPackage.LINK_VERTEX__AREAL_ARRAY:
				this.saveaRealArrayTo(n);
				return;
			case TestPackage.LINK_VERTEX__ASTRING_ARRAY:
				this.saveaStringArrayTo(n);
				return;
			case TestPackage.LINK_VERTEX__ANON_UNIQUE_ARRAY:
				this.saveaNonUniqueArrayTo(n);
				return;
			case TestPackage.LINK_VERTEX__ANON_ORDERED_ARRAY:
				this.saveaNonOrderedArrayTo(n);
				return;
			case TestPackage.LINK_VERTEX__ATEN_STRING_ARRAY:
				this.saveaTenStringArrayTo(n);
				return;
			case TestPackage.LINK_VERTEX__TEMPERATURE:
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
	private void savefirstNameTo(Node n) {
	
		if (firstName != null) n.setProperty("firstName", firstName);
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
	
protected static class LinkVertexReferences  extends DataLink {
    
    
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
	 * The cached value of the '{@link #getLink() <em>Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLink()
	 * @generated
	 * @ordered
	 */
	protected SoftReference<Link> link;
    
	/**
	 * The cached value of the '{@link #getVertex() <em>Vertex</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVertex()
	 * @generated
	 * @ordered
	 */
	protected SoftReference<Vertex> vertex;
}
// vcontainer : Container, bi:true, chan:true, list:false, change:true, kind:container reference
// from : EList<Link>, bi:true, chan:true, list:true, change:true, kind:reference list
// to : EList<Link>, bi:true, chan:true, list:true, change:true, kind:reference list
// link : Link, bi:false, chan:true, list:false, change:true, kind:reference
// vertex : Vertex, bi:false, chan:true, list:false, change:true, kind:reference
/*
* Neo4EMF inserted code -- end
*/




} //LinkVertexImpl
