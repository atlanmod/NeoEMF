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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.ManyReferenceAsManyValue;
import fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithArrays;
import fr.inria.atlanmod.neoemf.data.mapping.ReferenceAsValue;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link TransientBackend} that provides the default behavior of containers and meta-classes management.
 *
 * @param <K> the type of keys to use to identify features
 */
@ParametersAreNonnullByDefault
public abstract class AbstractTransientBackend<K> extends AbstractBackend implements TransientBackend, ReferenceAsValue, ManyValueWithArrays, ManyReferenceAsManyValue {

    /**
     * Casts the {@code value} as expected.
     *
     * @param value the value to be cast
     * @param <V>   the expected type of the value
     *
     * @return the {@code value} after casting, or {@code null} if the {@code value} is {@code null}
     *
     * @throws ClassCastException if the {@code value} is not {@code null} and is not assignable to the type {@code V}
     */
    @Nullable
    @SuppressWarnings("unchecked")
    protected static <V> V cast(@Nullable Object value) {
        return (V) value;
    }

    /**
     * Returns the map that holds all containers.
     *
     * @return a mutable map
     */
    @Nonnull
    protected abstract Map<Id, SingleFeatureBean> allContainers();

    /**
     * Returns the map that holds all instances.
     *
     * @return a mutable map
     */
    @Nonnull
    protected abstract Map<Id, ClassBean> allInstances();

    /**
     * Returns the map that holds all features.
     *
     * @return a mutable map
     */
    @Nonnull
    protected abstract Map<K, Object> allFeatures();

    /**
     * Transforms the specified {@code key} to be compatible with the type {@link K} used by this backend.
     *
     * @param key the key to transform
     *
     * @return the transformed key
     *
     * @see #allFeatures()
     * @see #valueOf(SingleFeatureBean)
     * @see #valueFor(SingleFeatureBean, Object)
     * @see #unsetValue(SingleFeatureBean)
     */
    @Nonnull
    protected abstract K transform(SingleFeatureBean key);

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id);

        return Optional.ofNullable(allContainers().get(id));
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id);
        checkNotNull(container);

        allContainers().put(id, container);
    }

    @Override
    public void unsetContainer(Id id) {
        checkNotNull(id);

        allContainers().remove(id);
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id);

        return Optional.ofNullable(allInstances().get(id));
    }

    @Override
    public void metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id);
        checkNotNull(metaClass);

        allInstances().put(id, metaClass);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        K k = transform(key);

        return Optional.ofNullable(cast(allFeatures().get(k)));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        K k = transform(key);
        checkNotNull(value);

        return Optional.ofNullable(cast(allFeatures().put(k, value)));
    }

    @Override
    public <V> void unsetValue(SingleFeatureBean key) {
        K k = transform(key);

        allFeatures().remove(k);
    }
}
