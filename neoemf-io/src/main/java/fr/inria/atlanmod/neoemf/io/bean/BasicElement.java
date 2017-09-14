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
 * A simple representation of an identifiable element, typed with a meta-class.
 */
@ParametersAreNonnullByDefault
public class BasicElement extends AbstractNamedElement {

    /**
     * The identifier of this element.
     */
    private Id id;

    /**
     * The meta-class of this element.
     */
    private BasicMetaclass metaClass;

    /**
     * Whether this element is the root of a structure.
     */
    private boolean isRoot;

    /**
     * Returns the identifier of this element.
     *
     * @return the identifier
     */
    public Id id() {
        return id;
    }

    /**
     * Defines the identifier of this element.
     *
     * @param id the identifier
     */
    public void id(Id id) {
        this.id = id;
    }

    /**
     * Returns the meta-class of this element.
     *
     * @return the meta-class
     */
    public BasicMetaclass metaClass() {
        return metaClass;
    }

    /**
     * Defines the meta-class of this element.
     *
     * @param metaClass the meta-class
     */
    public void metaClass(BasicMetaclass metaClass) {
        this.metaClass = metaClass;
    }

    /**
     * Returns whether this element is the root of a structure.
     *
     * @return {@code true} if this element is the root of a structure
     */
    public boolean isRoot() {
        return isRoot;
    }

    /**
     * Defines whether this element is the root of a structure.
     *
     * @param isRoot {@code true} if this element is the root of a structure
     */
    public void isRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BasicElement that = BasicElement.class.cast(o);
        return Objects.equals(id, that.id);
    }
}
