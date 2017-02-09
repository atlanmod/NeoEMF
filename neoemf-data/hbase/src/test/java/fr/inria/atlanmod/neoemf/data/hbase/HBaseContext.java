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

import fr.inria.atlanmod.neoemf.Context;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.hbase.option.HBaseOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.apache.hadoop.hbase.HBaseCluster;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Table;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static java.util.Objects.isNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * A specific {@link Context} for the HBase implementation.
 */
public class HBaseContext implements Context {

    /**
     * The name of this context.
     */
    public static final String NAME = "HBase";

    /**
     * Facility for testing HBase.
     */
    private final HBaseTestingUtility hbase = new HBaseTestingUtility();

    /**
     * A mini-cluster for testing HBase in a local environment.
     */
    private HBaseCluster hbaseCluster;

    /**
     * A mocked {@link PersistenceBackendFactory} for testing HBase in a local environment.
     */
    private PersistenceBackendFactory mockFactory;

    /**
     * Constructs a new {@code HBaseContext}.
     */
    protected HBaseContext() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class.
     */
    public static Context get() {
        return Holder.INSTANCE;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String uriScheme() {
        return HBaseURI.SCHEME;
    }

    @Override
    public URI createURI(URI uri) {
        return HBaseURI.createURI(uri);
    }

    @Override
    public URI createFileURI(File file) {
        return HBaseURI.createFileURI(file);
    }

    @Override
    public PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException {
        EPackage.Registry.INSTANCE.put(ePackage.getNsURI(), ePackage);

        ResourceSet resourceSet = new ResourceSetImpl();

        if (!PersistenceBackendFactoryRegistry.isRegistered(HBaseURI.SCHEME)) {
            PersistenceBackendFactoryRegistry.register(HBaseURI.SCHEME, persistenceBackendFactory());
        }
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(HBaseURI.SCHEME, persistenceBackendFactory());

        PersistentResource resource = (PersistentResource) resourceSet.createResource(URI.createFileURI(file.toString()));
        resource.save(HBaseOptionsBuilder.noOption());

        return resource;
    }

    @Override
    public PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException {
        return createPersistentResource(ePackage, file);
    }

    @Override
    public PersistenceBackendFactory persistenceBackendFactory() {
        if (isNull(mockFactory)) {
            try {
                HBaseBackendFactory factory = mock(HBaseBackendFactory.class);
                when(factory.createTable(any())).then(answer -> createTable());

                mockFactory = factory;
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return mockFactory;
    }

    /**
     * Creates a new HBase {@link Table} that runs in a local environment.
     *
     * @return a new {@link Table}
     */
    private Table createTable() {
        try {
            if (isNull(hbaseCluster)) {
                hbase.startMiniCluster();
            }

            return hbase.createTable(TableName.valueOf(UUID.randomUUID().toString()),
                    new byte[][]{
                            AbstractHBaseBackend.PROPERTY_FAMILY,
                            AbstractHBaseBackend.TYPE_FAMILY,
                            AbstractHBaseBackend.CONTAINMENT_FAMILY
                    });
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static class Holder {

        /**
         * The instance of the outer class.
         */
        private static final Context INSTANCE = new HBaseContext();
    }
}
