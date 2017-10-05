/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that records every calls to build usage statistics.
 *
 * @see StoreStats
 */
@ParametersAreNonnullByDefault
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"}) // Called dynamically
public class StatsRecordStore extends AbstractStore {

    /**
     * A map that accumulates the different calls made on the {@link Store} chain.
     */
    @Nonnull
    private final Map<String, AtomicLong> methodCallsAccumulator = new HashMap<>();

    /**
     * Constructs a new {@code LogStore}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    public StatsRecordStore(Store store) {
        super(store);
    }

    @Override
    public void close() {
        Log.info("Statistics for {0}: {1}", backend().getClass().getSimpleName() + '@' + backend().hashCode(), formatAsString());

        super.close();
    }

    @Override
    public void save() {
        record(super::save);
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        return record(() -> super.containerOf(id));
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        record(() -> super.containerFor(id, container));
    }

    @Override
    public void removeContainer(Id id) {
        record(() -> super.removeContainer(id));
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        return record(() -> super.metaClassOf(id));
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        return record(() -> super.metaClassFor(id, metaClass));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        return record(() -> super.valueOf(key));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        return record(() -> super.valueFor(key, value));
    }

    @Override
    public <V> void removeValue(SingleFeatureBean key) {
        record(() -> super.removeValue(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean key) {
        return record(() -> super.referenceOf(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        return record(() -> super.referenceFor(key, reference));
    }

    @Override
    public void removeReference(SingleFeatureBean key) {
        record(() -> super.removeReference(key));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        return record(() -> super.valueOf(key));
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean key) {
        return record(() -> super.allValuesOf(key));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        return record(() -> super.valueFor(key, value));
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        record(() -> super.addValue(key, value));
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        record(() -> super.addAllValues(key, collection));
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureBean key, V value) {
        return record(() -> super.appendValue(key, value));
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureBean key, List<? extends V> collection) {
        return record(() -> super.appendAllValues(key, collection));
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        return record(() -> super.removeValue(key));
    }

    @Override
    public <V> void removeAllValues(SingleFeatureBean key) {
        record(() -> super.removeAllValues(key));
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        return record(() -> super.sizeOfValue(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        return record(() -> super.referenceOf(key));
    }

    @Nonnull
    @Override
    public Stream<Id> allReferencesOf(SingleFeatureBean key) {
        return record(() -> super.allReferencesOf(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        return record(() -> super.referenceFor(key, reference));
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        record(() -> super.addReference(key, reference));
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        record(() -> super.addAllReferences(key, collection));
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureBean key, Id reference) {
        return record(() -> super.appendReference(key, reference));
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        return record(() -> super.appendAllReferences(key, collection));
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        return record(() -> super.removeReference(key));
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        record(() -> super.removeAllReferences(key));
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        return record(() -> super.sizeOfReference(key));
    }

    @Nonnull
    @Override
    public StoreStats stats() {
        return new StoreStats(methodCallsAccumulator);
    }

    /**
     * Records the call of a method.
     *
     * @param runnable the method to call
     */
    private void record(Runnable runnable) {
        record();
        runnable.run();
    }

    /**
     * Records the call of a method and returns the result.
     *
     * @param supplier the method to call
     *
     * @return the result of the call
     */
    private <R> R record(Supplier<R> supplier) {
        record();
        return supplier.get();
    }

    /**
     * Records the call of a method with a value.
     */
    private void record() {
        methodCallsAccumulator.computeIfAbsent(getCallingMethod(), s -> new AtomicLong()).incrementAndGet();
    }

    /**
     * Formats the results as a string.
     *
     * @return a formatted string
     */
    private String formatAsString() {
        return methodCallsAccumulator.isEmpty()
                ? "Nothing has been recorded"
                : "\n" + new StoreStats(methodCallsAccumulator);
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
