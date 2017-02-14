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

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl.BasicEStoreEList;

/**
 * ???
 *
 * @param <E> the type of elements in this list
 */
public class DelegatedStoreList<E> extends BasicEStoreEList<E> {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = 2630358403343923944L;

    /**
     * Constructs a new {@code DelegatedStoreList} with the given {@code feature}.
     *
     * @param owner   the owner of this list
     * @param feature the feature associated with this list
     */
    public DelegatedStoreList(PersistentEObject owner, EStructuralFeature feature) {
        super(owner, feature);
    }

    @Override
    public boolean contains(Object object) {
        return delegateContains(object);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Override the default implementation which relies on {@link #size()} to compute the insertion index by
     * providing a custom {@link PersistentStore#NO_INDEX} features, meaning that the {@link PersistenceBackend} has
     * to append the result to the existing list.
     * <p>
     * This behavior allows fast write operation on {@link PersistenceBackend} which would otherwise need to
     * deserialize the underlying list to add the element at the specified index.
     */
    @Override
    public boolean add(E object) {
        if (isUnique() && contains(object)) {
            return false;
        }
        else {
            if (eStructuralFeature instanceof EAttribute) {
                addUnique(object);
            }
            else {
                int index = size() == 0 ? 0 : PersistentStore.NO_INDEX;
                addUnique(index, object);
            }
            return true;
        }
    }
}
