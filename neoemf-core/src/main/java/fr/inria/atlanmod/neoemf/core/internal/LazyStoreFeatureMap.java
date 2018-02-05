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

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.DelegatingFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.FeatureMap;

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
 * A {@link org.eclipse.emf.ecore.util.FeatureMap} representing a multi-valued feature which behaves as a proxy and that
 * delegates its operations to the associated {@link Store}.
 *
 * @see PersistentEObject#eStore()
 */
@ParametersAreNonnullByDefault
// TODO Synchronize modifications from `LazyStoreList`
public class LazyStoreFeatureMap extends DelegatingFeatureMap {

    /**
     * The object that holds the store.
     */
    @Nonnull
    private final transient Storable storable;

    /**
     * Constructs a new {@code LazyStoreFeatureMap}.
     *
     * @param owner   the owner the {@code feature}
     * @param feature the feature associated with this list
     */
    public LazyStoreFeatureMap(PersistentEObject owner, EStructuralFeature feature) {
        super(owner, feature);
        this.storable = owner;
    }

    @Nonnull
    protected StoreAdapter eStore() {
        return storable.eStore();
    }

    // region Delegating methods (identical to LazyStoreList)

    @Override
    protected List<Entry> delegateList() {
        throw new UnsupportedOperationException("delegateList");
    }

    @Override
    protected int delegateSize() {
        return eStore().size(owner, eStructuralFeature);
    }

    @Override
    protected boolean delegateIsEmpty() {
        return eStore().isEmpty(owner, eStructuralFeature);
    }

    @Override
    protected boolean delegateContains(Object object) {
        return eStore().contains(owner, eStructuralFeature, object);
    }

    @Override
    protected boolean delegateContainsAll(Collection<?> collection) {
        if (isNull(collection) || collection.isEmpty()) {
            return false;
        }

        if (collection.size() == 1) {
            return contains(collection.iterator().next());
        }

        return eStore().getAll(owner, eStructuralFeature).containsAll(collection);
    }

    @Override
    protected int delegateIndexOf(Object object) {
        return eStore().indexOf(owner, eStructuralFeature, object);
    }

    @Override
    protected int delegateLastIndexOf(Object object) {
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
    protected int delegateHashCode() {
        return eStore().hashCode(owner, eStructuralFeature);
    }

    @Override
    protected String delegateToString() {
        return eStore().getAll(owner, eStructuralFeature)
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
        Object[] data = eStore().toArray(owner, eStructuralFeature);

        return data.length == 0
                ? ECollections.emptyEList()
                : new EcoreEList.UnmodifiableEList<>(owner, eStructuralFeature, data.length, data);
    }

    // endregion

    // region Iterators (identical to LazyStoreList)

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
