/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.io.Notifier;

import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.WillNotClose;

/**
 * A {@link fr.inria.atlanmod.neoemf.io.Notifier} able to read data from an {@link Object}.
 *
 * @param <T> the type of the source
 */
@ParametersAreNonnullByDefault
public interface Reader<T> extends Notifier {

    /**
     * Reads an {@link Object}.
     *
     * @param source the object to read
     *
     * @throws IllegalArgumentException if this reader hasn't any handler
     * @throws IOException              if an error occurred during the I/O process
     */
    void read(@WillNotClose T source) throws IOException;
}
