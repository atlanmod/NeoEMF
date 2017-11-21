/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.im.config;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.data.im.InMemoryBackendFactory;

import java.io.IOException;
import java.nio.file.Path;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.config.Config} that creates specific configuration for an {@link
 * fr.inria.atlanmod.neoemf.data.im.InMemoryBackend} instance.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 */
@FactoryBinding(InMemoryBackendFactory.class)
@ParametersAreNonnullByDefault
public class InMemoryConfig extends BaseConfig<InMemoryConfig> {

    /**
     * Constructs a new {@code BerkeleyDbConfig}.
     */
    protected InMemoryConfig() {
        // Don't set a default mapping for a multi-mapping configuration.
    }

    /**
     * Constructs a new {@code InMemoryConfig} instance with default settings.
     *
     * @return a new configuration
     */
    @Nonnull
    public static InMemoryConfig newConfig() {
        return new InMemoryConfig();
    }

    @Override
    public void save(Path directory) throws IOException {
        throw new UnsupportedOperationException("An in-memory backend cannot be stored locally");
    }
}
