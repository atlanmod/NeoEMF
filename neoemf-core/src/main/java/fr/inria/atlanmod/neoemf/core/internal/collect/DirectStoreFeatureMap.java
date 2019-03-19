/*
 * Copyright (c) 2013 Atlanmod.
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
import org.eclipse.emf.ecore.util.DelegatingFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.FeatureMap;

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

import static java.util.Objects.isNull;
import static org.atlanmod.commons.Preconditions.checkPositionIndex;

/**
 * A {@link org.eclipse.emf.ecore.util.FeatureMap} representing a multi-valued feature which behaves as a proxy and that
 * delegates its operations to the associated {@link fr.inria.atlanmod.neoemf.data.store.Store}.
 *
 * @see PersistentEObject#eStore()
 */
@ParametersAreNonnullByDefault
// Synchronize modifications from `DirectStoreList`
// TODO Re-implements all methods related to addition without index
public class DirectStoreFeatureMap extends DelegatingFeatureMap implements Storable {

    /**
     * The object that holds the store.
     */
    @Nonnull
    private final transient Storable storable;

    /**
     * Constructs a new {@code DirectStoreFeatureMap}.
     *
     * @param owner   the owner the {@code feature}
     * @param feature the feature associated with this list
     */
    public DirectStoreFeatureMap(PersistentEObject owner, EStructuralFeature feature) {
        super(owner, feature);
        this.storable = owner;
    }

    @Nonnull
    @Override
    public StoreAdapter eStore() {
        return storable.eStore();
    }

    // region Delegating methods (identical to DirectStoreList)

    @Override
    protected List<Entry> delegateList() {
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
    protected Entry delegateGet(int index) {
        return (Entry) eStore().get(owner, eStructuralFeature, index);
    }

    @Override
    protected Entry delegateSet(int index, Entry object) {
        return (Entry) eStore().set(owner, eStructuralFeature, index, object);
    }

    @Override
    protected void delegateAdd(Entry object) {
        delegateAdd(InternalEObject.EStore.NO_INDEX, object);
    }

    @Override
    protected void delegateAdd(int index, Entry object) {
        eStore().add(owner, eStructuralFeature, index, object);
    }

    @Override
    protected Entry delegateRemove(int index) {
        return (Entry) eStore().remove(owner, eStructuralFeature, index);
    }

    @Override
    protected void delegateClear() {
        eStore().clear(owner, eStructuralFeature);
    }

    @Override
    protected Entry delegateMove(int targetIndex, int sourceIndex) {
        return (Entry) eStore().move(owner, eStructuralFeature, targetIndex, sourceIndex);
    }

    @Override
    // FIXME Does not resolve
    protected boolean delegateEquals(@Nullable Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof List)) {
            return false;
        }

        List<?> list = (List) object;
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
    protected Iterator<Entry> delegateIterator() {
        return iterator();
    }

    @Override
    protected ListIterator<Entry> delegateListIterator() {
        return listIterator();
    }

    @Override
    protected List<Entry> delegateBasicList() {
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
    protected List<Entry> delegateGetAll() {
        return (List) eStore().getAll(owner, eStructuralFeature);
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
    protected int delegateAddAll(int index, Collection<? extends Entry> collection) {
        return eStore().addAll(owner, eStructuralFeature, index, collection);
    }

    // endregion

    // region Overrides from `DelegatingNotifyingListImpl`

    @Override
    protected boolean doAddAllUnique(int index, Collection<? extends Entry> collection) {
        ++modCount;

        if (collection.isEmpty()) {
            return false;
        }

        // Validate all values before insertion
        Collection<? extends Entry> validatedCollection = collection.stream()
                .map(e -> validate(index, e))
                .collect(Collectors.toList());

        int firstIndex = delegateAddAll(index, validatedCollection);

        for (Entry object : validatedCollection) {
            didAdd(firstIndex, object);
            didChange();
            firstIndex++;
        }

        return true;
    }

    @Override
    protected boolean doAddAllUnique(int index, Object[] objects, int start, int end) {
        return doAddAllUnique(index, Arrays.asList((Entry[]) objects));
    }

    // endregion

    // region Iterators (identical to DirectStoreList)

    @Nonnull
    @Override
    public Iterator<Entry> basicIterator() {
        return new NonResolvingLazyIterator<>(this, () -> modCount);
    }

    @Nonnull
    @Override
    public ListIterator<FeatureMap.Entry> basicListIterator() {
        return basicListIterator(0);
    }

    @Nonnull
    @Override
    public ListIterator<FeatureMap.Entry> basicListIterator(int index) {
        // Avoid checking the size when index == 0
        checkPositionIndex(index, index == 0 ? 0 : size());
        return new NonResolvingLazyListIterator<>(this, () -> modCount, index);
    }

    @Nonnull
    @Override
    public Iterator<Entry> iterator() {
        return new LazyIterator<>(this, () -> modCount);
    }

    @Nonnull
    @Override
    public ListIterator<FeatureMap.Entry> listIterator() {
        return listIterator(0);
    }

    @Nonnull
    @Override
    public ListIterator<FeatureMap.Entry> listIterator(int index) {
        // Avoid checking the size when index == 0
        checkPositionIndex(index, index == 0 ? 0 : size());
        return new LazyListIterator<>(this, () -> modCount, index);
    }

    // endregion
}
