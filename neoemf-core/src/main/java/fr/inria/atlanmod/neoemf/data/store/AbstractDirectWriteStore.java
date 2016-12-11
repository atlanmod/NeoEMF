/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.Arrays;

import static java.util.Objects.isNull;

public abstract class AbstractDirectWriteStore<P extends PersistenceBackend> extends AbstractPersistentStore {

    protected final P persistenceBackend;

    private final Resource.Internal resource;

    public AbstractDirectWriteStore(Resource.Internal resource, P persistenceBackend) {
        this.resource = resource;
        this.persistenceBackend = persistenceBackend;
    }

    @Override
    public Resource.Internal resource() {
        return resource;
    }

    @Override
    public PersistentStore getEStore() {
        return this;
    }

    @Override
    public void save() {
        persistenceBackend.save();
    }

    @Override
    public Object get(InternalEObject object, EStructuralFeature feature, int index) {
        PersistentEObject eObject = PersistentEObject.from(object);
        if (feature instanceof EAttribute) {
            return getAttribute(eObject, (EAttribute) feature, index);
        }
        else {
            return getReference(eObject, (EReference) feature, index);
        }
    }

    @Override
    public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        PersistentEObject eObject = PersistentEObject.from(object);
        if (feature instanceof EAttribute) {
            return setAttribute(eObject, (EAttribute) feature, index, value);
        }
        else {
            PersistentEObject referencedEObject = PersistentEObject.from(value);
            return setReference(eObject, (EReference) feature, index, referencedEObject);
        }
    }

    @Override
    public boolean isSet(InternalEObject object, EStructuralFeature feature) {
        PersistentEObject eObject = PersistentEObject.from(object);
        if (feature instanceof EAttribute) {
            return isSetAttribute(eObject, (EAttribute) feature);
        }
        else {
            return isSetReference(eObject, (EReference) feature);
        }
    }

    @Override
    public void unset(InternalEObject object, EStructuralFeature feature) {
        PersistentEObject eObject = PersistentEObject.from(object);
        if (feature instanceof EAttribute) {
            unsetAttribute(eObject, (EAttribute) feature);
        }
        else {
            unsetReference(eObject, (EReference) feature);
        }
    }

    @Override
    public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
        return size(object, feature) == 0;
    }

    @Override
    public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
        if (isNull(value)) {
            return false;
        }

        PersistentEObject eObject = PersistentEObject.from(object);
        if (feature instanceof EAttribute) {
            return containsAttribute(eObject, (EAttribute) feature, value);
        }
        else {
            PersistentEObject referencedEObject = PersistentEObject.from(value);
            return containsReference(eObject, (EReference) feature, referencedEObject);
        }
    }

    @Override
    public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
        PersistentEObject eObject = PersistentEObject.from(object);
        if (feature instanceof EAttribute) {
            return indexOfAttribute(eObject, (EAttribute) feature, value);
        }
        else {
            PersistentEObject referencedEObject = PersistentEObject.from(value);
            return indexOfReference(eObject, (EReference) feature, referencedEObject);
        }
    }

    @Override
    public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
        PersistentEObject eObject = PersistentEObject.from(object);
        if (feature instanceof EAttribute) {
            return lastIndexOfAttribute(eObject, (EAttribute) feature, value);
        }
        else {
            PersistentEObject referencedEObject = PersistentEObject.from(value);
            return lastIndexOfReference(eObject, (EReference) feature, referencedEObject);
        }
    }

    @Override
    public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        PersistentEObject eObject = PersistentEObject.from(object);
        if (feature instanceof EAttribute) {
            addAttribute(eObject, (EAttribute) feature, index, value);
        }
        else {
            PersistentEObject referencedEObject = PersistentEObject.from(value);
            addReference(eObject, (EReference) feature, index, referencedEObject);
        }
    }

    @Override
    public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
        PersistentEObject eObject = PersistentEObject.from(object);
        if (feature instanceof EAttribute) {
            return removeAttribute(eObject, (EAttribute) feature, index);
        }
        else {
            return removeReference(eObject, (EReference) feature, index);
        }
    }

    @Override
    public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        Object movedElement = remove(object, feature, sourceIndex);
        add(object, feature, targetIndex, movedElement);
        return movedElement;
    }

    @Override
    public void clear(InternalEObject object, EStructuralFeature feature) {
        PersistentEObject eObject = PersistentEObject.from(object);
        if (feature instanceof EAttribute) {
            clearAttribute(eObject, (EAttribute) feature);
        }
        else {
            clearReference(eObject, (EReference) feature);
        }
    }

    @Override
    public Object[] toArray(InternalEObject object, EStructuralFeature feature) {
        int size = size(object, feature);
        Object[] result = new Object[size];
        for (int index = 0; index < size; index++) {
            result[index] = get(object, feature, index);
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked") // Unchecked cast 'Object' to 'T'
    public <T> T[] toArray(InternalEObject object, EStructuralFeature feature, T[] array) {
        int size = size(object, feature);
        T[] result = array.length < size ? Arrays.copyOf(array, size) : array;
        for (int index = 0; index < size; index++) {
            result[index] = (T) get(object, feature, index);
        }
        return result;
    }

    @Override
    public int hashCode(InternalEObject object, EStructuralFeature feature) {
        return Arrays.hashCode(toArray(object, feature));
    }

    @Override
    public EObject create(EClass eClass) {
        throw new IllegalStateException("This method should not be called");
    }

    protected Object getAttribute(PersistentEObject object, EAttribute eAttribute, int index) {
        throw new UnsupportedOperationException();
    }

    protected Object getReference(PersistentEObject object, EReference eReference, int index) {
        throw new UnsupportedOperationException();
    }

    protected Object setAttribute(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
        throw new UnsupportedOperationException();
    }

    protected Object setReference(PersistentEObject object, EReference eReference, int index, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    protected boolean isSetAttribute(PersistentEObject object, EAttribute eAttribute) {
        throw new UnsupportedOperationException();
    }

    protected boolean isSetReference(PersistentEObject object, EReference eReference) {
        throw new UnsupportedOperationException();
    }

    protected void unsetAttribute(PersistentEObject object, EAttribute eAttribute) {
        throw new UnsupportedOperationException();
    }

    protected void unsetReference(PersistentEObject object, EReference eReference) {
        throw new UnsupportedOperationException();
    }

    protected boolean containsAttribute(PersistentEObject object, EAttribute eAttribute, Object value) {
        throw new UnsupportedOperationException();
    }

    protected boolean containsReference(PersistentEObject object, EReference eReference, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    protected int indexOfAttribute(PersistentEObject object, EAttribute eAttribute, Object value) {
        throw new UnsupportedOperationException();
    }

    protected int indexOfReference(PersistentEObject object, EReference eReference, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    protected int lastIndexOfAttribute(PersistentEObject object, EAttribute eAttribute, Object value) {
        throw new UnsupportedOperationException();
    }

    protected int lastIndexOfReference(PersistentEObject object, EReference eReference, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    protected void addAttribute(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
        throw new UnsupportedOperationException();
    }

    protected void addReference(PersistentEObject object, EReference eReference, int index, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    protected Object removeAttribute(PersistentEObject object, EAttribute eAttribute, int index) {
        throw new UnsupportedOperationException();
    }

    protected Object removeReference(PersistentEObject object, EReference eReference, int index) {
        throw new UnsupportedOperationException();
    }

    protected void clearAttribute(PersistentEObject object, EAttribute eAttribute) {
        throw new UnsupportedOperationException();
    }

    protected void clearReference(PersistentEObject object, EReference eReference) {
        throw new UnsupportedOperationException();
    }

    protected Object parseProperty(EAttribute eAttribute, Object property) {
        if (isNull(property)) {
            return null;
        }
        return EcoreUtil.createFromString(eAttribute.getEAttributeType(), property.toString());
    }

    protected Object serializeToProperty(EAttribute eAttribute, Object value) {
        if (isNull(value)) {
            return null;
        }
        return EcoreUtil.convertToString(eAttribute.getEAttributeType(), value);
    }
}
