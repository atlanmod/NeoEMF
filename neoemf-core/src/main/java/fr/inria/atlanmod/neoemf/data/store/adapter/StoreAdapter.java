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

package fr.inria.atlanmod.neoemf.data.store.adapter;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.Resolver;
import fr.inria.atlanmod.neoemf.data.DataManager;
import fr.inria.atlanmod.neoemf.data.store.Store;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

/**
 * An adapter that adapts and translates EMF requests from an {@link InternalEObject.EStore} to a {@link Store}.
 */
@Immutable
@ParametersAreNonnullByDefault
public interface StoreAdapter extends DataManager, InternalEObject.EStore, Resolver<Id, PersistentEObject> {

    /**
     * Returns the adapted store.
     *
     * @return the store
     */
    @Nonnull
    Store store();

    /**
     * Returns the resource to store and access.
     *
     * @return the resource
     */
    @Nullable
    Resource.Internal resource();

    /**
     * Defines the resource to store and access.
     *
     * @param resource the resource
     */
    void resource(@Nullable Resource.Internal resource);

    @Nullable
    @Override
    Object get(InternalEObject internalObject, EStructuralFeature feature, @Nonnegative int index);

    @Nullable
    @Override
    Object set(InternalEObject internalObject, EStructuralFeature feature, @Nonnegative int index, @Nullable Object value);

    @Override
    boolean isSet(InternalEObject internalObject, EStructuralFeature feature);

    @Override
    void unset(InternalEObject internalObject, EStructuralFeature feature);

    @Override
    boolean isEmpty(InternalEObject internalObject, EStructuralFeature feature);

    @Nonnegative
    @Override
    int size(InternalEObject internalObject, EStructuralFeature feature);

    @Override
    boolean contains(InternalEObject internalObject, EStructuralFeature feature, @Nullable Object value);

    @Override
    int indexOf(InternalEObject internalObject, EStructuralFeature feature, @Nullable Object value);

    @Override
    int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, @Nullable Object value);

    @Override
    void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value);

    @Nullable
    @Override
    Object remove(InternalEObject internalObject, EStructuralFeature feature, @Nonnegative int index);

    @Nullable
    @Override
    Object move(InternalEObject internalObject, EStructuralFeature feature, @Nonnegative int targetIndex, @Nonnegative int sourceIndex);

    @Override
    void clear(InternalEObject internalObject, EStructuralFeature feature);

    @Nonnull
    @Override
    Object[] toArray(InternalEObject internalObject, EStructuralFeature feature);

    @Nonnull
    @Override
    <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, @Nullable T[] array);

    @Override
    int hashCode(InternalEObject internalObject, EStructuralFeature feature);

    @Nullable
    @Override
    PersistentEObject getContainer(InternalEObject internalObject);

    @Nullable
    @Override
    EReference getContainingFeature(InternalEObject internalObject);

    @Override
    default PersistentEObject create(EClass eClass) {
        throw new IllegalStateException("EStore#create() should not be called");
    }

    /**
     * Returns all values in the {@link org.eclipse.emf.ecore.EObject#eGet(EStructuralFeature, boolean) content} of the
     * object's feature.
     *
     * @param internalObject the object in question
     * @param feature        a feature of the object
     *
     * @return all values in the content of the object's feature
     */
    @Nonnull
    List<Object> getAll(InternalEObject internalObject, EStructuralFeature feature);

    /**
     * Sets the {@code values} in the {@link org.eclipse.emf.ecore.EObject#eGet(EStructuralFeature, boolean) content} of
     * the
     * object's feature.
     * <p>
     * If the object's feature already have values, then they will be replaced by {@code values}.
     *
     * @param internalObject the object in question
     * @param feature        a feature of the object
     * @param values         the new values
     */
    void setAll(InternalEObject internalObject, EStructuralFeature feature, Collection<?> values);

    /**
     * Adds the {@code values} from the {@code index} in the {@link org.eclipse.emf.ecore.EObject#eGet(EStructuralFeature,
     * boolean) content} of the object's feature.
     *
     * @param internalObject the object in question
     * @param feature        a {@link org.eclipse.emf.ecore.ETypedElement#isMany() many-valued} feature of the object
     * @param index          the first index from which to start the addition within the content. If negative, then
     *                       the addition will begin from the end of the existing content
     * @param values         the value to add
     *
     * @return the first index
     */
    int addAll(InternalEObject internalObject, EStructuralFeature feature, int index, Collection<?> values);

    /**
     * Removes the {@code values} in the {@link org.eclipse.emf.ecore.EObject#eGet(EStructuralFeature, boolean) content}
     * of the object's feature.
     *
     * @param internalObject the object in question
     * @param feature        a {@link org.eclipse.emf.ecore.ETypedElement#isMany() many-valued} feature of the object
     * @param values         the values to remove
     */
    void removeAll(InternalEObject internalObject, EStructuralFeature feature, Collection<?> values);

    /**
     * Retains only the {@code values} in the {@link org.eclipse.emf.ecore.EObject#eGet(EStructuralFeature, boolean)
     * content} of the object's feature.
     *
     * @param internalObject the object in question
     * @param feature        a {@link org.eclipse.emf.ecore.ETypedElement#isMany() many-valued} feature of the object
     * @param values         the values to retain
     */
    void retainAll(InternalEObject internalObject, EStructuralFeature feature, Collection<?> values);

    /**
     * Creates or updates the containment link between {@code object} and {@code container}, and deletes any previous
     * link to {@code object}. The {@code object} is added to the containment list of the {@code container}.
     * <p>
     * The method checks if an existing container is stored and update it if needed.
     *
     * @param object             the object to add in the containment list of the {@code container}
     * @param containerReference the containment reference, from the {@code container} to the {@code object}
     * @param container          the container
     */
    void updateContainment(PersistentEObject object, EReference containerReference, PersistentEObject container);

    /**
     * Removes the containment link between {@code object} and its container, and deletes any previous link to
     * {@code object}.
     *
     * @param object the object to remove from the containment list of its actual container
     */
    void removeContainment(PersistentEObject object);
}
