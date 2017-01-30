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

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.io.mock.beans.ElementMock;
import fr.inria.atlanmod.neoemf.io.persistence.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Element;
import fr.inria.atlanmod.neoemf.io.structure.Reference;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 *
 */
public class StructuralPersistanceHandler implements PersistenceHandler {

    private final Cache<String, ElementMock> classifierMockCache;

    private final Deque<ElementMock> classifierStack;

    private final List<ElementMock> elements;

    public StructuralPersistanceHandler() {
        this.classifierMockCache = Caffeine.newBuilder().build();
        this.classifierStack = new ArrayDeque<>();
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
    public void handleStartElement(Element element) {
        ElementMock mock = new ElementMock(element);

        if (!classifierStack.isEmpty()) {
            classifierStack.getLast().elements().add(mock);
        }
        else {
            elements.add(mock);
        }

        if (nonNull(element.id())) {
            classifierMockCache.put(element.id().value(), mock);
        }
        classifierStack.addLast(mock);
    }

    @Override
    public void handleAttribute(Attribute attribute) {
        if (isNull(attribute.id()) || attribute.id().equals(classifierStack.getLast().id())) {
            classifierStack.getLast().attributes().add(attribute);
        }
        else {
            ElementMock mock = classifierMockCache.getIfPresent(attribute.id().value());
            if (nonNull(mock) && Objects.equals(mock.id(), attribute.id())) {
                mock.attributes().add(attribute);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void handleReference(Reference reference) {
        if (isNull(reference.id()) || reference.id().equals(classifierStack.getLast().id())) {
            classifierStack.getLast().references().add(reference);
        }
        else {
            ElementMock mock = classifierMockCache.getIfPresent(reference.id().value());
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
        classifierStack.removeLast();
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
