/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store.adapter;

import fr.inria.atlanmod.commons.function.BiConverter;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.util.EFeatures;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.commons.function.BiConverter} that transforms the value of {@link
 * org.eclipse.emf.ecore.util.FeatureMap.Entry} instances.
 */
@ParametersAreNonnullByDefault
public class FeatureMapConverter implements BiConverter<FeatureMap.Entry, EAttribute, String> {

    /**
     * The delimiter used to separate the feature from its value in a serialized {@link FeatureMap.Entry}.
     */
    @Nonnull
    public static final String ENTRY_DELIMITER = "#";

    /**
     * The pattern used to store feature map entries.
     */
    @Nonnull
    public static final String ENTRY_FORMAT = "%s" + ENTRY_DELIMITER + "%s";

    /**
     * The store associated to this converter.
     */
    @Nonnull
    private final StoreAdapter store;

    /**
     * Constructs a new {@code FeatureMapConverter} for the given {@code store}.
     *
     * @param store the store associated to this converter
     */
    public FeatureMapConverter(StoreAdapter store) {
        this.store = store;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Converts an instance of {@link FeatureMap.Entry} to a string literal representation.
     * <p>
     * The {@code entry} is serialized as {@code featureName#value}, where {@code value} is the string representation of
     * the {@link FeatureMap.Entry#getValue() value} for an attribute, or the string representation of the {@link Id}
     * for a reference.
     *
     * @param entry     the entry of the attribute
     * @param attribute the attribute to instantiate
     *
     * @return the string literal representation of the entry
     *
     * @see AttributeConverter#revert(Object, EAttribute)
     * @see EcoreUtil#convertToString(EDataType, Object)
     */
    @Nonnull
    @Override
    public String convert(FeatureMap.Entry entry, @SuppressWarnings("unused") EAttribute attribute) {
        EStructuralFeature entryFeature = entry.getEStructuralFeature();
        String value;

        if (EFeatures.isAttribute(entryFeature)) {
            EAttribute entryAttribute = EFeatures.asAttribute(entryFeature);
            value = EcoreUtil.convertToString(entryAttribute.getEAttributeType(), entry.getValue());
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(entry.getValue());
            store.updateInstanceOf(referencedObject);

            value = referencedObject.id().toHexString();
        }

        return String.format(ENTRY_FORMAT, entryFeature.getName(), value);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Creates an instance of {@link FeatureMap.Entry} from a string literal representation.
     * <p>
     * The {@code entry} must be serialized as {@code featureName#value}.
     *
     * @param entry     the string literal representation of the entry
     * @param attribute the attribute to instantiate
     *
     * @return the entry of the attribute
     *
     * @see AttributeConverter#convert(Object, EAttribute)
     * @see EcoreUtil#createFromString(EDataType, String)
     */
    @Nonnull
    @Override
    public FeatureMap.Entry revert(String entry, EAttribute attribute) {
        String[] splitValues = entry.split(ENTRY_DELIMITER, 2);

        EClass metaClass = attribute.getEContainingClass();
        EStructuralFeature entryFeature = metaClass.getEStructuralFeature(splitValues[0]);

        Object value;

        if (EFeatures.isAttribute(entryFeature)) {
            EAttribute entryAttribute = EFeatures.asAttribute(entryFeature);
            value = EcoreUtil.createFromString(entryAttribute.getEAttributeType(), splitValues[1]);
        }
        else {
            value = store.resolve(Id.getProvider().fromHexString(splitValues[1]));
        }

        return FeatureMapUtil.createEntry(entryFeature, value);
    }
}
