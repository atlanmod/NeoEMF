/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link java.util.Map} representing a multi-valued feature which behaves as a proxy and that delegates its
 * operations to the associated {@link Store}.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 *
 * @see PersistentEObject#eStore()
 */
@ParametersAreNonnullByDefault
public class LazyStoreMap<K, V> extends EcoreEMap<K, V> {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = 9173875843551606055L;

    /**
     * Constructs a {@code LazyStoreMap} with the given {@code owner} and {@code feature}.
     *
     * @param owner   the owner the {@code feature}
     * @param feature the feature associated with this map
     */
    public LazyStoreMap(PersistentEObject owner, EStructuralFeature feature) {
        super(EClass.class.cast(feature.getEType()), Entry.class, null);

        this.delegateEList = new EntryList(owner, feature);
        this.size = delegateEList.size();
    }

    /**
     * A {@link List} that holds entries of this map.
     */
    @ParametersAreNonnullByDefault
    private class EntryList extends EStoreEObjectImpl.BasicEStoreEList<Entry<K, V>> {

        @SuppressWarnings("JavaDoc")
        private static final long serialVersionUID = 3373155561238654363L;

        /**
         * The owner of this list.
         */
        @Nonnull
        private final transient PersistentEObject persistentOwner;

        /**
         * Constructs a new {@code EntryList}.
         *
         * @param owner   the persistentOwner the {@code feature}
         * @param feature the feature associated with this list
         */
        public EntryList(PersistentEObject owner, EStructuralFeature feature) {
            super(owner, feature);
            this.persistentOwner = owner;
        }

        @Nonnull
        @Override
        protected StoreAdapter eStore() {
            return persistentOwner.eStore();
        }

        @Override
        protected void didSet(int index, Entry<K, V> newObject, Entry<K, V> oldObject) {
            didRemove(index, oldObject);
            didAdd(index, newObject);
        }

        @Override
        protected void didAdd(int index, Entry<K, V> newObject) {
            LazyStoreMap.this.doPut(newObject);
        }

        @Override
        protected void didRemove(int index, Entry<K, V> oldObject) {
            LazyStoreMap.this.doRemove(oldObject);
        }

        @Override
        protected void didClear(int size, Object[] oldObjects) {
            LazyStoreMap.this.doClear();
        }

        @Override
        protected void didMove(int index, Entry<K, V> movedObject, int oldIndex) {
            LazyStoreMap.this.doMove(movedObject);
        }
    }
}
