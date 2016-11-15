/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.datastore.impl;

import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.InvalidOptionsException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.store.PersistentStore;
import fr.inria.atlanmod.neoemf.datastore.store.impl.CachingStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.store.impl.FeatureCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.store.impl.IsSetCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.store.impl.LoadedObjectCounterStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.store.impl.LoggingStoreDecorator;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceOptions;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

public abstract class AbstractPersistenceBackendFactory implements PersistenceBackendFactory {

    /**
     * Returns a list of store options from the given {@code options}.
     */
    @SuppressWarnings("unchecked")
    protected static List<PersistentResourceOptions.StoreOption> getStoreOptions(Map<?, ?> options) throws InvalidOptionsException {
        Object storeOptions = options.get(PersistentResourceOptions.STORE_OPTIONS);
        try {
            return (List<PersistentResourceOptions.StoreOption>) storeOptions;
        }
        catch (ClassCastException e) {
            throw new InvalidOptionsException("STORE_OPTIONS must be a List<PersistentResourceOptions.StoreOption>");
        }
    }

    /**
     * Returns a literal description of the created persistence backend.
     */
    protected abstract String getName();

    @Override
    public PersistentStore createPersistentStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException, InvalidOptionsException {
        PersistentStore eStore = createSpecificPersistentStore(resource, backend, options);
        List<PersistentResourceOptions.StoreOption> storeOptions = getStoreOptions(options);

        if (!isNull(storeOptions) && !storeOptions.isEmpty()) {
            if (storeOptions.contains(PersistentResourceOptions.EStoreOption.CACHE_IS_SET)) {
                eStore = new IsSetCachingStoreDecorator(eStore);
            }
            if (storeOptions.contains(PersistentResourceOptions.EStoreOption.CACHE_STRUCTURAL_FEATURE)) {
                eStore = new FeatureCachingStoreDecorator(eStore);
            }
            if (storeOptions.contains(PersistentResourceOptions.EStoreOption.CACHE_SIZE)) {
                eStore = new CachingStoreDecorator(eStore);
            }
            if (storeOptions.contains(PersistentResourceOptions.EStoreOption.LOG)) {
                eStore = new LoggingStoreDecorator(eStore);
            }
            if (storeOptions.contains(PersistentResourceOptions.EStoreOption.COUNT_LOADED_OBJECT)) {
                eStore = new LoadedObjectCounterStoreDecorator(eStore);
            }
        }
        return eStore;
    }

    protected abstract PersistentStore createSpecificPersistentStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException, InvalidOptionsException;

    /**
     * Creates and saves the NeoEMF configuration.
     *
     * @param directory the directory where the configuration must be stored.
     */
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
