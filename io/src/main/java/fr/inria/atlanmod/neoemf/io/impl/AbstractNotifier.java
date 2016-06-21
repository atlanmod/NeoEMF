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

    protected final void notifyStartDocument() throws Exception {
        for (T h : getHandlers()) {
            h.handleStartDocument();
        }
    }

    protected final void notifyStartElement(String namespace, String localName, String reference) throws Exception {
        for (T h : getHandlers()) {
            h.handleStartElement(namespace, localName, reference);
        }
    }

    protected final void notifyAttribute(String namespace, String localName, String value) throws Exception {
        for (T h : getHandlers()) {
            h.handleAttribute(namespace, localName, value);
        }
    }

    protected final void notifyReference(String namespace, String localName, String reference) throws Exception {
        for (T h : getHandlers()) {
            h.handleReference(namespace, localName, reference);
        }
    }

    protected final void notifyEndElement() throws Exception {
        for (T h : getHandlers()) {
            h.handleEndElement();
        }
    }

    protected final void notifyEndDocument() throws Exception {
        for (T h : getHandlers()) {
            h.handleEndDocument();
        }
    }
}
