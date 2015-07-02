/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.core.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;
import org.eclipse.emf.ecore.impl.MinimalEStoreEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.util.EcoreUtil;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.OwnedTransientEStoreImpl;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;

public class PersistentEObjectImpl extends MinimalEStoreEObjectImpl implements InternalPersistentEObject {

	protected static final int UNSETTED_FEATURE_ID = -1;
	
//	protected String id;
	protected Id id;
	
	protected Resource.Internal resource;
	
	/**
	 * The internal cached value of the eContainer. This information should be
	 * also maintained in the underlying {@link EStore}
	 */
	protected InternalEObject eContainer;
	
	protected int eContainerFeatureID = UNSETTED_FEATURE_ID;
	
	protected EStore eStore;

	public PersistentEObjectImpl() {
		this.id = new StringId(EcoreUtil.generateUUID());
	}
	

	@Override
	public Id id() {
		return id;
	}
	
	@Override
	public void id(Id id) {
		this.id = id;
	}
	
	@Override
	public InternalEObject eInternalContainer() {
		return eContainer;
	}
	
	@Override
	public EObject eContainer() {
		if (resource instanceof PersistentResource) {
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
		if (newContainer != null && newContainer.eResource() != resource) {
			resource((Resource.Internal) eContainer.eResource());
		}
	}
	
	@Override
	public int eContainerFeatureID() {
		if (eContainerFeatureID == UNSETTED_FEATURE_ID) {
			if (eDirectResource() instanceof PersistentResource) {
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
		if (resource != null) {
			return resource;
		} else {
			return super.eResource();
		}
	}
	
	@Override
	public Internal resource() {
		return resource;
	}
	
	@Override
	public void resource(Internal resource) {
		this.resource = resource;
		EStore oldStore = eStore;
		// Set the new EStore
		if (resource instanceof PersistentResource) {
			eStore = ((PersistentResource) resource).eStore();
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
							eStore.add(this, feature, i, 
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
			eStore().unset(this, feature);
			@SuppressWarnings("rawtypes")
			EList collection = (EList) value;
			for (int index = 0; index < collection.size(); index++) {
				eStore().set(this, feature, index, value);
			}
		} else {
			eStore().set(this, feature, InternalEObject.EStore.NO_INDEX, value);
		}
	}
	
	@Override
	public Object dynamicGet(int dynamicFeatureID) {
		EStructuralFeature feature = eDynamicFeature(dynamicFeatureID);
		if (feature.isMany()) {
			return new EStoreEObjectImpl.BasicEStoreEList<Object>(this, feature);
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
			PersistentEObject other = (PersistentEObject) obj;
			if (id == null) {
				if (other.id() != null) {
					return false;
				}
			} else if (!id.equals(other.id())) {
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
}
