package fr.inria.atlanmod.neoemf.graph.blueprints;

import org.apache.commons.configuration.Configuration;

import java.io.File;

public interface BlueprintsConfig {

    void putDefaultConfiguration(Configuration currentConfiguration, File dbLocation);

    void setGlobalSettings();
}
