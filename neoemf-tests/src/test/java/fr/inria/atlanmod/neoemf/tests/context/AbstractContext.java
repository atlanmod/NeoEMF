package fr.inria.atlanmod.neoemf.tests.context;

import fr.inria.atlanmod.neoemf.logging.NeoLogger;

/**
 *
 */
public abstract class AbstractContext implements Context {

    public AbstractContext() {
        NeoLogger.info("Initialize a new context : " + name());
    }
}
