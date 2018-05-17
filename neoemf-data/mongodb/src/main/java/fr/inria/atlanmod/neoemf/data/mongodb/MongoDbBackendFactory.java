/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.ReadConcern;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mongodb.config.MongoDbConfig;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.osgi.service.component.annotations.Component;

import java.net.URL;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * A {@link BackendFactory} that creates {@link MongoDbBackend} instances.
 */
@Component(service = BackendFactory.class)
@ParametersAreNonnullByDefault
public class MongoDbBackendFactory extends AbstractBackendFactory<MongoDbConfig> {

    /**
     * Constructs a new {@code MongoDbBackendFactory}.
     */
    public MongoDbBackendFactory() {
        super("mongodb", false);
    }

    @Nonnull
    @Override
    protected Backend createRemoteBackend(URL url, MongoDbConfig config) {
        final MongoClient client = createClient(url);
        final MongoDatabase database = createDatabase(client, url.getPath().substring(1));

        return createMapper(config.getMapping(), client, database);
    }

    /**
     * Creates a new MongoDB client on a server located by the specified {@code url}.
     *
     * @param url the URL locating the MongoDB server
     *
     * @return a new client
     */
    @Nonnull
    private MongoClient createClient(URL url) {
        return new MongoClient(url.getHost(), url.getPort());
    }

    /**
     * Retrieves the database with the specified {@code name} on the {@code client}.
     * The database will be created if it does not already exist.
     *
     * @param client the MongoDB client, connected on the server
     * @param name   the name of the database
     *
     * @return the database
     */
    @Nonnull
    private MongoDatabase createDatabase(MongoClient client, String name) {
        final CodecRegistry registry = fromRegistries(
                client.getMongoClientOptions().getCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        return client.getDatabase(name)
                .withCodecRegistry(registry)
                .withWriteConcern(WriteConcern.MAJORITY)
                .withReadConcern(ReadConcern.MAJORITY);
    }
}
