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

/**
 * An abstract {@link Notifier} that provides overall behavior for the management of handlers.
 *
 * @param <H> the type of the notified {@link Handler}
 */
public abstract class AbstractNotifier<H extends Handler> implements Notifier<H> {

    /**
     * The handler to notify.
     */
    private H handler;

    /**
     * Constructs a new {@code AbstractNotifier}.
     */
    protected AbstractNotifier() {
    }

    /**
     * Constructs a new {@code AbstractNotifier} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    protected AbstractNotifier(H handler) {
        this.handler = handler;
    }

    @Override
    public H handler() {
        return handler;
    }

    @Override
    public void handler(H handler) {
        this.handler = handler;
    }
}
