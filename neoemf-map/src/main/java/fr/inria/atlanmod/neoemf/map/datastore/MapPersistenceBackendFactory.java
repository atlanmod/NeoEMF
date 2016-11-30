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

package fr.inria.atlanmod.neoemf.map.datastore;

import fr.inria.atlanmod.neoemf.datastore.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.InvalidOptionsException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.store.PersistentStore;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.store.DirectWriteMapCacheManyStore;
import fr.inria.atlanmod.neoemf.map.datastore.store.DirectWriteMapIndicesStore;
import fr.inria.atlanmod.neoemf.map.datastore.store.DirectWriteMapListsStore;
import fr.inria.atlanmod.neoemf.map.datastore.store.DirectWriteMapStore;
import fr.inria.atlanmod.neoemf.map.option.MapStoreOptions;
import fr.inria.atlanmod.neoemf.map.util.MapURI;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public final class MapPersistenceBackendFactory extends AbstractPersistenceBackendFactory {

    public static final String NAME = MapPersistenceBackend.NAME;

    private MapPersistenceBackendFactory() {
    }

    public static PersistenceBackendFactory getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    protected PersistentStore createSpecificPersistentStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException, InvalidOptionsException {
        checkArgument(backend instanceof MapPersistenceBackend,
                "Trying to create a Map-based EStore with an invalid backend: " + backend.getClass().getName());

        PersistentStore eStore = null;
        List<PersistentStoreOptions> storeOptions = getStoreOptions(options);

        // Store
        if (isNull(storeOptions) || storeOptions.isEmpty() || storeOptions.contains(MapStoreOptions.DIRECT_WRITE) || (storeOptions.size() == 1 && storeOptions.contains(MapStoreOptions.AUTOCOMMIT))) {
            eStore = new DirectWriteMapStore(resource, (MapPersistenceBackend) backend);
        }
        else if (storeOptions.contains(MapStoreOptions.CACHE_MANY)) {
            eStore = new DirectWriteMapCacheManyStore(resource, (MapPersistenceBackend) backend);
        }
        else if (storeOptions.contains(MapStoreOptions.DIRECT_WRITE_LISTS)) {
            eStore = new DirectWriteMapListsStore(resource, (MapPersistenceBackend) backend);
        }
        else if (storeOptions.contains(MapStoreOptions.DIRECT_WRITE_INDICES)) {
            eStore = new DirectWriteMapIndicesStore(resource, (MapPersistenceBackend) backend);
        }
        // Autocommit
        if (isNull(eStore)) {
            throw new InvalidDataStoreException();
        }
        else if (nonNull(storeOptions) && storeOptions.contains(MapStoreOptions.AUTOCOMMIT)) {
            eStore = new AutocommitStoreDecorator(eStore);
        }
        return eStore;
    }

    @Override
    public PersistenceBackend createTransientBackend() {
        DB db = DBMaker.memoryDB().make();
        return new MapPersistenceBackend(db);
    }

    @Override
    public PersistenceBackend createPersistentBackend(File file, Map<?, ?> options) throws InvalidDataStoreException {
        MapPersistenceBackend backend;

        File dbFile = FileUtils.getFile(MapURI.createURI(URI.createFileURI(file.getAbsolutePath()).appendSegment("neoemf.mapdb")).toFileString());
        if (!dbFile.getParentFile().exists()) {
            try {
                Files.createDirectories(dbFile.getParentFile().toPath());
            }
            catch (IOException e) {
                NeoLogger.error(e);
            }
        }

        DB db = DBMaker.fileDB(dbFile).fileMmapEnableIfSupported().make();
        backend = new MapPersistenceBackend(db);
        processGlobalConfiguration(file);

        return backend;
    }

    @Override
    public PersistentStore createTransientStore(PersistentResource resource, PersistenceBackend backend) {
        checkArgument(backend instanceof MapPersistenceBackend,
                "Trying to create a Map-based EStore with an invalid backend: " + backend.getClass().getName());

        return new DirectWriteMapStore(resource, (MapPersistenceBackend) backend);
    }

    @Override
    public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
        checkArgument(from instanceof MapPersistenceBackend && to instanceof MapPersistenceBackend,
                "The backend to copy is not an instance of MapPersistenceBackend");

        MapPersistenceBackend source = (MapPersistenceBackend) from;
        MapPersistenceBackend target = (MapPersistenceBackend) to;

        source.copyTo(target);
    }

    private static class Holder {

        private static final PersistenceBackendFactory INSTANCE = new MapPersistenceBackendFactory();
    }
}
