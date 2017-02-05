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

package fr.inria.atlanmod.neoemf.data.map.core.store;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.map.core.MapBackend;
import fr.inria.atlanmod.neoemf.data.store.DefaultDirectWriteStore;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import static com.google.common.base.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

/**
 * A {@link DirectWriteMapStore} that uses an internal cache to store persisted {@link Object}s that are part of
 * multi-valued {@link EReference}s to speed-up their access.
 * <p>
 * Using a cache avoids multiple {@link java.util.List} deserialization to retrieve the same element, which can be an
 * important bottleneck in terms of execution time and memory consumption if the underlying model contains very large
 * {@link java.util.Collection}s.
 * <p>
 * This store can be used as a base store that can be complemented by plugging decorator stores on top of it (see {@link
 * fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator} subclasses) to provide additional features such
 * as caching or logging.
 *
 * @see DirectWriteMapStore
 * @see MapBackend
 * @see fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator
 */
@Deprecated
// TODO Unusable: must be completely reviewed
public class DirectWriteCachedMapStore extends DefaultDirectWriteStore<PersistenceBackend> {

    /**
     * In-memory cache that holds ???, identified by the associated {@link FeatureKey}.
     */
    private final Cache<FeatureKey, Object> objectsCache = Caffeine.newBuilder()
            .initialCapacity(1_000)
            .maximumSize(10_000).build();

    /**
     * Constructs a new {@code DirectWriteCachedMapStore} between the given {@code resource} and the {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteCachedMapStore(PersistentResource resource, PersistenceBackend backend) {
        super(resource, backend);
    }

    @Override
    protected void addReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        if (reference.isMany()) {
            FeatureKey featureKey = FeatureKey.from(object, reference);
            if (index == NO_INDEX) {
                /*
                 * Handle NO_INDEX index, which represent direct-append feature.
	             * The call to size should not cause an overhead because it would have been done in regular
	             * addUnique() otherwise.
	             */
                index = size(object, reference);
            }
            updateContainment(object, reference, value);
            updateInstanceOf(value);
            Object[] array = (Object[]) backend.valueOf(featureKey).orElse(null);
            if (isNull(array)) {
                array = new Object[]{};
            }
            checkPositionIndex(index, array.length, "Invalid add index " + index);
            array = ArrayUtils.add(array, index, value.id());
            objectsCache.put(featureKey, array);
            backend.valueFor(featureKey, array);
            persistentObjectsCache.put(value.id(), value);
        }
        else {
            super.addReference(object, reference, index, value);
        }
    }

    protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        return objectsCache.get(featureKey, key -> backend.valueOf(key).orElse(null));
    }
}
