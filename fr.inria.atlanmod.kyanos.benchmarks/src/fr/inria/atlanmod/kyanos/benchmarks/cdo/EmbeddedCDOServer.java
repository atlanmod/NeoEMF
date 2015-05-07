/*******************************************************************************
 * Copyright (c) 2014 Abel Gómez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel Gómez - initial API and implementation
 ******************************************************************************/
package fr.inria.atlanmod.kyanos.benchmarks.cdo;
/*******************************************************************************
 * Copyright (c) 2010 Stephan Zehrer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Zehrer - initial API and implementation
 *******************************************************************************/
 
import java.util.HashMap;
import java.util.Map;

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
import org.eclipse.emf.ecore.EcoreFactory;
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
 
public class EmbeddedCDOServer {
 
	EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;

	private String path;

	private String repositoryName;

	private IJVMConnector connector;

	private IManagedContainer container;
 
	public EmbeddedCDOServer(String path, String repositoryName) {
		this.path = path;
		this.repositoryName = repositoryName;
	}
 
	public void run() {
		try {
			JdbcDataSource dataSource = getJdbcDataSource("jdbc:h2:" + path + "/" + repositoryName);
	 
			IStore cdoStore = getStore(dataSource);
			IRepository cdoRepository = getRepository(cdoStore);
			
			container = getContainer();
			CDOServerUtil.addRepository(container, cdoRepository);
	 
			@SuppressWarnings("unused")
			IAcceptor acceptor = JVMUtil.getAcceptor(container, "default"); //$NON-NLS-1$
			connector = JVMUtil.getConnector(container, "default"); //$NON-NLS-1$
	
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
		} finally {
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					EmbeddedCDOServer.this.stop();
				}
			});
		}
	}
 
	public void stop() {
		connector.close();
		container.deactivate();
	}
	
	protected JdbcDataSource getJdbcDataSource(String url) {
		// Setup JdbcDataSource
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL(url);
		return dataSource;
	}
 
	protected IStore getStore(JdbcDataSource dataSource) {
		// Setup Store
		IMappingStrategy mappingStrategy = CDODBUtil.createHorizontalMappingStrategy(true);
		IDBAdapter dbAdapter = new H2Adapter();
		IDBConnectionProvider dbConnectionProvider = DBUtil.createConnectionProvider(dataSource);
		return CDODBUtil.createStore(mappingStrategy, dbAdapter, dbConnectionProvider);
	}
 
	protected IRepository getRepository(IStore store) {
		// Setup Repository
		Map<String, String> props = new HashMap<String, String>();
		props.put(IRepository.Props.OVERRIDE_UUID, repositoryName);
	    props.put(IRepository.Props.SUPPORTING_AUDITS, "false");
	    props.put(IRepository.Props.SUPPORTING_BRANCHES, "false");	
		return CDOServerUtil.createRepository(repositoryName, store, props);
	}
 
	protected IManagedContainer getContainer() {
		IManagedContainer container = ContainerUtil.createContainer();
		Net4jUtil.prepareContainer(container);
		JVMUtil.prepareContainer(container);
		CDONet4jUtil.prepareContainer(container);
		CDONet4jServerUtil.prepareContainer(container);
		container.activate();
		return container;
	}
 
 
	public CDOSession openSession() {
		CDONet4jSessionConfiguration config = CDONet4jUtil.createNet4jSessionConfiguration();
		config.setConnector(connector);
		config.setRepositoryName(repositoryName);
 
		return config.openNet4jSession();		
	}
	
	@Override
	protected void finalize() throws Throwable {
		stop();
		super.finalize();
	}
}
