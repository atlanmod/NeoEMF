/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.ui;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.BundleContext;

/**
 * The NeoEMF plugin.
 */
public class NeoUIPlugin extends AbstractUIPlugin {

    /**
     * The unique identifier of this plugin.
     */
    public static final String PLUGIN_ID = "fr.inria.atlanmod.neoemf.eclipse.ui";

    /**
     * The unique identifier of NeoEMF.
     */
    public static final String CORE_ID = "fr.inria.atlanmod.neoemf.core";

    /**
     * The shared instance of this plug-in.
     */
    private static NeoUIPlugin sharedInstance;

    /**
     * The global logger listener that handles all logging events, and eventually transforms them into a UI callback.
     */
    private final ILogListener logListener = (status, listener) -> {
        if (status.matches(IStatus.ERROR)) {
            StatusManager.getManager().handle(status, StatusManager.BLOCK);
        }
    };

    /**
     * Constructs a new {@code NeoUIPlugin}.
     */
    public NeoUIPlugin() {
        sharedInstance = this;
    }

    /**
     * Returns the shared instance of this plug-in.
     *
     * @return the shared instance
     */
    public static NeoUIPlugin getDefault() {
        return sharedInstance;
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

        getLog().addLogListener(logListener);

        // Initialize the metamodel registry
        MetamodelRegistry.getInstance().registerAll();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        getLog().removeLogListener(logListener);

        super.stop(context);
    }
}
