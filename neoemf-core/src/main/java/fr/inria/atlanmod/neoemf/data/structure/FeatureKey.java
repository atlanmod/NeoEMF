/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.structure;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import java.io.Serializable;
import java.util.Objects;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

public class FeatureKey implements Comparable<FeatureKey>, Serializable {

    private static final long serialVersionUID = 1L;

    @Nonnull
    private final Id id;

    @Nonnull
    private final String name;

    protected FeatureKey(@Nonnull Id id, @Nonnull String name) {
        this.id = checkNotNull(id);
        this.name = checkNotNull(name);
    }

    @Nonnull
    public static FeatureKey from(@Nonnull InternalEObject internalObject, @Nonnull EStructuralFeature feature) {
        return from(PersistentEObject.from(internalObject), feature);
    }

    @Nonnull
    public static FeatureKey from(@Nonnull PersistentEObject object, @Nonnull EStructuralFeature feature) {
        return of(object.id(), feature.getName());
    }

    @Nonnull
    public static FeatureKey of(@Nonnull Id id, @Nonnull String name) {
        return new FeatureKey(id, name);
    }

    @Nonnull
    public Id id() {
        return id;
    }

    @Nonnull
    public String name() {
        return name;
    }

    @Nonnull
    public MultivaluedFeatureKey withPosition(@Nonnegative int position) {
        return MultivaluedFeatureKey.of(id, name, position);
    }

    /**
     * Compares two feature keys.
     *
     * @return 0 if equal, -1 if before, 1 if after.
     */
    @Override
    public int compareTo(@Nonnull FeatureKey o) {
        final int EQUAL = 0;

        if (this == o) {
            return EQUAL;
        }
        int comparison = id.compareTo(o.id);

        if (comparison == EQUAL) {
            return name.compareTo(o.name);
        }
        else {
            return comparison;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    /**
     * Defines equality between feature keys.
     */
    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FeatureKey)) {
            return false;
        }

        FeatureKey that = (FeatureKey) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return "FK:{" + id + ", " + name + "}";
    }
}
