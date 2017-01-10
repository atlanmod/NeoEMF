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

package fr.inria.atlanmod.neoemf;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public abstract class AbstractActivator implements BundleActivator {

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        NeoLogger.info("NeoEMF-{0} plugin started", name());
        if (!PersistenceBackendFactoryRegistry.isRegistered(scheme())) {
            PersistenceBackendFactoryRegistry.register(scheme(), factory());
            NeoLogger.info("{0} backend registered", name());
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        NeoLogger.info("NeoEMF-{0} plugin stopped", name());
    }

    protected abstract String name();

    protected abstract String scheme();

    protected abstract PersistenceBackendFactory factory();
}
