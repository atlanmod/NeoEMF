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

/**
 * A simple representation of a element with a {@link Namespace} and a name.
 */
public class RawMetaclass extends RawNamedElement {

    /**
     * The instance of the default {@code RawMetaclass}.
     */
    private static final RawMetaclass DEFAULT = new RawMetaclass(Namespace.getDefault(), "EObject");

    /**
     * The namespace of this {@code RawMetaclass}.
     */
    private Namespace ns;

    /**
     * Constructs a new {@code RawMetaclass} with the given {@code namespace} and {@code name}.
     *
     * @param ns   the namespace of this {@code RawMetaclass}
     * @param name the name of this {@code RawMetaclass}
     */
    public RawMetaclass(Namespace ns, String name) {
        super(name);
        this.ns = ns;
    }

    /**
     * Returns the default {@code RawMetaclass}.
     *
     * @return the {@code RawMetaclass} representing "ecore:EObject"
     */
    public static RawMetaclass getDefault() {
        return DEFAULT;
    }

    /**
     * Returns the namespace of this {@code RawMetaclass}.
     *
     * @return the namespace
     */
    public Namespace ns() {
        return ns;
    }

    /**
     * Defines the namespace of this {@code RawMetaclass}.
     *
     * @param ns the namespace
     */
    public void ns(Namespace ns) {
        this.ns = ns;
    }

    @Override
    public String toString() {
        return ns.prefix() + ':' + name();
    }
}
