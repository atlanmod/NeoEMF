/*
 * Copyright (c) 2013 Atlanmod.
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
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Guards.checkNotNull;

/**
 * An abstract {@link DataMapper} that delegates method calls to a {@link DataMapper} chain.
 *
 * @param <M> the type of the inner {@link DataMapper}
 */
@ParametersAreNonnullByDefault
public abstract class AbstractDataMapperChain<M extends DataMapper> extends AbstractDataMapper {

    /**
     * The next mapper to notify.
     */
    private M next;

    /**
     * Returns the next mapper to notify.
     *
     * @return the next mapper
     */
    @Nonnull
    protected M next() {
        return checkNotNull(next, "next");
    }

    /**
     * Defines the next mapper to notify.
     *
     * @param next the next mapper
     */
    protected void next(M next) {
        this.next = checkNotNull(next, "next");
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void close() {
        next.close();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void save() {
        next.save();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void copyTo(DataMapper target) {
        next.copyTo(target);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<SingleFeatureBean> containerOf(Id id) {
        return next.containerOf(id);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void containerFor(Id id, SingleFeatureBean container) {
        next.containerFor(id, container);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void removeContainer(Id id) {
        next.removeContainer(id);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<ClassBean> metaClassOf(Id id) {
        return next.metaClassOf(id);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        return next.metaClassFor(id, metaClass);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Stream<Id> allInstancesOf(ClassBean metaClass, boolean strict) {
        return next.allInstancesOf(metaClass, strict);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Stream<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        return next.allInstancesOf(metaClasses);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> valueOf(SingleFeatureBean feature) {
        return next.valueOf(feature);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> valueFor(SingleFeatureBean feature, V value) {
        return next.valueFor(feature, value);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void removeValue(SingleFeatureBean feature) {
        next.removeValue(feature);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> referenceOf(SingleFeatureBean feature) {
        return next.referenceOf(feature);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> referenceFor(SingleFeatureBean feature, Id reference) {
        return next.referenceFor(feature, reference);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void removeReference(SingleFeatureBean feature) {
        next.removeReference(feature);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> valueOf(ManyFeatureBean feature) {
        return next.valueOf(feature);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Stream<V> allValuesOf(SingleFeatureBean feature) {
        return next.allValuesOf(feature);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> valueFor(ManyFeatureBean feature, V value) {
        return next.valueFor(feature, value);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> void addValue(ManyFeatureBean feature, V value) {
        next.addValue(feature, value);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> void addAllValues(ManyFeatureBean feature, List<? extends V> collection) {
        next.addAllValues(feature, collection);
    }

    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> int appendValue(SingleFeatureBean feature, V value) {
        return next.appendValue(feature, value);
    }

    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> int appendAllValues(SingleFeatureBean feature, List<? extends V> collection) {
        return next.appendAllValues(feature, collection);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> removeValue(ManyFeatureBean feature) {
        return next.removeValue(feature);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void removeAllValues(SingleFeatureBean feature) {
        next.removeAllValues(feature);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Integer> sizeOfValue(SingleFeatureBean feature) {
        return next.sizeOfValue(feature);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> referenceOf(ManyFeatureBean feature) {
        return next.referenceOf(feature);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Stream<Id> allReferencesOf(SingleFeatureBean feature) {
        return next.allReferencesOf(feature);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> referenceFor(ManyFeatureBean feature, Id reference) {
        return next.referenceFor(feature, reference);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void addReference(ManyFeatureBean feature, Id reference) {
        next.addReference(feature, reference);
    }

    @Override
    public void addAllReferences(ManyFeatureBean feature, List<Id> collection) {
        next.addAllReferences(feature, collection);
    }

    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public int appendReference(SingleFeatureBean feature, Id reference) {
        return next.appendReference(feature, reference);
    }

    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public int appendAllReferences(SingleFeatureBean feature, List<Id> collection) {
        return next.appendAllReferences(feature, collection);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> removeReference(ManyFeatureBean feature) {
        return next.removeReference(feature);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void removeAllReferences(SingleFeatureBean feature) {
        next.removeAllReferences(feature);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Integer> sizeOfReference(SingleFeatureBean feature) {
        return next.sizeOfReference(feature);
    }
}
