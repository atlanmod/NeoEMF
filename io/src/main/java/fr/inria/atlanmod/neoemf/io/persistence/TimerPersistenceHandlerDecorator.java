package fr.inria.atlanmod.neoemf.io.persistence;

import com.google.common.base.Stopwatch;

import fr.inria.atlanmod.neoemf.logging.NeoLogger;

/**
 * A delegated {@link PersistenceHandler} that gives the time to process.
 */
public class TimerPersistenceHandlerDecorator extends AbstractPersistenceHandlerDecorator {

    private final String name;

    private Stopwatch stopWatch;

    public TimerPersistenceHandlerDecorator(PersistenceHandler handler, String name) {
        super(handler);
        this.name = name;
    }

    @Override
    public void processStartDocument() throws Exception {
        NeoLogger.info("[{0}] Document analysis in progress...", name);
        stopWatch = Stopwatch.createStarted();

        super.processStartDocument();
    }

    @Override
    public void processEndDocument() throws Exception {
        NeoLogger.info("[{0}] Document analysis done in {1}", name, stopWatch.stop());

        super.processEndDocument();
    }
}