/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.common.log.Log;

import java.time.Duration;
import java.time.Instant;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Processor} that measures elapsed time between the start and the end of an I/O process.
 */
@ParametersAreNonnullByDefault
public class TimerProcessor extends AbstractProcessor<Processor> {

    /**
     * The start instant.
     */
    private final Stopwatch stopwatch;

    /**
     * Constructs a new {@code TimerProcessor} with the given {@code processors}.
     *
     * @param processors the processors to notify
     */
    public TimerProcessor(Processor... processors) {
        super(processors);
        stopwatch = new Stopwatch();
    }

    @Override
    public void onInitialize() {
        Log.info("Document analysis in progress...");
        stopwatch.start();

        notifyInitialize();
    }

    @Override
    public void onComplete() {
        Log.info("Document analysis done in {0}", stopwatch.stop());

        notifyComplete();
    }

    /**
     * An object that measures elapsed time.
     */
    private static class Stopwatch {

        /**
         * The start instant.
         */
        private Instant start;

        /**
         * Starts the stopwatch.
         */
        public void start() {
            start = Instant.now();
        }

        /**
         * Stops the stopwatch.
         *
         * @return the elapsed time between the call of {@link #start()} and {@link Instant#now()}
         */
        public Duration stop() {
            Instant end = Instant.now();
            return Duration.between(start, end);
        }
    }
}