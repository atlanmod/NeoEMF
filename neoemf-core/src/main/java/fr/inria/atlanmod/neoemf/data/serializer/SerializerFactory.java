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

package fr.inria.atlanmod.neoemf.data.serializer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import javax.annotation.Nonnull;

/**
 * A factory that creates {@link Serializer} instances.
 */
public interface SerializerFactory {

    /**
     * Gets the {@link Serializer} for any {@link Object}.
     *
     * @param <T> the type of (de)serialized objects
     *
     * @return a serializer
     */
    @Nonnull
    <T> Serializer<T> forAny();

    /**
     * Gets the {@link Serializer} for {@link Id}s.
     *
     * @return a serializer
     */
    @Nonnull
    Serializer<Id> forId();

    /**
     * Gets the {@link Serializer} for {@link ClassBean}s.
     *
     * @return a serializer
     */
    @Nonnull
    Serializer<ClassBean> forClass();

    /**
     * Gets the {@link Serializer} for {@link SingleFeatureBean}s.
     *
     * @return a serializer
     */
    @Nonnull
    Serializer<SingleFeatureBean> forSingleFeature();

    /**
     * Gets the {@link Serializer} for {@link ManyFeatureBean}s.
     *
     * @return a serializer
     */
    @Nonnull
    Serializer<ManyFeatureBean> forManyFeature();
}
