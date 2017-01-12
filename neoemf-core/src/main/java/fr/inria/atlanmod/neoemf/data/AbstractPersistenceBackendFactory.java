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

import fr.inria.atlanmod.neoemf.data.store.FeatureCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.IsSetCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.LoadedObjectCounterStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.LoggingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.data.store.SizeCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.option.CommonStoreOptions;
import fr.inria.atlanmod.neoemf.option.PersistentResourceOptions;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The abstract implementation of a {@link PersistenceBackendFactory}.
 */
public abstract class AbstractPersistenceBackendFactory implements PersistenceBackendFactory {

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
                NeoLogger.warn("STORE_OPTIONS must be a List<PersistentResourceOptions.StoreOption>. Consider that there is no option.");
                throw new NullPointerException();
            }
        }
        catch (NullPointerException e) {
            storeOptions = Collections.emptyList();
        }

        NeoLogger.debug("StoreOptions: " + storeOptions);

        return storeOptions;
    }

    /**
     * Returns the literal description of the created persistence back-end.
     */
    protected abstract String getName();

    @Override
    public PersistentStore createPersistentStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException {
        PersistentStore store = createSpecificPersistentStore(resource, backend, options);
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
                store = new LoggingStoreDecorator(store);
            }
            if (storeOptions.contains(CommonStoreOptions.COUNT_LOADED_OBJECT)) {
                store = new LoadedObjectCounterStoreDecorator(store);
            }
        }
        return store;
    }

    /**
     * Creates a {@link PersistentStore store} between the given {@code resource} and the given {@code backend}
     * according to the given {@code options}.
     * <p>
     * The returned {@link PersistentStore} may be a succession of several {@link PersistentStore}.
     * <p>
     * Contrary to {@link #createPersistentStore(PersistentResource, PersistenceBackend, Map)}, this method is
     * associated to a specific {@link PersistenceBackend back-end}, and called before creating global
     * {@link PersistentStore stores}.
     *
     * @param resource the resource
     * @param backend  the back-end
     * @param options  the options
     *
     * @return the newly created persistent store
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in {@code options}, or if an option is
     *                                   missing
     */
    protected abstract PersistentStore createSpecificPersistentStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException;

    /**
     * Creates and saves the NeoEMF configuration.
     * <p>
     * The configuration is stored beside a data store in order to identify a {@link PersistenceBackend}.
     *
     * @param directory the directory where the configuration must be stored.
     */
    // TODO Create a new PersistenceConfiguration to manage the configuration of back-ends
    protected void processGlobalConfiguration(File directory) throws InvalidDataStoreException {
        PropertiesConfiguration configuration;
        Path path = Paths.get(directory.getAbsolutePath()).resolve(CONFIG_FILE);

        try {
            configuration = new PropertiesConfiguration(path.toFile());
        }
        catch (ConfigurationException e) {
            throw new InvalidDataStoreException(e);
        }

        if (!configuration.containsKey(BACKEND_PROPERTY)) {
            configuration.setProperty(BACKEND_PROPERTY, getName());
        }

        try {
            configuration.save();
            NeoLogger.debug("Configuration stored at " + path);
        }
        catch (ConfigurationException e) {
            /*
             * Unable to save configuration.
             * Supposedly it's a minor error, so we log it without rising an exception.
             */
            NeoLogger.warn(e);
        }
    }
}
