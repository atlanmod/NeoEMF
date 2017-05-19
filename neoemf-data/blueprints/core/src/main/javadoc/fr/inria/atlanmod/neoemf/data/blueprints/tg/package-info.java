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
 * Provides classes related to data management specific to a TinkerGraph implementation, under Blueprints.
 * <p>
 * The TinkerGraph implementation is the default graph backend used by NeoEMF. TinkerGraph is a simple, in-memory
 * graph that can be serialized as a GraphML document. Note that TinkerGraph implementation does not aim to scale
 * to large models, and is used to provide basic graph support to store models with no configuration. We recommand to 
 * use the Neo4j Blueprints implementation to store large models through Blueprints (see fr.inria.atlanmod.neoemf.data.blueprints.neo4j.*).
 */

package fr.inria.atlanmod.neoemf.data.blueprints.tg;