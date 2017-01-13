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

package fr.inria.atlanmod.neoemf.data.mapdb.store;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import static com.google.common.base.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

public class DirectWriteMapDbCacheManyStore extends DirectWriteMapDbStore {

    private final Cache<FeatureKey, Object> objectsCache;

    /**
     * Constructs a new {@code DirectWriteMapDbCacheManyStore} between the given {@code resource} and the
     * {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend the persistence backend used to store the model
     */
    public DirectWriteMapDbCacheManyStore(Resource.Internal resource, MapDbPersistenceBackend backend) {
        super(resource, backend);
        this.objectsCache = Caffeine.newBuilder().maximumSize(10000).build();
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
            Object[] array = (Object[]) getFromMap(featureKey);
            if (isNull(array)) {
                array = new Object[]{};
            }
            checkPositionIndex(index, array.length, "Invalid add index " + index);
            array = ArrayUtils.add(array, index, value.id());
            objectsCache.put(featureKey, array);
            backend.storeValue(featureKey, array);
            persistentObjectsCache.put(value.id(), value);
        }
        else {
            super.addReference(object, reference, index, value);
        }
    }

    @Override
    protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        return objectsCache.get(featureKey, super::getFromMap);
    }
}
