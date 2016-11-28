/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.persistence;

import com.google.common.base.Stopwatch;

import fr.inria.atlanmod.neoemf.logging.NeoLogger;

/**
 * A delegated {@link PersistenceHandler} that gives the time to process.
 */
public class TimerPersistenceHandlerDecorator extends AbstractPersistenceHandlerDecorator {

    private static int id = 0;

    private final String name;

    private Stopwatch stopWatch;

    public TimerPersistenceHandlerDecorator(PersistenceHandler handler, String name) {
        super(handler);
        this.name = name;
    }

    public TimerPersistenceHandlerDecorator(PersistenceHandler handler) {
        this(handler, "dummy-" + ++id);
    }

    @Override
    public void processStartDocument() {
        NeoLogger.info("[{0}] Document analysis in progress...", name);
        stopWatch = Stopwatch.createStarted();

        super.processStartDocument();
    }

    @Override
    public void processEndDocument() {
        NeoLogger.info("[{0}] Document analysis done in {1}", name, stopWatch.stop());

        super.processEndDocument();
    }
}