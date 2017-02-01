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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An abstract {@link Notifier} that provides overall behavior for the management of handlers.
 *
 * @param <H> the type of the notified {@link Handler}
 */
public abstract class AbstractNotifier<H extends Handler> implements Notifier<H> {

    /**
     * The handlers to notify.
     */
    private Set<H> handlers;

    /**
     * Constructs a new {@code AbstractNotifier} with the given {@code handlers}.
     *
     * @param handlers the handlers to notify
     */
    @SafeVarargs
    public AbstractNotifier(H... handlers) {
        checkNotNull(handlers);

        this.handlers = new HashSet<>(Arrays.asList(handlers));
    }

    @Override
    public Iterable<H> next() {
        return handlers;
    }
}
