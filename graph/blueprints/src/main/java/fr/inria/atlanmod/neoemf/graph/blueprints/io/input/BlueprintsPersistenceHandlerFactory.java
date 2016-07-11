/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.graph.blueprints.io.input;

import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.io.PersistenceHandler;

/**
 *
 */
public class BlueprintsPersistenceHandlerFactory {

    private BlueprintsPersistenceHandlerFactory() {
    }

    /**
     * Creates a {@link PersistenceHandler persistence handler} on the given {@code persistenceBackend}.
     *
     * @param persistenceBackend the persistence backend where data must persist
     * @param conflictResolution {@code true} if you want a conflict resolution feature
     *
     * @return a newly created {@link PersistenceHandler persistence handler}
     */
    public static PersistenceHandler createPersistenceHandler(BlueprintsPersistenceBackend persistenceBackend, boolean conflictResolution) {
        if (conflictResolution) {
            return new BlueprintsConflictsResolverPersistenceHandler(persistenceBackend);
        } else {
            return new BlueprintsNotConflictsResolverPersistenceHandler(persistenceBackend);
        }
    }
}
