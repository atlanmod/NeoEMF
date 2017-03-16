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

import fr.inria.atlanmod.neoemf.data.store.AutoSaveStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.FeatureCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.IsSetCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.LoadedObjectCounterStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.LoggingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.ReadOnlyStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.SizeCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.option.CommonResourceOptions;
import fr.inria.atlanmod.neoemf.option.CommonStoreOptions;
import fr.inria.atlanmod.neoemf.option.PersistentResourceOptions;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.log.Level;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

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

        if (checkNotNull(options).containsKey(PersistentResourceOptions.STORE_OPTIONS)) {
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
        List<PersistentStoreOptions> storeOptions = (List<PersistentStoreOptions>) options.get(PersistentResourceOptions.STORE_OPTIONS);

        if (!storeOptions.isEmpty()) {
            if (storeOptions.contains(CommonStoreOptions.CACHE_IS_SET)) {
                store = new IsSetCachingStoreDecorator(store);
            }
            if (storeOptions.contains(CommonStoreOptions.CACHE_STRUCTURAL_FEATURE)) {
                store = new FeatureCachingStoreDecorator(store);
            }
            if (storeOptions.contains(CommonStoreOptions.CACHE_SIZE)) {
                store = new SizeCachingStoreDecorator(store);
            }
            if (storeOptions.contains(CommonStoreOptions.LOG)) {
                if (options.containsKey(CommonResourceOptions.LOG_LEVEL)) {
                    Level level = Level.valueOf(String.valueOf(options.get(CommonResourceOptions.LOG_LEVEL)));
                    store = new LoggingStoreDecorator(store, level);
                }
                else {
                    store = new LoggingStoreDecorator(store);
                }

            }
            if (storeOptions.contains(CommonStoreOptions.COUNT_LOADED_OBJECT)) {
                store = new LoadedObjectCounterStoreDecorator(store);
            }
            if (storeOptions.contains(CommonStoreOptions.READ_ONLY)) {
                store = new ReadOnlyStoreDecorator(store);
            }
            if (storeOptions.contains(CommonStoreOptions.AUTO_SAVE)) {
                if (options.containsKey(CommonResourceOptions.AUTO_SAVE_CHUNK)) {
                    long chunk = Long.parseLong((String) options.get(CommonResourceOptions.AUTO_SAVE_CHUNK));
                    store = new AutoSaveStoreDecorator(store, chunk);
                }
                else {
                    store = new AutoSaveStoreDecorator(store);
                }
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
