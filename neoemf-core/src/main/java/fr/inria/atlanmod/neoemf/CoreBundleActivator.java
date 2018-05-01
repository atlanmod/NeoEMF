/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.neoemf.util.service.ServiceResolver;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The OSGi activator of this module.
 */
@VisibleForReflection
@ParametersAreNonnullByDefault
public final class CoreBundleActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) {
        ServiceResolver.getInstance().loadContext(context);
    }

    @Override
    public void stop(BundleContext context) {
        ServiceResolver.getInstance().unloadContext();
    }
}