/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
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

import fr.inria.atlanmod.neoemf.io.mock.beans.ClassifierMock;
import fr.inria.atlanmod.neoemf.io.persistence.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
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
    public void processStartDocument() throws Exception {
        // Do nothing
    }

    @Override
    public void processStartElement(Classifier classifier) throws Exception {
        ClassifierMock mock = new ClassifierMock(classifier);

        if (!classifierStack.isEmpty()) {
            classifierStack.getLast().getElements().add(mock);
        }
        else {
            elements.add(mock);
        }

        if (nonNull(classifier.getId())) {
            classifierMockCache.put(classifier.getId().getValue(), mock);
        }
        classifierStack.addLast(mock);
    }

    @Override
    public void processAttribute(Attribute attribute) throws Exception {
        if (isNull(attribute.getId()) || attribute.getId().equals(classifierStack.getLast().getId())) {
            classifierStack.getLast().getAttributes().add(attribute);
        }
        else {
            ClassifierMock mock = classifierMockCache.getIfPresent(attribute.getId().getValue());
            if (nonNull(mock) && Objects.equals(mock.getId(), attribute.getId())) {
                mock.getAttributes().add(attribute);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void processReference(Reference reference) throws Exception {
        if (isNull(reference.getId()) || reference.getId().equals(classifierStack.getLast().getId())) {
            classifierStack.getLast().getReferences().add(reference);
        }
        else {
            ClassifierMock mock = classifierMockCache.getIfPresent(reference.getId().getValue());
            if (nonNull(mock) && Objects.equals(mock.getId(), reference.getId())) {
                mock.getReferences().add(reference);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void processEndElement() throws Exception {
        classifierStack.removeLast();
    }

    @Override
    public void processEndDocument() throws Exception {
        // Do nothing
    }
}
