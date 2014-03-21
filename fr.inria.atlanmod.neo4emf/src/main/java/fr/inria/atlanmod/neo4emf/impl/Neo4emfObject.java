package fr.inria.atlanmod.neo4emf.impl;

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
 * */

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.osgi.util.NLS;
import org.neo4j.graphdb.Node;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.change.IChangeLog;
import fr.inria.atlanmod.neo4emf.change.impl.AddLink;
import fr.inria.atlanmod.neo4emf.change.impl.Entry;
import fr.inria.atlanmod.neo4emf.change.impl.SetAttribute;

public class Neo4emfObject extends MinimalEObjectImpl implements INeo4emfObject {

	/**
	 * TODO: Comment this field
	 */
	protected volatile boolean loadingOnDemand = false;

	/**
	 * eObject ID
	 */
	private long id = -1;
	/**
	 * Partition ID
	 */
	private int partition = -1;
	/**
	 * isProxy flag
	 */
	protected boolean isProxy = false;

	/**
	 * Constructor
	 */
	public Neo4emfObject() {
		super();
	}

	public Neo4emfObject(final EClass eClass) {
		this();
		this.eSetClass(eClass);
	}

	/**
	 * hasProxy flag
	 */
	protected NeoObjectData getObjectData() {
		return null;
	}

	/**
	 * @see INeo4emfObject#getNodeId()
	 */
	@Override
	public long getNodeId() {
		return this.id;
	}

	/**
	 * @see INeo4emfObject#setNodeId()
	 */
	@Override
	public void setNodeId(final long nodeId) {
		this.id = nodeId;
	}

	@Override
	public int getPartitionId() {
		return partition;
	}

	@Override
	public void setPartitionId(final int partId) {
		this.partition = partId;
	}

	/**
	 * Clear the field <b>ID</b>
	 */
	public void clearId() {
		id = -1;
	}

	/**
	 * compare two objects of type {@link INeo4emfObject}
	 * 
	 * @param {@link INeo4emfObject}
	 * @return {@link int }
	 */
	@Override
	public int compareTo(final INeo4emfObject o) {
		return this.equals(o) ? 0 : this.eContainer().eClass().getName()
				.compareTo(o.eContainer().eClass().getName());
	}

	@Override
	public boolean eIsProxy() {
		// TODO redefine the eIsproxy method
		return this.isProxy;
	}

	@Override
	public void setProxy(final boolean isProxy) {
		this.isProxy = isProxy;
	}

	@Override
	public Object eGet(final EStructuralFeature eFeature) {
		return eGet(eFeature, true);
	}

	@Override
	public Object eGet(final EStructuralFeature str, boolean resolve) {
		return eGet(str, resolve, true);
	}

	@Override
	public Object eGet(final EStructuralFeature eFeature,
			final boolean resolve, final boolean coreType) {
		try {
			loadingOnDemand = true;
			int featureID = eDerivedStructuralFeatureID(eFeature);
			Assert.isTrue(featureID >= 0,
					"Invalid feature : " + eFeature.getName());
			return eGet(featureID, resolve, coreType);
		} finally {
			loadingOnDemand = false;
		}
	}

	@Override
	public Object eGet(final int featureID, final boolean resolve,
			final boolean coreType) {

		EStructuralFeature eFeature = eClass().getEStructuralFeature(featureID);
		Assert.isNotNull(eFeature, "Invalid feature : " + eFeature);
		// agomez - 2013-12-06: Disable notification of GET
		// Object result = simpleGet(featureID, resolve, coreType, true);
		Object result = simpleGet(featureID, resolve, coreType, false);
		if (this.id == -1 || eResource() == null) {
			return result;
		}
		if (!eFeature.isMany()) {
			if (result != null) {
				return result;
			}
		} else {
			if (!((List<?>) result).isEmpty()) {
				return result;
			}
		}

		Assert.isTrue(eResource() != null
				&& eResource() instanceof INeo4emfResource,
				"The resource is either null or not of type INeo4emfResource");
		INeo4emfResource resource = (INeo4emfResource) eResource();
		if (eFeature instanceof EAttribute) {
			resource.fetchAttributes(this);
		} else if (resolve) {
			resource.getOnDemand(this, featureID);
		}
		return simpleGet(featureID, resolve, coreType, false);

	}

	protected Object eDynamicGet(final int dynamicFeatureID,
			final EStructuralFeature eFeature, final boolean resolve,
			final boolean coreType) {
		Assert.isTrue(dynamicFeatureID >= 0, "invalid Feature with " + eFeature);
		Object result = eSettingDelegate(eFeature).dynamicGet(this,
				eSettings(), dynamicFeatureID, resolve, coreType);
		if (result == null) {
			INeo4emfResource resource = (INeo4emfResource) eResource();
			if (eFeature instanceof EAttribute) {
				resource.fetchAttributes(this);
			} else {
				resource.getOnDemand(this, dynamicFeatureID
						+ eStaticFeatureCount());
			}
		}
		// agomez - 2013-12-06: Disable notification of GET
		// return simpleGet(dynamicFeatureID + eStaticFeatureCount(), resolve,
		// coreType, true);

		return simpleGet(dynamicFeatureID + eStaticFeatureCount(), resolve,
				coreType, false);

	}

	protected Object simpleGet(final int featureID, final boolean resolve,
			final boolean coreType, final boolean notificationRequired) {
		int dynamicFeatureID = featureID - eStaticFeatureCount();
		EStructuralFeature eFeature = eClass().getEStructuralFeature(featureID);
		Assert.isTrue(eFeature != null, "Invalid featureID: " + featureID);
		Object result = eSettingDelegate(eFeature).dynamicGet(this,
				eSettings(), dynamicFeatureID, resolve, coreType);
		if (result != null && notificationRequired) {
			Notification msg = new ENotificationImpl(this,
					INeo4emfNotification.GET, eFeature, null, null);
			if (getChangeLog() != null) {
				getChangeLog().addNewEntry(msg);
			}
		}
		return result;
	}

	private IChangeLog<Entry> getChangeLog() {
		return eResource() != null && eResource() instanceof Neo4emfResource ? ((Neo4emfResource) eResource())
				.getChangeLog() : null;
	}

	@Override
	protected void eDynamicSet(final int dynamicFeatureID,
			final EStructuralFeature eFeature, Object newValue) {
		eSettingDelegate(eFeature).dynamicSet(this, eSettings(),
				dynamicFeatureID, newValue);
	}

	@Override
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		int featureID = eDerivedStructuralFeatureID(eFeature);
		Assert.isTrue(featureID >= 0, "Invalid Feature : " + eFeature.getName());
		eSet(featureID, newValue);
		addChangelogEntry(newValue, eFeature);
	}

	@Override
	public void eSet(int featureID, Object newValue) {
		super.eSet(featureID, newValue);
		EStructuralFeature eFeature = eClass().getEStructuralFeature(featureID);
		int dynamicFeatureID = featureID - eStaticFeatureCount();
		Assert.isTrue(eFeature.isChangeable(),
				" illegal argument feature Cannot be changed : " + eFeature);
		eDynamicSet(dynamicFeatureID, eFeature, newValue);
		addChangelogEntry(newValue, eFeature);

	}

	protected void addChangelogEntry(Object newValue, int eStructuralFeatureId) {
		addChangelogEntry(newValue,
				eClass().getEStructuralFeature(eStructuralFeatureId));
	}

	protected void addChangelogEntry(Object newValue,
			EStructuralFeature eFeature) {
		if (!loadingOnDemand && getChangeLog() != null) {
			if (eFeature instanceof EAttribute) {
				getChangeLog().add(
						new SetAttribute(this, (EAttribute) eFeature, eGet(
								eFeature, false), newValue));
			} else if (eFeature instanceof EReference) {
				@SuppressWarnings("unchecked")
				Collection<EObject> c = (Collection<EObject>) newValue;
				for (EObject elt : c) {
					getChangeLog().add(
							new AddLink(this, (EReference) eFeature, eGet(
									eFeature, false), elt));
				}
			} else {
				throw new WrappedException(new Exception(NLS.bind(
						"Unexpected EStructuralFeature {0}",
						eFeature.toString())));
			}
		}
	}

	@Override
	protected Object eVirtualValue(int index) {
		// handle virtual delegation in the eResource
		// Object result = eVirtualValues()[index];
		// if (result != null )
		// return result;
		// else {
		//
		// }
		throw new UnsupportedOperationException();
	}

	protected boolean isLoaded() {
		return getNodeId() > -1 & eResource() instanceof INeo4emfResource;
	}

	public static class NeoObjectData {

	}

	@Override
	public boolean eIsSet(EStructuralFeature eFeature) {
		// return simpleGet(eFeature.getFeatureID(),true, true, false) != null;
		return eGet(eFeature) != null;
	}

	public EList<EObject> eResolvedContents() {
		return new EContentsEList<EObject>(this) {
			@Override
			protected boolean resolve() {
				return false;
			}
		};
	}

	public TreeIterator<EObject> eAllResolvedContents() {
		return new AbstractTreeIterator<EObject>(this, false) {
			private static final long serialVersionUID = 1L;

			@Override
			public Iterator<EObject> getChildren(Object object) {
				return ((Neo4emfObject) object).eResolvedContents().iterator();
			}
		};
	}

	@Override
	public void eSetDirectResource(Internal resource) {
		super.eSetDirectResource(resource);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj.getClass() != this.getClass()) {
			return false;
		} else if (((Neo4emfObject) obj).getNodeId() == -1) {
			return false;
		} else if (this.getNodeId() == -1) {
			return false;
		} else {
			return ((Neo4emfObject) obj).getNodeId() == this.getNodeId();
		}
	}

	@Override
	public void saveAllAttributesTo(Node n) {
		throw new UnsupportedOperationException("Unsupported Method.");
	}

	@Override
	public void loadAllAttributesFrom(Node n) {
		throw new UnsupportedOperationException("Unsupported Method.");
	}
	@Override
	public void saveAllReferencesTo(Node n) {
		throw new UnsupportedOperationException("Unsupported Method.");
	}

	@Override
	public void loadAllReferencesFrom(Node n) {
		throw new UnsupportedOperationException("Unsupported Method.");
	}
}
