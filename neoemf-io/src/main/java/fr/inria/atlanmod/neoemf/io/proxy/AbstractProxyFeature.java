/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.proxy;

import fr.inria.atlanmod.neoemf.core.Id;

import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A simple representation of a {@link org.eclipse.emf.ecore.EStructuralFeature}.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractProxyFeature<T extends AbstractProxyFeature<T, R, V>, R extends EStructuralFeature, V> extends AbstractNamedElement<T> implements Proxy<T, R> {

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
    private boolean many;

    /**
     * The value of this feature.
     */
    @Nonnull
    private ProxyValue<V> value = ProxyValue.empty();

    /**
     * The {@link EStructuralFeature} represented by this object.
     */
    private R eFeature;

    @Override
    public R getOrigin() {
        return checkNotNull(eFeature, "eFeature");
    }

    @Nonnull
    @Override
    public T setOrigin(R eFeature) {
        this.eFeature = checkNotNull(eFeature, "eFeature");

        return setName(eFeature.getName()).setMany(eFeature.isMany());
    }

    /**
     * Returns the identifier of the element that owns this feature.
     *
     * @return the owner's identifier
     */
    public Id getOwner() {
        return owner;
    }

    /**
     * Defines the identifier of the element that owns this feature.
     *
     * @param owner the owner's identifier
     *
     * @return this instance (for chaining)
     */
    @Nonnull
    public T setOwner(Id owner) {
        this.owner = owner;

        return me();
    }

    /**
     * Returns the identifier of the feature for the owner element.
     *
     * @return the feature's identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Defines the identifier of the feature for the owner element.
     *
     * @param id the feature's identifier
     *
     * @return this instance (for chaining)
     */
    @Nonnull
    public T setId(int id) {
        this.id = id;

        return me();
    }

    /**
     * Returns {@code true} if this feature is multi-valued.
     *
     * @return {@code true} if this feature is multi-valued
     */
    public boolean isMany() {
        return many;
    }

    /**
     * Defines whether this feature is multi-valued.
     *
     * @param isMany {@code true} if this feature is multi-valued
     *
     * @return this instance (for chaining)
     */
    @Nonnull
    public T setMany(boolean isMany) {
        this.many = isMany;

        return me();
    }

    /**
     * Returns the value of this feature.
     *
     * @return the value
     */
    @Nonnull
    public ProxyValue<V> getValue() {
        return value;
    }

    /**
     * Defines the value of this feature.
     *
     * @param value the value
     *
     * @return this instance (for chaining)
     */
    @Nonnull
    public T setValue(ProxyValue<V> value) {
        this.value = value;

        return me();
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

        AbstractProxyFeature<?, ?, ?> that = (AbstractProxyFeature) o;
        return Objects.equals(owner, that.owner);
    }
}
