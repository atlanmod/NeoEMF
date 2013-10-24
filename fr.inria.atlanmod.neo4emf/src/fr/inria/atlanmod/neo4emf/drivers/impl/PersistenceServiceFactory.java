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
import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.IndexProvider;
import org.neo4j.index.lucene.LuceneIndexProvider;
import org.neo4j.kernel.impl.cache.CacheProvider;
import org.neo4j.kernel.impl.cache.GCResistantCacheProvider;

import fr.inria.atlanmod.neo4emf.drivers.IPersistenceManager;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceService;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceServiceFactory;
import org.neo4j.kernel.ListIndexIterable;


@SuppressWarnings("deprecation")
public class PersistenceServiceFactory extends GraphDatabaseFactory implements IPersistenceServiceFactory {

	@Override	
 public IPersistenceService createPersistenceService( String path, IPersistenceManager persistenceManager, Map<String,String> config ){
		

//		
//		IPersistenceService service = new PersistenceService(path, config, indexProviders, kernelExtensions, cacheProviders,
//				txInterceptorProviders, persistenceManager);
		IPersistenceService service = new PersistenceService(path, config, persistenceManager);
		registerShutdownHook(service);
		return service;
				
	}
	public static IPersistenceServiceFactory init () {
		if(eINSTANCE == null) {
			return  new PersistenceServiceFactory();
		}
		return eINSTANCE;
	}
	
	 /**
     * Register a shutdown so the database shuts clearly when an exception is raised
     * @param persistenceService {@link IPersistenceService}
     */
	public PersistenceServiceFactory (){
		//the cache providers
	    ArrayList<CacheProvider> cacheList = new ArrayList<CacheProvider>();
	    cacheList.add( new GCResistantCacheProvider() );

	    //the index providers
	    IndexProvider lucene = new LuceneIndexProvider();
	    ArrayList<IndexProvider> provs = new ArrayList<IndexProvider>();
	    provs.add( lucene );
	    ListIndexIterable providers = new ListIndexIterable();
	    providers.setIndexProviders( provs );
	    this.setIndexProviders( providers );
	    this.setCacheProviders( cacheList );
	}
	private static void registerShutdownHook( final IPersistenceService graphDb )
	{

		Runtime.getRuntime().addShutdownHook( new Thread()
		{
			@Override
			public void run()
			{
				graphDb.shutdown();
				//System.out.println("SHUTDOWN HOOK");
			}
		} );
	}
	@Override
	public IPersistenceService createPersistenceService(String path,
			IPersistenceManager persistenceManager) {
		IPersistenceService service = new PersistenceService(path, persistenceManager);
		registerShutdownHook(service);
		return service;
	}
}
