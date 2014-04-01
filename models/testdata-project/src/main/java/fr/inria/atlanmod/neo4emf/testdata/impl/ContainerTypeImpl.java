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
import fr.inria.atlanmod.neo4emf.NeoEObjectContainmentWithInverseEList;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;
import fr.inria.atlanmod.neo4emf.testdata.ContainerType;
import fr.inria.atlanmod.neo4emf.testdata.Link;
import fr.inria.atlanmod.neo4emf.testdata.TestPackage;
import fr.inria.atlanmod.neo4emf.testdata.Vertex;
import java.lang.ref.SoftReference;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.neo4j.graphdb.Node;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.ContainerTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.ContainerTypeImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.ContainerTypeImpl#getLinks <em>Links</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContainerTypeImpl extends Neo4emfObject implements ContainerType {

	 
	
	/**
	 * The cached value of the data structure {@link DataContainerType <em>data</em> } 
	 * @generated
	 */
	 	protected DataContainerType data;
	 
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainerTypeImpl() {
		super();
		
	}

	
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestPackage.Literals.CONTAINER_TYPE;
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
			TestPackage.CONTAINER_TYPE__NAME,
			oldName, getData().name));
        }  
  if(isLoaded()) {
		  this.addChangelogEntry(newName, TestPackage.CONTAINER_TYPE__NAME);
    }
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Vertex> getNodes() {
		try {
			setLoadingOnDemand();	
	   
	  	
			
				if (getData().nodes == null || getData().nodes.isEnqueued()){
				
		        
		        	EList<Vertex> newList = new NeoEObjectContainmentWithInverseEList<Vertex>(Vertex.class, this, TestPackage.CONTAINER_TYPE__NODES, TestPackage.VERTEX__VCONTAINER);
		        	getData().nodes = new SoftReference<EList<Vertex>>(newList, garbagedData);
					if (isLoaded()) {
						((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.CONTAINER_TYPE__NODES);			
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
	public EList<Link> getLinks() {
		try {
			setLoadingOnDemand();	
	   
	  	
			
				if (getData().links == null || getData().links.isEnqueued()){
				
		        
		        	EList<Link> newList = new NeoEObjectContainmentWithInverseEList<Link>(Link.class, this, TestPackage.CONTAINER_TYPE__LINKS, TestPackage.LINK__CONTAINER);
		        	getData().links = new SoftReference<EList<Link>>(newList, garbagedData);
					if (isLoaded()) {
						((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.CONTAINER_TYPE__LINKS);			
		        	}
		        	else {
		        		// TODO find a better implementation
		        		getData().strongLinks = newList;
		        	}
		        
		    	}
				return getData().links.get();
      	
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
			case TestPackage.CONTAINER_TYPE__NODES:
    			//if(((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
    			//	setLoadingOnDemand();
    			//}
    			if(isLoaded() && !isLoadingOnDemand()) {
    				addChangelogEntry(otherEnd, TestPackage.CONTAINER_TYPE__NODES);
    			}
    			return ((InternalEList<InternalEObject>)(InternalEList<?>)getNodes()).basicAdd(otherEnd, msgs);
    			//if(((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
    			//	unsetLoadingOnDemand();
    			//}
    			//return null;
			case TestPackage.CONTAINER_TYPE__LINKS:
    			//if(((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
    			//	setLoadingOnDemand();
    			//}
    			if(isLoaded() && !isLoadingOnDemand()) {
    				addChangelogEntry(otherEnd, TestPackage.CONTAINER_TYPE__LINKS);
    			}
    			return ((InternalEList<InternalEObject>)(InternalEList<?>)getLinks()).basicAdd(otherEnd, msgs);
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
			case TestPackage.CONTAINER_TYPE__NODES:
    			addChangelogRemoveEntry(otherEnd, TestPackage.CONTAINER_TYPE__NODES);
				return ((InternalEList<?>)getNodes()).basicRemove(otherEnd, msgs);
			case TestPackage.CONTAINER_TYPE__LINKS:
    			addChangelogRemoveEntry(otherEnd, TestPackage.CONTAINER_TYPE__LINKS);
				return ((InternalEList<?>)getLinks()).basicRemove(otherEnd, msgs);
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
			case TestPackage.CONTAINER_TYPE__NAME:
				return getName();
			case TestPackage.CONTAINER_TYPE__NODES:
				return getNodes();
			case TestPackage.CONTAINER_TYPE__LINKS:
				return getLinks();
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
			case TestPackage.CONTAINER_TYPE__NAME:
				setName((String)newValue);
				return;
			case TestPackage.CONTAINER_TYPE__NODES:
				getNodes().clear();
				getNodes().addAll((Collection<? extends Vertex>)newValue);
				return;
			case TestPackage.CONTAINER_TYPE__LINKS:
				getLinks().clear();
				getLinks().addAll((Collection<? extends Link>)newValue);
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
			case TestPackage.CONTAINER_TYPE__NAME:
				setName(DataContainerType.NAME_EDEFAULT);
				return;
			case TestPackage.CONTAINER_TYPE__NODES:
				getNodes().clear();
				return;
			case TestPackage.CONTAINER_TYPE__LINKS:
				getLinks().clear();
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
			case TestPackage.CONTAINER_TYPE__NAME:
				return DataContainerType.NAME_EDEFAULT == null ? getName() != null : !DataContainerType.NAME_EDEFAULT.equals(getName());
			case TestPackage.CONTAINER_TYPE__NODES:
				return getNodes() != null && !getNodes().isEmpty();
			case TestPackage.CONTAINER_TYPE__LINKS:
				return getLinks() != null && !getLinks().isEmpty();
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
				
			
				
			if(getData().strongLinks != null) {
				getData().strongLinks = getData().links.get();
			}
				
			
		}
	}

	/**
	 * @generated
	 */
	public void releaseDataStrongReferences() {
		
			
		
			
		getData().strongNodes = null;
			
		
			
		getData().strongLinks = null;
			
		
	}
/*
* Neo4EMF inserted code -- begin
*/
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 
	protected DataContainerType getData() {
		if ( data == null || !(data instanceof DataContainerType)){
			// TODO check that
			setLoadingOnDemand();
			data = new DataContainerType();
			if (isLoaded()) {
				((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
			unsetLoadingOnDemand();
			}
		return (DataContainerType) data;
	}

	/**
	*
	* @generated
	**/
	public void loadAllAttributesFrom(Node n) {
		this.data = new DataContainerType(this);
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
protected static class DataContainerType{


	/**
	 *Constructor of DataContainerType
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataContainerType() {
	}


	/**
	 * Constructor of DataContainerType
	 * Initializes multi-valued fields, if any.
	 *
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataContainerType(ContainerTypeImpl outer) {
		
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
      	
    protected EList<Vertex> strongNodes;
      	
	protected SoftReference<EList<Vertex>> nodes;
    
	/**
	 * The cached value of the '{@link #getLinks() <em>Links</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinks()
	 * @generated
	 * @ordered
	 */
      	
    protected EList<Link> strongLinks;
      	
	protected SoftReference<EList<Link>> links;

	
		
	
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
			
			case TestPackage.CONTAINER_TYPE__NAME:
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
	
protected static class ContainerTypeReferences  {
    
	/**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
      	
    protected EList<Vertex> strongNodes;
      	
	protected SoftReference<EList<Vertex>> nodes;
    
	/**
	 * The cached value of the '{@link #getLinks() <em>Links</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinks()
	 * @generated
	 * @ordered
	 */
      	
    protected EList<Link> strongLinks;
      	
	protected SoftReference<EList<Link>> links;
}
// nodes : EList<Vertex>, bi:true, chan:true, list:true, change:true, kind:containment reference list
// links : EList<Link>, bi:true, chan:true, list:true, change:true, kind:containment reference list
/*
* Neo4EMF inserted code -- end
*/




} //ContainerTypeImpl





