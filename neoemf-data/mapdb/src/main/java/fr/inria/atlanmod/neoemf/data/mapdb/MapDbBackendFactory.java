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

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.eclipse.emf.common.util.URI;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;

/**
 * A factory that creates {@link MapDbBackend} instances.
 * <p>
 * As other implementations of {@link BackendFactory}, this class can create transient and persistent databases.
 * Persistent back-end creation can be configured using {@link PersistentResource#save(Map)} and {@link
 * PersistentResource#load(Map)} option maps.
 *
 * @see PersistentResource
 * @see MapDbBackend
 * @see fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbOptionsBuilder
 */
@ParametersAreNonnullByDefault
public class MapDbBackendFactory extends AbstractBackendFactory {

    /**
     * The literal description of the factory.
     */
    public static final String NAME = MapDbBackend.NAME.toLowerCase();

    /**
     * Constructs a new {@code MapDbBackendFactory}.
     */
    protected MapDbBackendFactory() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static BackendFactory getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Nonnull
    @Override
    public Backend createPersistentBackend(URI uri, Map<String, Object> options) {
        MapDbBackend backend;

        checkArgument(uri.isFile(),
                "%s only supports file-based URIs",
                MapDbBackendFactory.class.getSimpleName());

        File file = new File(uri.toFileString());

        File dbFile = new File(MapDbURI.createURI(uri.appendSegment("neoemf.mapdb")).toFileString());
        if (!dbFile.getParentFile().exists()) {
            try {
                Files.createDirectories(dbFile.getParentFile().toPath());
            }
            catch (IOException e) {
                Log.error(e);
            }
        }

        DB db = DBMaker.fileDB(dbFile)
                .fileMmapEnableIfSupported()
                .make();

        backend = new MapDbBackendIndices(db);
        processGlobalConfiguration(file);

        return backend;
    }

    @Nonnull
    @Override
    public Backend createTransientBackend() {
        DB db = DBMaker.memoryDB().make();
        return new MapDbBackendIndices(db);
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final BackendFactory INSTANCE = new MapDbBackendFactory();
    }
}
