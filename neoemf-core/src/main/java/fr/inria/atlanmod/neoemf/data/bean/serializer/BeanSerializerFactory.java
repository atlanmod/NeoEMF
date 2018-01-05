/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.bean.serializer;

import fr.inria.atlanmod.commons.annotation.Singleton;
import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.io.serializer.Serializer;
import fr.inria.atlanmod.commons.io.serializer.SerializerFactory;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The factory that creates {@link Serializer} instances.
 */
@Singleton
@ParametersAreNonnullByDefault
public class BeanSerializerFactory extends SerializerFactory {

    /**
     * The instance of {@link IdSerializer}.
     */
    private final Serializer<Id> idSerializer = new IdSerializer();

    /**
     * The instance of {@link ClassSerializer}.
     */
    private final Serializer<ClassBean> classSerializer = new ClassSerializer();

    /**
     * The instance of {@link SingleFeatureSerializer}.
     */
    private final Serializer<SingleFeatureBean> singleFeatureSerializer = new SingleFeatureSerializer();

    /**
     * The instance of {@link ManyFeatureSerializer}.
     */
    private final Serializer<ManyFeatureBean> manyFeatureSerializer = new ManyFeatureSerializer();

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
     * Gets the {@link Serializer} for {@link Id}s.
     *
     * @return a serializer
     */
    @Nonnull
    public Serializer<Id> forId() {
        return idSerializer;
    }

    /**
     * Gets the {@link Serializer} for {@link ClassBean}s.
     *
     * @return a serializer
     */
    @Nonnull
    public Serializer<ClassBean> forClass() {
        return classSerializer;
    }

    /**
     * Gets the {@link Serializer} for {@link SingleFeatureBean}s.
     *
     * @return a serializer
     */
    @Nonnull
    public Serializer<SingleFeatureBean> forSingleFeature() {
        return singleFeatureSerializer;
    }

    /**
     * Gets the {@link Serializer} for {@link ManyFeatureBean}s.
     *
     * @return a serializer
     */
    @Nonnull
    public Serializer<ManyFeatureBean> forManyFeature() {
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
