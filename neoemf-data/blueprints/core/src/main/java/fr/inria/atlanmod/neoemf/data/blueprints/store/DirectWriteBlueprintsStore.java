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
import fr.inria.atlanmod.neoemf.data.store.AbstractDirectWriteStore;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
 */
public class DirectWriteBlueprintsStore extends AbstractDirectWriteStore<BlueprintsPersistenceBackend> {

    /**
     * Constructs a new {@code DirectWriteBlueprintsStore} between the given {@code resource} and the
     * {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteBlueprintsStore(Resource.Internal resource, BlueprintsPersistenceBackend backend) {
        super(resource, backend);
    }

    /**
     * Computes efficiently {@code allInstances} operation by using underlying graph facilities. This method uses
     * database indices to avoid costly traversal of the entire model.
     *
     * @param eClass the {@link EClass} to get the instances of
     * @param strict set to {@code true} if the method should look for instances of {@code eClass} only, set to {@code
     *               false} if the method should also return elements that are subclasses of {@code eClass}
     */
    @Override
    public Iterable<EObject> getAllInstances(EClass eClass, boolean strict) {
        return StreamSupport.stream(backend.getAllInstances(eClass, strict).spliterator(), false)
                .map(this::eObject)
                .collect(Collectors.toList());
    }
}
