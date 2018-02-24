/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.util.EFeatures;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkState;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A read-only {@link ListIterator} that iterates over all the content of a {@link PersistentEObject}.
 *
 * @param <E> the type of elements in this iterator
 *
 * @see ContentsList
 * @see PersistentEObject#eContents()
 * @see PersistentEObject#eAllContents()
 */
@ParametersAreNonnullByDefault
class ContentsListIterator<E extends EObject> implements EContentsEList.FeatureListIterator<E>, EContentsEList.Filterable {

    /**
     * The instance of an empty {@code ContentsListIterator}.
     */
    @Nonnull
    private static final ContentsListIterator<?> EMPTY = new EmptyContentsListIterator<>();

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
     * {@code true} if proxied {@link EObject objects} has to be resolved.
     */
    protected final boolean resolve;

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
     * @see #prepare(IterationDirection)
     */
    @Nonnull
    protected IterationResult<E> nextResult = IterationResult.undefined();

    /**
     * The current feature; initialized at the first call to {@link #next()} or {@link #previous()}. It corresponds to
     * the {@link IterationResult#feature() feature} of the last returned result.
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
    protected ListIterator<?> values;

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
     * @param resolve  {@code true} if proxied {@link EObject objects} has to be resolved
     */
    public ContentsListIterator(PersistentEObject owner, List<EStructuralFeature> features, boolean resolve) {
        this.owner = owner;
        this.features = features.listIterator();
        this.resolve = resolve;
    }

    /**
     * Returns an empty {@code ContentsListIterator}.
     *
     * @param <E> the type of elements in the iterator
     *
     * @return an empty iterator
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <E extends EObject> ContentsListIterator<E> empty() {
        return (ContentsListIterator<E>) EMPTY;
    }

    /**
     * Returns {@code true} if the {@code feature} must be included in the result, {@code false} if it should be
     * ignored.
     *
     * @param feature the feature to test
     *
     * @return {@code true} if the {@code feature} must be included in the result, {@code false} if it should be ignored
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
                && EFeatures.isReference(f)
                && EFeatures.asReference(f).isContainment();
    }

    @Override
    public void filter(EContentsEList.FeatureFilter filter) {
        checkState(cursor == 0 && isNull(this.filter), "Iterator already in use or already filtered");
        this.filter = filter;
    }

    @Override
    public EStructuralFeature feature() {
        return currentFeature;
    }

    @Override
    public boolean hasNext() {
        return hasMoreElements(IterationDirection.FORWARD);
    }

    @Nonnull
    @Override
    public E next() {
        return advance(IterationDirection.FORWARD);
    }

    @Override
    public boolean hasPrevious() {
        return hasMoreElements(IterationDirection.BACKWARD);
    }

    @Nonnull
    @Override
    public E previous() {
        return advance(IterationDirection.BACKWARD);
    }

    @Override
    public int nextIndex() {
        return IterationDirection.FORWARD.adaptCursor(cursor);
    }

    @Override
    public int previousIndex() {
        return IterationDirection.BACKWARD.adaptCursor(cursor);
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
     * @param direction the iteration direction
     *
     * @return {@code true} if the list iterator has more elements
     *
     * @see #hasNext()
     * @see #hasPrevious()
     * @see IterationDirection#hasMoreElements(ListIterator)
     */
    private boolean hasMoreElements(IterationDirection direction) {
        // If the iterator is already prepared, but on the wrong side: undo the last preparation
        if (nextResult.isInitialized() && !nextResult.hasDirection(direction)) {
            if (nonNull(values)) {
                direction.advance(values);
            }
            nextResult = IterationResult.undefined();
        }

        if (!nextResult.isInitialized()) {
            nextResult = prepare(direction);
        }

        return !nextResult.isEmpty();
    }

    /**
     * Returns the next element in the list and moves the cursor, according to the defined direction.
     *
     * @param direction the iteration direction that defines the direction to use
     *
     * @return the next element in the list, according to the defined direction
     *
     * @see #next()
     * @see #previous()
     * @see IterationDirection#advance(ListIterator)
     */
    @Nonnull
    private E advance(IterationDirection direction) {
        if ((!nextResult.hasDirection(direction) || nextResult.isEmpty()) && !hasMoreElements(direction)) {
            throw new NoSuchElementException();
        }

        // Retrieve the value
        E e = nextResult.value();

        // Store status information
        currentFeature = nextResult.feature();
        cursor = direction.advanceCursor(cursor);

        // Reset and prepare the next/previous element
        nextResult = IterationResult.undefined();
        nextResult = prepare(direction);

        return e;
    }

    /**
     * Prepares the result that will be returned at the next call to {@link #advance(IterationDirection)}. This method
     * iterates over all features to retrieve the next value for the defined direction. If the previously returned
     * feature was multi-valued, then this method iterates over all remaining values before analizing other features.
     *
     * @param direction the iteration direction
     *
     * @return the prepared result
     */
    @Nonnull
    private IterationResult<E> prepare(IterationDirection direction) {
        // The current feature is multi-valued, or a feature map, and has unprocessed values
        if (nonNull(values) && hasRemainingValues(direction)) {
            return getValue(direction, currentFeature);
        }

        // Processes other features
        while (direction.hasMoreElements(features)) {
            IterationResult<E> localResult = prepareFeature(direction, direction.advance(features));
            if (!localResult.isEmpty()) {
                return localResult;
            }
        }

        // No more value
        resetValues();
        isFeatureMap = false;
        return IterationResult.empty(direction);
    }

    /**
     * Prepares the result that will be returned at the next call to {@link #advance(IterationDirection)} for the
     * specified {@code feature} in the defined direction.
     *
     * @param direction the iteration direction
     * @param feature   the feature to retrieve the next value
     *
     * @return the prepared result
     */
    @Nonnull
    private IterationResult<E> prepareFeature(IterationDirection direction, EStructuralFeature feature) {
        if (!isIncluded(feature)) {
            // Feature is ignored
            return IterationResult.empty(direction);
        }

        final Object value = owner.eGet(feature, resolve);
        isFeatureMap = FeatureMapUtil.isFeatureMap(feature);

        // Feature is multi-valued, or a feature map
        if (isFeatureMap || feature.isMany()) {
            List<?> newValues = List.class.cast(value);
            setValues(newValues, direction.getFirstIndex(newValues));

            if (hasRemainingValues(direction)) {
                return getValue(direction, feature);
            }
        }
        // Feature is single-valued
        else if (nonNull(value)) {
            resetValues();
            return new IterationResult<>(direction, value, feature);
        }

        // Feature has no value
        return IterationResult.empty(direction);
    }

    // region Value management of multi-valued features

    /**
     * Defines the iterator on values for the last prepared multi-valued feature.
     *
     * @param newValues  the list of values
     * @param firstIndex the position in the created list iterator
     */
    private void setValues(List<?> newValues, int firstIndex) {
        if (resolve) {
            values = newValues.listIterator(firstIndex);
        }
        else {
            values = InternalEList.class.cast(newValues).basicListIterator(firstIndex);
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
     * @param direction the iteration direction
     *
     * @return {@code true} if the iterator on values for the last prepared multi-valued feature has more elements
     */
    private boolean hasRemainingValues(IterationDirection direction) {
        checkState(nonNull(values), "The current feature is neither multi-valued or a feature map");

        // Not a feature map
        if (!isFeatureMap) {
            return direction.hasMoreElements(values);
        }

        // Filter the entries of the feature map
        while (direction.hasMoreElements(values)) {
            FeatureMap.Entry entry = FeatureMap.Entry.class.cast(direction.advance(values));
            if (isIncludedEntry(entry)) {
                direction.reverse(values);
                return true;
            }
        }

        return false;
    }

    /**
     * Advances the iterator on values for the current multi-valued feature, and returns the value associated to the
     * specified {@code feature}.
     *
     * @param direction the iteration direction
     * @param feature   the multi-valued feature associated with the value
     *
     * @return the result of the iteration
     */
    @Nonnull
    private IterationResult<E> getValue(IterationDirection direction, EStructuralFeature feature) {
        checkState(nonNull(values), "The current feature is neither multi-valued or a feature map");

        final Object value = direction.advance(values);

        return isFeatureMap
                ? new IterationResult<>(direction, FeatureMap.Entry.class.cast(value))
                : new IterationResult<>(direction, value, feature);
    }

    // endregion

    // region Fast preparation

    /**
     * Advances this iterator at the specified {@code index}.
     *
     * @param index the desired position of this iterator
     *
     * @return this iterator
     *
     * @throws IllegalStateException  if the iterator is already in use
     * @throws NoSuchElementException if {@code index > size}
     * @see List#listIterator(int)
     */
    @Nonnull
    public ContentsListIterator<E> fastAdvance(int index) {
        checkState(cursor == 0, "Iterator already in use");

        while (cursor < index && features.hasNext()) {
            EStructuralFeature feature = features.next();
            currentFeature = feature;
            cursor += fastPrepare(feature, index);
        }

        if (index != cursor) {
            throw new NoSuchElementException();
        }

        return this;
    }

    /**
     * Analyzes the {@code feature} in order to prepare the value at the {@code expectedIndex}. The returned value can
     * be either the size of the feature or the index of the element if the {@code expectedIndex} has been reached.
     *
     * @param feature       the feature
     * @param expectedIndex the index to reach
     *
     * @return the number of steps taken during this preparation
     */
    @Nonnegative
    // TODO Checks the behavior with feature maps
    private int fastPrepare(EStructuralFeature feature, int expectedIndex) {
        if (!isIncluded(feature)) {
            // Feature is ignored
            return 0;
        }

        final Object value = owner.eGet(feature, resolve);

        int steps = 0;

        // Feature is a feature map
        if (FeatureMapUtil.isFeatureMap(feature)) {
            List<FeatureMap.Entry> newValues = FeatureMap.class.cast(value);
            SortedMap<Integer, Integer> indexMapping = fastFilterEntries(newValues);
            steps = indexMapping.lastKey(); // Size of included entries only

            if (cursor + steps >= expectedIndex) {
                steps = expectedIndex - cursor; // Index among included entries only
                setValues(newValues, indexMapping.get(steps));
                isFeatureMap = true;
            }
        }
        // Feature is multi-valued
        else if (feature.isMany()) {
            List<?> newValues = List.class.cast(value);
            steps = newValues.size();

            if (cursor + steps >= expectedIndex) {
                steps = expectedIndex - cursor;
                setValues(newValues, steps);
            }
        }
        // Feature is single-valued
        else if (nonNull(value)) {
            steps = 1;
        }

        return steps;
    }

    /**
     * Filters {@link FeatureMap.Entry}es from the list. The returned map contains the index of included entries
     * (sequential and ordered), associated with their real index in the list.
     *
     * @param entries the list of entries
     *
     * @return the index mapping
     *
     * @see #isIncludedEntry(FeatureMap.Entry)
     */
    @Nonnull
    private SortedMap<Integer, Integer> fastFilterEntries(List<FeatureMap.Entry> entries) {
        int index = 0; // Index among all entries
        int indexIncluded = 0; // Index among included entries only

        SortedMap<Integer, Integer> mapping = new TreeMap<>(Comparator.comparingInt(i -> i));
        for (FeatureMap.Entry e : entries) {
            if (isIncludedEntry(e)) {
                mapping.put(indexIncluded++, index);
            }
            index++;
        }
        return mapping;
    }

    // endregion

    /**
     * An empty {@link ContentsListIterator}.
     *
     * @param <E> the type of elements in this iterator
     */
    @ParametersAreNonnullByDefault
    private static final class EmptyContentsListIterator<E extends EObject> extends ContentsListIterator<E> {

        /**
         * Constructs a new {@code EmptyContentsListIterator}.
         */
        @SuppressWarnings("ConstantConditions") // null owner
        public EmptyContentsListIterator() {
            super(null, Collections.emptyList(), false);
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Nonnull
        @Override
        public E next() {
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Nonnull
        @Override
        public E previous() {
            throw new NoSuchElementException();
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return -1;
        }
    }
}
