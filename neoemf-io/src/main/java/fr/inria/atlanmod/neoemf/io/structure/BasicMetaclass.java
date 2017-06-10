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

package fr.inria.atlanmod.neoemf.io.structure;

import java.util.Objects;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A simple representation of a element with a {@link BasicNamespace} and a name.
 */
@ParametersAreNonnullByDefault
public class BasicMetaclass extends BasicNamedElement {

    /**
     * The instance of the default {@code BasicMetaclass}.
     */
    private static final BasicMetaclass DEFAULT = new BasicMetaclass(BasicNamespace.getDefault(), "EObject");

    /**
     * The namespace of this {@code BasicMetaclass}.
     */
    private BasicNamespace ns;

    /**
     * Constructs a new {@code BasicMetaclass} with the given {@code namespace} and {@code name}.
     *
     * @param ns   the namespace of this {@code BasicMetaclass}
     * @param name the name of this {@code BasicMetaclass}
     */
    public BasicMetaclass(BasicNamespace ns, String name) {
        super(name);
        this.ns = ns;
    }

    /**
     * Returns the default {@code BasicMetaclass}.
     *
     * @return the {@code BasicMetaclass} representing "ecore:EObject"
     */
    public static BasicMetaclass getDefault() {
        return DEFAULT;
    }

    /**
     * Returns the namespace of this {@code BasicMetaclass}.
     *
     * @return the namespace
     */
    public BasicNamespace ns() {
        return ns;
    }

    /**
     * Defines the namespace of this {@code BasicMetaclass}.
     *
     * @param ns the namespace
     */
    public void ns(BasicNamespace ns) {
        this.ns = ns;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ns);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        BasicMetaclass that = (BasicMetaclass) o;
        return Objects.equals(ns, that.ns);
    }

    @Override
    public String toString() {
        return ns.prefix() + ':' + name();
    }
}
