/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.InvalidBackend;
import fr.inria.atlanmod.neoemf.util.AbstractUriFactory;
import fr.inria.atlanmod.neoemf.util.UriFactory;

import org.eclipse.emf.common.util.URI;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A mocked {@link BackendFactory} that always returns the same {@link Backend}.
 * <p>
 * <b>NOTE:</b> This class exists for binding a {@link Config} and {@link UriFactory} to this {@link BackendFactory}.
 * You need to register the factory you want to use, with {@link BackendFactoryRegistry#register(String,
 * BackendFactory)}, before any call to {@link BackendFactory#createBackend(URI, ImmutableConfig)}.
 */
@ParametersAreNonnullByDefault
public final class MockBackendFactory extends AbstractBackendFactory<MockBackendFactory.MockConfig> {

    /**
     * The literal description of the factory.
     */
    @Nonnull
    private static final String NAME = "mock";

    /**
     * The back-end returned by {@link #createBackend(URI, ImmutableConfig)}.
     */
    @Nonnull
    private final Backend backend;

    /**
     * Constructs a new {@code MockBackendFactory}.
     */
    @VisibleForReflection
    public MockBackendFactory() {
        this(new InvalidBackend("You need to register the instance you want to use with BackendFactoryRegistry.register() first"));
    }

    /**
     * Constructs a new {@code MockBackendFactory} that always returns the specified {@code backend}.
     *
     * @param backend the back-end returned by {@link #createBackend(URI, ImmutableConfig)}
     */
    public MockBackendFactory(Backend backend) {
        this.backend = backend;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Nonnull
    @Override
    public Backend createBackend(URI uri, ImmutableConfig baseConfig) {
        return backend;
    }

    /**
     * A {@link Config} that creates configuration for the {@link MockBackendFactory}.
     */
    @FactoryBinding(factory = MockBackendFactory.class)
    @ParametersAreNonnullByDefault
    public static final class MockConfig extends BaseConfig<MockConfig> {
    }

    /**
     * A {@link UriFactory} that creates URIs that reference the {@link MockBackendFactory}.
     */
    @FactoryBinding(factory = MockBackendFactory.class)
    @ParametersAreNonnullByDefault
    public static final class MockUriFactory extends AbstractUriFactory {

        @Override
        public boolean supportsLocalUris() {
            return true;
        }

        @Override
        public boolean supportsRemoteUris() {
            return false;
        }
    }
}
