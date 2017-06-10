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

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link Store} wrapper that logs every call to its methods in the {@link Log}.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("MethodDoesntCallSuperMethod")
public class StatsStoreDecorator extends AbstractStoreDecorator {

    /**
     * A map that holds the different calls made on the {@link Store} chain.
     */
    private final Map<String, Integer> calls = new HashMap<>();

    /**
     * Constructs a new {@code LoggingStoreDecorator}.
     *
     * @param store the inner store
     */
    @SuppressWarnings("unused") // Called dynamically
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
    public Optional<ContainerDescriptor> containerOf(Id id) {
        return record(() -> super.containerOf(id));
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
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
    public <V> Optional<V> valueOf(FeatureKey key) {
        return record(() -> super.valueOf(key));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        return record(() -> super.valueFor(key, value));
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        record(() -> super.unsetValue(key));
    }

    @Override
    public <V> boolean hasValue(FeatureKey key) {
        return record(() -> super.hasValue(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        return record(() -> super.referenceOf(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        return record(() -> super.referenceFor(key, reference));
    }

    @Override
    public void unsetReference(FeatureKey key) {
        record(() -> super.unsetReference(key));
    }

    @Override
    public boolean hasReference(FeatureKey key) {
        return record(() -> super.hasReference(key));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        return record(() -> super.valueOf(key));
    }

    @Nonnull
    @Override
    public <V> List<V> allValuesOf(FeatureKey key) {
        return record(() -> super.allValuesOf(key));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        return record(() -> super.valueFor(key, value));
    }

    @Override
    public <V> boolean hasAnyValue(FeatureKey key) {
        return record(() -> super.hasAnyValue(key));
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        record(() -> super.addValue(key, value));
    }

    @Nonnegative
    @Override
    public <V> int appendValue(FeatureKey key, V value) {
        return record(() -> super.appendValue(key, value));
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(FeatureKey key, List<V> values) {
        return record(() -> super.appendAllValues(key, values));
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        return record(() -> super.removeValue(key));
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        record(() -> super.removeAllValues(key));
    }

    @Nonnull
    @Override
    public <V> Optional<V> moveValue(ManyFeatureKey source, ManyFeatureKey target) {
        return record(() -> super.moveValue(source, target));
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, @Nullable V value) {
        return record(() -> super.containsValue(key, value));
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> indexOfValue(FeatureKey key, @Nullable V value) {
        return record(() -> super.indexOfValue(key, value));
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> lastIndexOfValue(FeatureKey key, @Nullable V value) {
        return record(() -> super.lastIndexOfValue(key, value));
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> sizeOfValue(FeatureKey key) {
        return record(() -> super.sizeOfValue(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureKey key) {
        return record(() -> super.referenceOf(key));
    }

    @Nonnull
    @Override
    public List<Id> allReferencesOf(FeatureKey key) {
        return record(() -> super.allReferencesOf(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        return record(() -> super.referenceFor(key, reference));
    }

    @Override
    public boolean hasAnyReference(FeatureKey key) {
        return record(() -> super.hasAnyReference(key));
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        record(() -> super.addReference(key, reference));
    }

    @Nonnegative
    @Override
    public int appendReference(FeatureKey key, Id reference) {
        return record(() -> super.appendReference(key, reference));
    }

    @Nonnegative
    @Override
    public int appendAllReferences(FeatureKey key, List<Id> references) {
        return record(() -> super.appendAllReferences(key, references));
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        return record(() -> super.removeReference(key));
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        record(() -> super.removeAllReferences(key));
    }

    @Nonnull
    @Override
    public Optional<Id> moveReference(ManyFeatureKey source, ManyFeatureKey target) {
        return record(() -> super.moveReference(source, target));
    }

    @Override
    public boolean containsReference(FeatureKey key, @Nullable Id reference) {
        return record(() -> super.containsReference(key, reference));
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> indexOfReference(FeatureKey key, @Nullable Id reference) {
        return record(() -> super.indexOfReference(key, reference));
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> lastIndexOfReference(FeatureKey key, @Nullable Id reference) {
        return record(() -> super.lastIndexOfReference(key, reference));
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfReference(FeatureKey key) {
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
        calls.compute(getCallingMethod(), (s, count) -> nonNull(count) ? count + 1 : 1);
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
                    .sorted((e1, e2) -> e2.getValue() - e1.getValue()) // Descending order
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
