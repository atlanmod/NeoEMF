package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.common.log.Log;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadFactory;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link Backend} that provides a global behavior about the closure.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractBackend implements Backend {

    /**
     * A set that holds all {@link Backend}s that need to be closed when the JVM will shutdown.
     */
    @Nonnull
    private static final Set<AbstractBackend> BACKENDS = new HashSet<>();

    static {
        ThreadFactory factory = task -> {
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            return thread;
        };

        Runtime.getRuntime().addShutdownHook(factory.newThread(() -> {
            int count = BACKENDS.size();

            for (AbstractBackend b : BACKENDS) {
                b.close(false);
            }

            if (count > 0) {
                Log.info("All back-ends have been properly closed ({0,number,#})", count);
            }
        }));
    }

    /**
     * Whether this backend is closed.
     */
    private boolean isClosed;

    /**
     * Constructs a new {@code AbstractBackend}.
     */
    protected AbstractBackend() {
        BACKENDS.add(this);
    }

    @Override
    public final void close() {
        close(true);
    }

    /**
     * Cleanly closes this back-end, and releases any system resources associated with it.
     * All modifications are saved before closing.
     * <p>
     * If the back-end is already closed, then invoking this method has no effect.
     *
     * @param clean {@code true} if the registry must be cleaned after closure
     */
    private void close(boolean clean) {
        if (isClosed) {
            return;
        }

        try {
            save();
            safeClose();
        }
        catch (Exception ignored) {
        }
        finally {
            isClosed = true;

            if (clean) {
                BACKENDS.remove(this);
            }
        }
    }

    /**
     * Cleanly closes the database, and releases any system resources associated with it.
     *
     * @throws IOException if an I/O error occurs during the closure
     */
    protected abstract void safeClose() throws IOException;
}
