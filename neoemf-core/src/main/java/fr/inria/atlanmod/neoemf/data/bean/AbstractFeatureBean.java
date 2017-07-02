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

package fr.inria.atlanmod.neoemf.data.bean;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.Objects;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;
import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * An abstract {@link FeatureBean}.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractFeatureBean implements FeatureBean {

    /**
     * The position that indicates that this feature has no position.
     */
    private static final int NO_POSITION = -1;

    /**
     * The identifier of the object.
     */
    @Nonnull
    protected final Id id;

    /**
     * The name of the feature of the object.
     */
    @Nonnull
    protected final String name;

    /**
     * The position of this key.
     */
    @Nonnegative
    protected final int position;

    /**
     * Constructs a new {@code AbstractFeatureBean} with the given {@code id} and the given {@code name}.
     *
     * @param id   the identifier of the {@link PersistentEObject}
     * @param name the name of the {@link EStructuralFeature} of the {@link PersistentEObject}
     */
    protected AbstractFeatureBean(Id id, String name) {
        this(id, name, NO_POSITION);
    }

    /**
     * Constructs a new {@code ManyFeatureBean} with the given {@code id} and the given {@code name}, which are
     * used as a simple representation of a feature of an object. The "multi-valued" characteristic is identified with
     * the {@code position}.
     *
     * @param id       the identifier of the {@link PersistentEObject}
     * @param name     the name of the {@link EStructuralFeature} of the {@link PersistentEObject}
     * @param position the position of the {@link EStructuralFeature}
     *
     * @throws NullPointerException     if any argument is {@code null}
     * @throws IllegalArgumentException if the {@code position} is negative, if {@code isMany() == true}
     */
    protected AbstractFeatureBean(Id id, String name, int position) {
        this.id = checkNotNull(id);
        this.name = checkNotNull(name);

        if (isMany()) {
            checkArgument(position >= 0, "Position must be >= 0");
        }
        this.position = position;
    }

    @Nonnull
    @Override
    public Id id() {
        return id;
    }

    @Nonnull
    @Override
    public String name() {
        return name;
    }

    @Nonnegative
    public int position() {
        return position;
    }

    /**
     * Creates a new {@link ManyFeatureBean} with the {@link Id} and the name of this {@code FeatureBean}, and adding
     * the given {@code position}.
     *
     * @param position the position of the {@link EStructuralFeature}
     *
     * @return a new {@link ManyFeatureBean}
     *
     * @throws IllegalArgumentException if the {@code position} is negative
     * @see ManyFeatureBean#of(Id, String, int)
     */
    @Nonnull
    public ManyFeatureBean withPosition(@Nonnegative int position) {
        return ManyFeatureBean.of(id, name, position);
    }

    @Override
    public int compareTo(FeatureBean o) {
        if (this == o) {
            return 0;
        }

        int comparison = Boolean.compare(isMany(), o.isMany());

        if (comparison == 0) {
            comparison = id.compareTo(o.id());
        }

        if (comparison == 0) {
            comparison = name.compareTo(o.name());
        }

        if (comparison == 0 && isMany()) {
            comparison = Integer.compare(position, o.position());
        }

        return comparison;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, position);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (!AbstractFeatureBean.class.isInstance(o)) {
            return false;
        }

        AbstractFeatureBean that = AbstractFeatureBean.class.cast(o);
        return isMany() == that.isMany()
                && position == that.position
                && Objects.equals(id, that.id)
                && Objects.equals(name, that.name);
    }
}
