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

package fr.inria.atlanmod.neoemf.map.datastore.store;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.cache.FeatureCache;
import fr.inria.atlanmod.neoemf.cache.FeatureKey;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackend;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import static com.google.common.base.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

public class DirectWriteMapCacheManyStore extends DirectWriteMapStore {

    private final FeatureCache<Object> featureCache;

    public DirectWriteMapCacheManyStore(Resource.Internal resource, MapPersistenceBackend persistenceBackend) {
        super(resource, persistenceBackend);
        featureCache = new FeatureCache<>();
    }

    @Override
    protected void addReference(PersistentEObject object, EReference eReference, int index, PersistentEObject value) {
        if (eReference.isMany()) {
            FeatureKey featureKey = FeatureKey.from(object, eReference);
            if (index == NO_INDEX) {
                /*
                 * Handle NO_INDEX index, which represent direct-append feature.
	             * The call to size should not cause an overhead because it would have been done in regular
	             * addUnique() otherwise.
	             */
                index = size(object, eReference);
            }
            updateContainment(object, eReference, value);
            updateInstanceOf(value);
            Object[] array = (Object[]) getFromMap(featureKey);
            if (isNull(array)) {
                array = new Object[]{};
            }
            checkPositionIndex(index, array.length, "Invalid add index " + index);
            array = ArrayUtils.add(array, index, value.id());
            featureCache.put(featureKey, array);
            persistenceBackend.storeValue(featureKey, array);
            loadedEObjects.put(value.id(), value);
        }
        else {
            super.addReference(object, eReference, index, value);
        }
    }

    @Override
    protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        return featureCache.get(featureKey, super::getFromMap);
    }
}
