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

import fr.inria.atlanmod.neoemf.AbstractTestBuilder;
import fr.inria.atlanmod.neoemf.TestBuilder;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Table;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.io.IOException;

import static java.util.Objects.isNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * A specific {@link TestBuilder} for the MapDB implementation.
 */
public class HBaseTestBuilder extends AbstractTestBuilder<HBaseTestBuilder> {

    /**
     * Facility for testing HBase.
     */
    private static HBaseTestingUtility hbaseUtility;

    /**
     * Constructs a new {@code HBaseTestBuilder} with the given {@code ePackage}.
     *
     * @param ePackage the {@link EPackage} associated to the built {@link Resource}
     *
     * @see EPackage.Registry
     */
    public HBaseTestBuilder(EPackage ePackage) {
        super(ePackage);
    }

    @Override
    protected void initBuilder() {
        super.initBuilder();

        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(HBaseURI.SCHEME, PersistentResourceFactory.getInstance());
    }

    @Override
    public HBaseTestBuilder uri(URI uri) {
        throw new UnsupportedOperationException("HBase doesn't support URI creation");
    }

    @Override
    public HBaseTestBuilder file(File file) {
        // Register the original factory to create the HBase URI
        PersistenceBackendFactoryRegistry.register(HBaseURI.SCHEME, HBaseBackendFactory.getInstance());

        this.uri = HBaseURI.createHierarchicalURI("127.0.0.1", "0", URI.createURI(file.getName()));

        // Register the mock factory
        PersistenceBackendFactoryRegistry.register(HBaseURI.SCHEME, createMock(uri));

        return me();
    }

    @Override
    public PersistenceBackend createBackend() throws IOException {
        return createMock(uri).createPersistentBackend(uri, resourceOptions);
    }

    /**
     * Creates a mocked {@link PersistenceBackendFactory} for testing HBase in a local environment.
     *
     * @param uri the name of the {@link Table}
     *
     * @return a new {@link PersistenceBackendFactory}
     */
    private PersistenceBackendFactory createMock(URI uri) {
        if (isNull(hbaseUtility)) {
            initCluster();
        }

        Table table = initTable(uri);

        HBaseBackendFactory factory = mock(HBaseBackendFactory.class);
        when(factory.createTable(any())).thenReturn(table);

        when(factory.getName()).thenCallRealMethod();

        when(factory.createPersistentBackend(any(), any())).thenCallRealMethod();
        when(factory.createTransientBackend()).thenCallRealMethod();
        when(factory.createPersistentStore(any(), any(), any())).thenCallRealMethod();
        when(factory.createTransientStore(any(), any())).thenCallRealMethod();

        return factory;
    }

    /**
     * Initializes a {@link Table} with the given {@code name}.
     *
     * @param uri the uri of the table
     *
     * @return a new {@link Table}, or a previous if the name has already been used
     */
    private Table initTable(URI uri) {
        try {
            String name = HBaseURI.format(uri);

            TableName tableName = TableName.valueOf(name);
            Admin admin = hbaseUtility.getHBaseAdmin();

            if (admin.tableExists(tableName)) {
                return admin.getConnection().getTable(tableName);
            }
            else {
                byte[][] families = new byte[][]{
                        AbstractHBaseBackend.PROPERTY_FAMILY,
                        AbstractHBaseBackend.TYPE_FAMILY,
                        AbstractHBaseBackend.CONTAINMENT_FAMILY};

                return hbaseUtility.createTable(tableName, families);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes Hadoop cluster.
     */
    private void initCluster() {
        try {
            NeoLogger.info("Initializing Hadoop minicluster... (This may take several minutes)");

            hbaseUtility = new HBaseTestingUtility();
            hbaseUtility.startMiniCluster(1);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    NeoLogger.info("Shutting down Hadoop minicluster...");

                    Admin admin = hbaseUtility.getHBaseAdmin();
                    for (TableName tableName : admin.listTableNames()) {
                        hbaseUtility.deleteTable(tableName);
                    }

                    hbaseUtility.shutdownMiniCluster();
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
