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

package fr.inria.atlanmod.neoemf.datastore;

import fr.inria.atlanmod.neoemf.datastore.store.FeatureCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.store.IsSetCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.store.LoadedObjectCounterStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.store.LoggingStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.store.PersistentStore;
import fr.inria.atlanmod.neoemf.datastore.store.SizeCachingStoreDecorator;
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
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

public abstract class AbstractPersistenceBackendFactory implements PersistenceBackendFactory {

    /**
     * Returns a list of store options from the given {@code options}.
     */
    @SuppressWarnings("unchecked")
    protected static List<PersistentStoreOptions> getStoreOptions(Map<?, ?> options) throws InvalidOptionsException {
        Object storeOptions = options.get(PersistentResourceOptions.STORE_OPTIONS);
        try {
            return (List<PersistentStoreOptions>) storeOptions;
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
        List<PersistentStoreOptions> storeOptions = getStoreOptions(options);

        if (nonNull(storeOptions) && !storeOptions.isEmpty()) {
            if (storeOptions.contains(CommonStoreOptions.CACHE_IS_SET)) {
                eStore = new IsSetCachingStoreDecorator(eStore);
            }
            if (storeOptions.contains(CommonStoreOptions.CACHE_STRUCTURAL_FEATURE)) {
                eStore = new FeatureCachingStoreDecorator(eStore);
            }
            if (storeOptions.contains(CommonStoreOptions.CACHE_SIZE)) {
                eStore = new SizeCachingStoreDecorator(eStore);
            }
            if (storeOptions.contains(CommonStoreOptions.LOG)) {
                eStore = new LoggingStoreDecorator(eStore);
            }
            if (storeOptions.contains(CommonStoreOptions.COUNT_LOADED_OBJECT)) {
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
