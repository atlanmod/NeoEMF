/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.store.Storable;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

/**
 * A {@link List} representing a multi-valued feature which behaves as a proxy and that delegates its operations to the
 * associated {@link Store}.
 *
 * @param <E> the type of elements in this list
 *
 * @see PersistentEObject#eStore()
 */
@ParametersAreNonnullByDefault
public class LazyStoreList<E> extends EStoreEObjectImpl.BasicEStoreEList<E> {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = 2630358403343923944L;

    /**
     * The object that holds the store.
     */
    @Nonnull
    private final transient Storable storable;

    /**
     * Constructs a new {@code LazyStoreList}.
     *
     * @param owner   the owner the {@code feature}
     * @param feature the feature associated with this list
     */
    public LazyStoreList(PersistentEObject owner, EStructuralFeature feature) {
        super(owner, feature);
        this.storable = owner;
    }

    @Nonnull
    @Override
    protected StoreAdapter eStore() {
        return storable.eStore();
    }

    // region Delegating methods

    @Override
    protected void delegateAdd(Object object) {
        delegateAdd(InternalEObject.EStore.NO_INDEX, object);
    }

    @Override
    protected boolean delegateContainsAll(Collection<?> collection) {
        if (isNull(collection) || collection.isEmpty()) {
            return false;
        }

        if (collection.size() <= 1) {
            return contains(collection.iterator().next());
        }

        return eStore().getAll(owner, eStructuralFeature).containsAll(collection);
    }

    @Override
    // FIXME Does not resolve
    protected boolean delegateEquals(Object object) {
        if (object == this) {
            return true;
        }

        if (!List.class.isInstance(object)) {
            return false;
        }

        List<?> list = List.class.cast(object);
        return list.size() == delegateSize()
                && list.equals(eStore().getAll(owner, eStructuralFeature));
    }

    @Override
    protected String delegateToString() {
        return eStore().getAll(owner, eStructuralFeature)
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    // endregion

    @Nonnull
    @Override
    public Object[] toArray() {
        return delegateToArray();
    }

    @Nonnull
    @Override
    public <T> T[] toArray(T[] array) {
        return delegateToArray(array);
    }

    @Override
    // FIXME Does not resolve
    public boolean contains(Object object) {
        return delegateContains(object);
    }

    @Override
    // FIXME Does not resolve
    public int indexOf(Object object) {
        return delegateIndexOf(object);
    }

    @Override
    // FIXME Does not resolve
    public int lastIndexOf(Object object) {
        return delegateLastIndexOf(object);
    }

    @Override
    public void addUnique(E object) {
        addUnique(InternalEObject.EStore.NO_INDEX, object);
    }

    @Override
    protected boolean doAddAllUnique(Collection<? extends E> collection) {
        return doAddAllUnique(InternalEObject.EStore.NO_INDEX, collection);
    }

    @Override
    protected boolean doAddAllUnique(int index, Collection<? extends E> collection) {
        ++modCount;

        if (collection.isEmpty()) {
            return false;
        }

        int i = eStore().addAll(owner, eStructuralFeature, index, collection);

        for (E object : collection) {
            didAdd(i, object);
            didChange();
            i++;
        }

        return true;
    }

    @Override
    // TODO Re-implement this method
    public boolean removeAll(Collection<?> collection) {
        return super.removeAll(collection);
    }

    @Nonnull
    @Override
    public Iterator<E> basicIterator() {
        return new NonResolvingLazyIterator<>(this, () -> modCount);
    }

    @Nonnull
    @Override
    public ListIterator<E> basicListIterator() {
        return basicListIterator(0);
    }

    @Nonnull
    @Override
    public ListIterator<E> basicListIterator(int index) {
        // Avoid checking the size when index == 0
        checkPositionIndex(index, index == 0 ? 0 : size());
        return new NonResolvingLazyListIterator<>(this, () -> modCount, index);
    }

    @Nonnull
    @Override
    public Iterator<E> iterator() {
        return new LazyIterator<>(this, () -> modCount);
    }

    @Nonnull
    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Nonnull
    @Override
    public ListIterator<E> listIterator(int index) {
        // Avoid checking the size when index == 0
        checkPositionIndex(index, index == 0 ? 0 : size());
        return new LazyListIterator<>(this, () -> modCount, index);
    }
}
