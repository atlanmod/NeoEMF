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

import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import fr.inria.atlanmod.neo4emf.drivers.IPersistenceManager;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceService;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceServiceFactory;


public class PersistenceServiceFactory extends GraphDatabaseFactory implements IPersistenceServiceFactory {

	@Override	
 public IPersistenceService createPersistenceService (String path, IPersistenceManager persistenceService){
		IPersistenceService service = new PersistenceService(path, persistenceService);
		registerShutdownHook(service);
		return service;
				
	}
	public static IPersistenceServiceFactory init () {
		if (eINSTANCE==null)
			return  new PersistenceServiceFactory();
		return eINSTANCE;
	}
	
	 /**
     * Register a shutdown so the database shuts clearly when an exception is raised
     * @param persistenceService {@link IPersistenceService}
     */
	
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
}
