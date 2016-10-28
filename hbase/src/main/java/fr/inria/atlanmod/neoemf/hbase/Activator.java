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

package fr.inria.atlanmod.neoemf.hbase;

import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.hbase.datastore.HBasePersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.hbase.util.NeoHBaseURI;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

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
        NeoLogger.info("NeoEMF HBase plugin started");
        if (!PersistenceBackendFactoryRegistry.isRegistered(NeoHBaseURI.NEO_HBASE_SCHEME)) {
            PersistenceBackendFactoryRegistry.register(NeoHBaseURI.NEO_HBASE_SCHEME,
                    HBasePersistenceBackendFactory.getInstance());
            NeoLogger.info("HBase persistence backend registered");
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }
}
