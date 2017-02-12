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
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.hadoop.hbase.client.Table;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

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
class HBaseBackendStrings extends HBaseBackendArrays {

    /**
     * Expected length (in {@code bytes}) of stored elements.
     */
    public static final int UUID_LENGTH = 23;

    /**
     * The default separator used to serialize {@link Collection}s.
     */
    public static final String VALUE_SEPERATOR_DEFAULT = ",";

    /**
     * Constructs a new {@code HBaseBackendArrays} on th given {@code table}
     *
     * @param table the HBase table
     */
    protected HBaseBackendStrings(Table table) {
        super(table);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        return this.<String>valueOf(key)
                .map(this::parse);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(MultivaluedFeatureKey key) {
        return this.<String>valueOf(key.withoutPosition())
                .map(s -> parseArray(s)[key.position()]);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id id) {
        return this.<String>valueFor(key, format(id))
                .map(this::parse);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(MultivaluedFeatureKey key, Id id) {
        Id[] values = this.<String>valueOf(key.withoutPosition())
                .map(this::parseArray)
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<Id> previousValue = Optional.of(values[key.position()]);

        values[key.position()] = id;

        valueFor(key.withoutPosition(), formatArray(values));

        return previousValue;
    }

    @Override
    public void addReference(MultivaluedFeatureKey key, Id id) {
        Id[] values = this.<String>valueOf(key.withoutPosition())
                .map(this::parseArray)
                .orElse(new Id[0]);

        while (key.position() > values.length) {
            values = ArrayUtils.add(values, values.length, null);
        }

        if (key.position() < values.length && isNull(values[key.position()])) {
            values[key.position()] = id;
        }
        else {
            values = ArrayUtils.add(values, key.position(), id);
        }

        valueFor(key.withoutPosition(), formatArray(values));
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(MultivaluedFeatureKey key) {
        Id[] values = this.<String>valueOf(key.withoutPosition())
                .map(this::parseArray)
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<Id> previousValue = Optional.of(values[key.position()]);

        valueFor(key.withoutPosition(), formatArray(ArrayUtils.remove(values, key.position())));

        return previousValue;
    }

    @Override
    public boolean containsReference(FeatureKey key, Id id) {
        return this.<String>valueOf(key)
                .map(s -> ArrayUtils.contains(parseArray(s), id))
                .orElse(false);
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(FeatureKey key, Id id) {
        return this.<String>valueOf(key)
                .map(s -> {
                    int index = ArrayUtils.indexOf(parseArray(s), id);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, Id id) {
        return this.<String>valueOf(key)
                .map(s -> {
                    int index = ArrayUtils.lastIndexOf(parseArray(s), id);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    public Iterable<Id> referencesAsList(FeatureKey key) {
        Id[] values = this.<String>valueOf(key)
                .map(this::parseArray)
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        return Arrays.asList(values);
    }

    @Nonnull
    @Override
    public OptionalInt sizeOfReference(FeatureKey key) {
        return this.<String>valueOf(key)
                .map(s -> OptionalInt.of(parseArray(s).length))
                .orElse(OptionalInt.empty());
    }

    private String format(Id value) {
        checkNotNull(value);

        return value.toString();
    }

    private Id parse(String value) {
        checkNotNull(value);

        return StringId.of(value);
    }

    private String formatArray(Id[] values) {
        checkNotNull(values);

        return Stream.of(values)
                .map(this::format)
                .collect(Collectors.joining(VALUE_SEPERATOR_DEFAULT));
    }

    private Id[] parseArray(String value) {
        checkNotNull(value);

        // FIXME Work at low level to retrieve the byte[] size
//        checkArgument(data.length % (UUID_LENGTH + 1) == UUID_LENGTH);
//        int length = (data.length + 1) / (UUID_LENGTH + 1);

        return Stream.of(value.split(VALUE_SEPERATOR_DEFAULT))
                .map(this::parse)
                .toArray(Id[]::new);
    }
}