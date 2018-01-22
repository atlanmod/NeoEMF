/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package ${package};

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotContainsNull;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * The default {@link ${databaseName}Backend} mapping.
 *
 * @see ${databaseName}BackendFactory
 */
@ParametersAreNonnullByDefault
class Default${databaseName}Backend extends Abstract${databaseName}Backend {

    /**
     * Constructs a new {@code Default${databaseName}Backend}.
     *
     * @see ${databaseName}BackendFactory
     */
    protected Default${databaseName}Backend() {
        super();

        // TODO Implement this constructor
    }

    //region Single-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("valueOf");
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        // TODO Implement this method
        throw Throwables.notImplementedYet("valueFor");
    }

    @Override
    public void removeValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeValue");
    }

    //endregion

    //region Single-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("referenceOf");
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        // TODO Implement this method
        throw Throwables.notImplementedYet("referenceFor");
    }

    @Override
    public void removeReference(SingleFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeReference");
    }

    //endregion

    //region Multi-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("valueOf");
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("allValuesOf");
    }

    @Nonnull
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        // TODO Implement this method
        throw Throwables.notImplementedYet("valueFor");
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        // TODO Implement this method
        throw Throwables.notImplementedYet("addValue");
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        checkNotNull(key, "key");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        // TODO Implement this method
        throw Throwables.notImplementedYet("addAllValues");
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeValue");
    }

    @Override
    public void removeAllValues(SingleFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeAllValues");
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("sizeOfValue");
    }

    //endregion

    //region Multi-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("referenceOf");
    }

    @Nonnull
    @Override
    public Stream<Id> allReferencesOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("allReferencesOf");
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        // TODO Implement this method
        throw Throwables.notImplementedYet("referenceFor");
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        // TODO Implement this method
        throw Throwables.notImplementedYet("addReference");
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        checkNotNull(key, "key");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        // TODO Implement this method
        throw Throwables.notImplementedYet("addAllReferences");
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeReference");
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeAllReferences");
    }

    @Nonnull
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("sizeOfReference");
    }

    //endregion
}
