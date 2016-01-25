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
package fr.inria.atlanmod.kyanos.core.impl;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEStoreEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.util.EcoreUtil;

import fr.inria.atlanmod.kyanos.core.KyanosInternalEObject;
import fr.inria.atlanmod.kyanos.core.KyanosResource;
import fr.inria.atlanmod.kyanos.datastore.estores.impl.NullEStoreImpl;

public class KyanosEObjectProxyHandlerImpl extends MinimalEStoreEObjectImpl implements MethodInterceptor, KyanosInternalEObject {

	protected InternalEObject internalEObject;

	protected String id;

	protected InternalEObject.EStore eStore;

	protected Resource.Internal kyanosResource;

	public KyanosEObjectProxyHandlerImpl(InternalEObject internalEObject) {
		super();
		this.internalEObject = internalEObject;
		this.id = EcoreUtil.generateUUID();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		try {
			return this.getClass().getMethod(method.getName(), method.getParameterTypes()).invoke(this, args);
		} catch (NoSuchMethodException e) {
			return internalEObject.getClass().getMethod(method.getName(), method.getParameterTypes()).invoke(internalEObject, args);
		}
	}

	@Override
	public Object dynamicGet(int dynamicFeatureID) {
		EStructuralFeature feature = eClass().getEStructuralFeature(dynamicFeatureID);
		if (feature.isMany()) {
			return createList(feature);
		} else {
			return eStore().get(this, feature, EStore.NO_INDEX);
		}
	}
	
	@Override
	public void dynamicSet(int dynamicFeatureID, Object value) {
		EStructuralFeature feature = eClass().getEStructuralFeature(dynamicFeatureID);
		if (feature.isMany()) {
			// TODO this operation should be atomic 
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
	public void dynamicUnset(int dynamicFeatureID) {
		EStructuralFeature feature = eClass().getEStructuralFeature(dynamicFeatureID);
		eStore().unset(this, feature);
	}

	private void unsetInEStore(EStructuralFeature feature) {
		eStore().unset(this, feature);
	}

	@Override
	public void eSet(EStructuralFeature feature, Object newValue) {
		dynamicSet(eClass().getFeatureID(feature), newValue);
		internalEObject.eSet(feature, newValue);
	}

	@Override
	public void eUnset(EStructuralFeature feature) {
		unsetInEStore(feature);
		internalEObject.eUnset(feature);
	}

	@Override
	public void eSet(int featureID, Object newValue) {
		dynamicSet(featureID, newValue);
		internalEObject.eSet(featureID, newValue);
	}

	@Override
	public void eUnset(int featureID) {
		dynamicUnset(featureID);
		internalEObject.eUnset(featureID);
	}

	@Override
	public Object eGet(EStructuralFeature feature) {
		return eGet(feature, true);
	}

	@Override
	public Object eGet(EStructuralFeature feature, boolean resolve) {
		return eGet(feature, true, true);
	}

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		return eGet(eClass().getEStructuralFeature(featureID), resolve, coreType);
	}

	@Override
	public Object eGet(EStructuralFeature feature, boolean resolve, boolean coreType) {
		return dynamicGet(eClass().getFeatureID(feature));
	}

	@Override
	public String kyanosId() {
		return id;
	}

	@Override
	public void kyanosSetId(String id) {
		this.id = id;
	}

	@Override
	public Internal kyanosResource() {
		return kyanosResource;
	}

	@Override
	public void kyanosSetResource(Internal resource) {
		if (resource instanceof KyanosResource) {
			KyanosResource kyanosResource = (KyanosResource) resource;
			this.kyanosResource = kyanosResource;
			this.eStore = kyanosResource.eStore();
			for (EStructuralFeature feature : eClass().getEAllStructuralFeatures()) {
				if (internalEObject.eIsSet(feature)) {
					dynamicSet(eClass().getFeatureID(feature), internalEObject.eGet(feature));
				}
			}
		} else {
			for (EStructuralFeature feature : eClass().getEAllStructuralFeatures()) {
				if (eIsSet(feature)) {
					internalEObject.eSet(eClass().getFeatureID(feature), eGet(feature));
				}
			}
			this.kyanosResource = null;
			this.eStore = null;
		}
	}
	
	@Override
	public NotificationChain eSetResource(Internal resource, NotificationChain notifications) {
		kyanosSetResource(resource);
		return super.eSetResource(resource, notifications);
	}
	
	@Override
	public EStore eStore() {
		if (eStore == null) {
			eStore = new NullEStoreImpl();
		}
		return eStore;
	}

	@Override
	public void eSetStore(EStore store) {
		this.eStore = store;
	}
	
	@Override
	public EClass eClass() {
		return internalEObject.eClass();
	}
	
	@Override
	public EList<EObject> eContents() {
		return super.eContents();
	}
	
//	@Override
//	public EList<Adapter> eAdapters() {
//		return internalEObject.eAdapters();
//	}
//
//	@Override
//	public boolean eDeliver() {
//		return internalEObject.eDeliver();
//	}
//
//	@Override
//	public boolean eNotificationRequired() {
//		return internalEObject.eNotificationRequired();
//	}
//
//	@Override
//	public void eSetDeliver(boolean deliver) {
//		internalEObject.eSetDeliver(deliver);
//	}
//
//	@Override
//	public void eNotify(Notification notification) {
//		internalEObject.eNotify(notification);
//	}
//
//	@Override
//	public String eURIFragmentSegment(EStructuralFeature eFeature, EObject eObject) {
//		return internalEObject.eURIFragmentSegment(eFeature, eObject);
//	}
//
//	@Override
//	public EObject eObjectForURIFragmentSegment(String uriFragmentSegment) {
//		return internalEObject.eObjectForURIFragmentSegment(uriFragmentSegment);
//	}
//
//
//	@Override
//	public Setting eSetting(EStructuralFeature feature) {
//		return internalEObject.eSetting(feature);
//	}
//
//	@Override
//	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
//		return internalEObject.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
//	}
//
//	@Override
//	public Resource eResource() {
//		return internalEObject.eResource();
//	}
//
//	@Override
//	public int eContainerFeatureID() {
//		return internalEObject.eContainerFeatureID();
//	}
//
//	@Override
//	public EObject eContainer() {
//		return internalEObject.eContainer();
//	}
//
//	@Override
//	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
//		return internalEObject.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
//	}
//
//	@Override
//	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
//		return internalEObject.eDerivedOperationID(baseOperationID, baseClass);
//	}
//
//	@Override
//	public EStructuralFeature eContainingFeature() {
//		return internalEObject.eContainingFeature();
//	}
//
//	@Override
//	public NotificationChain eSetResource(Internal resource, NotificationChain notifications) {
//		return internalEObject.eSetResource(resource, notifications);
//	}
//
//	@Override
//	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class<?> baseClass, NotificationChain notifications) {
//		return internalEObject.eInverseAdd(otherEnd, featureID, baseClass, notifications);
//	}
//
//	@Override
//	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class<?> baseClass, NotificationChain notifications) {
//		return internalEObject.eInverseRemove(otherEnd, featureID, baseClass, notifications);
//	}
//
//	@Override
//	public EReference eContainmentFeature() {
//		return internalEObject.eContainmentFeature();
//	}
//
//	@Override
//	public NotificationChain eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID, NotificationChain notifications) {
//		return internalEObject.eBasicSetContainer(newContainer, newContainerFeatureID, notifications);
//	}
//
//	@Override
//	public NotificationChain eBasicRemoveFromContainer(NotificationChain notifications) {
//		return internalEObject.eBasicRemoveFromContainer(notifications);
//	}
//
//	@Override
//	public URI eProxyURI() {
//		return internalEObject.eProxyURI();
//	}
//
//	@Override
//	public EList<EObject> eContents() {
//		return EContentsEList.createEContentsEList(this);
//	}
//
//	@Override
//	public EList<EObject> eCrossReferences() {
//		return ECrossReferenceEList.createECrossReferenceEList(this);
//	}
//
//	@Override
//	public void eSetProxyURI(URI uri) {
//		internalEObject.eSetProxyURI(uri);
//	}
//
//	@Override
//	public EObject eResolveProxy(InternalEObject proxy) {
//		return internalEObject.eResolveProxy(proxy);
//	}
//
//	@Override
//	public InternalEObject eInternalContainer() {
//		return internalEObject.eInternalContainer();
//	}
//
//	@Override
//	public TreeIterator<EObject> eAllContents() {
//		return new AbstractTreeIterator<EObject>(this, false) {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public Iterator<EObject> getChildren(Object object) {
//				return ((EObject) object).eContents().iterator();
//			}
//		};
//	}
//
//	@Override
//	public Internal eInternalResource() {
//		return internalEObject.eInternalResource();
//	}
//
//	@Override
//	public Internal eDirectResource() {
//		return internalEObject.eDirectResource();
//	}
//
//	@Override
//	public boolean eIsProxy() {
//		return internalEObject.eIsProxy();
//	}
//
//
//	@Override
//	public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
//		return internalEObject.eInvoke(operation, arguments);
//	}
//
//	@Override
//	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
//		return internalEObject.eInvoke(operationID, arguments);
//	}
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer(super.toString());
		String delim = "";
		result.append(" (");
		for (EAttribute eAttribute : eClass().getEAllAttributes()) {
			result.append(delim);
			result.append(eAttribute.getName());
			result.append(": ");
			result.append(eGet(eAttribute));
			delim = ", ";
		}
		result.append(") [[Wrapped Information: ");
		result.append(internalEObject.toString());
		result.append("]]");
		return result.toString();
	}
}
