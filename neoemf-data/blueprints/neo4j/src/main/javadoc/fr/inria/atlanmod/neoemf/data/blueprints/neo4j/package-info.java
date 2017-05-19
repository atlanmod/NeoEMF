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

/**
 * Provides classes related to data management specific to a Neo4j implementation, under Blueprints.
 * <p>
 * This package defines Neo4j specific options (such as database caching strategy, memory buffer sizes, etc.) that can
 * be set in addition to the generic {@link fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions}
 * to configure precisely the behavior of a Neo4j database.
 * <p>
 * This package does not provide a dedicated {@link fr.inria.atlanmod.neoemf.data.PersistenceBackend}, see
 * {@link fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackend} to create a new Neo4j-Blueprints
 * backend.
 */

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j;