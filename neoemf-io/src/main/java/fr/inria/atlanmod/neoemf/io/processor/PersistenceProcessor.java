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

import fr.inria.atlanmod.neoemf.io.persistence.PersistenceHandler;

/**
 * A {@link Processor} that notifies registered {@link PersistenceHandler} of events, without any treatment.
 */
public final class PersistenceProcessor extends AbstractProcessor implements Processor {

    /**
     * Constructs a new {@code PersistenceProcessor} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public PersistenceProcessor(PersistenceHandler handler) {
        super(handler);
    }
}
