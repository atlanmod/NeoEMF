/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.commons.function.BiConverter;
import fr.inria.atlanmod.commons.primitive.Primitives;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;

/**
 * A {@link BiConverter} that transforms the value of {@link EAttribute} instances.
 */
@ParametersAreNonnullByDefault
public class ValueConverter implements BiConverter<String, EAttribute, Object> {

    /**
     * The singleton instance of this class.
     */
    @Nonnull
    public static final ValueConverter INSTANCE = new ValueConverter();

    /**
     * Constructs a new {@code ValueConverter}.
     */
    private ValueConverter() {
    }

    /**
     * Converts an instance of the {@code attribute} to a string literal representation.
     *
     * @param value     the value of the attribute
     * @param attribute the attribute to instantiate
     *
     * @return the string literal representation of the value, or {@code null} if the {@code value} is {@code null}
     *
     * @see #revert(Object, EAttribute)
     * @see EcoreUtil#convertToString(EDataType, Object)
     */
    @Override
    public Object convert(@Nullable String value, EAttribute attribute) {
        if (isNull(value)) {
            return null;
        }

        final EDataType dataType = attribute.getEAttributeType();

        if (FeatureMapUtil.isFeatureMapEntry(dataType)) {
            throw new UnsupportedOperationException("FeatureMaps are not supported yet");
        }

        if (Primitives.isPrimitiveOrString(dataType.getInstanceClass())) {
            return EcoreUtil.createFromString(dataType, value);
        }

        return value;
    }


    /**
     * Creates an instance of the {@code attribute} from a string literal representation.
     *
     * @param value     the string literal representation of the value
     * @param attribute the attribute to instantiate
     *
     * @return the value of the attribute, or {@code null} if the {@code value} is {@code null}
     *
     * @see #convert(String, EAttribute)
     * @see EcoreUtil#createFromString(EDataType, String)
     */
    @Override
    public String revert(@Nullable Object value, EAttribute attribute) {
        if (isNull(value)) {
            return null;
        }

        final EDataType dataType = attribute.getEAttributeType();

        if (FeatureMapUtil.isFeatureMapEntry(dataType)) {
            throw new UnsupportedOperationException("FeatureMaps are not supported yet");
        }

        if (Primitives.isPrimitiveOrString(dataType.getInstanceClass())) {
            return EcoreUtil.convertToString(dataType, value);
        }

        return String.class.cast(value);
    }
}
