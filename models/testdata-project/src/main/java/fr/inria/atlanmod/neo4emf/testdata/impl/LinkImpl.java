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
import fr.inria.atlanmod.neo4emf.testdata.ContainerType;
import fr.inria.atlanmod.neo4emf.testdata.Link;
import fr.inria.atlanmod.neo4emf.testdata.TestPackage;
import fr.inria.atlanmod.neo4emf.testdata.Vertex;
import java.lang.ref.SoftReference;
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
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkImpl#getOutGoing <em>Out Going</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkImpl#getContainer <em>Container</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkImpl#getInComing <em>In Coming</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkImpl extends NamedElementImpl implements Link {

	 
	
	 
	 
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
	public Vertex getOutGoing() {
		try {
			setLoadingOnDemand();	
	  
			if (getData().outGoing == null && isLoaded()) {
				((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK__OUT_GOING);
			} else if(getData().outGoing != null && getData().outGoing.isEnqueued()) {
				assert isLoaded();
				((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK__OUT_GOING);
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
	public Vertex basicGetOutGoing() {
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
	public NotificationChain basicSetOutGoing(Vertex newOutGoing, NotificationChain msgs) {
	
		
	
      	Vertex oldOutGoing = null;
      	if(getData().outGoing != null) {
      		oldOutGoing = getData().outGoing.get();
      	}
      	getData().outGoing = new SoftReference<Vertex>(newOutGoing, garbagedData);
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
	
		
	
        // setGenFeature.override.javajetinc (bidirectionnal handling)
        // TEST
        if(newOutGoing.eResource() == null) {
        	// Reproduce the error in standard EMF (when trying to add a non-containment reference
        	// on an EObject which is not in a resource)
        	throw new RuntimeException("The object '" + newOutGoing.toString() + "' is not container in a resource");
        }
        // TODO Check that (always true ? compare EObject with SoftReference)
        if(newOutGoing != getData().outGoing) {
        	NotificationChain msgs = null;
        	if(getData().outGoing != null || (!isLoadingOnDemand() && getOutGoing() != null)) {
        		
        		msgs = ((InternalEObject) getOutGoing()).eInverseRemove(this, TestPackage.VERTEX__FROM, Vertex.class, msgs);
        		addChangelogRemoveEntry(getOutGoing(), TestPackage.LINK__OUT_GOING);
        		
        	}
        	if(newOutGoing != null) {
        		
				msgs = ((InternalEObject) newOutGoing).eInverseAdd(this, TestPackage.VERTEX__FROM, Vertex.class, msgs);
        		
        	}
        	if(isLoaded()) {
        		addChangelogEntry(newOutGoing,TestPackage.LINK__OUT_GOING);
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
		//		msgs = ((InternalEObject) getData().outGoing).eInverseRemove(this, TestPackage.VERTEX__FROM, Vertex.class, msgs);
		//	if (newOutGoing != null)
		//		msgs = ((InternalEObject)newOutGoing).eInverseAdd(this, TestPackage.VERTEX__FROM, Vertex.class, msgs);
        //
		//	msgs = basicSetOutGoing(newOutGoing, msgs);
		//	if (msgs != null) msgs.dispatch();
		//}
		else if (eNotificationRequired()) {
				eNotify(new ENotificationImpl(this, Notification.SET, TestPackage.LINK__OUT_GOING, newOutGoing, newOutGoing));
          	}
  if(isLoaded()) {
		  this.addChangelogEntry(newOutGoing, TestPackage.LINK__OUT_GOING);
    }
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContainerType getContainer() {
		try {
			setLoadingOnDemand();	
	  
		if (isLoaded() && eContainer() == null) {
			ContainerType container = (ContainerType) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, TestPackage.LINK__CONTAINER);
			basicSetContainer(container,null);}
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
	public NotificationChain basicSetContainer(ContainerType newContainer, NotificationChain msgs) {
	
		msgs = eBasicSetContainer((InternalEObject)newContainer, TestPackage.LINK__CONTAINER, msgs);
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setContainer(ContainerType newContainer) {
	
		
	
		// TEST
      	if (newContainer != eInternalContainer() || (eContainerFeatureID() != TestPackage.LINK__CONTAINER && newContainer != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newContainer)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if(eInternalContainer() != null) {
				if(newContainer == null && isLoaded()) {
					addChangelogRemoveEntry(eInternalContainer(), TestPackage.LINK__CONTAINER);
				}
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if(isLoaded()) {
				addChangelogEntry(newContainer, TestPackage.LINK__CONTAINER);
			}
			msgs = basicSetContainer(newContainer, msgs);
			if(newContainer != null) {
				msgs = ((InternalEObject)newContainer).eInverseAdd(this, TestPackage.CONTAINER_TYPE__LINKS, ContainerType.class, msgs);
			}
			if(msgs != null) {
				msgs.dispatch();
			}	
		}
		else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, TestPackage.LINK__CONTAINER, newContainer, newContainer));
		}
  if(isLoaded()) {
		  this.addChangelogEntry(newContainer, TestPackage.LINK__CONTAINER);
    }
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
			setLoadingOnDemand();	
	  
			if (getData().inComing == null && isLoaded()) {
				((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK__IN_COMING);
			} else if(getData().inComing != null && getData().inComing.isEnqueued()) {
				assert isLoaded();
				((INeo4emfResource) this.eResource()).getOnDemand(this, TestPackage.LINK__IN_COMING);
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
	public Vertex basicGetInComing() {
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
	public NotificationChain basicSetInComing(Vertex newInComing, NotificationChain msgs) {
	
		
	
      	Vertex oldInComing = null;
      	if(getData().inComing != null) {
      		oldInComing = getData().inComing.get();
      	}
      	getData().inComing = new SoftReference<Vertex>(newInComing, garbagedData);
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
	
		
	
        // setGenFeature.override.javajetinc (bidirectionnal handling)
        // TEST
        if(newInComing.eResource() == null) {
        	// Reproduce the error in standard EMF (when trying to add a non-containment reference
        	// on an EObject which is not in a resource)
        	throw new RuntimeException("The object '" + newInComing.toString() + "' is not container in a resource");
        }
        // TODO Check that (always true ? compare EObject with SoftReference)
        if(newInComing != getData().inComing) {
        	NotificationChain msgs = null;
        	if(getData().inComing != null || (!isLoadingOnDemand() && getInComing() != null)) {
        		
        		msgs = ((InternalEObject) getInComing()).eInverseRemove(this, TestPackage.VERTEX__TO, Vertex.class, msgs);
        		addChangelogRemoveEntry(getInComing(), TestPackage.LINK__IN_COMING);
        		
        	}
        	if(newInComing != null) {
        		
				msgs = ((InternalEObject) newInComing).eInverseAdd(this, TestPackage.VERTEX__TO, Vertex.class, msgs);
        		
        	}
        	if(isLoaded()) {
        		addChangelogEntry(newInComing,TestPackage.LINK__IN_COMING);
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
		//		msgs = ((InternalEObject) getData().inComing).eInverseRemove(this, TestPackage.VERTEX__TO, Vertex.class, msgs);
		//	if (newInComing != null)
		//		msgs = ((InternalEObject)newInComing).eInverseAdd(this, TestPackage.VERTEX__TO, Vertex.class, msgs);
        //
		//	msgs = basicSetInComing(newInComing, msgs);
		//	if (msgs != null) msgs.dispatch();
		//}
		else if (eNotificationRequired()) {
				eNotify(new ENotificationImpl(this, Notification.SET, TestPackage.LINK__IN_COMING, newInComing, newInComing));
          	}
  if(isLoaded()) {
		  this.addChangelogEntry(newInComing, TestPackage.LINK__IN_COMING);
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
			case TestPackage.LINK__OUT_GOING:
				if (getData().outGoing != null && !getData().outGoing.get().equals(otherEnd))
					msgs = ((InternalEObject)getData().outGoing.get()).eInverseRemove(this, TestPackage.VERTEX__FROM, Vertex.class, msgs);
				return basicSetOutGoing((Vertex)otherEnd, msgs);
			case TestPackage.LINK__CONTAINER:
				if (eInternalContainer() != null && !eInternalContainer().equals(otherEnd)) {
					msgs = eBasicRemoveFromContainer(msgs);
				}
				// Should be done before basicSet, otherwise the ChangeLog may be flushed and the 
				// isLoaded method would return true, and duplicate the AddLink entry
				if(isLoaded() && !isLoadingOnDemand() && !((INeo4emfObject)otherEnd).isLoadingOnDemand()) {
					addChangelogEntry(otherEnd, TestPackage.LINK__CONTAINER);
				}
				return basicSetContainer((ContainerType)otherEnd, msgs);
			case TestPackage.LINK__IN_COMING:
				if (getData().inComing != null && !getData().inComing.get().equals(otherEnd))
					msgs = ((InternalEObject)getData().inComing.get()).eInverseRemove(this, TestPackage.VERTEX__TO, Vertex.class, msgs);
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
  				if(isLoaded()) {
  					addChangelogRemoveEntry(otherEnd,featureID);
  				}
				return basicSetOutGoing(null, msgs);
			case TestPackage.LINK__CONTAINER:
  				if(isLoaded()) {
  					addChangelogRemoveEntry(otherEnd,featureID);
  				}
				return basicSetContainer(null, msgs);
			case TestPackage.LINK__IN_COMING:
  				if(isLoaded()) {
  					addChangelogRemoveEntry(otherEnd,featureID);
  				}
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
				return eInternalContainer().eInverseRemove(this, TestPackage.CONTAINER_TYPE__LINKS, ContainerType.class, msgs);
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
			case TestPackage.LINK__OUT_GOING:
				setOutGoing((Vertex)newValue);
				return;
			case TestPackage.LINK__CONTAINER:
				setContainer((ContainerType)newValue);
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
			case TestPackage.LINK__OUT_GOING:
				setOutGoing((Vertex)null);
				return;
			case TestPackage.LINK__CONTAINER:
				setContainer((ContainerType)null);
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
			case TestPackage.LINK__OUT_GOING:
				return getOutGoing() != null;
			case TestPackage.LINK__CONTAINER:
				return getContainer() != null;
			case TestPackage.LINK__IN_COMING:
				return getInComing() != null;
		}
		return super.eIsSet(featureID);
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
	protected DataLink getData() {
		if ( data == null || !(data instanceof DataLink)){
			// TODO check that
			setLoadingOnDemand();
			data = new DataLink();
			if (isLoaded()) {
				((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
			unsetLoadingOnDemand();
			}
		return (DataLink) data;
	}

	/**
	*
	* @generated
	**/
	public void loadAllAttributesFrom(Node n) {
		this.data = new DataLink(this);
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
protected static class DataLink extends DataNamedElement{


	/**
	 *Constructor of DataLink
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataLink() {
	}


	/**
	 * Constructor of DataLink
	 * Initializes multi-valued fields, if any.
	 *
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataLink(LinkImpl outer) {
		 super(outer); 
	}


    
	/**
	 * The cached value of the '{@link #getOutGoing() <em>Out Going</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutGoing()
	 * @generated
	 * @ordered
	 */
      	
	protected SoftReference<Vertex> outGoing;
    
    
	/**
	 * The cached value of the '{@link #getInComing() <em>In Coming</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInComing()
	 * @generated
	 * @ordered
	 */
      	
	protected SoftReference<Vertex> inComing;

	
		
	/**
	 *Constructor of DataLink%>
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
	}


	/**
 	* <!-- begin-user-doc -->
 	* <!-- end-user-doc -->
 	* @generated
 	*/
	public void saveAllAttributesTo(Node n) {
		
		super.saveAllAttributesTo(n);
		
		
	} // saveTo()
	
	/**
 	* <!-- begin-user-doc -->
 	* <!-- end-user-doc -->
 	* @generated
 	*/
	public void saveAttributeTo(int featureID, Node n) {
		switch (featureID) {
			
		} // switch
	} // saveAttributeTo()
	




}//end attribute class
	
protected static class LinkReferences  extends DataNamedElement {
    
	/**
	 * The cached value of the '{@link #getOutGoing() <em>Out Going</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutGoing()
	 * @generated
	 * @ordered
	 */
      	
	protected SoftReference<Vertex> outGoing;
    
    
	/**
	 * The cached value of the '{@link #getInComing() <em>In Coming</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInComing()
	 * @generated
	 * @ordered
	 */
      	
	protected SoftReference<Vertex> inComing;
}
// outGoing : Vertex, bi:true, chan:true, list:false, change:true, kind:reference
// container : ContainerType, bi:true, chan:true, list:false, change:true, kind:container reference
// inComing : Vertex, bi:true, chan:true, list:false, change:true, kind:reference
/*
* Neo4EMF inserted code -- end
*/




} //LinkImpl





