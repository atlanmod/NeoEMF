/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/

package fr.inria.atlanmod.neo4emf;

import org.eclipse.emf.ecore.EPackage;

/**
 * @author sunye
 *
 */
public interface PersistentPackage extends EPackage {
	
	/**
	 * The RelationshipMapping maps EMF EReferences Ids and Neo4j Relationships.
	 * @return the relationship mapping.
	 */
	public RelationshipMapping getRelationshipMapping();
}
