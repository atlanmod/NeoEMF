package fr.inria.atlanmod.neoemf.extension;

import fr.inria.atlanmod.neoemf.logging.Logger;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.junit.runner.Description;

public class Watcher extends org.junit.rules.TestWatcher {

    private static final Logger LOG = NeoLogger.customLogger("test");

    @Override
    protected void succeeded(Description description) {
        LOG.info("[INFO] --- Succeeded");
    }

    @Override
    protected void failed(Throwable e, Description description) {
        LOG.warn("[WARN] --- Failed");
    }

    @Override
    protected void starting(Description description) {
        LOG.info("");
        LOG.info("[INFO] --- Running " + description.getMethodName());
    }

    @Override
    protected void finished(Description description) {
        LOG.info("");
    }
}
