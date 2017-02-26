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
import fr.inria.atlanmod.neoemf.util.logging.Log;

import org.eclipse.emf.common.util.URI;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * A {@link BundleActivator} that automatically registers a {@link PersistenceBackendFactory} with its associated
 * {@link URI URI} in the global {@link PersistenceBackendFactoryRegistry} when loading an OSGi bundle.
 * <p>
 * <b>Note:</b> This class should not be used in standard use.
 */
public abstract class AbstractActivator implements BundleActivator {

    /**
     * {@inheritDoc}
     * <p>
     * Registers the {@link PersistenceBackendFactory} from {@link #factory()}, with its {@link URI URI} scheme from
     * {@link #scheme()}, in the {@link PersistenceBackendFactoryRegistry registry} if it's not already.
     *
     * @param bundleContext the execution context of the bundle being started
     */
    @Override
    public final void start(BundleContext bundleContext) throws Exception {
        Log.info("NeoEMF-{0} plugin started", name());
        if (!PersistenceBackendFactoryRegistry.isRegistered(scheme())) {
            PersistenceBackendFactoryRegistry.register(scheme(), factory());
            Log.info("{0} backend registered", name());
        }
    }

    @Override
    public final void stop(BundleContext bundleContext) throws Exception {
        Log.info("NeoEMF-{0} plugin stopped", name());
    }

    /**
     * Returns the name of this bundle.
     *
     * @return the name
     */
    protected abstract String name();

    /**
     * Returns the {@link URI URI} scheme associated with this bundle.
     *
     * @return the {@link URI} scheme
     */
    protected abstract String scheme();

    /**
     * Returns the {@link PersistenceBackendFactory} associated with this bundle.
     *
     * @return the factory
     */
    protected abstract PersistenceBackendFactory factory();
}
