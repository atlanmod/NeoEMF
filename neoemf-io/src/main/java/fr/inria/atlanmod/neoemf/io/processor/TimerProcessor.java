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

import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import static java.util.Objects.nonNull;

/**
 * An {@link Processor} that measures elapsed time between the start and the end of an I/O process.
 */
public class TimerProcessor extends AbstractProcessor {

    /**
     * The current number of created {@code TimerProcessor}s, used for name generation.
     */
    private static int id = 0;

    /**
     * The name of this handler.
     */
    private final String name;

    /**
     * The stopwatch.
     */
    private Stopwatch stopWatch;

    /**
     * Constructs a new {@code TimerProcessor} with the given {@code name} on the underlying
     * {@code handler}.
     *
     * @param handler the underlying handler
     * @param name    the name of this handler
     */
    public TimerProcessor(Processor handler, String name) {
        super(handler);
        this.name = name;
    }

    /**
     * Constructs a new {@code TimerProcessor} on the underlying {@code handler}.
     *
     * @param handler the underlying handler
     */
    public TimerProcessor(Processor handler) {
        this(handler, "timer-" + ++id);
    }

    @Override
    public void processStartDocument() {
        NeoLogger.info("[{0}] Document analysis in progress...", name);
        stopWatch = Stopwatch.createStarted();

        super.processStartDocument();
    }

    @Override
    public void processEndDocument() {
        if (nonNull(stopWatch)) {
            NeoLogger.info("[{0}] Document analysis done in {1}", name, stopWatch.stop());
        }
        else {
            NeoLogger.info("[{0}] Document analysis done", name);
        }

        super.processEndDocument();
    }
}