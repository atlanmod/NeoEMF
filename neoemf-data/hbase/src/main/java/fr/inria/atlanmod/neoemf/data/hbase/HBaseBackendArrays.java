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
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.hbase.store.DirectWriteHBaseStore;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseEncoderUtil;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Mock {@link PersistenceBackend} implementation for HBase to fit core architecture.
 * <p>
 * This class does not access HBase database, but is here to fit the requirement of the core architecture. For
 * historical reasons the real access to the HBase Table is done in {@link DirectWriteHBaseStore}.
 * <p>
 * Moving HBase access to this class to fit NeoEMF back-end architecture is planned in a future release.
 *
 * @see DirectWriteHBaseStore
 */
@ParametersAreNonnullByDefault
class HBaseBackendArrays extends AbstractHBaseBackend {

    /**
     * Constructs a new {@code HBaseBackendArrays}.
     */
    protected HBaseBackendArrays(Table table) {
        super(table);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        return fromDatabase(key)
                .map(bytes -> Optional.of((V) Bytes.toString(bytes)))
                .orElse(Optional.empty());
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        return valueOf(key)
                .map(s -> Optional.of(StringId.of(s.toString())))
                .orElse(Optional.empty());
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(MultivaluedFeatureKey key) {
        return fromDatabase(key)
                .map(bytes -> Optional.of(HBaseEncoderUtil.<V>toStrings(bytes)[key.position()]))
                .orElse(Optional.empty());
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(MultivaluedFeatureKey key) {
        return fromDatabase(key)
                .map(bytes -> Optional.of(StringId.of(HBaseEncoderUtil.toStringsReferences(bytes)[key.position()])))
                .orElse(Optional.empty());
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        Optional<V> previousValue = valueOf(key);

        toDatabase(key, Bytes.toBytes(value.toString()));

        return previousValue;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id id) {
        return valueFor(key, id.toString())
                .map(s -> Optional.of(StringId.of(s)))
                .orElse(Optional.empty());
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(MultivaluedFeatureKey key, V value) {
        Optional<V> previousValue = valueOf(key);

        V[] values = fromDatabase(key)
                .map(HBaseEncoderUtil::<V>toStrings)
                .orElseThrow(NoSuchElementException::new);

        values[key.position()] = value;

        toDatabase(key, HBaseEncoderUtil.toBytes(values));

        return previousValue;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(MultivaluedFeatureKey key, Id id) {
        Optional<Id> previousId = referenceOf(key);

        String[] values = fromDatabase(key)
                .map(HBaseEncoderUtil::toStringsReferences)
                .orElseThrow(NoSuchElementException::new);

        values[key.position()] = id.toString();

        toDatabase(key, HBaseEncoderUtil.toBytesReferences(values));

        return previousId;
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
        unsetValue(key);
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
        return hasValue(key);
    }

    @Override
    public <V> void addValue(MultivaluedFeatureKey key, V value) {
        V[] values = fromDatabase(key)
                .map(HBaseEncoderUtil::<V>toStrings)
                .orElse(newValue());

        ArrayUtils.add(values, key.position(), value);

        toDatabase(key, HBaseEncoderUtil.toBytes(values));
    }

    @Override
    public void addReference(MultivaluedFeatureKey key, Id id) {
        String[] values = fromDatabase(key)
                .map(HBaseEncoderUtil::toStringsReferences)
                .orElse(newValue());

        ArrayUtils.add(values, key.position(), id.toString());

        toDatabase(key, HBaseEncoderUtil.toBytesReferences(values));
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(MultivaluedFeatureKey key) {
        V[] values = fromDatabase(key)
                .map(HBaseEncoderUtil::<V>toStrings)
                .orElseThrow(NoSuchElementException::new);

        Optional<V> previousValue = Optional.ofNullable(values[key.position()]);

        ArrayUtils.remove(values, key.position());

        toDatabase(key, HBaseEncoderUtil.toBytes(values));

        return previousValue;
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(MultivaluedFeatureKey key) {
        String[] values = fromDatabase(key)
                .map(HBaseEncoderUtil::toStringsReferences)
                .orElseThrow(NoSuchElementException::new);

        Optional<Id> previousId = Optional.ofNullable(values[key.position()]).map(StringId::of);

        ArrayUtils.remove(values, key.position());

        toDatabase(key, HBaseEncoderUtil.toBytesReferences(values));

        return previousId;
    }

    @Override
    public void cleanValues(FeatureKey key) {
        toDatabase(key, null);
    }

    @Override
    public void cleanReferences(FeatureKey key) {
        cleanValues(key);
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, V value) {
        V[] values = fromDatabase(key)
                .map(HBaseEncoderUtil::<V>toStrings)
                .orElseThrow(NoSuchElementException::new);

        return ArrayUtils.contains(values, value);
    }

    @Override
    public boolean containsReference(FeatureKey key, Id id) {
        String[] values = fromDatabase(key)
                .map(HBaseEncoderUtil::toStringsReferences)
                .orElseThrow(NoSuchElementException::new);

        return ArrayUtils.contains(values, id.toString());
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, V value) {
        V[] values = fromDatabase(key)
                .map(HBaseEncoderUtil::<V>toStrings)
                .orElseThrow(NoSuchElementException::new);

        int index = ArrayUtils.indexOf(values, value);
        return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(FeatureKey key, Id id) {
        String[] values = fromDatabase(key)
                .map(HBaseEncoderUtil::toStringsReferences)
                .orElseThrow(NoSuchElementException::new);

        int index = ArrayUtils.indexOf(values, id.toString());
        return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, V value) {
        V[] values = fromDatabase(key)
                .map(HBaseEncoderUtil::<V>toStrings)
                .orElseThrow(NoSuchElementException::new);

        int index = ArrayUtils.lastIndexOf(values, value);
        return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, Id id) {
        String[] values = fromDatabase(key)
                .map(HBaseEncoderUtil::toStringsReferences)
                .orElseThrow(NoSuchElementException::new);

        int index = ArrayUtils.lastIndexOf(values, id.toString());
        return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
    }

    @Nonnull
    @Override
    public <V> Iterable<V> valuesAsList(FeatureKey key) {
        V[] values = fromDatabase(key)
                .map(HBaseEncoderUtil::<V>toStrings)
                .orElseThrow(NoSuchElementException::new);

        return Arrays.asList(values);
    }

    @Nonnull
    @Override
    public Iterable<Id> referencesAsList(FeatureKey key) {
        String[] values = fromDatabase(key)
                .map(HBaseEncoderUtil::toStringsReferences)
                .orElseThrow(NoSuchElementException::new);

        return Stream.of(values)
                .map(StringId::of)
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    public OptionalInt sizeOf(FeatureKey key) {
        return fromDatabase(key)
                .map(b -> OptionalInt.of(HBaseEncoderUtil.toStrings(b).length))
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