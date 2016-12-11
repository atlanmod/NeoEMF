package fr.inria.atlanmod.neoemf.logging;

import org.apache.logging.log4j.LogManager;

abstract class AbstractLogger implements Logger {

    private final org.apache.logging.log4j.Logger logger;

    protected AbstractLogger(String name) {
        this.logger = LogManager.getLogger(name);
    }

    protected org.apache.logging.log4j.Logger logger() {
        return logger;
    }
}
