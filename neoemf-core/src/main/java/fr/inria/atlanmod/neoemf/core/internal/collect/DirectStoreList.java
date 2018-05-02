/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal.collect;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.store.Storable;
import fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.DelegatingEcoreEList;
import org.eclipse.emf.ecore.util.EcoreEList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

/**
 * A {@link List} representing a multi-valued feature which behaves as a proxy and that delegates its operations to the
 * associated {@link fr.inria.atlanmod.neoemf.data.store.Store}.
 *
 * @param <E> the type of elements in this list
 *
 * @see PersistentEObject#eStore()
 */
@ParametersAreNonnullByDefault
public class DirectStoreList<E> extends DelegatingEcoreEList.Dynamic<E> implements Storable {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = 2630358403343923944L;

    /**
     * The object that holds the store.
     */
    @Nonnull
    private final transient Storable storable;

    /**
     * Constructs a new {@code DirectStoreList}.
     *
     * @param owner   the owner the {@code feature}
     * @param feature the feature associated with this list
     */
    public DirectStoreList(PersistentEObject owner, EStructuralFeature feature) {
        super(owner, feature);
        this.storable = owner;
    }

    @Nonnull
    @Override
    public StoreAdapter eStore() {
        return storable.eStore();
    }

    // region Delegating methods

    @Override
    protected List<E> delegateList() {
        throw new UnsupportedOperationException("delegateList");
    }

    @Override
    @Nonnegative
    protected int delegateSize() {
        return eStore().size(owner, eStructuralFeature);
    }

    @Override
    protected boolean delegateIsEmpty() {
        return eStore().isEmpty(owner, eStructuralFeature);
    }

    @Override
    protected boolean delegateContains(@Nullable Object object) {
        return eStore().contains(owner, eStructuralFeature, object);
    }

    @Override
    protected boolean delegateContainsAll(@Nullable Collection<?> collection) {
        if (isNull(collection) || collection.isEmpty()) {
            return false;
        }

        if (collection.size() == 1) {
            return contains(collection.iterator().next());
        }

        return delegateGetAll().containsAll(collection);
    }

    @Override
    @Nonnegative
    protected int delegateIndexOf(@Nullable Object object) {
        return eStore().indexOf(owner, eStructuralFeature, object);
    }

    @Override
    @Nonnegative
    protected int delegateLastIndexOf(@Nullable Object object) {
        return eStore().lastIndexOf(owner, eStructuralFeature, object);
    }

    @Override
    protected Object[] delegateToArray() {
        return eStore().toArray(owner, eStructuralFeature);
    }

    @Override
    protected <T> T[] delegateToArray(T[] array) {
        return eStore().toArray(owner, eStructuralFeature, array);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected E delegateGet(int index) {
        return (E) eStore().get(owner, eStructuralFeature, index);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected E delegateSet(int index, E object) {
        return (E) eStore().set(owner, eStructuralFeature, index, object);
    }

    @Override
    protected void delegateAdd(E object) {
        delegateAdd(InternalEObject.EStore.NO_INDEX, object);
    }

    @Override
    protected void delegateAdd(int index, E object) {
        eStore().add(owner, eStructuralFeature, index, object);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected E delegateRemove(int index) {
        return (E) eStore().remove(owner, eStructuralFeature, index);
    }

    @Override
    protected void delegateClear() {
        eStore().clear(owner, eStructuralFeature);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected E delegateMove(int targetIndex, int sourceIndex) {
        return (E) eStore().move(owner, eStructuralFeature, targetIndex, sourceIndex);
    }

    @Override
    // FIXME Does not resolve
    protected boolean delegateEquals(@Nullable Object object) {
        if (object == this) {
            return true;
        }

        if (!List.class.isInstance(object)) {
            return false;
        }

        List<?> list = List.class.cast(object);
        return list.size() == delegateSize() && list.equals(delegateGetAll());
    }

    @Override
    protected int delegateHashCode() {
        return eStore().hashCode(owner, eStructuralFeature);
    }

    @Override
    protected String delegateToString() {
        return delegateGetAll()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    @Override
    protected Iterator<E> delegateIterator() {
        return iterator();
    }

    @Override
    protected ListIterator<E> delegateListIterator() {
        return listIterator();
    }

    @Override
    protected List<E> delegateBasicList() {
        final Object[] data = delegateToArray();

        return data.length == 0
                ? ECollections.emptyEList()
                : new EcoreEList.UnmodifiableEList<>(owner, eStructuralFeature, data.length, data);
    }

    /**
     * TODO
     *
     * @return
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    protected List<E> delegateGetAll() {
        return List.class.cast(eStore().getAll(owner, eStructuralFeature));
    }

    /**
     * TODO
     *
     * @param index
     * @param collection
     *
     * @return
     */
    @Nonnegative
    protected int delegateAddAll(int index, Collection<? extends E> collection) {
        return eStore().addAll(owner, eStructuralFeature, index, collection);
    }

    // endregion

    // region Overrides from `DelegatingNotifyingListImpl`

    @Override
    // FIXME Using EStore.NO_INDEX can cause issues with notifications
    public void addUnique(E object) {
        addUnique(InternalEObject.EStore.NO_INDEX, object);
    }

    @Override
    protected void doAddUnique(E object) {
        // Only called from #addUnique(Object)
        throw new UnsupportedOperationException("doAddUnique(Object)");
    }

    @Override
    public boolean addAllUnique(Collection<? extends E> collection) {
        final int index = isNotificationRequired() || hasInverse()
                ? size()
                : InternalEObject.EStore.NO_INDEX;

        return addAllUnique(index, collection);
    }

    @Override
    protected boolean doAddAllUnique(Collection<? extends E> collection) {
        // Only called from #addAllUnique(Collection<? extends E>)
        throw new UnsupportedOperationException("doAddAllUnique(Collection<? extends E>)");
    }

    @Override
    protected boolean doAddAllUnique(int index, Collection<? extends E> collection) {
        ++modCount;

        if (collection.isEmpty()) {
            return false;
        }

        // Validate all values before insertion
        Collection<? extends E> validatedCollection = collection.stream()
                .map(e -> validate(index, e))
                .collect(Collectors.toList());

        int firstIndex = delegateAddAll(index, validatedCollection);

        for (E object : validatedCollection) {
            didAdd(firstIndex, object);
            didChange();
            firstIndex++;
        }

        return true;
    }

    @Override
    public boolean addAllUnique(Object[] objects, int start, int end) {
        final int index = isNotificationRequired() || hasInverse()
                ? size()
                : InternalEObject.EStore.NO_INDEX;

        return addAllUnique(index, objects, start, end);
    }

    @Override
    protected boolean doAddAllUnique(Object[] objects, int start, int end) {
        // Only called from #addAllUnique(Object[], int, int)
        throw new UnsupportedOperationException("doAddAllUnique(Object[], int, int)");
    }

    @Override
    @SuppressWarnings("unchecked")
    protected boolean doAddAllUnique(int index, Object[] objects, int start, int end) {
        return doAddAllUnique(index, Arrays.asList((E[]) objects));
    }

    // endregion

    // region Iterators

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

    // endregion
}
