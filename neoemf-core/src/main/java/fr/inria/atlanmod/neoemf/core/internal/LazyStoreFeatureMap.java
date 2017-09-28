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

package fr.inria.atlanmod.neoemf.core.internal;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link org.eclipse.emf.ecore.util.FeatureMap} representing a multi-valued feature which behaves as a proxy and that
 * delegates its operations to the associated {@link Store}.
 *
 * @see PersistentEObject#eStore()
 */
@ParametersAreNonnullByDefault
// TODO Add optimization from `LazyStoreList`
public class LazyStoreFeatureMap extends EStoreEObjectImpl.BasicEStoreFeatureMap {

    /**
     * The owner of this list.
     */
    @Nonnull
    private final PersistentEObject owner;

    /**
     * Constructs a new {@code LazyStoreFeatureMap}.
     *
     * @param owner   the owner the {@code feature}
     * @param feature the feature associated with this list
     */
    public LazyStoreFeatureMap(PersistentEObject owner, EStructuralFeature feature) {
        super(owner, feature);
        this.owner = owner;
    }

    @Nonnull
    @Override
    protected StoreAdapter eStore() {
        return owner.eStore();
    }

    @Override
    protected void delegateAdd(Entry object) {
        super.delegateAdd(InternalEObject.EStore.NO_INDEX, object);
    }
}
