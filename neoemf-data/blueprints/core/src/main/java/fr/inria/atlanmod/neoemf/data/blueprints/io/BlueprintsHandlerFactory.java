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

package fr.inria.atlanmod.neoemf.data.blueprints.io;

import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.io.persistence.PersistenceHandler;

public class BlueprintsHandlerFactory {

    private BlueprintsHandlerFactory() {
    }

    /**
     * Creates a {@link PersistenceHandler} on the given {@code backend}.
     *
     * @param backend the persistence backend where data must persist
     * @param conflictResolution {@code true} if you want a conflict resolution feature
     *
     * @return a newly created {@link PersistenceHandler}
     */
    public static PersistenceHandler createPersistenceHandler(BlueprintsPersistenceBackend backend, boolean conflictResolution) {
        if (conflictResolution) {
            return new BlueprintsAwareHandler(backend);
        }
        else {
            return new BlueprintsNaiveHandler(backend);
        }
    }
}
