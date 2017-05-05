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

package fr.inria.atlanmod.neoemf.data.hbase.context;

import fr.inria.atlanmod.neoemf.util.log.Log;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseTestingUtility;

import static java.util.Objects.nonNull;

/**
 * An object that holds the HBase mini-cluster instance.
 */
final class HBaseCluster {

    /**
     * The property to retrieve the host form the HBase configuration.
     */
    private static final String HOST_CONFIG = "hbase.zookeeper.quorum";

    /**
     * The property to retrieve the port from the HBase configuration.
     */
    private static final String PORT_CONFIG = "hbase.zookeeper.property.clientPort";

    /**
     * Facility for testing HBase.
     */
    private static HBaseTestingUtility hbase;

    /**
     * The Zookeeper host.
     */
    private static String host;

    /**
     * The Zookeeper port.
     */
    private static int port;

    /**
     * Initializes the mini-cluster.
     * <p>
     * If the mini-cluster is already initialized, then calling this method does nothing.
     */
    private static void init() {
        // Is already initialized?
        if (nonNull(hbase)) {
            return;
        }

        try {
            Log.info("Initializing the Hadoop cluster... (This may take several minutes)");

            hbase = new HBaseTestingUtility();
            hbase.startMiniCluster(1);

            Configuration conf = hbase.getConnection().getConfiguration();
            host = conf.get(HOST_CONFIG);
            port = Integer.parseInt(conf.get(PORT_CONFIG));

            Log.info("Hadoop cluster running at {0}:{1,number,#}", host, port);

            Runtime.getRuntime().addShutdownHook(new Thread(HBaseCluster::close));
        }
        catch (Exception e) {
            Log.error(e, "Unable to create the Hadoop cluster. If you are testing on Windows, you need to install Cygwin (https://hbase.apache.org/cygwin.html)");
            throw new RuntimeException(e);
        }
    }

    /**
     * Closes the mini-cluster and cleans all resources related to it.
     */
    private static void close() {
        try {
            Log.info("Shutting down the Hadoop mini-cluster...");
            hbase.shutdownMiniCluster();

            hbase.cleanupTestDir();
            hbase.cleanupDataTestDirOnTestFS();
        }
        catch (Exception ignored) {
        }
    }

    /**
     * Returns the host of the HBase cluster.
     *
     * @return the host
     */
    public static String host() {
        init();

        return host;
    }

    /**
     * Returns the port of the HBase cluster.
     *
     * @return the port
     */
    public static int port() {
        init();

        return port;
    }
}
