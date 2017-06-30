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

import fr.inria.atlanmod.common.annotations.VisibleForReflection;
import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

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
public class StatsStoreDecorator extends AbstractStoreDecorator {

    /**
     * A map that holds the different calls made on the {@link Store} chain.
     */
    private final Map<String, AtomicLong> calls = new HashMap<>();

    /**
     * Constructs a new {@code LoggingStoreDecorator}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    public StatsStoreDecorator(Store store) {
        super(store);
    }

    @Override
    public void close() {
        Log.debug("Statistics for {0}: {1}", backend().getClass().getSimpleName() + "@" + backend().hashCode(), formatAsString());

        super.close();
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureKey> containerOf(Id id) {
        return record(() -> super.containerOf(id));
    }

    @Override
    public void containerFor(Id id, SingleFeatureKey container) {
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
    public Optional<ClassDescriptor> metaclassOf(Id id) {
        return record(() -> super.metaclassOf(id));
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        record(() -> super.metaclassFor(id, metaclass));
    }

    @Override
    public boolean hasMetaclass(Id id) {
        return record(() -> super.hasMetaclass(id));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureKey key) {
        return record(() -> super.valueOf(key));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureKey key, V value) {
        return record(() -> super.valueFor(key, value));
    }

    @Override
    public <V> void unsetValue(SingleFeatureKey key) {
        record(() -> super.unsetValue(key));
    }

    @Override
    public <V> boolean hasValue(SingleFeatureKey key) {
        return record(() -> super.hasValue(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureKey key) {
        return record(() -> super.referenceOf(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureKey key, Id reference) {
        return record(() -> super.referenceFor(key, reference));
    }

    @Override
    public void unsetReference(SingleFeatureKey key) {
        record(() -> super.unsetReference(key));
    }

    @Override
    public boolean hasReference(SingleFeatureKey key) {
        return record(() -> super.hasReference(key));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        return record(() -> super.valueOf(key));
    }

    @Nonnull
    @Override
    public <V> List<V> allValuesOf(SingleFeatureKey key) {
        return record(() -> super.allValuesOf(key));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        return record(() -> super.valueFor(key, value));
    }

    @Override
    public <V> boolean hasAnyValue(SingleFeatureKey key) {
        return record(() -> super.hasAnyValue(key));
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        record(() -> super.addValue(key, value));
    }

    @Override
    public <V> void addAllValues(ManyFeatureKey key, List<? extends V> collection) {
        record(() -> super.addAllValues(key, collection));
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureKey key, V value) {
        return record(() -> super.appendValue(key, value));
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureKey key, List<? extends V> collection) {
        return record(() -> super.appendAllValues(key, collection));
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        return record(() -> super.removeValue(key));
    }

    @Override
    public <V> void removeAllValues(SingleFeatureKey key) {
        record(() -> super.removeAllValues(key));
    }

    @Nonnull
    @Override
    public <V> Optional<V> moveValue(ManyFeatureKey source, ManyFeatureKey target) {
        return record(() -> super.moveValue(source, target));
    }

    @Override
    public <V> boolean containsValue(SingleFeatureKey key, @Nullable V value) {
        return record(() -> super.containsValue(key, value));
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> indexOfValue(SingleFeatureKey key, @Nullable V value) {
        return record(() -> super.indexOfValue(key, value));
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> lastIndexOfValue(SingleFeatureKey key, @Nullable V value) {
        return record(() -> super.lastIndexOfValue(key, value));
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> sizeOfValue(SingleFeatureKey key) {
        return record(() -> super.sizeOfValue(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureKey key) {
        return record(() -> super.referenceOf(key));
    }

    @Nonnull
    @Override
    public List<Id> allReferencesOf(SingleFeatureKey key) {
        return record(() -> super.allReferencesOf(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        return record(() -> super.referenceFor(key, reference));
    }

    @Override
    public boolean hasAnyReference(SingleFeatureKey key) {
        return record(() -> super.hasAnyReference(key));
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        record(() -> super.addReference(key, reference));
    }

    @Override
    public void addAllReferences(ManyFeatureKey key, List<Id> collection) {
        record(() -> super.addAllReferences(key, collection));
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureKey key, Id reference) {
        return record(() -> super.appendReference(key, reference));
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureKey key, List<Id> collection) {
        return record(() -> super.appendAllReferences(key, collection));
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        return record(() -> super.removeReference(key));
    }

    @Override
    public void removeAllReferences(SingleFeatureKey key) {
        record(() -> super.removeAllReferences(key));
    }

    @Nonnull
    @Override
    public Optional<Id> moveReference(ManyFeatureKey source, ManyFeatureKey target) {
        return record(() -> super.moveReference(source, target));
    }

    @Override
    public boolean containsReference(SingleFeatureKey key, @Nullable Id reference) {
        return record(() -> super.containsReference(key, reference));
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> indexOfReference(SingleFeatureKey key, @Nullable Id reference) {
        return record(() -> super.indexOfReference(key, reference));
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> lastIndexOfReference(SingleFeatureKey key, @Nullable Id reference) {
        return record(() -> super.lastIndexOfReference(key, reference));
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureKey key) {
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
