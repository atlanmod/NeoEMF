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

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import com.sleepycat.je.EnvironmentConfig;

import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.option.BerkeleyDbStoreOptions;
import fr.inria.atlanmod.neoemf.data.berkeleydb.store.DirectWriteBerkeleyDbCacheManyStore;
import fr.inria.atlanmod.neoemf.data.berkeleydb.store.DirectWriteBerkeleyDbIndicesStore;
import fr.inria.atlanmod.neoemf.data.berkeleydb.store.DirectWriteBerkeleyDbListsStore;
import fr.inria.atlanmod.neoemf.data.berkeleydb.store.DirectWriteBerkeleyDbStore;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbURI;
import fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.isNull;

public final class BerkeleyDbPersistenceBackendFactory extends AbstractPersistenceBackendFactory {

    public static final String NAME = BerkeleyDbPersistenceBackend.NAME;

    private BerkeleyDbPersistenceBackendFactory() {
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
        checkArgument(backend instanceof BerkeleyDbPersistenceBackend,
                "Trying to create a BerkeleyDB store with an invalid backend: " + backend.getClass().getName());

        PersistentStore store = null;
        List<PersistentStoreOptions> storeOptions = getStoreOptions(options);

        // Store
        if (storeOptions.isEmpty() || storeOptions.contains(BerkeleyDbStoreOptions.DIRECT_WRITE) || storeOptions.size() == 1 && storeOptions.contains(BerkeleyDbStoreOptions.AUTOCOMMIT)) {
            store = new DirectWriteBerkeleyDbStore(resource, (BerkeleyDbPersistenceBackend) backend);
        }
        else if (storeOptions.contains(BerkeleyDbStoreOptions.CACHE_MANY)) {
            store = new DirectWriteBerkeleyDbCacheManyStore(resource, (BerkeleyDbPersistenceBackend) backend);
        }
        else if (storeOptions.contains(BerkeleyDbStoreOptions.DIRECT_WRITE_LISTS)) {
            store = new DirectWriteBerkeleyDbListsStore(resource, (BerkeleyDbPersistenceBackend) backend);
        }
        else if (storeOptions.contains(BerkeleyDbStoreOptions.DIRECT_WRITE_INDICES)) {
            store = new DirectWriteBerkeleyDbIndicesStore(resource, (BerkeleyDbPersistenceBackend) backend);
        }
        // Autocommit
        if (isNull(store)) {
            throw new InvalidDataStoreException();
        }
        else if (storeOptions.contains(BerkeleyDbStoreOptions.AUTOCOMMIT)) {
            store = new AutocommitStoreDecorator(store);
        }
        return store;
    }

    @Override
    public PersistenceBackend createTransientBackend() {
        NeoLogger.debug("createTransientBackend()");
        BerkeleyDbPersistenceBackend backend = null;

        try {
            File temporaryFolder = Files.createTempDirectory("neoemf").toFile();
            EnvironmentConfig envConfig = new EnvironmentConfig();
            envConfig.setAllowCreate(true);
            envConfig.setConfigParam(EnvironmentConfig.LOG_MEM_ONLY, "true");

            File dir = new File(BerkeleyDbURI.createFileURI(temporaryFolder).toFileString());
            backend = new BerkeleyDbPersistenceBackend(dir, envConfig);
            backend.open();

        } catch (IOException e) {
            NeoLogger.error(e);
        }
        return backend;
    }

    @Override
    public PersistenceBackend createPersistentBackend(File directory, Map<?, ?> options) throws InvalidDataStoreException {
        NeoLogger.debug("createPersistentBackend() " + directory.toString());

        BerkeleyDbPersistenceBackend backend;
        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(true);
        File dir = new File(BerkeleyDbURI.createFileURI(directory).toFileString());
        if (!dir.exists()) {
            try {
                Files.createDirectories(dir.toPath());
            }
            catch (IOException e) {
                NeoLogger.error(e);
            }
        }
        backend = new BerkeleyDbPersistenceBackend(dir, envConfig);
        backend.open();
        processGlobalConfiguration(directory);
        return backend;
    }

    @Override
    public PersistentStore createTransientStore(PersistentResource resource, PersistenceBackend backend) {
        checkArgument(backend instanceof BerkeleyDbPersistenceBackend,
                "Trying to create a BerkeleyDB store with an invalid backend: " + backend.getClass().getName());

        return new DirectWriteBerkeleyDbStore(resource, (BerkeleyDbPersistenceBackend) backend);
    }

    @Override
    public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
        checkArgument(from instanceof BerkeleyDbPersistenceBackend && to instanceof BerkeleyDbPersistenceBackend,
                "The backend to copy is not an instance of BerkeleyDbPersistenceBackend");

        BerkeleyDbPersistenceBackend source = (BerkeleyDbPersistenceBackend) from;
        BerkeleyDbPersistenceBackend target = (BerkeleyDbPersistenceBackend) to;

        source.copyTo(target);
    }

    private static class Holder {

        private static final PersistenceBackendFactory INSTANCE = new BerkeleyDbPersistenceBackendFactory();
    }
}
