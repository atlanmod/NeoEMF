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

package fr.inria.atlanmod.neoemf.eclipse.ui;

import fr.inria.atlanmod.neoemf.bind.Bindings;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.namespace.BundleNamespace;
import org.osgi.framework.wiring.BundleWiring;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class NeoUIPlugin extends AbstractUIPlugin {

    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "fr.inria.atlanmod.neoemf.eclipse.ui";

    /**
     * The NeoEMF ID.
     */
    public static final String CORE_ID = "fr.inria.atlanmod.neoemf.core";

    /**
     * The global logger listener that handles all logging events, and eventually transforms them into a UI callback.
     */
    private static final ILogListener LOG_LISTENER = (status, listener) -> {
        if (status.matches(IStatus.ERROR)) {
            StatusManager.getManager().handle(status, StatusManager.BLOCK);
        }
    };

    /**
     * The shared instance of this plug-in.
     */
    private static NeoUIPlugin SHARED_INSTANCE;

    /**
     * Constructs a new {@code NeoUIPlugin}.
     */
    public NeoUIPlugin() {
        SHARED_INSTANCE = this;
    }

    /**
     * Returns the shared instance of this plug-in.
     *
     * @return the shared instance
     */
    public static NeoUIPlugin getDefault() {
        return SHARED_INSTANCE;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path.
     *
     * @param path the path
     *
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    /**
     * Retrieves the {@link URL} of all bundles, within the given {@code context}, that are dependant from NeoEMF.
     *
     * @param context the OSGi context to explore
     *
     * @return the {@link URL}s of all bundles that are dependant from NeoEMF
     */
    private static Set<URL> getDependentBundles(BundleContext context) {
        long coreId = getCoreBundleId(context);

        // Filter all bundles that are dependant to the `neoemf-core` bundle and collect their URL
        return Arrays.stream(context.getBundles())
                .filter(b -> isDependentOn(coreId, b))
                .map(NeoUIPlugin::getUrl)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves the identifier of the core bundle, {@code fr.inria.atlanmod.neoemf.core}.
     *
     * @param context the OSGi context to explore
     *
     * @return the identifier of the core bundle
     */
    private static long getCoreBundleId(BundleContext context) {
        return context.getBundle().adapt(BundleWiring.class)
                .getRequiredWires(BundleNamespace.BUNDLE_NAMESPACE)
                .stream()
                .map(b -> b.getProviderWiring().getBundle())
                .filter(b -> b.getSymbolicName().startsWith(CORE_ID))
                .findAny()
                .<IllegalStateException>orElseThrow(IllegalStateException::new) // This should never happen
                .getBundleId();
    }

    /**
     * Checks that the {@code bundle} is wired to the bundle identified by the specified {@code bundleId}.
     *
     * @param bundleId the identifier of the bundle
     * @param bundle   the bundle to check
     *
     * @return {@code true} if the {@code bundle} is dependent
     */
    private static boolean isDependentOn(long bundleId, Bundle bundle) {
        return bundle.adapt(BundleWiring.class)
                .getRequiredWires(BundleNamespace.BUNDLE_NAMESPACE)
                .stream()
                .map(r -> r.getProviderWiring().getBundle().getBundleId())
                .anyMatch(id -> id == bundleId);
    }

    /**
     * Retrieves the {@link URL} of the given {@code bundle} by using the {@link FileLocator}.
     * <p>
     * If an error occurs, it will be ignored.
     *
     * @param bundle the bundle to locate
     *
     * @return an {@link Optional} containing the {@link URL} of the {@code bundle}, or {@link Optional#empty()} if the
     * {@code bundle} cannot be located
     */
    private static Optional<URL> getUrl(Bundle bundle) {
        URL url = null;

        try {
            url = FileLocator.getBundleFile(bundle).toURI().toURL();
        }
        catch (IOException ignored) {
        }

        return Optional.ofNullable(url);
    }

    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);

        getLog().addLogListener(LOG_LISTENER);

        // Add the URLs of all dependant bundles
        Bindings.addUrls(getDependentBundles(context));

        // Initialize the backend registry (force)
        BackendFactoryRegistry.getInstance().registerAll();

        // Initialize the metamodel registry
        MetamodelRegistry.getInstance().registerAll();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        getLog().removeLogListener(LOG_LISTENER);

        super.stop(context);
    }
}
