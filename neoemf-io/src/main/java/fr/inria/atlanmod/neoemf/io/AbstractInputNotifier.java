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

package fr.inria.atlanmod.neoemf.io;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * The abstract implementation of {@link InputNotifier} that provides overall behavior for the management of handlers.
 *
 * @param <T> the type of the notified {@link InputHandler}
 */
@ParametersAreNonnullByDefault
public abstract class AbstractInputNotifier<T extends InputHandler> implements InputNotifier<T> {

    /**
     * The set of registered handlers.
     */
    private final Set<T> handlers;

    /**
     * Constructs a new {@code AbstractInputNotifier}.
     */
    public AbstractInputNotifier() {
        this.handlers = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     * <p>
     * If the {@code handler} is {@code null}, it will be ignored.
     */
    @Override
    public void addHandler(T handler) {
        if (nonNull(handler)) {
            handlers.add(handler);
        }
    }

    @Override
    public boolean hasHandler() {
        return !handlers.isEmpty();
    }

    /**
     * {@inheritDoc}
     *
     * @return an immutable collection
     */
    @Override
    public Iterable<T> getHandlers() {
        return Collections.unmodifiableSet(handlers);
    }
}
