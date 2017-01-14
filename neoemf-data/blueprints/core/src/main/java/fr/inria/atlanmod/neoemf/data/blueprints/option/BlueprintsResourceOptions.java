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

import fr.inria.atlanmod.neoemf.option.PersistentResourceOptions;

/**
 * An implementation of {@link PersistentResourceOptions} holding Blueprints related resource-level features such as the
 * Blueprints implementation used or the autocommit chunk size.
 */
public interface BlueprintsResourceOptions extends PersistentResourceOptions {

    /**
     * The Blueprints option key to define the graph implementation to use
     */
    String GRAPH_TYPE = "blueprints.graph";

    /**
     * The option key to define the number of operations between each commit in autocommit mode
     */
    String AUTOCOMMIT_CHUNK = "autocommit.chunk";

    /**
     * The default option value to define TinkerGraph as the graph implementation to use
     */
    String GRAPH_TYPE_DEFAULT = "com.tinkerpop.blueprints.impls.tg.TinkerGraph";
}