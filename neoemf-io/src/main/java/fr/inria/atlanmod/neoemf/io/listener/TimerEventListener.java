/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.listener;

import org.atlanmod.commons.log.Log;
import org.atlanmod.commons.time.Stopwatch;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link EventListener} that measures elapsed time between the start and the end of an I/O process.
 */
@ParametersAreNonnullByDefault
public class TimerEventListener extends AbstractEventListener {

    /**
     * The stopwatch to measure elapsed time.
     */
    @Nonnull
    private final Stopwatch stopwatch = Stopwatch.createUnstarted();

    @Override
    public void onInitialize() {
        Log.info("Migration in progress...");
        stopwatch.start();
    }

    @Override
    public void onComplete() {
        stopwatch.stop();
        Log.info("Migration done in {0}", stopwatch.elapsed());
    }
}
