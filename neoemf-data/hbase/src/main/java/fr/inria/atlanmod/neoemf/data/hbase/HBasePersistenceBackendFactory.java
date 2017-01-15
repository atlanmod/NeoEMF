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

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.option.HBaseResourceOptions;
import fr.inria.atlanmod.neoemf.data.hbase.store.DirectWriteHBaseStore;
import fr.inria.atlanmod.neoemf.data.hbase.store.ReadOnlyHBaseStore;
import fr.inria.atlanmod.neoemf.data.store.InvalidStore;
import fr.inria.atlanmod.neoemf.data.store.IsSetCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.data.store.SizeCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;

public class HBasePersistenceBackendFactory extends AbstractPersistenceBackendFactory {

    public static final String NAME = HBasePersistenceBackend.NAME;

    private HBasePersistenceBackendFactory() {
    }

    /**
     * Returns the instance of this class.
     */
    @Nonnull
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
    public PersistenceBackend createPersistentBackend(File directory, Map<?, ?> options) {
        // TODO Externalise the back-end implementation from the HBase EStores.
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

    private PersistentStore embedInDefaultWrapper(PersistentStore store) {
        return new IsSetCachingStoreDecorator(new SizeCachingStoreDecorator(store));
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static class Holder {

        private static final PersistenceBackendFactory INSTANCE = new HBasePersistenceBackendFactory();
    }
}
