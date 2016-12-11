package fr.inria.atlanmod.neoemf.logging;

import com.google.common.util.concurrent.MoreExecutors;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class AsyncLogger extends AbstractLogger {

    /**
     * The number of threads in the pool. Use a single thread to ensure the log order.
     */
    private static final int THREADS = 1;

    private static final int TERMINATION_TIMEOUT_MS = 100;

    private static final ExecutorService pool =
            MoreExecutors.getExitingExecutorService(
                    (ThreadPoolExecutor) Executors.newFixedThreadPool(THREADS),
                    TERMINATION_TIMEOUT_MS, TimeUnit.MILLISECONDS);

    public AsyncLogger(String name) {
        super(name);
    }

    @Override
    public void logMessage(Level level, Throwable throwable, CharSequence pattern, Object... args) {
        execute(() -> {
            try {
                logger().log(level.level(), () -> MessageFormat.format(pattern.toString(), args), throwable);
            }
            catch (Exception ignore) {
            }
        });
    }

    private void execute(Runnable runnable) {
        try {
            // Asynchronous call
            pool.submit(runnable);
        }
        catch (RejectedExecutionException e) {
            // Synchronous call
            runnable.run();
        }
    }
}
