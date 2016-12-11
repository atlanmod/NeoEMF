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

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbListsStore;
import fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbStoreOptions;
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.InvalidOptionsException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;
import fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbCacheManyStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbIndicesStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbStore;
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

public final class MapDbPersistenceBackendFactory extends AbstractPersistenceBackendFactory {

    public static final String NAME = MapDbPersistenceBackend.NAME;

    private MapDbPersistenceBackendFactory() {
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
        checkArgument(backend instanceof MapDbPersistenceBackend,
                "Trying to create a MapDB store with an invalid backend: " + backend.getClass().getName());

        PersistentStore eStore = null;
        List<PersistentStoreOptions> storeOptions = getStoreOptions(options);

        // Store
        if (isNull(storeOptions) || storeOptions.isEmpty() || storeOptions.contains(MapDbStoreOptions.DIRECT_WRITE) || (storeOptions.size() == 1 && storeOptions.contains(MapDbStoreOptions.AUTOCOMMIT))) {
            eStore = new DirectWriteMapDbStore(resource, (MapDbPersistenceBackend) backend);
        }
        else if (storeOptions.contains(MapDbStoreOptions.CACHE_MANY)) {
            eStore = new DirectWriteMapDbCacheManyStore(resource, (MapDbPersistenceBackend) backend);
        }
        else if (storeOptions.contains(MapDbStoreOptions.DIRECT_WRITE_LISTS)) {
            eStore = new DirectWriteMapDbListsStore(resource, (MapDbPersistenceBackend) backend);
        }
        else if (storeOptions.contains(MapDbStoreOptions.DIRECT_WRITE_INDICES)) {
            eStore = new DirectWriteMapDbIndicesStore(resource, (MapDbPersistenceBackend) backend);
        }
        // Autocommit
        if (isNull(eStore)) {
            throw new InvalidDataStoreException();
        }
        else if (nonNull(storeOptions) && storeOptions.contains(MapDbStoreOptions.AUTOCOMMIT)) {
            eStore = new AutocommitStoreDecorator(eStore);
        }
        return eStore;
    }

    @Override
    public PersistenceBackend createTransientBackend() {
        DB db = DBMaker.memoryDB().make();
        return new MapDbPersistenceBackend(db);
    }

    @Override
    public PersistenceBackend createPersistentBackend(File file, Map<?, ?> options) throws InvalidDataStoreException {
        MapDbPersistenceBackend backend;

        File dbFile = FileUtils.getFile(MapDbURI.createURI(URI.createFileURI(file.getAbsolutePath()).appendSegment("neoemf.mapdb")).toFileString());
        if (!dbFile.getParentFile().exists()) {
            try {
                Files.createDirectories(dbFile.getParentFile().toPath());
            }
            catch (IOException e) {
                NeoLogger.error(e);
            }
        }

        DB db = DBMaker.fileDB(dbFile).fileMmapEnableIfSupported().make();
        backend = new MapDbPersistenceBackend(db);
        processGlobalConfiguration(file);

        return backend;
    }

    @Override
    public PersistentStore createTransientStore(PersistentResource resource, PersistenceBackend backend) {
        checkArgument(backend instanceof MapDbPersistenceBackend,
                "Trying to create a MapDB store with an invalid backend: " + backend.getClass().getName());

        return new DirectWriteMapDbStore(resource, (MapDbPersistenceBackend) backend);
    }

    @Override
    public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
        checkArgument(from instanceof MapDbPersistenceBackend && to instanceof MapDbPersistenceBackend,
                "The backend to copy is not an instance of MapDbPersistenceBackend");

        MapDbPersistenceBackend source = (MapDbPersistenceBackend) from;
        MapDbPersistenceBackend target = (MapDbPersistenceBackend) to;

        source.copyTo(target);
    }

    private static class Holder {

        private static final PersistenceBackendFactory INSTANCE = new MapDbPersistenceBackendFactory();
    }
}
