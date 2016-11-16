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

package fr.inria.atlanmod.neoemf.io.impl;

import fr.inria.atlanmod.neoemf.io.InputHandler;
import fr.inria.atlanmod.neoemf.io.InputNotifier;
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
import fr.inria.atlanmod.neoemf.io.structure.Reference;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * An abstract implementation of a {@link InputNotifier}.
 */
public abstract class AbstractInputNotifier<T extends InputHandler> implements InputNotifier<T> {

    private final Set<T> handlers;

    public AbstractInputNotifier() {
        this.handlers = new HashSet<>();
    }

    @Override
    public void addHandler(T handler) {
        handlers.add(handler);
    }

    @Override
    public boolean hasHandler() {
        return !handlers.isEmpty();
    }

    @Override
    public Iterable<T> getHandlers() {
        return Collections.unmodifiableSet(handlers);
    }

    @Override
    public void notifyStartDocument() throws Exception {
        for (T h : getHandlers()) {
            h.processStartDocument();
        }
    }

    @Override
    public void notifyStartElement(Classifier classifier) throws Exception {
        for (T h : getHandlers()) {
            h.processStartElement(classifier);
        }
    }

    @Override
    public void notifyAttribute(Attribute attribute) throws Exception {
        for (T h : getHandlers()) {
            h.processAttribute(attribute);
        }
    }

    @Override
    public void notifyReference(Reference reference) throws Exception {
        for (T h : getHandlers()) {
            h.processReference(reference);
        }
    }

    @Override
    public void notifyEndElement() throws Exception {
        for (T h : getHandlers()) {
            h.processEndElement();
        }
    }

    @Override
    public void notifyEndDocument() throws Exception {
        for (T h : getHandlers()) {
            h.processEndDocument();
        }
    }
}
