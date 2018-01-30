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
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;

/**
 * A {@link org.eclipse.emf.ecore.util.FeatureMap} representing a multi-valued feature which behaves as a proxy and that
 * delegates its operations to the associated {@link Store}.
 *
 * @see PersistentEObject#eStore()
 */
@ParametersAreNonnullByDefault
public class LazyStoreFeatureMap extends EStoreEObjectImpl.BasicEStoreFeatureMap {

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
    @Override
    protected StoreAdapter eStore() {
        return storable.eStore();
    }

    // region Delegating methods (identical from LazyStoreList)

    @Override
    protected void delegateAdd(Entry object) {
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
    protected String delegateToString() {
        return eStore().getAll(owner, eStructuralFeature)
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
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

    // endregion
}
