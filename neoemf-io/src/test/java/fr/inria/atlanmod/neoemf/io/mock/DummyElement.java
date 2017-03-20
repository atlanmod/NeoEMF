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

import fr.inria.atlanmod.neoemf.io.structure.Namespace;
import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawElement;
import fr.inria.atlanmod.neoemf.io.structure.RawId;
import fr.inria.atlanmod.neoemf.io.structure.RawMetaclass;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;

import java.util.ArrayList;
import java.util.List;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;

/**
 * A {@link RawElement} wrapper that stores all elements in different {@link List}.
 */
public class DummyElement {

    /**
     * The original {@link RawElement}.
     */
    private final RawElement element;

    /**
     * A list that holds all attributes of this element.
     */
    private final List<RawAttribute> attributes;

    /**
     * A list that holds all references of this element.
     */
    private final List<RawReference> references;

    /**
     * A list that holds all children of this element.
     */
    private final List<DummyElement> children;

    /**
     * Constructs a new {@code DummyElement} from the given {@code element}.
     *
     * @param element the element to wrap
     */
    public DummyElement(RawElement element) {
        this.element = element;

        this.attributes = new ArrayList<>();
        this.references = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    /**
     * Retrieves an element from the {@code root} element with the successive {@code indexes}.
     *
     * @param root    the element from which to start the search
     * @param indexes the index of the element, recursively in the children from the {@code root}
     *
     * @return the element
     */
    public static DummyElement childFrom(DummyElement root, int... indexes) {
        checkArgument(indexes.length > 0, "You must define at least one index");

        DummyElement child = root;

        for (int index : indexes) {
            child = child.children().get(index);
        }

        return child;
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
    public Namespace ns() {
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
    public RawId id() {
        return element.id();
    }

    /**
     * Returns the metaclass of this element.
     *
     * @return the metaclass
     */
    public RawMetaclass metaClass() {
        return element.metaclass();
    }

    /**
     * Returns a list of all attributes of this element.
     *
     * @return a mutable list
     */
    public List<RawAttribute> attributes() {
        return attributes;
    }

    /**
     * Returns a list of all references of this element.
     *
     * @return a mutable list
     */
    public List<RawReference> references() {
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
