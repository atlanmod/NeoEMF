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
import fr.inria.atlanmod.neoemf.data.hbase.option.HBaseOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.client.Connection;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static java.util.Objects.isNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * A specific {@link TestHelper} for the MapDB implementation.
 */
public class HBaseTestHelper extends AbstractTestHelper<HBaseTestHelper> {

    /**
     * Facility for testing HBase.
     */
    private static HBaseTestingUtility hbase;

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
        HBaseBackendFactory factory = mock(HBaseBackendFactory.class);

        // Mocked methods
        when(factory.configureConnection(any())).thenReturn(configureConnection());

        // Real methods
        when(factory.getName()).thenCallRealMethod();
        when(factory.createPersistentBackend(any(), any())).thenCallRealMethod();
        when(factory.createTransientBackend()).thenCallRealMethod();
        when(factory.createPersistentStore(any(), any(), any())).thenCallRealMethod();
        when(factory.createTransientStore(any(), any())).thenCallRealMethod();

        return factory;
    }

    @Override
    protected Map<String, Object> defaultOptions() {
        return HBaseOptionsBuilder.noOption();
    }

    @Override
    protected String uriScheme() {
        return HBaseURI.SCHEME;
    }

    @Override
    public HBaseTestHelper uri(URI uri) {
        this.uri = HBaseURI.createHierarchicalURI("127.0.0.1", "0", uri);
        return me();
    }

    @Override
    public HBaseTestHelper file(File file) {
        this.uri = HBaseURI.createHierarchicalURI("127.0.0.1", "0", URI.createURI(file.getName()));
        return me();
    }

    /**
     * Gets the {@link Connection} to the mini-cluster.
     *
     * @return the connection
     */
    private Connection configureConnection() {
        if (isNull(hbase)) {
            initMiniCluster();
        }

        try {
            return hbase.getConnection();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes the mini-cluster.
     */
    private void initMiniCluster() {
        try {
            NeoLogger.info("Initializing Hadoop minicluster... (This may take several minutes)");

            hbase = new HBaseTestingUtility();
            hbase.startMiniCluster(1);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    NeoLogger.info("Shutting down Hadoop minicluster...");
                    hbase.shutdownMiniCluster();
                }
                catch (Exception ignore) {
                }
            }));

            NeoLogger.info("Hadoop minicluster is ready");
        }
        catch (Exception e) {
            NeoLogger.error(e, "Unable to create the Hadoop minicluster");
            throw new RuntimeException(e);
        }
    }
}
