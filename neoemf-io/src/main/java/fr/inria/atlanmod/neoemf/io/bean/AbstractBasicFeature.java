/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.bean;

import fr.inria.atlanmod.neoemf.core.Id;

import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A simple representation of a {@link EStructuralFeature}.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractBasicFeature<T extends AbstractBasicFeature<T, F, V>, F extends EStructuralFeature, V> extends AbstractNamedElement<T> {

    /**
     * The identifier of element that owns this feature.
     */
    private Id owner;

    /**
     * The identifier of the feature for the owner element.
     */
    private int id = -1;

    /**
     * Whether this feature is multi-valued.
     */
    private boolean isMany = false;

    /**
     * The value of this feature.
     */
    private V value;

    /**
     * The {@link EStructuralFeature} associated to this feature.
     */
    private F eFeature;

    /**
     * Returns the identifier of the element that owns this feature.
     *
     * @return the owner's identifier
     */
    public Id owner() {
        return owner;
    }

    /**
     * Defines the identifier of the element that owns this feature.
     *
     * @param owner the owner's identifier
     *
     * @return this instance (for chaining)
     */
    public T owner(Id owner) {
        this.owner = owner;

        return me();
    }

    /**
     * Returns the identifier of the feature for the owner element.
     *
     * @return the feature's identifier
     */
    public int id() {
        return id;
    }

    /**
     * Defines the identifier of the feature for the owner element.
     *
     * @param id the feature's identifier
     *
     * @return this instance (for chaining)
     */
    public T id(int id) {
        this.id = id;

        return me();
    }

    /**
     * Returns {@code true} if this feature is multi-valued.
     *
     * @return {@code true} if this feature is multi-valued
     */
    public boolean isMany() {
        return isMany;
    }

    /**
     * Defines whether this feature is multi-valued.
     *
     * @param isMany {@code true} if this feature is multi-valued
     *
     * @return this instance (for chaining)
     */
    public T isMany(boolean isMany) {
        this.isMany = isMany;

        return me();
    }

    /**
     * Returns the value of this feature.
     *
     * @return the value
     */
    public V value() {
        return value;
    }

    /**
     * Defines the value of this feature.
     *
     * @param value the value
     *
     * @return this instance (for chaining)
     */
    public T value(V value) {
        this.value = value;

        return me();
    }

    /**
     * Returns the {@link EStructuralFeature} associated to this meta-class.
     *
     * @return the {@link EStructuralFeature}
     */
    @Nonnull
    public F eFeature() {
        return checkNotNull(eFeature, "eFeature");
    }

    /**
     * Defines the {@link EStructuralFeature} associated to this meta-class.
     *
     * @param eFeature the {@link EStructuralFeature}
     *
     * @return this instance (for chaining)
     */
    public T eFeature(F eFeature) {
        this.eFeature = checkNotNull(eFeature, "eFeature");

        return name(eFeature.getName())
                .isMany(eFeature.isMany());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), owner);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        AbstractBasicFeature<?, ?, ?> that = AbstractBasicFeature.class.cast(o);
        return Objects.equals(owner, that.owner);
    }
}
