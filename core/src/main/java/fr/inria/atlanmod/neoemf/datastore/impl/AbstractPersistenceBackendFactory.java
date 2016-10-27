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
import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions.StoreOption;

import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

public abstract class AbstractPersistenceBackendFactory implements PersistenceBackendFactory {

    /**
     * Returns a list of store options from the given {@code options}.
     */
    protected static List<StoreOption> storeOptionsFrom(Map<?, ?> options) {
        return (List<StoreOption>)options.get(PersistentResourceOptions.STORE_OPTIONS);
    }

    @Override
    public PersistentEStore createPersistentEStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException {
        PersistentEStore eStore = internalCreatePersistentEStore(resource, backend, options);
        List<StoreOption> storeOptions = storeOptionsFrom(options);

        if (!isNull(storeOptions) && !storeOptions.isEmpty()) {
            if (storeOptions.contains(PersistentResourceOptions.EStoreOption.IS_SET_CACHING)) {
                eStore = new IsSetCachingEStoreDecorator(eStore);
            }
            if (storeOptions.contains(PersistentResourceOptions.EStoreOption.ESTRUCUTRALFEATURE_CACHING)) {
                eStore = new FeatureCachingEStoreDecorator(eStore);
            }
            if (storeOptions.contains(PersistentResourceOptions.EStoreOption.SIZE_CACHING)) {
                eStore = new CachingEStoreDecorator(eStore);
            }
            if (storeOptions.contains(PersistentResourceOptions.EStoreOption.LOGGING)) {
                eStore = new LoggingEStoreDecorator(eStore);
            }
            if (storeOptions.contains(PersistentResourceOptions.EStoreOption.LOADED_OBJECT_COUNTER_LOGGING)) {
                eStore = new LoadedObjectCounterEStoreDecorator(eStore);
            }
        }
        return eStore;
    }

    protected abstract PersistentEStore internalCreatePersistentEStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException;
}
