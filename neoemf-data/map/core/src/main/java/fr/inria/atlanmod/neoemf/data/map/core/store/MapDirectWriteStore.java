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

package fr.inria.atlanmod.neoemf.data.map.core.store;

import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractDirectWriteStore;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * An abstract {@link AbstractDirectWriteStore} that redirects certain methods according to the instance of the
 * encountered {@link EStructuralFeature}. If the subclass does not re-implement the inherited methods of EMF, the call
 * is automatically redirected to the associated method that begins with the same name.
 *
 * @param <P> the type of the supported {@link PersistenceBackend}
 */
public abstract class MapDirectWriteStore<P extends PersistenceBackend> extends AbstractDirectWriteStore<P> {

    /**
     * Constructs a new {@code MapDirectWriteStore} between the given {@code resource} and the {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public MapDirectWriteStore(Resource.Internal resource, P backend) {
        super(resource, backend);
    }

}
