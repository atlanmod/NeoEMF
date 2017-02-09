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

import fr.inria.atlanmod.neoemf.annotations.VisibleForTesting;
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.option.HBaseOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.hbase.option.HBaseResourceOptions;
import fr.inria.atlanmod.neoemf.data.hbase.store.DirectWriteHBaseStore;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;
import fr.inria.atlanmod.neoemf.data.store.InvalidStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Nonnull;

import static java.util.Objects.isNull;

/**
 * A factory that creates instances of {@link HBaseBackendArrays}.
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
 * @see HBaseBackend
 * @see HBaseOptionsBuilder
 * @see HBaseResourceOptions
 */
public class HBaseBackendFactory extends AbstractPersistenceBackendFactory {

    /**
     * The literal description of the factory.
     */
    public static final String NAME = HBaseBackend.NAME;

    /**
     * Constructs a new {@code HBaseBackendFactory}.
     */
    protected HBaseBackendFactory() {
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
        return new DirectWriteHBaseStore(resource, createTable(resource));
    }

    @Override
    public PersistenceBackend createTransientBackend() {
        return new HBaseBackendArrays(null);
    }

    @Override
    public PersistenceBackend createPersistentBackend(File directory, Map<?, ?> options) {
        // TODO Externalise the back-end implementation from the HBase EStores.
        return new HBaseBackendArrays(null);
    }

    @Override
    public PersistentStore createTransientStore(PersistentResource resource, PersistenceBackend backend) {
        return new InvalidStore();
    }

    @Override
    public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
        NeoLogger.warn("NeoEMF/HBase does not support copy backend feature");
    }

    @VisibleForTesting
    protected Table createTable(Resource resource) {
        try {
            Configuration configuration = HBaseConfiguration.create();
            configuration.set("hbase.zookeeper.quorum", resource.getURI().host());
            configuration.set("hbase.zookeeper.property.clientPort", isNull(resource.getURI().port()) ? "2181" : resource.getURI().port());

            TableName tableName = TableName.valueOf(HBaseURI.format(resource.getURI()));

            Connection connection = ConnectionFactory.createConnection(configuration);
            Admin admin = connection.getAdmin();

            // Initialize
            if (!admin.tableExists(tableName)) {
                // FIXME Don't initialize this table in READ ONLY.
                HTableDescriptor desc = new HTableDescriptor(tableName);
                HColumnDescriptor propertyFamily = new HColumnDescriptor(AbstractHBaseBackend.PROPERTY_FAMILY);
                HColumnDescriptor typeFamily = new HColumnDescriptor(AbstractHBaseBackend.TYPE_FAMILY);
                HColumnDescriptor containmentFamily = new HColumnDescriptor(AbstractHBaseBackend.CONTAINMENT_FAMILY);
                desc.addFamily(propertyFamily);
                desc.addFamily(typeFamily);
                desc.addFamily(containmentFamily);
                admin.createTable(desc);
            }

            return connection.getTable(tableName);
        }
        catch (IOException e) {
            throw new InvalidDataStoreException(e);
        }
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static class Holder {

        /**
         * The instance of the outer class.
         */
        private static final PersistenceBackendFactory INSTANCE = new HBaseBackendFactory();
    }
}
