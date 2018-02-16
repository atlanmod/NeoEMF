/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import fr.inria.atlanmod.neoemf.data.Backend;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Backend} that is responsible of low-level access to a MongoDb database.
 * <p>
 * It wraps an existing MongoDb database and provides facilities to create and retrieve elements.
 * <p>
 * <b>Note:</b> Instances of {@code MongoDbBackend} are created by {@link MongoDbBackendFactory} that
 * provides an usable database that can be manipulated by this wrapper.
 *
 * @see MongoDbBackendFactory
 */
@ParametersAreNonnullByDefault
public interface MongoDbBackend extends Backend {

    /**
     * Sets the MongoDb database to use for this backend
     * @param db the MongoDatabase
     */
    void setMongoDatabase(MongoDatabase db);

    /**
     * Sets the MongoDb client to use for this backend
     * @param client the MongoClient
     */
    void setMongoClient(MongoClient client);

    @Override
    default boolean isPersistent() {
        return true;
    }

    @Override
    default boolean isDistributed() {
        return false;
    }
}
