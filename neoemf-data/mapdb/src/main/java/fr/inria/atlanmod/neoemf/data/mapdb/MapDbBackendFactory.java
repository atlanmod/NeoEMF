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

import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.neoemf.bind.annotation.FactoryName;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidBackendException;
import fr.inria.atlanmod.neoemf.data.PersistentBackend;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;

import org.eclipse.emf.common.util.URI;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;

/**
 * A factory that creates {@link MapDbBackend} instances.
 * <p>
 * As other implementations of {@link BackendFactory}, this class can create transient and persistent databases.
 * Persistent back-end creation can be configured using {@link fr.inria.atlanmod.neoemf.resource.PersistentResource#save(Map)}
 * and {@link fr.inria.atlanmod.neoemf.resource.PersistentResource#load(Map)} option maps.
 *
 * @see MapDbBackend
 * @see fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbOptions
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResource
 */
@ParametersAreNonnullByDefault
public class MapDbBackendFactory extends AbstractBackendFactory {

    /**
     * The literal description of the factory.
     */
    @FactoryName
    public static final String NAME = "mapdb";

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
    public PersistentBackend createPersistentBackend(URI uri, Map<String, Object> options) {
        MapDbBackend backend;

        checkArgument(uri.isFile(), "MapDbBackendFactory only supports file-based URIs");

        boolean isReadOnly = StoreFactory.isDefined(options, PersistentStoreOptions.READ_ONLY);

        try {
            Path baseDirectory = Paths.get(uri.toFileString());

            if (Files.notExists(baseDirectory)) {
                Files.createDirectories(baseDirectory);
            }

            DBMaker.Maker dbBuilder = DBMaker
                    .fileDB(baseDirectory.resolve("data").toFile())
                    .fileMmapEnableIfSupported();

            if (isReadOnly) {
                dbBuilder.readOnly();
            }

            DB db = dbBuilder.make();

            String mapping = mappingFrom(options);
            backend = newInstanceOf(mapping,
                    new ConstructorParameter(db, DB.class));

            processGlobalConfiguration(baseDirectory, mapping);
        }
        catch (Exception e) {
            throw new InvalidBackendException(e);
        }

        return backend;
    }

    @Nonnull
    @Override
    // TODO Remove this implementation
    public Backend createTransientBackend() {
        try {
            DB db = DBMaker.memoryDB().make();
            return new MapDbBackendIndices(db);
        }
        catch (Exception e) {
            throw new InvalidBackendException(e);
        }
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final BackendFactory INSTANCE = new MapDbBackendFactory();
    }
}
