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
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.InvalidTransientResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.IsSetCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.SizeCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.impl.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.hbase.datastore.estores.impl.DirectWriteHBaseResourceEStoreImplTmp;
import fr.inria.atlanmod.neoemf.hbase.datastore.estores.impl.ReadOnlyHBaseResourceEStoreImplTmp;
import fr.inria.atlanmod.neoemf.hbase.resources.HBaseResourceOptionsTmp;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;

import java.io.File;
import java.util.Map;

public class HBasePersistenceBackendFactoryTmp extends AbstractPersistenceBackendFactory {

    public static final String HBASE_BACKEND = "hbase";
    
    @Override
    public PersistenceBackend createTransientBackend() {
        return new HBasePersistenceBackendTmp();
    }

    @Override
    public SearcheableResourceEStore createTransientEStore(PersistentResource resource, PersistenceBackend backend) {
        NeoLogger.warn(
                "NeoEMF/HBase does not provide a transient layer, you must save/load your resource before using it");
        return new InvalidTransientResourceEStoreImpl();
    }

    @Override
    public PersistenceBackend createPersistentBackend(File file, Map<?, ?> options) throws InvalidDataStoreException {
        // TODO Externalise the backend implementation from the HBase EStores.
        return new HBasePersistenceBackendTmp();
    }

    @Override
    protected SearcheableResourceEStore internalCreatePersistentEStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException {
        try {
            if(options.containsKey(HBaseResourceOptionsTmp.OPTIONS_HBASE_READ_ONLY)) {
                if(Boolean.TRUE.equals(options.get(HBaseResourceOptionsTmp.OPTIONS_HBASE_READ_ONLY))) {
                    // Create a read-only EStore
                    return embedInDefaultWrapper(new ReadOnlyHBaseResourceEStoreImplTmp(resource));
                }
                else {
                    // Create a default EStore
                    return embedInDefaultWrapper(new DirectWriteHBaseResourceEStoreImplTmp(resource));
                }
            } else {
                // Create a default EStore
                return embedInDefaultWrapper(new DirectWriteHBaseResourceEStoreImplTmp(resource));
            }
        } catch(Exception e) {
            throw new InvalidDataStoreException(e);
        }
    }

    @Override
    public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
        NeoLogger.warn("NeoEMF/HBase does not support copy backend feature");
    }

    private SearcheableResourceEStore embedInDefaultWrapper(SearcheableResourceEStore eStore) {
        return new IsSetCachingDelegatedEStoreImpl(new SizeCachingDelegatedEStoreImpl(eStore));
    }
}
