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

public abstract class AbstractDirectWriteStore<P extends PersistenceBackend> extends AbstractPersistentStore implements DirectWriteStore {

    protected final P backend;

    private final Resource.Internal resource;

    public AbstractDirectWriteStore(Resource.Internal resource, P backend) {
        this.resource = resource;
        this.backend = backend;
    }

    @Override
    public Resource.Internal resource() {
        return resource;
    }

    @Override
    // TODO Should returns null instead of this
    public PersistentStore getEStore() {
        return this;
    }

    @Override
    public void save() {
        backend.save();
    }

    @Override
    public Object get(InternalEObject internalObject, EStructuralFeature feature, int index) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        if (feature instanceof EAttribute) {
            return getAttribute(object, (EAttribute) feature, index);
        }
        else {
            return getReference(object, (EReference) feature, index);
        }
    }

    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        if (feature instanceof EAttribute) {
            return setAttribute(object, (EAttribute) feature, index, value);
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            return setReference(object, (EReference) feature, index, referencedObject);
        }
    }

    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        if (feature instanceof EAttribute) {
            return isSetAttribute(object, (EAttribute) feature);
        }
        else {
            return isSetReference(object, (EReference) feature);
        }
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        if (feature instanceof EAttribute) {
            unsetAttribute(object, (EAttribute) feature);
        }
        else {
            unsetReference(object, (EReference) feature);
        }
    }

    @Override
    public boolean isEmpty(InternalEObject internalObject, EStructuralFeature feature) {
        return size(internalObject, feature) == 0;
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        if (isNull(value)) {
            return false;
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        if (feature instanceof EAttribute) {
            return containsAttribute(object, (EAttribute) feature, value);
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            return containsReference(object, (EReference) feature, referencedObject);
        }
    }

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        if (feature instanceof EAttribute) {
            return indexOfAttribute(object, (EAttribute) feature, value);
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            return indexOfReference(object, (EReference) feature, referencedObject);
        }
    }

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        if (feature instanceof EAttribute) {
            return lastIndexOfAttribute(object, (EAttribute) feature, value);
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            return lastIndexOfReference(object, (EReference) feature, referencedObject);
        }
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        if (feature instanceof EAttribute) {
            addAttribute(object, (EAttribute) feature, index, value);
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            addReference(object, (EReference) feature, index, referencedObject);
        }
    }

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        if (feature instanceof EAttribute) {
            return removeAttribute(object, (EAttribute) feature, index);
        }
        else {
            return removeReference(object, (EReference) feature, index);
        }
    }

    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        Object movedElement = remove(internalObject, feature, sourceIndex);
        add(internalObject, feature, targetIndex, movedElement);
        return movedElement;
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        if (feature instanceof EAttribute) {
            clearAttribute(object, (EAttribute) feature);
        }
        else {
            clearReference(object, (EReference) feature);
        }
    }

    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        int size = size(internalObject, feature);
        Object[] array = new Object[size];
        for (int index = 0; index < size; index++) {
            array[index] = get(internalObject, feature, index);
        }
        return array;
    }

    @Override
    @SuppressWarnings("unchecked") // Unchecked cast 'Object' to 'T'
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, T[] array) {
        int size = size(internalObject, feature);
        array = array.length < size ? Arrays.copyOf(array, size) : array;
        for (int index = 0; index < size; index++) {
            array[index] = (T) get(internalObject, feature, index);
        }
        return array;
    }

    @Override
    public EObject create(EClass eClass) {
        throw new IllegalStateException("This method should not be called");
    }

    @Override
    public int hashCode(InternalEObject internalObject, EStructuralFeature feature) {
        return Arrays.hashCode(toArray(internalObject, feature));
    }

    @Override
    public InternalEObject getContainer(InternalEObject internalObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject internalObject) {
        throw new UnsupportedOperationException();
    }

    protected Object getAttribute(PersistentEObject object, EAttribute attribute, int index) {
        throw new UnsupportedOperationException();
    }

    protected Object getReference(PersistentEObject object, EReference reference, int index) {
        throw new UnsupportedOperationException();
    }

    protected Object setAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        throw new UnsupportedOperationException();
    }

    protected Object setReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    protected boolean isSetAttribute(PersistentEObject object, EAttribute attribute) {
        throw new UnsupportedOperationException();
    }

    protected boolean isSetReference(PersistentEObject object, EReference reference) {
        throw new UnsupportedOperationException();
    }

    protected void unsetAttribute(PersistentEObject object, EAttribute attribute) {
        throw new UnsupportedOperationException();
    }

    protected void unsetReference(PersistentEObject object, EReference reference) {
        throw new UnsupportedOperationException();
    }

    protected boolean containsAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        throw new UnsupportedOperationException();
    }

    protected boolean containsReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    protected int indexOfAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        throw new UnsupportedOperationException();
    }

    protected int indexOfReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    protected int lastIndexOfAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        throw new UnsupportedOperationException();
    }

    protected int lastIndexOfReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    protected void addAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        throw new UnsupportedOperationException();
    }

    protected void addReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    protected Object removeAttribute(PersistentEObject object, EAttribute attribute, int index) {
        throw new UnsupportedOperationException();
    }

    protected Object removeReference(PersistentEObject object, EReference reference, int index) {
        throw new UnsupportedOperationException();
    }

    protected void clearAttribute(PersistentEObject object, EAttribute attribute) {
        throw new UnsupportedOperationException();
    }

    protected void clearReference(PersistentEObject object, EReference reference) {
        throw new UnsupportedOperationException();
    }

    protected Object parseProperty(EAttribute attribute, Object property) {
        if (isNull(property)) {
            return null;
        }
        return EcoreUtil.createFromString(attribute.getEAttributeType(), property.toString());
    }

    protected Object serializeToProperty(EAttribute attribute, Object value) {
        if (isNull(value)) {
            return null;
        }
        return EcoreUtil.convertToString(attribute.getEAttributeType(), value);
    }
}
