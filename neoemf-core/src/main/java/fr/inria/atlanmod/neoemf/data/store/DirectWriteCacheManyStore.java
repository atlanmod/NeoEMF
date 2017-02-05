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

package fr.inria.atlanmod.neoemf.data.store;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Iterables;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;

import java.util.Optional;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore} that uses an internal cache to store values and
 * references that are part of multi-valued {@link org.eclipse.emf.ecore.EStructuralFeature}s to speed-up their access.
 * <p>
 * Large multi-valued {@link org.eclipse.emf.ecore.EStructuralFeature}s can be an execution time bottleneck in the
 * database implementation because any element access forces the underlying database engine to load all elements
 * corresponding to the {@link org.eclipse.emf.ecore.EStructuralFeature}. We overcome this limitation by caching all the
 * elements involved in multi-valued {@link org.eclipse.emf.ecore.EStructuralFeature}s the first time they are
 * traversed, limiting database access.
 * <p>
 * This store can be used as a base store that can be complemented by plugging decorator stores on top of it (see {@link
 * fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator} subclasses) to provide additional features such
 * as caching or logging.
 *
 * @param <P> the type of the supported {@link PersistenceBackend}
 *
 * @see fr.inria.atlanmod.neoemf.data.store.DirectWriteStore
 * @see fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator
 */
public class DirectWriteCacheManyStore<P extends PersistenceBackend> extends DefaultDirectWriteStore<P> {

    /**
     * In-memory cache that holds the recently loaded multivalued values, identified by the associated
     * {@link FeatureKey}.
     */
    private final Cache<FeatureKey, Object[]> valuesCache = Caffeine.newBuilder()
            .initialCapacity(100)
            .maximumSize(1_000)
            .build();

    /**
     * In-memory cache that holds the recently loaded multivalued references, identified by the associated
     * {@link FeatureKey}.
     */
    private final Cache<FeatureKey, Id[]> referenceCache = Caffeine.newBuilder()
            .initialCapacity(100)
            .maximumSize(1_000)
            .build();

    /**
     * Constructs a new {@code DirectWriteCacheManyStore} between the given {@code resource} and the
     * {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteCacheManyStore(PersistentResource resource, P backend) {
        super(resource, backend);
    }

    @Override
    protected Object getAttribute(PersistentEObject object, EAttribute attribute, int index) {
        if (attribute.isMany()) {
            FeatureKey key = FeatureKey.from(object, attribute);

            Object[] values = valuesCache.get(key, featureKey ->
                    Iterables.toArray(backend.valuesAsList(featureKey), Object.class));

            Optional<Object> value = Optional.ofNullable(values[index]);

            if (value.isPresent()) {
                return deserialize(attribute, value.get());
            }
        }
        return super.getAttribute(object, attribute, index);
    }

    @Override
    protected PersistentEObject getReference(PersistentEObject object, EReference reference, int index) {
        if (reference.isMany()) {
            FeatureKey key = FeatureKey.from(object, reference);

            Id[] references = referenceCache.get(key, featureKey ->
                    Iterables.toArray(backend.referencesAsList(featureKey), Id.class));

            Optional<Id> id = Optional.ofNullable(references[index]);

            if (id.isPresent()) {
                return eObject(StringId.from(id.get()));
            }
        }
        return super.getReference(object, reference, index);
    }
}
