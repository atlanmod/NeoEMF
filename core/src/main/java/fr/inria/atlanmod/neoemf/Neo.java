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
package fr.inria.atlanmod.neoemf;

import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunye on 17/03/15.
 */
public class Neo {

    private final Map<String, PersistentResourceFactory> factories = new HashMap<String, PersistentResourceFactory>();

    public Resource createResource(URI uri) {
        Resource result = null;
        PersistentResourceFactory factory = factories.get(uri.scheme());
        if (factory != null) {
            result = factory.createResource(uri);
        }

        return result;
    }

    public void registerPersistentResourceFactory(String scheme, PersistentResourceFactory factory) {
        assert scheme !=  null : "Null scheme";
        assert factory != null : "Null factory";

        this.factories.put(scheme, factory);
    }
}
