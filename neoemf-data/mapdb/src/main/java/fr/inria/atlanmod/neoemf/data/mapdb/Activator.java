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

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        NeoLogger.info("NeoEMF MapDB plugin started");
        if (!PersistenceBackendFactoryRegistry.isRegistered(MapDbURI.SCHEME)) {
            PersistenceBackendFactoryRegistry.register(MapDbURI.SCHEME,
                    MapDbPersistenceBackendFactory.getInstance());
            NeoLogger.info("MapDB persistence backend registered");
        }
    }

    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }
}
