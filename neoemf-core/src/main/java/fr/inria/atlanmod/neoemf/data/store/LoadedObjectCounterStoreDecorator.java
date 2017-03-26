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
import fr.inria.atlanmod.neoemf.util.log.Log;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that count the number elements used.
 */
@ParametersAreNonnullByDefault
public class LoadedObjectCounterStoreDecorator extends AbstractStoreDecorator {

    /**
     * Set that holds loaded objects.
     */
    private final Set<Id> loadedObjects;

    /**
     * Constructs a new {@code LoadedObjectCounterStoreDecorator}.
     *
     * @param store the inner store
     */
    public LoadedObjectCounterStoreDecorator(Store store) {
        super(store);
        loadedObjects = new TreeSet<>();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> Log.info("{0} objects loaded during the execution", loadedObjects.size())));
    }

    @Nonnull
    @Override
    public Optional<ContainerDescriptor> containerOf(Id id) {
        loadedObjects.add(id);
        return super.containerOf(id);
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
        loadedObjects.add(id);
        super.containerFor(id, container);
    }

    @Override
    public void unsetContainer(Id id) {
        loadedObjects.add(id);
        super.unsetContainer(id);
    }

    @Nonnull
    @Override
    public Optional<ClassDescriptor> metaclassOf(Id id) {
        loadedObjects.add(id);
        return super.metaclassOf(id);
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        loadedObjects.add(id);
        super.metaclassFor(id, metaclass);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        loadedObjects.add(key.id());
        return super.valueOf(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        loadedObjects.add(key.id());
        return super.valueFor(key, value);
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        loadedObjects.add(key.id());
        super.unsetValue(key);
    }

    @Override
    public <V> boolean hasValue(FeatureKey key) {
        loadedObjects.add(key.id());
        return super.hasValue(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        loadedObjects.add(key.id());
        return super.referenceOf(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        loadedObjects.add(key.id());
        return super.referenceFor(key, reference);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        loadedObjects.add(key.id());
        super.unsetReference(key);
    }

    @Override
    public boolean hasReference(FeatureKey key) {
        loadedObjects.add(key.id());
        return super.hasReference(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        loadedObjects.add(key.id());
        return super.valueOf(key);
    }

    @Nonnull
    @Override
    public <V> List<V> allValuesOf(FeatureKey key) {
        loadedObjects.add(key.id());
        return super.allValuesOf(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        loadedObjects.add(key.id());
        return super.valueFor(key, value);
    }

    @Override
    public <V> boolean hasAnyValue(FeatureKey key) {
        loadedObjects.add(key.id());
        return super.hasAnyValue(key);
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        loadedObjects.add(key.id());
        super.addValue(key, value);
    }

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        loadedObjects.add(key.id());
        super.appendValue(key, value);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        loadedObjects.add(key.id());
        return super.removeValue(key);
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        loadedObjects.add(key.id());
        super.removeAllValues(key);
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, @Nullable V value) {
        loadedObjects.add(key.id());
        return super.containsValue(key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, @Nullable V value) {
        loadedObjects.add(key.id());
        return super.indexOfValue(key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, @Nullable V value) {
        loadedObjects.add(key.id());
        return super.lastIndexOfValue(key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt sizeOfValue(FeatureKey key) {
        loadedObjects.add(key.id());
        return super.sizeOfValue(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureKey key) {
        loadedObjects.add(key.id());
        return super.referenceOf(key);
    }

    @Nonnull
    @Override
    public List<Id> allReferencesOf(FeatureKey key) {
        loadedObjects.add(key.id());
        return super.allReferencesOf(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        loadedObjects.add(key.id());
        return super.referenceFor(key, reference);
    }

    @Override
    public boolean hasAnyReference(FeatureKey key) {
        loadedObjects.add(key.id());
        return super.hasAnyReference(key);
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        loadedObjects.add(key.id());
        super.addReference(key, reference);
    }

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        loadedObjects.add(key.id());
        super.appendReference(key, reference);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        loadedObjects.add(key.id());
        return super.removeReference(key);
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        loadedObjects.add(key.id());
        super.removeAllReferences(key);
    }

    @Override
    public boolean containsReference(FeatureKey key, @Nullable Id reference) {
        loadedObjects.add(key.id());
        return super.containsReference(key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(FeatureKey key, @Nullable Id reference) {
        loadedObjects.add(key.id());
        return super.indexOfReference(key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, @Nullable Id reference) {
        loadedObjects.add(key.id());
        return super.lastIndexOfReference(key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt sizeOfReference(FeatureKey key) {
        loadedObjects.add(key.id());
        return super.sizeOfReference(key);
    }
}
