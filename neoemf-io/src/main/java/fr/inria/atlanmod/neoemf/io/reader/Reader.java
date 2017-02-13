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

import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.Notifier;

import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 *
 */
@ParametersAreNonnullByDefault
public interface Reader<T> extends Notifier<Handler> {

    /**
     * Reads an {@link Object} and notifies registered {@link Handler}.
     *
     * @param input the object to read
     *
     * @throws IllegalArgumentException if this reader hasn't any handler
     * @throws IOException              if an error occurred during the I/O process
     */
    void read(T input) throws IOException;
}
