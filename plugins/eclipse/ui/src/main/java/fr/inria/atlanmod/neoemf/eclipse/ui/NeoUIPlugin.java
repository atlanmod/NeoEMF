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

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.BundleContext;

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

    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);

        getLog().addLogListener(LOG_LISTENER);

        // Initialize the metamodel registry
        MetamodelRegistry.getInstance().registerAll();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        getLog().removeLogListener(LOG_LISTENER);

        super.stop(context);
    }
}
