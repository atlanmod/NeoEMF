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

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import fr.inria.atlanmod.neoemf.io.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.Classifier;
import fr.inria.atlanmod.neoemf.io.beans.Reference;
import fr.inria.atlanmod.neoemf.io.mock.beans.ClassifierMock;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *
 */
public class StructuralPersistanceHandler implements PersistenceHandler {

    private final Cache<String, ClassifierMock> classifierMockCache;

    private final Deque<ClassifierMock> classifierStack;

    private final List<ClassifierMock> elements;

    public StructuralPersistanceHandler() {
        this.classifierMockCache = CacheBuilder.newBuilder().build();
        this.classifierStack = new ArrayDeque<>();
        this.elements = new ArrayList<>();
    }

    public List<ClassifierMock> getElements() {
        return elements;
    }

    @Override
    public void handleStartDocument() throws Exception {
        // Do nothing
    }

    @Override
    public void handleStartElement(Classifier classifier) throws Exception {
        ClassifierMock mock = new ClassifierMock(classifier);

        if (!classifierStack.isEmpty()) {
            classifierStack.getLast().getElements().add(mock);
        } else {
            elements.add(mock);
        }

        if (classifier.getId() != null) {
            classifierMockCache.put(classifier.getId().getValue(), mock);
        }
        classifierStack.addLast(mock);
    }

    @Override
    public void handleAttribute(Attribute attribute) throws Exception {
        if (attribute.getId() == null || attribute.getId().equals(classifierStack.getLast().getId())) {
            classifierStack.getLast().getAttributes().add(attribute);
        } else {
            ClassifierMock mock = classifierMockCache.getIfPresent(attribute.getId().getValue());
            if (mock != null && mock.getId().equals(attribute.getId())) {
                mock.getAttributes().add(attribute);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void handleReference(Reference reference) throws Exception {
        if (reference.getId() == null || reference.getId().equals(classifierStack.getLast().getId())) {
            classifierStack.getLast().getReferences().add(reference);
        } else {
            ClassifierMock mock = classifierMockCache.getIfPresent(reference.getId().getValue());
            if (mock != null && mock.getId().equals(reference.getId())) {
                mock.getReferences().add(reference);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void handleEndElement() throws Exception {
        classifierStack.removeLast();
    }

    @Override
    public void handleEndDocument() throws Exception {
        // Do nothing
    }
}
