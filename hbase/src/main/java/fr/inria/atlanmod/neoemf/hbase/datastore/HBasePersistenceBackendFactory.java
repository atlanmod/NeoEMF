/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.hbase.datastore;

import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.estores.PersistentEStore;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.InvalidTransientResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.IsSetCachingEStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.CachingEStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.impl.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.hbase.datastore.estores.impl.DirectWriteHBaseResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.hbase.datastore.estores.impl.ReadOnlyHBaseResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.hbase.resources.HBaseResourceOptions;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;

import java.io.File;
import java.util.Map;

public class HBasePersistenceBackendFactory extends AbstractPersistenceBackendFactory {
    
    private static PersistenceBackendFactory INSTANCE;

    public static PersistenceBackendFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HBasePersistenceBackendFactory();
        }
        return INSTANCE;
    }
    
    public static final String HBASE_BACKEND = "hbase";
    
    private HBasePersistenceBackendFactory() {
    }
    
    @Override
    public PersistenceBackend createTransientBackend() {
        return new HBasePersistenceBackend();
    }

    @Override
    public PersistentEStore createTransientEStore(PersistentResource resource, PersistenceBackend backend) {
        NeoLogger.warn(
                "NeoEMF/HBase does not provide a transient layer, you must save/load your resource before using it");
        return new InvalidTransientResourceEStoreImpl();
    }

    @Override
    public PersistenceBackend createPersistentBackend(File file, Map<?, ?> options) throws InvalidDataStoreException {
        // TODO Externalise the backend implementation from the HBase EStores.
        return new HBasePersistenceBackend();
    }

    @Override
    protected PersistentEStore internalCreatePersistentEStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException {
        try {
            if(options.containsKey(HBaseResourceOptions.OPTIONS_HBASE_READ_ONLY)) {
                if(Boolean.TRUE.equals(options.get(HBaseResourceOptions.OPTIONS_HBASE_READ_ONLY))) {
                    // Create a read-only EStore
                    return embedInDefaultWrapper(new ReadOnlyHBaseResourceEStoreImpl(resource));
                }
                else {
                    // Create a default EStore
                    return embedInDefaultWrapper(new DirectWriteHBaseResourceEStoreImpl(resource));
                }
            } else {
                // Create a default EStore
                return embedInDefaultWrapper(new DirectWriteHBaseResourceEStoreImpl(resource));
            }
        } catch(Exception e) {
            throw new InvalidDataStoreException(e);
        }
    }

    @Override
    public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
        NeoLogger.warn("NeoEMF/HBase does not support copy backend feature");
    }

    private PersistentEStore embedInDefaultWrapper(PersistentEStore eStore) {
        return new IsSetCachingEStoreDecorator(new CachingEStoreDecorator(eStore));
    }
}
