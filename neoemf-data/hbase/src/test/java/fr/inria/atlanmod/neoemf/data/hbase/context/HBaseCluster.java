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

import fr.inria.atlanmod.common.log.Log;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseTestingUtility;

import static fr.inria.atlanmod.common.Preconditions.checkState;
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
     * Whether the mini-cluster has been initialized.
     */
    private static boolean initialized = false;

    /**
     * Checks whether the mini-cluster has been successfully initialized.
     *
     * @return {@code true} if the mini-cluster has been successfully initialized
     */
    public static boolean isInitialized() {
        return initialized && nonNull(hbase);
    }

    /**
     * Initializes the mini-cluster.
     * <p>
     * If the mini-cluster is already initialized, then calling this method does nothing.
     */
    public static void init() {
        // If already initialized, then do nothing
        if (initialized) {
            return;
        }

        initialized = true;

        try {
            Log.info("Initializing the Hadoop cluster... (This may take several minutes)");

            hbase = new HBaseTestingUtility();
            hbase.startMiniCluster();

            Configuration conf = hbase.getConnection().getConfiguration();
            host = conf.get(HOST_CONFIG);
            port = Integer.parseInt(conf.get(PORT_CONFIG));

            Log.info("Hadoop cluster running at {0}:{1,number,#}", host, port);

            Runtime.getRuntime().addShutdownHook(new Thread(HBaseCluster::close));
        }
        catch (Exception e) {
            reset();
            Log.error(e, "Unable to create the Hadoop cluster");
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

            reset();
        }
        catch (Exception ignored) {
        }
    }

    /**
     * Resets the current configuration.
     */
    private static void reset() {
        hbase = null;
        host = null;
        port = 0;
    }

    /**
     * Returns the host of the HBase cluster.
     *
     * @return the host
     */
    public static String host() {
        checkState(isInitialized(), "The mini-cluster has not been initialized");

        return host;
    }

    /**
     * Returns the port of the HBase cluster.
     *
     * @return the port
     */
    public static int port() {
        checkState(isInitialized(), "The mini-cluster has not been initialized");

        return port;
    }
}
