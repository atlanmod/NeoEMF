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

/**
 * The abstract implementation of {@link InputNotifier}.
 *
 * @param <T> the type of the notified {@link InputHandler}
 */
public abstract class AbstractInputNotifier<T extends InputHandler> implements InputNotifier<T> {

    /**
     * The set of registered handlers.
     */
    private final Set<T> handlers;

    /**
     * Instantiates a new {@code AbstractInputNotifier}.
     */
    public AbstractInputNotifier() {
        this.handlers = new HashSet<>();
    }

    @Override
    public void addHandler(T handler) {
        handlers.add(handler);
    }

    @Override
    public boolean hasHandler() {
        return !handlers.isEmpty();
    }

    @Override
    public Iterable<T> getHandlers() {
        return Collections.unmodifiableSet(handlers);
    }
}
