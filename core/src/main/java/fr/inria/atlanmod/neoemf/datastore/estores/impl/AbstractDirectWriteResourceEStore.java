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

package fr.inria.atlanmod.neoemf.datastore.estores.impl;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.impl.PersistentEObjectAdapter;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.estores.PersistentEStore;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.Arrays;

public abstract class AbstractDirectWriteResourceEStore<P extends PersistenceBackend> implements PersistentEStore {

    private final Resource.Internal resource;

    protected final P persistenceBackend;

    public AbstractDirectWriteResourceEStore(Resource.Internal resource, P persistenceBackend) {
        this.resource = resource;
        this.persistenceBackend = persistenceBackend;
    }

    @Override
    public Resource.Internal resource() {
        return resource;
    }

    @Override
    public Object get(InternalEObject object, EStructuralFeature feature, int index) {
        PersistentEObject eObject = PersistentEObjectAdapter.getAdapter(object);
        if (feature instanceof EAttribute) {
            return getWithAttribute(eObject, (EAttribute) feature, index);
        } else if (feature instanceof EReference) {
            return getWithReference(eObject, (EReference) feature, index);
        } else {
            throw new IllegalArgumentException(feature.toString());
        }
    }

    protected Object getWithAttribute(PersistentEObject object, EAttribute eAttribute, int index) {
        throw new UnsupportedOperationException();
    }

    protected Object getWithReference(PersistentEObject object, EReference eReference, int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        PersistentEObject eObject = PersistentEObjectAdapter.getAdapter(object);
        if (feature instanceof EAttribute) {
            return setWithAttribute(eObject, (EAttribute) feature, index, value);
        } else if (feature instanceof EReference) {
            PersistentEObject referencedEObject = PersistentEObjectAdapter.getAdapter(value);
            return setWithReference(eObject, (EReference) feature, index, referencedEObject);
        } else {
            throw new IllegalArgumentException(feature.toString());
        }
    }

    protected Object setWithAttribute(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
        throw new UnsupportedOperationException();
    }

    protected Object setWithReference(PersistentEObject object, EReference eReference, int index, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSet(InternalEObject object, EStructuralFeature feature) {
        PersistentEObject eObject = PersistentEObjectAdapter.getAdapter(object);
        if (feature instanceof EAttribute) {
            return isSetWithAttribute(eObject, (EAttribute) feature);
        } else if (feature instanceof EReference) {
            return isSetWithReference(eObject, (EReference) feature);
        } else {
            throw new IllegalArgumentException(feature.toString());
        }
    }

    protected boolean isSetWithAttribute(PersistentEObject object, EAttribute eAttribute) {
        throw new UnsupportedOperationException();
    }

    protected boolean isSetWithReference(PersistentEObject object, EReference eReference) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unset(InternalEObject object, EStructuralFeature feature) {
        PersistentEObject eObject = PersistentEObjectAdapter.getAdapter(object);
        if (feature instanceof EAttribute) {
            unsetWithAttribute(eObject, (EAttribute) feature);
        } else if (feature instanceof EReference) {
            unsetWithReference(eObject, (EReference) feature);
        } else {
            throw new IllegalArgumentException(feature.toString());
        }
    }

    protected void unsetWithAttribute(PersistentEObject object, EAttribute eAttribute) {
        throw new UnsupportedOperationException();
    }

    protected void unsetWithReference(PersistentEObject object, EReference eReference) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
        return size(object, feature) == 0;
    }

    @Override
    public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
        if (value == null) {
            return false;
        }

        PersistentEObject eObject = PersistentEObjectAdapter.getAdapter(object);
        if (feature instanceof EAttribute) {
            return containsWithAttribute(eObject, (EAttribute) feature, value);
        } else if(feature instanceof EReference) {
            PersistentEObject referencedEObject = PersistentEObjectAdapter.getAdapter(value);
            return containsWithReference(eObject, (EReference) feature, referencedEObject);
        } else {
            throw new IllegalArgumentException(feature.toString());
        }
    }

    protected boolean containsWithAttribute(PersistentEObject object, EAttribute eAttribute, Object value) {
        throw new UnsupportedOperationException();
    }

    protected boolean containsWithReference(PersistentEObject object, EReference eReference, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
        PersistentEObject eObject = PersistentEObjectAdapter.getAdapter(object);
        if(feature instanceof EAttribute) {
            return indexOfWithAttribute(eObject, (EAttribute) feature, value);
        } else if(feature instanceof EReference) {
            PersistentEObject referencedEObject = PersistentEObjectAdapter.getAdapter(value);
            return indexOfWithReference(eObject, (EReference) feature, referencedEObject);
        } else {
            throw new IllegalArgumentException(feature.toString());
        }
    }

    protected int indexOfWithAttribute(PersistentEObject object, EAttribute eAttribute, Object value) {
        throw new UnsupportedOperationException();
    }

    protected int indexOfWithReference(PersistentEObject object, EReference eReference, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
        PersistentEObject eObject = PersistentEObjectAdapter.getAdapter(object);
        if(feature instanceof EAttribute) {
            return lastIndexOfWithAttribute(eObject, (EAttribute) feature, value);
        } else if(feature instanceof EReference) {
            PersistentEObject referencedEObject = PersistentEObjectAdapter.getAdapter(value);
            return lastIndexOfWithReference(eObject, (EReference) feature, referencedEObject);
        } else {
            throw new IllegalArgumentException(feature.toString());
        }
    }

    protected int lastIndexOfWithAttribute(PersistentEObject object, EAttribute eAttribute, Object value) {
        throw new UnsupportedOperationException();
    }

    protected int lastIndexOfWithReference(PersistentEObject object, EReference eReference, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        PersistentEObject eObject = PersistentEObjectAdapter.getAdapter(object);
        if (feature instanceof EAttribute) {
            addWithAttribute(eObject, (EAttribute) feature, index, value);
        } else if (feature instanceof EReference) {
            PersistentEObject referencedEObject = PersistentEObjectAdapter.getAdapter(value);
            addWithReference(eObject, (EReference) feature, index, referencedEObject);
        } else {
            throw new IllegalArgumentException(feature.toString());
        }
    }

    protected void addWithAttribute(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
        throw new UnsupportedOperationException();
    }

    protected void addWithReference(PersistentEObject object, EReference eReference, int index, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
        PersistentEObject eObject = PersistentEObjectAdapter.getAdapter(object);
        if (feature instanceof EAttribute) {
            return removeWithAttribute(eObject, (EAttribute) feature, index);
        } else if (feature instanceof EReference) {
            return removeWithReference(eObject, (EReference) feature, index);
        } else {
            throw new IllegalArgumentException(feature.toString());
        }
    }

    protected Object removeWithAttribute(PersistentEObject object, EAttribute eAttribute, int index) {
        throw new UnsupportedOperationException();
    }

    protected Object removeWithReference(PersistentEObject object, EReference eReference, int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear(InternalEObject object, EStructuralFeature feature) {
        PersistentEObject eObject = PersistentEObjectAdapter.getAdapter(object);
        if (feature instanceof EAttribute) {
            clearWithAttribute(eObject, (EAttribute) feature);
        } else if (feature instanceof EReference) {
            clearWithReference(eObject, (EReference) feature);
        } else {
            throw new IllegalArgumentException(feature.toString());
        }
    }

    protected void clearWithAttribute(PersistentEObject object, EAttribute eAttribute) {
        throw new UnsupportedOperationException();
    }

    protected void clearWithReference(PersistentEObject object, EReference eReference) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        Object movedElement = remove(object, feature, sourceIndex);
        add(object, feature, targetIndex, movedElement);
        return movedElement;
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

    @SuppressWarnings("unchecked")
    @Override
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

    protected Object parseProperty(EAttribute eAttribute, Object property) {
        return property != null ? EcoreUtil.createFromString(eAttribute.getEAttributeType(), property.toString()) : null;
    }

    protected Object serializeToProperty(EAttribute eAttribute, Object value) {
        return value != null ? EcoreUtil.convertToString(eAttribute.getEAttributeType(), value) : null;
    }

    @Override
    public PersistentEStore getEStore() {
        return this;
    }

    @Override
    public void save() {
        persistenceBackend.save();
    }
}
