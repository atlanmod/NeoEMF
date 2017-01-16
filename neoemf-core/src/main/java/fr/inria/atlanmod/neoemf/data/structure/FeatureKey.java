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

/**
 * A simple representation of a {@link EStructuralFeature} of a {@link PersistentEObject}.
 */
public class FeatureKey implements Comparable<FeatureKey>, Serializable {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = 1L;

    /**
     * The identifier of the object.
     */
    @Nonnull
    private final Id id;

    /**
     * The name of the feature of the object.
     */
    @Nonnull
    private final String name;

    /**
     * Constructs a new {@code FeatureKey} with the given {@code id} and the given {@code name}, which are used as a
     * simple representation of a feature of an object.
     *
     * @param id   the identifier of the {@link PersistentEObject}
     * @param name the name of the {@link EStructuralFeature} of the {@link PersistentEObject}
     */
    protected FeatureKey(@Nonnull Id id, @Nonnull String name) {
        this.id = checkNotNull(id);
        this.name = checkNotNull(name);
    }

    /**
     * Creates a new {@code FeatureKey} from the given {@code internalObject} and the given {@code feature}.
     * <p>
     * This method behaves like: {@code from(PersistentEObject.from(internalObject), feature)}.
     *
     * @param internalObject the {@link InternalEObject} that will be adapted as {@link PersistentEObject}
     * @param feature        the {@link EStructuralFeature} of the {@link PersistentEObject}
     *
     * @return a new {@code FeatureKey}
     *
     * @see #from(PersistentEObject, EStructuralFeature)
     * @see PersistentEObject#from(Object)
     * @see EStructuralFeature#getName()
     */
    @Nonnull
    public static FeatureKey from(@Nonnull InternalEObject internalObject, @Nonnull EStructuralFeature feature) {
        return from(PersistentEObject.from(internalObject), feature);
    }

    /**
     * Creates a new {@code FeatureKey} from the given {@code object} and the given {@code feature}.
     * <p>
     * This method behaves like: {@code of(object.id(), feature.getName())}.
     *
     * @param object  the {@link PersistentEObject}
     * @param feature the {@link EStructuralFeature} of the {@link PersistentEObject}
     *
     * @return a new {@code FeatureKey}
     *
     * @see #of(Id, String)
     * @see PersistentEObject#id()
     * @see EStructuralFeature#getName()
     */
    @Nonnull
    public static FeatureKey from(@Nonnull PersistentEObject object, @Nonnull EStructuralFeature feature) {
        return of(object.id(), feature.getName());
    }

    /**
     * Creates a new {@code FeatureKey} with the given {@code id} and the given {@code name}, which are used as a
     * simple representation of a feature of an object.
     *
     * @param id   the identifier of the {@link PersistentEObject}
     * @param name the name of the {@link EStructuralFeature} of the {@link PersistentEObject}
     *
     * @return a new {@code FeatureKey}
     */
    @Nonnull
    public static FeatureKey of(@Nonnull Id id, @Nonnull String name) {
        return new FeatureKey(id, name);
    }

    /**
     * Returns the identifier of the {@link PersistentEObject}.
     *
     * @return the identifier of the object
     */
    @Nonnull
    public Id id() {
        return id;
    }

    /**
     * Returns the name of the {@link EStructuralFeature} of the {@link PersistentEObject}.
     *
     * @return the name of the feature
     */
    @Nonnull
    public String name() {
        return name;
    }

    /**
     * Creates a new {@link MultivaluedFeatureKey} with the {@link Id} and the name of this {@code FeatureKey}, and
     * adding the given {@code position}.
     *
     * @param position the position of the {@link EStructuralFeature}
     *
     * @return a new {@link MultivaluedFeatureKey}
     *
     * @see MultivaluedFeatureKey#of(Id, String)
     */
    @Nonnull
    public MultivaluedFeatureKey withPosition(@Nonnegative int position) {
        return MultivaluedFeatureKey.of(id, name, position);
    }

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
