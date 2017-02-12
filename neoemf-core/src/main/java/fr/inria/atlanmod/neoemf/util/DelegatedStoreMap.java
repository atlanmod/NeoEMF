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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl.BasicEStoreEList;
import org.eclipse.emf.ecore.util.EcoreEMap;

/**
 * ???
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class DelegatedStoreMap<K, V> extends EcoreEMap<K, V> {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = 1132800653976033784L;

    /**
     * Constructs a {@code DelegatedStoreMap} with the given {@code owner} and {@code feature}.
     *
     * @param owner   the owner of this map
     * @param feature the feature associated with this map
     */
    public DelegatedStoreMap(PersistentEObject owner, EStructuralFeature feature) {
        super((EClass) feature.getEType(), Entry.class, null);

        this.delegateEList = new EntriesList(owner, feature);
        this.size = delegateEList.size();
    }

    /**
     * ???
     */
    private class EntriesList extends BasicEStoreEList<Entry<K, V>> {

        @SuppressWarnings("JavaDoc")
        private static final long serialVersionUID = -1014158684112393931L;

        /**
         * Constructs a new {@code EntriesList} with the given {@code feature}.
         *
         * @param owner   the owner of this list
         * @param feature the feature associated with this list
         */
        public EntriesList(PersistentEObject owner, EStructuralFeature feature) {
            super(owner, feature);
        }

        @Override
        protected void didSet(int index, Entry<K, V> newObject, Entry<K, V> oldObject) {
            didRemove(index, oldObject);
            didAdd(index, newObject);
        }

        @Override
        protected void didAdd(int index, Entry<K, V> newObject) {
            doPut(newObject);
        }

        @Override
        protected void didRemove(int index, Entry<K, V> oldObject) {
            DelegatedStoreMap.this.doRemove(oldObject);
        }

        @Override
        protected void didClear(int size, Object[] oldObjects) {
            DelegatedStoreMap.this.doClear();
        }

        @Override
        protected void didMove(int index, Entry<K, V> movedObject, int oldIndex) {
            DelegatedStoreMap.this.doMove(movedObject);
        }
    }
}
