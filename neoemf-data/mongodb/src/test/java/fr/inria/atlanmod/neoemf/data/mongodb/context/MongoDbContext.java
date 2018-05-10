/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb.context;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import com.mongodb.connection.ServerVersion;

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.context.AbstractRemoteContext;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mongodb.MongoDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.mongodb.config.MongoDbConfig;

import java.net.URL;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A specific {@link Context} for the MongoDb implementation.
 */
@ParametersAreNonnullByDefault
public class MongoDbContext extends AbstractRemoteContext {

    /**
     * The initialized Fongo instance.
     */
    private static Fongo fongo;

    /**
     * Creates a new {@code MongoDbContext}.
     *
     * @return a new context.
     */
    @Nonnull
    public static Context getDefault() {
        return new MongoDbContext();
    }

    @Override
    public boolean isInitialized() {
        return nonNull(fongo);
    }

    @Override
    public Context init() {
        if (!isInitialized()) {
            try {
                fongo = new Fongo("neoemf", new ServerVersion(3, 6));
            }
            catch (Exception e) {
                Log.error(e, "Unable to create the MongoDB cluster");
                fongo = null;
            }
        }

        return this;
    }

    @Nonnull
    @Override
    public String name() {
        return "MongoDb";
    }

    @Nonnull
    @Override
    public BackendFactory factory() {
        return new MongoDbBackendFactoryMock();
    }

    @Nonnull
    @Override
    public ImmutableConfig config() {
        return MongoDbConfig.newConfig();
    }

    @Nonnull
    @Override
    protected String getHost() {
        return fongo.getServerAddress().getHost();
    }

    @Override
    protected int getPort() {
        return fongo.getServerAddress().getPort();
    }

    /**
     * A {@link MongoDbBackendFactory} using {@link Fongo} instead of a real instance.
     */
    @ParametersAreNonnullByDefault
    public class MongoDbBackendFactoryMock extends MongoDbBackendFactory {

        @Nonnull
        @Override
        public MongoClient createClient(URL url) {
            return fongo.getMongo();
        }
    }
}
