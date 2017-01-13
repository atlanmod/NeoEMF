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

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.Arrays;

import static java.util.Objects.isNull;

/**
 * The abstract implementation of {@link DirectWriteStore}.
 *
 * @param <P> the type of the supported {@link PersistenceBackend}
 */
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

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #getAttribute(PersistentEObject, EAttribute, int)
     * @see #getReference(PersistentEObject, EReference, int)
     */
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

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #setAttribute(PersistentEObject, EAttribute, int, Object)
     * @see #setReference(PersistentEObject, EReference, int, PersistentEObject)
     */
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

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #isSetAttribute(PersistentEObject, EAttribute)
     * @see #isSetReference(PersistentEObject, EReference)
     */
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

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #unsetAttribute(PersistentEObject, EAttribute)
     * @see #unsetReference(PersistentEObject, EReference)
     */
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

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #containsAttribute(PersistentEObject, EAttribute, Object)
     * @see #containsReference(PersistentEObject, EReference, PersistentEObject)
     */
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

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #indexOfAttribute(PersistentEObject, EAttribute, Object)
     * @see #indexOfReference(PersistentEObject, EReference, PersistentEObject)
     */
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

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #lastIndexOfAttribute(PersistentEObject, EAttribute, Object)
     * @see #lastIndexOfReference(PersistentEObject, EReference, PersistentEObject)
     */
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

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #addAttribute(PersistentEObject, EAttribute, int, Object)
     * @see #addReference(PersistentEObject, EReference, int, PersistentEObject)
     */
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

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #removeAttribute(PersistentEObject, EAttribute, int)
     * @see #removeReference(PersistentEObject, EReference, int)
     */
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

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #clearAttribute(PersistentEObject, EAttribute)
     * @see #clearReference(PersistentEObject, EReference)
     */
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

    /**
     * Returns the value at the {@code index} in the content of the {@code attribute} of the {@code object}.
     *
     * @param object    the object
     * @param attribute an attribute of the {@code object}
     * @param index     an index within the content or {@link PersistentStore#NO_INDEX}
     *
     * @return the value
     *
     * @see #get(InternalEObject, EStructuralFeature, int)
     */
    protected Object getAttribute(PersistentEObject object, EAttribute attribute, int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the value at the {@code index} in the content of the {@code reference} of the {@code object}.
     *
     * @param object    the object
     * @param reference a reference of the {@code object}
     * @param index     an index within the content or {@link PersistentStore#NO_INDEX}
     *
     * @return the value
     *
     * @see #get(InternalEObject, EStructuralFeature, int)
     */
    protected Object getReference(PersistentEObject object, EReference reference, int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets the value at the {@code index} in the content of the {@code attribute} of the {@code object}.
     *
     * @param object    the object
     * @param attribute an attribute of the {@code object}
     * @param index     an index within the content or {@link PersistentStore#NO_INDEX}
     * @param value     the new value
     *
     * @return the previous value
     *
     * @see #set(InternalEObject, EStructuralFeature, int, Object)
     */
    protected Object setAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets the value at the {@code index} in the content of the {@code reference} of the {@code object}.
     *
     * @param object    the object
     * @param reference a reference of the {@code object}
     * @param index     an index within the content or {@link PersistentStore#NO_INDEX}
     * @param value     the new value
     *
     * @return the previous value
     *
     * @see #set(InternalEObject, EStructuralFeature, int, Object)
     */
    protected Object setReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns whether the {@code attribute} of the {@code object} is considered set.
     *
     * @param object    the object
     * @param attribute an attribute of the {@code object}
     *
     * @return {@code true} if the attribute is considered set
     *
     * @see #isSet(InternalEObject, EStructuralFeature)
     */
    protected boolean isSetAttribute(PersistentEObject object, EAttribute attribute) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns whether the {@code reference} of the {@code object} is considered set.
     *
     * @param object    the object
     * @param reference a reference of the {@code object}
     *
     * @return {@code true} if the reference is considered set
     *
     * @see #isSet(InternalEObject, EStructuralFeature)
     */
    protected boolean isSetReference(PersistentEObject object, EReference reference) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unsets the {@code attribute} of the {@code object}.
     *
     * @param object    the object
     * @param attribute an attribute of the {@code object}
     *
     * @see #unset(InternalEObject, EStructuralFeature)
     */
    protected void unsetAttribute(PersistentEObject object, EAttribute attribute) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unsets the {@code reference} of the {@code object}.
     *
     * @param object    the object
     * @param reference a reference of the {@code object}
     *
     * @see #unset(InternalEObject, EStructuralFeature)
     */
    protected void unsetReference(PersistentEObject object, EReference reference) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns whether the content of the {@code attribute} of the {@code object} contains the given {@code value}.
     *
     * @param object    the object
     * @param attribute a many-valued attribute of the {@code object}
     * @param value     the value to look for
     *
     * @return {@code true} if the attribute contains the given value
     *
     * @see #contains(InternalEObject, EStructuralFeature, Object)
     */
    protected boolean containsAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns whether the content of the {@code reference} of the {@code object} contains the given {@code value}.
     *
     * @param object    the object
     * @param reference a many-valued reference of the {@code object}
     * @param value     the value to look for
     *
     * @return {@code true} if the reference contains the given value
     *
     * @see #contains(InternalEObject, EStructuralFeature, Object)
     */
    protected boolean containsReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the first index of the given {@code value} in the content of the {@code attribute} of the {@code object}.
     *
     * @param object    the object
     * @param attribute a many-valued attribute of the {@code object}
     * @param value     the value to look for
     *
     * @return the first index of the given value, or {@link PersistentStore#NO_INDEX} if it is not found
     *
     * @see #indexOf(InternalEObject, EStructuralFeature, Object)
     */
    protected int indexOfAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the first index of the given {@code value} in the content of the {@code reference} of the {@code object}.
     *
     * @param object    the object
     * @param reference a many-valued reference of the {@code object}
     * @param value     the value to look for
     *
     * @return the first index of the given value, or {@link PersistentStore#NO_INDEX} if it is not found
     *
     * @see #indexOf(InternalEObject, EStructuralFeature, Object)
     */
    protected int indexOfReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the last index of the given {@code value} in the content of the {@code attribute} of the {@code object}.
     *
     * @param object    the object
     * @param attribute a many-valued attribute of the {@code object}
     * @param value     the value to look for
     *
     * @return the last index of the given value, or {@link PersistentStore#NO_INDEX} if it is not found
     *
     * @see #lastIndexOf(InternalEObject, EStructuralFeature, Object)
     */
    protected int lastIndexOfAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the last index of the given {@code value} in the content of the {@code reference} of the {@code object}.
     *
     * @param object    the object
     * @param reference a many-valued reference of the {@code object}
     * @param value     the value to look for
     *
     * @return the last index of the given value, or {@link PersistentStore#NO_INDEX} if it is not
     *
     * @see #lastIndexOf(InternalEObject, EStructuralFeature, Object)
     */
    protected int lastIndexOfReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Adds the {@code value} at the {@code index} in the content of the {@code attribute} of the {@code object}.
     *
     * @param object    the object
     * @param attribute a many-valued attribute of the {@code object}
     * @param index     an index within the content
     * @param value     the value to add
     *
     * @see #add(InternalEObject, EStructuralFeature, int, Object)
     */
    protected void addAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Adds the {@code value} at the {@code index} in the content of the {@code reference} of the {@code object}.
     *
     * @param object the object
     * @param reference a many-valued reference of the {@code object}
     * @param index an index within the content
     * @param value the value to add
     *
     * @see #add(InternalEObject, EStructuralFeature, int, Object)
     */
    protected void addReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the value at the {@code index} in the content of the {@code attribute} of the {@code object}.
     *
     * @param object the object
     * @param attribute a many-valued attribute of the {@code object}
     * @param index the index within the content of the value to remove
     *
     * @return the removed value
     *
     * @see #remove(InternalEObject, EStructuralFeature, int)
     */
    protected Object removeAttribute(PersistentEObject object, EAttribute attribute, int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the value at the {@code index} in the content of the {@code reference} of the {@code object}.
     *
     * @param object the object
     * @param reference a many-valued reference of the {@code object}
     * @param index the index within the content of the value to remove
     *
     * @return the removed value
     *
     * @see #remove(InternalEObject, EStructuralFeature, int)
     */
    protected Object removeReference(PersistentEObject object, EReference reference, int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes all values form the content of the {@code attribute} of the {@code object}.
     *
     * @param object the object
     * @param attribute a many-valued attribute of the {@code object}
     *
     * @see #clear(InternalEObject, EStructuralFeature)
     */
    protected void clearAttribute(PersistentEObject object, EAttribute attribute) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes all values form the content of the {@code reference} of the {@code object}.
     *
     * @param object the object
     * @param reference a many-valued reference of the {@code object}
     *
     * @see #clear(InternalEObject, EStructuralFeature)
     */
    protected void clearReference(PersistentEObject object, EReference reference) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an instance of the {@code attribute}.
     *
     * @param attribute the attribute to instantiate
     * @param property the string value of the attribute
     *
     * @return an instance of the attribute
     *
     * @see EcoreUtil#createFromString(EDataType, String)
     */
    protected Object parseProperty(EAttribute attribute, Object property) {
        return isNull(property) ? null : EcoreUtil.createFromString(attribute.getEAttributeType(), property.toString());
    }

    /**
     * Converts an instance of the {@code attribute} to a string literal representation.
     *
     * @param attribute the attribute to instantiate
     * @param value a value of the attribute
     *
     * @return the string literal representation of the value
     *
     * @see EcoreUtil#convertToString(EDataType, Object)
     */
    protected Object serializeToProperty(EAttribute attribute, Object value) {
        return isNull(value) ? null : EcoreUtil.convertToString(attribute.getEAttributeType(), value);
    }
}
