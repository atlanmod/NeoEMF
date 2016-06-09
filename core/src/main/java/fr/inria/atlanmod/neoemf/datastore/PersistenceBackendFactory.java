/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.datastore;

import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;

import java.io.File;
import java.util.Map;

/**
 *
 */
public interface PersistenceBackendFactory {

    String NEO_CONFIG_FILE = "neoconfig.properties";
    String BACKEND_PROPERTY = "backend";

    PersistenceBackend createTransientBackend();

    PersistenceBackend createPersistentBackend(File file, Map<?,?> options) throws InvalidDataStoreException;

    SearcheableResourceEStore createTransientEStore(PersistentResource resource, PersistenceBackend backend);

    SearcheableResourceEStore createPersistentEStore(PersistentResource resource, PersistenceBackend backend, Map<?,?> options) throws InvalidDataStoreException;

    void copyBackend(PersistenceBackend from, PersistenceBackend to);
}
