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

package fr.inria.atlanmod.neoemf.data.blueprints.option;

import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

import fr.inria.atlanmod.neoemf.option.PersistentResourceOptions;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * {@link PersistentResourceOptions} that hold Blueprints related resource-level features, such as the Blueprints
 * implementation used.
 */
@ParametersAreNonnullByDefault
public interface BlueprintsResourceOptions extends PersistentResourceOptions {

    /**
     * The Blueprints option key to define the graph implementation to use.
     */
    String GRAPH_TYPE = "blueprints.graph";

    /**
     * The default option value to define {@link TinkerGraph} as the graph implementation to use.
     */
    String GRAPH_TYPE_DEFAULT = com.tinkerpop.blueprints.impls.tg.TinkerGraph.class.getName();
}