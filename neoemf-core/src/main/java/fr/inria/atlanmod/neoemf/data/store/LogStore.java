/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.commons.log.Level;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link Store} wrapper that logs every calls in the {@link Log}.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"}) // Called dynamically
public class LogStore extends AbstractStore {

    /**
     * The default logging level.
     */
    @Nonnull
    public static final Level DEFAULT_LEVEL = Level.INFO;

    /**
     * The default {@link Level} for logging.
     */
    @Nonnull
    private final Level level;

    /**
     * Constructs a new {@code LogStore}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    public LogStore(Store store) {
        this(store, DEFAULT_LEVEL);
    }

    /**
     * Constructs a new {@code LogStore} with the given logging {@code level}.
     *
     * @param store the underlying store
     * @param level the logging level
     */
    @VisibleForReflection
    public LogStore(Store store, Level level) {
        super(store);
        this.level = level;
    }

    @Override
    public void close() {
        call(super::close);
    }

    @Override
    public void save() {
        call(super::save);
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        return callAndReturn(super::containerOf, id);
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        call(super::containerFor, id, container);
    }

    @Override
    public void removeContainer(Id id) {
        call(super::removeContainer, id);
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        return callAndReturn(super::metaClassOf, id);
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        return callAndReturn(super::metaClassFor, id, metaClass);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        return callAndReturn(super::valueOf, key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        return callAndReturn(super::valueFor, key, value);
    }

    @Override
    public void removeValue(SingleFeatureBean key) {
        call(super::removeValue, key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean key) {
        return callAndReturn(super::referenceOf, key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        return callAndReturn(super::referenceFor, key, reference);
    }

    @Override
    public void removeReference(SingleFeatureBean key) {
        call(super::removeReference, key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        return callAndReturn(super::valueOf, key);
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean key) {
        return callAndReturn(super::allValuesOf, key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        return callAndReturn(super::valueFor, key, value);
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        call(super::addValue, key, value);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        call(super::addAllValues, key, collection);
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureBean key, V value) {
        return callAndReturn(super::appendValue, key, value);
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureBean key, List<? extends V> collection) {
        return callAndReturn(super::appendAllValues, key, collection);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        return callAndReturn(super::removeValue, key);
    }

    @Override
    public void removeAllValues(SingleFeatureBean key) {
        call(super::removeAllValues, key);
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        return callAndReturn(super::sizeOfValue, key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        return callAndReturn(super::referenceOf, key);
    }

    @Nonnull
    @Override
    public Stream<Id> allReferencesOf(SingleFeatureBean key) {
        return callAndReturn(super::allReferencesOf, key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        return callAndReturn(super::referenceFor, key, reference);
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        call(super::addReference, key, reference);
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        call(super::addAllReferences, key, collection);
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureBean key, Id reference) {
        return callAndReturn(super::appendReference, key, reference);
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        return callAndReturn(super::appendAllReferences, key, collection);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        return callAndReturn(super::removeReference, key);
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        call(super::removeAllReferences, key);
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        return callAndReturn(super::sizeOfReference, key);
    }

    /**
     * Logs the call of a method.
     *
     * @param runnable the method to call
     */
    private void call(Runnable runnable) {
        runnable.run();
        log();
    }

    /**
     * Logs the call of a method.
     *
     * @param consumer the method to call
     * @param key      the key used during the call
     */
    private <K> void call(Consumer<K> consumer, K key) {
        try {
            consumer.accept(key);
            logSuccess(key, null, null);
        }
        catch (RuntimeException e) {
            logFailure(key, null, e);
            throw e;
        }
    }

    /**
     * Logs the call of a method.
     *
     * @param consumer the method to call
     * @param key      the key used during the call
     * @param value    the value of the key
     */
    private <K, V> void call(BiConsumer<K, V> consumer, K key, V value) {
        try {
            consumer.accept(key, value);
            logSuccess(key, value, null);
        }
        catch (RuntimeException e) {
            logFailure(key, value, e);
            throw e;
        }
    }

    /**
     * Logs the call of a method and returns the result.
     *
     * @param function the method to call
     * @param key      the key used during the call
     *
     * @return the result of the call
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    private <K, R> R callAndReturn(Function<K, R> function, K key) {
        try {
            R result = function.apply(key);
            Object resultToLog;

            if (Stream.class.isInstance(result)) {
                // Clone the stream
                Stream<?> stream = Stream.class.cast(result);
                List<?> list = stream.collect(Collectors.toList());

                result = (R) list.stream();
                resultToLog = list.stream();
            }
            else {
                resultToLog = result;
            }

            logSuccess(key, null, resultToLog);
            return result;
        }
        catch (RuntimeException e) {
            logFailure(key, null, e);
            throw e;
        }
    }

    /**
     * Logs the call of a method and returns the result.
     *
     * @param function the method to call
     * @param key      the key used during the call
     * @param value    the value of the key
     *
     * @return the result of the call
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    private <K, V, R> R callAndReturn(BiFunction<K, V, R> function, K key, @Nullable V value) {
        try {
            R result = function.apply(key, value);
            Object resultToLog;

            if (Stream.class.isInstance(result)) {
                // Clone the stream
                Stream<?> stream = Stream.class.cast(result);
                List<?> list = stream.collect(Collectors.toList());

                result = (R) list.stream();
                resultToLog = list.stream();
            }
            else {
                resultToLog = result;
            }

            logSuccess(key, value, resultToLog);
            return result;
        }
        catch (RuntimeException e) {
            logFailure(key, value, e);
            throw e;
        }
    }

    /**
     * Logs a call of a method.
     */
    private void log() {
        Log.log(level, "[{0}] Called {1}()",
                backend(),
                getCallingMethod());
    }

    /**
     * Logs a successful call of a method.
     *
     * @param key    the key used during the call
     * @param value  the value of the key
     * @param result the result of the call
     */
    private void logSuccess(Object key, @Nullable Object value, @Nullable Object result) {
        Log.log(level, "[{0}] Called {1}() for {2}" + (nonNull(value) ? " with {3}" : Strings.EMPTY) + (nonNull(result) ? " = {4}" : Strings.EMPTY),
                backend(),
                getCallingMethod(),
                key,
                value,
                result);
    }

    /**
     * Logs a successful call of a method.
     *
     * @param key   the key used during the call
     * @param value the value of the key
     * @param e     the exception thrown during the the call
     */
    private void logFailure(Object key, @Nullable Object value, Throwable e) {
        Log.log(level, "[{0}] Called {1}() for {2}" + (nonNull(value) ? " with {3}" : Strings.EMPTY) + " but failed with {4}",
                backend(),
                getCallingMethod(),
                key,
                value,
                e.getClass().getSimpleName());
    }

    /**
     * Returns the name of the calling method.
     *
     * @return the name
     */
    private String getCallingMethod() {
        return Thread.currentThread().getStackTrace()[4].getMethodName();
    }
}
