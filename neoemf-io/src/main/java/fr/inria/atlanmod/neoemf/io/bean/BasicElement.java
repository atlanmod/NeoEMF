/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.bean;

import fr.inria.atlanmod.neoemf.core.Id;

import java.util.Objects;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A simple representation of a {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}.
 */
@ParametersAreNonnullByDefault
public class BasicElement extends AbstractNamedElement<BasicElement> {

    /**
     * The identifier of this element.
     */
    private Id id;

    /**
     * The string representation of the identifier.
     */
    private String rawId;

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
     *
     * @return this instance (for chaining)
     */
    public BasicElement id(Id id) {
        this.id = id;

        return me();
    }

    /**
     * Returns the string representation of the identifier.
     *
     * @return the string representation of the identifier
     */
    public String rawId() {
        return rawId;
    }

    /**
     * Defines the string representation of the identifier.
     *
     * @param rawId the string representation of the identifier
     *
     * @return this instance (for chaining)
     */
    public BasicElement rawId(@Nullable String rawId) {
        this.rawId = rawId;

        return me();
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
     *
     * @return this instance (for chaining)
     */
    public BasicElement metaClass(BasicMetaclass metaClass) {
        this.metaClass = metaClass;

        return me();
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
     *
     * @return this instance (for chaining)
     */
    public BasicElement isRoot(boolean isRoot) {
        this.isRoot = isRoot;

        return me();
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
