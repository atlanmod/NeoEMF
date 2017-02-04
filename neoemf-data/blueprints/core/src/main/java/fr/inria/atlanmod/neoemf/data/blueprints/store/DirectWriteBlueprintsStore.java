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

package fr.inria.atlanmod.neoemf.data.blueprints.store;

import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.DefaultDirectWriteStore;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore} that translates model-level operations to Blueprints
 * calls.
 * <p>
 * This class implements the {@link fr.inria.atlanmod.neoemf.data.store.PersistentStore} interface that defines a set of
 * operations to implement in order to allow EMF persistence delegation. If this store is used, every method call and
 * property access on {@link fr.inria.atlanmod.neoemf.core.PersistentEObject} is forwarded to this class, that takes
 * care of the database serialization and deserialization using its embedded {@link BlueprintsPersistenceBackend}.
 * <p>
 * This store can be used as a base store that can be complemented by plugging decorator stores on top of it (see {@link
 * fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator} subclasses) to provide additional features such
 * as caching or logging.
 *
 * @see fr.inria.atlanmod.neoemf.core.PersistentEObject
 * @see BlueprintsPersistenceBackend
 * @see fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator
 *
 * @deprecated Use {@link DefaultDirectWriteStore} instead
 */
@Deprecated
public class DirectWriteBlueprintsStore extends DefaultDirectWriteStore<BlueprintsPersistenceBackend> {

    /**
     * Constructs a new {@code DirectWriteBlueprintsStore} between the given {@code resource} and the
     * {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteBlueprintsStore(PersistentResource resource, BlueprintsPersistenceBackend backend) {
        super(resource, backend);
    }
}
