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

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.HBaseBackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.option.HBaseOptions;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;
import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptions;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.eclipse.emf.common.util.URI;

import java.io.File;

import static java.util.Objects.isNull;

/**
 * A specific {@link Context} for the HBase implementation.
 */
public abstract class HBaseContext implements Context {

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
     * Creates a new {@code HBaseContext} with a mapping with arrays and strings.
     *
     * @return a new context.
     */
    public static Context getWithArraysAndStrings() {
        return new HBaseContext() {
            @Override
            public AbstractPersistenceOptions<?> optionsBuilder() {
                return HBaseOptions.newBuilder().withArraysAndStrings();
            }
        };
    }

    /**
     * Initializes the mini-cluster.
     */
    private static void initMiniCluster() {
        try {
            Log.info("Initializing the Hadoop cluster... (This may take several minutes)");

            hbase = new HBaseTestingUtility();
            hbase.startMiniCluster(1);

            Configuration conf = hbase.getConnection().getConfiguration();
            host = conf.get("hbase.zookeeper.quorum");
            port = Integer.parseInt(conf.get("hbase.zookeeper.property.clientPort"));

            Log.info("Hadoop cluster running at {0}:{1,number,#}", host, port);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    Log.info("Shutting down the Hadoop mini-cluster...");
                    hbase.shutdownMiniCluster();
                }
                catch (Exception ignored) {
                }
            }));
        }
        catch (Exception e) {
            Log.error(e, "Unable to create the Hadoop cluster. If you're testing on Windows, you need to install Cygwin (https://hbase.apache.org/cygwin.html)");
            throw new RuntimeException(e);
        }
    }

    @Override
    public String name() {
        return "HBase";
    }

    @Override
    public BackendFactory factory() {
        if (isNull(hbase)) {
            initMiniCluster();
        }

        return HBaseBackendFactory.getInstance();
    }

    @Override
    public String uriScheme() {
        return HBaseURI.SCHEME;
    }

    @Override
    public URI createUri(URI uri) {
        return HBaseURI.createHierarchicalURI(host, port, uri);
    }

    @Override
    public URI createUri(File file) {
        return HBaseURI.createHierarchicalURI(host, port, URI.createURI(file.getName()));
    }
}
