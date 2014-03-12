package fr.inria.atlanmod.neo4emf;

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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.neo4emf.drivers.NEConfiguration;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfResourceFactory;

public interface INeo4emfResourceFactory extends Resource.Factory {

	/**
	 * The singleton instance
	 */
	INeo4emfResourceFactory eINSTANCE = Neo4emfResourceFactory.init();

	/**
	 * Creates a resource from an URI
	 */
	public INeo4emfResource createResource(URI uri);

	/**
	 * Sets the configuration (parameters) needed to create a Neo4EMF resource.
	 * @param nec
	 */
	public void setConfiguration(NEConfiguration nec);
}
