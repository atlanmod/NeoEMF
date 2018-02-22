/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

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
public class ReadOnlyStore extends AbstractStore {

    /**
     * The exceptions thrown when calling methods.
     */
    @Nonnull
    private final Supplier<RuntimeException> e = () -> new UnsupportedOperationException("Operation forbidden in read-only mode");

    /**
     * Constructs a new {@code ReadOnlyStore}.
     */
    public ReadOnlyStore() {
        super(20);
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
    public <V> Optional<V> valueFor(SingleFeatureBean feature, V value) {
        throw e.get();
    }

    @Override
    public void removeValue(SingleFeatureBean feature) {
        throw e.get();
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean feature, Id reference) {
        throw e.get();
    }

    @Override
    public void removeReference(SingleFeatureBean feature) {
        throw e.get();
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean feature, V value) {
        throw e.get();
    }

    @Override
    public <V> void addValue(ManyFeatureBean feature, V value) {
        throw e.get();
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean feature, List<? extends V> values) {
        throw e.get();
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureBean feature, V value) {
        throw e.get();
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureBean feature, List<? extends V> values) {
        throw e.get();
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean feature) {
        throw e.get();
    }

    @Override
    public void removeAllValues(SingleFeatureBean feature) {
        throw e.get();
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean feature, Id reference) {
        throw e.get();
    }

    @Override
    public void addReference(ManyFeatureBean feature, Id reference) {
        throw e.get();
    }

    @Override
    public void addAllReferences(ManyFeatureBean feature, List<Id> references) {
        throw e.get();
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureBean feature, Id reference) {
        throw e.get();
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureBean feature, List<Id> references) {
        throw e.get();
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean feature) {
        throw e.get();
    }

    @Override
    public void removeAllReferences(SingleFeatureBean feature) {
        throw e.get();
    }
}
