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

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import fr.inria.atlanmod.neoemf.AbstractActivator;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbURI;

import org.osgi.framework.BundleActivator;

/**
 * A {@link BundleActivator} that automatically registers a {@link BerkeleyDbBackendFactory} with its
 * associated {@link BerkeleyDbURI} in the global {@link BackendFactoryRegistry} when loading an OSGi bundle.
 * <p>
 * <b>Note:</b> This class should not be used in standard use.
 *
 * @see BerkeleyDbBackendFactory
 * @see BackendFactoryRegistry
 */
public class Activator extends AbstractActivator {

    @Override
    protected String name() {
        return BerkeleyDbBackend.NAME;
    }

    @Override
    protected String scheme() {
        return BerkeleyDbURI.SCHEME;
    }

    @Override
    protected BackendFactory factory() {
        return BerkeleyDbBackendFactory.getInstance();
    }
}
