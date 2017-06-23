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

import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.BundleContext;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

public class NeoUIPlugin extends AbstractUIPlugin {

    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "fr.inria.atlanmod.neoemf.eclipse.ui";

    private static final ILogListener LOG_LISTENER = (status, listener) -> {
        if (status.matches(IStatus.ERROR)) {
            StatusManager.getManager().handle(status, StatusManager.BLOCK);
        }
    };

    private static NeoUIPlugin SHARED_INSTANCE;

    public NeoUIPlugin() {
        SHARED_INSTANCE = this;
    }

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
     * Retrieves the {@link URL}s of the bundles within the given {@code context}.
     *
     * @param context the OSGi context to explore
     *
     * @return an array of {@link URL}
     */
    private static URL[] getUrls(BundleContext context) {
        return Arrays.stream(context.getBundles())
                .filter(b -> b.getSymbolicName().startsWith("fr.inria.atlanmod.neoemf"))
                .map(b -> {
                    try {
                        return FileLocator.getBundleFile(b).toURI().toURL();
                    }
                    catch (IOException ignored) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toArray(URL[]::new);
    }

    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);

        getLog().addLogListener(LOG_LISTENER);

        // Initialize the backend registry (force)
        BackendFactoryRegistry.getInstance().registerAll(getUrls(context));

        // Initialize the metamodel registry
        MetamodelRegistry.getInstance().registerAll();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        getLog().removeLogListener(LOG_LISTENER);

        super.stop(context);
    }
}
