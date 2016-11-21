/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.resource;

import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.resource.impl.PersistentResourceImpl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

public class PersistentResourceFactory implements Resource.Factory {

    protected PersistentResourceFactory() {
    }

    public static PersistentResourceFactory getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Resource createResource(URI uri) {
        Resource resource = null;
        if (PersistenceBackendFactoryRegistry.isRegistered(uri.scheme())) {
            resource = new PersistentResourceImpl(uri);
        }
        return resource;
    }

    private static class Holder {

        private static final PersistentResourceFactory INSTANCE = new PersistentResourceFactory();
    }
}
