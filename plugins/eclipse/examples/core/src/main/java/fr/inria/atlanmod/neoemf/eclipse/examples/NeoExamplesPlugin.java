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
public class NeoExamplesPlugin extends AbstractUIPlugin {

    /**
     * The unique identifier of this plugin.
     */
    public static final String PLUGIN_ID = "fr.inria.atlanmod.neoemf.eclipse.examples";

    public static final String BUNDLE_ID = "fr.inria.atlanmod.neoemf.eclipse.examples.core";

    private static NeoExamplesPlugin instance;

    /**
     * Constructs a new instance of this plugin.
     */
    public NeoExamplesPlugin() {
        instance = this;
    }

    /**
     * Returns the singleton instance of this plugin.
     *
     * @return the singleton instance of this plugin
     */
    public static NeoExamplesPlugin getDefault() {
        if (instance == null) {
            return new NeoExamplesPlugin();
        }
        return instance;
    }
}
