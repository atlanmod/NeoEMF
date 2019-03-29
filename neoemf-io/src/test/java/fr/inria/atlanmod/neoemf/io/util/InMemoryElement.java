/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.util;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyClass;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyElement;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link ProxyElement} wrapper that stores all elements in different {@link List}.
 */
@ParametersAreNonnullByDefault
public final class InMemoryElement {

    /**
     * The original {@link ProxyElement}.
     */
    private final ProxyElement element;

    /**
     * A list that holds all attributes of this element.
     */
    private final List<ProxyAttribute> attributes;

    /**
     * A list that holds all references of this element.
     */
    private final List<ProxyReference> references;

    /**
     * A list that holds all children of this element.
     */
    private final List<InMemoryElement> children;

    /**
     * Constructs a new {@code InMemoryElement} from the given {@code element}.
     *
     * @param element the element to wrap
     */
    public InMemoryElement(ProxyElement element) {
        this.element = element;

        this.attributes = new ArrayList<>();
        this.references = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    /**
     * Returns the name of this element.
     *
     * @return the name
     */
    public String name() {
        return element.getName();
    }

    /**
     * Returns the identifier of this classifier.
     *
     * @return the identifier
     */
    public Id id() {
        return element.getId().getResolved();
    }

    /**
     * Returns the meta-class of this element.
     *
     * @return the meta-class
     */
    public ProxyClass metaClass() {
        return element.getMetaClass();
    }

    /**
     * Returns a list of all attributes of this element.
     *
     * @return a mutable list
     */
    public List<ProxyAttribute> attributes() {
        return attributes;
    }

    /**
     * Returns a list of all references of this element.
     *
     * @return a mutable list
     */
    public List<ProxyReference> references() {
        return references;
    }

    /**
     * Returns a list of all children of this element.
     *
     * @return a mutable list
     */
    public List<InMemoryElement> children() {
        return children;
    }
}
