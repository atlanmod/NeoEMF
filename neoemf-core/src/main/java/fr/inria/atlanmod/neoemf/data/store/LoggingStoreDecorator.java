/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * A {@link PersistentStore} wrapper that logs every call to its methods in the {@link NeoLogger}.
 */
public class LoggingStoreDecorator extends AbstractPersistentStoreDecorator {

    /**
     * Constructs a new {@code LoggingStoreDecorator}.
     *
     * @param store the underlying store
     */
    public LoggingStoreDecorator(PersistentStore store) {
        super(store);
    }

    @Override
    public Object get(InternalEObject internalObject, EStructuralFeature feature, int index) {
        NeoLogger.info("Called get for {0}.{1}[{2}]", internalObject, feature.getName(), index);
        return super.get(internalObject, feature, index);
    }

    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        NeoLogger.info("Called set for {0}.{1}[{2}] with value {3}", internalObject, feature.getName(), index, value);
        return super.set(internalObject, feature, index, value);
    }

    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        NeoLogger.info("Called isSet for {0}.{1}", internalObject, feature.getName());
        return super.isSet(internalObject, feature);
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        NeoLogger.info("Called unSet for {0}.{1}", internalObject, feature.getName());
        super.unset(internalObject, feature);
    }

    @Override
    public boolean isEmpty(InternalEObject internalObject, EStructuralFeature feature) {
        NeoLogger.info("Called isEmtpy for {0}.{1}", internalObject, feature.getName());
        return super.isEmpty(internalObject, feature);
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        NeoLogger.info("Called size for {0}.{1}", internalObject, feature.getName());
        return super.size(internalObject, feature);
    }

    @Override
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        NeoLogger.info("Called contains for {0}.{1} with value {2}", internalObject, feature.getName(), value);
        return super.contains(internalObject, feature, value);
    }

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        NeoLogger.info("Called indexOf for {0}.{1} with value {2}", internalObject, feature.getName(), value);
        return super.indexOf(internalObject, feature, value);
    }

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        NeoLogger.info("Called lastIndexOf for {0}.{1} with value {2}", internalObject, feature.getName(), value);
        return super.lastIndexOf(internalObject, feature, value);
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        NeoLogger.info("Called add for {0}.{1}[{2}] with value {3}", internalObject, feature.getName(), index, value);
        super.add(internalObject, feature, index, value);
    }

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        NeoLogger.info("Called remove for {0}.{1}[{2}]", internalObject, feature.getName(), index);
        return super.remove(internalObject, feature, index);
    }

    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        NeoLogger.info("Called move for {0}.{1} from [{2}] to [{3}]", internalObject, feature.getName(), sourceIndex, targetIndex);
        return super.move(internalObject, feature, targetIndex, sourceIndex);
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        NeoLogger.info("Called clear for {0}.{1}", internalObject, feature.getName());
        super.clear(internalObject, feature);
    }

    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        NeoLogger.info("Called toArray for {0}.{1}", internalObject, feature.getName());
        return super.toArray(internalObject, feature);
    }

    @Override
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, T[] array) {
        NeoLogger.info("Called toArray for {0}.{1}", internalObject, feature.getName());
        return super.toArray(internalObject, feature, array);
    }

    @Override
    public int hashCode(InternalEObject internalObject, EStructuralFeature feature) {
        NeoLogger.info("Called hashCode for {0}.{1}", internalObject, feature.getName());
        return super.hashCode(internalObject, feature);
    }

    @Override
    public InternalEObject getContainer(InternalEObject internalObject) {
        NeoLogger.info("Called getContainer for {0}", internalObject);
        return super.getContainer(internalObject);
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject internalObject) {
        NeoLogger.info("Called getContainingFeature for {0}", internalObject);
        return super.getContainingFeature(internalObject);
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
