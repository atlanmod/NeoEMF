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

import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.estores.PersistentEStore;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.AutocommitEStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.impl.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.CachedManyDirectWriteMapResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.DirectWriteMapResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.DirectWriteMapWithIndexesResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.DirectWriteMapWithListsResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.map.util.NeoMapURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions.StoreOption;

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
import static fr.inria.atlanmod.neoemf.map.resources.MapResourceOptions.EStoreMapOption.*;
import static java.util.Objects.isNull;

public final class MapPersistenceBackendFactory extends AbstractPersistenceBackendFactory {

    public static final String MAPDB_BACKEND = "mapdb";

    private static PersistenceBackendFactory INSTANCE;

    private MapPersistenceBackendFactory() {
    }

    public static PersistenceBackendFactory getInstance() {
        if (isNull(INSTANCE)) {
            INSTANCE = new MapPersistenceBackendFactory();
        }
        return INSTANCE;
    }

    @Override
    public String getName() {
        return MAPDB_BACKEND;
    }

    @Override
    protected PersistentEStore internalCreatePersistentEStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException {
        checkArgument(backend instanceof MapPersistenceBackend,
                "Trying to create a Map-based EStore with an invalid backend: " + backend.getClass().getName());

        PersistentEStore eStore = null;
        List<StoreOption> storeOptions = storeOptionsFrom(options);

        // Store
        if (isNull(storeOptions) || storeOptions.isEmpty() || storeOptions.contains(DIRECT_WRITE) || (storeOptions.size() == 1 && storeOptions.contains(AUTOCOMMIT))) {
            eStore = new DirectWriteMapResourceEStoreImpl(resource, (MapPersistenceBackend) backend);
        }
        else if (storeOptions.contains(CACHED_MANY)) {
            eStore = new CachedManyDirectWriteMapResourceEStoreImpl(resource, (MapPersistenceBackend) backend);
        }
        else if (storeOptions.contains(DIRECT_WRITE_WITH_LISTS)) {
            eStore = new DirectWriteMapWithListsResourceEStoreImpl(resource, (MapPersistenceBackend) backend);
        }
        else if (storeOptions.contains(DIRECT_WRITE_WITH_INDEXES)) {
            eStore = new DirectWriteMapWithIndexesResourceEStoreImpl(resource, (MapPersistenceBackend) backend);
        }
        // Autocommit
        if (isNull(eStore)) {
            throw new InvalidDataStoreException();
        }
        else if (!isNull(storeOptions) && storeOptions.contains(AUTOCOMMIT)) {
            eStore = new AutocommitEStoreDecorator(eStore);
        }
        return eStore;
    }

    @Override
    public PersistenceBackend createTransientBackend() {
        //Engine mapEngine = DBMaker.newMemoryDB().makeEngine();
        DB db = DBMaker.memoryDB().make();
        return new MapPersistenceBackend(db);
    }

    @Override
    public PersistenceBackend createPersistentBackend(File file, Map<?, ?> options) throws InvalidDataStoreException {
        MapPersistenceBackend backend;

        File dbFile = FileUtils.getFile(NeoMapURI.createNeoMapURI(URI.createFileURI(file.getAbsolutePath()).appendSegment("neoemf.mapdb")).toFileString());
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
    public PersistentEStore createTransientEStore(PersistentResource resource, PersistenceBackend backend) {
        checkArgument(backend instanceof MapPersistenceBackend,
                "Trying to create a Map-based EStore with an invalid backend: " + backend.getClass().getName());

        return new DirectWriteMapResourceEStoreImpl(resource, (MapPersistenceBackend) backend);
    }

    @Override
    public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
        checkArgument(from instanceof MapPersistenceBackend && to instanceof MapPersistenceBackend,
                "The backend to copy is not an instance of MapPersistenceBackend");

        MapPersistenceBackend source = (MapPersistenceBackend) from;
        MapPersistenceBackend target = (MapPersistenceBackend) to;

        source.copyTo(target);
    }
}
