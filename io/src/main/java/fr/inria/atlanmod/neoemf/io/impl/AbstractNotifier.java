/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.impl;

import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.Notifier;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public abstract class AbstractNotifier<T extends Handler> implements Notifier<T> {

    private Set<T> handlers;

    public AbstractNotifier() {
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
    public final void notifyStartDocument() throws Exception {
        for (T h : getHandlers()) {
            h.handleStartDocument();
        }
    }

    @Override
    public final void notifyStartElement(String nsUri, String name, String reference) throws Exception {
        for (T h : getHandlers()) {
            h.handleStartElement(nsUri, name, reference);
        }
    }

    @Override
    public final void notifyMetaClass(String nsUri, String name) throws Exception {
        for (T h : getHandlers()) {
            h.handleMetaClass(nsUri, name);
        }
    }

    @Override
    public final void notifyAttribute(String nsUri, String name, int index, String value) throws Exception {
        for (T h : getHandlers()) {
            h.handleAttribute(nsUri, name, index, value);
        }
    }

    @Override
    public final void notifyReference(String nsUri, String name, int index, String reference) throws Exception {
        for (T h : getHandlers()) {
            h.handleReference(nsUri, name, index, reference);
        }
    }

    @Override
    public final void notifyEndElement() throws Exception {
        for (T h : getHandlers()) {
            h.handleEndElement();
        }
    }

    @Override
    public final void notifyEndDocument() throws Exception {
        for (T h : getHandlers()) {
            h.handleEndDocument();
        }
    }
}
