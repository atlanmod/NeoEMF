/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
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
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
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
    private static final RuntimeException EXCEPTION = new ClosedStoreException("Store is closed");

    @Nonnull
    @Override
    public Backend backend() {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public StoreStats stats() {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        throw EXCEPTION;
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(ClassBean metaClass, boolean strict) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        throw EXCEPTION;
    }

    @Override
    public <V> void removeValue(SingleFeatureBean key) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        throw EXCEPTION;
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        throw EXCEPTION;
    }

    @Override
    public void removeContainer(Id id) {
        throw EXCEPTION;
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
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean key) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        throw EXCEPTION;
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        throw EXCEPTION;
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        throw EXCEPTION;
    }

    @Override
    public <V> int appendValue(SingleFeatureBean key, V value) {
        throw EXCEPTION;
    }

    @Override
    public <V> int appendAllValues(SingleFeatureBean key, List<? extends V> collection) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        throw EXCEPTION;
    }

    @Override
    public <V> void removeAllValues(SingleFeatureBean key) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public <V> Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean key) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        throw EXCEPTION;
    }

    @Override
    public void removeReference(SingleFeatureBean key) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public Stream<Id> allReferencesOf(SingleFeatureBean key) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        throw EXCEPTION;
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        throw EXCEPTION;
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        throw EXCEPTION;
    }

    @Override
    public int appendReference(SingleFeatureBean key, Id reference) {
        throw EXCEPTION;
    }

    @Override
    public int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        throw EXCEPTION;
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        throw EXCEPTION;
    }
}
