/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util;

import org.atlanmod.commons.Throwables;
import org.atlanmod.commons.annotation.Static;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Static utility methods related to {@link EStructuralFeature}s.
 */
@Static
@ParametersAreNonnullByDefault
// TODO Use ExtendedMetaData to handle features whose kind differs from their instance
public final class EFeatures {

    private EFeatures() {
        throw Throwables.notInstantiableClass(getClass());
    }

    /**
     * Checks whether the {@code feature} represents an attribute.
     *
     * @param feature the feature to test
     *
     * @return {@code true} if the {@code feature} represents an attribute
     */
    public static boolean isAttribute(EStructuralFeature feature) {
        return EAttribute.class.isInstance(feature);
    }

    /**
     * Checks whether the {@code feature} represents a reference.
     *
     * @param feature the feature to test
     *
     * @return {@code true} if the {@code feature} represents an reference
     */
    public static boolean isReference(EStructuralFeature feature) {
        return EReference.class.isInstance(feature);
    }

    /**
     * Casts the {@code feature} as an attribute.
     *
     * @param feature the feature to cast
     *
     * @return the attribute
     *
     * @throws NullPointerException if the {@code feature} is {@code null}
     * @throws ClassCastException   if the {@code feature} is not an attribute
     */
    @Nonnull
    public static EAttribute asAttribute(EStructuralFeature feature) {
        return EAttribute.class.cast(feature);
    }

    /**
     * Casts the {@code feature} as a reference.
     *
     * @param feature the feature to cast
     *
     * @return the reference
     *
     * @throws NullPointerException if the {@code feature} is {@code null}
     * @throws ClassCastException   if the {@code feature} is not a reference
     */
    @Nonnull
    public static EReference asReference(EStructuralFeature feature) {
        return EReference.class.cast(feature);
    }
}
