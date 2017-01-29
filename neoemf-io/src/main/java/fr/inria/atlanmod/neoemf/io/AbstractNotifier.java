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

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link Notifier} that provides overall behavior for the management of handlers.
 *
 * @param <T> the type of the notified {@link Handler}
 */
@ParametersAreNonnullByDefault
public abstract class AbstractNotifier<T extends Handler> implements Notifier<T> {

    /**
     * The registered handler.
     */
    private T handler;

    @Override
    public T handler() {
        return handler;
    }

    @Override
    public void handler(T handler) {
        this.handler = handler;
    }
}
