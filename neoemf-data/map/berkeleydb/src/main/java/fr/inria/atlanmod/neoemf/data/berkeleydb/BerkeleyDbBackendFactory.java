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

import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.EnvironmentConfig;

import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbURI;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * ???
 */
public class BerkeleyDbBackendFactory extends AbstractPersistenceBackendFactory {

    /**
     * The literal description of the factory.
     */
    public static final String NAME = BerkeleyDbBackend.NAME;

    /**
     * Constructs a new {@code BerkeleyDbBackendFactory}.
     */
    protected BerkeleyDbBackendFactory() {
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
        checkArgument(backend instanceof BerkeleyDbBackend,
                "Trying to create a BerkeleyDB store with an invalid backend: " + backend.getClass().getName());

        return new DirectWriteStore<>(resource, backend);
    }

    @Override
    public PersistenceBackend createTransientBackend() throws InvalidDataStoreException {
        BerkeleyDbBackend backend;

        try {
            File dir = new File(BerkeleyDbURI.createFileURI(Files.createTempDirectory("neoemf").toFile()).toFileString());

            EnvironmentConfig envConfig = new EnvironmentConfig()
                    .setAllowCreate(true)
                    .setConfigParam(EnvironmentConfig.LOG_MEM_ONLY, "true");

            DatabaseConfig dbConfig = new DatabaseConfig()
                    .setAllowCreate(true)
                    .setSortedDuplicates(false)
                    .setDeferredWrite(true);

            backend = new BerkeleyDbBackendIndices(dir, envConfig, dbConfig);
//            backend.open();
        }
        catch (IOException e) {
            NeoLogger.error(e);
            throw new InvalidDataStoreException(e);
        }

        return backend;
    }

    @Override
    public PersistenceBackend createPersistentBackend(URI uri, Map<?, ?> options) throws InvalidDataStoreException {
        BerkeleyDbBackend backend;

        checkArgument(uri.isFile(), "NeoEMF/BerkeleyDB only supports file URIs");
        File file = FileUtils.getFile(uri.toFileString());

        try {
            File dir = new File(uri.toFileString());
            if (!dir.exists()) {
                Files.createDirectories(dir.toPath());
            }

            EnvironmentConfig envConfig = new EnvironmentConfig()
                    .setAllowCreate(true);

            DatabaseConfig dbConfig = new DatabaseConfig()
                    .setAllowCreate(true)
                    .setSortedDuplicates(false)
                    .setDeferredWrite(true);

            backend = new BerkeleyDbBackendIndices(dir, envConfig, dbConfig);
//            backend.open();

            processGlobalConfiguration(file);
        }
        catch (IOException e) {
            NeoLogger.error(e);
            throw new InvalidDataStoreException(e);
        }

        return backend;
    }

    @Override
    public PersistentStore createTransientStore(PersistentResource resource, PersistenceBackend backend) {
        checkArgument(backend instanceof BerkeleyDbBackend,
                "Trying to create a BerkeleyDB store with an invalid backend: " + backend.getClass().getName());

        return new DirectWriteStore<>(resource, backend);
    }

    @Override
    public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
        checkArgument(from instanceof BerkeleyDbBackend && to instanceof BerkeleyDbBackend,
                "The backend to copy is not an instance of BerkeleyDbBackendIndices");

        BerkeleyDbBackend source = (BerkeleyDbBackend) from;
        BerkeleyDbBackend target = (BerkeleyDbBackend) to;

        source.copyTo(target);
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static class Holder {

        /**
         * The instance of the outer class.
         */
        private static final PersistenceBackendFactory INSTANCE = new BerkeleyDbBackendFactory();
    }
}
