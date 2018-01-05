/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.neoemf.io.writer.Writer;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Processor} that simply notifies registered {@link Writer} of events, without any additional operation.
 */
@ParametersAreNonnullByDefault
public final class NoopProcessor extends AbstractProcessor<Writer> implements Processor {

    /**
     * Constructs a new {@code NoopProcessor} with the given {@code writers}.
     *
     * @param writers the writers to notify
     */
    public NoopProcessor(Writer... writers) {
        super(writers);
    }
}
