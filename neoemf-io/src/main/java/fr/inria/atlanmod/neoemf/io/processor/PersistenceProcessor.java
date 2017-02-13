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

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.neoemf.io.writer.PersistenceWriter;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Processor} that notifies registered {@link PersistenceWriter} of events, without any treatment.
 */
@ParametersAreNonnullByDefault
public final class PersistenceProcessor extends AbstractProcessor<PersistenceWriter> implements Processor {

    /**
     * Constructs a new {@code PersistenceProcessor} with the given {@code handlers}.
     *
     * @param handlers the handlers to notify
     */
    public PersistenceProcessor(PersistenceWriter... handlers) {
        super(handlers);
    }
}
