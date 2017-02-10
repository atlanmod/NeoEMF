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

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.hadoop.hbase.client.Table;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Mock {@link PersistenceBackend} implementation for HBase to fit core architecture.
 * <p>
 * This class does not access HBase database, but is here to fit the requirement of the core architecture. For
 * historical reasons the real access to the HBase Table is done in {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore}.
 * <p>
 * Moving HBase access to this class to fit NeoEMF back-end architecture is planned in a future release.
 *
 * @see fr.inria.atlanmod.neoemf.data.store.DirectWriteStore
 */
@ParametersAreNonnullByDefault
class HBaseBackendArrays extends AbstractHBaseBackend {

    /**
     * Constructs a new {@code HBaseBackendArrays} on th given {@code table}
     *
     * @param table the HBase table
     */
    protected HBaseBackendArrays(Table table) {
        super(table);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        return fromDatabase(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        return valueOf(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(MultivaluedFeatureKey key) {
        return this.<V[]>valueOf(key.withoutPosition())
                .map(ts -> ts[key.position()]);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(MultivaluedFeatureKey key) {
        return valueOf(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        Optional<V> previousValue = valueOf(key);

        toDatabase(key, value);

        return previousValue;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id id) {
        return valueFor(key, id);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(MultivaluedFeatureKey key, V value) {
        V[] values = this.<V[]>valueOf(key.withoutPosition())
                .orElseThrow(NoSuchElementException::new);

        Optional<V> previousValue = Optional.of(values[key.position()]);

        values[key.position()] = value;

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(MultivaluedFeatureKey key, Id id) {
        return valueFor(key, id);
    }

    @Override
    public void unsetValue(FeatureKey key) {
        outDatabase(key);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        unsetValue(key);
    }

    @Override
    public void unsetAllValues(FeatureKey key) {
        unsetValue(key);
    }

    @Override
    public void unsetAllReferences(FeatureKey key) {
        unsetReference(key);
    }

    @Override
    public boolean hasValue(FeatureKey key) {
        return fromDatabase(key).isPresent();
    }

    @Override
    public boolean hasReference(FeatureKey key) {
        return hasValue(key);
    }

    @Override
    public boolean hasAnyValue(FeatureKey key) {
        return hasValue(key);
    }

    @Override
    public boolean hasAnyReference(FeatureKey key) {
        return hasReference(key);
    }

    @Override
    public <V> void addValue(MultivaluedFeatureKey key, V value) {
        V[] values = this.<V[]>valueOf(key.withoutPosition())
                .orElse(newValue());

        valueFor(key.withoutPosition(), ArrayUtils.add(values, key.position(), value));
    }

    @Override
    public void addReference(MultivaluedFeatureKey key, Id id) {
        addValue(key, id);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(MultivaluedFeatureKey key) {
        V[] values = this.<V[]>valueOf(key.withoutPosition())
                .orElseThrow(NoSuchElementException::new);

        Optional<V> previousValue = Optional.of(values[key.position()]);

        valueFor(key.withoutPosition(), ArrayUtils.remove(values, key.position()));

        return previousValue;
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(MultivaluedFeatureKey key) {
        return removeValue(key);
    }

    @Override
    public void cleanValues(FeatureKey key) {
        unsetValue(key);
    }

    @Override
    public void cleanReferences(FeatureKey key) {
        unsetReference(key);
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, V value) {
        return this.<V[]>valueOf(key)
                .map(ts -> org.apache.commons.lang.ArrayUtils.contains(ts, value))
                .orElse(false);
    }

    @Override
    public boolean containsReference(FeatureKey key, Id id) {
        return containsValue(key, id);
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, V value) {
        return this.<V[]>valueOf(key)
                .map(ts -> {
                    int index = org.apache.commons.lang.ArrayUtils.indexOf(ts, value);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(FeatureKey key, Id id) {
        return indexOfValue(key, id);
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, V value) {
        return this.<V[]>valueOf(key)
                .map(ts -> {
                    int index = org.apache.commons.lang.ArrayUtils.lastIndexOf(ts, value);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, Id id) {
        return lastIndexOfValue(key, id);
    }

    @Nonnull
    @Override
    public <V> Iterable<V> valuesAsList(FeatureKey key) {
        V[] values = this.<V[]>valueOf(key)
                .orElseThrow(NoSuchElementException::new);

        return Arrays.asList(values);
    }

    @Nonnull
    @Override
    public Iterable<Id> referencesAsList(FeatureKey key) {
        return valuesAsList(key);
    }

    @Nonnull
    @Override
    public <V> OptionalInt sizeOf(FeatureKey key) {
        return this.<V[]>valueOf(key)
                .map(ts -> OptionalInt.of(ts.length))
                .orElse(OptionalInt.empty());
    }

    /**
     * Creates a new multi-value.
     *
     * @param <V> the type of the multi-value
     *
     * @return a new multi-value
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    private <V> V[] newValue() {
        return (V[]) new Object[0];
    }
}