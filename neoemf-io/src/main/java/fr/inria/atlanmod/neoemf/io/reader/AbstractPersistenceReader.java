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

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.io.AbstractNotifier;
import fr.inria.atlanmod.neoemf.io.Handler;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 *
 */
@ParametersAreNonnullByDefault
public class AbstractPersistenceReader extends AbstractNotifier<Handler> implements PersistenceReader {

    /**
     * The identifier of the root element.
     */
    private final static Id ROOT_ID = StringId.of("ROOT");

    /**
     * The property key used by the root element to define its content.
     */
    private static final String ROOT_FEATURE_NAME = "eContents";

    /**
     * Constructs a new {@code AbstractPersistenceReader} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public AbstractPersistenceReader(Handler handler) {
        super(handler);
    }

    @Override
    public void read(PersistenceBackend backend) {
        // TODO Implement this method
    }
}
