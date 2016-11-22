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

package fr.inria.atlanmod.neoemf.hbase.datastore;

import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.impl.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.store.PersistentStore;
import fr.inria.atlanmod.neoemf.datastore.store.impl.CachingStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.store.impl.InvalidStore;
import fr.inria.atlanmod.neoemf.datastore.store.impl.IsSetCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.hbase.datastore.store.impl.DirectWriteHBaseStore;
import fr.inria.atlanmod.neoemf.hbase.datastore.store.impl.ReadOnlyHBaseStore;
import fr.inria.atlanmod.neoemf.hbase.option.HBaseResourceOptions;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class HBasePersistenceBackendFactory extends AbstractPersistenceBackendFactory {

    public static final String NAME = HBasePersistenceBackend.NAME;

    private HBasePersistenceBackendFactory() {
    }

    public static PersistenceBackendFactory getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    protected PersistentStore createSpecificPersistentStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException {
        try {
            if (options.containsKey(HBaseResourceOptions.READ_ONLY) && Objects.equals(Boolean.TRUE, options.get(HBaseResourceOptions.READ_ONLY))) {
                // Create a read-only EStore
                return embedInDefaultWrapper(new ReadOnlyHBaseStore(resource));
            }
            else {
                // Create a default EStore
                return embedInDefaultWrapper(new DirectWriteHBaseStore(resource));
            }
        }
        catch (IOException e) {
            throw new InvalidDataStoreException(e);
        }
    }

    @Override
    public PersistenceBackend createTransientBackend() {
        return new HBasePersistenceBackend();
    }

    @Override
    public PersistenceBackend createPersistentBackend(File file, Map<?, ?> options) {
        // TODO Externalise the backend implementation from the HBase EStores.
        return new HBasePersistenceBackend();
    }

    @Override
    public PersistentStore createTransientStore(PersistentResource resource, PersistenceBackend backend) {
        return new InvalidStore();
    }

    @Override
    public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
        NeoLogger.warn("NeoEMF/HBase does not support copy backend feature");
    }

    private PersistentStore embedInDefaultWrapper(PersistentStore eStore) {
        return new IsSetCachingStoreDecorator(new CachingStoreDecorator(eStore));
    }

    private static class Holder {

        private static final PersistenceBackendFactory INSTANCE = new HBasePersistenceBackendFactory();
    }
}
