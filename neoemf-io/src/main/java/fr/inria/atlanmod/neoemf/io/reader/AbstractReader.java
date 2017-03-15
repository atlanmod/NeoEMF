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

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.io.AbstractNotifier;
import fr.inria.atlanmod.neoemf.io.Handler;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link Reader}.
 *
 * @param <T> the type of the read source
 */
@ParametersAreNonnullByDefault
public abstract class AbstractReader<T> extends AbstractNotifier<Handler> implements Reader<T> {

    /**
     * Constructs a new {@code AbstractReader} with the given {@code handlers}.
     *
     * @param handlers the handlers to notify
     */
    public AbstractReader(Handler... handlers) {
        super(handlers);
    }
}
