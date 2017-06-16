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
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The factory that creates {@link Serializer} instances that uses the standard Java serialization.
 */
@ParametersAreNonnullByDefault
public final class JavaSerializerFactory implements SerializerFactory {

    /**
     * The instance of {@link ObjectSerializer}.
     */
    private final Serializer<?> objectSerializer = new ObjectSerializer<>();

    /**
     * The instance of {@link IdSerializer}.
     */
    private final Serializer<Id> idSerializer = new IdSerializer();

    /**
     * The instance of {@link ClassDescriptorSerializer}.
     */
    private final Serializer<ClassDescriptor> classDescriptorSerializer = new ClassDescriptorSerializer();

    /**
     * The instance of {@link SingleFeatureKeySerializer}.
     */
    private final Serializer<SingleFeatureKey> singleFeatureKeySerializer = new SingleFeatureKeySerializer();

    /**
     * The instance of {@link ManyFeatureKeySerializer}.
     */
    private final Serializer<ManyFeatureKey> manyFeatureKeySerializer = new ManyFeatureKeySerializer();

    /**
     * Constructs a new {@code JavaSerializerFactory}.
     */
    private JavaSerializerFactory() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static SerializerFactory getInstance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public <T> Serializer<T> forAny() {
        return (Serializer<T>) objectSerializer;
    }

    @Nonnull
    @Override
    public Serializer<Id> forId() {
        return idSerializer;
    }

    @Nonnull
    @Override
    public Serializer<ClassDescriptor> forClass() {
        return classDescriptorSerializer;
    }

    @Nonnull
    @Override
    public Serializer<SingleFeatureKey> forSingleFeatureKey() {
        return singleFeatureKeySerializer;
    }

    @Nonnull
    @Override
    public Serializer<ManyFeatureKey> forManyFeatureKey() {
        return manyFeatureKeySerializer;
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final SerializerFactory INSTANCE = new JavaSerializerFactory();
    }
}
