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
public abstract class AbstractBasicFeature extends AbstractBasicElement {

    /**
     * The identifier of the feature.
     */
    private BasicId id;

    /**
     * The index of the feature.
     */
    private int index;

    /**
     * Whether this feature is multi-valued.
     */
    private boolean isMany;

    /**
     * Constructs a new {@code AbstractBasicFeature} with the given {@code name}. Its index is initialized to {@code
     * -1}.
     *
     * @param name the name of this feature
     */
    public AbstractBasicFeature(String name) {
        super(name);
        this.index = -1;
        this.isMany = false;
    }

    /**
     * Returns the identifier of this feature.
     *
     * @return the identifier
     */
    public BasicId id() {
        return id;
    }

    /**
     * Defines the identifier of this feature.
     *
     * @param id the identifier
     */
    public void id(BasicId id) {
        this.id = id;
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
        return Objects.hash(super.hashCode(), id);
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
        return Objects.equals(id, that.id);
    }
}
