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

package fr.inria.atlanmod.neoemf.data.hbase.structure;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import org.eclipse.emf.ecore.EStructuralFeature;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A {@link FeatureKey} specific to the HBase implementation which needs a complete representation of a
 * {@link EStructuralFeature} instead of a simple name.
 */
public class HBaseFeatureKey extends FeatureKey {

    private static final long serialVersionUID = 1L;

    @Nonnull
    private final EStructuralFeature feature;

    /**
     * Instantiates a new {@code HBaseFeatureKey} with the given {@code id} and the given {@code feature}.
     *
     * @param id      the identifier of the {@link PersistentEObject}
     * @param feature the {@link EStructuralFeature} of the {@link PersistentEObject}
     */
    protected HBaseFeatureKey(@Nonnull Id id, @Nonnull EStructuralFeature feature) {
        super(id, feature.getName());
        this.feature = checkNotNull(feature);
    }

    /**
     * Creates a new {@code HBaseFeatureKey} from the given {@code object} and the given {@code feature}.
     * <p>
     * This method behaves like: {@code HBaseFeatureKey.of(object.id(), feature)}
     *
     * @param object  the {@link PersistentEObject}
     * @param feature the {@link EStructuralFeature} of the {@link PersistentEObject}
     *
     * @return a new {@code HBaseFeatureKey}
     *
     * @see #of(Id, String)
     * @see PersistentEObject#id()
     */
    @Nonnull
    public static HBaseFeatureKey from(@Nonnull PersistentEObject object, @Nonnull EStructuralFeature feature) {
        return of(object.id(), feature);
    }

    /**
     * Creates a new {@code HBaseFeatureKey} with the given {@code id} and the given {@code feature}.
     *
     * @param id      the identifier of the {@link PersistentEObject}
     * @param feature the {@link EStructuralFeature} of the {@link PersistentEObject}
     *
     * @return a new {@code HBaseFeatureKey}
     */
    @Nonnull
    public static HBaseFeatureKey of(@Nonnull Id id, @Nonnull EStructuralFeature feature) {
        return new HBaseFeatureKey(id, feature);
    }

    /**
     * Returns the {@link EStructuralFeature} of the {@link PersistentEObject}
     *
     * @return the feature
     */
    @Nonnull
    public EStructuralFeature feature() {
        return feature;
    }
}
