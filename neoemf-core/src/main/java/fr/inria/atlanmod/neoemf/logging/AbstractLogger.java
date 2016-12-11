package fr.inria.atlanmod.neoemf.logging;

import org.apache.logging.log4j.LogManager;

abstract class AbstractLogger implements Logger {

    private final org.apache.logging.log4j.Logger logger;

    protected AbstractLogger() {
        this(LogManager.getRootLogger());
    }

    protected AbstractLogger(String name) {
        this(LogManager.getLogger(name));
    }

    private AbstractLogger(org.apache.logging.log4j.Logger logger) {
        this.logger = logger;
    }

    protected org.apache.logging.log4j.Logger logger() {
        return logger;
    }
}
