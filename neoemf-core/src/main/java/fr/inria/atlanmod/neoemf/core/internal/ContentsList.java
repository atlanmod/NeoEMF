/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal;

import fr.inria.atlanmod.commons.LazyReference;
import fr.inria.atlanmod.commons.primitive.Booleans;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.util.EObjects;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.util.AbstractSequentialInternalEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.ParametersAreNullableByDefault;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A read-only sequential {@link List} that delegates its operations to the associated {@link Store}.
 * <p>
 * Instances of this class are created by {@link PersistentEObject#eContents()} and allows to access the content of a
 * {@link PersistentEObject} by lazily loading the elements.
 *
 * @param <E> the type of elements in this list
 */
@ParametersAreNonnullByDefault
public class ContentsList<E extends EObject> extends AbstractSequentialInternalEList<E> {

    /**
     * The instance of an empty {@code ContentsList}.
     */
    @Nonnull
    private static final ContentsList<?> EMPTY = new EmptyContentsList<>();

    /**
     * The owner of this list.
     */
    @Nonnull
    private final PersistentEObject owner;

    /**
     * The containment features that are handled by this list.
     */
    @Nonnull
    private final List<EStructuralFeature> features;

    /**
     * {@code true} if proxied {@link EObject objects} has to be resolved.
     */
    private final boolean resolve;

    /**
     * The cached non-resolving list.
     *
     * @see #basicList()
     */
    @Nonnull
    private final LazyReference<ContentsList<E>> nonResolvingList;

    /**
     * Constructs a new {@code ContentsList}.
     *
     * @param owner    the owner of this list
     * @param features the containment features that are handled by this list
     * @param resolve  {@code true} if proxied {@link EObject objects} has to be resolved
     */
    protected ContentsList(PersistentEObject owner, List<EStructuralFeature> features, boolean resolve) {
        this.owner = owner;
        this.features = features;
        this.resolve = resolve;
        this.nonResolvingList = LazyReference.soft(() -> resolve ? new ContentsList<>(owner, features, false) : this);
    }

    /**
     * Returns an empty {@code ContentsList}.
     *
     * @param <E> the type of elements in this list
     *
     * @return an empty list
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <E extends EObject> ContentsList<E> empty() {
        return (ContentsList<E>) EMPTY;
    }

    /**
     * Creates a new {@code ContentsList} with the given {@code owner}.
     *
     * @param owner the owner of this list
     * @param <E>   the type of elements in this list
     *
     * @return a new list
     */
    @Nonnull
    public static <E extends EObject> ContentsList<E> newList(PersistentEObject owner) {
        List<EStructuralFeature> features = owner.eClass().getEAllStructuralFeatures();
        EStructuralFeature[] containments = EClassImpl.FeatureSubsetSupplier.class.cast(features).containments();

        return nonNull(containments)
                ? new ContentsList<>(owner, Arrays.asList(containments), true)
                : ContentsList.empty();
    }

    /**
     * Creates a list iterator over the elements in this list
     *
     * @return a new list iterator
     */
    @Nonnull
    protected ContentsListIterator<E> newListIterator() {
        return features.isEmpty()
                ? ContentsListIterator.empty()
                : new ContentsListIterator<>(owner, features, resolve);
    }

    /**
     * Returns {@code true} if the {@code feature} must be included in the result, {@code false} if it should be ignored.
     *
     * @param feature the feature to test
     *
     * @return {@code true} if the {@code feature} must be included in the result, {@code false} if it should be ignored
     */
    protected boolean isIncluded(EStructuralFeature feature) {
        return true;
    }

    /**
     * Returns {@code true} if the feature of the {@code entry} must be included in the result, {@code false} if it
     * should be ignored.
     *
     * @param entry the entry to test
     *
     * @return {@code true} if the feature of the {@code entry} must be included in the result, {@code false} if it
     * should be ignored
     *
     * @see #isIncluded(EStructuralFeature)
     */
    protected boolean isIncludedEntry(FeatureMap.Entry entry) {
        final EStructuralFeature feature = entry.getEStructuralFeature();

        return nonNull(entry.getValue())
                && isIncluded(feature)
                && EObjects.isReference(feature)
                && EObjects.asReference(feature).isContainment();
    }

    @Nonnull
    @Override
    public ListIterator<E> listIterator(int index) {
        if (features.isEmpty() && index != 0) {
            throw new IndexOutOfBoundsException(String.format("index=%d, size=%d", index, 0));
        }

        return newListIterator().fastAdvance(index);
    }

    @Nonnegative
    @Override
    public int size() {
        return features.stream()
                .filter(this::isIncluded)
                .mapToInt(this::size)
                .sum();
    }

    @Override
    public boolean isEmpty() {
        return features.stream()
                .filter(this::isIncluded)
                .allMatch(this::isEmpty);
    }

    /**
     * Returns the number of elements of the {@code feature}.
     *
     * @param feature the feature
     *
     * @return the number of elements of the {@code feature}
     */
    @Nonnegative
    private int size(EStructuralFeature feature) {
        final Object value = owner.eGet(feature, false);

        if (FeatureMapUtil.isFeatureMap(feature)) {
            return FeatureMap.class.cast(value).stream()
                    .filter(this::isIncludedEntry)
                    .mapToInt(e -> 1)
                    .sum();
        }

        if (feature.isMany()) {
            return Collection.class.cast(value).size();
        }

        return Booleans.toInt(nonNull(value));
    }

    /**
     * Returns {@code true} if the {@code feature} has no element.
     *
     * @param feature the feature
     *
     * @return {@code true} if the {@code feature} has no element
     */
    private boolean isEmpty(EStructuralFeature feature) {
        final Object value = owner.eGet(feature, false);

        if (FeatureMapUtil.isFeatureMap(feature)) {
            return FeatureMap.class.cast(value).stream()
                    .noneMatch(this::isIncludedEntry);
        }

        if (feature.isMany()) {
            return Collection.class.cast(value).isEmpty();
        }

        return isNull(value);
    }

    @Nonnull
    @Override
    public List<E> basicList() {
        return nonResolvingList.get();
    }

    @Override
    public void move(int newPosition, E e) {
        throw new UnsupportedOperationException("move");
    }

    @Override
    public E move(int newPosition, int oldPosition) {
        throw new UnsupportedOperationException("move");
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (isNull(o) || getClass() != o.getClass()) {
            return false;
        }

        ContentsList<?> that = ContentsList.class.cast(o);
        return Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner);
    }

    /**
     * An empty {@code ContentsList}.
     *
     * @param <E> the type of elements in this list
     */
    @ParametersAreNullableByDefault
    private static class EmptyContentsList<E extends EObject> extends ContentsList<E> {

        /**
         * Constructs a new {@code Empty}.
         */
        @SuppressWarnings("ConstantConditions") // null values
        public EmptyContentsList() {
            super(null, Collections.emptyList(), false);
        }

        @Nonnull
        @Override
        public List<E> basicList() {
            return this;
        }
    }
}
