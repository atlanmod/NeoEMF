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

package fr.inria.atlanmod.neoemf.graph.blueprints.resources;

import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;

public interface BlueprintsResourceOptions extends PersistentResourceOptions {
    
    String OPTIONS_BLUEPRINTS_GRAPH_TYPE = "blueprints.graph";
    String OPTIONS_BLUEPRINTS_GRAPH_TYPE_DEFAULT = "com.tinkerpop.blueprints.impls.tg.TinkerGraph";
    
    enum EStoreGraphOption implements StoreOption {
        AUTOCOMMIT,
        DIRECT_WRITE,
    }
    
}