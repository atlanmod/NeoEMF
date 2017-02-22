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
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassDescriptor;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link PersistentStore} that translates model-level operations into datastore calls.
 */
@ParametersAreNonnullByDefault
public class DirectWriteStore extends AbstractPersistentStore {

    /**
     * The persistence back-end used to store the model.
     */
    @Nonnull
    protected final PersistenceBackend backend;

    /**
     * The resource to persist and access.
     */
    @Nullable
    private final PersistentResource resource;

    /**
     * Constructs a new {@code DirectWriteStore} between the given {@code resource} and the {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteStore(@Nullable PersistentResource resource, PersistenceBackend backend) {
        this.resource = resource;
        this.backend = backend;
    }

    @Override
    public void save() {
        backend.save();
    }

    @Override
    public PersistentResource resource() {
        return resource;
    }

    /**
     * Computes efficiently {@code allInstances} operation by using underlying graph facilities. This method uses
     * database indices to avoid costly traversal of the entire model.
     *
     * @param metaclass the {@link EClass} to get the instances of
     * @param strict    set to {@code true} if the method should look for instances of {@code eClass} only, set to
     *                  {@code false} if the method should also return elements that are subclasses of {@code eClass}
     */
    @Override
    public Iterable<EObject> allInstances(EClass metaclass, boolean strict) {
        return StreamSupport.stream(backend.allInstances(metaclass, strict).spliterator(), false)
                .map(this::object)
                .collect(Collectors.toList());
    }

    @Override
    public boolean exists(Id id) {
        return backend.exists(id);
    }

    @Nonnull
    @Override
    public Optional<ContainerDescriptor> containerOf(Id id) {
        return backend.containerOf(id);
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
        backend.containerFor(id, container);
    }

    @Nonnull
    @Override
    public Optional<MetaclassDescriptor> metaclassOf(Id id) {
        return backend.metaclassOf(id);
    }

    @Override
    public void metaclassFor(Id id, MetaclassDescriptor metaclass) {
        backend.metaclassFor(id, metaclass);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        return backend.valueOf(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        return backend.valueFor(key, value);
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        backend.unsetValue(key);
    }

    @Override
    public <V> boolean hasValue(FeatureKey key) {
        return backend.hasValue(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        return backend.valueOf(key);
    }

    @Nonnull
    @Override
    public <V> Iterable<V> allValuesOf(FeatureKey key) {
        return backend.allValuesOf(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        return backend.valueFor(key, value);
    }

    @Override
    public <V> boolean hasAnyValue(FeatureKey key) {
        return backend.hasAnyValue(key);
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        backend.addValue(key, value);
    }

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        backend.appendValue(key, value);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        return backend.removeValue(key);
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        backend.removeAllValues(key);
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, @Nullable V value) {
        return backend.containsValue(key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, @Nullable V value) {
        return backend.indexOfValue(key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, @Nullable V value) {
        return backend.lastIndexOfValue(key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt sizeOfValue(FeatureKey key) {
        return backend.sizeOfValue(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        return backend.referenceOf(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        return backend.referenceFor(key, reference);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        backend.unsetReference(key);
    }

    @Override
    public boolean hasReference(FeatureKey key) {
        return backend.hasReference(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureKey key) {
        return backend.referenceOf(key);
    }

    @Nonnull
    @Override
    public Iterable<Id> allReferencesOf(FeatureKey key) {
        return backend.allReferencesOf(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        return backend.referenceFor(key, reference);
    }

    @Override
    public boolean hasAnyReference(FeatureKey key) {
        return backend.hasAnyReference(key);
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        backend.addReference(key, reference);
    }

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        backend.appendReference(key, reference);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        return backend.removeReference(key);
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        backend.removeAllReferences(key);
    }

    @Override
    public boolean containsReference(FeatureKey key, @Nullable Id reference) {
        return backend.containsReference(key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(FeatureKey key, @Nullable Id reference) {
        return backend.indexOfReference(key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, @Nullable Id reference) {
        return backend.lastIndexOfReference(key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt sizeOfReference(FeatureKey key) {
        return backend.sizeOfReference(key);
    }
}