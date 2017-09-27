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

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.List;
import java.util.Optional;

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
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"}) // Called dynamically
public class ReadOnlyStore extends AbstractStore {

    /**
     * The exceptions thrown when calling methods.
     */
    private static final RuntimeException EXCEPTION = new UnsupportedOperationException("Operation forbidden in read-only mode");

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
    public boolean metaClassFor(Id id, ClassBean metaClass) {
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
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        throw EXCEPTION;
    }

    @Override
    public void removeReference(SingleFeatureBean key) {
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
}
