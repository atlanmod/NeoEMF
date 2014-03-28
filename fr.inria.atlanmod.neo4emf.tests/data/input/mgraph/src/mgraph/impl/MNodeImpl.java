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
package mgraph.impl;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.NeoEObjectWithInverseResolvingEList;

import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;

import java.lang.ref.SoftReference;

import java.util.Collection;

import mgraph.MEdge;
import mgraph.MGraph;
import mgraph.MNode;
import mgraph.MgraphPackage;

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
 * An implementation of the model object '<em><b>MNode</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link mgraph.impl.MNodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link mgraph.impl.MNodeImpl#getGraph <em>Graph</em>}</li>
 *   <li>{@link mgraph.impl.MNodeImpl#getFrom <em>From</em>}</li>
 *   <li>{@link mgraph.impl.MNodeImpl#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MNodeImpl extends Neo4emfObject implements MNode {

	 
	
	/**
	 * The cached value of the data structure {@link DataMNode <em>data</em> } 
	 * @generated
	 */
	 	protected DataMNode data;
	 
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MNodeImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 
	protected DataMNode getData(){
		if ( data == null || !(data instanceof DataMNode)){
			// TODO check that
			setLoadingOnDemand();
			data = new DataMNode();
			if (isLoaded()) {
				((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
			unsetLoadingOnDemand();
		}
		return (DataMNode) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MgraphPackage.Literals.MNODE;
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
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			MgraphPackage.MNODE__NAME,
			oldName, getData().name));
        }  
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MGraph getGraph() {
		try {
			setLoadingOnDemand();	
	  
		if (isLoaded() && eContainer() == null) {
			MGraph graph = (MGraph) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, MgraphPackage.MNODE__GRAPH);
			basicSetGraph(graph,null);}
			return (MGraph)eContainer();
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
	public NotificationChain basicSetGraph(MGraph newGraph, NotificationChain msgs) {
	
		msgs = eBasicSetContainer((InternalEObject)newGraph, MgraphPackage.MNODE__GRAPH, msgs);
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setGraph(MGraph newGraph) {
	
		
		// TEST
      	if (newGraph != eInternalContainer() || (eContainerFeatureID() != MgraphPackage.MNODE__GRAPH && newGraph != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newGraph)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if(eInternalContainer() != null) {
				if(newGraph == null && isLoaded()) {
					addChangelogRemoveEntry(eInternalContainer(), MgraphPackage.MNODE__GRAPH);
				}
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if(isLoaded()) {
				addChangelogEntry(newGraph, MgraphPackage.MNODE__GRAPH);
			}
			msgs = basicSetGraph(newGraph, msgs);
			if(newGraph != null) {
				msgs = ((InternalEObject)newGraph).eInverseAdd(this, MgraphPackage.MGRAPH__NODES, MGraph.class, msgs);
			}
			if(msgs != null) {
				msgs.dispatch();
			}	
		}
		else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, MgraphPackage.MNODE__GRAPH, newGraph, newGraph));
		}
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MEdge> getFrom() {
		try {
			setLoadingOnDemand();	
	   
		
			if (getData().from == null || getData().from.isEnqueued()){
			
	        
	        	EList<MEdge> newList = new NeoEObjectWithInverseResolvingEList<MEdge>(MEdge.class, this, MgraphPackage.MNODE__FROM, MgraphPackage.MEDGE__OUT_GOING);
	        	getData().from = new SoftReference<EList<MEdge>>(newList, garbagedData);
				if (isLoaded()) {
					((INeo4emfResource) this.eResource()).getOnDemand(this, MgraphPackage.MNODE__FROM);			
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
	public EList<MEdge> getTo() {
		try {
			setLoadingOnDemand();	
	   
		
			if (getData().to == null || getData().to.isEnqueued()){
			
	        
	        	EList<MEdge> newList = new NeoEObjectWithInverseResolvingEList<MEdge>(MEdge.class, this, MgraphPackage.MNODE__TO, MgraphPackage.MEDGE__IN_COMING);
	        	getData().to = new SoftReference<EList<MEdge>>(newList, garbagedData);
				if (isLoaded()) {
					((INeo4emfResource) this.eResource()).getOnDemand(this, MgraphPackage.MNODE__TO);			
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
			case MgraphPackage.MNODE__GRAPH:
				if (eInternalContainer() != null && !eInternalContainer().equals(otherEnd)) {
					msgs = eBasicRemoveFromContainer(msgs);
				}
				// Should be done before basicSet, otherwise the ChangeLog may be flushed and the 
				// isLoaded method would return true, and duplicate the AddLink entry
				if(isLoaded() && !isLoadingOnDemand() && !((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
					addChangelogEntry(otherEnd, MgraphPackage.MNODE__GRAPH);
				}
				return basicSetGraph((MGraph)otherEnd, msgs);
			case MgraphPackage.MNODE__FROM:
    			//if(((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
    			//	setLoadingOnDemand();
    			//}
    			if(isLoaded() && !isLoadingOnDemand()) {
    				addChangelogEntry(otherEnd, MgraphPackage.MNODE__FROM);
    			}
    			return ((InternalEList<InternalEObject>)(InternalEList<?>)getFrom()).basicAdd(otherEnd, msgs);
    			//if(((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
    			//	unsetLoadingOnDemand();
    			//}
    			//return null;
			case MgraphPackage.MNODE__TO:
    			//if(((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
    			//	setLoadingOnDemand();
    			//}
    			if(isLoaded() && !isLoadingOnDemand()) {
    				addChangelogEntry(otherEnd, MgraphPackage.MNODE__TO);
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
			case MgraphPackage.MNODE__GRAPH:
  				if(isLoaded()) {
  					addChangelogRemoveEntry(otherEnd,featureID);
  				}
				return basicSetGraph(null, msgs);
			case MgraphPackage.MNODE__FROM:
    			addChangelogRemoveEntry(otherEnd, MgraphPackage.MNODE__FROM);
				return ((InternalEList<?>)getFrom()).basicRemove(otherEnd, msgs);
			case MgraphPackage.MNODE__TO:
    			addChangelogRemoveEntry(otherEnd, MgraphPackage.MNODE__TO);
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
			case MgraphPackage.MNODE__GRAPH:
				return eInternalContainer().eInverseRemove(this, MgraphPackage.MGRAPH__NODES, MGraph.class, msgs);
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
			case MgraphPackage.MNODE__NAME:
				return getName();
			case MgraphPackage.MNODE__GRAPH:
				return getGraph();
			case MgraphPackage.MNODE__FROM:
				return getFrom();
			case MgraphPackage.MNODE__TO:
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
			case MgraphPackage.MNODE__NAME:
				setName((String)newValue);
				return;
			case MgraphPackage.MNODE__GRAPH:
				setGraph((MGraph)newValue);
				return;
			case MgraphPackage.MNODE__FROM:
				getFrom().clear();
				getFrom().addAll((Collection<? extends MEdge>)newValue);
				return;
			case MgraphPackage.MNODE__TO:
				getTo().clear();
				getTo().addAll((Collection<? extends MEdge>)newValue);
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
			case MgraphPackage.MNODE__NAME:
				setName(DataMNode.NAME_EDEFAULT);
				return;
			case MgraphPackage.MNODE__GRAPH:
				setGraph((MGraph)null);
				return;
			case MgraphPackage.MNODE__FROM:
				getFrom().clear();
				return;
			case MgraphPackage.MNODE__TO:
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
			case MgraphPackage.MNODE__NAME:
				return DataMNode.NAME_EDEFAULT == null ? getName() != null : !DataMNode.NAME_EDEFAULT.equals(getName());
			case MgraphPackage.MNODE__GRAPH:
				return getGraph() != null;
			case MgraphPackage.MNODE__FROM:
				return getFrom() != null && !getFrom().isEmpty();
			case MgraphPackage.MNODE__TO:
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




// data Class generation 
protected static  class DataMNode {


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
    protected EList<MEdge> strongFrom;
	protected SoftReference<EList<MEdge>> from;
// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getTo() <em>To</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
    protected EList<MEdge> strongTo;
	protected SoftReference<EList<MEdge>> to;
	/**
	 *Constructor of DataMNode
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataMNode() {
		
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
} //MNodeImpl
