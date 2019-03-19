/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.bean.serializer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import org.atlanmod.commons.annotation.Singleton;
import org.atlanmod.commons.annotation.Static;
import org.atlanmod.commons.io.serializer.BinarySerializer;
import org.atlanmod.commons.io.serializer.BinarySerializerFactory;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The factory that creates {@link org.atlanmod.commons.io.serializer.BinarySerializer} instances.
 */
@Singleton
@ParametersAreNonnullByDefault
public class BeanSerializerFactory extends BinarySerializerFactory {

    /**
     * The instance of {@link IdSerializer}.
     */
    private final BinarySerializer<Id> idSerializer = new IdSerializer();

    /**
     * The instance of {@link ClassSerializer}.
     */
    private final BinarySerializer<ClassBean> classSerializer = new ClassSerializer();

    /**
     * The instance of {@link SingleFeatureSerializer}.
     */
    private final BinarySerializer<SingleFeatureBean> singleFeatureSerializer = new SingleFeatureSerializer();

    /**
     * The instance of {@link ManyFeatureSerializer}.
     */
    private final BinarySerializer<ManyFeatureBean> manyFeatureSerializer = new ManyFeatureSerializer();

    /**
     * Constructs a new {@code BeanSerializerFactory}.
     */
    private BeanSerializerFactory() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static BeanSerializerFactory getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Gets the {@link org.atlanmod.commons.io.serializer.BinarySerializer} for {@link
     * fr.inria.atlanmod.neoemf.core.Id}s.
     *
     * @return a serializer
     */
    @Nonnull
    public BinarySerializer<Id> forId() {
        return idSerializer;
    }

    /**
     * Gets the {@link org.atlanmod.commons.io.serializer.BinarySerializer} for {@link
     * fr.inria.atlanmod.neoemf.data.bean.ClassBean}s.
     *
     * @return a serializer
     */
    @Nonnull
    public BinarySerializer<ClassBean> forClass() {
        return classSerializer;
    }

    /**
     * Gets the {@link org.atlanmod.commons.io.serializer.BinarySerializer} for {@link
     * fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean}s.
     *
     * @return a serializer
     */
    @Nonnull
    public BinarySerializer<SingleFeatureBean> forSingleFeature() {
        return singleFeatureSerializer;
    }

    /**
     * Gets the {@link org.atlanmod.commons.io.serializer.BinarySerializer} for {@link
     * fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean}s.
     *
     * @return a serializer
     */
    @Nonnull
    public BinarySerializer<ManyFeatureBean> forManyFeature() {
        return manyFeatureSerializer;
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        static final BeanSerializerFactory INSTANCE = new BeanSerializerFactory();
    }
}
