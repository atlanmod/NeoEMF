/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.hbase.context;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.atlanmod.commons.concurrent.MoreThreads;
import org.atlanmod.commons.log.Log;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;
import static org.atlanmod.commons.Guards.checkState;

/**
 * An object that holds the HBase mini-cluster instance.
 */
@ParametersAreNonnullByDefault
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
    private static boolean initialized;

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

            Configuration configuration = hbase.getConfiguration();
            /*
            Do not touch the following lines!!!
            For some reasons, when trying to get the local host, Hadoop gets the public host name,
            instead of "localhost" or "127.0.0.1

            See: https://stackoverflow.com/questions/9481865/getting-the-ip-address-of-the-current-machine-using-java
             */
            configuration.set("hbase.master.hostname", "localhost");
            configuration.set("hbase.regionserver.ipc.address", "localhost");
            configuration.set("hbase.unsafe.regionserver.hostname", "localhost");

            hbase.startMiniCluster();
            host = configuration.get(HOST_CONFIG);
            port = configuration.getInt(PORT_CONFIG, -1);
            Log.info("Hadoop cluster running at {0}:{1,number,#}", host, port);
            MoreThreads.executeAtExit(HBaseCluster::close);
        }
        catch (Throwable e) {
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
        catch (Throwable ignored) {
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
    @Nonnull
    public static String host() {
        checkState(isInitialized(), "The mini-cluster has not been initialized");

        return host;
    }

    /**
     * Returns the port of the HBase cluster.
     *
     * @return the port
     */
    @Nonnegative
    public static int port() {
        checkState(isInitialized(), "The mini-cluster has not been initialized");

        return port;
    }

    public static void main(String[] args) {
        HBaseCluster.init();
    }
}
