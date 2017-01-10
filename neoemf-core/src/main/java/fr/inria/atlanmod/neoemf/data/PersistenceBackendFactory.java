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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import java.io.File;
import java.util.Map;

/**
 * A factory of {@link PersistenceBackend}.
 */
public interface PersistenceBackendFactory {

    /**
     * The name of the configuration file of a backend persistence.
     */
    String CONFIG_FILE = "neoconfig.properties";

    /**
     * The "backend" property in the configuration file.
     */
    String BACKEND_PROPERTY = "backend";

    /**
     * Creates transient backend persistence backend.
     *
     * @return the persistence backend
     */
    PersistenceBackend createTransientBackend();

    /**
     * Creates a {@link PersistenceBackend} in the given {@code file}.
     *
     * @param file    the file
     * @param options the options
     *
     * @return the persistence backend
     *
     * @throws InvalidDataStoreException the invalid data store exception
     */
    PersistenceBackend createPersistentBackend(File file, Map<?, ?> options) throws InvalidDataStoreException;

    /**
     * Creates a {@link PersistentStore} between the given {@code resource} and the given {@code backend}.
     *
     * @param resource the resource
     * @param backend  the backend
     *
     * @return the newly created persistent store.
     */
    PersistentStore createTransientStore(PersistentResource resource, PersistenceBackend backend);

    /**
     * Creates a {@link PersistentStore} between the given {@code resource} and the given {@code backend}
     * according to the given {@code options}.
     * <p/>
     * The returned {@link PersistentStore} may be a succession of several {@link PersistentStore}.
     *
     * @param resource the resource
     * @param backend  the backend
     * @param options  the options
     *
     * @return the newly created persistent store.
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in {@code options}, or if an option is missing
     * @throws IllegalArgumentException if the given {@code backend} is not an instance of the targeted {@link PersistenceBackend} for this factory
     */
    PersistentStore createPersistentStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException, IllegalArgumentException;

    /**
     * Copies the content from a {@link PersistenceBackend} to another.
     *
     * @param from the backend to copy
     * @param to   the destination
     */
    void copyBackend(PersistenceBackend from, PersistenceBackend to);
}
