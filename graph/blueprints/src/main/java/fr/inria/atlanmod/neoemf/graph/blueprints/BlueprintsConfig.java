package fr.inria.atlanmod.neoemf.graph.blueprints;

import org.apache.commons.configuration.Configuration;

import java.io.File;

public interface BlueprintsConfig {

    void putDefaultConfiguration(Configuration currentConfiguration, File dbLocation);

    // TODO: All implementations are empty. Is this method really necessary ?
    void setGlobalSettings();
}
