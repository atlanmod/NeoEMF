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

package fr.inria.atlanmod.neoemf;

import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import java.io.IOException;

/**
 * A builder of {@link PersistentResource}.
 */
public interface TestBuilder {

    /**
     * Creates a {@link PersistentResource} according to the specified options.
     *
     * @return a new {@link PersistentResource}
     *
     * @throws IOException if an I/O error occurs
     */
    PersistentResource createResource() throws IOException;

    /**
     * Loads an existing {@link PersistentResource} according to the specified options.
     *
     * @return a new {@link PersistentResource}
     *
     * @throws IOException if an I/O error occurs
     */
    PersistentResource loadResource() throws IOException;

    /**
     * Creates a new {@link PersistenceBackend} according to the specified options.
     *
     * @return a new {@link PersistenceBackend}
     *
     * @throws IOException if an I/O error occurs
     */
    PersistenceBackend createBackend() throws IOException;
}
