/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal.collect;

import org.eclipse.emf.ecore.util.InternalEList;

import java.util.function.IntSupplier;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A read-only {@link LazyListIterator} that does not resolve objects.
 *
 * @param <L> the type of the containing list
 * @param <E> the type of elements returned by this list iterator
 */
@ParametersAreNonnullByDefault
public class NonResolvingLazyListIterator<L extends InternalEList<E>, E> extends LazyListIterator<L, E> {

    /**
     * Constructs a new {@code NonResolvingLazyListIterator}.
     *
     * @param containingList the containing list
     * @param modCount       the function to retrieve the modification count of the containing list
     */
    public NonResolvingLazyListIterator(L containingList, IntSupplier modCount) {
        super(containingList, modCount);
    }

    /**
     * Constructs a new {@code NonResolvingLazyListIterator}.
     *
     * @param containingList the containing list
     * @param modCount       the function to retrieve the modification count of the containing list
     * @param index          the starting index
     */
    public NonResolvingLazyListIterator(L containingList, IntSupplier modCount, int index) {
        super(containingList, modCount, index);
    }

    @Nonnull
    @Override
    protected E doGet(int index) {
        return containingList.basicGet(index);
    }

    @Override
    protected void checkNotReadOnly() {
        throw new UnsupportedOperationException(Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
