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

import com.google.common.base.Stopwatch;

import fr.inria.atlanmod.neoemf.util.log.Log;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Processor} that measures elapsed time between the start and the end of an I/O process.
 */
@ParametersAreNonnullByDefault
public class TimerProcessor extends AbstractProcessor<Processor> {

    /**
     * The stopwatch.
     */
    private Stopwatch stopWatch;

    /**
     * Constructs a new {@code TimerProcessor} with the given {@code processors}.
     *
     * @param processors the processors to notify
     */
    public TimerProcessor(Processor... processors) {
        super(processors);
    }

    @Override
    public void onInitialize() {
        Log.info("Document analysis in progress...");
        stopWatch = Stopwatch.createStarted();

        notifyInitialize();
    }

    @Override
    public void onComplete() {
        Log.info("Document analysis done in {0}", stopWatch.stop());

        notifyComplete();
    }
}