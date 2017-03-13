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

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.InvalidTransientBackend;
import fr.inria.atlanmod.neoemf.data.hbase.option.HBaseOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;
import fr.inria.atlanmod.neoemf.data.store.InvalidStore;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.eclipse.emf.common.util.URI;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static java.util.Objects.isNull;

/**
 * A factory that creates instances of {@link HBaseBackend}.
 * <p>
 * This class only creates persistent databases that can be configured using {@link PersistentResource#save(Map)} and
 * {@link PersistentResource#load(Map)} options maps.
 * <p>
 * <b>Important Note:</b> Transient back-ends can be instantiated using this factory, but they will be handed as
 * persistent ones. This is a limitation that will be solved in next releases. To avoid any consistency issue we
 * recommend every HBase resource right after their creation, ensuring the resource is using a persistent back-end.
 *
 * @see PersistentResource
 * @see HBaseBackend
 * @see HBaseOptionsBuilder
 */
@ParametersAreNonnullByDefault
public class HBaseBackendFactory extends AbstractBackendFactory {

    /**
     * The literal description of the factory.
     */
    public static final String NAME = HBaseBackend.NAME;

    /**
     * The property to define the ZooKeeper host.
     */
    protected static final String HOST_PROPERTY = "hbase.zookeeper.quorum";

    /**
     * The property to define the ZooKeeper port.
     */
    protected static final String PORT_PROPERTY = "hbase.zookeeper.property.clientPort";

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
    public static BackendFactory getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Nonnull
    @Override
    public Store createTransientStore(PersistentResource resource, Backend backend) {
        return new InvalidStore();
    }

    @Nonnull
    @Override
    public Backend createTransientBackend() {
        return new InvalidTransientBackend();
    }

    @Nonnull
    @Override
    public Backend createPersistentBackend(URI uri, Map<String, Object> options) {
        checkArgument(uri.isHierarchical(), "NeoEMF/HBase only supports hierarchical URIs");

        return new HBaseBackendArraysStrings(createTable(uri));
    }

    /**
     * Retrieves or creates or a new {@link Table} with the given {@code uri}.
     *
     * @param uri the {@link URI} to get information about it
     *
     * @return a new {@link Table}
     */
    private Table createTable(URI uri) {
        try {
            Configuration configuration = HBaseConfiguration.create();
            configuration.set(HOST_PROPERTY, uri.host());
            configuration.set(PORT_PROPERTY, isNull(uri.port()) ? "2181" : uri.port());

            Connection connection = ConnectionFactory.createConnection(configuration);
            Admin admin = connection.getAdmin();

            TableName tableName = TableName.valueOf(HBaseURI.format(uri));

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
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final BackendFactory INSTANCE = new HBaseBackendFactory();
    }
}
