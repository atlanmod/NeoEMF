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

package fr.inria.atlanmod.neoemf.io.serializer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The factory that creates {@link Serializer} instances.
 */
@ParametersAreNonnullByDefault
public final class Serializers<T> {

    /**
     * The unique instance of {@link ObjectSerializer}.
     */
    private static final Serializer<?> OBJECT = new ObjectSerializer<>();

    /**
     * The unique instance of {@link IdSerializer}.
     */
    private static final Serializer<Id> ID = new IdSerializer();

    /**
     * The unique instance of {@link ContainerDescriptorSerializer}.
     */
    private static final Serializer<ContainerDescriptor> CONTAINER = new ContainerDescriptorSerializer();

    /**
     * The unique instance of {@link ClassDescriptorSerializer}.
     */
    private static final Serializer<ClassDescriptor> METACLASS = new ClassDescriptorSerializer();

    /**
     * The unique instance of {@link FeatureKeySerializer}.
     */
    private static final Serializer<FeatureKey> FEATURE_KEY = new FeatureKeySerializer();

    /**
     * The unique instance of {@link ManyFeatureKeySerializer}.
     */
    private static final Serializer<ManyFeatureKey> MANY_FEATURE_KEY = new ManyFeatureKeySerializer();

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private Serializers() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Gets the {@link Serializer} for any {@link Object}, using Java serialization.
     *
     * @param <T> the type of (de)serialized objects
     *
     * @return a serializer
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <T> Serializer<T> forObjects() {
        return (Serializer<T>) OBJECT;
    }

    /**
     * Gets the {@link Serializer} for {@link Id}s.
     *
     * @return a serializer
     */
    @Nonnull
    public static Serializer<Id> forIds() {
        return ID;
    }

    /**
     * Gets the {@link Serializer} for {@link ContainerDescriptor}s.
     *
     * @return a serializer
     */
    @Nonnull
    public static Serializer<ContainerDescriptor> forContainers() {
        return CONTAINER;
    }

    /**
     * Gets the {@link Serializer} for {@link ClassDescriptor}s.
     *
     * @return a serializer
     */
    @Nonnull
    public static Serializer<ClassDescriptor> forMetaclasses() {
        return METACLASS;
    }

    /**
     * Gets the {@link Serializer} for {@link FeatureKey}s.
     *
     * @return a serializer
     */
    @Nonnull
    public static Serializer<FeatureKey> forFeatureKeys() {
        return FEATURE_KEY;
    }

    /**
     * Gets the {@link ManyFeatureKey} for {@link FeatureKey}s.
     *
     * @return a serializer
     */
    @Nonnull
    public static Serializer<ManyFeatureKey> forManyFeatureKeys() {
        return MANY_FEATURE_KEY;
    }
}
