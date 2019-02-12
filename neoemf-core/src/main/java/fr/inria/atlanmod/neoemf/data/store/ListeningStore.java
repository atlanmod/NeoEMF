/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.store.listener.BackendReport;
import fr.inria.atlanmod.neoemf.data.store.listener.FailureCallReport;
import fr.inria.atlanmod.neoemf.data.store.listener.StoreListener;
import fr.inria.atlanmod.neoemf.data.store.listener.SuccessCallReport;

import org.atlanmod.commons.Lazy;

import java.util.ArrayList;
import java.util.Collection;
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

/**
 * An {@link Store} that listen calls made on this store chain and notify defined {@link StoreListener}s.
 */
@ParametersAreNonnullByDefault
public class ListeningStore extends AbstractStore {

    /**
     * Information about the listened {@link fr.inria.atlanmod.neoemf.data.Backend}.
     */
    @Nonnull
    private final Lazy<BackendReport> backendReport = Lazy.with(() -> new BackendReport(next().backend()));

    /**
     * All listeners to notify.
     */
    @Nonnull
    private final List<StoreListener> listeners = new ArrayList<>();

    /**
     * Constructs a new {@code ListeningStore} with the given {@code listener}.
     *
     * @param listeners the store listeners to notify
     */
    public ListeningStore(Collection<StoreListener> listeners) {
        super(10);
        this.listeners.addAll(listeners);
    }

    @Override
    public void next(Store next) {
        super.next(next);
        notifyListeners(StoreListener::onInitialize);
    }

    @Override
    public String toString() {
        return super.toString() + listeners.stream()
                .map(StoreListener::getClass)
                .map(Class::getSimpleName)
                .collect(Collectors.joining(", ", " [", "]"));
    }

    @Override
    public void close() {
        super.close();
        notifyListeners(StoreListener::onClose);
    }

    @Override
    public void save() {
        onCall(super::save);
    }

    @Override
    public void copyTo(DataMapper target) {
        onCall(super::copyTo, target);
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        return onCallResult(super::containerOf, id);
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        onCall(super::containerFor, id, container);
    }

    @Override
    public void removeContainer(Id id) {
        onCall(super::removeContainer, id);
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        return onCallResult(super::metaClassOf, id);
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        return onCallResult(super::metaClassFor, id, metaClass);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean feature) {
        return onCallResult(super::valueOf, feature);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean feature, V value) {
        return onCallResult(super::valueFor, feature, value);
    }

    @Override
    public void removeValue(SingleFeatureBean feature) {
        onCall(super::removeValue, feature);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean feature) {
        return onCallResult(super::referenceOf, feature);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean feature, Id reference) {
        return onCallResult(super::referenceFor, feature, reference);
    }

    @Override
    public void removeReference(SingleFeatureBean feature) {
        onCall(super::removeReference, feature);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean feature) {
        return onCallResult(super::valueOf, feature);
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean feature) {
        return onCallResult(super::allValuesOf, feature);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean feature, V value) {
        return onCallResult(super::valueFor, feature, value);
    }

    @Override
    public <V> void addValue(ManyFeatureBean feature, V value) {
        onCall(super::addValue, feature, value);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean feature, List<? extends V> collection) {
        onCall(super::addAllValues, feature, collection);
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureBean feature, V value) {
        return onCallResult(super::appendValue, feature, value);
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureBean feature, List<? extends V> collection) {
        return onCallResult(super::appendAllValues, feature, collection);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean feature) {
        return onCallResult(super::removeValue, feature);
    }

    @Override
    public void removeAllValues(SingleFeatureBean feature) {
        onCall(super::removeAllValues, feature);
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfValue(SingleFeatureBean feature) {
        return onCallResult(super::sizeOfValue, feature);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean feature) {
        return onCallResult(super::referenceOf, feature);
    }

    @Nonnull
    @Override
    public Stream<Id> allReferencesOf(SingleFeatureBean feature) {
        return onCallResult(super::allReferencesOf, feature);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean feature, Id reference) {
        return onCallResult(super::referenceFor, feature, reference);
    }

    @Override
    public void addReference(ManyFeatureBean feature, Id reference) {
        onCall(super::addReference, feature, reference);
    }

    @Override
    public void addAllReferences(ManyFeatureBean feature, List<Id> collection) {
        onCall(super::addAllReferences, feature, collection);
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureBean feature, Id reference) {
        return onCallResult(super::appendReference, feature, reference);
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureBean feature, List<Id> collection) {
        return onCallResult(super::appendAllReferences, feature, collection);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean feature) {
        return onCallResult(super::removeReference, feature);
    }

    @Override
    public void removeAllReferences(SingleFeatureBean feature) {
        onCall(super::removeAllReferences, feature);
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean feature) {
        return onCallResult(super::sizeOfReference, feature);
    }

    /**
     * Logs the call of a method.
     *
     * @param runnable the method to call
     */
    private void onCall(Runnable runnable) {
        onCallResult((k, v) -> {
            runnable.run();
            return null;
        }, null, null);
    }

    /**
     * Executes and notifies the call of a method.
     *
     * @param consumer the method to call
     * @param key      the key used during the call
     */
    private <K> void onCall(Consumer<K> consumer, @Nullable K key) {
        onCallResult((k, v) -> {
            consumer.accept(k);
            return null;
        }, key, null);
    }

    /**
     * Executes and notifies the call of a method.
     *
     * @param consumer the method to call
     * @param key      the key used during the call
     * @param value    the value of the key
     */
    private <K, V> void onCall(BiConsumer<K, V> consumer, @Nullable K key, @Nullable V value) {
        onCallResult((k, v) -> {
            consumer.accept(k, v);
            return null;
        }, key, value);
    }

    /**
     * Executes and notifies the call of a method and returns the result.
     *
     * @param function the method to call
     * @param key      the key used during the call
     *
     * @return the result of the call
     */
    private <K, R> R onCallResult(Function<K, R> function, @Nullable K key) {
        return onCallResult((k, v) -> function.apply(k), key, null);
    }

    /**
     * Executes and notifies the call of a method and returns the result.
     *
     * @param function the method to call
     * @param key      the key used during the call
     * @param value    the value of the key
     *
     * @return the result of the call
     */
    @SuppressWarnings("unchecked")
    private <K, V, R> R onCallResult(BiFunction<K, V, R> function, @Nullable K key, @Nullable V value) {
        try {
            R result = function.apply(key, value);
            Object resultToLog = result;

            // Clone the stream
            if (result instanceof Stream) {
                Stream<?> stream = (Stream) result;
                List<?> list = stream.collect(Collectors.toList());

                result = (R) list.stream();
                resultToLog = list;
            }

            notifySuccess(key, value, resultToLog);
            return result;
        }
        catch (Exception e) {
            notifyError(key, value, e);
            throw e;
        }
    }

    /**
     * Notifies a successful method call.
     *
     * @param key    the key used during the call
     * @param value  the value of the key
     * @param result the result of the call
     */
    private <K, V, R> void notifySuccess(@Nullable K key, @Nullable V value, R result) {
        final SuccessCallReport<K, V, Object> report = new SuccessCallReport<>(backendReport.get(), getCallingMethod(), key, value, result);
        notifyListeners(l -> l.onSuccess(report));
    }

    /**
     * Notifies a failed method call.
     *
     * @param key   the key used during the call
     * @param value the value of the key
     * @param error the exception thrown during the call
     */
    private <K, V> void notifyError(@Nullable K key, @Nullable V value, Throwable error) {
        final FailureCallReport<K, V> report = new FailureCallReport<>(backendReport.get(), getCallingMethod(), key, value, error);
        notifyListeners(l -> l.onFailure(report));
    }

    /**
     * Notifies all listeners.
     *
     * @param func the function defining the notification
     */
    private void notifyListeners(Consumer<StoreListener> func) {
        listeners.forEach(func);
    }

    /**
     * Returns the name of the calling method.
     *
     * @return the name
     */
    @Nonnull
    private String getCallingMethod() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        for (int i = 4; i < stack.length; i++) {
            String methodName = stack[i].getMethodName();
            if (!methodName.startsWith("onCall") && !methodName.startsWith("dispatch")) {
                return methodName;
            }
        }

        throw new IllegalStateException(); // Should never happen
    }
}
