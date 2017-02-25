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

import fr.inria.atlanmod.neoemf.AbstractTestHelper;
import fr.inria.atlanmod.neoemf.TestHelper;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.option.HBaseOptions;
import fr.inria.atlanmod.neoemf.data.hbase.option.HBaseOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;

import static java.util.Objects.isNull;

/**
 * A specific {@link TestHelper} for the MapDB implementation.
 */
public class HBaseTestHelper extends AbstractTestHelper<HBaseTestHelper> {

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
     * Constructs a new {@code HBaseTestHelper} with the given {@code ePackage}.
     *
     * @param ePackage the {@link EPackage} associated to the built {@link Resource}
     *
     * @see EPackage.Registry
     */
    public HBaseTestHelper(EPackage ePackage) {
        super(ePackage);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Creates a mocked {@link PersistenceBackendFactory} for testing HBase in a local environment.
     */
    @Override
    protected PersistenceBackendFactory factory() {
        if (isNull(hbase)) {
            initMiniCluster();
        }

        NeoLogger.info("Hadoop mini-cluster running at {0}:{1}", host, port);

        return HBaseBackendFactory.getInstance();
    }

    @Override
    protected HBaseOptionsBuilder optionsBuilder() {
        return HBaseOptions.newBuilder();
    }

    @Override
    protected String uriScheme() {
        return HBaseURI.SCHEME;
    }

    @Override
    public HBaseTestHelper uri(URI uri) {
        this.uri = HBaseURI.createHierarchicalURI(host, port, uri);
        return me();
    }

    @Override
    public HBaseTestHelper file(File file) {
        this.uri = HBaseURI.createHierarchicalURI(host, port, URI.createURI(file.getName()));
        return me();
    }

    /**
     * Initializes the mini-cluster.
     */
    private void initMiniCluster() {
        try {
            NeoLogger.info("Initializing the Hadoop mini-cluster... (This may take several minutes)");

            hbase = new HBaseTestingUtility();
            hbase.startMiniCluster(1);

            Configuration conf = hbase.getConnection().getConfiguration();
            host = conf.get(HBaseBackendFactory.HOST_PROPERTY);
            port = Integer.parseInt(conf.get(HBaseBackendFactory.PORT_PROPERTY));

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    NeoLogger.info("Shutting down the Hadoop minicluster...");
                    hbase.shutdownMiniCluster();
                }
                catch (Exception ignore) {
                }
            }));
        }
        catch (Exception e) {
            NeoLogger.error(e, "Unable to create the Hadoop mini-cluster. If you're testing on Windows, you need to install Cygwin");
            throw new RuntimeException(e);
        }
    }
}
