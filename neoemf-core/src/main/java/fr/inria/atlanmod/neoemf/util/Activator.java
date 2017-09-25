package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.neoemf.bind.Bindings;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The OSGi activator of this module.
 */
@VisibleForReflection
@ParametersAreNonnullByDefault
public final class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        Bindings.withContext(context);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        // Do nothing
    }
}
