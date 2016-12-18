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

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDBURI;
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
        NeoLogger.info("NeoEMF BerkeleyDB plugin started");
        if (!PersistenceBackendFactoryRegistry.isRegistered(BerkeleyDBURI.SCHEME)) {
            PersistenceBackendFactoryRegistry.register(BerkeleyDBURI.SCHEME,
                    BerkeleyDBPersistenceBackendFactory.getInstance());
            NeoLogger.info("BerkeleyDB persistence backend registered");
        }
    }

    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }
}
