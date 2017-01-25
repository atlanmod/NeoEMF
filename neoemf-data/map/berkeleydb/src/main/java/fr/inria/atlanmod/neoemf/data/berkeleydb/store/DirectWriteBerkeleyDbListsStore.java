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

package fr.inria.atlanmod.neoemf.data.berkeleydb.store;

import fr.inria.atlanmod.neoemf.annotations.Experimental;
import fr.inria.atlanmod.neoemf.data.map.core.MapBackend;
import fr.inria.atlanmod.neoemf.data.map.core.store.MapStoreWithLists;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * ???
 */
@Experimental
public class DirectWriteBerkeleyDbListsStore extends MapStoreWithLists<MapBackend> {

    /**
     * Constructs a new {@code DirectWriteBerkeleyDbListsStore} between the given {@code resource} and the
     * {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteBerkeleyDbListsStore(Resource.Internal resource, MapBackend backend) {
        super(resource, backend);
    }

}
