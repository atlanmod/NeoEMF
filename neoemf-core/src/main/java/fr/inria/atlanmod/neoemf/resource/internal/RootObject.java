/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.resource.internal;

import fr.inria.atlanmod.neoemf.core.DefaultPersistentEObject;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.ecore.resource.Resource;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The {@link PersistentEObject} that represents the root entry point for a {@link PersistentResource}.
 */
@ParametersAreNonnullByDefault
final class RootObject extends DefaultPersistentEObject {

    /**
     * Constructs a new {@code RootObject} with the given {@code resource}.
     *
     * @param resource the resource containing this object.
     */
    public RootObject(Resource.Internal resource) {
        super(PersistentResource.ROOT_ID);
        eSetDirectResource(resource);
    }
}
