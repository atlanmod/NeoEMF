/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io;

import java.util.Arrays;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link Notifier} that provides overall behavior for the management of handlers.
 *
 * @param <H> the type of the notified {@link Handler}
 */
@ParametersAreNonnullByDefault
public abstract class AbstractNotifier<H extends Handler> implements Notifier<H> {

    /**
     * The handlers to notify.
     */
    @Nonnull
    private final Iterable<H> handlers;

    /**
     * Constructs a new {@code AbstractNotifier} with the given {@code handlers}.
     *
     * @param handlers the handlers to notify
     */
    @SafeVarargs
    public AbstractNotifier(H... handlers) {
        checkNotNull(handlers, "handlers");
        this.handlers = Arrays.asList(handlers);
    }

    @Nonnull
    @Override
    public Iterable<H> next() {
        return handlers;
    }
}
