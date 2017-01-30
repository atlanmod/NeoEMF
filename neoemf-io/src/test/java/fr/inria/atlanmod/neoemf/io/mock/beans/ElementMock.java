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

package fr.inria.atlanmod.neoemf.io.mock.beans;

import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Element;
import fr.inria.atlanmod.neoemf.io.structure.MetaClass;
import fr.inria.atlanmod.neoemf.io.structure.Namespace;
import fr.inria.atlanmod.neoemf.io.structure.RawId;
import fr.inria.atlanmod.neoemf.io.structure.Reference;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ElementMock {

    private final Element element;

    private final List<Attribute> attributes;
    private final List<Reference> references;

    private final List<ElementMock> elements;

    public ElementMock(Element element) {
        this.element = element;

        this.attributes = new ArrayList<>();
        this.references = new ArrayList<>();
        this.elements = new ArrayList<>();
    }

    public static ElementMock childFrom(ElementMock root, int... indexes) {
        if (indexes.length == 0) {
            throw new IllegalArgumentException("You must define at least one index");
        }

        ElementMock child = root;

        for (int index : indexes) {
            child = child.elements().get(index);
        }

        return child;
    }

    public String name() {
        return element.name();
    }

    public Namespace ns() {
        return element.ns();
    }

    public boolean isRoot() {
        return element.root();
    }

    public String className() {
        return element.className();
    }

    public RawId id() {
        return element.id();
    }

    public MetaClass metaClass() {
        return element.metaClass();
    }

    public List<Attribute> attributes() {
        return attributes;
    }

    public List<Reference> references() {
        return references;
    }

    public List<ElementMock> elements() {
        return elements;
    }
}
