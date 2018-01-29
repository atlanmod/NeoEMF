/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.util.EObjects;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkState;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link ListIterator} that iterates over all the content of a {@link PersistentEObject}.
 *
 * @param <E> the type of elements in this iterator
 *
 * @see PersistentEObject#eContents()
 * @see PersistentEObject#eAllContents()
 */
@ParametersAreNonnullByDefault
class ContentsListIterator<E extends EObject> implements EContentsEList.FeatureListIterator<E>, EContentsEList.Filterable {

    /**
     * The owner of this iterator.
     */
    @Nonnull
    protected final PersistentEObject owner;

    /**
     * The containment features that are handled by this iterator.
     */
    @Nonnull
    protected final ListIterator<EStructuralFeature> features;

    /**
     * The current position of this iterator.
     *
     * @see #nextIndex()
     * @see #previousIndex()
     */
    @Nonnegative
    protected int cursor;

    /**
     * The prepared result that will be returned at the next call to {@link #next()} or {@link #previous()}.
     *
     * @see #prepare(IterationHelper)
     */
    @Nonnull
    protected FutureResult<E> nextResult = FutureResult.undefined();

    /**
     * The current feature; initialized at the first call to {@link #next()} or {@link #previous()}. It corresponds to
     * the {@link FutureResult#feature() feature} of the last returned result.
     *
     * @see #feature()
     */
    protected EStructuralFeature currentFeature;

    /**
     * {@code true} if the prepared result is a feature map.
     */
    protected boolean isFeatureMap;

    /**
     * The list iterator on values; only with multi-valued features and feature maps.
     */
    protected ListIterator<E> values;

    /**
     * The feature filter to determine if a feature should be ignored from the result.
     */
    @Nullable
    protected EContentsEList.FeatureFilter filter;

    /**
     * Constructs a new {@code ContentsListIterator} for the given {@code owner}.
     *
     * @param owner    the owner of this iterator
     * @param features the containment features that are handled by this iterator
     */
    public ContentsListIterator(PersistentEObject owner, EStructuralFeature[] features) {
        this.owner = owner;
        this.features = Arrays.asList(features).listIterator();
    }

    /**
     * Returns {@code true} if proxied {@link EObject objects} has to be resolved.
     *
     * @return {@code true} if proxied objects has to be resolved
     */
    protected boolean resolve() {
        return false;
    }

    /**
     * Returns {@code true} if the {@code feature} must be included in the result, {@code false} if it should be ignored.
     *
     * @param feature the feature to test
     *
     * @return {@code true} if the {@code feature} must be included in the result, {@code false} if it should be ignored
     *
     * @see EContentsEList.FeatureFilter#isIncluded(EStructuralFeature)
     */
    protected boolean isIncluded(EStructuralFeature feature) {
        return isNull(filter) || filter.isIncluded(feature);
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
        final EStructuralFeature f = entry.getEStructuralFeature();

        return nonNull(entry.getValue())
                && isIncluded(f)
                && EObjects.isReference(f)
                && EObjects.asReference(f).isContainment();
    }

    @Override
    public void filter(EContentsEList.FeatureFilter newFilter) {
        checkState(cursor == 0 && isNull(filter), "Iterator already in use or already filtered");
        filter = newFilter;
    }

    @Override
    public EStructuralFeature feature() {
        return currentFeature;
    }

    @Override
    public boolean hasNext() {
        return hasMoreElements(IterationHelpers.ASCENDING);
    }

    @Nonnull
    @Override
    public E next() {
        return advance(IterationHelpers.ASCENDING);
    }

    @Override
    public boolean hasPrevious() {
        return hasMoreElements(IterationHelpers.DESCENDING);
    }

    @Nonnull
    @Override
    public E previous() {
        return advance(IterationHelpers.DESCENDING);
    }

    @Override
    public int nextIndex() {
        return IterationHelpers.ASCENDING.adaptCursor(cursor);
    }

    @Override
    public int previousIndex() {
        return IterationHelpers.DESCENDING.adaptCursor(cursor);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }

    @Override
    public void set(E e) {
        throw new UnsupportedOperationException("set");
    }

    @Override
    public void add(E e) {
        throw new UnsupportedOperationException("add");
    }

    /**
     * Returns {@code true} if this list iterator has more elements when traversing the list in the defined direction.
     *
     * @param helper the iteration helper that defines the direction to use
     *
     * @return {@code true} if the list iterator has more elements
     *
     * @see #hasNext()
     * @see #hasPrevious()
     * @see IterationHelper#direction()
     * @see IterationHelper#hasMoreElements(ListIterator)
     */
    private boolean hasMoreElements(IterationHelper helper) {
        // If the iterator is already prepared, but on the wrong side: undo the last preparation
        if (nextResult.isInitialized() && !nextResult.hasDirection(helper.direction())) {
            if (nonNull(values)) {
                helper.advance(values);
            }
            nextResult = FutureResult.undefined();
        }

        if (!nextResult.isInitialized()) {
            nextResult = prepare(helper);
        }

        return !nextResult.isEmpty();
    }

    /**
     * Returns the next element in the list and moves the cursor, according to the defined direction.
     *
     * @param helper the iteration helper that defines the direction to use
     *
     * @return the next element in the list, according to the defined direction
     *
     * @see #next()
     * @see #previous()
     * @see IterationHelper#direction()
     * @see IterationHelper#advance(ListIterator)
     */
    @Nonnull
    private E advance(IterationHelper helper) {
        if ((!nextResult.hasDirection(helper.direction()) || nextResult.isEmpty()) && !hasMoreElements(helper)) {
            throw new NoSuchElementException();
        }

        // Retrieve the value
        E e = nextResult.value();

        // Store status information
        currentFeature = nextResult.feature();
        cursor = helper.advanceCursor(cursor);

        // Reset and prepare the next/previous element
        nextResult = FutureResult.undefined();
        nextResult = prepare(helper);

        return e;
    }

    /**
     * Prepares the result that will be returned at the next call to {@link #advance(IterationHelper)}.
     * This method iterates over all features to retrieve the next value for the defined direction.
     * If the previously returned feature was multi-valued, then this method iterates over all remaining values before
     * analizing other features.
     *
     * @param helper the iteration helper that defines the direction to use
     *
     * @return the prepared result
     */
    @Nonnull
    private FutureResult<E> prepare(IterationHelper helper) {
        // The current feature is multi-valued, or a feature map, and has unprocessed values
        if (nonNull(values) && hasRemainingValues(helper)) {
            return getValue(helper, currentFeature);
        }

        // Processes other features
        while (helper.hasMoreElements(features)) {
            FutureResult<E> localResult = prepareFeature(helper, helper.advance(features));
            if (!localResult.isEmpty()) {
                return localResult;
            }
        }

        // No more value
        resetValues();
        isFeatureMap = false;
        return FutureResult.empty(helper.direction());
    }

    /**
     * Prepares the result that will be returned at the next call to {@link #advance(IterationHelper)} for the specified
     * {@code feature} in the defined direction.
     *
     * @param helper  the iteration helper that defines the direction to use
     * @param feature the feature to retrieve the next value
     *
     * @return the prepared result
     */
    @Nonnull
    private FutureResult<E> prepareFeature(IterationHelper helper, EStructuralFeature feature) {
        final IterationDirection direction = helper.direction();

        if (!isIncluded(feature)) {
            // Feature is ignored
            return FutureResult.empty(direction);
        }

        final Object value = owner.eGet(feature, resolve());
        isFeatureMap = FeatureMapUtil.isFeatureMap(feature);

        // The feature is multi-valued, or a feature map
        if (isFeatureMap || feature.isMany()) {
            setValues(helper, value);

            if (hasRemainingValues(helper)) {
                return getValue(helper, feature);
            }
        }
        // The feature is single-valued
        else if (nonNull(value)) {
            resetValues();
            return new FutureResult<>(direction, value, feature);
        }

        // The feature has no value
        return FutureResult.empty(direction);
    }

    // region Value management of multi-valued features

    /**
     * Defines the iterator on values for the last prepared multi-valued feature.
     *
     * @param helper the iteration helper that defines the direction to use
     * @param value  the list of values
     */
    @SuppressWarnings("unchecked")
    private void setValues(IterationHelper helper, Object value) {
        List<E> valuesList = List.class.cast(value);
        final int firstIndex = helper.getFirstIndex(valuesList);

        if (resolve()) {
            values = valuesList.listIterator(firstIndex);
        }
        else {
            values = InternalEList.class.cast(valuesList).basicListIterator(firstIndex);
        }
    }

    /**
     * Resets the iterator on values for the last prepared multi-valued feature.
     */
    private void resetValues() {
        values = null;
    }

    /**
     * Returns {@code true} if the iterator on values for the last prepared multi-valued feature has more elements when
     * traversing the list in the defined direction.
     *
     * @param helper the iteration helper that defines the direction to use
     *
     * @return {@code true} if the iterator on values for the last prepared multi-valued feature has more elements
     */
    private boolean hasRemainingValues(IterationHelper helper) {
        checkState(nonNull(values), "The current feature is neither multi-valued or a feature map");

        // Not a feature map
        if (!isFeatureMap) {
            return helper.hasMoreElements(values);
        }

        // Filter the entries of the feature map
        while (helper.hasMoreElements(values)) {
            E value = helper.advance(values);
            FeatureMap.Entry entry = FeatureMap.Entry.class.cast(value);
            if (isIncludedEntry(entry)) {
                helper.reverse(values);
                return true;
            }
        }

        return false;
    }

    /**
     * Advances the iterator on values for the current multi-valued feature, and returns the value associated to the
     * specified {@code feature}.
     *
     * @param helper  the iteration helper that defines the direction to use
     * @param feature the multi-valued feature associated with the value
     *
     * @return the result of the iteration
     */
    @Nonnull
    private FutureResult<E> getValue(IterationHelper helper, EStructuralFeature feature) {
        checkState(nonNull(values), "The current feature is neither multi-valued or a feature map");

        final Object value = helper.advance(values);
        final IterationDirection direction = helper.direction();

        return isFeatureMap
                ? new FutureResult<>(direction, FeatureMap.Entry.class.cast(value))
                : new FutureResult<>(direction, value, feature);
    }

    // endregion

    /**
     * A prepared result.
     *
     * @param <E> the type of the value
     */
    @ParametersAreNonnullByDefault
    private static class FutureResult<E> {

        /**
         * An undefined result.
         */
        @Nonnull
        private static final FutureResult<?> UNDEFINED = new Empty<>();

        /**
         * The result value.
         */
        private final E value;

        /**
         * The feature associated to the {@link #value}.
         */
        private final EStructuralFeature feature;

        /**
         * The iteration direction used to prepared this result.
         */
        @Nonnull
        private final IterationDirection direction;

        /**
         * Constructs a new {@code FutureResult} with a value and a feature.
         *
         * @param direction the iteration direction used to prepared this result
         * @param value     the result value
         * @param feature   the feature associated to the {@code value}
         */
        @SuppressWarnings("unchecked")
        public FutureResult(IterationDirection direction, Object value, EStructuralFeature feature) {
            checkNotNull(direction, "direction");

            this.direction = direction;
            this.value = (E) value;
            this.feature = feature;
        }

        /**
         * Constructs a new {@code FutureResult} from an {@code entry}.
         *
         * @param direction the iteration direction used to prepared this result
         * @param entry     the entry from which to retrieve the result and its associated feature
         */
        public FutureResult(IterationDirection direction, FeatureMap.Entry entry) {
            this(direction, entry.getValue(), entry.getEStructuralFeature());
        }

        /**
         * Returns an undefined {@code FutureResult}, i.e. not initialized.
         *
         * @return an undefined result
         *
         * @see #isInitialized()
         */
        @Nonnull
        @SuppressWarnings("unchecked")
        public static <E> FutureResult<E> undefined() {
            return (FutureResult<E>) UNDEFINED;
        }

        /**
         * Returns an empty {@code FutureResult}.
         *
         * @param direction the iteration direction used to prepared this result
         *
         * @return an empty result
         */
        @Nonnull
        public static <E> FutureResult<E> empty(IterationDirection direction) {
            return new Empty<>(direction);
        }

        /**
         * Returns the result value.
         *
         * @return the result value
         */
        @Nonnull
        public E value() {
            return value;
        }

        /**
         * Returns the feature associated to the result.
         *
         * @return the feature associated to the result
         */
        @Nonnull
        public EStructuralFeature feature() {
            return feature;
        }

        /**
         * Returns {@code true} if the iteration direction used to prepared this result is {@code expectedDirection}.
         *
         * @param expectedDirection the expected iteration direction
         *
         * @return {@code true} if the iteration direction used to prepared this result is {@code expectedDirection}
         */
        public boolean hasDirection(IterationDirection expectedDirection) {
            return direction == expectedDirection;
        }

        /**
         * Returns {@code true} if this result has no value.
         *
         * @return {@code true} if this result has no value
         */
        public boolean isEmpty() {
            return false;
        }

        /**
         * Returns {@code true} if this result has been initialized.
         *
         * @return {@code true} if this result has been initialized
         */
        public boolean isInitialized() {
            return direction != IterationDirection.UNDEFINED;
        }

        /**
         * A {@link FutureResult} with no value.
         *
         * @param <E> the type of the value
         */
        @ParametersAreNonnullByDefault
        private static final class Empty<E> extends FutureResult<E> {

            /**
             * Constructs a new {@code Empty}.
             */
            public Empty() {
                this(IterationDirection.UNDEFINED);
            }

            /**
             * Constructs a new {@code Empty}.
             *
             * @param direction the iteration direction used to prepared this result
             */
            @SuppressWarnings("ConstantConditions") // null values in constructors
            public Empty(IterationDirection direction) {
                super(direction, null, null);
            }

            @Nonnull
            @Override
            public E value() {
                throw new NoSuchElementException();
            }

            @Nonnull
            @Override
            public EStructuralFeature feature() {
                throw new NoSuchElementException();
            }

            @Override
            public boolean isEmpty() {
                return true;
            }
        }
    }
}
