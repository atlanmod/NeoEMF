/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal.collect;

import org.eclipse.emf.common.util.TreeIterator;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link org.eclipse.emf.common.util.TreeIterator} that iterates recursively over all the contents of an {@link
 * Iterable} and its content.
 *
 * @param <R> the type of the root
 * @param <E> the type of elements contained in the root
 */
@ParametersAreNonnullByDefault
public class AllContentsIterator<R extends Iterable<E>, E extends Iterable<E>> implements TreeIterator<E> {

    /**
     * The deque of children iterators.
     */
    @Nonnull
    private final Deque<Iterator<E>> stack = new ArrayDeque<>();

    /**
     * The current iterator. It corresponds to the cached value of {@code stack#getLast()}.
     */
    @Nonnull
    private Iterator<E> currentIterator = Collections.emptyIterator();

    /**
     * The last added iterator.
     */
    @Nullable
    private Iterator<E> lastAddedIterator;

    /**
     * Constructs a new {@code AllContentsIterator}.
     *
     * @param root the root of this iterator
     */
    public AllContentsIterator(R root) {
        setNext(root.iterator());
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty() && stack.peekLast().hasNext();
    }

    @Override
    public E next() {
        currentIterator = stack.getLast(); // throw a NoSuchElementException if empty
        final E result = currentIterator.next();

        // Prepare the next iterator on the contents of the result
        Iterator<E> currentChildIterator = result.iterator();
        if (currentChildIterator.hasNext()) {
            setNext(currentChildIterator);
        }
        else {
            prepareNext(!currentIterator.hasNext());
        }

        return result;
    }

    @Override
    public void remove() {
        currentIterator.remove();
    }

    @Override
    public void prune() {
        if (nonNull(lastAddedIterator)) {
            prepareNext(!stack.isEmpty() && stack.peekLast() == lastAddedIterator);
        }
    }

    /**
     * Defines the next {@code iterator}.
     *
     * @param iterator the next iterator
     */
    private void setNext(Iterator<E> iterator) {
        lastAddedIterator = iterator;

        stack.offerLast(iterator);
    }

    /**
     * Prepares the next iterator.
     *
     * @param prune {@code true} if the stack must be cleaned until the next valid iterator
     */
    private void prepareNext(boolean prune) {
        lastAddedIterator = null;

        if (prune) {
            do {
                stack.pollLast();
            }
            while (!stack.isEmpty() && !stack.peekLast().hasNext());
        }
    }
}
