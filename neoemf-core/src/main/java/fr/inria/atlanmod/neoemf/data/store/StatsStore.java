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
import java.util.stream.Collectors;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that logs every call to its methods in the {@link Log}.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"}) // Called dynamically
public class StatsStore extends AbstractStore {

    /**
     * A map that holds the different calls made on the {@link Store} chain.
     */
    private final Map<String, AtomicLong> calls = new HashMap<>();

    /**
     * Constructs a new {@code LoggingStore}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    public StatsStore(Store store) {
        super(store);
    }

    @Override
    public void close() {
        Log.debug("Statistics for {0}: {1}", backend().getClass().getSimpleName() + '@' + backend().hashCode(), formatAsString());

        super.close();
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
    public void unsetContainer(Id id) {
        record(() -> super.unsetContainer(id));
    }

    @Override
    public boolean hasContainer(Id id) {
        return record(() -> super.hasContainer(id));
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

    @Override
    public boolean hasMetaclass(Id id) {
        return record(() -> super.hasMetaclass(id));
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
    public <V> void unsetValue(SingleFeatureBean key) {
        record(() -> super.unsetValue(key));
    }

    @Override
    public <V> boolean hasValue(SingleFeatureBean key) {
        return record(() -> super.hasValue(key));
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
    public void unsetReference(SingleFeatureBean key) {
        record(() -> super.unsetReference(key));
    }

    @Override
    public boolean hasReference(SingleFeatureBean key) {
        return record(() -> super.hasReference(key));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        return record(() -> super.valueOf(key));
    }

    @Nonnull
    @Override
    public <V> List<V> allValuesOf(SingleFeatureBean key) {
        return record(() -> super.allValuesOf(key));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        return record(() -> super.valueFor(key, value));
    }

    @Override
    public <V> boolean hasAnyValue(SingleFeatureBean key) {
        return record(() -> super.hasAnyValue(key));
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
    @Override
    public <V> Optional<V> moveValue(ManyFeatureBean source, ManyFeatureBean target) {
        return record(() -> super.moveValue(source, target));
    }

    @Override
    public <V> boolean containsValue(SingleFeatureBean key, @Nullable V value) {
        return record(() -> super.containsValue(key, value));
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> indexOfValue(SingleFeatureBean key, @Nullable V value) {
        return record(() -> super.indexOfValue(key, value));
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> lastIndexOfValue(SingleFeatureBean key, @Nullable V value) {
        return record(() -> super.lastIndexOfValue(key, value));
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
    public List<Id> allReferencesOf(SingleFeatureBean key) {
        return record(() -> super.allReferencesOf(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        return record(() -> super.referenceFor(key, reference));
    }

    @Override
    public boolean hasAnyReference(SingleFeatureBean key) {
        return record(() -> super.hasAnyReference(key));
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
    @Override
    public Optional<Id> moveReference(ManyFeatureBean source, ManyFeatureBean target) {
        return record(() -> super.moveReference(source, target));
    }

    @Override
    public boolean containsReference(SingleFeatureBean key, @Nullable Id reference) {
        return record(() -> super.containsReference(key, reference));
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> indexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        return record(() -> super.indexOfReference(key, reference));
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> lastIndexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        return record(() -> super.lastIndexOfReference(key, reference));
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        return record(() -> super.sizeOfReference(key));
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
        calls.computeIfAbsent(getCallingMethod(), s -> new AtomicLong()).incrementAndGet();
    }

    /**
     * Formats the results as a string.
     *
     * @return a formatted string
     */
    private String formatAsString() {
        if (calls.isEmpty()) {
            return "Nothing has been recorded";
        }
        else {
            return "\n" + calls.entrySet().stream()
                    .sorted((e1, e2) -> Long.compare(e2.getValue().get(), e1.getValue().get())) // Descending order
                    .map(e -> e.getKey() + " = " + e.getValue())
                    .collect(Collectors.joining("\n"));
        }
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
