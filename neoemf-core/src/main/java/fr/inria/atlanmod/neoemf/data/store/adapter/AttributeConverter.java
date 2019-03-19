/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store.adapter;

import org.atlanmod.commons.function.BiConverter;
import org.atlanmod.commons.primitive.Primitives;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;

/**
 * A {@link org.atlanmod.commons.function.BiConverter} that transforms the value of {@link
 * org.eclipse.emf.ecore.EAttribute} instances.
 */
@ParametersAreNonnullByDefault
public class AttributeConverter implements BiConverter<Object, EAttribute, Object> {

    /**
     * The converter for {@link FeatureMap.Entry} instances.
     */
    @Nonnull
    private final FeatureMapConverter featureMapConverter;

    /**
     * Constructs a new {@code AttributeConverter} for the given {@code store}.
     *
     * @param featureMapConverter the converter for {@link org.eclipse.emf.ecore.util.FeatureMap.Entry} instances
     */
    public AttributeConverter(FeatureMapConverter featureMapConverter) {
        this.featureMapConverter = featureMapConverter;
    }

    /**
     * {@inheritDoc}
     * <p>
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
    public Object convert(@Nullable Object value, EAttribute attribute) {
        if (isNull(value)) {
            return null;
        }

        final EDataType dataType = attribute.getEAttributeType();

        if (!(dataType instanceof EEnum) && Primitives.isPrimitiveOrString(dataType.getInstanceClass())) {
            return value;
        }

        if (FeatureMapUtil.isFeatureMapEntry(dataType)) {
            return featureMapConverter.convert((FeatureMap.Entry) value, attribute);
        }

        return EcoreUtil.convertToString(dataType, value);
    }


    /**
     * {@inheritDoc}
     * <p>
     * Creates an instance of the {@code attribute} from a string literal representation.
     *
     * @param value     the string literal representation of the value
     * @param attribute the attribute to instantiate
     *
     * @return the value of the attribute, or {@code null} if the {@code value} is {@code null}
     *
     * @see #convert(Object, EAttribute)
     * @see EcoreUtil#createFromString(EDataType, String)
     */
    @Override
    public Object revert(@Nullable Object value, EAttribute attribute) {
        if (isNull(value)) {
            return null;
        }

        final EDataType dataType = attribute.getEAttributeType();

        if (!(dataType instanceof EEnum) && Primitives.isPrimitiveOrString(dataType.getInstanceClass())) {
            return value;
        }

        // From this point, the value is considered as a String.
        final String strValue = (String) value;

        if (FeatureMapUtil.isFeatureMapEntry(dataType)) {
            return featureMapConverter.revert(strValue, attribute);
        }

        return EcoreUtil.createFromString(dataType, strValue);
    }
}
