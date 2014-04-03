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

import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;

import java.lang.ref.SoftReference;

import mgraph.MEdge;
import mgraph.MGraph;
import mgraph.MNode;
import mgraph.MgraphPackage;

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
 * An implementation of the model object '<em><b>MEdge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link mgraph.impl.MEdgeImpl#getName <em>Name</em>}</li>
 *   <li>{@link mgraph.impl.MEdgeImpl#getInComing <em>In Coming</em>}</li>
 *   <li>{@link mgraph.impl.MEdgeImpl#getOutGoing <em>Out Going</em>}</li>
 *   <li>{@link mgraph.impl.MEdgeImpl#getGraph <em>Graph</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MEdgeImpl extends Neo4emfObject implements MEdge {

	 
	
	/**
	 * The cached value of the data structure {@link DataMEdge <em>data</em> } 
	 * @generated
	 */
	 	protected DataMEdge data;
	 
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MEdgeImpl() {
		super();
		
	}

	
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MgraphPackage.Literals.MEDGE;
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
			MgraphPackage.MEDGE__NAME,
			oldName, getData().name));
        }  
  if(isLoaded()) {
		  this.addChangelogEntry(newName, MgraphPackage.MEDGE__NAME);
    }
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MNode getInComing() {
		try {
			setLoadingOnDemand();	
	  
			if (getData().inComing == null && isLoaded()) {
				((INeo4emfResource) this.eResource()).getOnDemand(this, MgraphPackage.MEDGE__IN_COMING);
			} else if(getData().inComing != null && getData().inComing.isEnqueued()) {
				assert isLoaded();
				((INeo4emfResource) this.eResource()).getOnDemand(this, MgraphPackage.MEDGE__IN_COMING);
			}
			if(getData().inComing == null) {
				return null; // avoid a get() call on a null pointer, maybe not enough
			}		
			return getData().inComing.get();
		
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
	public MNode basicGetInComing() {
      	if(data != null) {
			return getData().inComing == null ? null : getData().inComing.get();
      	}
      	return null;
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInComing(MNode newInComing, NotificationChain msgs) {
	
		
	
      	MNode oldInComing = null;
      	if(getData().inComing != null) {
      		oldInComing = getData().inComing.get();
      	}
      	getData().inComing = new SoftReference<MNode>(newInComing, garbagedData);
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MgraphPackage.MEDGE__IN_COMING, oldInComing, newInComing);
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
	public void setInComing(MNode newInComing) {
	
		
        // setGenFeature.override.javajetinc (bidirectionnal handling)
        // TEST
		// [2-04]
//        if(newInComing.eResource() == null) {
        	// Reproduce the error in standard EMF (when trying to add a non-containment reference
        	// on an EObject which is not in a resource)
//        	throw new RuntimeException("The object '" + newInComing.toString() + "' is not container in a resource");
//        }
		// [2-04]
        // TODO Check that (always true ? compare EObject with SoftReference)
        if(newInComing != getData().inComing) {
        	NotificationChain msgs = null;
        	if(getData().inComing != null || (!isLoadingOnDemand() && getInComing() != null)) {
        		
        		msgs = ((InternalEObject) getInComing()).eInverseRemove(this, MgraphPackage.MNODE__TO, MNode.class, msgs);
        		addChangelogRemoveEntry(getInComing(), MgraphPackage.MEDGE__IN_COMING);
        		
        	}
        	if(newInComing != null) {
        		
				msgs = ((InternalEObject) newInComing).eInverseAdd(this, MgraphPackage.MNODE__TO, MNode.class, msgs);
        		
        	}
        	if(isLoaded()) {
        		addChangelogEntry(newInComing,MgraphPackage.MEDGE__IN_COMING);
        	}
        	msgs = basicSetInComing(newInComing,msgs);
        	if(msgs != null) {
        		msgs.dispatch();
        	}
        }
        // TEST
		//if (newInComing != getData().inComing)
		//{
		//	NotificationChain msgs = null;
		//	if (getData().inComing != null)
        //
		//		msgs = ((InternalEObject) getData().inComing).eInverseRemove(this, MgraphPackage.MNODE__TO, MNode.class, msgs);
		//	if (newInComing != null)
		//		msgs = ((InternalEObject)newInComing).eInverseAdd(this, MgraphPackage.MNODE__TO, MNode.class, msgs);
        //
		//	msgs = basicSetInComing(newInComing, msgs);
		//	if (msgs != null) msgs.dispatch();
		//}
		else if (eNotificationRequired()) {
				eNotify(new ENotificationImpl(this, Notification.SET, MgraphPackage.MEDGE__IN_COMING, newInComing, newInComing));
          	}
  if(isLoaded()) {
		  this.addChangelogEntry(newInComing, MgraphPackage.MEDGE__IN_COMING);
    }
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MNode getOutGoing() {
		try {
			setLoadingOnDemand();	
	  
			if (getData().outGoing == null && isLoaded()) {
				((INeo4emfResource) this.eResource()).getOnDemand(this, MgraphPackage.MEDGE__OUT_GOING);
			} else if(getData().outGoing != null && getData().outGoing.isEnqueued()) {
				assert isLoaded();
				((INeo4emfResource) this.eResource()).getOnDemand(this, MgraphPackage.MEDGE__OUT_GOING);
			}
			if(getData().outGoing == null) {
				return null; // avoid a get() call on a null pointer, maybe not enough
			}		
			return getData().outGoing.get();
		
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
	public MNode basicGetOutGoing() {
      	if(data != null) {
			return getData().outGoing == null ? null : getData().outGoing.get();
      	}
      	return null;
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutGoing(MNode newOutGoing, NotificationChain msgs) {
	
		
	
      	MNode oldOutGoing = null;
      	if(getData().outGoing != null) {
      		oldOutGoing = getData().outGoing.get();
      	}
      	getData().outGoing = new SoftReference<MNode>(newOutGoing, garbagedData);
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MgraphPackage.MEDGE__OUT_GOING, oldOutGoing, newOutGoing);
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
	public void setOutGoing(MNode newOutGoing) {
	
		
        // setGenFeature.override.javajetinc (bidirectionnal handling)
        // TEST
		// [2-04]
//        if(newOutGoing.eResource() == null) {
        	// Reproduce the error in standard EMF (when trying to add a non-containment reference
        	// on an EObject which is not in a resource)
//        	throw new RuntimeException("The object '" + newOutGoing.toString() + "' is not container in a resource");
//        }
		// [2-04]
        // TODO Check that (always true ? compare EObject with SoftReference)
        if(newOutGoing != getData().outGoing) {
        	NotificationChain msgs = null;
        	if(getData().outGoing != null || (!isLoadingOnDemand() && getOutGoing() != null)) {
        		
        		msgs = ((InternalEObject) getOutGoing()).eInverseRemove(this, MgraphPackage.MNODE__FROM, MNode.class, msgs);
        		addChangelogRemoveEntry(getOutGoing(), MgraphPackage.MEDGE__OUT_GOING);
        		
        	}
        	if(newOutGoing != null) {
        		
				msgs = ((InternalEObject) newOutGoing).eInverseAdd(this, MgraphPackage.MNODE__FROM, MNode.class, msgs);
        		
        	}
        	if(isLoaded()) {
        		addChangelogEntry(newOutGoing,MgraphPackage.MEDGE__OUT_GOING);
        	}
        	msgs = basicSetOutGoing(newOutGoing,msgs);
        	if(msgs != null) {
        		msgs.dispatch();
        	}
        }
        // TEST
		//if (newOutGoing != getData().outGoing)
		//{
		//	NotificationChain msgs = null;
		//	if (getData().outGoing != null)
        //
		//		msgs = ((InternalEObject) getData().outGoing).eInverseRemove(this, MgraphPackage.MNODE__FROM, MNode.class, msgs);
		//	if (newOutGoing != null)
		//		msgs = ((InternalEObject)newOutGoing).eInverseAdd(this, MgraphPackage.MNODE__FROM, MNode.class, msgs);
        //
		//	msgs = basicSetOutGoing(newOutGoing, msgs);
		//	if (msgs != null) msgs.dispatch();
		//}
		else if (eNotificationRequired()) {
				eNotify(new ENotificationImpl(this, Notification.SET, MgraphPackage.MEDGE__OUT_GOING, newOutGoing, newOutGoing));
          	}
  if(isLoaded()) {
		  this.addChangelogEntry(newOutGoing, MgraphPackage.MEDGE__OUT_GOING);
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
			MGraph graph = (MGraph) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, MgraphPackage.MEDGE__GRAPH);
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
	
		msgs = eBasicSetContainer((InternalEObject)newGraph, MgraphPackage.MEDGE__GRAPH, msgs);
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
      	if (newGraph != eInternalContainer() || (eContainerFeatureID() != MgraphPackage.MEDGE__GRAPH && newGraph != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newGraph)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if(eInternalContainer() != null) {
				if(newGraph == null && isLoaded()) {
					addChangelogRemoveEntry(eInternalContainer(), MgraphPackage.MEDGE__GRAPH);
				}
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if(isLoaded()) {
				addChangelogEntry(newGraph, MgraphPackage.MEDGE__GRAPH);
			}
			msgs = basicSetGraph(newGraph, msgs);
			if(newGraph != null) {
				msgs = ((InternalEObject)newGraph).eInverseAdd(this, MgraphPackage.MGRAPH__EDGES, MGraph.class, msgs);
			}
			if(msgs != null) {
				msgs.dispatch();
			}	
		}
		else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, MgraphPackage.MEDGE__GRAPH, newGraph, newGraph));
		}
  if(isLoaded()) {
		  this.addChangelogEntry(newGraph, MgraphPackage.MEDGE__GRAPH);
    }
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
			case MgraphPackage.MEDGE__IN_COMING:
				if (getData().inComing != null && !getData().inComing.get().equals(otherEnd))
					msgs = ((InternalEObject)getData().inComing.get()).eInverseRemove(this, MgraphPackage.MNODE__TO, MNode.class, msgs);
				return basicSetInComing((MNode)otherEnd, msgs);
			case MgraphPackage.MEDGE__OUT_GOING:
				if (getData().outGoing != null && !getData().outGoing.get().equals(otherEnd))
					msgs = ((InternalEObject)getData().outGoing.get()).eInverseRemove(this, MgraphPackage.MNODE__FROM, MNode.class, msgs);
				return basicSetOutGoing((MNode)otherEnd, msgs);
			case MgraphPackage.MEDGE__GRAPH:
				if (eInternalContainer() != null && !eInternalContainer().equals(otherEnd)) {
					msgs = eBasicRemoveFromContainer(msgs);
				}
				// Should be done before basicSet, otherwise the ChangeLog may be flushed and the 
				// isLoaded method would return true, and duplicate the AddLink entry
				if(isLoaded() && !isLoadingOnDemand() && !((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
					addChangelogEntry(otherEnd, MgraphPackage.MEDGE__GRAPH);
				}
				return basicSetGraph((MGraph)otherEnd, msgs);
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
			case MgraphPackage.MEDGE__IN_COMING:
  				if(isLoaded()) {
  					addChangelogRemoveEntry(otherEnd,featureID);
  				}
				return basicSetInComing(null, msgs);
			case MgraphPackage.MEDGE__OUT_GOING:
  				if(isLoaded()) {
  					addChangelogRemoveEntry(otherEnd,featureID);
  				}
				return basicSetOutGoing(null, msgs);
			case MgraphPackage.MEDGE__GRAPH:
  				if(isLoaded()) {
  					addChangelogRemoveEntry(otherEnd,featureID);
  				}
				return basicSetGraph(null, msgs);
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
			case MgraphPackage.MEDGE__GRAPH:
				return eInternalContainer().eInverseRemove(this, MgraphPackage.MGRAPH__EDGES, MGraph.class, msgs);
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
			case MgraphPackage.MEDGE__NAME:
				return getName();
			case MgraphPackage.MEDGE__IN_COMING:
				if (resolve) return getInComing();
				return basicGetInComing();
			case MgraphPackage.MEDGE__OUT_GOING:
				if (resolve) return getOutGoing();
				return basicGetOutGoing();
			case MgraphPackage.MEDGE__GRAPH:
				return getGraph();
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
			case MgraphPackage.MEDGE__NAME:
				setName((String)newValue);
				return;
			case MgraphPackage.MEDGE__IN_COMING:
				setInComing((MNode)newValue);
				return;
			case MgraphPackage.MEDGE__OUT_GOING:
				setOutGoing((MNode)newValue);
				return;
			case MgraphPackage.MEDGE__GRAPH:
				setGraph((MGraph)newValue);
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
			case MgraphPackage.MEDGE__NAME:
				setName(DataMEdge.NAME_EDEFAULT);
				return;
			case MgraphPackage.MEDGE__IN_COMING:
				setInComing((MNode)null);
				return;
			case MgraphPackage.MEDGE__OUT_GOING:
				setOutGoing((MNode)null);
				return;
			case MgraphPackage.MEDGE__GRAPH:
				setGraph((MGraph)null);
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
			case MgraphPackage.MEDGE__NAME:
				return DataMEdge.NAME_EDEFAULT == null ? getName() != null : !DataMEdge.NAME_EDEFAULT.equals(getName());
			case MgraphPackage.MEDGE__IN_COMING:
				return getInComing() != null;
			case MgraphPackage.MEDGE__OUT_GOING:
				return getOutGoing() != null;
			case MgraphPackage.MEDGE__GRAPH:
				return getGraph() != null;
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
	 
	protected DataMEdge getData() {
		if ( data == null || !(data instanceof DataMEdge)){
			// TODO check that
			setLoadingOnDemand();
			data = new DataMEdge();
			if (isLoaded()) {
				((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
			unsetLoadingOnDemand();
			}
		return (DataMEdge) data;
	}

	/**
	*
	* @generated
	**/
	public void loadAllAttributesFrom(Node n) {
		this.data = new DataMEdge(this);
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
protected static class DataMEdge{


	/**
	 *Constructor of DataMEdge
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataMEdge() {
	}


	/**
	 * Constructor of DataMEdge
	 * Initializes multi-valued fields, if any.
	 *
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataMEdge(MEdgeImpl outer) {
		
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
	 * The cached value of the '{@link #getInComing() <em>In Coming</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInComing()
	 * @generated
	 * @ordered
	 */
      	
	protected SoftReference<MNode> inComing;
    
	/**
	 * The cached value of the '{@link #getOutGoing() <em>Out Going</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutGoing()
	 * @generated
	 * @ordered
	 */
      	
	protected SoftReference<MNode> outGoing;
    

	
		
	
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
			
			case MgraphPackage.MEDGE__NAME:
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
	
protected static class MEdgeReferences  {
    
	/**
	 * The cached value of the '{@link #getInComing() <em>In Coming</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInComing()
	 * @generated
	 * @ordered
	 */
      	
	protected SoftReference<MNode> inComing;
    
	/**
	 * The cached value of the '{@link #getOutGoing() <em>Out Going</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutGoing()
	 * @generated
	 * @ordered
	 */
      	
	protected SoftReference<MNode> outGoing;
    
}
// inComing : MNode, bi:true, chan:true, list:false, change:true, kind:reference
// outGoing : MNode, bi:true, chan:true, list:false, change:true, kind:reference
// graph : MGraph, bi:true, chan:true, list:false, change:true, kind:container reference
/*
* Neo4EMF inserted code -- end
*/




} //MEdgeImpl
