/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.listener;

import fr.inria.atlanmod.commons.log.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Nonnegative;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link EventListener} that logs the progress of a migration.
 */
@ParametersAreNonnullByDefault
public class ProgressEventListener extends AbstractEventListener {

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
     * Constructs a new {@code ProgressEventListener} with the default period.
     *
     * @param stream the stream to watch
     */
    public ProgressEventListener(InputStream stream) {
        this(stream, 20_000);
    }

    /**
     * Constructs a new {@code ProgressEventListener} with the given {@code period}.
     *
     * @param stream the stream to watch
     * @param period the time between progress analysis, in milliseconds
     */
    public ProgressEventListener(InputStream stream, @Nonnegative long period) {
        this.stream = stream;
        this.period = period;

        try {
            this.totalSize = stream.available();
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
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
    }

    @Override
    public void onComplete() {
        task.cancel();
    }
}
