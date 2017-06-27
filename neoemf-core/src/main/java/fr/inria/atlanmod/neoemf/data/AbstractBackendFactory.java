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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.bind.Singleton;
import fr.inria.atlanmod.neoemf.data.mapper.AbstractMapperFactory;
import fr.inria.atlanmod.neoemf.option.PersistentResourceOptions;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkState;

/**
 * An abstract {@link BackendFactory} that processes common store options and manages the configuration.
 */
@Singleton
@ParametersAreNonnullByDefault
public abstract class AbstractBackendFactory extends AbstractMapperFactory implements BackendFactory {

    /**
     * Constructs a new {@code AbstractBackendFactory}.
     */
    protected AbstractBackendFactory() {
    }

    @Nonnull
    @Override
    public Backend createTransientBackend() {
        if (supportsTransient()) {
            return new DefaultTransientBackend();
        }
        else {
            return new InvalidTransientBackend();
        }
    }

    /**
     * Creates and saves the NeoEMF configuration.
     * <p>
     * The configuration is stored as a properties file beside a database in order to identify a {@link
     * PersistentBackend}.
     *
     * @param baseDirectory the directory where the configuration must be stored
     * @param mapping       the used mapping
     *
     * @throws InvalidBackendException if the configuration cannot be created in the {@code baseDirectory}
     */
    protected final void processGlobalConfiguration(Path baseDirectory, String mapping) {
        Path path = baseDirectory.resolve(CONFIG_FILE);

        BackendConfiguration configuration;

        try {
            configuration = BackendConfiguration.load(path);
        }
        catch (IOException e) {
            throw new InvalidBackendException(e);
        }

        configuration.setIfAbsent(BACKEND_PROPERTY, name());
        configuration.setIfAbsent(FACTORY_PROPERTY, getClass().getName());

        if (configuration.has(PersistentResourceOptions.MAPPING)) {
            String savedMapping = configuration.get(PersistentResourceOptions.MAPPING);
            checkState(Objects.equals(mapping, savedMapping),
                    "The back-end is mapped with %s (but actual is %s)", savedMapping, mapping);
        }
        else {
            configuration.set(PersistentResourceOptions.MAPPING, mapping);
        }

        try {
            configuration.save();
        }
        catch (IOException e) {
            throw new InvalidBackendException(e);
        }
    }
}
