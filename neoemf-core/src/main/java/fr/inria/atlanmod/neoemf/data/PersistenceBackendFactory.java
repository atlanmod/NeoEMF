/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
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
 *
 */
public interface PersistenceBackendFactory {

    String CONFIG_FILE = "neoconfig.properties";

    String BACKEND_PROPERTY = "backend";

    PersistenceBackend createTransientBackend();

    PersistenceBackend createPersistentBackend(File file, Map<?, ?> options) throws InvalidDataStoreException;

    PersistentStore createTransientStore(PersistentResource resource, PersistenceBackend backend);

    PersistentStore createPersistentStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException, InvalidOptionsException;

    void copyBackend(PersistenceBackend from, PersistenceBackend to);
}
