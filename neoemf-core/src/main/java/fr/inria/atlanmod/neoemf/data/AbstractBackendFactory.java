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
import fr.inria.atlanmod.neoemf.data.store.InvalidStore;
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
import fr.inria.atlanmod.neoemf.util.log.Log;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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

    /**
     * Parses the store options from the given {@code options}.
     * <p>
     * The store options must be a {@code List<PersistentStoreOptions>} registered with the
     * {@link PersistentResourceOptions#STORE_OPTIONS} key.
     *
     * @param options the options
     *
     * @return a immutable list of store options
     */
    @SuppressWarnings("unchecked")
    @Nonnull
    protected static List<PersistentStoreOptions> getStoreOptions(@Nullable Map<String, Object> options) {
        List<PersistentStoreOptions> storeOptions;

        try {
            try {
                Object storeOptionsRaw = checkNotNull(options).get(PersistentResourceOptions.STORE_OPTIONS);
                storeOptions = (List<PersistentStoreOptions>) storeOptionsRaw;

                // Use an immutable list to avoid later modification
                storeOptions = Collections.unmodifiableList(storeOptions);
            }
            catch (ClassCastException e) {
                Log.warn("STORE_OPTIONS must be a List<PersistentResourceOptions.StoreOption>. Consider that there is no option.");
                throw new NullPointerException();
            }
        }
        catch (NullPointerException e) {
            storeOptions = Collections.emptyList();
        }

        Log.debug("StoreOptions: " + storeOptions);

        return storeOptions;
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
    public Store createTransientStore(PersistentResource resource, Backend backend) {
        if (supportsTransient()) {
            return new DirectWriteStore(backend, resource);
        }
        else {
            return new InvalidStore();
        }
    }

    @Nonnull
    @Override
    public Store createPersistentStore(PersistentResource resource, Backend backend, Map<String, Object> options) {
        Store store = new DirectWriteStore(backend, resource);

        List<PersistentStoreOptions> storeOptions = getStoreOptions(options);

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
                if (options.containsKey(CommonResourceOptions.LOGGING_LEVEL)) {
                    Level level = Level.valueOf(String.valueOf(options.get(CommonResourceOptions.LOGGING_LEVEL)));
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
                    long chunk = Long.valueOf((String) options.get(CommonResourceOptions.AUTO_SAVE_CHUNK));
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
            configuration.setProperty(BACKEND_PROPERTY, getName());
        }

        configuration.save();
    }
}
