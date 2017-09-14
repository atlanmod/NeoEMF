package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.io.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Nonnegative;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Processor} that logs the progress of a migration.
 */
@ParametersAreNonnullByDefault
public class ProgressProcessor extends AbstractProcessor<Handler> {

    /**
     * The stream to watch.
     */
    private final InputStream stream;

    /**
     * The total size of the {@link #stream}.
     */
    private final double totalSize;

    /**
     * The time between progress analysis, in milliseconds
     */
    private final long period;

    /**
     * The progress analysis task.
     */
    private Timer task;

    /**
     * Constructs a new {@code ProgressProcessor} with the given {@code handler}.
     *
     * @param handler the handler to notify
     * @param stream  the stream to watch
     */
    public ProgressProcessor(Handler handler, InputStream stream) {
        this(handler, stream, 20_000);
    }

    /**
     * Constructs a new {@code ProgressProcessor} with the given {@code handler}.
     *
     * @param handler the handler to notify
     * @param stream  the stream to watch
     * @param period  the time between progress analysis, in milliseconds
     */
    public ProgressProcessor(Handler handler, InputStream stream, @Nonnegative long period) {
        super(handler);
        this.stream = stream;
        this.period = period;

        try {
            this.totalSize = stream.available();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onInitialize() {
        task = new Timer();
        task.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    final double progress = totalSize / (totalSize - stream.available());
                    Log.info("Progress: {0,number,0.00%}", progress);
                }
                catch (IOException e) {
                    Log.warn(e);
                    task.cancel();
                }
            }
        }, 0, period);

        notifyInitialize();
    }

    @Override
    public void onComplete() {
        task.cancel();

        notifyComplete();
    }
}