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
import fr.inria.atlanmod.neo4emf.NeoEObjectContainmentWithInverseEList;

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
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.neo4j.graphdb.Node;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MGraph</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link mgraph.impl.MGraphImpl#getName <em>Name</em>}</li>
 *   <li>{@link mgraph.impl.MGraphImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link mgraph.impl.MGraphImpl#getEdges <em>Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MGraphImpl extends Neo4emfObject implements MGraph {

	 
	
	/**
	 * The cached value of the data structure {@link DataMGraph <em>data</em> } 
	 * @generated
	 */
	 	protected DataMGraph data;
	 
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MGraphImpl() {
		super();
		
	}

	
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MgraphPackage.Literals.MGRAPH;
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
			MgraphPackage.MGRAPH__NAME,
			oldName, getData().name));
        }  
  if(isLoaded()) {
		  this.addChangelogEntry(newName, MgraphPackage.MGRAPH__NAME);
    }
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MNode> getNodes() {
		try {
			setLoadingOnDemand();	
	   
	  	
			
				if (getData().nodes == null || getData().nodes.isEnqueued()){
				
		        
		        	EList<MNode> newList = new NeoEObjectContainmentWithInverseEList<MNode>(MNode.class, this, MgraphPackage.MGRAPH__NODES, MgraphPackage.MNODE__GRAPH);
		        	getData().nodes = new SoftReference<EList<MNode>>(newList, garbagedData);
					if (isLoaded()) {
						((INeo4emfResource) this.eResource()).getOnDemand(this, MgraphPackage.MGRAPH__NODES);			
		        	}
		        	else {
		        		// TODO find a better implementation
		        		getData().strongNodes = newList;
		        	}
		        
		    	}
				return getData().nodes.get();
      	
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
	public EList<MEdge> getEdges() {
		try {
			setLoadingOnDemand();	
	   
	  	
			
				if (getData().edges == null || getData().edges.isEnqueued()){
				
		        
		        	EList<MEdge> newList = new NeoEObjectContainmentWithInverseEList<MEdge>(MEdge.class, this, MgraphPackage.MGRAPH__EDGES, MgraphPackage.MEDGE__GRAPH);
		        	getData().edges = new SoftReference<EList<MEdge>>(newList, garbagedData);
					if (isLoaded()) {
						((INeo4emfResource) this.eResource()).getOnDemand(this, MgraphPackage.MGRAPH__EDGES);			
		        	}
		        	else {
		        		// TODO find a better implementation
		        		getData().strongEdges = newList;
		        	}
		        
		    	}
				return getData().edges.get();
      	
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
			case MgraphPackage.MGRAPH__NODES:
    			//if(((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
    			//	setLoadingOnDemand();
    			//}
    			if(isLoaded() && !isLoadingOnDemand()) {
    				addChangelogEntry(otherEnd, MgraphPackage.MGRAPH__NODES);
    			}
    			return ((InternalEList<InternalEObject>)(InternalEList<?>)getNodes()).basicAdd(otherEnd, msgs);
    			//if(((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
    			//	unsetLoadingOnDemand();
    			//}
    			//return null;
			case MgraphPackage.MGRAPH__EDGES:
    			//if(((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
    			//	setLoadingOnDemand();
    			//}
    			if(isLoaded() && !isLoadingOnDemand()) {
    				addChangelogEntry(otherEnd, MgraphPackage.MGRAPH__EDGES);
    			}
    			return ((InternalEList<InternalEObject>)(InternalEList<?>)getEdges()).basicAdd(otherEnd, msgs);
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
			case MgraphPackage.MGRAPH__NODES:
    			addChangelogRemoveEntry(otherEnd, MgraphPackage.MGRAPH__NODES);
				return ((InternalEList<?>)getNodes()).basicRemove(otherEnd, msgs);
			case MgraphPackage.MGRAPH__EDGES:
    			addChangelogRemoveEntry(otherEnd, MgraphPackage.MGRAPH__EDGES);
				return ((InternalEList<?>)getEdges()).basicRemove(otherEnd, msgs);
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
			case MgraphPackage.MGRAPH__NAME:
				return getName();
			case MgraphPackage.MGRAPH__NODES:
				return getNodes();
			case MgraphPackage.MGRAPH__EDGES:
				return getEdges();
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
			case MgraphPackage.MGRAPH__NAME:
				setName((String)newValue);
				return;
			case MgraphPackage.MGRAPH__NODES:
				getNodes().clear();
				getNodes().addAll((Collection<? extends MNode>)newValue);
				return;
			case MgraphPackage.MGRAPH__EDGES:
				getEdges().clear();
				getEdges().addAll((Collection<? extends MEdge>)newValue);
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
			case MgraphPackage.MGRAPH__NAME:
				setName(DataMGraph.NAME_EDEFAULT);
				return;
			case MgraphPackage.MGRAPH__NODES:
				getNodes().clear();
				return;
			case MgraphPackage.MGRAPH__EDGES:
				getEdges().clear();
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
			case MgraphPackage.MGRAPH__NAME:
				return DataMGraph.NAME_EDEFAULT == null ? getName() != null : !DataMGraph.NAME_EDEFAULT.equals(getName());
			case MgraphPackage.MGRAPH__NODES:
				return getNodes() != null && !getNodes().isEmpty();
			case MgraphPackage.MGRAPH__EDGES:
				return getEdges() != null && !getEdges().isEmpty();
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
			
				
			
				
			if(getData().strongNodes != null) {
				getData().strongNodes = getData().nodes.get();
			}
				
			
				
			if(getData().strongEdges != null) {
				getData().strongEdges = getData().edges.get();
			}
				
			
		}
	}

	/**
	 * @generated
	 */
	public void releaseDataStrongReferences() {
		
			
		
			
		getData().strongNodes = null;
			
		
			
		getData().strongEdges = null;
			
		
	}
/*
* Neo4EMF inserted code -- begin
*/
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 
	protected DataMGraph getData() {
		if ( data == null || !(data instanceof DataMGraph)){
			// TODO check that
			setLoadingOnDemand();
			data = new DataMGraph();
			if (isLoaded()) {
				((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
			unsetLoadingOnDemand();
			}
		return (DataMGraph) data;
	}

	/**
	*
	* @generated
	**/
	public void loadAllAttributesFrom(Node n) {
		this.data = new DataMGraph(this);
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
*   extends Neo4emfObject
*  0
*
*/
protected static class DataMGraph{


	/**
	 *Constructor of DataMGraph
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataMGraph() {
	}


	/**
	 * Constructor of DataMGraph
	 * Initializes multi-valued fields, if any.
	 *
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataMGraph(MGraphImpl outer) {
		
	}


    
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

    
	/**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
      	
    protected EList<MNode> strongNodes;
      	
	protected SoftReference<EList<MNode>> nodes;
    
	/**
	 * The cached value of the '{@link #getEdges() <em>Edges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdges()
	 * @generated
	 * @ordered
	 */
      	
    protected EList<MEdge> strongEdges;
      	
	protected SoftReference<EList<MEdge>> edges;

	
		
	
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
		

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void loadAllAttributesFrom(Node n) {
		Object aux;
		aux = n.getProperty("name", null);
		if (aux != null) {
			name = (String) aux;
		} 
	}


	/**
 	* <!-- begin-user-doc -->
 	* <!-- end-user-doc -->
 	* @generated
 	*/
	public void saveAllAttributesTo(Node n) {
		
		
		this.savenameTo(n);
	} // saveTo()
	
	/**
 	* <!-- begin-user-doc -->
 	* <!-- end-user-doc -->
 	* @generated
 	*/
	public void saveAttributeTo(int featureID, Node n) {
		switch (featureID) {
			
			case MgraphPackage.MGRAPH__NAME:
				this.savenameTo(n);
				return;
		} // switch
	} // saveAttributeTo()
	

	/*
	*
	*/
	private void savenameTo(Node n) {
	
		if (name != null) n.setProperty("name", name);
	} 
	



}//end attribute class
	
protected static class MGraphReferences  {
    
	/**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
      	
    protected EList<MNode> strongNodes;
      	
	protected SoftReference<EList<MNode>> nodes;
    
	/**
	 * The cached value of the '{@link #getEdges() <em>Edges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdges()
	 * @generated
	 * @ordered
	 */
      	
    protected EList<MEdge> strongEdges;
      	
	protected SoftReference<EList<MEdge>> edges;
}
// nodes : EList<MNode>, bi:true, chan:true, list:true, change:true, kind:containment reference list
// edges : EList<MEdge>, bi:true, chan:true, list:true, change:true, kind:containment reference list
/*
* Neo4EMF inserted code -- end
*/




} //MGraphImpl
