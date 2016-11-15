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
import fr.inria.atlanmod.neoemf.datastore.store.PersistentEStore;
import fr.inria.atlanmod.neoemf.datastore.store.impl.CachingEStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.store.impl.InvalidTransientEStore;
import fr.inria.atlanmod.neoemf.datastore.store.impl.IsSetCachingEStoreDecorator;
import fr.inria.atlanmod.neoemf.hbase.datastore.store.impl.DirectWriteHBaseEStore;
import fr.inria.atlanmod.neoemf.hbase.datastore.store.impl.ReadOnlyHBaseEStore;
import fr.inria.atlanmod.neoemf.hbase.resource.HBaseResourceOptions;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import java.io.File;
import java.util.Map;

import static java.util.Objects.isNull;

public class HBasePersistenceBackendFactory extends AbstractPersistenceBackendFactory {

    public static final String NAME = "hbase";

    private static PersistenceBackendFactory INSTANCE;

    private HBasePersistenceBackendFactory() {
    }

    public static PersistenceBackendFactory getInstance() {
        if (isNull(INSTANCE)) {
            INSTANCE = new HBasePersistenceBackendFactory();
        }
        return INSTANCE;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    protected PersistentEStore internalCreatePersistentEStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException {
        try {
            if (options.containsKey(HBaseResourceOptions.READ_ONLY)) {
                if (Boolean.TRUE.equals(options.get(HBaseResourceOptions.READ_ONLY))) {
                    // Create a read-only EStore
                    return embedInDefaultWrapper(new ReadOnlyHBaseEStore(resource));
                }
                else {
                    // Create a default EStore
                    return embedInDefaultWrapper(new DirectWriteHBaseEStore(resource));
                }
            }
            else {
                // Create a default EStore
                return embedInDefaultWrapper(new DirectWriteHBaseEStore(resource));
            }
        }
        catch (Exception e) {
            throw new InvalidDataStoreException(e);
        }
    }

    @Override
    public PersistenceBackend createTransientBackend() {
        return new HBasePersistenceBackend();
    }

    @Override
    public PersistenceBackend createPersistentBackend(File file, Map<?, ?> options) throws InvalidDataStoreException {
        // TODO Externalise the backend implementation from the HBase EStores.
        return new HBasePersistenceBackend();
    }

    @Override
    public PersistentEStore createTransientEStore(PersistentResource resource, PersistenceBackend backend) {
        return new InvalidTransientEStore();
    }

    @Override
    public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
        NeoLogger.warn("NeoEMF/HBase does not support copy backend feature");
    }

    private PersistentEStore embedInDefaultWrapper(PersistentEStore eStore) {
        return new IsSetCachingEStoreDecorator(new CachingEStoreDecorator(eStore));
    }
}
