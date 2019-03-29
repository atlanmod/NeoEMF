/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;

import org.atlanmod.commons.concurrent.MoreThreads;
import org.atlanmod.commons.log.Log;
import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.net4j.CDONet4jSession;
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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.connector.IConnector;
import org.eclipse.net4j.db.DBUtil;
import org.eclipse.net4j.db.IDBAdapter;
import org.eclipse.net4j.db.IDBConnectionProvider;
import org.eclipse.net4j.db.h2.H2Adapter;
import org.eclipse.net4j.jvm.JVMUtil;
import org.eclipse.net4j.signal.ISignalProtocol;
import org.eclipse.net4j.util.container.ContainerEventAdapter;
import org.eclipse.net4j.util.container.ContainerUtil;
import org.eclipse.net4j.util.container.IContainer;
import org.eclipse.net4j.util.container.IManagedContainer;
import org.eclipse.net4j.util.lifecycle.ILifecycle;
import org.h2.jdbcx.JdbcDataSource;

import java.io.Closeable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;
import static org.atlanmod.commons.Preconditions.checkState;

/**
 * An {@link Adapter} on top of a CDO server.
 */
@AdapterName("cdo")
@ParametersAreNonnullByDefault
public class CdoAdapter extends AbstractAdapter {

    /**
     * The embedded CDO server.
     */
    private EmbeddedCdoServer server;

    /**
     * Constructs a new {@code CdoAdapter}.
     */
    public CdoAdapter() {
        super("cdo", org.eclipse.gmt.modisco.java.cdo.impl.JavaPackageImpl.class);
    }

    @Nonnull
    @Override
    public URI createUri(Path directory, String fileName) {
        return URI.createFileURI(directory.resolve(fileName).toFile().getAbsolutePath());
    }

    @Nonnull
    @Override
    public Resource create(URI uri) {
        checkState(uri.isFile());

        final Path file = Paths.get(uri.toFileString());

        server = new EmbeddedCdoServer(file);
        return server.getTransaction().getOrCreateResource(file.getFileName().toString());
    }

    @Override
    public void unload(Resource resource) {
        super.unload(resource);

        if (nonNull(server)) {
            server.close();
        }
    }

    @Nonnull
    @Override
    protected Map<String, ?> getOptions(ImmutableConfig config) {
        checkState(nonNull(server), "The CDO server has not been initialized");

        Map<String, Object> options = new HashMap<>();
        options.put(CDOResource.OPTION_SAVE_OVERRIDE_TRANSACTION, server.getTransaction());
        return options;
    }

    /**
     * An embedded CDO server.
     */
    @ParametersAreNonnullByDefault
    private static final class EmbeddedCdoServer implements Closeable {

        private static final String DEFAULT_REPOSITORY_NAME = "repo";

        private static final String DEFAULT_DESCRIPTION = "default";

        @Nonnull
        private final IConnector connector;

        @Nonnull
        private final IManagedContainer container;

        @Nonnull
        private final CDOSession session;

        @Nonnull
        private final CDOTransaction transaction;

        /**
         * Constructs and initializes a new {@code EmbeddedCdoServer}.
         *
         * @param path the path of the server
         */
        public EmbeddedCdoServer(Path path) {
            try {
                JdbcDataSource dataSource = createDataSource("jdbc:h2:" + path + "/" + DEFAULT_REPOSITORY_NAME);

                IStore store = createStore(dataSource);
                IRepository repository = createRepository(store);

                container = createContainer();
                CDOServerUtil.addRepository(container, repository);

                JVMUtil.getAcceptor(container, DEFAULT_DESCRIPTION);
                connector = JVMUtil.getConnector(container, DEFAULT_DESCRIPTION);

                repository.getSessionManager().addListener(new ContainerEventAdapter<ISession>() {
                    @Override
                    protected void onAdded(IContainer<ISession> container, ISession session) {
                        ISessionProtocol protocol = session.getProtocol();
                        if (protocol instanceof ISignalProtocol) {
                            ISignalProtocol<?> signalProtocol = (ISignalProtocol) protocol;
                            signalProtocol.setTimeout(60000);
                        }
                    }
                });

                session = openSession();

                transaction = session.openTransaction();
            }
            finally {
                MoreThreads.executeAtExit(this::close);
            }
        }

        /**
         * Closes the specified object, if it is not already {@link org.eclipse.net4j.util.collection.Closeable#isClosed()
         * closed}.
         *
         * @param closeable the object to close
         */
        private static void close(@Nullable org.eclipse.net4j.util.collection.Closeable closeable) {
            if (nonNull(closeable) && !closeable.isClosed()) {
                closeable.close();
            }
        }

        /**
         * Deactivates the specified object, if it is {@link ILifecycle#isActive() active}.
         *
         * @param lifecycle the object to deactivate
         */
        private static void deactivate(@Nullable ILifecycle lifecycle) {
            if (nonNull(lifecycle) && lifecycle.isActive()) {
                Optional.ofNullable(lifecycle.deactivate()).ifPresent(Log::error);
            }
        }

        /**
         * Gets the transaction used by this server.
         *
         * @return the transaction
         */
        @Nonnull
        public CDOTransaction getTransaction() {
            return transaction;
        }

        @Override
        public void close() {
            if (!connector.isClosed()) {
                close(transaction);
                close(session);
                close(connector);
                deactivate(container);
            }
        }

        @Nonnull
        private JdbcDataSource createDataSource(String url) {
            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setURL(url);
            return dataSource;
        }

        @Nonnull
        private IStore createStore(JdbcDataSource dataSource) {
            IMappingStrategy mappingStrategy = CDODBUtil.createHorizontalMappingStrategy(true);
            mappingStrategy.getProperties().put(IMappingStrategy.Props.QUALIFIED_NAMES, Boolean.TRUE.toString());

            IDBAdapter dbAdapter = new H2Adapter();

            IDBConnectionProvider dbConnectionProvider = DBUtil.createConnectionProvider(dataSource);

            return CDODBUtil.createStore(mappingStrategy, dbAdapter, dbConnectionProvider);
        }

        @Nonnull
        private IRepository createRepository(IStore store) {
            Map<String, String> properties = new HashMap<>();
            properties.put(IRepository.Props.OVERRIDE_UUID, DEFAULT_REPOSITORY_NAME);
            properties.put(IRepository.Props.SUPPORTING_AUDITS, Boolean.FALSE.toString());
            properties.put(IRepository.Props.SUPPORTING_BRANCHES, Boolean.FALSE.toString());

            return CDOServerUtil.createRepository(DEFAULT_REPOSITORY_NAME, store, properties);
        }

        @Nonnull
        private IManagedContainer createContainer() {
            IManagedContainer container = ContainerUtil.createContainer();

            Net4jUtil.prepareContainer(container);
            JVMUtil.prepareContainer(container);
            CDONet4jUtil.prepareContainer(container);
            CDONet4jServerUtil.prepareContainer(container);

            container.activate();
            return container;
        }

        @Nonnull
        private CDOSession openSession() {
            CDONet4jSessionConfiguration config = CDONet4jUtil.createNet4jSessionConfiguration();
            config.setConnector(connector);
            config.setRepositoryName(DEFAULT_REPOSITORY_NAME);

            CDONet4jSession session = config.openNet4jSession();
            session.options().getNet4jProtocol().setTimeout(1000000);
            return session;
        }
    }
}
