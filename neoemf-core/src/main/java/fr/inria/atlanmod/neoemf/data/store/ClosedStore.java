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
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A closed {@link Store}.
 * <p>
 * All methods throws an {@link UnsupportedOperationException}.
 */
@ParametersAreNonnullByDefault
public final class ClosedStore implements Store {

    /**
     * The exceptions thrown when calling methods.
     */
    private static final RuntimeException E = new IllegalStateException("The back-end you are using is closed");

    @Nonnull
    @Override
    public Backend backend() {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaclassOf(Id id) {
        throw E;
    }

    @Override
    public void metaclassFor(Id id, ClassBean metaclass) {
        throw E;
    }

    @Override
    public boolean hasMetaclass(Id id) {
        throw E;
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(ClassBean metaclass, boolean strict) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        throw E;
    }

    @Override
    public <V> void unsetValue(SingleFeatureBean key) {
        throw E;
    }

    @Override
    public <V> boolean hasValue(SingleFeatureBean key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        throw E;
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        throw E;
    }

    @Override
    public void unsetContainer(Id id) {
        throw E;
    }

    @Override
    public boolean hasContainer(Id id) {
        throw E;
    }

    @Override
    public void close() {
        // Do nothing
    }

    @Override
    public void save() {
        // Do nothing
    }

    @Override
    public void copyTo(DataMapper target) {
        throw E;
    }

    @Override
    public boolean exists(Id id) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> List<V> allValuesOf(SingleFeatureBean key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        throw E;
    }

    @Override
    public <V> boolean hasAnyValue(SingleFeatureBean key) {
        throw E;
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        throw E;
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        throw E;
    }

    @Override
    public <V> int appendValue(SingleFeatureBean key, V value) {
        throw E;
    }

    @Override
    public <V> int appendAllValues(SingleFeatureBean key, List<? extends V> collection) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        throw E;
    }

    @Override
    public <V> void removeAllValues(SingleFeatureBean key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> moveValue(ManyFeatureBean source, ManyFeatureBean target) {
        throw E;
    }

    @Override
    public <V> boolean containsValue(SingleFeatureBean key, @Nullable V value) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<Integer> indexOfValue(SingleFeatureBean key, @Nullable V value) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<Integer> lastIndexOfValue(SingleFeatureBean key, @Nullable V value) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        throw E;
    }

    @Override
    public void unsetReference(SingleFeatureBean key) {
        throw E;
    }

    @Override
    public boolean hasReference(SingleFeatureBean key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        throw E;
    }

    @Nonnull
    @Override
    public List<Id> allReferencesOf(SingleFeatureBean key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        throw E;
    }

    @Override
    public boolean hasAnyReference(SingleFeatureBean key) {
        throw E;
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        throw E;
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        throw E;
    }

    @Override
    public int appendReference(SingleFeatureBean key, Id reference) {
        throw E;
    }

    @Override
    public int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        throw E;
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> moveReference(ManyFeatureBean source, ManyFeatureBean target) {
        throw E;
    }

    @Override
    public boolean containsReference(SingleFeatureBean key, @Nullable Id reference) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Integer> indexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Integer> lastIndexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        throw E;
    }
}
