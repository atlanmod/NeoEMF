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
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.estores.PersistentEStore;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.CachingEStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.FeatureCachingEStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.IsSetCachingEStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.LoadedObjectCounterEStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.LoggingEStoreDecorator;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions.EStoreOption.ESTRUCUTRALFEATURE_CACHING;
import static fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions.EStoreOption.IS_SET_CACHING;
import static fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions.EStoreOption.LOADED_OBJECT_COUNTER_LOGGING;
import static fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions.EStoreOption.LOGGING;
import static fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions.EStoreOption.SIZE_CACHING;
import static fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions.STORE_OPTIONS;
import static fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions.StoreOption;
import static java.util.Objects.isNull;

public abstract class AbstractPersistenceBackendFactory implements PersistenceBackendFactory {

    /**
     * Returns a list of store options from the given {@code options}.
     */
    protected static List<StoreOption> storeOptionsFrom(Map<?, ?> options) {
        return (List<StoreOption>) options.get(STORE_OPTIONS);
    }

    /**
     * Returns a literal description of the created persistence backend.
     */
    protected abstract String getName();

    @Override
    public PersistentEStore createPersistentEStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException {
        PersistentEStore eStore = internalCreatePersistentEStore(resource, backend, options);
        List<StoreOption> storeOptions = storeOptionsFrom(options);

        if (!isNull(storeOptions) && !storeOptions.isEmpty()) {
            if (storeOptions.contains(IS_SET_CACHING)) {
                eStore = new IsSetCachingEStoreDecorator(eStore);
            }
            if (storeOptions.contains(ESTRUCUTRALFEATURE_CACHING)) {
                eStore = new FeatureCachingEStoreDecorator(eStore);
            }
            if (storeOptions.contains(SIZE_CACHING)) {
                eStore = new CachingEStoreDecorator(eStore);
            }
            if (storeOptions.contains(LOGGING)) {
                eStore = new LoggingEStoreDecorator(eStore);
            }
            if (storeOptions.contains(LOADED_OBJECT_COUNTER_LOGGING)) {
                eStore = new LoadedObjectCounterEStoreDecorator(eStore);
            }
        }
        return eStore;
    }

    protected abstract PersistentEStore internalCreatePersistentEStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException;

    /**
     * Creates and saves the NeoEMF configuration.
     *
     * @param directory the directory where the configuration must be stored.
     */
    protected void processGlobalConfiguration(File directory) throws InvalidDataStoreException {
        PropertiesConfiguration configuration;
        Path path = Paths.get(directory.getAbsolutePath()).resolve(NEO_CONFIG_FILE);

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
