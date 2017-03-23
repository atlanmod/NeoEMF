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

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.util.log.Level;
import fr.inria.atlanmod.neoemf.util.log.Log;
import fr.inria.atlanmod.neoemf.util.log.Logger;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link Store} wrapper that logs every call to its methods in the {@link Log}.
 */
@ParametersAreNonnullByDefault
public class LoggingStoreDecorator extends AbstractStoreDecorator {

    /**
     * The {@link Logger} for this class.
     */
    private static final Logger LOG = Log.customLogger(LoggingStoreDecorator.class.getName());

    /**
     * The default {@link Level} for the {@link #LOG}.
     */
    private final Level level;

    /**
     * Constructs a new {@code LoggingStoreDecorator}.
     *
     * @param store the inner store
     */
    public LoggingStoreDecorator(Store store) {
        this(store, Level.DEBUG);
    }

    /**
     * Constructs a new {@code LoggingStoreDecorator} with the given logging {@code level}.
     *
     * @param store the underlying store
     * @param level the logging level to use
     */
    public LoggingStoreDecorator(Store store, Level level) {
        super(store);
        this.level = level;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        called("get", key);
        return super.valueOf(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        called("set", key, value);
        return super.valueFor(key, value);
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        called("unSet", key);
        super.unsetValue(key);
    }

    @Override
    public <V> boolean hasValue(FeatureKey key) {
        called("isSet", key);
        return super.hasValue(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        called("get", key);
        return super.referenceOf(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        called("set", key, reference);
        return super.referenceFor(key, reference);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        called("unSet", key);
        super.unsetReference(key);
    }

    @Override
    public boolean hasReference(FeatureKey key) {
        called("isSet", key);
        return super.hasReference(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        called("get", key);
        return super.valueOf(key);
    }

    @Nonnull
    @Override
    public <V> List<V> allValuesOf(FeatureKey key) {
        called("toArray", key);
        return super.allValuesOf(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        called("set", key, value);
        return super.valueFor(key, value);
    }

    @Override
    public <V> boolean hasAnyValue(FeatureKey key) {
        called("isSet", key);
        return super.hasAnyValue(key);
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        called("add", key, value);
        super.addValue(key, value);
    }

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        called("append", key, value);
        super.appendValue(key, value);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        called("remove", key);
        return super.removeValue(key);
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        called("clean", key);
        super.removeAllValues(key);
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, @Nullable V value) {
        called("contains", key, value);
        return super.containsValue(key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, @Nullable V value) {
        called("indexOf", key, value);
        return super.indexOfValue(key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, @Nullable V value) {
        called("lastIndexOf", key, value);
        return super.lastIndexOfValue(key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt sizeOfValue(FeatureKey key) {
        called("size", key);
        return super.sizeOfValue(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureKey key) {
        called("get", key);
        return super.referenceOf(key);
    }

    @Nonnull
    @Override
    public List<Id> allReferencesOf(FeatureKey key) {
        called("toArray", key);
        return super.allReferencesOf(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        called("set", key, reference);
        return super.referenceFor(key, reference);
    }

    @Override
    public boolean hasAnyReference(FeatureKey key) {
        called("isSet", key);
        return super.hasAnyReference(key);
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        called("add", key, reference);
        super.addReference(key, reference);
    }

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        called("append", key, reference);
        super.appendReference(key, reference);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        called("remove", key);
        return super.removeReference(key);
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        called("clean", key);
        super.removeAllReferences(key);
    }

    @Override
    public boolean containsReference(FeatureKey key, @Nullable Id reference) {
        called("contains", key, reference);
        return super.containsReference(key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(FeatureKey key, @Nullable Id reference) {
        called("indexOf", key, reference);
        return super.indexOfReference(key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, @Nullable Id reference) {
        called("lastIndexOf", key, reference);
        return super.lastIndexOfReference(key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt sizeOfReference(FeatureKey key) {
        called("size", key);
        return super.sizeOfReference(key);
    }

    /**
     * Logs the call of a method.
     *
     * @param method the name of the called method
     * @param key    the key used during the call
     */
    private void called(String method, FeatureKey key) {
        called(method, key, null);
    }

    /**
     * Logs the call of a method with a value.
     *
     * @param method the name of the called method
     * @param key    the key used during the call
     * @param value  the value of the key
     */
    private void called(String method, FeatureKey key, @Nullable Object value) {
        if (key instanceof ManyFeatureKey) {
            ManyFeatureKey multiKey = (ManyFeatureKey) key;
            LOG.log(level, "Called {0}() for {1}.{2} [{3}]" + (nonNull(value) ? " with {4}" : ""), method, multiKey.id(), multiKey.name(), multiKey.position(), value);
        }
        else {
            LOG.log(level, "Called {0}() for {1}.{2}" + (nonNull(value) ? " with {3}" : ""), method, key.id(), key.name(), value);
        }
    }
}
