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
public class MetaClass extends NamedElement {

    /**
     * The instance of the default {@code MetaClass}.
     */
    private static final MetaClass DEFAULT = new MetaClass(Namespace.getDefault(), "EObject");

    /**
     * The namespace of this {@code MetaClass}.
     */
    private Namespace ns;

    /**
     * Constructs a new {@code MetaClass} with the given {@code namespace} and {@code name}.
     *
     * @param ns   the namespace of this {@code MetaClass}
     * @param name the name of this {@code MetaClass}
     */
    public MetaClass(Namespace ns, String name) {
        super(name);
        this.ns = ns;
    }

    /**
     * Returns the default {@code MetaClass}.
     *
     * @return the {@code MetaClass} representing "ecore:EObject"
     */
    public static MetaClass getDefault() {
        return DEFAULT;
    }

    /**
     * Returns the namespace of this {@code MetaClass}.
     *
     * @return the namespace
     */
    public Namespace ns() {
        return ns;
    }

    /**
     * Defines the namespace of this {@code MetaClass}.
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
