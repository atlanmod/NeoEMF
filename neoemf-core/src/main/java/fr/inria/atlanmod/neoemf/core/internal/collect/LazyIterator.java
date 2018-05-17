/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal.collect;

import fr.inria.atlanmod.commons.LazyInt;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.IntSupplier;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkState;

/**
 * A lazy {@link Iterator} that delegates its operations (structural and verification) to the containing list.
 *
 * @param <L> the type of the containing list
 * @param <E> the type of elements returned by this iterator
 */
@ParametersAreNonnullByDefault
public class LazyIterator<L extends List<E>, E> implements Iterator<E> {

    /**
     * The containing list
     */
    @Nonnull
    protected final L containingList;

    /**
     * The function to retrieve the modification count of the containing list.
     */
    @Nonnull
    protected final IntSupplier modCount;

    /**
     * The cached size of the containing list.
     */
    @Nonnull
    protected final LazyInt size;

    /**
     * The current position of the iterator.
     */
    @Nonnegative
    protected int cursor;

    /**
     * The previous position of the iterator.
     */
    protected int lastCursor = -1;

    /**
     * The modification count of the containing list.
     */
    protected int expectedModCount;

    /**
     * Constructs a new {@code LazyIterator}.
     *
     * @param containingList the containing list
     * @param modCount       the function to retrieve the modification count of the containing list
     */
    public LazyIterator(L containingList, IntSupplier modCount) {
        this.modCount = modCount;
        this.containingList = containingList;
        this.expectedModCount = modCount.getAsInt();
        this.size = LazyInt.with(containingList::size);
    }

    @Override
    public final boolean hasNext() {
        return cursor < size.getAsInt();
    }

    @Override
    public final E next() {
        try {
            E next = doGet(cursor);
            checkModCount();
            lastCursor = cursor++;
            return next;
        }
        catch (IndexOutOfBoundsException exception) {
            checkModCount();
            throw new NoSuchElementException();
        }
    }

    @Override
    public final void remove() {
        checkNotReadOnly();
        checkState(lastCursor != -1);
        checkModCount();

        try {
            doRemove(lastCursor);
            expectedModCount = modCount.getAsInt();
            size.update(s -> s - 1);
            if (lastCursor < cursor) {
                --cursor;
            }
            lastCursor = -1;
        }
        catch (IndexOutOfBoundsException e) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * Returns the element at the specified {@code index} in the containing list.
     *
     * @param index index of the element to return
     *
     * @return the element at the specified position in the containing list
     */
    @Nonnull
    protected E doGet(int index) {
        return containingList.get(index);
    }

    /**
     * Removes the element at the specified {@code index} in the containing list.
     *
     * @param index the index of the element to be removed
     */
    protected void doRemove(int index) {
        containingList.remove(index);
    }

    /**
     * Ensures that the modification count is as expected.
     *
     * @throws ConcurrentModificationException if the modification count is not as expected.
     */
    protected void checkModCount() {
        if (modCount.getAsInt() != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * Ensures that this iterator is not read-only before executing a write operation.
     *
     * @throws UnsupportedOperationException if this iterator is read-only
     */
    protected void checkNotReadOnly() {
        // Do nothing
    }
}
