/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store.adapter;

import org.eclipse.emf.ecore.EStructuralFeature;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A wrapper of {@link EStructuralFeature} used for logging.
 */
@ParametersAreNonnullByDefault
class FeatureWrapper {

    /**
     * The wrapped feature.
     */
    @Nonnull
    private final EStructuralFeature feature;

    /**
     * The type of the {@code feature}.
     */
    @Nonnull
    private final String type;

    /**
     * Constructs a new {@code FeatureWrapper}.
     *
     * @param feature the wrapped feature
     * @param type    the type of the {@code feature}
     */
    public FeatureWrapper(EStructuralFeature feature, String type) {
        this.feature = feature;
        this.type = type;
    }

    /**
     * Returns the wrapped feature.
     *
     * @return the feature
     */
    @Nonnull
    public EStructuralFeature getFeature() {
        return feature;
    }

    @Override
    public String toString() {
        return type;
    }
}
