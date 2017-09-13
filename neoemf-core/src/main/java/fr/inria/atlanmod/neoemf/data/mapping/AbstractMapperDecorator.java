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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
    public void unsetContainer(Id id) {
        next.unsetContainer(id);
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
    public <V> void unsetValue(SingleFeatureBean key) {
        next.unsetValue(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> boolean hasValue(SingleFeatureBean key) {
        return next.hasValue(key);
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
    public void unsetReference(SingleFeatureBean key) {
        next.unsetReference(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public boolean hasReference(SingleFeatureBean key) {
        return next.hasReference(key);
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
    public <V> List<V> allValuesOf(SingleFeatureBean key) {
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
    public <V> boolean hasAnyValue(SingleFeatureBean key) {
        return next.hasAnyValue(key);
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
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> moveValue(ManyFeatureBean source, ManyFeatureBean target) {
        return next.moveValue(source, target);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> boolean containsValue(SingleFeatureBean key, @Nullable V value) {
        return next.containsValue(key, value);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<Integer> indexOfValue(SingleFeatureBean key, @Nullable V value) {
        return next.indexOfValue(key, value);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<Integer> lastIndexOfValue(SingleFeatureBean key, @Nullable V value) {
        return next.lastIndexOfValue(key, value);
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
    public List<Id> allReferencesOf(SingleFeatureBean key) {
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
    public boolean hasAnyReference(SingleFeatureBean key) {
        return next.hasAnyReference(key);
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
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> moveReference(ManyFeatureBean source, ManyFeatureBean target) {
        return next.moveReference(source, target);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public boolean containsReference(SingleFeatureBean key, @Nullable Id reference) {
        return next.containsReference(key, reference);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Integer> indexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        return next.indexOfReference(key, reference);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Integer> lastIndexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        return next.lastIndexOfReference(key, reference);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        return next.sizeOfReference(key);
    }
}
