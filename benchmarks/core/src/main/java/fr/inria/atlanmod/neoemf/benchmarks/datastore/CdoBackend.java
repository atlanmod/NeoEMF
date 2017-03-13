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

package fr.inria.atlanmod.neoemf.benchmarks.datastore;

import fr.inria.atlanmod.neoemf.util.log.Log;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.net4j.CDONet4jSessionConfiguration;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.server.CDOServerUtil;
import org.eclipse.emf.cdo.server.IRepository;
import org.eclipse.emf.cdo.server.ISession;
import org.eclipse.emf.cdo.server.IStore;
import org.eclipse.emf.cdo.server.db.CDODBUtil;
import org.eclipse.emf.cdo.server.db.mapping.IMappingStrategy;
import org.eclipse.emf.cdo.server.net4j.CDONet4jServerUtil;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.spi.server.ISessionProtocol;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.acceptor.IAcceptor;
import org.eclipse.net4j.db.DBUtil;
import org.eclipse.net4j.db.IDBAdapter;
import org.eclipse.net4j.db.IDBConnectionProvider;
import org.eclipse.net4j.db.h2.H2Adapter;
import org.eclipse.net4j.jvm.IJVMConnector;
import org.eclipse.net4j.jvm.JVMUtil;
import org.eclipse.net4j.signal.ISignalProtocol;
import org.eclipse.net4j.util.container.ContainerEventAdapter;
import org.eclipse.net4j.util.container.ContainerUtil;
import org.eclipse.net4j.util.container.IContainer;
import org.eclipse.net4j.util.container.IManagedContainer;
import org.h2.jdbcx.JdbcDataSource;

import java.io.Closeable;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class CdoBackend extends AbstractBackend {

    public static final String NAME = "cdo";

    private static final String RESOURCE_EXTENSION = "cdo";
    private static final String STORE_EXTENSION = "resource"; // -> cdo.resource

    private static final Class<?> EPACKAGE_CLASS = org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.class;

    private EmbeddedCdoServer server;
    private CDOSession session;
    private CDOTransaction transaction;

    @SuppressWarnings("unused") // Called dynamically
    public CdoBackend() {
        super(NAME, RESOURCE_EXTENSION, STORE_EXTENSION, EPACKAGE_CLASS);
    }

    @Override
    public Resource createResource(File file, ResourceSet resourceSet) throws Exception {
        server = new EmbeddedCdoServer(file.toPath());
        server.run();
        session = server.openSession();
        transaction = session.openTransaction();

        return transaction.getRootResource();
    }

    @Override
    public Map<String, Object> getOptions() {
        Map<String, Object> saveOpts = new HashMap<>();
        saveOpts.put(CDOResource.OPTION_SAVE_OVERRIDE_TRANSACTION, transaction);
        return saveOpts;
    }

    @Override
    public Resource load(File file) throws Exception {
        initAndGetEPackage();

        Resource resource = createResource(file, new ResourceSetImpl());
        resource.load(getOptions());
        return resource;
    }

    @Override
    public void unload(Resource resource) {
        if (nonNull(transaction) && !transaction.isClosed()) {
            transaction.close();
        }

        if (nonNull(session) && !session.isClosed()) {
            session.close();
        }

        if (nonNull(server) && !server.isClosed()) {
            server.close();
        }

        if (nonNull(resource) && resource.isLoaded()) {
            resource.unload();
        }
    }

    /**
     * Embedded implementation of CDO server.
     */
    public static class EmbeddedCdoServer implements Closeable {

        private static final String DEFAULT_REPOSITORY_NAME = "repo";

        private final Path path;

        private final String repositoryName;

        private IJVMConnector connector;

        private IManagedContainer container;

        public EmbeddedCdoServer(Path path) {
            this.path = path;
            this.repositoryName = DEFAULT_REPOSITORY_NAME;
        }

        public void run() {
            try {
                JdbcDataSource dataSource = createDataSource("jdbc:h2:" + path + "/" + repositoryName);

                IStore cdoStore = createStore(dataSource);
                IRepository cdoRepository = createRepository(cdoStore);

                container = createContainer();
                CDOServerUtil.addRepository(container, cdoRepository);

                @SuppressWarnings("unused")
                IAcceptor acceptor = JVMUtil.getAcceptor(container, "default");
                connector = JVMUtil.getConnector(container, "default");

                cdoRepository.getSessionManager().addListener(new ContainerEventAdapter<ISession>() {
                    @Override
                    protected void onAdded(IContainer<ISession> container, ISession session) {
                        ISessionProtocol protocol = session.getProtocol();
                        if (protocol instanceof ISignalProtocol) {
                            ISignalProtocol<?> signalProtocol = (ISignalProtocol<?>) protocol;
                            signalProtocol.setTimeout(30L * 1000L);
                        }
                    }
                });
            }
            finally {
                Runtime.getRuntime().addShutdownHook(new Thread(EmbeddedCdoServer.this::close));
            }
        }

        @Override
        public void close() {
            if (nonNull(connector) && !connector.isClosed()) {
                connector.close();
            }
            if (nonNull(container) && container.isActive()) {
                Exception e = container.deactivate();

                if (nonNull(e)) {
                    Log.error(e);
                }
            }
        }

        public boolean isClosed() {
            return isNull(connector) || connector.isClosed();
        }

        public CDOSession openSession() {
            CDONet4jSessionConfiguration config = CDONet4jUtil.createNet4jSessionConfiguration();
            config.setConnector(connector);
            config.setRepositoryName(repositoryName);
            return config.openNet4jSession();
        }

        private JdbcDataSource createDataSource(String url) {
            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setURL(url);
            return dataSource;
        }

        private IStore createStore(JdbcDataSource dataSource) {
            IMappingStrategy mappingStrategy = CDODBUtil.createHorizontalMappingStrategy(true);
            IDBAdapter dbAdapter = new H2Adapter();
            IDBConnectionProvider dbConnectionProvider = DBUtil.createConnectionProvider(dataSource);
            return CDODBUtil.createStore(mappingStrategy, dbAdapter, dbConnectionProvider);
        }

        private IRepository createRepository(IStore store) {
            Map<String, String> props = new HashMap<>();
            props.put(IRepository.Props.OVERRIDE_UUID, repositoryName);
            props.put(IRepository.Props.SUPPORTING_AUDITS, "false");
            props.put(IRepository.Props.SUPPORTING_BRANCHES, "false");
            return CDOServerUtil.createRepository(repositoryName, store, props);
        }

        private IManagedContainer createContainer() {
            IManagedContainer container = ContainerUtil.createContainer();
            Net4jUtil.prepareContainer(container);
            JVMUtil.prepareContainer(container);
            CDONet4jUtil.prepareContainer(container);
            CDONet4jServerUtil.prepareContainer(container);
            container.activate();
            return container;
        }
    }
}
