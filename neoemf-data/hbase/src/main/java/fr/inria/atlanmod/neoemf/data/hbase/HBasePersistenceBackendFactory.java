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
import fr.inria.atlanmod.neoemf.data.hbase.option.HBaseOptionsBuilder;
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

/**
 * A factory that creates instances of {@link HBasePersistenceBackend}.
 * <p>
 * This class only creates persistent databases that can be configured using
 * {@link PersistentResource#save(Map)} and {@link PersistentResource#load(Map)}
 * options maps.
 * <p>
 * Note that transient back-ends can be instantiated using this factory, but they will
 * be handed as persistent ones. This is a limitation that will be solved in next releases.
 * To avoid any consistency issue we recommend every HBase resource right after their creation,
 * ensuring the resource is using a persistent back-end.
 *
 * @see PersistentResource
 * @see HBasePersistenceBackend
 * @see HBaseOptionsBuilder
 * @see HBaseResourceOptions
 */
public class HBasePersistenceBackendFactory extends AbstractPersistenceBackendFactory {

    /**
     * The literal description of the factory.
     */
    public static final String NAME = HBasePersistenceBackend.NAME;

    /**
     * Constructs a new {@code HBasePersistenceBackendFactory}.
     */
    private HBasePersistenceBackendFactory() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
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

    /**
     * Wraps the given {@code store} in the default {@link PersistentStore}.
     *
     * @param store the store to wrap
     *
     * @return the {@code store} wrapped in another
     */
    private PersistentStore embedInDefaultWrapper(PersistentStore store) {
        return new IsSetCachingStoreDecorator(new SizeCachingStoreDecorator(store));
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static class Holder {

        /**
         * The instance of the outer class.
         */
        private static final PersistenceBackendFactory INSTANCE = new HBasePersistenceBackendFactory();
    }
}
