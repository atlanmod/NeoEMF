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

import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.mock.beans.ClassifierMock;
import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawClassifier;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;

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
public class StructuralPersistanceHandler implements Handler {

    private final Cache<String, ClassifierMock> classifierMockCache;

    private final Deque<ClassifierMock> classifierStack;

    private final List<ClassifierMock> elements;

    public StructuralPersistanceHandler() {
        this.classifierMockCache = Caffeine.newBuilder().build();
        this.classifierStack = new ArrayDeque<>();
        this.elements = new ArrayList<>();
    }

    public List<ClassifierMock> getElements() {
        return elements;
    }

    @Override
    public void processStartDocument() {
        // Do nothing
    }

    @Override
    public void processStartElement(RawClassifier classifier) {
        ClassifierMock mock = new ClassifierMock(classifier);

        if (!classifierStack.isEmpty()) {
            classifierStack.getLast().getElements().add(mock);
        }
        else {
            elements.add(mock);
        }

        if (nonNull(classifier.id())) {
            classifierMockCache.put(classifier.id().value(), mock);
        }
        classifierStack.addLast(mock);
    }

    @Override
    public void processAttribute(RawAttribute attribute) {
        if (isNull(attribute.id()) || attribute.id().equals(classifierStack.getLast().getId())) {
            classifierStack.getLast().getAttributes().add(attribute);
        }
        else {
            ClassifierMock mock = classifierMockCache.getIfPresent(attribute.id().value());
            if (nonNull(mock) && Objects.equals(mock.getId(), attribute.id())) {
                mock.getAttributes().add(attribute);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void processReference(RawReference reference) {
        if (isNull(reference.id()) || reference.id().equals(classifierStack.getLast().getId())) {
            classifierStack.getLast().getReferences().add(reference);
        }
        else {
            ClassifierMock mock = classifierMockCache.getIfPresent(reference.id().value());
            if (nonNull(mock) && Objects.equals(mock.getId(), reference.id())) {
                mock.getReferences().add(reference);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void processEndElement() {
        classifierStack.removeLast();
    }

    @Override
    public void processEndDocument() {
        // Do nothing
    }

    @Override
    public void processCharacters(String characters) {
        // Do nothing
    }
}
