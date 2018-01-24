/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that only allows read operations on the underlying database.
 * <p>
 * Read-only configuration allows to access model element faster, without checking value consistency between database
 * calls. This store re-implements all the mutators and throws an {@link UnsupportedOperationException} when they are
 * called, preventing resource corruption.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public class ReadOnlyStore extends AbstractStore {

    /**
     * The exceptions thrown when calling methods.
     */
    @Nonnull
    private final Supplier<RuntimeException> e = () -> new UnsupportedOperationException("Operation forbidden in read-only mode");

    /**
     * Constructs a new {@code ReadOnlyStore} on the given {@code store}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    public ReadOnlyStore(Store store) {
        super(store);
    }

    @Override
    public void save() {
        throw e.get();
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        throw e.get();
    }

    @Override
    public void removeContainer(Id id) {
        throw e.get();
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        throw e.get();
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        throw e.get();
    }

    @Override
    public void removeValue(SingleFeatureBean key) {
        throw e.get();
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        throw e.get();
    }

    @Override
    public void removeReference(SingleFeatureBean key) {
        throw e.get();
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        throw e.get();
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        throw e.get();
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        throw e.get();
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureBean key, V value) {
        throw e.get();
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureBean key, List<? extends V> collection) {
        throw e.get();
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        throw e.get();
    }

    @Override
    public void removeAllValues(SingleFeatureBean key) {
        throw e.get();
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        throw e.get();
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        throw e.get();
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        throw e.get();
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureBean key, Id reference) {
        throw e.get();
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        throw e.get();
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        throw e.get();
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        throw e.get();
    }
}
