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

import javax.annotation.Nonnull;
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
    @Nonnull
    private Data<Id> id = Data.empty();

    /**
     * The meta-class of this element.
     */
    private BasicClass metaClass;

    /**
     * Whether this element is the root of a structure.
     */
    private boolean root;

    /**
     * Returns the identifier of this element.
     *
     * @return the identifier
     */
    @Nonnull
    public Data<Id> getId() {
        return id;
    }

    /**
     * Defines the identifier of this element.
     *
     * @param id the identifier
     *
     * @return this instance (for chaining)
     */
    @Nonnull
    public BasicElement setId(Data<Id> id) {
        this.id = id;

        return me();
    }

    /**
     * Returns the meta-class of this element.
     *
     * @return the meta-class
     */
    public BasicClass getMetaClass() {
        return metaClass;
    }

    /**
     * Defines the meta-class of this element.
     *
     * @param metaClass the meta-class
     *
     * @return this instance (for chaining)
     */
    @Nonnull
    public BasicElement setMetaClass(BasicClass metaClass) {
        this.metaClass = metaClass;

        return me();
    }

    /**
     * Returns whether this element is the root of a structure.
     *
     * @return {@code true} if this element is the root of a structure
     */
    public boolean isRoot() {
        return root;
    }

    /**
     * Defines whether this element is the root of a structure.
     *
     * @param isRoot {@code true} if this element is the root of a structure
     *
     * @return this instance (for chaining)
     */
    @Nonnull
    public BasicElement setRoot(boolean isRoot) {
        this.root = isRoot;

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
