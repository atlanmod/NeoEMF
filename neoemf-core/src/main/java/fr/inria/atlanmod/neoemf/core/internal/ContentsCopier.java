/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter;
import fr.inria.atlanmod.neoemf.util.EFeatures;

import org.atlanmod.commons.function.Copier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.atlanmod.commons.Preconditions.checkState;

/**
 * A {@link org.atlanmod.commons.function.Copier} that recursively copies the content related to a {@link
 * fr.inria.atlanmod.neoemf.core.PersistentEObject}, from a {@link StoreAdapter} to another, by using the EMF methods.
 */
@ParametersAreNonnullByDefault
public final class ContentsCopier implements Copier<StoreAdapter> {

    /**
     * The root object to copy.
     */
    @Nonnull
    private final PersistentEObject object;

    /**
     * Contructs a new {@code ContentsCopier} for the specified {@code object}.
     *
     * @param object the object to copy
     */
    public ContentsCopier(PersistentEObject object) {
        this.object = object;
    }

    @Override
    public void copy(StoreAdapter source, StoreAdapter target) {
        copyContainer(source, target);

        object.eClass().getEAllStructuralFeatures().forEach(f -> copyFeature(f, source, target));
    }

    /**
     * Copies the container of the {@link #object} from the {@code source} to the {@code target}.
     *
     * @param source the source where to get the value
     * @param target the target where to store the value
     */
    private void copyContainer(StoreAdapter source, StoreAdapter target) {
        PersistentEObject container = object.eInternalContainer();
        if (nonNull(container)) {
            target.updateContainment(object, source.getContainingFeature(object), container);
        }
        else {
            target.removeContainment(object);
        }
    }

    /**
     * Copies the value of the {@code feature} from the {@code source} to the {@code target}.
     *
     * @param feature the feature of the value to copy
     * @param source  the source where to get the value
     * @param target  the target where to store the value
     */
    private void copyFeature(EStructuralFeature feature, StoreAdapter source, StoreAdapter target) {
        if (!feature.isMany()) {
            copySingleFeature(feature, source, target);
        }
        else {
            copyManyFeature(feature, source, target);
        }
    }

    /**
     * Copies the value of the single-valued {@code feature} from the {@code source} to the {@code target}.
     *
     * @param feature the feature of the value to copy
     * @param source  the source where to get the value
     * @param target  the target where to store the value
     */
    private void copySingleFeature(EStructuralFeature feature, StoreAdapter source, StoreAdapter target) {
        checkState(!feature.isMany(), "feature must be single-valued");

        Object value = source.get(object, feature, InternalEObject.EStore.NO_INDEX);
        if (nonNull(value)) {
            if (requireAttachment(feature)) {
                value = attach(value);
            }

            target.set(object, feature, InternalEObject.EStore.NO_INDEX, value);
        }
    }

    /**
     * Copies the value of the multi-valued {@code feature} from the {@code source} to the {@code target}.
     *
     * @param feature the feature of the value to copy
     * @param source  the source where to get the value
     * @param target  the target where to store the value
     */
    private void copyManyFeature(EStructuralFeature feature, StoreAdapter source, StoreAdapter target) {
        checkState(feature.isMany(), "feature must be multi-valued");

        List<Object> values = source.getAll(object, feature);
        if (!values.isEmpty()) {
            if (requireAttachment(feature)) {
                values = values.stream().map(this::attach).collect(Collectors.toList());
            }

            target.setAll(object, feature, values);
        }
    }

    /**
     * Checks that a {@code feature} is candidate for attachment.
     *
     * @param feature the feature to test
     *
     * @return {@code true} is the {@code feature} is candidate for attachment
     *
     * @see #attach(Object)
     */
    private boolean requireAttachment(EStructuralFeature feature) {
        return EFeatures.isReference(feature) && EFeatures.asReference(feature).isContainment();
    }

    /**
     * Attachs the {@code value} to {@link PersistentEObject#resource()} if it is assignable to a {@link
     * PersistentEObject}.
     *
     * @param value the value to attach
     *
     * @return the {@code value}
     *
     * @see PersistentEObject#resource(org.eclipse.emf.ecore.resource.Resource.Internal)
     */
    @Nullable
    private Object attach(@Nullable Object value) {
        if (isNull(value)) {
            return null;
        }

        PersistentEObject o = PersistentEObject.from(value);
        o.resource(object.resource());
        return o;
    }
}
