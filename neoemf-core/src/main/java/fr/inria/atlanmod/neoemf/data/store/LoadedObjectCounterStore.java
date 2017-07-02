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

import fr.inria.atlanmod.common.annotation.VisibleForReflection;
import fr.inria.atlanmod.common.concurrent.MoreExecutors;
import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.FeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that count the number elements used.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public class LoadedObjectCounterStore extends AbstractStore {

    /**
     * Set that holds loaded objects.
     */
    private final Set<Id> loadedObjects;

    /**
     * Constructs a new {@code LoadedObjectCounterStore}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    public LoadedObjectCounterStore(Store store) {
        super(store);
        loadedObjects = new TreeSet<>();

        MoreExecutors.executeAtExit(() -> Log.info("{0} objects loaded during the execution", loadedObjects.size()));
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        register(id);
        return super.containerOf(id);
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        register(id);
        super.containerFor(id, container);
    }

    @Override
    public void unsetContainer(Id id) {
        register(id);
        super.unsetContainer(id);
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaclassOf(Id id) {
        register(id);
        return super.metaclassOf(id);
    }

    @Override
    public void metaclassFor(Id id, ClassBean metaclass) {
        register(id);
        super.metaclassFor(id, metaclass);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        register(key);
        return super.valueOf(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        register(key);
        return super.valueFor(key, value);
    }

    @Override
    public <V> void unsetValue(SingleFeatureBean key) {
        register(key);
        super.unsetValue(key);
    }

    @Override
    public <V> boolean hasValue(SingleFeatureBean key) {
        register(key);
        return super.hasValue(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean key) {
        register(key);
        return super.referenceOf(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        register(key);
        return super.referenceFor(key, reference);
    }

    @Override
    public void unsetReference(SingleFeatureBean key) {
        register(key);
        super.unsetReference(key);
    }

    @Override
    public boolean hasReference(SingleFeatureBean key) {
        register(key);
        return super.hasReference(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        register(key);
        return super.valueOf(key);
    }

    @Nonnull
    @Override
    public <V> List<V> allValuesOf(SingleFeatureBean key) {
        register(key);
        return super.allValuesOf(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        register(key);
        return super.valueFor(key, value);
    }

    @Override
    public <V> boolean hasAnyValue(SingleFeatureBean key) {
        register(key);
        return super.hasAnyValue(key);
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        register(key);
        super.addValue(key, value);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        register(key);
        super.addAllValues(key, collection);
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureBean key, V value) {
        register(key);
        return super.appendValue(key, value);
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureBean key, List<? extends V> collection) {
        register(key);
        return super.appendAllValues(key, collection);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        register(key);
        return super.removeValue(key);
    }

    @Override
    public <V> void removeAllValues(SingleFeatureBean key) {
        register(key);
        super.removeAllValues(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> moveValue(ManyFeatureBean source, ManyFeatureBean target) {
        register(source);
        register(target);
        return super.moveValue(source, target);
    }

    @Override
    public <V> boolean containsValue(SingleFeatureBean key, @Nullable V value) {
        register(key);
        return super.containsValue(key, value);
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> indexOfValue(SingleFeatureBean key, @Nullable V value) {
        register(key);
        return super.indexOfValue(key, value);
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> lastIndexOfValue(SingleFeatureBean key, @Nullable V value) {
        register(key);
        return super.lastIndexOfValue(key, value);
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        register(key);
        return super.sizeOfValue(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        register(key);
        return super.referenceOf(key);
    }

    @Nonnull
    @Override
    public List<Id> allReferencesOf(SingleFeatureBean key) {
        register(key);
        return super.allReferencesOf(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        register(key);
        return super.referenceFor(key, reference);
    }

    @Override
    public boolean hasAnyReference(SingleFeatureBean key) {
        register(key);
        return super.hasAnyReference(key);
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        register(key);
        super.addReference(key, reference);
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        register(key);
        super.addAllReferences(key, collection);
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureBean key, Id reference) {
        register(key);
        return super.appendReference(key, reference);
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        register(key);
        return super.appendAllReferences(key, collection);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        register(key);
        return super.removeReference(key);
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        register(key);
        super.removeAllReferences(key);
    }

    @Nonnull
    @Override
    public Optional<Id> moveReference(ManyFeatureBean source, ManyFeatureBean target) {
        register(source);
        register(target);
        return super.moveReference(source, target);
    }

    @Override
    public boolean containsReference(SingleFeatureBean key, @Nullable Id reference) {
        register(key);
        return super.containsReference(key, reference);
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> indexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        register(key);
        return super.indexOfReference(key, reference);
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> lastIndexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        register(key);
        return super.lastIndexOfReference(key, reference);
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        register(key);
        return super.sizeOfReference(key);
    }

    /**
     * Registers the given {@code key} as loaded.
     *
     * @param key the key from which to extract the identifier
     *
     * @see #register(Id)
     */
    private void register(FeatureBean key) {
        register(key.id());
    }

    /**
     * Registers the given {@code id} as loaded.
     *
     * @param id the identifier to register
     */
    private void register(Id id) {
        loadedObjects.add(id);
    }
}
