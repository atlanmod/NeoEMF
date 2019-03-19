/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.ui.command;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.viewers.IStructuredSelection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * An {@link AbstractSelectionHandler} which ensures that selected objects are instance of a specified class and match a
 * predicate.
 * <p>
 * All selected objects are filtered, and only matching objects are processed.
 *
 * @param <T> the expected type of the selected object
 */
abstract class AbstractSelectionManyHandler<T> extends AbstractSelectionHandler {

    private final Class<? extends T> expectedType;

    private final Predicate<T> predicate;

    public AbstractSelectionManyHandler(Class<? extends T> expectedType) {
        this(expectedType, o -> true);
    }

    public AbstractSelectionManyHandler(Class<? extends T> expectedType, Predicate<T> predicate) {
        this.expectedType = expectedType;
        this.predicate = predicate;
    }

    @Override
    protected void execute(ExecutionEvent event, IStructuredSelection selection) {
        final List<T> selectedItems = new ArrayList<>(selection.size());

        for (Iterator<?> iterator = selection.iterator(); iterator.hasNext(); ) {
            final Object selectedObject = iterator.next();
            if (expectedType.isInstance(selectedObject)) {
                final T selectedItem = expectedType.cast(selectedObject);
                if (predicate.test(selectedItem)) {
                    selectedItems.add(selectedItem);
                }
            }
        }

        if (!selectedItems.isEmpty()) {
            execute(event, selectedItems);
        }
    }

    protected abstract void execute(ExecutionEvent event, Iterable<? extends T> selectedItems);
}
