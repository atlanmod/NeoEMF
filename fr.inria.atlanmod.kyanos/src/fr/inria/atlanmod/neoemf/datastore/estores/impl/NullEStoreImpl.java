/*******************************************************************************
 * Copyright (c) 2015 Abel Gómez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel Gómez - initial API and implementation
 ******************************************************************************/
package fr.inria.atlanmod.kyanos.datastore.estores.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;

/**
 * A dummy {@link EStore} implementation that does not store any information
 * 
 * @author agomez
 * 
 */
public class NullEStoreImpl implements InternalEObject.EStore {

	@Override
	public EStructuralFeature getContainingFeature(InternalEObject eObject) {
		// This should never be called.
		throw new UnsupportedOperationException();
	}

	@Override
	public EObject create(EClass eClass) {
		// Unimplemented
		// TODO: In which case is needed?
		throw new UnsupportedOperationException();
	}

	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		return null;
	}

	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		return null;
	}

	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		return false;
	}

	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
	}

	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		return false;
	}

	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		return 0;
	}

	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		return false;
	}

	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		return 0;
	}

	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		return 0;
	}

	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		return null;
	}

	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		return null;
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
	}

	@Override
	public Object[] toArray(InternalEObject object, EStructuralFeature feature) {
		return null;
	}

	@Override
	public <T> T[] toArray(InternalEObject object, EStructuralFeature feature, T[] array) {
		return null;
	}

	@Override
	public int hashCode(InternalEObject object, EStructuralFeature feature) {
		return 0;
	}

	@Override
	public InternalEObject getContainer(InternalEObject object) {
		return null;
	}
}
