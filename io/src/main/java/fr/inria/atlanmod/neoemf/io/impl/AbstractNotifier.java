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

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public abstract class AbstractNotifier implements Notifier {

    private Set<Handler> handlers;

    public AbstractNotifier() {
        this.handlers = new HashSet<>();
    }

    @Override
    public void addHandler(Handler handler) {
        handlers.add(handler);
    }

    @Override
    public boolean hasHandler() {
        return !handlers.isEmpty();
    }

    protected final void notifyStartDocument() {
        for (Handler h : handlers) {
            h.handleStartDocument();
        }
    }

    protected final void notifyStartElement(String namespace, String localName) {
        for (Handler h : handlers) {
            h.handleStartElement(namespace, localName);
        }
    }

    protected final void notifyAttribute(String namespace, String localName, String value) {
        for (Handler h : handlers) {
            h.handleAttribute(namespace, localName, value);
        }
    }

    protected final void notifyReference(String namespace, String localName, String reference) {
        for (Handler h : handlers) {
            h.handleReference(namespace, localName, reference);
        }
    }

    protected final void notifyEndElement() {
        for (Handler h : handlers) {
            h.handleEndElement();
        }
    }

    protected final void notifyEndDocument() {
        for (Handler h : handlers) {
            h.handleEndDocument();
        }
    }
}
