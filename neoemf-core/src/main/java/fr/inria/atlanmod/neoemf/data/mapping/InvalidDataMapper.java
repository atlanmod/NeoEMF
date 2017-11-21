/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An invalid {@link DataMapper} that throws a {@link RuntimeException} at each call.
 */
@ParametersAreNonnullByDefault
public class InvalidDataMapper implements DataMapper {

    /**
     * The exception thrown when calling methods.
     */
    @Nonnull
    protected final Supplier<RuntimeException> e;

    /**
     * Constructs a new {@code InvalidDataMapper} with the exception thrown when calling unsupported methods.
     *
     * @param e the exception thrown when calling unsupported methods
     */
    public InvalidDataMapper(Supplier<RuntimeException> e) {
        this.e = checkNotNull(e, "e");
    }

    @Override
    public void copyTo(DataMapper target) {
        throw e.get();
    }

    @Override
    public void close() {
        // Do nothing (see PersistentResource#unload())
    }

    @Override
    public void save() {
        // Do nothing (see PersistentResource#save())
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        throw e.get();
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        throw e.get();
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(ClassBean metaClass, boolean strict) {
        throw e.get();
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        throw e.get();
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
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

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
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
    public Optional<Id> referenceOf(SingleFeatureBean key) {
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
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        throw e.get();
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean key) {
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

    @Override
    public <V> int appendValue(SingleFeatureBean key, V value) {
        throw e.get();
    }

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
    public Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        throw e.get();
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        throw e.get();
    }

    @Nonnull
    @Override
    public Stream<Id> allReferencesOf(SingleFeatureBean key) {
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

    @Override
    public int appendReference(SingleFeatureBean key, Id reference) {
        throw e.get();
    }

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

    @Nonnull
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        throw e.get();
    }
}
