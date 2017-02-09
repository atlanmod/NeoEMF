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

import fr.inria.atlanmod.neoemf.AbstractResourceBuilder;
import fr.inria.atlanmod.neoemf.ResourceBuilder;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Table;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * A specific {@link ResourceBuilder} for the MapDB implementation.
 */
public class HBaseResourceBuilder extends AbstractResourceBuilder<HBaseResourceBuilder> {

    /**
     * Constructs a new {@code HBaseResourceBuilder} with the given {@code ePackage}.
     *
     * @param ePackage the {@link EPackage} associated to the built {@link Resource}
     *
     * @see EPackage.Registry
     */
    public HBaseResourceBuilder(EPackage ePackage) {
        super(ePackage);
    }

    @Override
    protected void initBuilder() {
        super.initBuilder();

        if (!PersistenceBackendFactoryRegistry.isRegistered(HBaseURI.SCHEME)) {
            PersistenceBackendFactoryRegistry.register(HBaseURI.SCHEME, createMock());
        }
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(HBaseURI.SCHEME, PersistentResourceFactory.getInstance());
    }

    @Override
    public HBaseResourceBuilder uri(URI uri) {
        this.uri = HBaseURI.createHierarchicalURI("127.0.0.1", "0", HBaseURI.createURI(uri));
        return me();
    }

    @Override
    public HBaseResourceBuilder file(File file) {
        this.uri = HBaseURI.createHierarchicalURI("127.0.0.1", "0", URI.createURI(file.getName()));
        return me();
    }

    public static PersistenceBackendFactory createMock() {
        Table table;
        try {
            NeoLogger.info("Initializing Hadoop minicluster... (This may take several minutes)");

            HBaseTestingUtility hbase = new HBaseTestingUtility();
            hbase.startMiniCluster(1);

            TableName tableName = TableName.valueOf(UUID.randomUUID().toString());
            byte[][] families = new byte[][]{
                    AbstractHBaseBackend.PROPERTY_FAMILY,
                    AbstractHBaseBackend.TYPE_FAMILY,
                    AbstractHBaseBackend.CONTAINMENT_FAMILY};

            table = hbase.createTable(tableName, families);

            NeoLogger.info("Hadoop minicluster is ready");
        }
        catch (Exception e) {
            NeoLogger.error(e, "Unable to create the Hadoop minicluster");
            throw new RuntimeException(e);
        }

        HBaseBackendFactory factory = mock(HBaseBackendFactory.class);
        when(factory.createTable(any())).thenReturn(table);

        doCallRealMethod().when(factory).getName();
        doCallRealMethod().when(factory).createPersistentBackend(any(), any());
        doCallRealMethod().when(factory).createTransientBackend();
        doCallRealMethod().when(factory).createPersistentStore(any(), any(), any());
        doCallRealMethod().when(factory).createSpecificPersistentStore(any(), any(), any());
        doCallRealMethod().when(factory).createTransientStore(any(), any());
        doCallRealMethod().when(factory).copyBackend(any(), any());

        return factory;
    }
}
