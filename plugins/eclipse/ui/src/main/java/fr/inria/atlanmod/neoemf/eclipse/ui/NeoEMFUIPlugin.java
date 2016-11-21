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

package fr.inria.atlanmod.neoemf.eclipse.ui;

import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.map.util.MapURI;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.BundleContext;

public class NeoEMFUIPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "fr.inria.atlanmod.neoemf.eclipse.ui"; //$NON-NLS-1$

    // The shared instance
    private static NeoEMFUIPlugin plugin;

    private static ILogListener logListener = (status, plugin1) -> {
        if (status.matches(IStatus.ERROR)) {
            StatusManager.getManager().handle(status, StatusManager.BLOCK);
        }
    };

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static NeoEMFUIPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given
     * plug-in relative path
     *
     * @param path the path
     *
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        getDefault().getLog().addLogListener(logListener);
        /*
         * Needed because auto-registration doesn't work if only static String are accessed before resource loading.
		 * This happens when an eclipse instance is loaded with an opened NeoEMF editor
		 * (only NeoBlueprintsURI.NEO_GRAPH_SCHEME is accessed)
		 */
        if (!PersistenceBackendFactoryRegistry.isRegistered(BlueprintsURI.SCHEME)) {
            PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME, BlueprintsPersistenceBackendFactory.getInstance());
        }
        if (!PersistenceBackendFactoryRegistry.isRegistered(MapURI.SCHEME)) {
            PersistenceBackendFactoryRegistry.register(MapURI.SCHEME, MapPersistenceBackendFactory.getInstance());
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        getDefault().getLog().removeLogListener(logListener);
        plugin = null;
        super.stop(context);
    }
}