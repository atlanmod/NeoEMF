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
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassDescriptor;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link PersistentStore} wrapper that count the number elements used.
 */
@ParametersAreNonnullByDefault
public class LoadedObjectCounterStoreDecorator extends AbstractPersistentStoreDecorator<PersistentStore> {

    /**
     * Set that holds loaded objects.
     */
    private final Set<Id> loadedObjects;

    /**
     * Constructs a new {@code LoadedObjectCounterStoreDecorator}.
     *
     * @param store the underlying store
     */
    public LoadedObjectCounterStoreDecorator(PersistentStore store) {
        super(store);
        loadedObjects = new TreeSet<>();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> NeoLogger.info("{0} objects loaded during the execution", loadedObjects.size())));
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
    public Optional<MetaclassDescriptor> metaclassOf(Id id) {
        loadedObjects.add(id);
        return super.metaclassOf(id);
    }

    @Override
    public void metaclassFor(Id id, MetaclassDescriptor metaclass) {
        loadedObjects.add(id);
        super.metaclassFor(id, metaclass);
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

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(MultiFeatureKey key) {
        loadedObjects.add(key.id());
        return super.valueOf(key);
    }

    @Nonnull
    @Override
    public <V> Iterable<V> allValuesOf(FeatureKey key) {
        loadedObjects.add(key.id());
        return super.allValuesOf(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(MultiFeatureKey key, V value) {
        loadedObjects.add(key.id());
        return super.valueFor(key, value);
    }

    @Override
    public <V> void unsetAllValues(FeatureKey key) {
        loadedObjects.add(key.id());
        super.unsetAllValues(key);
    }

    @Override
    public <V> boolean hasAnyValue(FeatureKey key) {
        loadedObjects.add(key.id());
        return super.hasAnyValue(key);
    }

    @Override
    public <V> void addValue(MultiFeatureKey key, V value) {
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
    public <V> Optional<V> removeValue(MultiFeatureKey key) {
        loadedObjects.add(key.id());
        return super.removeValue(key);
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        loadedObjects.add(key.id());
        super.removeAllValues(key);
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, V value) {
        loadedObjects.add(key.id());
        return super.containsValue(key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, V value) {
        loadedObjects.add(key.id());
        return super.indexOfValue(key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, V value) {
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
    public Optional<Id> referenceOf(MultiFeatureKey key) {
        loadedObjects.add(key.id());
        return super.referenceOf(key);
    }

    @Nonnull
    @Override
    public Iterable<Id> allReferencesOf(FeatureKey key) {
        loadedObjects.add(key.id());
        return super.allReferencesOf(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(MultiFeatureKey key, Id reference) {
        loadedObjects.add(key.id());
        return super.referenceFor(key, reference);
    }

    @Override
    public void unsetAllReferences(FeatureKey key) {
        loadedObjects.add(key.id());
        super.unsetAllReferences(key);
    }

    @Override
    public boolean hasAnyReference(FeatureKey key) {
        loadedObjects.add(key.id());
        return super.hasAnyReference(key);
    }

    @Override
    public void addReference(MultiFeatureKey key, Id reference) {
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
    public Optional<Id> removeReference(MultiFeatureKey key) {
        loadedObjects.add(key.id());
        return super.removeReference(key);
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        loadedObjects.add(key.id());
        super.removeAllReferences(key);
    }

    @Override
    public boolean containsReference(FeatureKey key, Id reference) {
        loadedObjects.add(key.id());
        return super.containsReference(key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(FeatureKey key, Id reference) {
        loadedObjects.add(key.id());
        return super.indexOfReference(key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, Id reference) {
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
