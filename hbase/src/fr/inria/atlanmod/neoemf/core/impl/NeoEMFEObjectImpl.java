/*******************************************************************************
 * Copyright (c) 2014 Abel G�mez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel G�mez - initial API and implementation
 ******************************************************************************/
package fr.inria.atlanmod.neoemf.core.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;
import org.eclipse.emf.ecore.impl.MinimalEStoreEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.util.EcoreUtil;

import fr.inria.atlanmod.neoemf.core.NeoEMFEObject;
import fr.inria.atlanmod.neoemf.core.NeoEMFInternalEObject;
import fr.inria.atlanmod.neoemf.core.NeoEMFResource;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.OwnedTransientEStoreImpl;

public class NeoEMFEObjectImpl extends MinimalEStoreEObjectImpl implements NeoEMFInternalEObject {

	protected static final int UNSETTED_FEATURE_ID = -1;
	
	protected String id;
	
	protected Resource.Internal neoemfResource;
	
	/**
	 * The internal cached value of the eContainer. This information should be
	 * also maintained in the underlying {@link EStore}
	 */
	protected InternalEObject eContainer;
	
	protected int eContainerFeatureID = UNSETTED_FEATURE_ID;
	
	protected EStore eStore;

	public NeoEMFEObjectImpl() {
		this.id = EcoreUtil.generateUUID();
	}

	@Override
	public String neoemfId() {
		return id;
	}

	@Override
	public void neoemfSetId(String id) {
		this.id = id;
	}

	@Override
	public InternalEObject eInternalContainer() {
		return eContainer;
	}
	
	@Override
	public EObject eContainer() {
		if (neoemfResource instanceof NeoEMFResource) {
			InternalEObject container = eStore().getContainer(this);
			eBasicSetContainer(container);
			eBasicSetContainerFeatureID(eContainerFeatureID());
			return container;
		} else {
			return super.eContainer();
		}
	}
	
	@Override
	protected void eBasicSetContainer(InternalEObject newContainer) {
		eContainer = newContainer;
		if (newContainer != null && newContainer.eResource() != neoemfResource) {
			neoemfSetResource((Resource.Internal) eContainer.eResource());
		}
	}
	
	@Override
	public int eContainerFeatureID() {
		if (eContainerFeatureID == UNSETTED_FEATURE_ID) {
			if (eResource() instanceof NeoEMFResource) {
				EReference containingFeature = (EReference) eStore().getContainingFeature(this);
				if (containingFeature != null) {
					EReference oppositeFeature = containingFeature.getEOpposite();
					if (oppositeFeature != null) {
						eBasicSetContainerFeatureID(eClass().getFeatureID(oppositeFeature));
					} else {
						eBasicSetContainerFeatureID(
								InternalEObject.EOPPOSITE_FEATURE_BASE - 
								eInternalContainer().eClass().getFeatureID(containingFeature));
					}
				}
			}
		}
		return eContainerFeatureID;
	}
	
	@Override
	protected void eBasicSetContainerFeatureID(int newContainerFeatureID) {
		eContainerFeatureID = newContainerFeatureID;
	}
	
	@Override
	public Resource eResource() {
		if (neoemfResource != null) {
			return neoemfResource;
		} else {
			return super.eResource();
		}
	}
	
	@Override
	public Internal neoemfResource() {
		return neoemfResource;
	}
	
	@Override
	public void neoemfSetResource(Internal resource) {
		this.neoemfResource = resource;
		EStore oldStore = eStore;
		// Set the new EStore
		if (resource instanceof NeoEMFResource) {
			eStore = ((NeoEMFResource) resource).eStore();
		} else {
			eStore = new OwnedTransientEStoreImpl(this);
		}
		// Move contents from oldStore to eStore
		if (oldStore != null && eStore != null && eStore != oldStore) {
			// If the new store is different, initialize the new store
			// with the data stored in the old store
			for (EStructuralFeature feature : eClass().getEAllStructuralFeatures()) {
				if (oldStore.isSet(this, feature)) {
					if (!feature.isMany()) {
						eStore.set(this, feature, EStore.NO_INDEX, 
								oldStore.get(this, feature, EStore.NO_INDEX));
					} else {
						eStore.clear(this, feature);
						int size = oldStore.size(this, feature);
						for (int i = 0; i < size; i++) {
							eStore.add(this, feature, i == 0 ? 0 : EStore.NO_INDEX, 
									oldStore.get(this, feature, i));
						}
					}
				}
			}
		}
	}
	

	@Override
	public EStore eStore() {
		if (eStore == null) {
			eStore = new OwnedTransientEStoreImpl(this);
		}
		return eStore;
	}
	
	@Override
	protected boolean eIsCaching() {
		return false;
	}
	
	@Override
	public void dynamicSet(int dynamicFeatureID, Object value) {
		EStructuralFeature feature = eDynamicFeature(dynamicFeatureID);
		if (feature.isMany()) {
			// TODO this operation should be atomic 
			// restitute the old value in case the operation fails in the middle
			eStore().unset(this, feature);
			@SuppressWarnings("rawtypes")
			EList collection = (EList) value;
			for (int index = 0; index < collection.size(); index++) {
				eStore().add(this, feature, index, collection.get(index));
			}
		} else {
			eStore().set(this, feature, InternalEObject.EStore.NO_INDEX, value);
		}
	}
	
	@Override
	public Object dynamicGet(int dynamicFeatureID) {
		EStructuralFeature feature = eDynamicFeature(dynamicFeatureID);
		if (feature.isMany()) {
			return new NeoEMFEStoreEList(this, feature);
		} else {
			return eStore().get(this, feature, EStore.NO_INDEX);
		}
	}
	
	@Override
	public void dynamicUnset(int dynamicFeatureID) {
		EStructuralFeature feature = eDynamicFeature(dynamicFeatureID);
		eStore().unset(this, feature);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		} else {
			NeoEMFEObject other = (NeoEMFEObject) obj;
			if (id == null) {
				if (other.neoemfId() != null) {
					return false;
				}
			} else if (!id.equals(other.neoemfId())) {
				return false;
			}
			return true;
		}
	}
	
	@Override
	public String toString() {
	    StringBuilder result = new StringBuilder(getClass().getName());
	    result.append('@');
	    result.append(Integer.toHexString(hashCode()));

	    if (eIsProxy())
	    {
	      result.append(" (eProxyURI: ");
	      result.append(eProxyURI());
	      if (eDynamicClass() != null)
	      {
	        result.append(" eClass: ");
	        result.append(eDynamicClass());
	      }
	      result.append(')');
	    }
	    else if (eDynamicClass() != null)
	    {
	      result.append(" (eClass: ");
	      result.append(eDynamicClass());
	      result.append(')');
	    } else if (eStaticClass() != null) {
		      result.append(" (eClass: ");
		      result.append(eStaticClass());
		      result.append(')');
	    }
		return result.toString();
	}
	
	public class NeoEMFEStoreEList extends EStoreEObjectImpl.BasicEStoreEList<Object> {

		/**
		 * @author Amine Benelallam
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NeoEMFEStoreEList(InternalEObject owner,
				EStructuralFeature eStructuralFeature) {
			super(owner, eStructuralFeature);
		}
		
//	    @Override
//	    protected void delegateAdd(Object object)
//	    {
//	      delegateAdd(delegateSize() == 0 ? 0 : EStore.NO_INDEX , object);
//	      add(object);
//	    }
	    
	    @Override
	    public boolean add(Object object)
	    {
	      if (isUnique() && contains(object))
	      {
	        return false;
	      }
	      else
	      {
	    	if (eStructuralFeature instanceof EAttribute) { 
	        addUnique(object);
	    	} else {
	    		int index = size() == 0 ? 0 : EStore.NO_INDEX;
	    		addUnique(index, object);
	    	}
	        return true;
	      }
	    }

	}
// end NeoEMFEStoreElist
}
// end NeoEMFEObject