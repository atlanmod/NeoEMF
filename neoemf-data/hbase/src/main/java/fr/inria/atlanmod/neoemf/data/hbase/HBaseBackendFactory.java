/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.neoemf.config.ConfigValue;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidBackendException;
import fr.inria.atlanmod.neoemf.data.PersistentBackend;

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

import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * A factory that creates {@link HBaseBackend} instances.
 * <p>
 * This class only creates persistent databases that can be configured using {@link
 * fr.inria.atlanmod.neoemf.resource.PersistentResource#save(Map)} and {@link fr.inria.atlanmod.neoemf.resource.PersistentResource#load(Map)}
 * options maps.
 * <p>
 * <b>Important Note:</b> Transient back-ends can be instantiated using this factory, but they will be handed as
 * persistent ones. This is a limitation that will be solved in next releases. To avoid any consistency issue we
 * recommend every HBase resource right after their creation, ensuring the resource is using a persistent back-end.
 *
 * @see HBaseBackend
 * @see fr.inria.atlanmod.neoemf.data.hbase.config.HBaseConfig
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResource
 */
@ParametersAreNonnullByDefault
public class HBaseBackendFactory extends AbstractBackendFactory {

    /**
     * The literal description of the factory.
     */
    public static final String NAME = "hbase";

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
    public String name() {
        return NAME;
    }

    @Override
    public boolean supportsTransient() {
        return false;
    }

    @Nonnull
    @Override
    public PersistentBackend createPersistentBackend(URI uri, ImmutableConfig baseConfig) {
        HBaseBackend backend;

        checkArgument(uri.isHierarchical(), "%s only supports hierarchical URIs", getClass().getSimpleName());

        try {
            // No file-based configuration
            String mapping = baseConfig.getMapping();
            boolean isReadOnly = baseConfig.isReadOnly();

            Configuration configuration = HBaseConfiguration.create();
            configuration.set("hbase.zookeeper.quorum", uri.host());
            configuration.set("hbase.zookeeper.property.clientPort", isNull(uri.port()) ? "2181" : uri.port());

            Connection connection = ConnectionFactory.createConnection(configuration);
            Admin admin = connection.getAdmin();

            TableName tableName = TableName.valueOf(
                    checkNotNull(uri).segmentsList().stream()
                            .map(s -> s.replaceAll("-", "_"))
                            .collect(Collectors.joining("_")));

            if (!admin.tableExists(tableName) && !isReadOnly) {
                HTableDescriptor desc = new HTableDescriptor(tableName);
                HColumnDescriptor propertyFamily = new HColumnDescriptor(AbstractHBaseBackend.FAMILY_PROPERTY);
                HColumnDescriptor typeFamily = new HColumnDescriptor(AbstractHBaseBackend.FAMILY_TYPE);
                HColumnDescriptor containmentFamily = new HColumnDescriptor(AbstractHBaseBackend.FAMILY_CONTAINMENT);
                desc.addFamily(propertyFamily);
                desc.addFamily(typeFamily);
                desc.addFamily(containmentFamily);
                admin.createTable(desc);
            }

            Table table = connection.getTable(tableName);

            backend = createMapper(mapping,
                    new ConfigValue<>(table, Table.class));
        }
        catch (Exception e) {
            throw new InvalidBackendException("Unable to open the HBase database", e);
        }

        return backend;
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        static final BackendFactory INSTANCE = new HBaseBackendFactory();
    }
}
