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

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.AbstractActivator;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;

import org.osgi.framework.BundleActivator;

/**
 * A {@link BundleActivator} that automatically registers an instance of {@link MapDbPersistenceBackendFactory} in the
 * global {@link PersistenceBackendFactoryRegistry} when the plugin is started.
 */
public class Activator extends AbstractActivator {

    @Override
    protected String name() {
        return "MapDB";
    }

    @Override
    protected String scheme() {
        return MapDbURI.SCHEME;
    }

    @Override
    protected PersistenceBackendFactory factory() {
        return MapDbPersistenceBackendFactory.getInstance();
    }
}
