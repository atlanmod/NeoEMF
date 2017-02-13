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

package fr.inria.atlanmod.neoemf.io.writer;

import fr.inria.atlanmod.neoemf.data.PersistenceBackend;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The factory that creates instances of {@link PersistenceWriter}s.
 */
@ParametersAreNonnullByDefault
public class PersistenceHandlerFactory {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private PersistenceHandlerFactory() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Creates a {@link PersistenceWriter} on the given {@code backend} with conflict resolution feature.
     *
     * @param backend the back-end where data must persist
     *
     * @return a new persistence handler
     */
    public static PersistenceWriter newAwareHandler(PersistenceBackend backend) {
        return new PersistenceAwareWriter(backend);
    }

    /**
     * Creates a {@link PersistenceWriter} on the given {@code backend} <b>without</b> conflict resolution feature.
     *
     * @param backend the back-end where data must persist
     *
     * @return a new persistence handler
     */
    public static PersistenceWriter newNaiveHandler(PersistenceBackend backend) {
        return new PersistenceNaiveWriter(backend);
    }
}
