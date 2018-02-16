/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.examples;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * NeoEMF examples plugin.
 */
public class NeoEMFExamplesPlugin extends AbstractUIPlugin {

    /**
     * The plugin id.
     */
    public static final String PLUGIN_ID = "fr.inria.atlanmod.neoemf.eclipse.examples";

    private static NeoEMFExamplesPlugin instance;

    /**
     * Constructs a new instance of this plugin.
     */
    public NeoEMFExamplesPlugin() {
        instance = this;
    }

    /**
     * Returns the singleton instance of this plugin.
     *
     * @return the singleton instance of this plugin
     */
    public static NeoEMFExamplesPlugin getDefault() {
        if (instance == null) {
            return new NeoEMFExamplesPlugin();
        }
        return instance;
    }
}
