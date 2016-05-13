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

package fr.inria.atlanmod.neoemf.datastore.estores.impl;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * A {@link SearcheableResourceEStore} wrapper that delegates method calls to an
 * internal {@link SearcheableResourceEStore}
 * 
 */
public class DelegatedResourceEStoreImpl implements SearcheableResourceEStore {

	/**
	 * The wrapped {@link SearcheableResourceEStore}
	 */
	protected SearcheableResourceEStore eStore;

	public DelegatedResourceEStoreImpl(SearcheableResourceEStore eStore) {
		this.eStore = eStore;
	}

	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		return eStore.get(object, feature, index);
	}

	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		return eStore.set(object, feature, index, value);
	}

	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		return eStore.isSet(object, feature);
	}

	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		eStore.unset(object, feature);
	}

	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		return eStore.isEmpty(object, feature);
	}

	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		return eStore.size(object, feature);
	}

	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		return eStore.contains(object, feature, value);
	}

	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		return eStore.indexOf(object, feature, value);
	}

	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		return eStore.lastIndexOf(object, feature, value);
	}

	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		eStore.add(object, feature, index, value);
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		return eStore.remove(object, feature, index);
	}

	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		return eStore.move(object, feature, targetIndex, sourceIndex);
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		eStore.clear(object, feature);
	}

	@Override
	public Object[] toArray(InternalEObject object, EStructuralFeature feature) {
		return eStore.toArray(object, feature);
	}

	@Override
	public <T> T[] toArray(InternalEObject object, EStructuralFeature feature, T[] array) {
		return eStore.toArray(object, feature, array);
	}

	@Override
	public int hashCode(InternalEObject object, EStructuralFeature feature) {
		return eStore.hashCode(object, feature);
	}

	@Override
	public InternalEObject getContainer(InternalEObject object) {
		return eStore.getContainer(object);
	}

	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		return eStore.getContainingFeature(object);
	}

	@Override
	public EObject create(EClass eClass) {
		return eStore.create(eClass);
	}

	@Override
	public Resource resource() {
		return eStore.resource();
	}

	@Override
	public EObject eObject(Id id) {
		return eStore.eObject(id);
	}
	
	@Override
	public EList<EObject> getAllInstances(EClass eClass, boolean strict)
			throws UnsupportedOperationException {
		return eStore.getAllInstances(eClass, strict);
	}
}
