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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link TransientBackend} used with databases that does not support the transient state.
 * <p>
 * All methods throws an {@link UnsupportedOperationException}.
 */
@ParametersAreNonnullByDefault
public final class InvalidTransientBackend implements TransientBackend {

    /**
     * The exception thrown when calling methods.
     */
    private static final RuntimeException EXCEPTION = new UnsupportedOperationException(
            "The back-end you are using doesn't provide a transient layer. "
                    + "You must save/load your resource before using it");

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
        // No need to close anything
    }

    @Override
    public void copyTo(DataMapper target) {
        // No need to copy anything
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
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public <V> List<V> allValuesOf(SingleFeatureBean key) {
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

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureBean key, V value) {
        throw EXCEPTION;
    }

    @Nonnegative
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
    public <V> Optional<V> moveValue(ManyFeatureBean source, ManyFeatureBean target) {
        throw EXCEPTION;
    }

    @Override
    public <V> boolean containsValue(SingleFeatureBean key, @Nullable V value) {
        throw EXCEPTION;
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> indexOfValue(SingleFeatureBean key, @Nullable V value) {
        throw EXCEPTION;
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> lastIndexOfValue(SingleFeatureBean key, @Nullable V value) {
        throw EXCEPTION;
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        throw EXCEPTION;
    }

    @Nonnull
    @Override
    public List<Id> allReferencesOf(SingleFeatureBean key) {
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

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureBean key, Id reference) {
        throw EXCEPTION;
    }

    @Nonnegative
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
    public Optional<Id> moveReference(ManyFeatureBean source, ManyFeatureBean target) {
        throw EXCEPTION;
    }

    @Override
    public boolean containsReference(SingleFeatureBean key, @Nullable Id reference) {
        throw EXCEPTION;
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> indexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        throw EXCEPTION;
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> lastIndexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        throw EXCEPTION;
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        throw EXCEPTION;
    }
}
