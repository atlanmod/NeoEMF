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

import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.io.Handler;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 *
 */
@ParametersAreNonnullByDefault
public abstract class AbstractPersistenceReader extends AbstractReader<PersistenceBackend> implements PersistenceReader {

    /**
     * Constructs a new {@code AbstractPersistenceReader} with the given {@code handlers}.
     *
     * @param handlers the handlers to notify
     */
    public AbstractPersistenceReader(Handler... handlers) {
        super(handlers);
    }

    @Override
    public void read(PersistenceBackend source) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
