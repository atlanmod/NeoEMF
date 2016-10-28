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

package fr.inria.atlanmod.neoemf.graph.blueprints;

import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
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
        NeoLogger.info("NeoEMF Graph.Blueprints plugin started");
        if (!PersistenceBackendFactoryRegistry.isRegistered(NeoBlueprintsURI.NEO_GRAPH_SCHEME)) {
            PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.NEO_GRAPH_SCHEME,
                    BlueprintsPersistenceBackendFactory.getInstance());
            NeoLogger.info("Graph persistence backend registered");
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }
}
