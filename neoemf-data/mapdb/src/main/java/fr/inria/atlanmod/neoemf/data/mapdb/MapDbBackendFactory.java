/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.config.MapDbConfig;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.osgi.service.component.annotations.Component;

import java.nio.file.Files;
import java.nio.file.Path;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.BackendFactory} that creates {@link MapDbBackend} instances.
 */
@Component(service = BackendFactory.class)
@ParametersAreNonnullByDefault
public class MapDbBackendFactory extends AbstractBackendFactory<MapDbConfig> {

    /**
     * Constructs a new {@code MapDbBackendFactory}.
     */
    public MapDbBackendFactory() {
        super("mapdb");
    }

    @Nonnull
    @Override
    protected Backend createLocalBackend(Path directory, MapDbConfig config) throws Exception {
        if (!directory.toFile().exists()) {
            Files.createDirectories(directory);
        }

        DBMaker.Maker dbBuilder = DBMaker
                .fileDB(directory.resolve("data").toFile())
                .fileMmapEnableIfSupported();

        if (config.isReadOnly()) {
            dbBuilder.readOnly();
        }

        DB db = dbBuilder.make();

        return createMapper(config.getMapping(), db);
    }
}
