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

package fr.inria.atlanmod.neoemf.datastore.estores.impl;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.estores.DirectWriteResourceEStore;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

public class AutocommitResourceEStoreImpl implements DirectWriteResourceEStore {

	/**
	 * Default number of allowed modifications (100000) between commits on the
	 * underlying {@link InternalEObject.EStore}.
	 */
	private static final int OPS_BETWEEN_COMMITS_DEFAULT = 100000;

	/**
	 * Number of allowed modifications between commits on the underlying {@link InternalEObject.EStore}
	 * for this {@link AutocommitResourceEStoreImpl}.
	 */
	private final int opsBetweenCommits;

	/**
	 * Current number of modifications modulo {@link #opsBetweenCommits}.
	 */
	private int opCount;

	private DirectWriteResourceEStore eStore;

	/**
	 * Allows to specify the number of allowed modification on the underlying {@link InternalEObject.EStore} before
	 * saving automatically.
	 */
	public AutocommitResourceEStoreImpl(DirectWriteResourceEStore eStore, int opsBetweenCommits) {
		this.opCount = 0;
		this.opsBetweenCommits = opsBetweenCommits;
		this.eStore = eStore;
	    NeoLogger.info("Autocommit Store Created (chunk = {0})", opsBetweenCommits);
	}

	/**
	 * Allows to make {@link #OPS_BETWEEN_COMMITS_DEFAULT} modifications on the underlying
	 * {@link InternalEObject.EStore} before saving automatically.
	 */
	public AutocommitResourceEStoreImpl(DirectWriteResourceEStore eStore) {
		this(eStore, OPS_BETWEEN_COMMITS_DEFAULT);
	}

	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		eStore.add(object, feature, index, value);
		incrementAndCommit();
	}

	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		Object returnValue = eStore.set(object, feature, index, value);
		incrementAndCommit();
		return returnValue;
	}

	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		Object returnValue = eStore.move(object, feature, targetIndex, sourceIndex);
		incrementAndCommit();
		return returnValue;
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		Object returnValue = eStore.remove(object, feature, index);
		incrementAndCommit();
		return returnValue;
	}

	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		eStore.unset(object, feature);
		incrementAndCommit();
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		eStore.clear(object, feature);
		incrementAndCommit();
	}

	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		return eStore.get(object, feature, index);
	}

	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		return eStore.isSet(object, feature);
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
	public PersistenceBackend getPersistenceBackend() {
		return eStore.getPersistenceBackend();
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
	public EList<EObject> getAllInstances(EClass eClass, boolean strict) {
		return eStore.getAllInstances(eClass, strict);
	}

	private void incrementAndCommit() {
		opCount = (opCount + 1) % opsBetweenCommits;
		if (opCount == 0) {
			eStore.getPersistenceBackend().save();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			eStore.getPersistenceBackend().save();
		} catch (Exception ex) {
			NeoLogger.error(ex);
		}
		super.finalize();
	}
}
