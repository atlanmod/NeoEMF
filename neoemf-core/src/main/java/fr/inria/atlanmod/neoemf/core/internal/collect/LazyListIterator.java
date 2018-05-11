/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal.collect;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.IntSupplier;

import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;
import static fr.inria.atlanmod.commons.Preconditions.checkState;

/**
 * A lazy {@link ListIterator} that delegates its operations (structural and verification) to the containing list.
 *
 * @param <L> the type of the containing list
 * @param <E> the type of elements returned by this list iterator
 */
@ParametersAreNonnullByDefault
public class LazyListIterator<L extends List<E>, E> extends LazyIterator<L, E> implements ListIterator<E> {

    /**
     * Constructs a new {@code LazyListIterator}.
     *
     * @param containingList the containing list
     * @param modCount       the function to retrieve the modification count of the containing list
     */
    public LazyListIterator(L containingList, IntSupplier modCount) {
        this(containingList, modCount, 0);
    }

    /**
     * Constructs a new {@code LazyListIterator}.
     *
     * @param containingList the containing list
     * @param modCount       the function to retrieve the modification count of the containing list
     * @param index          the starting index
     */
    public LazyListIterator(L containingList, IntSupplier modCount, int index) {
        super(containingList, modCount);
        checkPositionIndex(index, index == 0 ? 0 : size.getAsInt());
        this.cursor = index;
    }

    @Override
    public final boolean hasPrevious() {
        return cursor > 0;
    }

    @Override
    public final E previous() {
        try {
            E previous = doGet(--cursor);
            checkModCount();
            lastCursor = cursor;
            return previous;
        }
        catch (IndexOutOfBoundsException exception) {
            checkModCount();
            throw new NoSuchElementException();
        }
    }

    @Override
    public int nextIndex() {
        return cursor;
    }

    @Override
    public int previousIndex() {
        return cursor - 1;
    }

    @Override
    public final void set(E e) {
        checkNotReadOnly();
        checkState(lastCursor != -1);
        checkModCount();

        try {
            doSet(lastCursor, e);
            expectedModCount = modCount.getAsInt();
        }
        catch (IndexOutOfBoundsException exception) {
            throw new ConcurrentModificationException();
        }
    }

    @Override
    public final void add(E e) {
        checkNotReadOnly();
        checkModCount();

        try {
            doAdd(cursor++, e);
            expectedModCount = modCount.getAsInt();
            size.update(s -> s + 1);
            lastCursor = -1;
        }
        catch (IndexOutOfBoundsException exception) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * Replaces the element at the specified {@code index} in the containing list with the specified {@code element}.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     */
    protected void doSet(int index, E element) {
        containingList.set(index, element);
    }

    /**
     * Inserts the specified {@code element} at the specified {@code index} in the containing list.
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    protected void doAdd(int index, E element) {
        containingList.add(index, element);
    }
}
