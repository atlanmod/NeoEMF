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

package fr.inria.atlanmod.neoemf.data.hbase.util;

import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.eclipse.emf.common.util.URI;

import java.io.IOException;

import javax.annotation.Nonnull;

import static java.util.Objects.isNull;

/**
 * Utility singleton class that provides HBase-specific helpers.
 */
public class HBaseResourceUtil {

    private final Configuration conf = HBaseConfiguration.create();

    private HBaseResourceUtil() {
    }

    /**
     * Returns the instance of this class.
     */
    @Nonnull
    public static HBaseResourceUtil getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Deletes a table if exist
     *
     * @return {@code true} if deleted, {@code false} otherwise
     */
    public boolean deleteResourceIfExists(URI modelURI) throws IOException {

        // Setting up the configuration according to the URI
        conf.set("hbase.zookeeper.quorum", modelURI.host());
        conf.set("hbase.zookeeper.property.clientPort", isNull(modelURI.port()) ? "2181" : modelURI.port());

        // Checking HBase availability
        try {
            HBaseAdmin.checkHBaseAvailable(conf);
        }
        catch (MasterNotRunningException e) {
            NeoLogger.error("The Master node is not running, details below \n {0}", e.getLocalizedMessage());
        }
        catch (ZooKeeperConnectionException e) {
            NeoLogger.error("zooKeeper connexion failed using the following configuration:\n hbase.zookeeper.quorum:{0}\nhbase.zookeeper.property.clientPort:{1}", e.getLocalizedMessage(), conf.get("hbase.zookeeper.property.clientPort"));
        }
        catch (Exception e) {
            NeoLogger.error(e);
        }

        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        String cloneURI = HBaseURI.format(modelURI);
        TableName tableName = TableName.valueOf(cloneURI);
        NeoLogger.error("Delete table if exists");
        try {
            if (admin.tableExists(tableName)) {
                if (!admin.isTableDisabled(tableName)) {
                    admin.disableTable(tableName);
                }
                admin.deleteTable(tableName);
                NeoLogger.info("Table has been deleted");
                return true;
            }
        }
        catch (IOException e) {
            NeoLogger.error(e);
            throw e;
        }
        return false;
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static class Holder {

        private static final HBaseResourceUtil INSTANCE = new HBaseResourceUtil();
    }
}
