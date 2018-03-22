/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.config.HBaseConfig;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;

import java.net.URL;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.BackendFactory} that creates {@link HBaseBackend} instances.
 * <p>
 * <b>IMPORTANT:</b> Transient back-ends can be instantiated using this factory, but they will be handed as persistent
 * ones. This is a limitation that will be solved in next releases. To avoid any consistency issue we recommend every
 * HBase resource right after their creation, ensuring the resource is using a persistent back-end.
 */
@ParametersAreNonnullByDefault
public class HBaseBackendFactory extends AbstractBackendFactory<HBaseConfig> {

    /**
     * The literal description of the factory.
     */
    private static final String NAME = "hbase";

    /**
     * @deprecated Use the default constructor instead.
     */
    @Nonnull
    @Deprecated
    public static BackendFactory getInstance() {
        return new HBaseBackendFactory();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public boolean supportsTransient() {
        return false;
    }

    @Nonnull
    @Override
    protected Backend createRemoteBackend(URL url, HBaseConfig config) throws Exception {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", url.getHost());
        configuration.set("hbase.zookeeper.property.clientPort", String.valueOf(url.getPort() == -1 ? 2181 : url.getPort()));

        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();

        TableName tableName = TableName.valueOf(url.getPath().substring(1));

        if (!admin.tableExists(tableName) && !config.isReadOnly()) {
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName)
                    .addFamily(new HColumnDescriptor(AbstractHBaseBackend.FAMILY_PROPERTY))
                    .addFamily(new HColumnDescriptor(AbstractHBaseBackend.FAMILY_TYPE))
                    .addFamily(new HColumnDescriptor(AbstractHBaseBackend.FAMILY_CONTAINMENT));

            admin.createTable(tableDescriptor);
        }

        Table table = connection.getTable(tableName);

        return createMapper(config.getMapping(), table);
    }
}
