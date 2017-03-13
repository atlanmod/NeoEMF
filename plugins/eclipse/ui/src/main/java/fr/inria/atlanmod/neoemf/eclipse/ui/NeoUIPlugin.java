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
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbURI;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;

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

    private static final ILogListener logListener = (status, listener) -> {
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
     * Registers all instances of {@link fr.inria.atlanmod.neoemf.data.BackendFactory} in the
     * {@link BackendFactoryRegistry}.
     * <p/>
     * Needed because auto-registration doesn't work if only static {@link String} are accessed before resource loading.
     * This happens when an Eclipse instance is loaded with an opened NeoEMF editor (only {@link BlueprintsURI#SCHEME}
     * is accessed).
     */
    private static void registerFactories() {
        if (!BackendFactoryRegistry.isRegistered(BlueprintsURI.SCHEME)) {
            BackendFactoryRegistry.register(BlueprintsURI.SCHEME, BlueprintsBackendFactory.getInstance());
        }
        if (!BackendFactoryRegistry.isRegistered(MapDbURI.SCHEME)) {
            BackendFactoryRegistry.register(MapDbURI.SCHEME, MapDbBackendFactory.getInstance());
        }
        if (!BackendFactoryRegistry.isRegistered(BerkeleyDbURI.SCHEME)) {
            BackendFactoryRegistry.register(BerkeleyDbURI.SCHEME, BerkeleyDbBackendFactory.getInstance());
        }
    }

    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        getLog().addLogListener(logListener);
        registerFactories();
        MetamodelRegistry.getInstance();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        getLog().removeLogListener(logListener);
        super.stop(context);
    }
}
