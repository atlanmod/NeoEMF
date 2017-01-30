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
import fr.inria.atlanmod.neoemf.annotations.Experimental;
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.option.BerkeleyDbStoreOptions;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbURI;
import fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteCachedMapStore;
import fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteMapStore;
import fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteMapStoreWithIndices;
import fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteMapStoreWithLists;
import fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.isNull;

/**
 * ???
 */
@Experimental
public class BerkeleyDbPersistenceBackendFactory extends AbstractPersistenceBackendFactory {

    /**
     * The literal description of the factory.
     */
    public static final String NAME = BerkeleyDbPersistenceBackend.NAME;

    /**
     * Constructs a new {@code BerkeleyDbPersistenceBackendFactory}.
     */
    protected BerkeleyDbPersistenceBackendFactory() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
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
            store = new DirectWriteMapStore<BerkeleyDbPersistenceBackend>(resource, (BerkeleyDbPersistenceBackend) backend);
        }
        else if (storeOptions.contains(BerkeleyDbStoreOptions.CACHE_MANY)) {
            store = new DirectWriteCachedMapStore<BerkeleyDbPersistenceBackend>(resource, (BerkeleyDbPersistenceBackend) backend);
        }
        else if (storeOptions.contains(BerkeleyDbStoreOptions.DIRECT_WRITE_LISTS)) {
            store = new DirectWriteMapStoreWithLists<BerkeleyDbPersistenceBackend>(resource, (BerkeleyDbPersistenceBackend) backend);
        }
        else if (storeOptions.contains(BerkeleyDbStoreOptions.DIRECT_WRITE_INDICES)) {
            store = new DirectWriteMapStoreWithIndices<BerkeleyDbPersistenceBackend>(resource, (BerkeleyDbPersistenceBackend) backend);
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

        }
        catch (IOException e) {
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

        return new DirectWriteMapStore<BerkeleyDbPersistenceBackend>(resource, (BerkeleyDbPersistenceBackend) backend);
    }

    @Override
    public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
        checkArgument(from instanceof BerkeleyDbPersistenceBackend && to instanceof BerkeleyDbPersistenceBackend,
                "The backend to copy is not an instance of BerkeleyDbPersistenceBackend");

        BerkeleyDbPersistenceBackend source = (BerkeleyDbPersistenceBackend) from;
        BerkeleyDbPersistenceBackend target = (BerkeleyDbPersistenceBackend) to;

        source.copyTo(target);
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static class Holder {

        /**
         * The instance of the outer class.
         */
        private static final PersistenceBackendFactory INSTANCE = new BerkeleyDbPersistenceBackendFactory();
    }
}
