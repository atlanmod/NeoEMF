package fr.inria.atlanmod.neoemf.examples;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * ATL examples plugin.
 */
public class NeoEMFExamplesPlugin extends AbstractUIPlugin {

    /**
     * The plugin id.
     */
    public static final String PLUGIN_ID = "fr.inria.atlanmod.neoemf.examples";

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
