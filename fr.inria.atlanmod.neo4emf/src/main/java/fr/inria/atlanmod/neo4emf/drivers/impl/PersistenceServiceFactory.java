package fr.inria.atlanmod.neo4emf.drivers.impl;

/**
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 * Descritpion ! To come
 * @author Amine BENELALLAM
 * */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.index.lucene.LuceneKernelExtensionFactory;
import org.neo4j.kernel.extension.KernelExtensionFactory;
import org.neo4j.kernel.impl.cache.CacheProvider;
import org.neo4j.kernel.impl.cache.SoftCacheProvider;

import fr.inria.atlanmod.neo4emf.drivers.IPersistenceService;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceServiceFactory;

public class PersistenceServiceFactory extends GraphDatabaseFactory implements IPersistenceServiceFactory {

	@Override
	public IPersistenceService createPersistenceService(String path, Map<String, String> config) {

		// the cache providers
		ArrayList<CacheProvider> cacheList = new ArrayList<CacheProvider>();
		cacheList.add(new SoftCacheProvider());

		// the kernel extensions
		LuceneKernelExtensionFactory lucene = new LuceneKernelExtensionFactory();
		List<KernelExtensionFactory<?>> extensions = new ArrayList<KernelExtensionFactory<?>>();
		extensions.add(lucene);

		// the database setup
		GraphDatabaseFactory gdbf = new GraphDatabaseFactory();
		gdbf.setKernelExtensions(extensions);
		gdbf.setCacheProviders(cacheList);
		GraphDatabaseService db = gdbf.newEmbeddedDatabase(path);
		
		IPersistenceService service = new PersistenceService(db);
		registerShutdownHook(service);
		return service;

	}

	public static IPersistenceServiceFactory init() {
		if (eINSTANCE == null) {
			return new PersistenceServiceFactory();
		}
		return eINSTANCE;
	}

	/**
	 * Register a shutdown so the database shuts clearly when an exception is
	 * raised
	 * 
	 * @param persistenceService
	 *            {@link IPersistenceService}
	 */
	private static void registerShutdownHook(final IPersistenceService graphDb) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// At this point the plugin "Neo4emfPlugin" has been already stopped
				// Don't use the shared instance!
				// Shutdown the DB
				graphDb.shutdown();
			}
		});
	}
}
