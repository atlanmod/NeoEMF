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

package fr.inria.atlanmod.neoemf.io.internal;

import fr.inria.atlanmod.neoemf.io.Notifier;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public abstract class AbstractInternalNotifier implements Notifier<InternalHandler> {

    private Set<InternalHandler> handlers;

    public AbstractInternalNotifier() {
        this.handlers = new HashSet<>();
    }

    @Override
    public void addHandler(InternalHandler handler) {
        handlers.add(handler);
    }

    @Override
    public boolean hasHandler() {
        return !handlers.isEmpty();
    }

    protected final void notifyStartDocument() {
        for (InternalHandler h : handlers) {
            h.handleStartDocument();
        }
    }

    protected final void notifyStartElement(String namespace, String localName) {
        for (InternalHandler h : handlers) {
            h.handleStartElement(namespace, localName);
        }
    }

    protected final void notifyAttribute(String namespace, String localName, String value) {
        for (InternalHandler h : handlers) {
            h.handleAttribute(namespace, localName, value);
        }
    }

    protected final void notifyReference(String namespace, String localName, String reference) {
        for (InternalHandler h : handlers) {
            h.handleReference(namespace, localName, reference);
        }
    }

    protected final void notifyEndElement() {
        for (InternalHandler h : handlers) {
            h.handleEndElement();
        }
    }

    protected final void notifyEndDocument() {
        for (InternalHandler h : handlers) {
            h.handleEndDocument();
        }
    }
}
