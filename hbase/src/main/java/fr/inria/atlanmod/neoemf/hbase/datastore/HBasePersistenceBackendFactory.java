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
import fr.inria.atlanmod.neoemf.datastore.estores.PersistentEStore;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.CachingEStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.InvalidTransientResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.IsSetCachingEStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.impl.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.hbase.datastore.estores.impl.DirectWriteHBaseResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.hbase.datastore.estores.impl.ReadOnlyHBaseResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;

import java.io.File;
import java.util.Map;

import static fr.inria.atlanmod.neoemf.hbase.resources.HBaseResourceOptions.OPTIONS_HBASE_READ_ONLY;
import static java.util.Objects.isNull;

public class HBasePersistenceBackendFactory extends AbstractPersistenceBackendFactory {

    public static final String HBASE_BACKEND = "hbase";

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
        return HBASE_BACKEND;
    }

    @Override
    protected PersistentEStore internalCreatePersistentEStore(PersistentResource resource, PersistenceBackend backend, Map<?, ?> options) throws InvalidDataStoreException {
        try {
            if (options.containsKey(OPTIONS_HBASE_READ_ONLY)) {
                if (Boolean.TRUE.equals(options.get(OPTIONS_HBASE_READ_ONLY))) {
                    // Create a read-only EStore
                    return embedInDefaultWrapper(new ReadOnlyHBaseResourceEStoreImpl(resource));
                }
                else {
                    // Create a default EStore
                    return embedInDefaultWrapper(new DirectWriteHBaseResourceEStoreImpl(resource));
                }
            }
            else {
                // Create a default EStore
                return embedInDefaultWrapper(new DirectWriteHBaseResourceEStoreImpl(resource));
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
        return new InvalidTransientResourceEStoreImpl();
    }

    @Override
    public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
        NeoLogger.warn("NeoEMF/HBase does not support copy backend feature");
    }

    private PersistentEStore embedInDefaultWrapper(PersistentEStore eStore) {
        return new IsSetCachingEStoreDecorator(new CachingEStoreDecorator(eStore));
    }
}
