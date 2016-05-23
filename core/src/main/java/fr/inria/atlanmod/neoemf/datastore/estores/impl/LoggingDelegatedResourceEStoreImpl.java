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
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * A {@link SearcheableResourceEStore} wrapper that logs every call to its
 * methods in the {@link NeoLogger}
 * 
 */
public class LoggingDelegatedResourceEStoreImpl extends DelegatedResourceEStoreImpl implements SearcheableResourceEStore {

	public LoggingDelegatedResourceEStoreImpl(SearcheableResourceEStore eStore) {
		super(eStore);
	}

	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		NeoLogger.info("Called get for {0}.{1}[{2}]", object, feature.getName(), index);
		return super.get(object, feature, index);
	}

	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		NeoLogger.info("Called set for {0}.{1}[{2}] with value {3}", object, feature.getName(), index, value);
		return super.set(object, feature, index, value);
	}

	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		NeoLogger.info("Called isSet for {0}.{1}", object, feature.getName());
		return super.isSet(object, feature);
	}

	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		NeoLogger.info("Called unSet for {0}.{1}", object, feature.getName());
		super.unset(object, feature);
	}

	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		NeoLogger.info("Called isEmtpy for {0}.{1}", object, feature.getName());
		return super.isEmpty(object, feature);
	}

	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		NeoLogger.info("Called size for {0}.{1}", object, feature.getName());
		return super.size(object, feature);
	}

	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		NeoLogger.info("Called contains for {0}.{1} with value {2}", object, feature.getName(), value);
		return super.contains(object, feature, value);
	}

	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		NeoLogger.info("Called indexOf for {0}.{1} with value {2}", object, feature.getName(), value);
		return super.indexOf(object, feature, value);
	}

	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		NeoLogger.info("Called lastIndexOf for {0}.{1} with value {2}", object, feature.getName(), value);
		return super.lastIndexOf(object, feature, value);
	}

	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		NeoLogger.info("Called add for {0}.{1}[{2}] with value {3}", object, feature.getName(), index, value);
		super.add(object, feature, index, value);
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		NeoLogger.info("Called remove for {0}.{1}[{2}]", object, feature.getName(), index);
		return super.remove(object, feature, index);
	}

	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		NeoLogger.info("Called move for {0}.{1} from [{2}] to [{3}]", object, feature.getName(), sourceIndex, targetIndex);
		return super.move(object, feature, targetIndex, sourceIndex);
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		NeoLogger.info("Called clear for {0}.{1}[{2}]", object, feature.getName());
		super.clear(object, feature);
	}

	@Override
	public Object[] toArray(InternalEObject object, EStructuralFeature feature) {
		NeoLogger.info("Called toArray for {0}.{1}", object, feature.getName());
		return super.toArray(object, feature);
	}

	@Override
	public <T> T[] toArray(InternalEObject object, EStructuralFeature feature, T[] array) {
		NeoLogger.info("Called toArray for {0}.{1}", object, feature.getName());
		return super.toArray(object, feature, array);
	}

	@Override
	public int hashCode(InternalEObject object, EStructuralFeature feature) {
		NeoLogger.info("Called hashCode for {0}.{1}", object, feature.getName());
		return super.hashCode(object, feature);
	}

	@Override
	public InternalEObject getContainer(InternalEObject object) {
		NeoLogger.info("Called getContainer for {0}", object);
		return super.getContainer(object);
	}

	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		NeoLogger.info("Called getContainingFeature for {0}", object);
		return super.getContainingFeature(object);
	}

	@Override
	public EObject create(EClass eClass) {
		NeoLogger.info("Called create for {0}", eClass.getName());
		return super.create(eClass);
	}

	@Override
	public Resource resource() {
		NeoLogger.info("Called getResource");
		return super.resource();
	}

	@Override
	public EObject eObject(Id id) {
		NeoLogger.info("Called getEObject with value {0}", id);
		return super.eObject(id);
	}
}
