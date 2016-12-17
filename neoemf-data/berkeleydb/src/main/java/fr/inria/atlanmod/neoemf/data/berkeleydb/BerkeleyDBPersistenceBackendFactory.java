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

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.option.BerkeleyDBStoreOptions;
import fr.inria.atlanmod.neoemf.data.berkeleydb.store.DirectWriteBerkeleyDBCacheManyStore;
import fr.inria.atlanmod.neoemf.data.berkeleydb.store.DirectWriteBerkeleyDBIndicesStore;
import fr.inria.atlanmod.neoemf.data.berkeleydb.store.DirectWriteBerkeleyDBListsStore;
import fr.inria.atlanmod.neoemf.data.berkeleydb.store.DirectWriteBerkeleyDBStore;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDBURI;
import fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public final class BerkeleyDBPersistenceBackendFactory extends AbstractPersistenceBackendFactory {

    public static final String NAME = BerkeleyDBPersistenceBackend.NAME;

    private BerkeleyDBPersistenceBackendFactory() {
    }

    public static PersistenceBackendFactory getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    protected PersistentStore createSpecificPersistentStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException {
        checkArgument(backend instanceof BerkeleyDBPersistenceBackend,
                "Trying to create a MapDB store with an invalid backend: " + backend.getClass().getName());

        PersistentStore eStore = null;
        List<PersistentStoreOptions> storeOptions = getStoreOptions(options);

        // Store
        if (isNull(storeOptions) || storeOptions.isEmpty() || storeOptions.contains(BerkeleyDBStoreOptions.DIRECT_WRITE) || (storeOptions.size() == 1 && storeOptions.contains(BerkeleyDBStoreOptions.AUTOCOMMIT))) {
            eStore = new DirectWriteBerkeleyDBStore(resource, (BerkeleyDBPersistenceBackend) backend);
        }
        else if (storeOptions.contains(BerkeleyDBStoreOptions.CACHE_MANY)) {
            eStore = new DirectWriteBerkeleyDBCacheManyStore(resource, (BerkeleyDBPersistenceBackend) backend);
        }
        else if (storeOptions.contains(BerkeleyDBStoreOptions.DIRECT_WRITE_LISTS)) {
            eStore = new DirectWriteBerkeleyDBListsStore(resource, (BerkeleyDBPersistenceBackend) backend);
        }
        else if (storeOptions.contains(BerkeleyDBStoreOptions.DIRECT_WRITE_INDICES)) {
            eStore = new DirectWriteBerkeleyDBIndicesStore(resource, (BerkeleyDBPersistenceBackend) backend);
        }
        // Autocommit
        if (isNull(eStore)) {
            throw new InvalidDataStoreException();
        }
        else if (nonNull(storeOptions) && storeOptions.contains(BerkeleyDBStoreOptions.AUTOCOMMIT)) {
            eStore = new AutocommitStoreDecorator(eStore);
        }
        return eStore;
    }

    @Override
    public PersistenceBackend createTransientBackend() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PersistenceBackend createPersistentBackend(File file, Map<?, ?> options) throws InvalidDataStoreException {
        BerkeleyDBPersistenceBackend backend;

        File dbFile = FileUtils.getFile(BerkeleyDBURI.createURI(URI.createFileURI(file.getAbsolutePath()).appendSegment("neoemf.berkeleydb")).toFileString());
        if (!dbFile.getParentFile().exists()) {
            try {
                Files.createDirectories(dbFile.getParentFile().toPath());
            }
            catch (IOException e) {
                NeoLogger.error(e);
            }
        }
        backend = new BerkeleyDBPersistenceBackend(dbFile);
        processGlobalConfiguration(file);

        return backend;
    }

    @Override
    public PersistentStore createTransientStore(PersistentResource resource, PersistenceBackend backend) {
        checkArgument(backend instanceof BerkeleyDBPersistenceBackend,
                "Trying to create a MapDB store with an invalid backend: " + backend.getClass().getName());

        return new DirectWriteBerkeleyDBStore(resource, (BerkeleyDBPersistenceBackend) backend);
    }

    @Override
    public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
        checkArgument(from instanceof BerkeleyDBPersistenceBackend && to instanceof BerkeleyDBPersistenceBackend,
                "The backend to copy is not an instance of BerkeleyDBPersistenceBackend");

        BerkeleyDBPersistenceBackend source = (BerkeleyDBPersistenceBackend) from;
        BerkeleyDBPersistenceBackend target = (BerkeleyDBPersistenceBackend) to;

        source.copyTo(target);
    }

    private static class Holder {

        private static final PersistenceBackendFactory INSTANCE = new BerkeleyDBPersistenceBackendFactory();
    }
}
