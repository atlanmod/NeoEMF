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

import fr.inria.atlanmod.neoemf.io.mock.beans.ElementMock;
import fr.inria.atlanmod.neoemf.io.persistence.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawElement;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 *
 */
public class StructuralPersistanceHandler implements PersistenceHandler {

    private final Map<String, ElementMock> elementMocks;

    private final Deque<ElementMock> elementsStack;

    private final List<ElementMock> elements;

    public StructuralPersistanceHandler() {
        this.elementMocks = new HashMap<>();
        this.elementsStack = new ArrayDeque<>();
        this.elements = new ArrayList<>();
    }

    public List<ElementMock> getElements() {
        return elements;
    }

    @Override
    public void handleStartDocument() {
        // Do nothing
    }

    @Override
    public void handleStartElement(RawElement element) {
        ElementMock mock = new ElementMock(element);

        if (!elementsStack.isEmpty()) {
            elementsStack.getLast().elements().add(mock);
        }
        else {
            elements.add(mock);
        }

        if (nonNull(element.id())) {
            elementMocks.put(element.id().value(), mock);
        }
        elementsStack.addLast(mock);
    }

    @Override
    public void handleAttribute(RawAttribute attribute) {
        if (isNull(attribute.id()) || attribute.id().equals(elementsStack.getLast().id())) {
            elementsStack.getLast().attributes().add(attribute);
        }
        else {
            ElementMock mock = elementMocks.get(attribute.id().value());
            if (nonNull(mock) && Objects.equals(mock.id(), attribute.id())) {
                mock.attributes().add(attribute);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void handleReference(RawReference reference) {
        if (isNull(reference.id()) || reference.id().equals(elementsStack.getLast().id())) {
            elementsStack.getLast().references().add(reference);
        }
        else {
            ElementMock mock = elementMocks.get(reference.id().value());
            if (nonNull(mock) && Objects.equals(mock.id(), reference.id())) {
                mock.references().add(reference);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void handleEndElement() {
        elementsStack.removeLast();
    }

    @Override
    public void handleEndDocument() {
        // Do nothing
    }

    @Override
    public void handleCharacters(String characters) {
        // Do nothing
    }
}
