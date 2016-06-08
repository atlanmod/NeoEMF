/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.core.impl;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.OwnedTransientEStoreImpl;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.util.NeoEContentsEList;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;
import org.eclipse.emf.ecore.impl.MinimalEStoreEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

public class PersistentEObjectImpl extends MinimalEStoreEObjectImpl implements InternalPersistentEObject {

	private static int numberNewStoreFeatureSet;
	private static int numberNewStoreEobject;

	private static int numberManyFeatureSet;
	private static int numberSingleFeatureSet;

	private static final int UNSETTED_FEATURE_ID = -1;

	private Id id;

	private Resource.Internal resource;

	private boolean isMapped;

	/**
	 * The internal cached value of the eContainer. This information should be
	 * also maintained in the underlying {@link EStore}.
	 */
	private InternalEObject eContainer;

	private int eContainerFeatureId;

	private EStore eStore;

	public PersistentEObjectImpl() {
		this(new StringId(EcoreUtil.generateUUID()));
	}

	protected PersistentEObjectImpl(Id id) {
		this.id = id;
		this.eContainerFeatureId = UNSETTED_FEATURE_ID;
		this.isMapped = false;
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
	public boolean isMapped() {
	    return isMapped;
	}

	@Override
	public void setMapped(boolean mapped) {
	    this.isMapped = mapped;
	}

	/**
	 * Returns the container of the {@link PersistentEObject}.
	 * <p/>
	 * Do not return the same value as standard EMF implementation if the container
	 * has not been accessed with the public method {@link #eContainer()} before.
	 * @return the container of the {@link PersistentEObject}.
	 */
	@Override
	public InternalEObject eInternalContainer() {
		// Do not load the container from the eStore here: it creates an important overhead and performance loss
		return eContainer == null ? super.eInternalContainer() : eContainer;
	}

	@Override
	public EObject eContainer() {
		EObject returnValue;
		if (resource instanceof PersistentResource) {
			InternalEObject container = eStore().getContainer(this);
			eBasicSetContainer(container);
			eBasicSetContainerFeatureID(eContainerFeatureID());
			returnValue = container;
		} else {
			returnValue = super.eContainer();
		}
		return returnValue;
	}

	@Override
	public EList<EObject> eContents() {
		return NeoEContentsEList.createNeoEContentsEList(this);
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
		if (eContainerFeatureId == UNSETTED_FEATURE_ID && resource instanceof PersistentResource) {
			EReference containingFeature = (EReference) eStore().getContainingFeature(this);
			if (containingFeature != null) {
				EReference oppositeFeature = containingFeature.getEOpposite();
				if (oppositeFeature != null) {
					eBasicSetContainerFeatureID(eClass().getFeatureID(oppositeFeature));
				} else {
					eBasicSetContainerFeatureID(
							InternalEObject.EOPPOSITE_FEATURE_BASE
									- eInternalContainer().eClass().getFeatureID(containingFeature));
				}
			}
		}
		return eContainerFeatureId;
	}

	@Override
	protected void eBasicSetContainerFeatureID(int newContainerFeatureId) {
		eContainerFeatureId = newContainerFeatureId;
	}

	@Override
	public Resource eResource() {
		return resource != null ? resource : super.eResource();
	}

	@Override
	public Internal resource() {
		return resource;
	}

	@Override
	public void resource(Internal resource) {
	    boolean updated = false;
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
						Object value = getAdaptedValue(oldStore, feature, EStore.NO_INDEX);
						updated = value != null;
						if (updated) {
							eStore.set(this, feature, EStore.NO_INDEX, value);
							numberSingleFeatureSet++;
						}
					} else {
						eStore.clear(this, feature);
						for (int i = 0; i < oldStore.size(this, feature); i++) {
							Object value = getAdaptedValue(oldStore, feature, i);
							updated = value != null;
							if (updated) {
								eStore.add(this, feature, i, value);
								numberManyFeatureSet++;
							}
						}
					}
				}
			}
		}
		// FIXME Can be false even if it has been updated : if the last element in a feature hasn't be updated.
		if (updated) {
			numberNewStoreEobject++;
		}
	}

	private Object getAdaptedValue(EStore store, EStructuralFeature feature, int index) {
		Object value = store.get(this, feature, index);
		if (value != null) {
			if(feature instanceof EReference) {
				EReference eRef = (EReference)feature;
				if(eRef.isContainment()) {
					InternalPersistentEObject internalElement = checkNotNull(
							NeoEObjectAdapterFactoryImpl.getAdapter(value, InternalPersistentEObject.class));
					if(internalElement.resource() != resource()) {
						internalElement.resource(resource());
					}
				}
			}
			numberNewStoreFeatureSet++;
		}
		else {
			NeoLogger.debug("A null value has been detected in the old store (Feature {0}.{1})", ((EClassifier)feature.eContainer()).getName(), feature.getName());
		}
		return value;
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
	public void dynamicSet(int dynamicFeatureId, Object value) {
		EStructuralFeature feature = eDynamicFeature(dynamicFeatureId);
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
	public Object dynamicGet(int dynamicFeatureId) {
		Object returnValue;
		final EStructuralFeature feature = eDynamicFeature(dynamicFeatureId);
		final EClassifier eType = feature.getEType();
		if (feature.isMany()) {
		    if(eType.getInstanceClassName() != null && eType.getInstanceClassName().equals("java.util.Map$Entry")) {
				returnValue = new EStoreEcoreEMap(eType, feature);
		    }
		    else {
				returnValue = new EStoreEObjectImpl.BasicEStoreEList<Object>(this,feature) {
					private static final long serialVersionUID = 1L;

					@Override
		            public boolean contains(Object object) {
		                return delegateContains(object);
		            }
		        };
		    }
		} else {
			returnValue = eStore().get(this, feature, EStore.NO_INDEX);
		}
		return returnValue;
	}

	@Override
	public void dynamicUnset(int dynamicFeatureId) {
		EStructuralFeature feature = eDynamicFeature(dynamicFeatureId);
		eStore().unset(this, feature);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		PersistentEObject other = (PersistentEObject) obj;
		return Objects.equals(id, other.id());
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

	private class EStoreEcoreEMap extends EcoreEMap<Object, Object> {
		private static final long serialVersionUID = 1L;

		public EStoreEcoreEMap(EClassifier eType, EStructuralFeature feature) {
			super ((EClass)eType, BasicEMap.Entry.class, null);
			delegateEList = new EntryBasicEStoreEList(feature);
			size = delegateEList.size();
		}

		private class EntryBasicEStoreEList extends EStoreEObjectImpl.BasicEStoreEList<Entry<Object, Object>> {
			private static final long serialVersionUID = 1L;

			public EntryBasicEStoreEList(EStructuralFeature feature) {
				super(PersistentEObjectImpl.this, feature);
			}

			@Override
            protected void didAdd(int index, Entry<Object, Object> newObject) {
                doPut(newObject);
            }

			@Override
            protected void didSet(int index, Entry<Object, Object> newObject, Entry<Object, Object> oldObject) {
                didRemove(index, oldObject);
                didAdd(index, newObject);
            }

			@Override
            protected void didRemove(int index, Entry<Object, Object> oldObject) {
                EStoreEcoreEMap.this.doRemove(oldObject);
            }

			@Override
            protected void didClear(int size, Object [] oldObjects) {
                EStoreEcoreEMap.this.doClear();
            }

			@Override
            protected void didMove(int index, Entry<Object, Object> movedObject, int oldIndex) {
                EStoreEcoreEMap.this.doMove(movedObject);
            }
		}
	}
}
