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

import fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.FeatureCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.IsSetCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.LoadedObjectCounterStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.LoggingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.data.store.ReadOnlyStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.SizeCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.option.CommonResourceOptions;
import fr.inria.atlanmod.neoemf.option.CommonStoreOptions;
import fr.inria.atlanmod.neoemf.option.PersistentResourceOptions;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.logging.Level;
import fr.inria.atlanmod.neoemf.util.logging.Log;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An abstract {@link PersistenceBackendFactory} that processes common store options and manages the configuration.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractPersistenceBackendFactory implements PersistenceBackendFactory {

    /**
     * Constructs a new {@code AbstractPersistenceBackendFactory}.
     */
    protected AbstractPersistenceBackendFactory() {
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
    protected static List<PersistentStoreOptions> getStoreOptions(@Nullable Map<?, ?> options) {
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

    /**
     * Returns the literal description of the created {@link PersistenceBackend}.
     *
     * @return the literal description of the created {@link PersistenceBackend}
     */
    protected abstract String getName();

    @Nonnull
    @Override
    public PersistentStore createTransientStore(PersistentResource resource, PersistenceBackend backend) {
        return new DirectWriteStore(backend, resource);
    }

    @Nonnull
    @Override
    public PersistentStore createPersistentStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException {
        PersistentStore store = new DirectWriteStore(backend, resource);

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
            if (storeOptions.contains(CommonStoreOptions.AUTOCOMMIT)) {
                if (options.containsKey(CommonResourceOptions.AUTOCOMMIT_CHUNK)) {
                    long autoCommitChunk = Long.valueOf((String) options.get(CommonResourceOptions.AUTOCOMMIT_CHUNK));
                    store = new AutocommitStoreDecorator(store, autoCommitChunk);
                }
                else {
                    store = new AutocommitStoreDecorator(store);
                }
            }
        }
        return store;
    }

    /**
     * Creates and saves the NeoEMF configuration.
     * <p>
     * The configuration is stored as a properties file beside a database in order to identify a {@link
     * PersistenceBackend}.
     *
     * @param directory the directory where the configuration must be stored
     *
     * @throws InvalidDataStoreException if the configuration cannot be created in the {@code directory}
     */
    protected void processGlobalConfiguration(File directory) throws InvalidDataStoreException {
        Path path = Paths.get(directory.getAbsolutePath()).resolve(CONFIG_FILE);
        PersistenceConfiguration configuration = PersistenceConfiguration.load(path.toFile());

        if (!configuration.containsKey(BACKEND_PROPERTY)) {
            configuration.setProperty(BACKEND_PROPERTY, getName());
        }

        configuration.save();
    }
}
