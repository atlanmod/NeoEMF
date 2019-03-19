/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.mongodb.config.MongoDbConfig;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Adapter} on top of a {@link fr.inria.atlanmod.neoemf.data.mongodb.MongoDbBackend}.
 *
 * @see "https://hub.docker.com/_/mongo/"
 */
@AdapterName("mongodb")
@ParametersAreNonnullByDefault
public class MongoDbAdapter extends AbstractPersistentRemoteAdapter {

    @Nonnull
    @Override
    protected ImmutableConfig createConfig() {
        return new MongoDbConfig();
    }

    @Nonnull
    @Override
    protected String getHost() {
        return "localhost";
    }

    @Override
    protected int getPort() {
        return 27017;
    }
}
