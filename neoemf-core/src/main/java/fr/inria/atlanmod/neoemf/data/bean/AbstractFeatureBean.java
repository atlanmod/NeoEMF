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
     * The position that indicates that a feature has no position.
     */
    private static final int NO_POSITION = -1;

    /**
     * The identifier of the object using the feature.
     */
    @Nonnull
    protected final Id owner;

    /**
     * The identifier of the feature.
     */
    @Nonnull
    protected final String id;

    /**
     * The position of the feature in its {@code owner}.
     */
    @Nonnegative
    protected final int position;

    /**
     * Constructs a new {@code AbstractFeatureBean} with the given {@code owner} and the given {@code id}.
     *
     * @param owner the identifier of the {@link PersistentEObject} using the feature
     * @param id    the identifier of the {@link EStructuralFeature} of the {@code owner}
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    protected AbstractFeatureBean(Id owner, String id) {
        this(owner, id, NO_POSITION);
    }

    /**
     * Constructs a new {@code ManyFeatureBean} with the given {@code id} and the given {@code name}, which are used as
     * a simple representation of a feature of an object. The "multi-valued" characteristic is identified with the
     * {@code position}.
     *
     * @param owner    the identifier of the {@link PersistentEObject} using the feature
     * @param id       the identifier of the {@link EStructuralFeature} of the {@code owner}
     * @param position the position of the {@link EStructuralFeature} in the {@code owner}
     *
     * @throws NullPointerException     if any argument is {@code null}
     * @throws IllegalArgumentException if the {@code position} is negative when {@code isMany() == true}
     */
    protected AbstractFeatureBean(Id owner, String id, int position) {
        this.owner = checkNotNull(owner);
        this.id = checkNotNull(id);

        if (isMany()) {
            checkArgument(position >= 0, "position must be >= 0");
        }
        this.position = position;
    }

    @Nonnull
    @Override
    public Id owner() {
        return owner;
    }

    @Nonnull
    @Override
    public String id() {
        return id;
    }

    @Nonnegative
    @Override
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
        return ManyFeatureBean.of(owner, id, position);
    }

    @Override
    public int compareTo(FeatureBean o) {
        if (this == o) {
            return 0;
        }

        int comparison = Boolean.compare(isMany(), o.isMany());

        if (comparison == 0) {
            comparison = owner.compareTo(o.owner());
        }

        if (comparison == 0) {
            comparison = id.compareTo(o.id());
        }

        if (comparison == 0 && isMany()) {
            comparison = Integer.compare(position, o.position());
        }

        return comparison;
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, id, position);
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
                && Objects.equals(owner, that.owner)
                && Objects.equals(id, that.id);
    }
}
