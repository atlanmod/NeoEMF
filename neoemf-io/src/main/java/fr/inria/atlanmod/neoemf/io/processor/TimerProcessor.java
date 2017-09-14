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

import fr.inria.atlanmod.commons.Stopwatch;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.io.Handler;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Processor} that measures elapsed time between the start and the end of an I/O process.
 */
@ParametersAreNonnullByDefault
public class TimerProcessor extends AbstractProcessor<Handler> {

    /**
     * The start instant.
     */
    @Nonnull
    private final Stopwatch stopwatch = Stopwatch.createUnstarted();

    /**
     * Constructs a new {@code TimerProcessor} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public TimerProcessor(Handler handler) {
        super(handler);
    }

    @Override
    public void onInitialize() {
        Log.info("Migration in progress...");
        stopwatch.start();

        notifyInitialize();
    }

    @Override
    public void onComplete() {
        stopwatch.stop();
        Log.info("Migration done in {0}", stopwatch.elapsed());

        notifyComplete();
    }
}