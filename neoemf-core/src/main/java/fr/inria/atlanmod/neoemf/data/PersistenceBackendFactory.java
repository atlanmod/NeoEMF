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
 * A factory of {@link PersistenceBackend} and {@link PersistentStore}.
 * <p>
 * The creation can be configured using {@link PersistentResource#save(Map)} and {@link PersistentResource#load(Map)}
 * option maps.
 *
 * @see fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder
 */
public interface PersistenceBackendFactory {

    /**
     * The name of the configuration file of a back-end persistence.
     */
    String CONFIG_FILE = "neoconfig.properties";

    /**
     * The "back-end" property in the configuration file.
     */
    String BACKEND_PROPERTY = "backend";

    /**
     * Creates an in-memory {@link PersistenceBackend}.
     *
     * @return the persistence back-end
     */
    PersistenceBackend createTransientBackend();

    /**
     * Creates a {@link PersistenceBackend} in the given {@code directory}.
     *
     * @param directory the directory
     * @param options   the options that defines the behaviour of the back-end
     *
     * @return the persistence back-end
     *
     * @throws InvalidDataStoreException the invalid datastore exception
     */
    PersistenceBackend createPersistentBackend(File directory, Map<?, ?> options) throws InvalidDataStoreException;

    /**
     * Creates a {@link PersistentStore} between the given {@code resource} and the given in-memory {@code backend}.
     *
     * @param resource the resource
     * @param backend  the back-end
     *
     * @return the newly created persistent store.
     */
    PersistentStore createTransientStore(PersistentResource resource, PersistenceBackend backend);

    /**
     * Creates a {@link PersistentStore} between the given {@code resource} and the given {@code backend}
     * according to the given {@code options}.
     * <p>
     * The returned {@link PersistentStore} may be a succession of several {@link PersistentStore}.
     *
     * @param resource the resource
     * @param backend  the back-end
     * @param options  the options that defines the behaviour of the back-end
     *
     * @return the newly created persistent store.
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in {@code options}, or if an option is
     *                                   missing
     * @throws IllegalArgumentException  if the given {@code backend} is not an instance of the targeted {@link
     *                                   PersistenceBackend} for this factory
     */
    PersistentStore createPersistentStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException, IllegalArgumentException;

    /**
     * Copies the content from a {@link PersistenceBackend} to another.
     *
     * @param from the back-end to copy
     * @param to   the destination
     */
    void copyBackend(PersistenceBackend from, PersistenceBackend to);
}
