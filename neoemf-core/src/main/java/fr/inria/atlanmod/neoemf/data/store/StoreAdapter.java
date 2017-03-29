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
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.Resolver;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} used as bridge between a {@link InternalEObject.EStore} and a {@link Store}.
 */
@ParametersAreNonnullByDefault
public interface StoreAdapter extends InternalEObject.EStore, Store, Resolver<Id, PersistentEObject> {

    @Nullable
    @Override
    Object get(InternalEObject internalObject, EStructuralFeature feature, int index);

    @Nullable
    @Override
    Object set(InternalEObject internalObject, EStructuralFeature feature, int index, @Nullable Object value);

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
    default EObject create(EClass eClass) {
        throw new IllegalStateException("EStore#create() should not be called");
    }

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
