/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.io.AbstractNotifier;
import fr.inria.atlanmod.neoemf.io.Handler;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link Reader}.
 *
 * @param <T> the type of the source
 */
@ParametersAreNonnullByDefault
public abstract class AbstractReader<T> extends AbstractNotifier<Handler> implements Reader<T> {

    /**
     * Constructs a new {@code AbstractReader} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public AbstractReader(Handler handler) {
        super(handler);
    }
}
