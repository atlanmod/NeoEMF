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

import fr.inria.atlanmod.common.Stopwatch;
import fr.inria.atlanmod.common.log.Log;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Processor} that measures elapsed time between the start and the end of an I/O process.
 */
@ParametersAreNonnullByDefault
public class ProgressProcessor extends AbstractProcessor<Processor> {

    /**
     * The start instant.
     */
    private final Stopwatch stopwatch = Stopwatch.createUnstarted();

    /**
     * Constructs a new {@code ProgressProcessor} with the given {@code processor}.
     *
     * @param processor the processor to notify
     */
    public ProgressProcessor(Processor processor) {
        super(processor);
    }

    @Override
    public void onInitialize() {
        Log.info("Document analysis in progress...");
        stopwatch.start();

        notifyInitialize();
    }

    @Override
    public void onComplete() {
        stopwatch.stop();
        Log.info("Document analysis done in {0}", stopwatch.elapsed());

        notifyComplete();
    }
}