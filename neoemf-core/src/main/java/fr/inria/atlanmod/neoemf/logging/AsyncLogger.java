package fr.inria.atlanmod.neoemf.logging;

import org.apache.logging.log4j.Level;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.MoreExecutors.getExitingExecutorService;
import static java.util.concurrent.Executors.newFixedThreadPool;

class AsyncLogger extends AbstractLogger {

    private static final ExecutorService pool = getExitingExecutorService((ThreadPoolExecutor) newFixedThreadPool(1), 1, TimeUnit.MILLISECONDS);

    public AsyncLogger() {
        super();
    }

    public AsyncLogger(String name) {
        super(name);
    }

    public static Logger getRootLogger() {
        return RootLoggerHolder.ROOT;
    }

    public void logMessage(Level level, Throwable throwable, String pattern, Object... args) {
        Runnable loggerCall = () -> {
            try {
                logger().log(level, () -> MessageFormat.format(pattern, args), throwable);
            }
            catch (Exception ignore) {
            }
        };

        try {
            // Asynchronous call
            pool.submit(loggerCall);
        }
        catch (RejectedExecutionException e) {
            // Synchronous call
            loggerCall.run();
        }
    }

    private static class RootLoggerHolder {

        private static final Logger ROOT = new AsyncLogger();
    }
}
