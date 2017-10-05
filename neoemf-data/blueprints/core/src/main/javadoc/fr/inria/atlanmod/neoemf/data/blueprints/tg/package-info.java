/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

/**
 * Provides classes related to data management specific to a TinkerGraph implementation, under Blueprints.
 * <p>
 * The TinkerGraph implementation is the default graph backend used by NeoEMF. TinkerGraph is a simple, in-memory graph
 * that can be serialized as a GraphML document. TinkerGraph implementation does not aim to scale to large models, and
 * is used to provide basic graph support to store models with no configuration. We recommand to use the Neo4j
 * Blueprints implementation to store large models through Blueprints (see {@code fr.inria.atlanmod.neoemf.data.blueprints.neo4j.*}).
 */

package fr.inria.atlanmod.neoemf.data.blueprints.tg;