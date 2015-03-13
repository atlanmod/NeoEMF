/*******************************************************************************
 * Copyright (c) 2014 Abel Gómez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel Gómez - initial API and implementation
 ******************************************************************************/
package fr.inria.atlanmod.kyanos.datastore.estores.impl;

import java.text.MessageFormat;

import org.eclipse.core.runtime.ILog;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.kyanos.KyanosPlugin;
import fr.inria.atlanmod.kyanos.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.kyanos.logger.Logger;

/**
 * A {@link SearcheableResourceEStore} wrapper that logs every call to its
 * methods in the {@link KyanosPlugin} default {@link ILog}
 * 
 * @author agomez
 * 
 */
public class LoggingDelegatedResourceEStoreImpl extends DelegatedResourceEStoreImpl implements SearcheableResourceEStore {

	public LoggingDelegatedResourceEStoreImpl(SearcheableResourceEStore eStore) {
		super(eStore);
	}

	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called get for {0}.{1}[{2}]", object, feature.getName(), index));
		return super.get(object, feature, index);
	}

	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called set for {0}.{1}[{2}] with value {3}", object, feature.getName(), index, value));
		return super.set(object, feature, index, value);
	}

	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called isSet for {0}.{1}", object, feature.getName()));
		return super.isSet(object, feature);
	}

	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called unSet for {0}.{1}", object, feature.getName()));
		super.unset(object, feature);
	}

	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called isEmtpy for {0}.{1}", object, feature.getName()));
		return super.isEmpty(object, feature);
	}

	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called size for {0}.{1}", object, feature.getName()));
		return super.size(object, feature);
	}

	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called contains for {0}.{1} with value {2}", object, feature.getName(), value));
		return super.contains(object, feature, value);
	}

	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called indexOf for {0}.{1} with value {2}", object, feature.getName(), value));
		return super.indexOf(object, feature, value);
	}

	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called lastIndexOf for {0}.{1} with value {2}", object, feature.getName(), value));
		return super.lastIndexOf(object, feature, value);
	}

	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called add for {0}.{1}[{2}] with value {3}", object, feature.getName(), index, value));
		super.add(object, feature, index, value);
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called remove for {0}.{1}[{2}]", object, feature.getName(), index));
		return super.remove(object, feature, index);
	}

	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		Logger.log(Logger.SEVERITY_INFO,
				MessageFormat.format("Called move for {0}.{1} from [{2}] to [{3}]", object, feature.getName(), sourceIndex, targetIndex));
		return super.move(object, feature, targetIndex, sourceIndex);
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called clear for {0}.{1}[{2}]", object, feature.getName()));
		super.clear(object, feature);
	}

	@Override
	public Object[] toArray(InternalEObject object, EStructuralFeature feature) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called toArray for {0}.{1}", object, feature.getName()));
		return super.toArray(object, feature);
	}

	@Override
	public <T> T[] toArray(InternalEObject object, EStructuralFeature feature, T[] array) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called toArray for {0}.{1}", object, feature.getName()));
		return super.toArray(object, feature, array);
	}

	@Override
	public int hashCode(InternalEObject object, EStructuralFeature feature) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called hashCode for {0}.{1}", object, feature.getName()));
		return super.hashCode(object, feature);
	}

	@Override
	public InternalEObject getContainer(InternalEObject object) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called getContainer for {0}", object));
		return super.getContainer(object);
	}

	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called getContainingFeature for {0}", object));
		return super.getContainingFeature(object);
	}

	@Override
	public EObject create(EClass eClass) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called create for {0}", eClass.getName()));
		return super.create(eClass);
	}

	@Override
	public Resource getResource() {
		Logger.log(Logger.SEVERITY_INFO, "Called getResource");
		return super.getResource();
	}

	@Override
	public EObject getEObject(String id) {
		Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("Called getEObject with value {0}", id));
		return super.getEObject(id);
	}
}
