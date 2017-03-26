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
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.util.log.Level;
import fr.inria.atlanmod.neoemf.util.log.Log;
import fr.inria.atlanmod.neoemf.util.log.Logger;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link Store} wrapper that logs every call to its methods in the {@link Log}.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("MethodDoesntCallSuperMethod")
public class LoggingStoreDecorator extends AbstractStoreDecorator {

    /**
     * The {@link Logger} for this class.
     */
    private static final Logger LOG = Log.customLogger("store.logging");

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
    public Optional<ContainerDescriptor> containerOf(Id id) {
        return call(() -> super.containerOf(id), "containerOf", id);
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
        call(() -> super.containerFor(id, container), "containerFor", id, container);
    }

    @Override
    public void unsetContainer(Id id) {
        call(() -> super.unsetContainer(id), "unsetContainer", id);
    }

    @Nonnull
    @Override
    public Optional<ClassDescriptor> metaclassOf(Id id) {
        return call(() -> super.metaclassOf(id), "metaclassOf", id);
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        call(() -> super.metaclassFor(id, metaclass), "metaclassFor", id, metaclass);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        return call(() -> super.valueOf(key), "valueOf", key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        return call(() -> super.valueFor(key, value), "valueFor", key, value);
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        call(() -> super.unsetValue(key), "unsetValue", key);
    }

    @Override
    public <V> boolean hasValue(FeatureKey key) {
        return call(() -> super.hasValue(key), "hasValue", key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        return call(() -> super.referenceOf(key), "referenceOf", key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        return call(() -> super.referenceFor(key, reference), "referenceFor", key, reference);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        call(() -> super.unsetReference(key), "unsetReference", key);
    }

    @Override
    public boolean hasReference(FeatureKey key) {
        return call(() -> super.hasReference(key), "hasReference", key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        return call(() -> super.valueOf(key), "valueOf", key);
    }

    @Nonnull
    @Override
    public <V> List<V> allValuesOf(FeatureKey key) {
        return call(() -> super.allValuesOf(key), "allValuesOf", key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        return call(() -> super.valueFor(key, value), "valueFor", key, value);
    }

    @Override
    public <V> boolean hasAnyValue(FeatureKey key) {
        return call(() -> super.hasAnyValue(key), "hasAnyValue", key);
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        call(() -> super.addValue(key, value), "addValue", key, value);
    }

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        call(() -> super.appendValue(key, value), "appendValue", key, value);
    }

    @Override
    public <V> void appendAllValues(FeatureKey key, List<V> values) {
        call(() -> super.appendAllValues(key, values), "appendAllValues", key, values);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        return call(() -> super.removeValue(key), "removeValue", key);
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        call(() -> super.removeAllValues(key), "removeAllValues", key);
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, @Nullable V value) {
        return call(() -> super.containsValue(key, value), "containsValue", key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, @Nullable V value) {
        return call(() -> super.indexOfValue(key, value), "indexOfValue", key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, @Nullable V value) {
        return call(() -> super.lastIndexOfValue(key, value), "lastIndexOfValue", key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt sizeOfValue(FeatureKey key) {
        return call(() -> super.sizeOfValue(key), "sizeOfValue", key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureKey key) {
        return call(() -> super.referenceOf(key), "referenceOf", key);
    }

    @Nonnull
    @Override
    public List<Id> allReferencesOf(FeatureKey key) {
        return call(() -> super.allReferencesOf(key), "allReferencesOf", key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        return call(() -> super.referenceFor(key, reference), "referenceFor", key, reference);
    }

    @Override
    public boolean hasAnyReference(FeatureKey key) {
        return call(() -> super.hasAnyReference(key), "hasAnyReference", key);
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        call(() -> super.addReference(key, reference), "addReference", key, reference);
    }

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        call(() -> super.appendReference(key, reference), "appendReference", key, reference);
    }

    @Override
    public void appendAllReferences(FeatureKey key, List<Id> references) {
        call(() -> super.appendAllReferences(key, references), "appendAllReferences", key, references);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        return call(() -> super.removeReference(key), "removeReference", key);
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        call(() -> super.removeAllReferences(key), "removeAllReferences", key);
    }

    @Override
    public boolean containsReference(FeatureKey key, @Nullable Id reference) {
        return call(() -> super.containsReference(key, reference), "containsReference", key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(FeatureKey key, @Nullable Id reference) {
        return call(() -> super.indexOfReference(key, reference), "indexOfReference", key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, @Nullable Id reference) {
        return call(() -> super.lastIndexOfReference(key, reference), "lastIndexOfReference", key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt sizeOfReference(FeatureKey key) {
        return call(() -> super.sizeOfReference(key), "sizeOfReference", key);
    }

    /**
     * Logs the call of a method.
     *
     * @param runnable the method to call
     * @param method   the name of the call method
     * @param key      the key used during the call
     */
    private void call(Runnable runnable, String method, Object key) {
        call(runnable, method, key, null);
    }

    /**
     * Logs the call of a method with a value.
     *
     * @param runnable the method to call
     * @param method   the name of the call method
     * @param key      the key used during the call
     * @param value    the value of the key
     */
    private void call(Runnable runnable, String method, Object key, @Nullable Object value) {
        try {
            runnable.run();
            logSuccess(method, key, value, null);
        }
        catch (RuntimeException e) {
            logFailure(method, key, value, e);
            throw e;
        }
    }

    /**
     * Logs the call of a method.
     *
     * @param callable the method to call
     * @param method   the name of the call method
     * @param key      the key used during the call
     *
     * @return the result of the call
     */
    private <T> T call(Callable<T> callable, String method, Object key) {
        return call(callable, method, key, null);
    }

    /**
     * Logs the call of a method with a value.
     *
     * @param callable the method to call
     * @param method   the name of the call method
     * @param key      the key used during the call
     * @param value    the value of the key
     *
     * @return the result of the call
     */
    private <T> T call(Callable<T> callable, String method, Object key, @Nullable Object value) {
        T result;
        try {
            result = callable.call();
            logSuccess(method, key, value, result);
        }
        catch (RuntimeException e) {
            logFailure(method, key, value, e);
            throw e;
        }
        catch (Exception e) { // Should never happen
            throw new IllegalStateException(e);
        }
        return result;
    }

    /**
     * Logs a successful call of a method.
     *
     * @param method the name of the called method
     * @param key    the key used during the call
     * @param value  the value of the key
     * @param result the result of the call
     */
    private void logSuccess(String method, Object key, @Nullable Object value, @Nullable Object result) {
        LOG.log(level, "Called {0}() for {1}" + (nonNull(value) ? " with {2}" : "") + (nonNull(result) ? " = {3}" : ""), method, key, value, result);
    }

    /**
     * Logs a successful call of a method.
     *
     * @param method the name of the called method
     * @param key    the key used during the call
     * @param value  the value of the key
     * @param e      the exception thrown during the the call
     */
    private void logFailure(String method, Object key, @Nullable Object value, Throwable e) {
        LOG.log(level, "Called {0}() for {1}" + (nonNull(value) ? " with {2}" : "") + " but failed with {3}", method, key, value, e.getClass().getSimpleName());
    }
}
