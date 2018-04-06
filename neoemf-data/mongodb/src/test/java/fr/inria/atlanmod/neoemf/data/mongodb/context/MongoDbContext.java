/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb.context;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.context.AbstractContext;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;

import fr.inria.atlanmod.neoemf.data.mongodb.MongoDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.mongodb.config.MongoDbConfig;
import fr.inria.atlanmod.neoemf.util.UriBuilder;
import org.eclipse.emf.common.util.URI;
import shaded.org.apache.http.client.utils.URIBuilder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.io.IOException;

/**
 * A specific {@link Context} for the MongoDb implementation.
 */
@ParametersAreNonnullByDefault
public class MongoDbContext extends AbstractContext {

    public MongoDbContext()
    {
        this.config = MongoDbConfig.newConfig();
    }

    private static final String TEST_HOST = "localhost";
    private static final int TEST_PORT = 27017;

    private MongoDbConfig config;
    /**
     * Creates a new {@code MongoDbContext}.
     *
     * @return a new context.
     */
    @Nonnull
    public static Context getDefault() {
        return new MongoDbContext();
    }

    @Nonnull
    @Override
    public ImmutableConfig config() {
        return config;
    }

    @Nonnull
    @Override
    public String name() {
        return "MongoDb";
    }

    @Nonnull
    @Override
    public BackendFactory factory() {
        return MongoDbBackendFactory.getInstance();
    }

    @Nonnull
    @Override
    public URI createUri(URI uri) {
        if (uri.scheme().equals("neo-mongodb") || uri.scheme().equals("file"))
            return UriBuilder.forScheme(uriScheme()).fromServer(TEST_HOST, TEST_PORT, uri);
        else
            throw new IllegalArgumentException();
    }

    @Nonnull
    @Override
    public URI createUri(File file) {
        return UriBuilder.forScheme(uriScheme()).fromServer(TEST_HOST, TEST_PORT, file.getName());
    }


}
