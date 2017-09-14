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

package fr.inria.atlanmod.neoemf.io.bean;

import fr.inria.atlanmod.neoemf.core.Id;

import java.util.Objects;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A simple representation of a structural feature, which can be either a reference or an attribute.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractBasicFeature<V> extends AbstractNamedElement {

    /**
     * The identifier of element that owns this feature.
     */
    private Id owner;

    /**
     * Whether this feature is multi-valued.
     */
    private boolean isMany = false;

    /**
     * The value of this feature.
     */
    private V value;

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
     * @param ownerId the owner's identifier
     */
    public void owner(Id ownerId) {
        this.owner = ownerId;
    }

    /**
     * Sets whether this feature is multi-valued, or not.
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
     */
    public void isMany(boolean isMany) {
        this.isMany = isMany;
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
     */
    public void value(V value) {
        this.value = value;
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

        AbstractBasicFeature<?> that = AbstractBasicFeature.class.cast(o);
        return Objects.equals(owner, that.owner);
    }
}
