/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.commons.LazyInt;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;

/**
 * A dynamic {@link PersistentEObject}.
 */
@ParametersAreNonnullByDefault
public class DynamicPersistentEObject extends DefaultPersistentEObject {

    /**
     * Constructs a new {@code DynamicPersistentEObject} that is an instance of the specified {@code eClass}.
     *
     * @param eClass the meta-class of this object
     */
    public DynamicPersistentEObject(EClass eClass) {
        eSetClass(eClass);
    }

    /**
     * A dynamic {@link java.util.Map.Entry}.
     *
     * @param <K> the type of the key
     * @param <V> the type of the value
     */
    @ParametersAreNonnullByDefault
    static class MapEntry<K, V> extends DynamicPersistentEObject implements BasicEMap.Entry<K, V> {

        /**
         * The hash-code of the key.
         */
        @Nonnull
        private final LazyInt lazyHash = LazyInt.with(() -> {
            final Object key = getKey();
            return isNull(key) ? 0 : key.hashCode();
        });

        /**
         * The feature of the key.
         */
        private EStructuralFeature keyFeature;

        /**
         * The feature of the value.
         */
        private EStructuralFeature valueFeature;

        /**
         * Constructs a new dynamic {@code MapEntry} that is an instance of the specified {@code eClass}.
         *
         * @param eClass the meta-class of this entry
         */
        public MapEntry(EClass eClass) {
            super(eClass);
        }

        @Override
        public K getKey() {
            return cast(eGet(keyFeature));
        }

        @Override
        public void setKey(Object key) {
            eSet(keyFeature, key);
        }

        @Override
        public int getHash() {
            return lazyHash.getAsInt();
        }

        @Override
        public void setHash(int hash) {
            lazyHash.update(hash);
        }

        @Override
        public V getValue() {
            return cast(eGet(valueFeature));
        }

        @Override
        public V setValue(V value) {
            V result = cast(eGet(valueFeature));
            eSet(valueFeature, value);
            return result;
        }

        @Override
        public void eSetClass(EClass eClass) {
            super.eSetClass(eClass);
            keyFeature = eClass.getEStructuralFeature("key");
            valueFeature = eClass.getEStructuralFeature("value");
        }

        @SuppressWarnings("unchecked")
        private <T> T cast(Object o) {
            return (T) o;
        }
    }
}
