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

package fr.inria.atlanmod.neoemf.io.mock;

import fr.inria.atlanmod.neoemf.io.structure.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.structure.BasicElement;
import fr.inria.atlanmod.neoemf.io.structure.BasicId;
import fr.inria.atlanmod.neoemf.io.structure.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.structure.BasicNamespace;
import fr.inria.atlanmod.neoemf.io.structure.BasicReference;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link BasicElement} wrapper that stores all elements in different {@link List}.
 */
public class DummyElement {

    /**
     * The original {@link BasicElement}.
     */
    private final BasicElement element;

    /**
     * A list that holds all attributes of this element.
     */
    private final List<BasicAttribute> attributes;

    /**
     * A list that holds all references of this element.
     */
    private final List<BasicReference> references;

    /**
     * A list that holds all children of this element.
     */
    private final List<DummyElement> children;

    /**
     * Constructs a new {@code DummyElement} from the given {@code element}.
     *
     * @param element the element to wrap
     */
    public DummyElement(BasicElement element) {
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
        return element.name();
    }

    /**
     * Returns the namespace of this element.
     *
     * @return the namespace
     */
    public BasicNamespace ns() {
        return element.ns();
    }

    /**
     * Returns whether this element is the root element of a structure.
     *
     * @return {@code true} if this element is a root element
     */
    public boolean isRoot() {
        return element.isRoot();
    }

    /**
     * Returns the name of the class of this element.
     *
     * @return the name of the class
     */
    public String className() {
        return element.className();
    }

    /**
     * Returns the identifier of this classifier.
     *
     * @return the identifier
     */
    public BasicId id() {
        return element.id();
    }

    /**
     * Returns the metaclass of this element.
     *
     * @return the metaclass
     */
    public BasicMetaclass metaClass() {
        return element.metaclass();
    }

    /**
     * Returns a list of all attributes of this element.
     *
     * @return a mutable list
     */
    public List<BasicAttribute> attributes() {
        return attributes;
    }

    /**
     * Returns a list of all references of this element.
     *
     * @return a mutable list
     */
    public List<BasicReference> references() {
        return references;
    }

    /**
     * Returns a list of all children of this element.
     *
     * @return a mutable list
     */
    public List<DummyElement> children() {
        return children;
    }
}
