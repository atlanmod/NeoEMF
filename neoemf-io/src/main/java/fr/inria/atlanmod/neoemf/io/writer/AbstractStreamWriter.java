/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.writer;

import fr.inria.atlanmod.commons.annotation.Beta;

import java.io.OutputStream;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A abstract {@link Writer} that writes data into a {@link OutputStream}.
 */
@Beta
@ParametersAreNonnullByDefault
public abstract class AbstractStreamWriter extends AbstractWriter<OutputStream> {

    /**
     * Constructs a new {@code AbstractStreamWriter} with the given {@code stream}.
     *
     * @param stream the stream where to write data
     */
    protected AbstractStreamWriter(OutputStream stream) {
        super(stream);
    }

    @Override
    protected boolean requireEndBeforeFlush() {
        return true;
    }
}
