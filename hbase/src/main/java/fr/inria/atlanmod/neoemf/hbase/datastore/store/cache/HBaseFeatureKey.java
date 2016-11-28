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

package fr.inria.atlanmod.neoemf.hbase.datastore.store.cache;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.cache.FeatureKey;

import org.eclipse.emf.ecore.EStructuralFeature;

import static com.google.common.base.Preconditions.checkNotNull;

public class HBaseFeatureKey extends FeatureKey {

    private static final long serialVersionUID = 1L;

    public final EStructuralFeature feature;

    public static HBaseFeatureKey from(PersistentEObject object, EStructuralFeature feature) {
        return of(object.id(), feature);
    }

    public static HBaseFeatureKey of(Id id, EStructuralFeature feature) {
        return new HBaseFeatureKey(id, feature);
    }

    protected HBaseFeatureKey(Id id, EStructuralFeature feature) {
        super(id, feature.getName());
        this.feature = checkNotNull(feature);
    }

    public EStructuralFeature feature() {
        return feature;
    }
}
