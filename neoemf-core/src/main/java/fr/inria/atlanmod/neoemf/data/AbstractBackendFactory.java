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

import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.option.PersistentResourceOptions;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * An abstract {@link BackendFactory} that processes common store options and manages the configuration.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractBackendFactory implements BackendFactory {

    /**
     * Constructs a new {@code AbstractBackendFactory}.
     */
    protected AbstractBackendFactory() {
    }

    /**
     * Retrieves the mapping to use from the given {@code options}.
     *
     * @param options the options defining the mapping to use
     *
     * @return an {@link Optional} containing the name of the mapping to use, or {@link Optional#empty()} if the mapping
     * is not defined.
     */
    protected static Optional<String> mapping(Map<String, Object> options) {
        Object mapping = options.get(PersistentResourceOptions.MAPPING);

        if (nonNull(mapping)) {
            return Optional.of(mapping.toString());
        }
        else {
            return Optional.empty();
        }
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

    @Nonnull
    @Override
    public Store createStore(Backend backend, PersistentResource resource, Map<String, Object> options) {
        Store store = new DirectWriteStore(backend, resource);

        if (checkNotNull(options).containsKey(PersistentResourceOptions.STORES)) {
            store = decorateStore(store, options);
        }

        return store;
    }

    /**
     * Decorates a {@code store} with other stores, as specified by the {@code options}.
     *
     * @param store   the store to decorate
     * @param options the options defining the stores to use
     *
     * @return the decorated {@code store}
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    private Store decorateStore(Store store, Map<String, Object> options) {
        List<PersistentStoreOptions> storeOptions = (List<PersistentStoreOptions>) options.get(PersistentResourceOptions.STORES);

        if (!storeOptions.isEmpty()) {
            for (PersistentStoreOptions opt : storeOptions.stream().sorted().collect(Collectors.toList())) {
                List<Object> parameters = opt.parameters().stream()
                        .filter(options::containsKey)
                        .map(options::get)
                        .collect(Collectors.toList());

                parameters.add(0, store);

                store = opt.newInstance(parameters.toArray());
            }
        }

        return store;
    }

    /**
     * Creates and saves the NeoEMF configuration.
     * <p>
     * The configuration is stored as a properties file beside a database in order to identify a {@link
     * PersistentBackend}.
     *
     * @param directory the directory where the configuration must be stored
     *
     * @throws InvalidDataStoreException if the configuration cannot be created in the {@code directory}
     */
    protected void processGlobalConfiguration(File directory) {
        Path path = Paths.get(directory.getAbsolutePath()).resolve(CONFIG_FILE);
        Configuration configuration = Configuration.load(path.toFile());

        if (!configuration.containsKey(BACKEND_PROPERTY)) {
            configuration.setProperty(BACKEND_PROPERTY, name());
        }

        configuration.save();
    }
}
