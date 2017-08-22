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

import java.util.Objects;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A simple representation of a structural feature, which can be either a reference or an attribute.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractBasicFeature extends AbstractNamedElement {

    /**
     * The identifier of element that owns this feature.
     */
    private BasicId owner;

    /**
     * The index of the feature.
     */
    private int index = -1;

    /**
     * Whether this feature is multi-valued.
     */
    private boolean isMany = false;

    /**
     * Returns the identifier of the element that owns this feature.
     *
     * @return the owner's identifier
     */
    public BasicId owner() {
        return owner;
    }

    /**
     * Defines the identifier of the element that owns this feature.
     *
     * @param ownerId the owner's identifier
     */
    public void owner(BasicId ownerId) {
        this.owner = ownerId;
    }

    /**
     * Returns the index of this feature.
     *
     * @return the index
     */
    public int index() {
        return index;
    }

    /**
     * Defines the index of this feature.
     *
     * @param index the index
     */
    public void index(int index) {
        this.index = index;
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
     * Defines whether this feature is a {@link BasicReference}.
     *
     * @return {@code true} if this feature is a reference.
     */
    public boolean isReference() {
        return false;
    }

    /**
     * Defines whether this feature is an {@link BasicAttribute}.
     *
     * @return {@code true} if this feature is an attribute.
     */
    public boolean isAttribute() {
        return false;
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
        if (!AbstractBasicFeature.class.isInstance(o)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        AbstractBasicFeature that = AbstractBasicFeature.class.cast(o);
        return Objects.equals(owner, that.owner);
    }
}
