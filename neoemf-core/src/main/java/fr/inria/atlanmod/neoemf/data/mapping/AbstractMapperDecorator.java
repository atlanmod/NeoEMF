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

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.commons.log.Log;
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

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link DataMapper} wrapper that delegates method calls to an internal {@link DataMapper}.
 *
 * @param <M> the type of the inner {@link DataMapper}
 */
@ParametersAreNonnullByDefault
public class AbstractMapperDecorator<M extends DataMapper> implements DataMapper {

    /**
     * The inner mapper.
     */
    private M next;

    /**
     * Constructs a new {@code AbstractStore} on the given {@code mapper}.
     *
     * @param mapper the inner mapper
     */
    protected AbstractMapperDecorator(M mapper) {
        this.next = checkNotNull(mapper);

        Log.debug("{0} created", getClass().getSimpleName());
    }

    /**
     * Returns the inner mapper.
     *
     * @return the inner mapper
     */
    @Nonnull
    protected final M next() {
        return next;
    }

    /**
     * Defines the inner mapper.
     *
     * @param mapper the inner mapper
     */
    protected final void next(M mapper) {
        this.next = mapper;
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
    public void removeContainer(Id id) {
        next.removeContainer(id);
    }

    @Override
    public boolean hasContainer(Id id) {
        return next.hasContainer(id);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<ClassBean> metaClassOf(Id id) {
        return next.metaClassOf(id);
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        return next.metaClassFor(id, metaClass);
    }

    @Override
    public boolean hasMetaclass(Id id) {
        return next.hasMetaclass(id);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Iterable<Id> allInstancesOf(ClassBean metaClass, boolean strict) {
        return next.allInstancesOf(metaClass, strict);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Iterable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        return next.allInstancesOf(metaClasses);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        return next.valueOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        return next.valueFor(key, value);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> void removeValue(SingleFeatureBean key) {
        next.removeValue(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> referenceOf(SingleFeatureBean key) {
        return next.referenceOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        return next.referenceFor(key, reference);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void removeReference(SingleFeatureBean key) {
        next.removeReference(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        return next.valueOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Stream<V> allValuesOf(SingleFeatureBean key) {
        return next.allValuesOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        return next.valueFor(key, value);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> void addValue(ManyFeatureBean key, V value) {
        next.addValue(key, value);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        next.addAllValues(key, collection);
    }

    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> int appendValue(SingleFeatureBean key, V value) {
        return next.appendValue(key, value);
    }

    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> int appendAllValues(SingleFeatureBean key, List<? extends V> collection) {
        return next.appendAllValues(key, collection);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        return next.removeValue(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> void removeAllValues(SingleFeatureBean key) {
        next.removeAllValues(key);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        return next.sizeOfValue(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        return next.referenceOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Stream<Id> allReferencesOf(SingleFeatureBean key) {
        return next.allReferencesOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        return next.referenceFor(key, reference);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void addReference(ManyFeatureBean key, Id reference) {
        next.addReference(key, reference);
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        next.addAllReferences(key, collection);
    }

    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public int appendReference(SingleFeatureBean key, Id reference) {
        return next.appendReference(key, reference);
    }

    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        return next.appendAllReferences(key, collection);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> removeReference(ManyFeatureBean key) {
        return next.removeReference(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void removeAllReferences(SingleFeatureBean key) {
        next.removeAllReferences(key);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        return next.sizeOfReference(key);
    }
}
